<%-- 
    Document   : registrarProyecto
    Created on : 27/05/2020, 04:14:41 PM
    Author     : stive
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="DTO.Docente"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="../templates/meta.jsp"/>
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container">
            <a class="btn btn-primary" role="button" href="../index.jsp">INDEX </a>
            <p>FORMULARIO DE REGISTRO DE PROYECTOS</p>
            <form action="../RegistroProController" method="POST">
                <div class="form-group">

                    <label for="director">Director</label>
                    <jsp:useBean id="docenteDAO" class="DAO.DocenteJpaController" scope="request"></jsp:useBean>
                        <select class="form-control" name="carrera">
                            <option>Selecciones un director de proyecto</option>
                        <c:forEach var="u" items="${docenteDAO.findDocenteEntities()}">
                            <option value="<c:out value="${u.getCodigo()}"/>"><c:out value="${u.getCodigo()} -- ${u.getNombre()}"/></option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="titulo">Titulo</label>
                    <input type="text" class="form-control" name="titulo" placeholder="Ingrese el titulo de la propuesta">
                </div>
                <div class="form-group">
                    <label for="resumen">Resumen</label>
                    <input type="text" class="form-control" name="resumen" placeholder="Ingrese un resumen de la propuesta">
                </div>
                <div class="form-group">
                    <label for="fechaInicio">Fecha inicio</label>
                    <input type="date" class="form-control" name="fechaInicio" placeholder="Ingrese la fecha de inicio">
                </div>
                <div class="form-group">
                    <label for="fechaFin">Fecha fin</label>
                    <input type="date" class="form-control" name="fechaFin" placeholder="Ingrese la fecha de fin">
                </div>
                <button type="submit" class="btn btn-primary">Registrar</button>
            </form>
        </div>
        <jsp:include page="../templates/footer.jsp"/> 
    </body>
</html>
