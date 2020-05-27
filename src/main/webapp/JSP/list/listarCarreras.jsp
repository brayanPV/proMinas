<%-- 
    Document   : listarCarreras
    Created on : 27/05/2020, 01:04:31 PM
    Author     : stive
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="DTO.Carrera"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="../../templates/meta.jsp"/>
        <title>Listado de carreras</title>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <a class="btn btn-primary" href="../../index.jsp" role="button">Inicio</a> </br>
                <p>Listado de las carreras inscritas </p> </br>
                <table id="example" class="table table-striped table-bordered" style="width:100%">
                    <thead>
                        <tr>
                            <th>Codigo</th>
                            <th>Nombre</th>
                            <th>Credito</th>
                            <th>Semestre</th>
                        </tr>
                    </thead>
                    <tbody>
                        <jsp:useBean id="CarreraDAO" class="DAO.CarreraJpaController" scope="request"></jsp:useBean>
                        <c:forEach var="car" items="${CarreraDAO.findCarreraEntities()}">
                            <tr>
                                <td><c:out value="${car.getCodigo()}"/></td>  
                                <td><c:out value="${car.getNombre()}"/></td> 
                                <td><c:out value="${car.getCredito()}"/></td> 
                                <td><c:out value="${car.getSemestre()}"/></td> 
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
    <jsp:include page="../../templates/footer.jsp"/>
</html>
