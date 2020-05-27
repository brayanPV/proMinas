<%-- 
    Document   : index
    Created on : 26/05/2020, 08:52:23 PM
    Author     : stive
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <!--  <//jsp:include page="templates/meta.jsp"/>  -->
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!-- Font Awesome -->
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css">
        <!-- Google Fonts -->
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap">
        <!-- Bootstrap core CSS -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.4.1/css/bootstrap.min.css" rel="stylesheet">
        <!-- Material Design Bootstrap -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.16.0/css/mdb.min.css" rel="stylesheet">
        <title>Index</title>
    </head>
    <body>
        <div class="container">
            <!-- <//jsp:include page="templates/header.jsp"/>  -->
            <header>
                <!--Navbar-->
                <nav class="navbar navbar-expand-lg navbar-dark primary-color">

                    <!-- Navbar brand -->
                    <a class="navbar-brand" href="index.jsp">ProMinas</a>

                    <!-- Collapse button -->
                    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#basicExampleNav" aria-controls="basicExampleNav" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>

                    <!-- Collapsible content -->
                    <div class="collapse navbar-collapse" id="basicExampleNav">

                        <!-- Links -->
                        <ul class="navbar-nav">
                            <li class="nav-item">
                                <a class="nav-link" href="JSP/registrarCarrera.jsp">Registrar Carrera</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="JSP/registrarEstudiante.jsp">Registrar Estudiante</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="JSP/registrarDocente.jsp">Registrar Docente</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="JSP/registrarProyecto.jsp">Registrar Proyecto</a>
                            </li>
                            

                        </ul>
                        <ul class="navbar-nav ml-auto">
                            <li class="nav-item">
                                <a class="nav-link" href="" data-toggle="modal" data-target="#modalLoginForm">Iniciar Sesion</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#" data-toggle="modal" data-target="#modalRegisterForm">Registrarte</a>
                            </li>


                        </ul>
                        <!-- Links -->

                        <!--<form class="form-inline">
                            <div class="md-form my-0">
                                <input class="form-control mr-sm-2" type="text" placeholder="Search" aria-label="Search">
                            </div>
                        </form>-->
                    </div>
                    <!-- Collapsible content -->

                </nav>
                <!--/.Navbar-->
            </header>
            <div class="row">
                <div class="col-md-8">
                    <section>
                        <p>HEY AQUI VAMOS A ASOCIAR COSAS</p>

                    </section>
                </div>
                <div class="col-md-4">
                    <aside>
                        <p>En los siguientes enlaces puede encontrar diferente listados</p>
                        <ul>
                            <li>
                                <a href="JSP/list/listarCarreras.jsp"> VER CARRERAS </a>
                            </li>
                            <li>
                                <a href="JSP/list/listarDocentes.jsp"> VER DOCENTES </a>
                            </li>
                            <li>
                                <a href="JSP/list/listarEstudiantes.jsp"> VER ESTUDIANTES </a>
                            </li>
                        </ul>
                        
                    </aside>
                </div>

            </div>



        
        <!-- <//jsp:include page="templates/footer.jsp"/> -->

        <div class="row">
        <footer class="page-footer font-small mdb-color lighten-3 pt-4">

            <!-- Footer Elements -->
            <div class="container">

                <!--Grid row-->
                <div class="row">

                    <!--Grid column-->
                    <div class="col-lg-3 col-md-12 mb-4">

                        <!--Image-->
                        <div class="view overlay z-depth-1-half">
                            <img src="https://www.dnp.gov.co/img/gobierno-de-colombia.jpg" class="img-fluid" alt="">
                            <a href="">
                                <div class="mask rgba-white-light"></div>
                            </a>
                        </div>

                    </div>
                    <!--Grid column-->

                    <!--Grid column-->
                    <div class="col-lg-3 col-md-6 mb-4">

                        <!--Image-->
                        <div class="view overlay z-depth-1-half">
                            <img src="https://codaltec.com/sites/all/themes/bootstrap/images/logo.jpg" class="img-fluid" alt="">
                            <a href="">
                                <div class="mask rgba-white-light"></div>
                            </a>
                        </div>

                    </div>
                    <!--Grid column-->

                    <!--Grid column-->
                    <div class="col-lg-3 col-md-6 mb-4">

                        <!--Image-->
                        <div class="view overlay z-depth-1-half">
                            <img src="http://www.cccucuta.org.co/imagenes//uploads/secc_ac26913a2a67ad1ca455a167cec22fe4.jpg" class="img-fluid" alt="">
                            <a href="">
                                <div class="mask rgba-white-light"></div>
                            </a>
                        </div>

                    </div>
                    <!--Grid column-->

                    <!--Grid column-->
                    <div class="col-lg-3 col-md-12 mb-4">

                        <!--Image-->
                        <div class="view overlay z-depth-1-half">
                            <img src="https://ww2.ufps.edu.co/public/archivos/elementos_corporativos/Logo-nuevo-horizontal.png" alt="" class="img-fluid">
                            <a href="">
                                <div class="mask rgba-white-light"></div>
                            </a>
                        </div>

                    </div>
                    <!--Grid column-->





                </div>
                <!--Grid row-->

            </div>
            <!-- Footer Elements -->

            <!-- Copyright -->
            <div class="footer-copyright text-center py-3">© 2020 Copyright:
                <a href="https://mdbootstrap.com/"> Brayan Stiven Palomino - 1151370</a>
            </div>
            <!-- Copyright -->

        </footer>
        <!-- Footer -->
        </div>
        </div>
    </body>

    <!-- JQuery -->
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <!-- Bootstrap tooltips -->
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.4/umd/popper.min.js"></script>
    <!-- Bootstrap core JavaScript -->
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.4.1/js/bootstrap.min.js"></script>
    <!-- MDB core JavaScript -->
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.16.0/js/mdb.min.js"></script>
    <script type="text/javascript">

   </html>
