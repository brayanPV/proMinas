/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.exceptions.IllegalOrphanException;
import DAO.exceptions.NonexistentEntityException;
import DAO.exceptions.PreexistingEntityException;
import DTO.Carrera;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import DTO.Estudiante;
import java.util.ArrayList;
import java.util.List;
import DTO.Docente;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author stive
 */
public class CarreraJpaController implements Serializable {

    public CarreraJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public CarreraJpaController(){
        this.emf = Conexion.getEm();
    }

    public void create(Carrera carrera) throws PreexistingEntityException, Exception {
        if (carrera.getEstudianteList() == null) {
            carrera.setEstudianteList(new ArrayList<Estudiante>());
        }
        if (carrera.getDocenteList() == null) {
            carrera.setDocenteList(new ArrayList<Docente>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Estudiante> attachedEstudianteList = new ArrayList<Estudiante>();
            for (Estudiante estudianteListEstudianteToAttach : carrera.getEstudianteList()) {
                estudianteListEstudianteToAttach = em.getReference(estudianteListEstudianteToAttach.getClass(), estudianteListEstudianteToAttach.getCodigo());
                attachedEstudianteList.add(estudianteListEstudianteToAttach);
            }
            carrera.setEstudianteList(attachedEstudianteList);
            List<Docente> attachedDocenteList = new ArrayList<Docente>();
            for (Docente docenteListDocenteToAttach : carrera.getDocenteList()) {
                docenteListDocenteToAttach = em.getReference(docenteListDocenteToAttach.getClass(), docenteListDocenteToAttach.getCodigo());
                attachedDocenteList.add(docenteListDocenteToAttach);
            }
            carrera.setDocenteList(attachedDocenteList);
            em.persist(carrera);
            for (Estudiante estudianteListEstudiante : carrera.getEstudianteList()) {
                Carrera oldCarreraOfEstudianteListEstudiante = estudianteListEstudiante.getCarrera();
                estudianteListEstudiante.setCarrera(carrera);
                estudianteListEstudiante = em.merge(estudianteListEstudiante);
                if (oldCarreraOfEstudianteListEstudiante != null) {
                    oldCarreraOfEstudianteListEstudiante.getEstudianteList().remove(estudianteListEstudiante);
                    oldCarreraOfEstudianteListEstudiante = em.merge(oldCarreraOfEstudianteListEstudiante);
                }
            }
            for (Docente docenteListDocente : carrera.getDocenteList()) {
                Carrera oldCarreraOfDocenteListDocente = docenteListDocente.getCarrera();
                docenteListDocente.setCarrera(carrera);
                docenteListDocente = em.merge(docenteListDocente);
                if (oldCarreraOfDocenteListDocente != null) {
                    oldCarreraOfDocenteListDocente.getDocenteList().remove(docenteListDocente);
                    oldCarreraOfDocenteListDocente = em.merge(oldCarreraOfDocenteListDocente);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCarrera(carrera.getCodigo()) != null) {
                throw new PreexistingEntityException("Carrera " + carrera + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Carrera carrera) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Carrera persistentCarrera = em.find(Carrera.class, carrera.getCodigo());
            List<Estudiante> estudianteListOld = persistentCarrera.getEstudianteList();
            List<Estudiante> estudianteListNew = carrera.getEstudianteList();
            List<Docente> docenteListOld = persistentCarrera.getDocenteList();
            List<Docente> docenteListNew = carrera.getDocenteList();
            List<String> illegalOrphanMessages = null;
            for (Estudiante estudianteListOldEstudiante : estudianteListOld) {
                if (!estudianteListNew.contains(estudianteListOldEstudiante)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Estudiante " + estudianteListOldEstudiante + " since its carrera field is not nullable.");
                }
            }
            for (Docente docenteListOldDocente : docenteListOld) {
                if (!docenteListNew.contains(docenteListOldDocente)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Docente " + docenteListOldDocente + " since its carrera field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Estudiante> attachedEstudianteListNew = new ArrayList<Estudiante>();
            for (Estudiante estudianteListNewEstudianteToAttach : estudianteListNew) {
                estudianteListNewEstudianteToAttach = em.getReference(estudianteListNewEstudianteToAttach.getClass(), estudianteListNewEstudianteToAttach.getCodigo());
                attachedEstudianteListNew.add(estudianteListNewEstudianteToAttach);
            }
            estudianteListNew = attachedEstudianteListNew;
            carrera.setEstudianteList(estudianteListNew);
            List<Docente> attachedDocenteListNew = new ArrayList<Docente>();
            for (Docente docenteListNewDocenteToAttach : docenteListNew) {
                docenteListNewDocenteToAttach = em.getReference(docenteListNewDocenteToAttach.getClass(), docenteListNewDocenteToAttach.getCodigo());
                attachedDocenteListNew.add(docenteListNewDocenteToAttach);
            }
            docenteListNew = attachedDocenteListNew;
            carrera.setDocenteList(docenteListNew);
            carrera = em.merge(carrera);
            for (Estudiante estudianteListNewEstudiante : estudianteListNew) {
                if (!estudianteListOld.contains(estudianteListNewEstudiante)) {
                    Carrera oldCarreraOfEstudianteListNewEstudiante = estudianteListNewEstudiante.getCarrera();
                    estudianteListNewEstudiante.setCarrera(carrera);
                    estudianteListNewEstudiante = em.merge(estudianteListNewEstudiante);
                    if (oldCarreraOfEstudianteListNewEstudiante != null && !oldCarreraOfEstudianteListNewEstudiante.equals(carrera)) {
                        oldCarreraOfEstudianteListNewEstudiante.getEstudianteList().remove(estudianteListNewEstudiante);
                        oldCarreraOfEstudianteListNewEstudiante = em.merge(oldCarreraOfEstudianteListNewEstudiante);
                    }
                }
            }
            for (Docente docenteListNewDocente : docenteListNew) {
                if (!docenteListOld.contains(docenteListNewDocente)) {
                    Carrera oldCarreraOfDocenteListNewDocente = docenteListNewDocente.getCarrera();
                    docenteListNewDocente.setCarrera(carrera);
                    docenteListNewDocente = em.merge(docenteListNewDocente);
                    if (oldCarreraOfDocenteListNewDocente != null && !oldCarreraOfDocenteListNewDocente.equals(carrera)) {
                        oldCarreraOfDocenteListNewDocente.getDocenteList().remove(docenteListNewDocente);
                        oldCarreraOfDocenteListNewDocente = em.merge(oldCarreraOfDocenteListNewDocente);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = carrera.getCodigo();
                if (findCarrera(id) == null) {
                    throw new NonexistentEntityException("The carrera with id " + id + " no longer exists.");
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
            Carrera carrera;
            try {
                carrera = em.getReference(Carrera.class, id);
                carrera.getCodigo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The carrera with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Estudiante> estudianteListOrphanCheck = carrera.getEstudianteList();
            for (Estudiante estudianteListOrphanCheckEstudiante : estudianteListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Carrera (" + carrera + ") cannot be destroyed since the Estudiante " + estudianteListOrphanCheckEstudiante + " in its estudianteList field has a non-nullable carrera field.");
            }
            List<Docente> docenteListOrphanCheck = carrera.getDocenteList();
            for (Docente docenteListOrphanCheckDocente : docenteListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Carrera (" + carrera + ") cannot be destroyed since the Docente " + docenteListOrphanCheckDocente + " in its docenteList field has a non-nullable carrera field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(carrera);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Carrera> findCarreraEntities() {
        return findCarreraEntities(true, -1, -1);
    }

    public List<Carrera> findCarreraEntities(int maxResults, int firstResult) {
        return findCarreraEntities(false, maxResults, firstResult);
    }

    private List<Carrera> findCarreraEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Carrera.class));
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

    public Carrera findCarrera(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Carrera.class, id);
        } finally {
            em.close();
        }
    }

    public int getCarreraCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Carrera> rt = cq.from(Carrera.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
