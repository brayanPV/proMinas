/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.exceptions.NonexistentEntityException;
import DAO.exceptions.PreexistingEntityException;
import DTO.Asesor;
import DTO.AsesorPK;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import DTO.Docente;
import DTO.Proyecto;
import DTO.Tipoasesor;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author stive
 */
public class AsesorJpaController implements Serializable {

    public AsesorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Asesor asesor) throws PreexistingEntityException, Exception {
        if (asesor.getAsesorPK() == null) {
            asesor.setAsesorPK(new AsesorPK());
        }
        asesor.getAsesorPK().setProyecto(asesor.getProyecto1().getId());
        asesor.getAsesorPK().setDocente(asesor.getDocente1().getCodigo());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Docente docente1 = asesor.getDocente1();
            if (docente1 != null) {
                docente1 = em.getReference(docente1.getClass(), docente1.getCodigo());
                asesor.setDocente1(docente1);
            }
            Proyecto proyecto1 = asesor.getProyecto1();
            if (proyecto1 != null) {
                proyecto1 = em.getReference(proyecto1.getClass(), proyecto1.getId());
                asesor.setProyecto1(proyecto1);
            }
            Tipoasesor tipo = asesor.getTipo();
            if (tipo != null) {
                tipo = em.getReference(tipo.getClass(), tipo.getId());
                asesor.setTipo(tipo);
            }
            em.persist(asesor);
            if (docente1 != null) {
                docente1.getAsesorList().add(asesor);
                docente1 = em.merge(docente1);
            }
            if (proyecto1 != null) {
                proyecto1.getAsesorList().add(asesor);
                proyecto1 = em.merge(proyecto1);
            }
            if (tipo != null) {
                tipo.getAsesorList().add(asesor);
                tipo = em.merge(tipo);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAsesor(asesor.getAsesorPK()) != null) {
                throw new PreexistingEntityException("Asesor " + asesor + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Asesor asesor) throws NonexistentEntityException, Exception {
        asesor.getAsesorPK().setProyecto(asesor.getProyecto1().getId());
        asesor.getAsesorPK().setDocente(asesor.getDocente1().getCodigo());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Asesor persistentAsesor = em.find(Asesor.class, asesor.getAsesorPK());
            Docente docente1Old = persistentAsesor.getDocente1();
            Docente docente1New = asesor.getDocente1();
            Proyecto proyecto1Old = persistentAsesor.getProyecto1();
            Proyecto proyecto1New = asesor.getProyecto1();
            Tipoasesor tipoOld = persistentAsesor.getTipo();
            Tipoasesor tipoNew = asesor.getTipo();
            if (docente1New != null) {
                docente1New = em.getReference(docente1New.getClass(), docente1New.getCodigo());
                asesor.setDocente1(docente1New);
            }
            if (proyecto1New != null) {
                proyecto1New = em.getReference(proyecto1New.getClass(), proyecto1New.getId());
                asesor.setProyecto1(proyecto1New);
            }
            if (tipoNew != null) {
                tipoNew = em.getReference(tipoNew.getClass(), tipoNew.getId());
                asesor.setTipo(tipoNew);
            }
            asesor = em.merge(asesor);
            if (docente1Old != null && !docente1Old.equals(docente1New)) {
                docente1Old.getAsesorList().remove(asesor);
                docente1Old = em.merge(docente1Old);
            }
            if (docente1New != null && !docente1New.equals(docente1Old)) {
                docente1New.getAsesorList().add(asesor);
                docente1New = em.merge(docente1New);
            }
            if (proyecto1Old != null && !proyecto1Old.equals(proyecto1New)) {
                proyecto1Old.getAsesorList().remove(asesor);
                proyecto1Old = em.merge(proyecto1Old);
            }
            if (proyecto1New != null && !proyecto1New.equals(proyecto1Old)) {
                proyecto1New.getAsesorList().add(asesor);
                proyecto1New = em.merge(proyecto1New);
            }
            if (tipoOld != null && !tipoOld.equals(tipoNew)) {
                tipoOld.getAsesorList().remove(asesor);
                tipoOld = em.merge(tipoOld);
            }
            if (tipoNew != null && !tipoNew.equals(tipoOld)) {
                tipoNew.getAsesorList().add(asesor);
                tipoNew = em.merge(tipoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                AsesorPK id = asesor.getAsesorPK();
                if (findAsesor(id) == null) {
                    throw new NonexistentEntityException("The asesor with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(AsesorPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Asesor asesor;
            try {
                asesor = em.getReference(Asesor.class, id);
                asesor.getAsesorPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The asesor with id " + id + " no longer exists.", enfe);
            }
            Docente docente1 = asesor.getDocente1();
            if (docente1 != null) {
                docente1.getAsesorList().remove(asesor);
                docente1 = em.merge(docente1);
            }
            Proyecto proyecto1 = asesor.getProyecto1();
            if (proyecto1 != null) {
                proyecto1.getAsesorList().remove(asesor);
                proyecto1 = em.merge(proyecto1);
            }
            Tipoasesor tipo = asesor.getTipo();
            if (tipo != null) {
                tipo.getAsesorList().remove(asesor);
                tipo = em.merge(tipo);
            }
            em.remove(asesor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Asesor> findAsesorEntities() {
        return findAsesorEntities(true, -1, -1);
    }

    public List<Asesor> findAsesorEntities(int maxResults, int firstResult) {
        return findAsesorEntities(false, maxResults, firstResult);
    }

    private List<Asesor> findAsesorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Asesor.class));
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

    public Asesor findAsesor(AsesorPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Asesor.class, id);
        } finally {
            em.close();
        }
    }

    public int getAsesorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Asesor> rt = cq.from(Asesor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
