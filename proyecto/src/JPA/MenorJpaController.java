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
public class MenorJpaController implements Serializable {

    public MenorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Menor menor) throws PreexistingEntityException, Exception {
        if (menor.getInscripcionCollection() == null) {
            menor.setInscripcionCollection(new ArrayList<Inscripcion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Centro idcentro = menor.getIdcentro();
            if (idcentro != null) {
                idcentro = em.getReference(idcentro.getClass(), idcentro.getIdcentro());
                menor.setIdcentro(idcentro);
            }
            Direccion direccion = menor.getDireccion();
            if (direccion != null) {
                direccion = em.getReference(direccion.getClass(), direccion.getDireccionPK());
                menor.setDireccion(direccion);
            }
            Collection<Inscripcion> attachedInscripcionCollection = new ArrayList<Inscripcion>();
            for (Inscripcion inscripcionCollectionInscripcionToAttach : menor.getInscripcionCollection()) {
                inscripcionCollectionInscripcionToAttach = em.getReference(inscripcionCollectionInscripcionToAttach.getClass(), inscripcionCollectionInscripcionToAttach.getIdins());
                attachedInscripcionCollection.add(inscripcionCollectionInscripcionToAttach);
            }
            menor.setInscripcionCollection(attachedInscripcionCollection);
            em.persist(menor);
            if (idcentro != null) {
                idcentro.getMenorCollection().add(menor);
                idcentro = em.merge(idcentro);
            }
            if (direccion != null) {
                direccion.getMenorCollection().add(menor);
                direccion = em.merge(direccion);
            }
            for (Inscripcion inscripcionCollectionInscripcion : menor.getInscripcionCollection()) {
                Menor oldCodmenorOfInscripcionCollectionInscripcion = inscripcionCollectionInscripcion.getCodmenor();
                inscripcionCollectionInscripcion.setCodmenor(menor);
                inscripcionCollectionInscripcion = em.merge(inscripcionCollectionInscripcion);
                if (oldCodmenorOfInscripcionCollectionInscripcion != null) {
                    oldCodmenorOfInscripcionCollectionInscripcion.getInscripcionCollection().remove(inscripcionCollectionInscripcion);
                    oldCodmenorOfInscripcionCollectionInscripcion = em.merge(oldCodmenorOfInscripcionCollectionInscripcion);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findMenor(menor.getCodmenor()) != null) {
                throw new PreexistingEntityException("Menor " + menor + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Menor menor) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Menor persistentMenor = em.find(Menor.class, menor.getCodmenor());
            Centro idcentroOld = persistentMenor.getIdcentro();
            Centro idcentroNew = menor.getIdcentro();
            Direccion direccionOld = persistentMenor.getDireccion();
            Direccion direccionNew = menor.getDireccion();
            Collection<Inscripcion> inscripcionCollectionOld = persistentMenor.getInscripcionCollection();
            Collection<Inscripcion> inscripcionCollectionNew = menor.getInscripcionCollection();
            List<String> illegalOrphanMessages = null;
            for (Inscripcion inscripcionCollectionOldInscripcion : inscripcionCollectionOld) {
                if (!inscripcionCollectionNew.contains(inscripcionCollectionOldInscripcion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Inscripcion " + inscripcionCollectionOldInscripcion + " since its codmenor field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idcentroNew != null) {
                idcentroNew = em.getReference(idcentroNew.getClass(), idcentroNew.getIdcentro());
                menor.setIdcentro(idcentroNew);
            }
            if (direccionNew != null) {
                direccionNew = em.getReference(direccionNew.getClass(), direccionNew.getDireccionPK());
                menor.setDireccion(direccionNew);
            }
            Collection<Inscripcion> attachedInscripcionCollectionNew = new ArrayList<Inscripcion>();
            for (Inscripcion inscripcionCollectionNewInscripcionToAttach : inscripcionCollectionNew) {
                inscripcionCollectionNewInscripcionToAttach = em.getReference(inscripcionCollectionNewInscripcionToAttach.getClass(), inscripcionCollectionNewInscripcionToAttach.getIdins());
                attachedInscripcionCollectionNew.add(inscripcionCollectionNewInscripcionToAttach);
            }
            inscripcionCollectionNew = attachedInscripcionCollectionNew;
            menor.setInscripcionCollection(inscripcionCollectionNew);
            menor = em.merge(menor);
            if (idcentroOld != null && !idcentroOld.equals(idcentroNew)) {
                idcentroOld.getMenorCollection().remove(menor);
                idcentroOld = em.merge(idcentroOld);
            }
            if (idcentroNew != null && !idcentroNew.equals(idcentroOld)) {
                idcentroNew.getMenorCollection().add(menor);
                idcentroNew = em.merge(idcentroNew);
            }
            if (direccionOld != null && !direccionOld.equals(direccionNew)) {
                direccionOld.getMenorCollection().remove(menor);
                direccionOld = em.merge(direccionOld);
            }
            if (direccionNew != null && !direccionNew.equals(direccionOld)) {
                direccionNew.getMenorCollection().add(menor);
                direccionNew = em.merge(direccionNew);
            }
            for (Inscripcion inscripcionCollectionNewInscripcion : inscripcionCollectionNew) {
                if (!inscripcionCollectionOld.contains(inscripcionCollectionNewInscripcion)) {
                    Menor oldCodmenorOfInscripcionCollectionNewInscripcion = inscripcionCollectionNewInscripcion.getCodmenor();
                    inscripcionCollectionNewInscripcion.setCodmenor(menor);
                    inscripcionCollectionNewInscripcion = em.merge(inscripcionCollectionNewInscripcion);
                    if (oldCodmenorOfInscripcionCollectionNewInscripcion != null && !oldCodmenorOfInscripcionCollectionNewInscripcion.equals(menor)) {
                        oldCodmenorOfInscripcionCollectionNewInscripcion.getInscripcionCollection().remove(inscripcionCollectionNewInscripcion);
                        oldCodmenorOfInscripcionCollectionNewInscripcion = em.merge(oldCodmenorOfInscripcionCollectionNewInscripcion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Short id = menor.getCodmenor();
                if (findMenor(id) == null) {
                    throw new NonexistentEntityException("The menor with id " + id + " no longer exists.");
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
            Menor menor;
            try {
                menor = em.getReference(Menor.class, id);
                menor.getCodmenor();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The menor with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Inscripcion> inscripcionCollectionOrphanCheck = menor.getInscripcionCollection();
            for (Inscripcion inscripcionCollectionOrphanCheckInscripcion : inscripcionCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Menor (" + menor + ") cannot be destroyed since the Inscripcion " + inscripcionCollectionOrphanCheckInscripcion + " in its inscripcionCollection field has a non-nullable codmenor field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Centro idcentro = menor.getIdcentro();
            if (idcentro != null) {
                idcentro.getMenorCollection().remove(menor);
                idcentro = em.merge(idcentro);
            }
            Direccion direccion = menor.getDireccion();
            if (direccion != null) {
                direccion.getMenorCollection().remove(menor);
                direccion = em.merge(direccion);
            }
            em.remove(menor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Menor> findMenorEntities() {
        return findMenorEntities(true, -1, -1);
    }

    public List<Menor> findMenorEntities(int maxResults, int firstResult) {
        return findMenorEntities(false, maxResults, firstResult);
    }

    private List<Menor> findMenorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Menor.class));
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

    public Menor findMenor(Short id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Menor.class, id);
        } finally {
            em.close();
        }
    }

    public int getMenorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Menor> rt = cq.from(Menor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
