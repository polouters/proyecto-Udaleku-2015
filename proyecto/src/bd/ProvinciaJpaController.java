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
public class ProvinciaJpaController implements Serializable {

    public ProvinciaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Provincia provincia) throws PreexistingEntityException, Exception {
        if (provincia.getCentroCollection() == null) {
            provincia.setCentroCollection(new ArrayList<Centro>());
        }
        if (provincia.getMunicipioCollection() == null) {
            provincia.setMunicipioCollection(new ArrayList<Municipio>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Centro> attachedCentroCollection = new ArrayList<Centro>();
            for (Centro centroCollectionCentroToAttach : provincia.getCentroCollection()) {
                centroCollectionCentroToAttach = em.getReference(centroCollectionCentroToAttach.getClass(), centroCollectionCentroToAttach.getIdcentro());
                attachedCentroCollection.add(centroCollectionCentroToAttach);
            }
            provincia.setCentroCollection(attachedCentroCollection);
            Collection<Municipio> attachedMunicipioCollection = new ArrayList<Municipio>();
            for (Municipio municipioCollectionMunicipioToAttach : provincia.getMunicipioCollection()) {
                municipioCollectionMunicipioToAttach = em.getReference(municipioCollectionMunicipioToAttach.getClass(), municipioCollectionMunicipioToAttach.getIdmunicipio());
                attachedMunicipioCollection.add(municipioCollectionMunicipioToAttach);
            }
            provincia.setMunicipioCollection(attachedMunicipioCollection);
            em.persist(provincia);
            for (Centro centroCollectionCentro : provincia.getCentroCollection()) {
                Provincia oldIdprovOfCentroCollectionCentro = centroCollectionCentro.getIdprov();
                centroCollectionCentro.setIdprov(provincia);
                centroCollectionCentro = em.merge(centroCollectionCentro);
                if (oldIdprovOfCentroCollectionCentro != null) {
                    oldIdprovOfCentroCollectionCentro.getCentroCollection().remove(centroCollectionCentro);
                    oldIdprovOfCentroCollectionCentro = em.merge(oldIdprovOfCentroCollectionCentro);
                }
            }
            for (Municipio municipioCollectionMunicipio : provincia.getMunicipioCollection()) {
                Provincia oldIdprovOfMunicipioCollectionMunicipio = municipioCollectionMunicipio.getIdprov();
                municipioCollectionMunicipio.setIdprov(provincia);
                municipioCollectionMunicipio = em.merge(municipioCollectionMunicipio);
                if (oldIdprovOfMunicipioCollectionMunicipio != null) {
                    oldIdprovOfMunicipioCollectionMunicipio.getMunicipioCollection().remove(municipioCollectionMunicipio);
                    oldIdprovOfMunicipioCollectionMunicipio = em.merge(oldIdprovOfMunicipioCollectionMunicipio);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findProvincia(provincia.getIdprov()) != null) {
                throw new PreexistingEntityException("Provincia " + provincia + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Provincia provincia) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Provincia persistentProvincia = em.find(Provincia.class, provincia.getIdprov());
            Collection<Centro> centroCollectionOld = persistentProvincia.getCentroCollection();
            Collection<Centro> centroCollectionNew = provincia.getCentroCollection();
            Collection<Municipio> municipioCollectionOld = persistentProvincia.getMunicipioCollection();
            Collection<Municipio> municipioCollectionNew = provincia.getMunicipioCollection();
            List<String> illegalOrphanMessages = null;
            for (Centro centroCollectionOldCentro : centroCollectionOld) {
                if (!centroCollectionNew.contains(centroCollectionOldCentro)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Centro " + centroCollectionOldCentro + " since its idprov field is not nullable.");
                }
            }
            for (Municipio municipioCollectionOldMunicipio : municipioCollectionOld) {
                if (!municipioCollectionNew.contains(municipioCollectionOldMunicipio)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Municipio " + municipioCollectionOldMunicipio + " since its idprov field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Centro> attachedCentroCollectionNew = new ArrayList<Centro>();
            for (Centro centroCollectionNewCentroToAttach : centroCollectionNew) {
                centroCollectionNewCentroToAttach = em.getReference(centroCollectionNewCentroToAttach.getClass(), centroCollectionNewCentroToAttach.getIdcentro());
                attachedCentroCollectionNew.add(centroCollectionNewCentroToAttach);
            }
            centroCollectionNew = attachedCentroCollectionNew;
            provincia.setCentroCollection(centroCollectionNew);
            Collection<Municipio> attachedMunicipioCollectionNew = new ArrayList<Municipio>();
            for (Municipio municipioCollectionNewMunicipioToAttach : municipioCollectionNew) {
                municipioCollectionNewMunicipioToAttach = em.getReference(municipioCollectionNewMunicipioToAttach.getClass(), municipioCollectionNewMunicipioToAttach.getIdmunicipio());
                attachedMunicipioCollectionNew.add(municipioCollectionNewMunicipioToAttach);
            }
            municipioCollectionNew = attachedMunicipioCollectionNew;
            provincia.setMunicipioCollection(municipioCollectionNew);
            provincia = em.merge(provincia);
            for (Centro centroCollectionNewCentro : centroCollectionNew) {
                if (!centroCollectionOld.contains(centroCollectionNewCentro)) {
                    Provincia oldIdprovOfCentroCollectionNewCentro = centroCollectionNewCentro.getIdprov();
                    centroCollectionNewCentro.setIdprov(provincia);
                    centroCollectionNewCentro = em.merge(centroCollectionNewCentro);
                    if (oldIdprovOfCentroCollectionNewCentro != null && !oldIdprovOfCentroCollectionNewCentro.equals(provincia)) {
                        oldIdprovOfCentroCollectionNewCentro.getCentroCollection().remove(centroCollectionNewCentro);
                        oldIdprovOfCentroCollectionNewCentro = em.merge(oldIdprovOfCentroCollectionNewCentro);
                    }
                }
            }
            for (Municipio municipioCollectionNewMunicipio : municipioCollectionNew) {
                if (!municipioCollectionOld.contains(municipioCollectionNewMunicipio)) {
                    Provincia oldIdprovOfMunicipioCollectionNewMunicipio = municipioCollectionNewMunicipio.getIdprov();
                    municipioCollectionNewMunicipio.setIdprov(provincia);
                    municipioCollectionNewMunicipio = em.merge(municipioCollectionNewMunicipio);
                    if (oldIdprovOfMunicipioCollectionNewMunicipio != null && !oldIdprovOfMunicipioCollectionNewMunicipio.equals(provincia)) {
                        oldIdprovOfMunicipioCollectionNewMunicipio.getMunicipioCollection().remove(municipioCollectionNewMunicipio);
                        oldIdprovOfMunicipioCollectionNewMunicipio = em.merge(oldIdprovOfMunicipioCollectionNewMunicipio);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = provincia.getIdprov();
                if (findProvincia(id) == null) {
                    throw new NonexistentEntityException("The provincia with id " + id + " no longer exists.");
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
            Provincia provincia;
            try {
                provincia = em.getReference(Provincia.class, id);
                provincia.getIdprov();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The provincia with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Centro> centroCollectionOrphanCheck = provincia.getCentroCollection();
            for (Centro centroCollectionOrphanCheckCentro : centroCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Provincia (" + provincia + ") cannot be destroyed since the Centro " + centroCollectionOrphanCheckCentro + " in its centroCollection field has a non-nullable idprov field.");
            }
            Collection<Municipio> municipioCollectionOrphanCheck = provincia.getMunicipioCollection();
            for (Municipio municipioCollectionOrphanCheckMunicipio : municipioCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Provincia (" + provincia + ") cannot be destroyed since the Municipio " + municipioCollectionOrphanCheckMunicipio + " in its municipioCollection field has a non-nullable idprov field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(provincia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Provincia> findProvinciaEntities() {
        return findProvinciaEntities(true, -1, -1);
    }

    public List<Provincia> findProvinciaEntities(int maxResults, int firstResult) {
        return findProvinciaEntities(false, maxResults, firstResult);
    }

    private List<Provincia> findProvinciaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Provincia.class));
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

    public Provincia findProvincia(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Provincia.class, id);
        } finally {
            em.close();
        }
    }

    public int getProvinciaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Provincia> rt = cq.from(Provincia.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
