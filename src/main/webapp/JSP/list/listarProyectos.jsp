<%-- 
    Document   : listarProyectos
    Created on : 27/05/2020, 05:49:10 PM
    Author     : stive
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="DTO.Proyecto"%>
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
                            <th>Id</th>
                            <th>Director</th>
                            <th>Titulo</th>
                            <th>Resumen</th>
                            <th>Fecha Inicio</th>
                            <th>Fecha Fin</th>
                            <th>Fecha Anteproyecto</th>
                            <th>Fecha Proyecto</th>
                        </tr>
                    </thead>

                    <jsp:useBean id="proyectoDAO" class="DAO.ProyectoJpaController" scope="request"></jsp:useBean>
                    <c:forEach var="pro" items="${proyectoDAO.findProyectoEntities()}">
                        <tr>
                            <td><c:out value="${pro.getId()}"/></td>  
                            <td><c:out value="${pro.getDirector().toString()}"/></td> 
                            <td><c:out value="${pro.getTitulo()}"/></td> 
                            <td><c:out value="${pro.getResumen()}"/></td> 
                            <td><c:out value="${pro.getFechainicio()}"/></td> 
                            <td><c:out value="${pro.getFechafin()}"/></td> 
                            <td><c:out value="${pro.getFechaanteproyecto()}"/></td> 
                            <td><c:out value="${pro.getFechaproyecto()}"/></td> 
                        </tr>
                    </c:forEach>

                </table>
            </div>
        </div>
    </body>
    <jsp:include page="../../templates/footer.jsp"/>
</html>