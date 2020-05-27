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
import DTO.Estudiante;
import DTO.Estudianteproyecto;
import DTO.EstudianteproyectoPK;
import DTO.Proyecto;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author stive
 */
public class EstudianteproyectoJpaController implements Serializable {

    public EstudianteproyectoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Estudianteproyecto estudianteproyecto) throws PreexistingEntityException, Exception {
        if (estudianteproyecto.getEstudianteproyectoPK() == null) {
            estudianteproyecto.setEstudianteproyectoPK(new EstudianteproyectoPK());
        }
        estudianteproyecto.getEstudianteproyectoPK().setEstudiante(estudianteproyecto.getEstudiante1().getCodigo());
        estudianteproyecto.getEstudianteproyectoPK().setProyecto(estudianteproyecto.getProyecto1().getId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estudiante estudiante1 = estudianteproyecto.getEstudiante1();
            if (estudiante1 != null) {
                estudiante1 = em.getReference(estudiante1.getClass(), estudiante1.getCodigo());
                estudianteproyecto.setEstudiante1(estudiante1);
            }
            Proyecto proyecto1 = estudianteproyecto.getProyecto1();
            if (proyecto1 != null) {
                proyecto1 = em.getReference(proyecto1.getClass(), proyecto1.getId());
                estudianteproyecto.setProyecto1(proyecto1);
            }
            em.persist(estudianteproyecto);
            if (estudiante1 != null) {
                estudiante1.getEstudianteproyectoList().add(estudianteproyecto);
                estudiante1 = em.merge(estudiante1);
            }
            if (proyecto1 != null) {
                proyecto1.getEstudianteproyectoList().add(estudianteproyecto);
                proyecto1 = em.merge(proyecto1);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEstudianteproyecto(estudianteproyecto.getEstudianteproyectoPK()) != null) {
                throw new PreexistingEntityException("Estudianteproyecto " + estudianteproyecto + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Estudianteproyecto estudianteproyecto) throws NonexistentEntityException, Exception {
        estudianteproyecto.getEstudianteproyectoPK().setEstudiante(estudianteproyecto.getEstudiante1().getCodigo());
        estudianteproyecto.getEstudianteproyectoPK().setProyecto(estudianteproyecto.getProyecto1().getId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estudianteproyecto persistentEstudianteproyecto = em.find(Estudianteproyecto.class, estudianteproyecto.getEstudianteproyectoPK());
            Estudiante estudiante1Old = persistentEstudianteproyecto.getEstudiante1();
            Estudiante estudiante1New = estudianteproyecto.getEstudiante1();
            Proyecto proyecto1Old = persistentEstudianteproyecto.getProyecto1();
            Proyecto proyecto1New = estudianteproyecto.getProyecto1();
            if (estudiante1New != null) {
                estudiante1New = em.getReference(estudiante1New.getClass(), estudiante1New.getCodigo());
                estudianteproyecto.setEstudiante1(estudiante1New);
            }
            if (proyecto1New != null) {
                proyecto1New = em.getReference(proyecto1New.getClass(), proyecto1New.getId());
                estudianteproyecto.setProyecto1(proyecto1New);
            }
            estudianteproyecto = em.merge(estudianteproyecto);
            if (estudiante1Old != null && !estudiante1Old.equals(estudiante1New)) {
                estudiante1Old.getEstudianteproyectoList().remove(estudianteproyecto);
                estudiante1Old = em.merge(estudiante1Old);
            }
            if (estudiante1New != null && !estudiante1New.equals(estudiante1Old)) {
                estudiante1New.getEstudianteproyectoList().add(estudianteproyecto);
                estudiante1New = em.merge(estudiante1New);
            }
            if (proyecto1Old != null && !proyecto1Old.equals(proyecto1New)) {
                proyecto1Old.getEstudianteproyectoList().remove(estudianteproyecto);
                proyecto1Old = em.merge(proyecto1Old);
            }
            if (proyecto1New != null && !proyecto1New.equals(proyecto1Old)) {
                proyecto1New.getEstudianteproyectoList().add(estudianteproyecto);
                proyecto1New = em.merge(proyecto1New);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                EstudianteproyectoPK id = estudianteproyecto.getEstudianteproyectoPK();
                if (findEstudianteproyecto(id) == null) {
                    throw new NonexistentEntityException("The estudianteproyecto with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(EstudianteproyectoPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estudianteproyecto estudianteproyecto;
            try {
                estudianteproyecto = em.getReference(Estudianteproyecto.class, id);
                estudianteproyecto.getEstudianteproyectoPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The estudianteproyecto with id " + id + " no longer exists.", enfe);
            }
            Estudiante estudiante1 = estudianteproyecto.getEstudiante1();
            if (estudiante1 != null) {
                estudiante1.getEstudianteproyectoList().remove(estudianteproyecto);
                estudiante1 = em.merge(estudiante1);
            }
            Proyecto proyecto1 = estudianteproyecto.getProyecto1();
            if (proyecto1 != null) {
                proyecto1.getEstudianteproyectoList().remove(estudianteproyecto);
                proyecto1 = em.merge(proyecto1);
            }
            em.remove(estudianteproyecto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Estudianteproyecto> findEstudianteproyectoEntities() {
        return findEstudianteproyectoEntities(true, -1, -1);
    }

    public List<Estudianteproyecto> findEstudianteproyectoEntities(int maxResults, int firstResult) {
        return findEstudianteproyectoEntities(false, maxResults, firstResult);
    }

    private List<Estudianteproyecto> findEstudianteproyectoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Estudianteproyecto.class));
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

    public Estudianteproyecto findEstudianteproyecto(EstudianteproyectoPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Estudianteproyecto.class, id);
        } finally {
            em.close();
        }
    }

    public int getEstudianteproyectoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Estudianteproyecto> rt = cq.from(Estudianteproyecto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
