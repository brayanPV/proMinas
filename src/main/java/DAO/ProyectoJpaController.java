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
import DTO.Estudianteproyecto;
import java.util.ArrayList;
import java.util.List;
import DTO.Juradoanteproyecto;
import DTO.Asesor;
import DTO.Juradoproyecto;
import DTO.Proyecto;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author stive
 */
public class ProyectoJpaController implements Serializable {

    public ProyectoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Proyecto proyecto) {
        if (proyecto.getEstudianteproyectoList() == null) {
            proyecto.setEstudianteproyectoList(new ArrayList<Estudianteproyecto>());
        }
        if (proyecto.getJuradoanteproyectoList() == null) {
            proyecto.setJuradoanteproyectoList(new ArrayList<Juradoanteproyecto>());
        }
        if (proyecto.getAsesorList() == null) {
            proyecto.setAsesorList(new ArrayList<Asesor>());
        }
        if (proyecto.getJuradoproyectoList() == null) {
            proyecto.setJuradoproyectoList(new ArrayList<Juradoproyecto>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Estudianteproyecto> attachedEstudianteproyectoList = new ArrayList<Estudianteproyecto>();
            for (Estudianteproyecto estudianteproyectoListEstudianteproyectoToAttach : proyecto.getEstudianteproyectoList()) {
                estudianteproyectoListEstudianteproyectoToAttach = em.getReference(estudianteproyectoListEstudianteproyectoToAttach.getClass(), estudianteproyectoListEstudianteproyectoToAttach.getEstudianteproyectoPK());
                attachedEstudianteproyectoList.add(estudianteproyectoListEstudianteproyectoToAttach);
            }
            proyecto.setEstudianteproyectoList(attachedEstudianteproyectoList);
            List<Juradoanteproyecto> attachedJuradoanteproyectoList = new ArrayList<Juradoanteproyecto>();
            for (Juradoanteproyecto juradoanteproyectoListJuradoanteproyectoToAttach : proyecto.getJuradoanteproyectoList()) {
                juradoanteproyectoListJuradoanteproyectoToAttach = em.getReference(juradoanteproyectoListJuradoanteproyectoToAttach.getClass(), juradoanteproyectoListJuradoanteproyectoToAttach.getJuradoanteproyectoPK());
                attachedJuradoanteproyectoList.add(juradoanteproyectoListJuradoanteproyectoToAttach);
            }
            proyecto.setJuradoanteproyectoList(attachedJuradoanteproyectoList);
            List<Asesor> attachedAsesorList = new ArrayList<Asesor>();
            for (Asesor asesorListAsesorToAttach : proyecto.getAsesorList()) {
                asesorListAsesorToAttach = em.getReference(asesorListAsesorToAttach.getClass(), asesorListAsesorToAttach.getAsesorPK());
                attachedAsesorList.add(asesorListAsesorToAttach);
            }
            proyecto.setAsesorList(attachedAsesorList);
            List<Juradoproyecto> attachedJuradoproyectoList = new ArrayList<Juradoproyecto>();
            for (Juradoproyecto juradoproyectoListJuradoproyectoToAttach : proyecto.getJuradoproyectoList()) {
                juradoproyectoListJuradoproyectoToAttach = em.getReference(juradoproyectoListJuradoproyectoToAttach.getClass(), juradoproyectoListJuradoproyectoToAttach.getJuradoproyectoPK());
                attachedJuradoproyectoList.add(juradoproyectoListJuradoproyectoToAttach);
            }
            proyecto.setJuradoproyectoList(attachedJuradoproyectoList);
            em.persist(proyecto);
            for (Estudianteproyecto estudianteproyectoListEstudianteproyecto : proyecto.getEstudianteproyectoList()) {
                Proyecto oldProyecto1OfEstudianteproyectoListEstudianteproyecto = estudianteproyectoListEstudianteproyecto.getProyecto1();
                estudianteproyectoListEstudianteproyecto.setProyecto1(proyecto);
                estudianteproyectoListEstudianteproyecto = em.merge(estudianteproyectoListEstudianteproyecto);
                if (oldProyecto1OfEstudianteproyectoListEstudianteproyecto != null) {
                    oldProyecto1OfEstudianteproyectoListEstudianteproyecto.getEstudianteproyectoList().remove(estudianteproyectoListEstudianteproyecto);
                    oldProyecto1OfEstudianteproyectoListEstudianteproyecto = em.merge(oldProyecto1OfEstudianteproyectoListEstudianteproyecto);
                }
            }
            for (Juradoanteproyecto juradoanteproyectoListJuradoanteproyecto : proyecto.getJuradoanteproyectoList()) {
                Proyecto oldProyecto1OfJuradoanteproyectoListJuradoanteproyecto = juradoanteproyectoListJuradoanteproyecto.getProyecto1();
                juradoanteproyectoListJuradoanteproyecto.setProyecto1(proyecto);
                juradoanteproyectoListJuradoanteproyecto = em.merge(juradoanteproyectoListJuradoanteproyecto);
                if (oldProyecto1OfJuradoanteproyectoListJuradoanteproyecto != null) {
                    oldProyecto1OfJuradoanteproyectoListJuradoanteproyecto.getJuradoanteproyectoList().remove(juradoanteproyectoListJuradoanteproyecto);
                    oldProyecto1OfJuradoanteproyectoListJuradoanteproyecto = em.merge(oldProyecto1OfJuradoanteproyectoListJuradoanteproyecto);
                }
            }
            for (Asesor asesorListAsesor : proyecto.getAsesorList()) {
                Proyecto oldProyecto1OfAsesorListAsesor = asesorListAsesor.getProyecto1();
                asesorListAsesor.setProyecto1(proyecto);
                asesorListAsesor = em.merge(asesorListAsesor);
                if (oldProyecto1OfAsesorListAsesor != null) {
                    oldProyecto1OfAsesorListAsesor.getAsesorList().remove(asesorListAsesor);
                    oldProyecto1OfAsesorListAsesor = em.merge(oldProyecto1OfAsesorListAsesor);
                }
            }
            for (Juradoproyecto juradoproyectoListJuradoproyecto : proyecto.getJuradoproyectoList()) {
                Proyecto oldProyecto1OfJuradoproyectoListJuradoproyecto = juradoproyectoListJuradoproyecto.getProyecto1();
                juradoproyectoListJuradoproyecto.setProyecto1(proyecto);
                juradoproyectoListJuradoproyecto = em.merge(juradoproyectoListJuradoproyecto);
                if (oldProyecto1OfJuradoproyectoListJuradoproyecto != null) {
                    oldProyecto1OfJuradoproyectoListJuradoproyecto.getJuradoproyectoList().remove(juradoproyectoListJuradoproyecto);
                    oldProyecto1OfJuradoproyectoListJuradoproyecto = em.merge(oldProyecto1OfJuradoproyectoListJuradoproyecto);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Proyecto proyecto) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Proyecto persistentProyecto = em.find(Proyecto.class, proyecto.getId());
            List<Estudianteproyecto> estudianteproyectoListOld = persistentProyecto.getEstudianteproyectoList();
            List<Estudianteproyecto> estudianteproyectoListNew = proyecto.getEstudianteproyectoList();
            List<Juradoanteproyecto> juradoanteproyectoListOld = persistentProyecto.getJuradoanteproyectoList();
            List<Juradoanteproyecto> juradoanteproyectoListNew = proyecto.getJuradoanteproyectoList();
            List<Asesor> asesorListOld = persistentProyecto.getAsesorList();
            List<Asesor> asesorListNew = proyecto.getAsesorList();
            List<Juradoproyecto> juradoproyectoListOld = persistentProyecto.getJuradoproyectoList();
            List<Juradoproyecto> juradoproyectoListNew = proyecto.getJuradoproyectoList();
            List<String> illegalOrphanMessages = null;
            for (Estudianteproyecto estudianteproyectoListOldEstudianteproyecto : estudianteproyectoListOld) {
                if (!estudianteproyectoListNew.contains(estudianteproyectoListOldEstudianteproyecto)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Estudianteproyecto " + estudianteproyectoListOldEstudianteproyecto + " since its proyecto1 field is not nullable.");
                }
            }
            for (Juradoanteproyecto juradoanteproyectoListOldJuradoanteproyecto : juradoanteproyectoListOld) {
                if (!juradoanteproyectoListNew.contains(juradoanteproyectoListOldJuradoanteproyecto)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Juradoanteproyecto " + juradoanteproyectoListOldJuradoanteproyecto + " since its proyecto1 field is not nullable.");
                }
            }
            for (Asesor asesorListOldAsesor : asesorListOld) {
                if (!asesorListNew.contains(asesorListOldAsesor)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Asesor " + asesorListOldAsesor + " since its proyecto1 field is not nullable.");
                }
            }
            for (Juradoproyecto juradoproyectoListOldJuradoproyecto : juradoproyectoListOld) {
                if (!juradoproyectoListNew.contains(juradoproyectoListOldJuradoproyecto)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Juradoproyecto " + juradoproyectoListOldJuradoproyecto + " since its proyecto1 field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Estudianteproyecto> attachedEstudianteproyectoListNew = new ArrayList<Estudianteproyecto>();
            for (Estudianteproyecto estudianteproyectoListNewEstudianteproyectoToAttach : estudianteproyectoListNew) {
                estudianteproyectoListNewEstudianteproyectoToAttach = em.getReference(estudianteproyectoListNewEstudianteproyectoToAttach.getClass(), estudianteproyectoListNewEstudianteproyectoToAttach.getEstudianteproyectoPK());
                attachedEstudianteproyectoListNew.add(estudianteproyectoListNewEstudianteproyectoToAttach);
            }
            estudianteproyectoListNew = attachedEstudianteproyectoListNew;
            proyecto.setEstudianteproyectoList(estudianteproyectoListNew);
            List<Juradoanteproyecto> attachedJuradoanteproyectoListNew = new ArrayList<Juradoanteproyecto>();
            for (Juradoanteproyecto juradoanteproyectoListNewJuradoanteproyectoToAttach : juradoanteproyectoListNew) {
                juradoanteproyectoListNewJuradoanteproyectoToAttach = em.getReference(juradoanteproyectoListNewJuradoanteproyectoToAttach.getClass(), juradoanteproyectoListNewJuradoanteproyectoToAttach.getJuradoanteproyectoPK());
                attachedJuradoanteproyectoListNew.add(juradoanteproyectoListNewJuradoanteproyectoToAttach);
            }
            juradoanteproyectoListNew = attachedJuradoanteproyectoListNew;
            proyecto.setJuradoanteproyectoList(juradoanteproyectoListNew);
            List<Asesor> attachedAsesorListNew = new ArrayList<Asesor>();
            for (Asesor asesorListNewAsesorToAttach : asesorListNew) {
                asesorListNewAsesorToAttach = em.getReference(asesorListNewAsesorToAttach.getClass(), asesorListNewAsesorToAttach.getAsesorPK());
                attachedAsesorListNew.add(asesorListNewAsesorToAttach);
            }
            asesorListNew = attachedAsesorListNew;
            proyecto.setAsesorList(asesorListNew);
            List<Juradoproyecto> attachedJuradoproyectoListNew = new ArrayList<Juradoproyecto>();
            for (Juradoproyecto juradoproyectoListNewJuradoproyectoToAttach : juradoproyectoListNew) {
                juradoproyectoListNewJuradoproyectoToAttach = em.getReference(juradoproyectoListNewJuradoproyectoToAttach.getClass(), juradoproyectoListNewJuradoproyectoToAttach.getJuradoproyectoPK());
                attachedJuradoproyectoListNew.add(juradoproyectoListNewJuradoproyectoToAttach);
            }
            juradoproyectoListNew = attachedJuradoproyectoListNew;
            proyecto.setJuradoproyectoList(juradoproyectoListNew);
            proyecto = em.merge(proyecto);
            for (Estudianteproyecto estudianteproyectoListNewEstudianteproyecto : estudianteproyectoListNew) {
                if (!estudianteproyectoListOld.contains(estudianteproyectoListNewEstudianteproyecto)) {
                    Proyecto oldProyecto1OfEstudianteproyectoListNewEstudianteproyecto = estudianteproyectoListNewEstudianteproyecto.getProyecto1();
                    estudianteproyectoListNewEstudianteproyecto.setProyecto1(proyecto);
                    estudianteproyectoListNewEstudianteproyecto = em.merge(estudianteproyectoListNewEstudianteproyecto);
                    if (oldProyecto1OfEstudianteproyectoListNewEstudianteproyecto != null && !oldProyecto1OfEstudianteproyectoListNewEstudianteproyecto.equals(proyecto)) {
                        oldProyecto1OfEstudianteproyectoListNewEstudianteproyecto.getEstudianteproyectoList().remove(estudianteproyectoListNewEstudianteproyecto);
                        oldProyecto1OfEstudianteproyectoListNewEstudianteproyecto = em.merge(oldProyecto1OfEstudianteproyectoListNewEstudianteproyecto);
                    }
                }
            }
            for (Juradoanteproyecto juradoanteproyectoListNewJuradoanteproyecto : juradoanteproyectoListNew) {
                if (!juradoanteproyectoListOld.contains(juradoanteproyectoListNewJuradoanteproyecto)) {
                    Proyecto oldProyecto1OfJuradoanteproyectoListNewJuradoanteproyecto = juradoanteproyectoListNewJuradoanteproyecto.getProyecto1();
                    juradoanteproyectoListNewJuradoanteproyecto.setProyecto1(proyecto);
                    juradoanteproyectoListNewJuradoanteproyecto = em.merge(juradoanteproyectoListNewJuradoanteproyecto);
                    if (oldProyecto1OfJuradoanteproyectoListNewJuradoanteproyecto != null && !oldProyecto1OfJuradoanteproyectoListNewJuradoanteproyecto.equals(proyecto)) {
                        oldProyecto1OfJuradoanteproyectoListNewJuradoanteproyecto.getJuradoanteproyectoList().remove(juradoanteproyectoListNewJuradoanteproyecto);
                        oldProyecto1OfJuradoanteproyectoListNewJuradoanteproyecto = em.merge(oldProyecto1OfJuradoanteproyectoListNewJuradoanteproyecto);
                    }
                }
            }
            for (Asesor asesorListNewAsesor : asesorListNew) {
                if (!asesorListOld.contains(asesorListNewAsesor)) {
                    Proyecto oldProyecto1OfAsesorListNewAsesor = asesorListNewAsesor.getProyecto1();
                    asesorListNewAsesor.setProyecto1(proyecto);
                    asesorListNewAsesor = em.merge(asesorListNewAsesor);
                    if (oldProyecto1OfAsesorListNewAsesor != null && !oldProyecto1OfAsesorListNewAsesor.equals(proyecto)) {
                        oldProyecto1OfAsesorListNewAsesor.getAsesorList().remove(asesorListNewAsesor);
                        oldProyecto1OfAsesorListNewAsesor = em.merge(oldProyecto1OfAsesorListNewAsesor);
                    }
                }
            }
            for (Juradoproyecto juradoproyectoListNewJuradoproyecto : juradoproyectoListNew) {
                if (!juradoproyectoListOld.contains(juradoproyectoListNewJuradoproyecto)) {
                    Proyecto oldProyecto1OfJuradoproyectoListNewJuradoproyecto = juradoproyectoListNewJuradoproyecto.getProyecto1();
                    juradoproyectoListNewJuradoproyecto.setProyecto1(proyecto);
                    juradoproyectoListNewJuradoproyecto = em.merge(juradoproyectoListNewJuradoproyecto);
                    if (oldProyecto1OfJuradoproyectoListNewJuradoproyecto != null && !oldProyecto1OfJuradoproyectoListNewJuradoproyecto.equals(proyecto)) {
                        oldProyecto1OfJuradoproyectoListNewJuradoproyecto.getJuradoproyectoList().remove(juradoproyectoListNewJuradoproyecto);
                        oldProyecto1OfJuradoproyectoListNewJuradoproyecto = em.merge(oldProyecto1OfJuradoproyectoListNewJuradoproyecto);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = proyecto.getId();
                if (findProyecto(id) == null) {
                    throw new NonexistentEntityException("The proyecto with id " + id + " no longer exists.");
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
            Proyecto proyecto;
            try {
                proyecto = em.getReference(Proyecto.class, id);
                proyecto.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The proyecto with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Estudianteproyecto> estudianteproyectoListOrphanCheck = proyecto.getEstudianteproyectoList();
            for (Estudianteproyecto estudianteproyectoListOrphanCheckEstudianteproyecto : estudianteproyectoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Proyecto (" + proyecto + ") cannot be destroyed since the Estudianteproyecto " + estudianteproyectoListOrphanCheckEstudianteproyecto + " in its estudianteproyectoList field has a non-nullable proyecto1 field.");
            }
            List<Juradoanteproyecto> juradoanteproyectoListOrphanCheck = proyecto.getJuradoanteproyectoList();
            for (Juradoanteproyecto juradoanteproyectoListOrphanCheckJuradoanteproyecto : juradoanteproyectoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Proyecto (" + proyecto + ") cannot be destroyed since the Juradoanteproyecto " + juradoanteproyectoListOrphanCheckJuradoanteproyecto + " in its juradoanteproyectoList field has a non-nullable proyecto1 field.");
            }
            List<Asesor> asesorListOrphanCheck = proyecto.getAsesorList();
            for (Asesor asesorListOrphanCheckAsesor : asesorListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Proyecto (" + proyecto + ") cannot be destroyed since the Asesor " + asesorListOrphanCheckAsesor + " in its asesorList field has a non-nullable proyecto1 field.");
            }
            List<Juradoproyecto> juradoproyectoListOrphanCheck = proyecto.getJuradoproyectoList();
            for (Juradoproyecto juradoproyectoListOrphanCheckJuradoproyecto : juradoproyectoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Proyecto (" + proyecto + ") cannot be destroyed since the Juradoproyecto " + juradoproyectoListOrphanCheckJuradoproyecto + " in its juradoproyectoList field has a non-nullable proyecto1 field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(proyecto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Proyecto> findProyectoEntities() {
        return findProyectoEntities(true, -1, -1);
    }

    public List<Proyecto> findProyectoEntities(int maxResults, int firstResult) {
        return findProyectoEntities(false, maxResults, firstResult);
    }

    private List<Proyecto> findProyectoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Proyecto.class));
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

    public Proyecto findProyecto(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Proyecto.class, id);
        } finally {
            em.close();
        }
    }

    public int getProyectoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Proyecto> rt = cq.from(Proyecto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
