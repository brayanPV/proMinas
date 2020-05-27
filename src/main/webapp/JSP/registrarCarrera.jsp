<%-- 
    Document   : registrarCarrera
    Created on : 27/05/2020, 12:25:08 PM
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
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container">
            <button> <a href="../index.jsp">INDEX </a></button>
            <p>FORMULARIO DE REGISTRO DE CARRERAS</p>
            <form action="../RegistroCarController" method="POST">
                <div class="form-group">

                    <label for="codigo">Codigo</label>
                    <input type="text" class="form-control" name="codigo" aria-describedby="codigo" placeholder="Ingrese el codigo de la carrera">

                </div>
                <div class="form-group">
                    <label for="nombre">Nombre</label>
                    <input type="text" class="form-control" name="nombre" placeholder="Ingrese el nombre de la carrera">
                </div>
                <div class="form-group">
                    <label for="credito">Creditos</label>
                    <input type="number" class="form-control" name="credito" placeholder="Ingrese la cantidad de creditos">
                </div>
                <div class="form-group">
                    <label for="semestre">Semestre</label>
                    <input type="number" class="form-control" name="semestre" placeholder="Ingrese la cantidad de semestres">
                </div>
                <button type="submit" class="btn btn-primary">Registrar</button>
            </form>
        </div>
        <jsp:include page="../templates/footer.jsp"/> 
    </body>
</html>
