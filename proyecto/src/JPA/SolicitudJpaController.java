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
public class SolicitudJpaController implements Serializable {

    public SolicitudJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Solicitud solicitud) throws PreexistingEntityException, Exception {
        if (solicitud.getInscripcionCollection() == null) {
            solicitud.setInscripcionCollection(new ArrayList<Inscripcion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Sorteo idsorteo = solicitud.getIdsorteo();
            if (idsorteo != null) {
                idsorteo = em.getReference(idsorteo.getClass(), idsorteo.getIdsorteo());
                solicitud.setIdsorteo(idsorteo);
            }
            Collection<Inscripcion> attachedInscripcionCollection = new ArrayList<Inscripcion>();
            for (Inscripcion inscripcionCollectionInscripcionToAttach : solicitud.getInscripcionCollection()) {
                inscripcionCollectionInscripcionToAttach = em.getReference(inscripcionCollectionInscripcionToAttach.getClass(), inscripcionCollectionInscripcionToAttach.getIdins());
                attachedInscripcionCollection.add(inscripcionCollectionInscripcionToAttach);
            }
            solicitud.setInscripcionCollection(attachedInscripcionCollection);
            em.persist(solicitud);
            if (idsorteo != null) {
                idsorteo.getSolicitudCollection().add(solicitud);
                idsorteo = em.merge(idsorteo);
            }
            for (Inscripcion inscripcionCollectionInscripcion : solicitud.getInscripcionCollection()) {
                Solicitud oldNsolicitudOfInscripcionCollectionInscripcion = inscripcionCollectionInscripcion.getNsolicitud();
                inscripcionCollectionInscripcion.setNsolicitud(solicitud);
                inscripcionCollectionInscripcion = em.merge(inscripcionCollectionInscripcion);
                if (oldNsolicitudOfInscripcionCollectionInscripcion != null) {
                    oldNsolicitudOfInscripcionCollectionInscripcion.getInscripcionCollection().remove(inscripcionCollectionInscripcion);
                    oldNsolicitudOfInscripcionCollectionInscripcion = em.merge(oldNsolicitudOfInscripcionCollectionInscripcion);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSolicitud(solicitud.getNsolicitud()) != null) {
                throw new PreexistingEntityException("Solicitud " + solicitud + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Solicitud solicitud) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Solicitud persistentSolicitud = em.find(Solicitud.class, solicitud.getNsolicitud());
            Sorteo idsorteoOld = persistentSolicitud.getIdsorteo();
            Sorteo idsorteoNew = solicitud.getIdsorteo();
            Collection<Inscripcion> inscripcionCollectionOld = persistentSolicitud.getInscripcionCollection();
            Collection<Inscripcion> inscripcionCollectionNew = solicitud.getInscripcionCollection();
            List<String> illegalOrphanMessages = null;
            for (Inscripcion inscripcionCollectionOldInscripcion : inscripcionCollectionOld) {
                if (!inscripcionCollectionNew.contains(inscripcionCollectionOldInscripcion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Inscripcion " + inscripcionCollectionOldInscripcion + " since its nsolicitud field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idsorteoNew != null) {
                idsorteoNew = em.getReference(idsorteoNew.getClass(), idsorteoNew.getIdsorteo());
                solicitud.setIdsorteo(idsorteoNew);
            }
            Collection<Inscripcion> attachedInscripcionCollectionNew = new ArrayList<Inscripcion>();
            for (Inscripcion inscripcionCollectionNewInscripcionToAttach : inscripcionCollectionNew) {
                inscripcionCollectionNewInscripcionToAttach = em.getReference(inscripcionCollectionNewInscripcionToAttach.getClass(), inscripcionCollectionNewInscripcionToAttach.getIdins());
                attachedInscripcionCollectionNew.add(inscripcionCollectionNewInscripcionToAttach);
            }
            inscripcionCollectionNew = attachedInscripcionCollectionNew;
            solicitud.setInscripcionCollection(inscripcionCollectionNew);
            solicitud = em.merge(solicitud);
            if (idsorteoOld != null && !idsorteoOld.equals(idsorteoNew)) {
                idsorteoOld.getSolicitudCollection().remove(solicitud);
                idsorteoOld = em.merge(idsorteoOld);
            }
            if (idsorteoNew != null && !idsorteoNew.equals(idsorteoOld)) {
                idsorteoNew.getSolicitudCollection().add(solicitud);
                idsorteoNew = em.merge(idsorteoNew);
            }
            for (Inscripcion inscripcionCollectionNewInscripcion : inscripcionCollectionNew) {
                if (!inscripcionCollectionOld.contains(inscripcionCollectionNewInscripcion)) {
                    Solicitud oldNsolicitudOfInscripcionCollectionNewInscripcion = inscripcionCollectionNewInscripcion.getNsolicitud();
                    inscripcionCollectionNewInscripcion.setNsolicitud(solicitud);
                    inscripcionCollectionNewInscripcion = em.merge(inscripcionCollectionNewInscripcion);
                    if (oldNsolicitudOfInscripcionCollectionNewInscripcion != null && !oldNsolicitudOfInscripcionCollectionNewInscripcion.equals(solicitud)) {
                        oldNsolicitudOfInscripcionCollectionNewInscripcion.getInscripcionCollection().remove(inscripcionCollectionNewInscripcion);
                        oldNsolicitudOfInscripcionCollectionNewInscripcion = em.merge(oldNsolicitudOfInscripcionCollectionNewInscripcion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Short id = solicitud.getNsolicitud();
                if (findSolicitud(id) == null) {
                    throw new NonexistentEntityException("The solicitud with id " + id + " no longer exists.");
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
            Solicitud solicitud;
            try {
                solicitud = em.getReference(Solicitud.class, id);
                solicitud.getNsolicitud();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The solicitud with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Inscripcion> inscripcionCollectionOrphanCheck = solicitud.getInscripcionCollection();
            for (Inscripcion inscripcionCollectionOrphanCheckInscripcion : inscripcionCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Solicitud (" + solicitud + ") cannot be destroyed since the Inscripcion " + inscripcionCollectionOrphanCheckInscripcion + " in its inscripcionCollection field has a non-nullable nsolicitud field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Sorteo idsorteo = solicitud.getIdsorteo();
            if (idsorteo != null) {
                idsorteo.getSolicitudCollection().remove(solicitud);
                idsorteo = em.merge(idsorteo);
            }
            em.remove(solicitud);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Solicitud> findSolicitudEntities() {
        return findSolicitudEntities(true, -1, -1);
    }

    public List<Solicitud> findSolicitudEntities(int maxResults, int firstResult) {
        return findSolicitudEntities(false, maxResults, firstResult);
    }

    private List<Solicitud> findSolicitudEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Solicitud.class));
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

    public Solicitud findSolicitud(Short id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Solicitud.class, id);
        } finally {
            em.close();
        }
    }

    public int getSolicitudCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Solicitud> rt = cq.from(Solicitud.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
