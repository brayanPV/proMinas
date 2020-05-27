/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.exceptions.IllegalOrphanException;
import DAO.exceptions.NonexistentEntityException;
import DAO.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import DTO.Carrera;
import DTO.Estudiante;
import DTO.Estudianteproyecto;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author stive
 */
public class EstudianteJpaController implements Serializable {

    public EstudianteJpaController(){
        emf = Conexion.getEm();
    }
    
    public EstudianteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Estudiante estudiante) throws PreexistingEntityException, Exception {
        if (estudiante.getEstudianteproyectoList() == null) {
            estudiante.setEstudianteproyectoList(new ArrayList<Estudianteproyecto>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Carrera carrera = estudiante.getCarrera();
            if (carrera != null) {
                carrera = em.getReference(carrera.getClass(), carrera.getCodigo());
                estudiante.setCarrera(carrera);
            }
            List<Estudianteproyecto> attachedEstudianteproyectoList = new ArrayList<Estudianteproyecto>();
            for (Estudianteproyecto estudianteproyectoListEstudianteproyectoToAttach : estudiante.getEstudianteproyectoList()) {
                estudianteproyectoListEstudianteproyectoToAttach = em.getReference(estudianteproyectoListEstudianteproyectoToAttach.getClass(), estudianteproyectoListEstudianteproyectoToAttach.getEstudianteproyectoPK());
                attachedEstudianteproyectoList.add(estudianteproyectoListEstudianteproyectoToAttach);
            }
            estudiante.setEstudianteproyectoList(attachedEstudianteproyectoList);
            em.persist(estudiante);
            if (carrera != null) {
                carrera.getEstudianteList().add(estudiante);
                carrera = em.merge(carrera);
            }
            for (Estudianteproyecto estudianteproyectoListEstudianteproyecto : estudiante.getEstudianteproyectoList()) {
                Estudiante oldEstudiante1OfEstudianteproyectoListEstudianteproyecto = estudianteproyectoListEstudianteproyecto.getEstudiante1();
                estudianteproyectoListEstudianteproyecto.setEstudiante1(estudiante);
                estudianteproyectoListEstudianteproyecto = em.merge(estudianteproyectoListEstudianteproyecto);
                if (oldEstudiante1OfEstudianteproyectoListEstudianteproyecto != null) {
                    oldEstudiante1OfEstudianteproyectoListEstudianteproyecto.getEstudianteproyectoList().remove(estudianteproyectoListEstudianteproyecto);
                    oldEstudiante1OfEstudianteproyectoListEstudianteproyecto = em.merge(oldEstudiante1OfEstudianteproyectoListEstudianteproyecto);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEstudiante(estudiante.getCodigo()) != null) {
                throw new PreexistingEntityException("Estudiante " + estudiante + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Estudiante estudiante) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estudiante persistentEstudiante = em.find(Estudiante.class, estudiante.getCodigo());
            Carrera carreraOld = persistentEstudiante.getCarrera();
            Carrera carreraNew = estudiante.getCarrera();
            List<Estudianteproyecto> estudianteproyectoListOld = persistentEstudiante.getEstudianteproyectoList();
            List<Estudianteproyecto> estudianteproyectoListNew = estudiante.getEstudianteproyectoList();
            List<String> illegalOrphanMessages = null;
            for (Estudianteproyecto estudianteproyectoListOldEstudianteproyecto : estudianteproyectoListOld) {
                if (!estudianteproyectoListNew.contains(estudianteproyectoListOldEstudianteproyecto)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Estudianteproyecto " + estudianteproyectoListOldEstudianteproyecto + " since its estudiante1 field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (carreraNew != null) {
                carreraNew = em.getReference(carreraNew.getClass(), carreraNew.getCodigo());
                estudiante.setCarrera(carreraNew);
            }
            List<Estudianteproyecto> attachedEstudianteproyectoListNew = new ArrayList<Estudianteproyecto>();
            for (Estudianteproyecto estudianteproyectoListNewEstudianteproyectoToAttach : estudianteproyectoListNew) {
                estudianteproyectoListNewEstudianteproyectoToAttach = em.getReference(estudianteproyectoListNewEstudianteproyectoToAttach.getClass(), estudianteproyectoListNewEstudianteproyectoToAttach.getEstudianteproyectoPK());
                attachedEstudianteproyectoListNew.add(estudianteproyectoListNewEstudianteproyectoToAttach);
            }
            estudianteproyectoListNew = attachedEstudianteproyectoListNew;
            estudiante.setEstudianteproyectoList(estudianteproyectoListNew);
            estudiante = em.merge(estudiante);
            if (carreraOld != null && !carreraOld.equals(carreraNew)) {
                carreraOld.getEstudianteList().remove(estudiante);
                carreraOld = em.merge(carreraOld);
            }
            if (carreraNew != null && !carreraNew.equals(carreraOld)) {
                carreraNew.getEstudianteList().add(estudiante);
                carreraNew = em.merge(carreraNew);
            }
            for (Estudianteproyecto estudianteproyectoListNewEstudianteproyecto : estudianteproyectoListNew) {
                if (!estudianteproyectoListOld.contains(estudianteproyectoListNewEstudianteproyecto)) {
                    Estudiante oldEstudiante1OfEstudianteproyectoListNewEstudianteproyecto = estudianteproyectoListNewEstudianteproyecto.getEstudiante1();
                    estudianteproyectoListNewEstudianteproyecto.setEstudiante1(estudiante);
                    estudianteproyectoListNewEstudianteproyecto = em.merge(estudianteproyectoListNewEstudianteproyecto);
                    if (oldEstudiante1OfEstudianteproyectoListNewEstudianteproyecto != null && !oldEstudiante1OfEstudianteproyectoListNewEstudianteproyecto.equals(estudiante)) {
                        oldEstudiante1OfEstudianteproyectoListNewEstudianteproyecto.getEstudianteproyectoList().remove(estudianteproyectoListNewEstudianteproyecto);
                        oldEstudiante1OfEstudianteproyectoListNewEstudianteproyecto = em.merge(oldEstudiante1OfEstudianteproyectoListNewEstudianteproyecto);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = estudiante.getCodigo();
                if (findEstudiante(id) == null) {
                    throw new NonexistentEntityException("The estudiante with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estudiante estudiante;
            try {
                estudiante = em.getReference(Estudiante.class, id);
                estudiante.getCodigo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The estudiante with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Estudianteproyecto> estudianteproyectoListOrphanCheck = estudiante.getEstudianteproyectoList();
            for (Estudianteproyecto estudianteproyectoListOrphanCheckEstudianteproyecto : estudianteproyectoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Estudiante (" + estudiante + ") cannot be destroyed since the Estudianteproyecto " + estudianteproyectoListOrphanCheckEstudianteproyecto + " in its estudianteproyectoList field has a non-nullable estudiante1 field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Carrera carrera = estudiante.getCarrera();
            if (carrera != null) {
                carrera.getEstudianteList().remove(estudiante);
                carrera = em.merge(carrera);
            }
            em.remove(estudiante);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Estudiante> findEstudianteEntities() {
        return findEstudianteEntities(true, -1, -1);
    }

    public List<Estudiante> findEstudianteEntities(int maxResults, int firstResult) {
        return findEstudianteEntities(false, maxResults, firstResult);
    }

    private List<Estudiante> findEstudianteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Estudiante.class));
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

    public Estudiante findEstudiante(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Estudiante.class, id);
        } finally {
            em.close();
        }
    }

    public int getEstudianteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Estudiante> rt = cq.from(Estudiante.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
