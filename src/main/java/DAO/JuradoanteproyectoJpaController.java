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
import DTO.Juradoanteproyecto;
import DTO.JuradoanteproyectoPK;
import DTO.Proyecto;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author stive
 */
public class JuradoanteproyectoJpaController implements Serializable {

    public JuradoanteproyectoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Juradoanteproyecto juradoanteproyecto) throws PreexistingEntityException, Exception {
        if (juradoanteproyecto.getJuradoanteproyectoPK() == null) {
            juradoanteproyecto.setJuradoanteproyectoPK(new JuradoanteproyectoPK());
        }
        juradoanteproyecto.getJuradoanteproyectoPK().setProyecto(juradoanteproyecto.getProyecto1().getId());
        juradoanteproyecto.getJuradoanteproyectoPK().setDocente(juradoanteproyecto.getDocente1().getCodigo());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Docente docente1 = juradoanteproyecto.getDocente1();
            if (docente1 != null) {
                docente1 = em.getReference(docente1.getClass(), docente1.getCodigo());
                juradoanteproyecto.setDocente1(docente1);
            }
            Proyecto proyecto1 = juradoanteproyecto.getProyecto1();
            if (proyecto1 != null) {
                proyecto1 = em.getReference(proyecto1.getClass(), proyecto1.getId());
                juradoanteproyecto.setProyecto1(proyecto1);
            }
            em.persist(juradoanteproyecto);
            if (docente1 != null) {
                docente1.getJuradoanteproyectoList().add(juradoanteproyecto);
                docente1 = em.merge(docente1);
            }
            if (proyecto1 != null) {
                proyecto1.getJuradoanteproyectoList().add(juradoanteproyecto);
                proyecto1 = em.merge(proyecto1);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findJuradoanteproyecto(juradoanteproyecto.getJuradoanteproyectoPK()) != null) {
                throw new PreexistingEntityException("Juradoanteproyecto " + juradoanteproyecto + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Juradoanteproyecto juradoanteproyecto) throws NonexistentEntityException, Exception {
        juradoanteproyecto.getJuradoanteproyectoPK().setProyecto(juradoanteproyecto.getProyecto1().getId());
        juradoanteproyecto.getJuradoanteproyectoPK().setDocente(juradoanteproyecto.getDocente1().getCodigo());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Juradoanteproyecto persistentJuradoanteproyecto = em.find(Juradoanteproyecto.class, juradoanteproyecto.getJuradoanteproyectoPK());
            Docente docente1Old = persistentJuradoanteproyecto.getDocente1();
            Docente docente1New = juradoanteproyecto.getDocente1();
            Proyecto proyecto1Old = persistentJuradoanteproyecto.getProyecto1();
            Proyecto proyecto1New = juradoanteproyecto.getProyecto1();
            if (docente1New != null) {
                docente1New = em.getReference(docente1New.getClass(), docente1New.getCodigo());
                juradoanteproyecto.setDocente1(docente1New);
            }
            if (proyecto1New != null) {
                proyecto1New = em.getReference(proyecto1New.getClass(), proyecto1New.getId());
                juradoanteproyecto.setProyecto1(proyecto1New);
            }
            juradoanteproyecto = em.merge(juradoanteproyecto);
            if (docente1Old != null && !docente1Old.equals(docente1New)) {
                docente1Old.getJuradoanteproyectoList().remove(juradoanteproyecto);
                docente1Old = em.merge(docente1Old);
            }
            if (docente1New != null && !docente1New.equals(docente1Old)) {
                docente1New.getJuradoanteproyectoList().add(juradoanteproyecto);
                docente1New = em.merge(docente1New);
            }
            if (proyecto1Old != null && !proyecto1Old.equals(proyecto1New)) {
                proyecto1Old.getJuradoanteproyectoList().remove(juradoanteproyecto);
                proyecto1Old = em.merge(proyecto1Old);
            }
            if (proyecto1New != null && !proyecto1New.equals(proyecto1Old)) {
                proyecto1New.getJuradoanteproyectoList().add(juradoanteproyecto);
                proyecto1New = em.merge(proyecto1New);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                JuradoanteproyectoPK id = juradoanteproyecto.getJuradoanteproyectoPK();
                if (findJuradoanteproyecto(id) == null) {
                    throw new NonexistentEntityException("The juradoanteproyecto with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(JuradoanteproyectoPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Juradoanteproyecto juradoanteproyecto;
            try {
                juradoanteproyecto = em.getReference(Juradoanteproyecto.class, id);
                juradoanteproyecto.getJuradoanteproyectoPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The juradoanteproyecto with id " + id + " no longer exists.", enfe);
            }
            Docente docente1 = juradoanteproyecto.getDocente1();
            if (docente1 != null) {
                docente1.getJuradoanteproyectoList().remove(juradoanteproyecto);
                docente1 = em.merge(docente1);
            }
            Proyecto proyecto1 = juradoanteproyecto.getProyecto1();
            if (proyecto1 != null) {
                proyecto1.getJuradoanteproyectoList().remove(juradoanteproyecto);
                proyecto1 = em.merge(proyecto1);
            }
            em.remove(juradoanteproyecto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Juradoanteproyecto> findJuradoanteproyectoEntities() {
        return findJuradoanteproyectoEntities(true, -1, -1);
    }

    public List<Juradoanteproyecto> findJuradoanteproyectoEntities(int maxResults, int firstResult) {
        return findJuradoanteproyectoEntities(false, maxResults, firstResult);
    }

    private List<Juradoanteproyecto> findJuradoanteproyectoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Juradoanteproyecto.class));
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

    public Juradoanteproyecto findJuradoanteproyecto(JuradoanteproyectoPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Juradoanteproyecto.class, id);
        } finally {
            em.close();
        }
    }

    public int getJuradoanteproyectoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Juradoanteproyecto> rt = cq.from(Juradoanteproyecto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
