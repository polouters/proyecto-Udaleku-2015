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
public class SorteoJpaController implements Serializable {

    public SorteoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Sorteo sorteo) throws PreexistingEntityException, Exception {
        if (sorteo.getSolicitudCollection() == null) {
            sorteo.setSolicitudCollection(new ArrayList<Solicitud>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Solicitud> attachedSolicitudCollection = new ArrayList<Solicitud>();
            for (Solicitud solicitudCollectionSolicitudToAttach : sorteo.getSolicitudCollection()) {
                solicitudCollectionSolicitudToAttach = em.getReference(solicitudCollectionSolicitudToAttach.getClass(), solicitudCollectionSolicitudToAttach.getNsolicitud());
                attachedSolicitudCollection.add(solicitudCollectionSolicitudToAttach);
            }
            sorteo.setSolicitudCollection(attachedSolicitudCollection);
            em.persist(sorteo);
            for (Solicitud solicitudCollectionSolicitud : sorteo.getSolicitudCollection()) {
                Sorteo oldIdsorteoOfSolicitudCollectionSolicitud = solicitudCollectionSolicitud.getIdsorteo();
                solicitudCollectionSolicitud.setIdsorteo(sorteo);
                solicitudCollectionSolicitud = em.merge(solicitudCollectionSolicitud);
                if (oldIdsorteoOfSolicitudCollectionSolicitud != null) {
                    oldIdsorteoOfSolicitudCollectionSolicitud.getSolicitudCollection().remove(solicitudCollectionSolicitud);
                    oldIdsorteoOfSolicitudCollectionSolicitud = em.merge(oldIdsorteoOfSolicitudCollectionSolicitud);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSorteo(sorteo.getIdsorteo()) != null) {
                throw new PreexistingEntityException("Sorteo " + sorteo + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Sorteo sorteo) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Sorteo persistentSorteo = em.find(Sorteo.class, sorteo.getIdsorteo());
            Collection<Solicitud> solicitudCollectionOld = persistentSorteo.getSolicitudCollection();
            Collection<Solicitud> solicitudCollectionNew = sorteo.getSolicitudCollection();
            List<String> illegalOrphanMessages = null;
            for (Solicitud solicitudCollectionOldSolicitud : solicitudCollectionOld) {
                if (!solicitudCollectionNew.contains(solicitudCollectionOldSolicitud)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Solicitud " + solicitudCollectionOldSolicitud + " since its idsorteo field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Solicitud> attachedSolicitudCollectionNew = new ArrayList<Solicitud>();
            for (Solicitud solicitudCollectionNewSolicitudToAttach : solicitudCollectionNew) {
                solicitudCollectionNewSolicitudToAttach = em.getReference(solicitudCollectionNewSolicitudToAttach.getClass(), solicitudCollectionNewSolicitudToAttach.getNsolicitud());
                attachedSolicitudCollectionNew.add(solicitudCollectionNewSolicitudToAttach);
            }
            solicitudCollectionNew = attachedSolicitudCollectionNew;
            sorteo.setSolicitudCollection(solicitudCollectionNew);
            sorteo = em.merge(sorteo);
            for (Solicitud solicitudCollectionNewSolicitud : solicitudCollectionNew) {
                if (!solicitudCollectionOld.contains(solicitudCollectionNewSolicitud)) {
                    Sorteo oldIdsorteoOfSolicitudCollectionNewSolicitud = solicitudCollectionNewSolicitud.getIdsorteo();
                    solicitudCollectionNewSolicitud.setIdsorteo(sorteo);
                    solicitudCollectionNewSolicitud = em.merge(solicitudCollectionNewSolicitud);
                    if (oldIdsorteoOfSolicitudCollectionNewSolicitud != null && !oldIdsorteoOfSolicitudCollectionNewSolicitud.equals(sorteo)) {
                        oldIdsorteoOfSolicitudCollectionNewSolicitud.getSolicitudCollection().remove(solicitudCollectionNewSolicitud);
                        oldIdsorteoOfSolicitudCollectionNewSolicitud = em.merge(oldIdsorteoOfSolicitudCollectionNewSolicitud);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Short id = sorteo.getIdsorteo();
                if (findSorteo(id) == null) {
                    throw new NonexistentEntityException("The sorteo with id " + id + " no longer exists.");
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
            Sorteo sorteo;
            try {
                sorteo = em.getReference(Sorteo.class, id);
                sorteo.getIdsorteo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The sorteo with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Solicitud> solicitudCollectionOrphanCheck = sorteo.getSolicitudCollection();
            for (Solicitud solicitudCollectionOrphanCheckSolicitud : solicitudCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Sorteo (" + sorteo + ") cannot be destroyed since the Solicitud " + solicitudCollectionOrphanCheckSolicitud + " in its solicitudCollection field has a non-nullable idsorteo field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(sorteo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Sorteo> findSorteoEntities() {
        return findSorteoEntities(true, -1, -1);
    }

    public List<Sorteo> findSorteoEntities(int maxResults, int firstResult) {
        return findSorteoEntities(false, maxResults, firstResult);
    }

    private List<Sorteo> findSorteoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Sorteo.class));
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

    public Sorteo findSorteo(Short id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Sorteo.class, id);
        } finally {
            em.close();
        }
    }

    public int getSorteoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Sorteo> rt = cq.from(Sorteo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
