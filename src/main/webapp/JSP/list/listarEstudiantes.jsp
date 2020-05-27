<%-- 
    Document   : listarEstudiantes
    Created on : 27/05/2020, 01:04:39 PM
    Author     : stive
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="DTO.Estudiante"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="../../templates/meta.jsp"/>
        <title>Listado de Estudiantes</title>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <a class="btn btn-primary" href="../../index.jsp" role="button">Inicio</a> </br>
                <p>Listado de los Estudiantes </p> </br>
                <table id="example" class="table table-striped table-bordered" style="width:100%">
                    <thead>
                        <tr>
                            <th>Codigo</th>
                            <th>Nombre</th>
                            <th>Apellido</th>
                            <th>Carrera</th>
                            <th>Fecha Nacimiento</th>
                            <th>Genero</th>
                        </tr>
                    </thead>
                    
                        <jsp:useBean id="estudianteDAO" class="DAO.EstudianteJpaController" scope="request"></jsp:useBean>
                        <c:forEach var="est" items="${estudianteDAO.findEstudianteEntities()}">
                            <tr>
                                <td><c:out value="${est.getCodigo()}"/></td>  
                                <td><c:out value="${est.getNombre()}"/></td> 
                                <td><c:out value="${est.getApellido()}"/></td> 
                                <td><c:out value="${est.getCarrera().toString()}"/></td> 
                                <td><c:out value="${est.getFechanacimiento()}"/></td> 
                                <td><c:out value="${est.getGenero() ? 'MASCULINO' : 'FEMENINO'}"/></td> 
                            </tr>
                        </c:forEach>
                    
                </table>
            </div>
        </div>
    </body>
    <jsp:include page="../../templates/footer.jsp"/>
</html>