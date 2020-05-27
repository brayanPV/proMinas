-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 27-05-2020 a las 03:46:13
-- Versión del servidor: 10.4.11-MariaDB
-- Versión de PHP: 7.4.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `prominas`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `asesor`
--

CREATE TABLE `asesor` (
  `proyecto` int(11) NOT NULL,
  `docente` varchar(5) NOT NULL,
  `tipo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `carrera`
--

CREATE TABLE `carrera` (
  `codigo` varchar(3) NOT NULL,
  `nombre` varchar(200) NOT NULL,
  `credito` int(11) NOT NULL,
  `semestre` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `docente`
--

CREATE TABLE `docente` (
  `carrera` varchar(3) NOT NULL,
  `codigo` varchar(5) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `apellido` varchar(100) NOT NULL,
  `fechanacimiento` date NOT NULL,
  `genero` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estudiante`
--

CREATE TABLE `estudiante` (
  `codigo` varchar(7) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `apellido` varchar(100) NOT NULL,
  `carrera` varchar(3) NOT NULL,
  `fechanacimiento` date NOT NULL,
  `genero` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estudianteproyecto`
--

CREATE TABLE `estudianteproyecto` (
  `estudiante` varchar(7) NOT NULL,
  `proyecto` int(11) NOT NULL,
  `campo` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `juradoanteproyecto`
--

CREATE TABLE `juradoanteproyecto` (
  `docente` varchar(5) NOT NULL,
  `proyecto` int(11) NOT NULL,
  `observacion` varchar(500) NOT NULL,
  `nota` int(11) NOT NULL,
  `fechaasignacion` date NOT NULL,
  `fechanota` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `juradoproyecto`
--

CREATE TABLE `juradoproyecto` (
  `docente` varchar(5) NOT NULL,
  `proyecto` int(11) NOT NULL,
  `observacion` varchar(500) NOT NULL,
  `nota` int(11) NOT NULL,
  `fechaasignacion` date NOT NULL,
  `fechanota` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proyecto`
--

CREATE TABLE `proyecto` (
  `director` varchar(5) NOT NULL,
  `id` int(11) NOT NULL,
  `titulo` varchar(200) NOT NULL,
  `fechainicio` date NOT NULL,
  `fechafin` date NOT NULL,
  `resumen` varchar(500) NOT NULL,
  `fechaanteproyecto` date NOT NULL,
  `fechaproyecto` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipoasesor`
--

CREATE TABLE `tipoasesor` (
  `id` int(11) NOT NULL,
  `descripcion` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `asesor`
--
ALTER TABLE `asesor`
  ADD PRIMARY KEY (`proyecto`,`docente`),
  ADD KEY `docente` (`docente`),
  ADD KEY `tipo` (`tipo`);

--
-- Indices de la tabla `carrera`
--
ALTER TABLE `carrera`
  ADD PRIMARY KEY (`codigo`);

--
-- Indices de la tabla `docente`
--
ALTER TABLE `docente`
  ADD PRIMARY KEY (`codigo`),
  ADD KEY `carrera` (`carrera`);

--
-- Indices de la tabla `estudiante`
--
ALTER TABLE `estudiante`
  ADD PRIMARY KEY (`codigo`),
  ADD KEY `carrera` (`carrera`);

--
-- Indices de la tabla `estudianteproyecto`
--
ALTER TABLE `estudianteproyecto`
  ADD PRIMARY KEY (`estudiante`,`proyecto`),
  ADD KEY `proyecto` (`proyecto`);

--
-- Indices de la tabla `juradoanteproyecto`
--
ALTER TABLE `juradoanteproyecto`
  ADD PRIMARY KEY (`docente`,`proyecto`),
  ADD KEY `proyecto` (`proyecto`);

--
-- Indices de la tabla `juradoproyecto`
--
ALTER TABLE `juradoproyecto`
  ADD PRIMARY KEY (`docente`,`proyecto`),
  ADD KEY `proyecto` (`proyecto`);

--
-- Indices de la tabla `proyecto`
--
ALTER TABLE `proyecto`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `tipoasesor`
--
ALTER TABLE `tipoasesor`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `proyecto`
--
ALTER TABLE `proyecto`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `tipoasesor`
--
ALTER TABLE `tipoasesor`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `asesor`
--
ALTER TABLE `asesor`
  ADD CONSTRAINT `asesor_ibfk_1` FOREIGN KEY (`docente`) REFERENCES `docente` (`codigo`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `asesor_ibfk_2` FOREIGN KEY (`proyecto`) REFERENCES `proyecto` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `asesor_ibfk_3` FOREIGN KEY (`tipo`) REFERENCES `tipoasesor` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `docente`
--
ALTER TABLE `docente`
  ADD CONSTRAINT `docente_ibfk_1` FOREIGN KEY (`carrera`) REFERENCES `carrera` (`codigo`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `estudiante`
--
ALTER TABLE `estudiante`
  ADD CONSTRAINT `estudiante_ibfk_1` FOREIGN KEY (`carrera`) REFERENCES `carrera` (`codigo`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `estudianteproyecto`
--
ALTER TABLE `estudianteproyecto`
  ADD CONSTRAINT `estudianteproyecto_ibfk_1` FOREIGN KEY (`estudiante`) REFERENCES `estudiante` (`codigo`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `estudianteproyecto_ibfk_2` FOREIGN KEY (`proyecto`) REFERENCES `proyecto` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `juradoanteproyecto`
--
ALTER TABLE `juradoanteproyecto`
  ADD CONSTRAINT `juradoanteproyecto_ibfk_1` FOREIGN KEY (`docente`) REFERENCES `docente` (`codigo`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `juradoanteproyecto_ibfk_2` FOREIGN KEY (`proyecto`) REFERENCES `proyecto` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `juradoproyecto`
--
ALTER TABLE `juradoproyecto`
  ADD CONSTRAINT `juradoproyecto_ibfk_1` FOREIGN KEY (`docente`) REFERENCES `docente` (`codigo`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `juradoproyecto_ibfk_2` FOREIGN KEY (`proyecto`) REFERENCES `proyecto` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
