/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JPA;

import JPA.exceptions.IllegalOrphanException;
import JPA.exceptions.NonexistentEntityException;
import JPA.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Ruben
 */
public class CentroJpaController implements Serializable {

    public CentroJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Centro centro) throws PreexistingEntityException, Exception {
        if (centro.getMenorCollection() == null) {
            centro.setMenorCollection(new ArrayList<Menor>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Provincia idprov = centro.getIdprov();
            if (idprov != null) {
                idprov = em.getReference(idprov.getClass(), idprov.getIdprov());
                centro.setIdprov(idprov);
            }
            Collection<Menor> attachedMenorCollection = new ArrayList<Menor>();
            for (Menor menorCollectionMenorToAttach : centro.getMenorCollection()) {
                menorCollectionMenorToAttach = em.getReference(menorCollectionMenorToAttach.getClass(), menorCollectionMenorToAttach.getCodmenor());
                attachedMenorCollection.add(menorCollectionMenorToAttach);
            }
            centro.setMenorCollection(attachedMenorCollection);
            em.persist(centro);
            if (idprov != null) {
                idprov.getCentroCollection().add(centro);
                idprov = em.merge(idprov);
            }
            for (Menor menorCollectionMenor : centro.getMenorCollection()) {
                Centro oldIdcentroOfMenorCollectionMenor = menorCollectionMenor.getIdcentro();
                menorCollectionMenor.setIdcentro(centro);
                menorCollectionMenor = em.merge(menorCollectionMenor);
                if (oldIdcentroOfMenorCollectionMenor != null) {
                    oldIdcentroOfMenorCollectionMenor.getMenorCollection().remove(menorCollectionMenor);
                    oldIdcentroOfMenorCollectionMenor = em.merge(oldIdcentroOfMenorCollectionMenor);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCentro(centro.getIdcentro()) != null) {
                throw new PreexistingEntityException("Centro " + centro + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Centro centro) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Centro persistentCentro = em.find(Centro.class, centro.getIdcentro());
            Provincia idprovOld = persistentCentro.getIdprov();
            Provincia idprovNew = centro.getIdprov();
            Collection<Menor> menorCollectionOld = persistentCentro.getMenorCollection();
            Collection<Menor> menorCollectionNew = centro.getMenorCollection();
            List<String> illegalOrphanMessages = null;
            for (Menor menorCollectionOldMenor : menorCollectionOld) {
                if (!menorCollectionNew.contains(menorCollectionOldMenor)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Menor " + menorCollectionOldMenor + " since its idcentro field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idprovNew != null) {
                idprovNew = em.getReference(idprovNew.getClass(), idprovNew.getIdprov());
                centro.setIdprov(idprovNew);
            }
            Collection<Menor> attachedMenorCollectionNew = new ArrayList<Menor>();
            for (Menor menorCollectionNewMenorToAttach : menorCollectionNew) {
                menorCollectionNewMenorToAttach = em.getReference(menorCollectionNewMenorToAttach.getClass(), menorCollectionNewMenorToAttach.getCodmenor());
                attachedMenorCollectionNew.add(menorCollectionNewMenorToAttach);
            }
            menorCollectionNew = attachedMenorCollectionNew;
            centro.setMenorCollection(menorCollectionNew);
            centro = em.merge(centro);
            if (idprovOld != null && !idprovOld.equals(idprovNew)) {
                idprovOld.getCentroCollection().remove(centro);
                idprovOld = em.merge(idprovOld);
            }
            if (idprovNew != null && !idprovNew.equals(idprovOld)) {
                idprovNew.getCentroCollection().add(centro);
                idprovNew = em.merge(idprovNew);
            }
            for (Menor menorCollectionNewMenor : menorCollectionNew) {
                if (!menorCollectionOld.contains(menorCollectionNewMenor)) {
                    Centro oldIdcentroOfMenorCollectionNewMenor = menorCollectionNewMenor.getIdcentro();
                    menorCollectionNewMenor.setIdcentro(centro);
                    menorCollectionNewMenor = em.merge(menorCollectionNewMenor);
                    if (oldIdcentroOfMenorCollectionNewMenor != null && !oldIdcentroOfMenorCollectionNewMenor.equals(centro)) {
                        oldIdcentroOfMenorCollectionNewMenor.getMenorCollection().remove(menorCollectionNewMenor);
                        oldIdcentroOfMenorCollectionNewMenor = em.merge(oldIdcentroOfMenorCollectionNewMenor);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Short id = centro.getIdcentro();
                if (findCentro(id) == null) {
                    throw new NonexistentEntityException("The centro with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Short id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Centro centro;
            try {
                centro = em.getReference(Centro.class, id);
                centro.getIdcentro();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The centro with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Menor> menorCollectionOrphanCheck = centro.getMenorCollection();
            for (Menor menorCollectionOrphanCheckMenor : menorCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Centro (" + centro + ") cannot be destroyed since the Menor " + menorCollectionOrphanCheckMenor + " in its menorCollection field has a non-nullable idcentro field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Provincia idprov = centro.getIdprov();
            if (idprov != null) {
                idprov.getCentroCollection().remove(centro);
                idprov = em.merge(idprov);
            }
            em.remove(centro);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Centro> findCentroEntities() {
        return findCentroEntities(true, -1, -1);
    }

    public List<Centro> findCentroEntities(int maxResults, int firstResult) {
        return findCentroEntities(false, maxResults, firstResult);
    }

    private List<Centro> findCentroEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Centro.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Centro findCentro(Short id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Centro.class, id);
        } finally {
            em.close();
        }
    }

    public int getCentroCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Centro> rt = cq.from(Centro.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
