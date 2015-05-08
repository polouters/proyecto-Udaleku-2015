/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd;

import bd.exceptions.NonexistentEntityException;
import bd.exceptions.PreexistingEntityException;
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
 * @author jon
 */
public class DireccionJpaController implements Serializable {

    public DireccionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Direccion direccion) throws PreexistingEntityException, Exception {
        if (direccion.getDireccionPK() == null) {
            direccion.setDireccionPK(new DireccionPK());
        }
        direccion.getDireccionPK().setIdcalle(direccion.getCalle().getIdcalle());
        direccion.getDireccionPK().setIdvivienda(direccion.getVivienda().getIdvivienda());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Calle calle = direccion.getCalle();
            if (calle != null) {
                calle = em.getReference(calle.getClass(), calle.getIdcalle());
                direccion.setCalle(calle);
            }
            Vivienda vivienda = direccion.getVivienda();
            if (vivienda != null) {
                vivienda = em.getReference(vivienda.getClass(), vivienda.getIdvivienda());
                direccion.setVivienda(vivienda);
            }
            em.persist(direccion);
            if (calle != null) {
                calle.getDireccionCollection().add(direccion);
                calle = em.merge(calle);
            }
            if (vivienda != null) {
                vivienda.getDireccionCollection().add(direccion);
                vivienda = em.merge(vivienda);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDireccion(direccion.getDireccionPK()) != null) {
                throw new PreexistingEntityException("Direccion " + direccion + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Direccion direccion) throws NonexistentEntityException, Exception {
        direccion.getDireccionPK().setIdcalle(direccion.getCalle().getIdcalle());
        direccion.getDireccionPK().setIdvivienda(direccion.getVivienda().getIdvivienda());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Direccion persistentDireccion = em.find(Direccion.class, direccion.getDireccionPK());
            Calle calleOld = persistentDireccion.getCalle();
            Calle calleNew = direccion.getCalle();
            Vivienda viviendaOld = persistentDireccion.getVivienda();
            Vivienda viviendaNew = direccion.getVivienda();
            if (calleNew != null) {
                calleNew = em.getReference(calleNew.getClass(), calleNew.getIdcalle());
                direccion.setCalle(calleNew);
            }
            if (viviendaNew != null) {
                viviendaNew = em.getReference(viviendaNew.getClass(), viviendaNew.getIdvivienda());
                direccion.setVivienda(viviendaNew);
            }
            direccion = em.merge(direccion);
            if (calleOld != null && !calleOld.equals(calleNew)) {
                calleOld.getDireccionCollection().remove(direccion);
                calleOld = em.merge(calleOld);
            }
            if (calleNew != null && !calleNew.equals(calleOld)) {
                calleNew.getDireccionCollection().add(direccion);
                calleNew = em.merge(calleNew);
            }
            if (viviendaOld != null && !viviendaOld.equals(viviendaNew)) {
                viviendaOld.getDireccionCollection().remove(direccion);
                viviendaOld = em.merge(viviendaOld);
            }
            if (viviendaNew != null && !viviendaNew.equals(viviendaOld)) {
                viviendaNew.getDireccionCollection().add(direccion);
                viviendaNew = em.merge(viviendaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                DireccionPK id = direccion.getDireccionPK();
                if (findDireccion(id) == null) {
                    throw new NonexistentEntityException("The direccion with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(DireccionPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Direccion direccion;
            try {
                direccion = em.getReference(Direccion.class, id);
                direccion.getDireccionPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The direccion with id " + id + " no longer exists.", enfe);
            }
            Calle calle = direccion.getCalle();
            if (calle != null) {
                calle.getDireccionCollection().remove(direccion);
                calle = em.merge(calle);
            }
            Vivienda vivienda = direccion.getVivienda();
            if (vivienda != null) {
                vivienda.getDireccionCollection().remove(direccion);
                vivienda = em.merge(vivienda);
            }
            em.remove(direccion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Direccion> findDireccionEntities() {
        return findDireccionEntities(true, -1, -1);
    }

    public List<Direccion> findDireccionEntities(int maxResults, int firstResult) {
        return findDireccionEntities(false, maxResults, firstResult);
    }

    private List<Direccion> findDireccionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Direccion.class));
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

    public Direccion findDireccion(DireccionPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Direccion.class, id);
        } finally {
            em.close();
        }
    }

    public int getDireccionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Direccion> rt = cq.from(Direccion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
