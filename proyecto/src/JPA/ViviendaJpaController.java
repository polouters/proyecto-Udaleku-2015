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
public class ViviendaJpaController implements Serializable {

    public ViviendaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Vivienda vivienda) throws PreexistingEntityException, Exception {
        if (vivienda.getDireccionCollection() == null) {
            vivienda.setDireccionCollection(new ArrayList<Direccion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Direccion> attachedDireccionCollection = new ArrayList<Direccion>();
            for (Direccion direccionCollectionDireccionToAttach : vivienda.getDireccionCollection()) {
                direccionCollectionDireccionToAttach = em.getReference(direccionCollectionDireccionToAttach.getClass(), direccionCollectionDireccionToAttach.getDireccionPK());
                attachedDireccionCollection.add(direccionCollectionDireccionToAttach);
            }
            vivienda.setDireccionCollection(attachedDireccionCollection);
            em.persist(vivienda);
            for (Direccion direccionCollectionDireccion : vivienda.getDireccionCollection()) {
                Vivienda oldViviendaOfDireccionCollectionDireccion = direccionCollectionDireccion.getVivienda();
                direccionCollectionDireccion.setVivienda(vivienda);
                direccionCollectionDireccion = em.merge(direccionCollectionDireccion);
                if (oldViviendaOfDireccionCollectionDireccion != null) {
                    oldViviendaOfDireccionCollectionDireccion.getDireccionCollection().remove(direccionCollectionDireccion);
                    oldViviendaOfDireccionCollectionDireccion = em.merge(oldViviendaOfDireccionCollectionDireccion);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findVivienda(vivienda.getIdvivienda()) != null) {
                throw new PreexistingEntityException("Vivienda " + vivienda + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Vivienda vivienda) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Vivienda persistentVivienda = em.find(Vivienda.class, vivienda.getIdvivienda());
            Collection<Direccion> direccionCollectionOld = persistentVivienda.getDireccionCollection();
            Collection<Direccion> direccionCollectionNew = vivienda.getDireccionCollection();
            List<String> illegalOrphanMessages = null;
            for (Direccion direccionCollectionOldDireccion : direccionCollectionOld) {
                if (!direccionCollectionNew.contains(direccionCollectionOldDireccion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Direccion " + direccionCollectionOldDireccion + " since its vivienda field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Direccion> attachedDireccionCollectionNew = new ArrayList<Direccion>();
            for (Direccion direccionCollectionNewDireccionToAttach : direccionCollectionNew) {
                direccionCollectionNewDireccionToAttach = em.getReference(direccionCollectionNewDireccionToAttach.getClass(), direccionCollectionNewDireccionToAttach.getDireccionPK());
                attachedDireccionCollectionNew.add(direccionCollectionNewDireccionToAttach);
            }
            direccionCollectionNew = attachedDireccionCollectionNew;
            vivienda.setDireccionCollection(direccionCollectionNew);
            vivienda = em.merge(vivienda);
            for (Direccion direccionCollectionNewDireccion : direccionCollectionNew) {
                if (!direccionCollectionOld.contains(direccionCollectionNewDireccion)) {
                    Vivienda oldViviendaOfDireccionCollectionNewDireccion = direccionCollectionNewDireccion.getVivienda();
                    direccionCollectionNewDireccion.setVivienda(vivienda);
                    direccionCollectionNewDireccion = em.merge(direccionCollectionNewDireccion);
                    if (oldViviendaOfDireccionCollectionNewDireccion != null && !oldViviendaOfDireccionCollectionNewDireccion.equals(vivienda)) {
                        oldViviendaOfDireccionCollectionNewDireccion.getDireccionCollection().remove(direccionCollectionNewDireccion);
                        oldViviendaOfDireccionCollectionNewDireccion = em.merge(oldViviendaOfDireccionCollectionNewDireccion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Short id = vivienda.getIdvivienda();
                if (findVivienda(id) == null) {
                    throw new NonexistentEntityException("The vivienda with id " + id + " no longer exists.");
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
            Vivienda vivienda;
            try {
                vivienda = em.getReference(Vivienda.class, id);
                vivienda.getIdvivienda();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The vivienda with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Direccion> direccionCollectionOrphanCheck = vivienda.getDireccionCollection();
            for (Direccion direccionCollectionOrphanCheckDireccion : direccionCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Vivienda (" + vivienda + ") cannot be destroyed since the Direccion " + direccionCollectionOrphanCheckDireccion + " in its direccionCollection field has a non-nullable vivienda field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(vivienda);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Vivienda> findViviendaEntities() {
        return findViviendaEntities(true, -1, -1);
    }

    public List<Vivienda> findViviendaEntities(int maxResults, int firstResult) {
        return findViviendaEntities(false, maxResults, firstResult);
    }

    private List<Vivienda> findViviendaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Vivienda.class));
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

    public Vivienda findVivienda(Short id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Vivienda.class, id);
        } finally {
            em.close();
        }
    }

    public int getViviendaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Vivienda> rt = cq.from(Vivienda.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
