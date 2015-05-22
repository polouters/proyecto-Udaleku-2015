/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JPA;

import JPA.exceptions.NonexistentEntityException;
import JPA.exceptions.PreexistingEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Ruben
 */
public class InscripcionJpaController implements Serializable {

    public InscripcionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Inscripcion inscripcion) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Menor codmenor = inscripcion.getCodmenor();
            if (codmenor != null) {
                codmenor = em.getReference(codmenor.getClass(), codmenor.getCodmenor());
                inscripcion.setCodmenor(codmenor);
            }
            Solicitud nsolicitud = inscripcion.getNsolicitud();
            if (nsolicitud != null) {
                nsolicitud = em.getReference(nsolicitud.getClass(), nsolicitud.getNsolicitud());
                inscripcion.setNsolicitud(nsolicitud);
            }
            Tutor dni = inscripcion.getDni();
            if (dni != null) {
                dni = em.getReference(dni.getClass(), dni.getDni());
                inscripcion.setDni(dni);
            }
            em.persist(inscripcion);
            if (codmenor != null) {
                codmenor.getInscripcionCollection().add(inscripcion);
                codmenor = em.merge(codmenor);
            }
            if (nsolicitud != null) {
                nsolicitud.getInscripcionCollection().add(inscripcion);
                nsolicitud = em.merge(nsolicitud);
            }
            if (dni != null) {
                dni.getInscripcionCollection().add(inscripcion);
                dni = em.merge(dni);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findInscripcion(inscripcion.getIdins()) != null) {
                throw new PreexistingEntityException("Inscripcion " + inscripcion + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Inscripcion inscripcion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Inscripcion persistentInscripcion = em.find(Inscripcion.class, inscripcion.getIdins());
            Menor codmenorOld = persistentInscripcion.getCodmenor();
            Menor codmenorNew = inscripcion.getCodmenor();
            Solicitud nsolicitudOld = persistentInscripcion.getNsolicitud();
            Solicitud nsolicitudNew = inscripcion.getNsolicitud();
            Tutor dniOld = persistentInscripcion.getDni();
            Tutor dniNew = inscripcion.getDni();
            if (codmenorNew != null) {
                codmenorNew = em.getReference(codmenorNew.getClass(), codmenorNew.getCodmenor());
                inscripcion.setCodmenor(codmenorNew);
            }
            if (nsolicitudNew != null) {
                nsolicitudNew = em.getReference(nsolicitudNew.getClass(), nsolicitudNew.getNsolicitud());
                inscripcion.setNsolicitud(nsolicitudNew);
            }
            if (dniNew != null) {
                dniNew = em.getReference(dniNew.getClass(), dniNew.getDni());
                inscripcion.setDni(dniNew);
            }
            inscripcion = em.merge(inscripcion);
            if (codmenorOld != null && !codmenorOld.equals(codmenorNew)) {
                codmenorOld.getInscripcionCollection().remove(inscripcion);
                codmenorOld = em.merge(codmenorOld);
            }
            if (codmenorNew != null && !codmenorNew.equals(codmenorOld)) {
                codmenorNew.getInscripcionCollection().add(inscripcion);
                codmenorNew = em.merge(codmenorNew);
            }
            if (nsolicitudOld != null && !nsolicitudOld.equals(nsolicitudNew)) {
                nsolicitudOld.getInscripcionCollection().remove(inscripcion);
                nsolicitudOld = em.merge(nsolicitudOld);
            }
            if (nsolicitudNew != null && !nsolicitudNew.equals(nsolicitudOld)) {
                nsolicitudNew.getInscripcionCollection().add(inscripcion);
                nsolicitudNew = em.merge(nsolicitudNew);
            }
            if (dniOld != null && !dniOld.equals(dniNew)) {
                dniOld.getInscripcionCollection().remove(inscripcion);
                dniOld = em.merge(dniOld);
            }
            if (dniNew != null && !dniNew.equals(dniOld)) {
                dniNew.getInscripcionCollection().add(inscripcion);
                dniNew = em.merge(dniNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Short id = inscripcion.getIdins();
                if (findInscripcion(id) == null) {
                    throw new NonexistentEntityException("The inscripcion with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Short id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Inscripcion inscripcion;
            try {
                inscripcion = em.getReference(Inscripcion.class, id);
                inscripcion.getIdins();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The inscripcion with id " + id + " no longer exists.", enfe);
            }
            Menor codmenor = inscripcion.getCodmenor();
            if (codmenor != null) {
                codmenor.getInscripcionCollection().remove(inscripcion);
                codmenor = em.merge(codmenor);
            }
            Solicitud nsolicitud = inscripcion.getNsolicitud();
            if (nsolicitud != null) {
                nsolicitud.getInscripcionCollection().remove(inscripcion);
                nsolicitud = em.merge(nsolicitud);
            }
            Tutor dni = inscripcion.getDni();
            if (dni != null) {
                dni.getInscripcionCollection().remove(inscripcion);
                dni = em.merge(dni);
            }
            em.remove(inscripcion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Inscripcion> findInscripcionEntities() {
        return findInscripcionEntities(true, -1, -1);
    }

    public List<Inscripcion> findInscripcionEntities(int maxResults, int firstResult) {
        return findInscripcionEntities(false, maxResults, firstResult);
    }

    private List<Inscripcion> findInscripcionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Inscripcion.class));
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

    public Inscripcion findInscripcion(Short id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Inscripcion.class, id);
        } finally {
            em.close();
        }
    }

    public int getInscripcionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Inscripcion> rt = cq.from(Inscripcion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
