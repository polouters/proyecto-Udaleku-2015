/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd;

import bd.exceptions.IllegalOrphanException;
import bd.exceptions.NonexistentEntityException;
import bd.exceptions.PreexistingEntityException;
import java.io.Serializable;
import java.math.BigDecimal;
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
 * @author jon
 */
public class CalleJpaController implements Serializable {

    public CalleJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Calle calle) throws PreexistingEntityException, Exception {
        if (calle.getDireccionCollection() == null) {
            calle.setDireccionCollection(new ArrayList<Direccion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Municipio idmunicipio = calle.getIdmunicipio();
            if (idmunicipio != null) {
                idmunicipio = em.getReference(idmunicipio.getClass(), idmunicipio.getIdmunicipio());
                calle.setIdmunicipio(idmunicipio);
            }
            Collection<Direccion> attachedDireccionCollection = new ArrayList<Direccion>();
            for (Direccion direccionCollectionDireccionToAttach : calle.getDireccionCollection()) {
                direccionCollectionDireccionToAttach = em.getReference(direccionCollectionDireccionToAttach.getClass(), direccionCollectionDireccionToAttach.getDireccionPK());
                attachedDireccionCollection.add(direccionCollectionDireccionToAttach);
            }
            calle.setDireccionCollection(attachedDireccionCollection);
            em.persist(calle);
            if (idmunicipio != null) {
                idmunicipio.getCalleCollection().add(calle);
                idmunicipio = em.merge(idmunicipio);
            }
            for (Direccion direccionCollectionDireccion : calle.getDireccionCollection()) {
                Calle oldCalleOfDireccionCollectionDireccion = direccionCollectionDireccion.getCalle();
                direccionCollectionDireccion.setCalle(calle);
                direccionCollectionDireccion = em.merge(direccionCollectionDireccion);
                if (oldCalleOfDireccionCollectionDireccion != null) {
                    oldCalleOfDireccionCollectionDireccion.getDireccionCollection().remove(direccionCollectionDireccion);
                    oldCalleOfDireccionCollectionDireccion = em.merge(oldCalleOfDireccionCollectionDireccion);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCalle(calle.getIdcalle()) != null) {
                throw new PreexistingEntityException("Calle " + calle + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Calle calle) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Calle persistentCalle = em.find(Calle.class, calle.getIdcalle());
            Municipio idmunicipioOld = persistentCalle.getIdmunicipio();
            Municipio idmunicipioNew = calle.getIdmunicipio();
            Collection<Direccion> direccionCollectionOld = persistentCalle.getDireccionCollection();
            Collection<Direccion> direccionCollectionNew = calle.getDireccionCollection();
            List<String> illegalOrphanMessages = null;
            for (Direccion direccionCollectionOldDireccion : direccionCollectionOld) {
                if (!direccionCollectionNew.contains(direccionCollectionOldDireccion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Direccion " + direccionCollectionOldDireccion + " since its calle field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idmunicipioNew != null) {
                idmunicipioNew = em.getReference(idmunicipioNew.getClass(), idmunicipioNew.getIdmunicipio());
                calle.setIdmunicipio(idmunicipioNew);
            }
            Collection<Direccion> attachedDireccionCollectionNew = new ArrayList<Direccion>();
            for (Direccion direccionCollectionNewDireccionToAttach : direccionCollectionNew) {
                direccionCollectionNewDireccionToAttach = em.getReference(direccionCollectionNewDireccionToAttach.getClass(), direccionCollectionNewDireccionToAttach.getDireccionPK());
                attachedDireccionCollectionNew.add(direccionCollectionNewDireccionToAttach);
            }
            direccionCollectionNew = attachedDireccionCollectionNew;
            calle.setDireccionCollection(direccionCollectionNew);
            calle = em.merge(calle);
            if (idmunicipioOld != null && !idmunicipioOld.equals(idmunicipioNew)) {
                idmunicipioOld.getCalleCollection().remove(calle);
                idmunicipioOld = em.merge(idmunicipioOld);
            }
            if (idmunicipioNew != null && !idmunicipioNew.equals(idmunicipioOld)) {
                idmunicipioNew.getCalleCollection().add(calle);
                idmunicipioNew = em.merge(idmunicipioNew);
            }
            for (Direccion direccionCollectionNewDireccion : direccionCollectionNew) {
                if (!direccionCollectionOld.contains(direccionCollectionNewDireccion)) {
                    Calle oldCalleOfDireccionCollectionNewDireccion = direccionCollectionNewDireccion.getCalle();
                    direccionCollectionNewDireccion.setCalle(calle);
                    direccionCollectionNewDireccion = em.merge(direccionCollectionNewDireccion);
                    if (oldCalleOfDireccionCollectionNewDireccion != null && !oldCalleOfDireccionCollectionNewDireccion.equals(calle)) {
                        oldCalleOfDireccionCollectionNewDireccion.getDireccionCollection().remove(direccionCollectionNewDireccion);
                        oldCalleOfDireccionCollectionNewDireccion = em.merge(oldCalleOfDireccionCollectionNewDireccion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = calle.getIdcalle();
                if (findCalle(id) == null) {
                    throw new NonexistentEntityException("The calle with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(BigDecimal id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Calle calle;
            try {
                calle = em.getReference(Calle.class, id);
                calle.getIdcalle();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The calle with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Direccion> direccionCollectionOrphanCheck = calle.getDireccionCollection();
            for (Direccion direccionCollectionOrphanCheckDireccion : direccionCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Calle (" + calle + ") cannot be destroyed since the Direccion " + direccionCollectionOrphanCheckDireccion + " in its direccionCollection field has a non-nullable calle field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Municipio idmunicipio = calle.getIdmunicipio();
            if (idmunicipio != null) {
                idmunicipio.getCalleCollection().remove(calle);
                idmunicipio = em.merge(idmunicipio);
            }
            em.remove(calle);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Calle> findCalleEntities() {
        return findCalleEntities(true, -1, -1);
    }

    public List<Calle> findCalleEntities(int maxResults, int firstResult) {
        return findCalleEntities(false, maxResults, firstResult);
    }

    private List<Calle> findCalleEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Calle.class));
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

    public Calle findCalle(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Calle.class, id);
        } finally {
            em.close();
        }
    }

    public int getCalleCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Calle> rt = cq.from(Calle.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
