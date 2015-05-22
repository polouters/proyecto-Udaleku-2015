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
public class MunicipioJpaController implements Serializable {

    public MunicipioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Municipio municipio) throws PreexistingEntityException, Exception {
        if (municipio.getCalleCollection() == null) {
            municipio.setCalleCollection(new ArrayList<Calle>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Provincia idprov = municipio.getIdprov();
            if (idprov != null) {
                idprov = em.getReference(idprov.getClass(), idprov.getIdprov());
                municipio.setIdprov(idprov);
            }
            Collection<Calle> attachedCalleCollection = new ArrayList<Calle>();
            for (Calle calleCollectionCalleToAttach : municipio.getCalleCollection()) {
                calleCollectionCalleToAttach = em.getReference(calleCollectionCalleToAttach.getClass(), calleCollectionCalleToAttach.getIdcalle());
                attachedCalleCollection.add(calleCollectionCalleToAttach);
            }
            municipio.setCalleCollection(attachedCalleCollection);
            em.persist(municipio);
            if (idprov != null) {
                idprov.getMunicipioCollection().add(municipio);
                idprov = em.merge(idprov);
            }
            for (Calle calleCollectionCalle : municipio.getCalleCollection()) {
                Municipio oldIdmunicipioOfCalleCollectionCalle = calleCollectionCalle.getIdmunicipio();
                calleCollectionCalle.setIdmunicipio(municipio);
                calleCollectionCalle = em.merge(calleCollectionCalle);
                if (oldIdmunicipioOfCalleCollectionCalle != null) {
                    oldIdmunicipioOfCalleCollectionCalle.getCalleCollection().remove(calleCollectionCalle);
                    oldIdmunicipioOfCalleCollectionCalle = em.merge(oldIdmunicipioOfCalleCollectionCalle);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findMunicipio(municipio.getIdmunicipio()) != null) {
                throw new PreexistingEntityException("Municipio " + municipio + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Municipio municipio) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Municipio persistentMunicipio = em.find(Municipio.class, municipio.getIdmunicipio());
            Provincia idprovOld = persistentMunicipio.getIdprov();
            Provincia idprovNew = municipio.getIdprov();
            Collection<Calle> calleCollectionOld = persistentMunicipio.getCalleCollection();
            Collection<Calle> calleCollectionNew = municipio.getCalleCollection();
            List<String> illegalOrphanMessages = null;
            for (Calle calleCollectionOldCalle : calleCollectionOld) {
                if (!calleCollectionNew.contains(calleCollectionOldCalle)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Calle " + calleCollectionOldCalle + " since its idmunicipio field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idprovNew != null) {
                idprovNew = em.getReference(idprovNew.getClass(), idprovNew.getIdprov());
                municipio.setIdprov(idprovNew);
            }
            Collection<Calle> attachedCalleCollectionNew = new ArrayList<Calle>();
            for (Calle calleCollectionNewCalleToAttach : calleCollectionNew) {
                calleCollectionNewCalleToAttach = em.getReference(calleCollectionNewCalleToAttach.getClass(), calleCollectionNewCalleToAttach.getIdcalle());
                attachedCalleCollectionNew.add(calleCollectionNewCalleToAttach);
            }
            calleCollectionNew = attachedCalleCollectionNew;
            municipio.setCalleCollection(calleCollectionNew);
            municipio = em.merge(municipio);
            if (idprovOld != null && !idprovOld.equals(idprovNew)) {
                idprovOld.getMunicipioCollection().remove(municipio);
                idprovOld = em.merge(idprovOld);
            }
            if (idprovNew != null && !idprovNew.equals(idprovOld)) {
                idprovNew.getMunicipioCollection().add(municipio);
                idprovNew = em.merge(idprovNew);
            }
            for (Calle calleCollectionNewCalle : calleCollectionNew) {
                if (!calleCollectionOld.contains(calleCollectionNewCalle)) {
                    Municipio oldIdmunicipioOfCalleCollectionNewCalle = calleCollectionNewCalle.getIdmunicipio();
                    calleCollectionNewCalle.setIdmunicipio(municipio);
                    calleCollectionNewCalle = em.merge(calleCollectionNewCalle);
                    if (oldIdmunicipioOfCalleCollectionNewCalle != null && !oldIdmunicipioOfCalleCollectionNewCalle.equals(municipio)) {
                        oldIdmunicipioOfCalleCollectionNewCalle.getCalleCollection().remove(calleCollectionNewCalle);
                        oldIdmunicipioOfCalleCollectionNewCalle = em.merge(oldIdmunicipioOfCalleCollectionNewCalle);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Short id = municipio.getIdmunicipio();
                if (findMunicipio(id) == null) {
                    throw new NonexistentEntityException("The municipio with id " + id + " no longer exists.");
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
            Municipio municipio;
            try {
                municipio = em.getReference(Municipio.class, id);
                municipio.getIdmunicipio();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The municipio with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Calle> calleCollectionOrphanCheck = municipio.getCalleCollection();
            for (Calle calleCollectionOrphanCheckCalle : calleCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Municipio (" + municipio + ") cannot be destroyed since the Calle " + calleCollectionOrphanCheckCalle + " in its calleCollection field has a non-nullable idmunicipio field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Provincia idprov = municipio.getIdprov();
            if (idprov != null) {
                idprov.getMunicipioCollection().remove(municipio);
                idprov = em.merge(idprov);
            }
            em.remove(municipio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Municipio> findMunicipioEntities() {
        return findMunicipioEntities(true, -1, -1);
    }

    public List<Municipio> findMunicipioEntities(int maxResults, int firstResult) {
        return findMunicipioEntities(false, maxResults, firstResult);
    }

    private List<Municipio> findMunicipioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Municipio.class));
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

    public Municipio findMunicipio(Short id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Municipio.class, id);
        } finally {
            em.close();
        }
    }

    public int getMunicipioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Municipio> rt = cq.from(Municipio.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
