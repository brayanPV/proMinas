/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.exceptions.NonexistentEntityException;
import DAO.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import DTO.Docente;
import DTO.Juradoproyecto;
import DTO.JuradoproyectoPK;
import DTO.Proyecto;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author stive
 */
public class JuradoproyectoJpaController implements Serializable {

    public JuradoproyectoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Juradoproyecto juradoproyecto) throws PreexistingEntityException, Exception {
        if (juradoproyecto.getJuradoproyectoPK() == null) {
            juradoproyecto.setJuradoproyectoPK(new JuradoproyectoPK());
        }
        juradoproyecto.getJuradoproyectoPK().setProyecto(juradoproyecto.getProyecto1().getId());
        juradoproyecto.getJuradoproyectoPK().setDocente(juradoproyecto.getDocente1().getCodigo());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Docente docente1 = juradoproyecto.getDocente1();
            if (docente1 != null) {
                docente1 = em.getReference(docente1.getClass(), docente1.getCodigo());
                juradoproyecto.setDocente1(docente1);
            }
            Proyecto proyecto1 = juradoproyecto.getProyecto1();
            if (proyecto1 != null) {
                proyecto1 = em.getReference(proyecto1.getClass(), proyecto1.getId());
                juradoproyecto.setProyecto1(proyecto1);
            }
            em.persist(juradoproyecto);
            if (docente1 != null) {
                docente1.getJuradoproyectoList().add(juradoproyecto);
                docente1 = em.merge(docente1);
            }
            if (proyecto1 != null) {
                proyecto1.getJuradoproyectoList().add(juradoproyecto);
                proyecto1 = em.merge(proyecto1);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findJuradoproyecto(juradoproyecto.getJuradoproyectoPK()) != null) {
                throw new PreexistingEntityException("Juradoproyecto " + juradoproyecto + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Juradoproyecto juradoproyecto) throws NonexistentEntityException, Exception {
        juradoproyecto.getJuradoproyectoPK().setProyecto(juradoproyecto.getProyecto1().getId());
        juradoproyecto.getJuradoproyectoPK().setDocente(juradoproyecto.getDocente1().getCodigo());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Juradoproyecto persistentJuradoproyecto = em.find(Juradoproyecto.class, juradoproyecto.getJuradoproyectoPK());
            Docente docente1Old = persistentJuradoproyecto.getDocente1();
            Docente docente1New = juradoproyecto.getDocente1();
            Proyecto proyecto1Old = persistentJuradoproyecto.getProyecto1();
            Proyecto proyecto1New = juradoproyecto.getProyecto1();
            if (docente1New != null) {
                docente1New = em.getReference(docente1New.getClass(), docente1New.getCodigo());
                juradoproyecto.setDocente1(docente1New);
            }
            if (proyecto1New != null) {
                proyecto1New = em.getReference(proyecto1New.getClass(), proyecto1New.getId());
                juradoproyecto.setProyecto1(proyecto1New);
            }
            juradoproyecto = em.merge(juradoproyecto);
            if (docente1Old != null && !docente1Old.equals(docente1New)) {
                docente1Old.getJuradoproyectoList().remove(juradoproyecto);
                docente1Old = em.merge(docente1Old);
            }
            if (docente1New != null && !docente1New.equals(docente1Old)) {
                docente1New.getJuradoproyectoList().add(juradoproyecto);
                docente1New = em.merge(docente1New);
            }
            if (proyecto1Old != null && !proyecto1Old.equals(proyecto1New)) {
                proyecto1Old.getJuradoproyectoList().remove(juradoproyecto);
                proyecto1Old = em.merge(proyecto1Old);
            }
            if (proyecto1New != null && !proyecto1New.equals(proyecto1Old)) {
                proyecto1New.getJuradoproyectoList().add(juradoproyecto);
                proyecto1New = em.merge(proyecto1New);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                JuradoproyectoPK id = juradoproyecto.getJuradoproyectoPK();
                if (findJuradoproyecto(id) == null) {
                    throw new NonexistentEntityException("The juradoproyecto with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(JuradoproyectoPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Juradoproyecto juradoproyecto;
            try {
                juradoproyecto = em.getReference(Juradoproyecto.class, id);
                juradoproyecto.getJuradoproyectoPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The juradoproyecto with id " + id + " no longer exists.", enfe);
            }
            Docente docente1 = juradoproyecto.getDocente1();
            if (docente1 != null) {
                docente1.getJuradoproyectoList().remove(juradoproyecto);
                docente1 = em.merge(docente1);
            }
            Proyecto proyecto1 = juradoproyecto.getProyecto1();
            if (proyecto1 != null) {
                proyecto1.getJuradoproyectoList().remove(juradoproyecto);
                proyecto1 = em.merge(proyecto1);
            }
            em.remove(juradoproyecto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Juradoproyecto> findJuradoproyectoEntities() {
        return findJuradoproyectoEntities(true, -1, -1);
    }

    public List<Juradoproyecto> findJuradoproyectoEntities(int maxResults, int firstResult) {
        return findJuradoproyectoEntities(false, maxResults, firstResult);
    }

    private List<Juradoproyecto> findJuradoproyectoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Juradoproyecto.class));
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

    public Juradoproyecto findJuradoproyecto(JuradoproyectoPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Juradoproyecto.class, id);
        } finally {
            em.close();
        }
    }

    public int getJuradoproyectoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Juradoproyecto> rt = cq.from(Juradoproyecto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
