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
import DTO.Juradoanteproyecto;
import java.util.ArrayList;
import java.util.List;
import DTO.Asesor;
import DTO.Docente;
import DTO.Juradoproyecto;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author stive
 */
public class DocenteJpaController implements Serializable {

    public DocenteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
     public DocenteJpaController(){
        emf = Conexion.getEm();
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Docente docente) throws PreexistingEntityException, Exception {
        if (docente.getJuradoanteproyectoList() == null) {
            docente.setJuradoanteproyectoList(new ArrayList<Juradoanteproyecto>());
        }
        if (docente.getAsesorList() == null) {
            docente.setAsesorList(new ArrayList<Asesor>());
        }
        if (docente.getJuradoproyectoList() == null) {
            docente.setJuradoproyectoList(new ArrayList<Juradoproyecto>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Carrera carrera = docente.getCarrera();
            if (carrera != null) {
                carrera = em.getReference(carrera.getClass(), carrera.getCodigo());
                docente.setCarrera(carrera);
            }
            List<Juradoanteproyecto> attachedJuradoanteproyectoList = new ArrayList<Juradoanteproyecto>();
            for (Juradoanteproyecto juradoanteproyectoListJuradoanteproyectoToAttach : docente.getJuradoanteproyectoList()) {
                juradoanteproyectoListJuradoanteproyectoToAttach = em.getReference(juradoanteproyectoListJuradoanteproyectoToAttach.getClass(), juradoanteproyectoListJuradoanteproyectoToAttach.getJuradoanteproyectoPK());
                attachedJuradoanteproyectoList.add(juradoanteproyectoListJuradoanteproyectoToAttach);
            }
            docente.setJuradoanteproyectoList(attachedJuradoanteproyectoList);
            List<Asesor> attachedAsesorList = new ArrayList<Asesor>();
            for (Asesor asesorListAsesorToAttach : docente.getAsesorList()) {
                asesorListAsesorToAttach = em.getReference(asesorListAsesorToAttach.getClass(), asesorListAsesorToAttach.getAsesorPK());
                attachedAsesorList.add(asesorListAsesorToAttach);
            }
            docente.setAsesorList(attachedAsesorList);
            List<Juradoproyecto> attachedJuradoproyectoList = new ArrayList<Juradoproyecto>();
            for (Juradoproyecto juradoproyectoListJuradoproyectoToAttach : docente.getJuradoproyectoList()) {
                juradoproyectoListJuradoproyectoToAttach = em.getReference(juradoproyectoListJuradoproyectoToAttach.getClass(), juradoproyectoListJuradoproyectoToAttach.getJuradoproyectoPK());
                attachedJuradoproyectoList.add(juradoproyectoListJuradoproyectoToAttach);
            }
            docente.setJuradoproyectoList(attachedJuradoproyectoList);
            em.persist(docente);
            if (carrera != null) {
                carrera.getDocenteList().add(docente);
                carrera = em.merge(carrera);
            }
            for (Juradoanteproyecto juradoanteproyectoListJuradoanteproyecto : docente.getJuradoanteproyectoList()) {
                Docente oldDocente1OfJuradoanteproyectoListJuradoanteproyecto = juradoanteproyectoListJuradoanteproyecto.getDocente1();
                juradoanteproyectoListJuradoanteproyecto.setDocente1(docente);
                juradoanteproyectoListJuradoanteproyecto = em.merge(juradoanteproyectoListJuradoanteproyecto);
                if (oldDocente1OfJuradoanteproyectoListJuradoanteproyecto != null) {
                    oldDocente1OfJuradoanteproyectoListJuradoanteproyecto.getJuradoanteproyectoList().remove(juradoanteproyectoListJuradoanteproyecto);
                    oldDocente1OfJuradoanteproyectoListJuradoanteproyecto = em.merge(oldDocente1OfJuradoanteproyectoListJuradoanteproyecto);
                }
            }
            for (Asesor asesorListAsesor : docente.getAsesorList()) {
                Docente oldDocente1OfAsesorListAsesor = asesorListAsesor.getDocente1();
                asesorListAsesor.setDocente1(docente);
                asesorListAsesor = em.merge(asesorListAsesor);
                if (oldDocente1OfAsesorListAsesor != null) {
                    oldDocente1OfAsesorListAsesor.getAsesorList().remove(asesorListAsesor);
                    oldDocente1OfAsesorListAsesor = em.merge(oldDocente1OfAsesorListAsesor);
                }
            }
            for (Juradoproyecto juradoproyectoListJuradoproyecto : docente.getJuradoproyectoList()) {
                Docente oldDocente1OfJuradoproyectoListJuradoproyecto = juradoproyectoListJuradoproyecto.getDocente1();
                juradoproyectoListJuradoproyecto.setDocente1(docente);
                juradoproyectoListJuradoproyecto = em.merge(juradoproyectoListJuradoproyecto);
                if (oldDocente1OfJuradoproyectoListJuradoproyecto != null) {
                    oldDocente1OfJuradoproyectoListJuradoproyecto.getJuradoproyectoList().remove(juradoproyectoListJuradoproyecto);
                    oldDocente1OfJuradoproyectoListJuradoproyecto = em.merge(oldDocente1OfJuradoproyectoListJuradoproyecto);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDocente(docente.getCodigo()) != null) {
                throw new PreexistingEntityException("Docente " + docente + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Docente docente) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Docente persistentDocente = em.find(Docente.class, docente.getCodigo());
            Carrera carreraOld = persistentDocente.getCarrera();
            Carrera carreraNew = docente.getCarrera();
            List<Juradoanteproyecto> juradoanteproyectoListOld = persistentDocente.getJuradoanteproyectoList();
            List<Juradoanteproyecto> juradoanteproyectoListNew = docente.getJuradoanteproyectoList();
            List<Asesor> asesorListOld = persistentDocente.getAsesorList();
            List<Asesor> asesorListNew = docente.getAsesorList();
            List<Juradoproyecto> juradoproyectoListOld = persistentDocente.getJuradoproyectoList();
            List<Juradoproyecto> juradoproyectoListNew = docente.getJuradoproyectoList();
            List<String> illegalOrphanMessages = null;
            for (Juradoanteproyecto juradoanteproyectoListOldJuradoanteproyecto : juradoanteproyectoListOld) {
                if (!juradoanteproyectoListNew.contains(juradoanteproyectoListOldJuradoanteproyecto)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Juradoanteproyecto " + juradoanteproyectoListOldJuradoanteproyecto + " since its docente1 field is not nullable.");
                }
            }
            for (Asesor asesorListOldAsesor : asesorListOld) {
                if (!asesorListNew.contains(asesorListOldAsesor)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Asesor " + asesorListOldAsesor + " since its docente1 field is not nullable.");
                }
            }
            for (Juradoproyecto juradoproyectoListOldJuradoproyecto : juradoproyectoListOld) {
                if (!juradoproyectoListNew.contains(juradoproyectoListOldJuradoproyecto)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Juradoproyecto " + juradoproyectoListOldJuradoproyecto + " since its docente1 field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (carreraNew != null) {
                carreraNew = em.getReference(carreraNew.getClass(), carreraNew.getCodigo());
                docente.setCarrera(carreraNew);
            }
            List<Juradoanteproyecto> attachedJuradoanteproyectoListNew = new ArrayList<Juradoanteproyecto>();
            for (Juradoanteproyecto juradoanteproyectoListNewJuradoanteproyectoToAttach : juradoanteproyectoListNew) {
                juradoanteproyectoListNewJuradoanteproyectoToAttach = em.getReference(juradoanteproyectoListNewJuradoanteproyectoToAttach.getClass(), juradoanteproyectoListNewJuradoanteproyectoToAttach.getJuradoanteproyectoPK());
                attachedJuradoanteproyectoListNew.add(juradoanteproyectoListNewJuradoanteproyectoToAttach);
            }
            juradoanteproyectoListNew = attachedJuradoanteproyectoListNew;
            docente.setJuradoanteproyectoList(juradoanteproyectoListNew);
            List<Asesor> attachedAsesorListNew = new ArrayList<Asesor>();
            for (Asesor asesorListNewAsesorToAttach : asesorListNew) {
                asesorListNewAsesorToAttach = em.getReference(asesorListNewAsesorToAttach.getClass(), asesorListNewAsesorToAttach.getAsesorPK());
                attachedAsesorListNew.add(asesorListNewAsesorToAttach);
            }
            asesorListNew = attachedAsesorListNew;
            docente.setAsesorList(asesorListNew);
            List<Juradoproyecto> attachedJuradoproyectoListNew = new ArrayList<Juradoproyecto>();
            for (Juradoproyecto juradoproyectoListNewJuradoproyectoToAttach : juradoproyectoListNew) {
                juradoproyectoListNewJuradoproyectoToAttach = em.getReference(juradoproyectoListNewJuradoproyectoToAttach.getClass(), juradoproyectoListNewJuradoproyectoToAttach.getJuradoproyectoPK());
                attachedJuradoproyectoListNew.add(juradoproyectoListNewJuradoproyectoToAttach);
            }
            juradoproyectoListNew = attachedJuradoproyectoListNew;
            docente.setJuradoproyectoList(juradoproyectoListNew);
            docente = em.merge(docente);
            if (carreraOld != null && !carreraOld.equals(carreraNew)) {
                carreraOld.getDocenteList().remove(docente);
                carreraOld = em.merge(carreraOld);
            }
            if (carreraNew != null && !carreraNew.equals(carreraOld)) {
                carreraNew.getDocenteList().add(docente);
                carreraNew = em.merge(carreraNew);
            }
            for (Juradoanteproyecto juradoanteproyectoListNewJuradoanteproyecto : juradoanteproyectoListNew) {
                if (!juradoanteproyectoListOld.contains(juradoanteproyectoListNewJuradoanteproyecto)) {
                    Docente oldDocente1OfJuradoanteproyectoListNewJuradoanteproyecto = juradoanteproyectoListNewJuradoanteproyecto.getDocente1();
                    juradoanteproyectoListNewJuradoanteproyecto.setDocente1(docente);
                    juradoanteproyectoListNewJuradoanteproyecto = em.merge(juradoanteproyectoListNewJuradoanteproyecto);
                    if (oldDocente1OfJuradoanteproyectoListNewJuradoanteproyecto != null && !oldDocente1OfJuradoanteproyectoListNewJuradoanteproyecto.equals(docente)) {
                        oldDocente1OfJuradoanteproyectoListNewJuradoanteproyecto.getJuradoanteproyectoList().remove(juradoanteproyectoListNewJuradoanteproyecto);
                        oldDocente1OfJuradoanteproyectoListNewJuradoanteproyecto = em.merge(oldDocente1OfJuradoanteproyectoListNewJuradoanteproyecto);
                    }
                }
            }
            for (Asesor asesorListNewAsesor : asesorListNew) {
                if (!asesorListOld.contains(asesorListNewAsesor)) {
                    Docente oldDocente1OfAsesorListNewAsesor = asesorListNewAsesor.getDocente1();
                    asesorListNewAsesor.setDocente1(docente);
                    asesorListNewAsesor = em.merge(asesorListNewAsesor);
                    if (oldDocente1OfAsesorListNewAsesor != null && !oldDocente1OfAsesorListNewAsesor.equals(docente)) {
                        oldDocente1OfAsesorListNewAsesor.getAsesorList().remove(asesorListNewAsesor);
                        oldDocente1OfAsesorListNewAsesor = em.merge(oldDocente1OfAsesorListNewAsesor);
                    }
                }
            }
            for (Juradoproyecto juradoproyectoListNewJuradoproyecto : juradoproyectoListNew) {
                if (!juradoproyectoListOld.contains(juradoproyectoListNewJuradoproyecto)) {
                    Docente oldDocente1OfJuradoproyectoListNewJuradoproyecto = juradoproyectoListNewJuradoproyecto.getDocente1();
                    juradoproyectoListNewJuradoproyecto.setDocente1(docente);
                    juradoproyectoListNewJuradoproyecto = em.merge(juradoproyectoListNewJuradoproyecto);
                    if (oldDocente1OfJuradoproyectoListNewJuradoproyecto != null && !oldDocente1OfJuradoproyectoListNewJuradoproyecto.equals(docente)) {
                        oldDocente1OfJuradoproyectoListNewJuradoproyecto.getJuradoproyectoList().remove(juradoproyectoListNewJuradoproyecto);
                        oldDocente1OfJuradoproyectoListNewJuradoproyecto = em.merge(oldDocente1OfJuradoproyectoListNewJuradoproyecto);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = docente.getCodigo();
                if (findDocente(id) == null) {
                    throw new NonexistentEntityException("The docente with id " + id + " no longer exists.");
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
            Docente docente;
            try {
                docente = em.getReference(Docente.class, id);
                docente.getCodigo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The docente with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Juradoanteproyecto> juradoanteproyectoListOrphanCheck = docente.getJuradoanteproyectoList();
            for (Juradoanteproyecto juradoanteproyectoListOrphanCheckJuradoanteproyecto : juradoanteproyectoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Docente (" + docente + ") cannot be destroyed since the Juradoanteproyecto " + juradoanteproyectoListOrphanCheckJuradoanteproyecto + " in its juradoanteproyectoList field has a non-nullable docente1 field.");
            }
            List<Asesor> asesorListOrphanCheck = docente.getAsesorList();
            for (Asesor asesorListOrphanCheckAsesor : asesorListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Docente (" + docente + ") cannot be destroyed since the Asesor " + asesorListOrphanCheckAsesor + " in its asesorList field has a non-nullable docente1 field.");
            }
            List<Juradoproyecto> juradoproyectoListOrphanCheck = docente.getJuradoproyectoList();
            for (Juradoproyecto juradoproyectoListOrphanCheckJuradoproyecto : juradoproyectoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Docente (" + docente + ") cannot be destroyed since the Juradoproyecto " + juradoproyectoListOrphanCheckJuradoproyecto + " in its juradoproyectoList field has a non-nullable docente1 field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Carrera carrera = docente.getCarrera();
            if (carrera != null) {
                carrera.getDocenteList().remove(docente);
                carrera = em.merge(carrera);
            }
            em.remove(docente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Docente> findDocenteEntities() {
        return findDocenteEntities(true, -1, -1);
    }

    public List<Docente> findDocenteEntities(int maxResults, int firstResult) {
        return findDocenteEntities(false, maxResults, firstResult);
    }

    private List<Docente> findDocenteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Docente.class));
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

    public Docente findDocente(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Docente.class, id);
        } finally {
            em.close();
        }
    }

    public int getDocenteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Docente> rt = cq.from(Docente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
