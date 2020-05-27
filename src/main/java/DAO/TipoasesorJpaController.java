/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.exceptions.IllegalOrphanException;
import DAO.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import DTO.Asesor;
import DTO.Tipoasesor;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author stive
 */
public class TipoasesorJpaController implements Serializable {

    public TipoasesorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tipoasesor tipoasesor) {
        if (tipoasesor.getAsesorList() == null) {
            tipoasesor.setAsesorList(new ArrayList<Asesor>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Asesor> attachedAsesorList = new ArrayList<Asesor>();
            for (Asesor asesorListAsesorToAttach : tipoasesor.getAsesorList()) {
                asesorListAsesorToAttach = em.getReference(asesorListAsesorToAttach.getClass(), asesorListAsesorToAttach.getAsesorPK());
                attachedAsesorList.add(asesorListAsesorToAttach);
            }
            tipoasesor.setAsesorList(attachedAsesorList);
            em.persist(tipoasesor);
            for (Asesor asesorListAsesor : tipoasesor.getAsesorList()) {
                Tipoasesor oldTipoOfAsesorListAsesor = asesorListAsesor.getTipo();
                asesorListAsesor.setTipo(tipoasesor);
                asesorListAsesor = em.merge(asesorListAsesor);
                if (oldTipoOfAsesorListAsesor != null) {
                    oldTipoOfAsesorListAsesor.getAsesorList().remove(asesorListAsesor);
                    oldTipoOfAsesorListAsesor = em.merge(oldTipoOfAsesorListAsesor);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tipoasesor tipoasesor) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tipoasesor persistentTipoasesor = em.find(Tipoasesor.class, tipoasesor.getId());
            List<Asesor> asesorListOld = persistentTipoasesor.getAsesorList();
            List<Asesor> asesorListNew = tipoasesor.getAsesorList();
            List<String> illegalOrphanMessages = null;
            for (Asesor asesorListOldAsesor : asesorListOld) {
                if (!asesorListNew.contains(asesorListOldAsesor)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Asesor " + asesorListOldAsesor + " since its tipo field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Asesor> attachedAsesorListNew = new ArrayList<Asesor>();
            for (Asesor asesorListNewAsesorToAttach : asesorListNew) {
                asesorListNewAsesorToAttach = em.getReference(asesorListNewAsesorToAttach.getClass(), asesorListNewAsesorToAttach.getAsesorPK());
                attachedAsesorListNew.add(asesorListNewAsesorToAttach);
            }
            asesorListNew = attachedAsesorListNew;
            tipoasesor.setAsesorList(asesorListNew);
            tipoasesor = em.merge(tipoasesor);
            for (Asesor asesorListNewAsesor : asesorListNew) {
                if (!asesorListOld.contains(asesorListNewAsesor)) {
                    Tipoasesor oldTipoOfAsesorListNewAsesor = asesorListNewAsesor.getTipo();
                    asesorListNewAsesor.setTipo(tipoasesor);
                    asesorListNewAsesor = em.merge(asesorListNewAsesor);
                    if (oldTipoOfAsesorListNewAsesor != null && !oldTipoOfAsesorListNewAsesor.equals(tipoasesor)) {
                        oldTipoOfAsesorListNewAsesor.getAsesorList().remove(asesorListNewAsesor);
                        oldTipoOfAsesorListNewAsesor = em.merge(oldTipoOfAsesorListNewAsesor);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tipoasesor.getId();
                if (findTipoasesor(id) == null) {
                    throw new NonexistentEntityException("The tipoasesor with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tipoasesor tipoasesor;
            try {
                tipoasesor = em.getReference(Tipoasesor.class, id);
                tipoasesor.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoasesor with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Asesor> asesorListOrphanCheck = tipoasesor.getAsesorList();
            for (Asesor asesorListOrphanCheckAsesor : asesorListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tipoasesor (" + tipoasesor + ") cannot be destroyed since the Asesor " + asesorListOrphanCheckAsesor + " in its asesorList field has a non-nullable tipo field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(tipoasesor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tipoasesor> findTipoasesorEntities() {
        return findTipoasesorEntities(true, -1, -1);
    }

    public List<Tipoasesor> findTipoasesorEntities(int maxResults, int firstResult) {
        return findTipoasesorEntities(false, maxResults, firstResult);
    }

    private List<Tipoasesor> findTipoasesorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tipoasesor.class));
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

    public Tipoasesor findTipoasesor(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tipoasesor.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoasesorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tipoasesor> rt = cq.from(Tipoasesor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
