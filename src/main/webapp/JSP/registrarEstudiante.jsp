<%-- 
    Document   : registrarEstudiante
    Created on : 26/05/2020, 08:52:23 PM
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
        <jsp:include page="../templates/meta.jsp"/>
        <title>Index</title>
    </head>
    <body>
        <div class="container">
            <a class="btn btn-primary" role="button" href="../index.jsp">INDEX </a>
            <p>FORMULARIO DE REGISTRO DE ESTUDIANTE</p>
            <form action="../RegistroEstController" method="POST">
                <div class="form-group">

                    <label for="codigo">Codigo</label>
                    <input type="text" class="form-control" name="codigo" aria-describedby="codigo" placeholder="Ingrese su codigo">

                </div>
                <div class="form-group">
                    <label for="nombre">Nombre</label>
                    <input type="text" class="form-control" name="nombre" placeholder="Ingrese su nombre">
                </div>
                <div class="form-group">
                    <label for="apellido">Apellido</label>
                    <input type="text" class="form-control" name="apellido" placeholder="Ingrese su apellido">
                </div>
                <div class="form-group">
                    <label for="nombre">Carrera</label>
                    <jsp:useBean id="carreraDAO" class="DAO.CarreraJpaController" scope="request"></jsp:useBean>
                        <select class="form-control" name="carrera">
                            <option>Selecciones una carrera</option>
                        <c:forEach var="u" items="${carreraDAO.findCarreraEntities()}">
                            <option value="<c:out value="${u.getCodigo()}"/>"><c:out value="${u.getCodigo()} -- ${u.getNombre()}"/></option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="fechaN">Fecha Nacimiento</label>
                    <input type="Date" class="form-control" name="fechaN" placeholder="Ingrese su fecha de nacimiento">
                </div>
                <div class="form-group">
                    <label for="genero">Genero</label>
                    <select class="form-control" name="genero">
                        <option>Selecciones un genero</option>
                        <option value="true">Masculino</option>
                        <option value="false">Femenino</option>
                    </select>

                </div>
                <button type="submit" class="btn btn-primary">Registrar</button>
            </form>
        </div>
        <jsp:include page="../templates/footer.jsp"/> 
    </body>
</html>
