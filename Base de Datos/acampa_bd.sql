-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 08-04-2018 a las 03:02:11
-- Versión del servidor: 10.1.30-MariaDB
-- Versión de PHP: 7.2.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `acampa_bd`
--

DELIMITER $$
--
-- Procedimientos
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `contador` ()  BEGIN
DECLARE vIdMax Integer;
DECLARE vCont Integer DEFAULT 1;
SET vIdMax = (select IdUsuario from usuario order by idusuario desc limit 1);
WHILE vCont <= vIdMax DO
	Call prueba(vCont);
	SET vCont = vCont + 1;
END WHILE;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `prueba` (IN `Idu` INT)  BEGIN
DECLARE vFecha VarChar(50);
DECLARE vEdad Integer;
DECLARE vSeccion Integer;

SET vFecha = (SELECT FechaNacimiento FROM usuario WHERE IdUsuario = Idu);
SET vEdad = (SELECT TIMESTAMPDIFF(YEAR,vFecha,CURDATE()));

IF vEdad < 11 THEN
    	SET vSeccion = (SELECT idseccion from seccion where Nombre = 'manada');
    ELSEIF vEdad < 15 THEN
    	SET vSeccion = (SELECT idseccion from seccion where Nombre = 'tropa');
    ELSEIF vEdad < 18 THEN
    	SET vSeccion = (SELECT idseccion from seccion where Nombre = 'comunidad');
    ElSEIF vEdad < 22 THEN
    	SET vSeccion = (SELECT idseccion from seccion where Nombre = 'clan');
    ELSE
    	SET vSeccion = (SELECT idseccion from seccion where Nombre = 'dirigente');
END IF;
UPDATE usuario SET ApellidoPaterno = vFecha where IdUsuario = Idu;
UPDATE usuario SET ApellidoMaterno = vEdad where IdUsuario = Idu;
UPDATE Usuario SET Fkseccion = vSeccion WHERE IdUsuario = Idu;

END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `adultomayor`
--

CREATE TABLE `adultomayor` (
  `IdAdultoMayor` int(11) NOT NULL,
  `Nombre` varchar(50) DEFAULT NULL,
  `ApellidoPaterno` varchar(50) DEFAULT NULL,
  `ApellidoMaterno` varchar(50) CHARACTER SET latin1 NOT NULL,
  `Fotografia` varchar(100) CHARACTER SET latin1 NOT NULL,
  `Diabetico` tinyint(1) NOT NULL,
  `FkDependencia` int(11) DEFAULT NULL,
  `FkDomicilio` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

--
-- Volcado de datos para la tabla `adultomayor`
--

INSERT INTO `adultomayor` (`IdAdultoMayor`, `Nombre`, `ApellidoPaterno`, `ApellidoMaterno`, `Fotografia`, `Diabetico`, `FkDependencia`, `FkDomicilio`) VALUES
(1, 'Patricia', 'González', 'Martinez', 'img/am/1.jpg', 0, 2, 1),
(2, 'Manuel', 'Alvarez', 'Figueroa', 'img/am/2.jpg', 0, 1, 2),
(3, 'Ramiro', 'Pérez', 'Chavez', 'img/am/9.jpg', 0, 2, 3),
(4, 'Facundo', 'Cabral', 'Ramiro', 'img/am/90.jpg', 1, 1, 4),
(5, 'Luis', 'Reyes', 'Martinez', 'img/am/50.jpg', 1, 3, 5),
(6, 'JoseMi', 'González', 'Pérez', 'img/0001.jpg', 0, 3, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `asignacion`
--

CREATE TABLE `asignacion` (
  `IdAsignacion` int(11) NOT NULL,
  `Status` tinyint(1) NOT NULL,
  `Fecha` date NOT NULL,
  `FkUsuario` int(11) DEFAULT NULL,
  `FkAdultoMayor` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `asignacion`
--

INSERT INTO `asignacion` (`IdAsignacion`, `Status`, `Fecha`, `FkUsuario`, `FkAdultoMayor`) VALUES
(1, 1, '2018-03-12', 1, 1),
(2, 1, '2018-03-12', 3, 2),
(8, 1, '2018-10-23', 3, 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `comentarioam`
--

CREATE TABLE `comentarioam` (
  `IdComentarioAM` int(11) NOT NULL,
  `Nombre` text NOT NULL,
  `Fecha` date NOT NULL,
  `FkAdultoMayor` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `comentarioam`
--

INSERT INTO `comentarioam` (`IdComentarioAM`, `Nombre`, `Fecha`, `FkAdultoMayor`) VALUES
(1, 'Hola me parece buena personA', '2018-03-12', 1),
(2, 'Hola es un poco extraño, pero me agrada', '2018-03-12', 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `coordinador`
--

CREATE TABLE `coordinador` (
  `IdCoordinador` int(11) NOT NULL,
  `FkScouter` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `coordinador`
--

INSERT INTO `coordinador` (`IdCoordinador`, `FkScouter`) VALUES
(1, 1),
(2, 2),
(181, 2),
(182, 2),
(183, 2),
(184, 2),
(185, 2),
(186, 2),
(187, 2),
(188, 2),
(189, 2),
(190, 2),
(191, 2),
(192, 2),
(193, 2),
(194, 2),
(195, 2),
(196, 2),
(197, 2),
(198, 2),
(199, 2),
(200, 2),
(201, 2),
(202, 2),
(203, 2),
(204, 2),
(205, 2),
(206, 2),
(207, 2),
(208, 2),
(209, 2),
(210, 2),
(211, 2),
(212, 2),
(213, 2),
(214, 2),
(215, 2),
(216, 2),
(217, 2),
(218, 2),
(219, 2),
(220, 2),
(221, 2),
(222, 2),
(223, 2),
(224, 2),
(225, 2),
(226, 2),
(227, 2),
(228, 2),
(229, 2),
(230, 2),
(231, 2),
(232, 2),
(233, 2),
(234, 2),
(235, 2),
(236, 2),
(237, 2),
(238, 2),
(239, 2),
(240, 2),
(241, 2),
(242, 2),
(243, 2),
(244, 2),
(245, 2),
(246, 2),
(247, 2),
(248, 2),
(249, 2),
(250, 2),
(251, 2),
(252, 2),
(253, 2),
(254, 2),
(255, 2),
(256, 2),
(257, 2),
(258, 2),
(259, 2),
(260, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `dependencia`
--

CREATE TABLE `dependencia` (
  `IdDependencia` int(11) NOT NULL,
  `Nombre` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `dependencia`
--

INSERT INTO `dependencia` (`IdDependencia`, `Nombre`) VALUES
(1, 'Bajo'),
(2, 'Medio'),
(3, 'Alto');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `domicilio`
--

CREATE TABLE `domicilio` (
  `IdDomicilio` int(11) NOT NULL,
  `Numero` int(11) NOT NULL,
  `Calle` varchar(50) NOT NULL,
  `Colonia` varchar(50) NOT NULL,
  `Foto` varchar(100) NOT NULL,
  `FkUbicacion` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `domicilio`
--

INSERT INTO `domicilio` (`IdDomicilio`, `Numero`, `Calle`, `Colonia`, `Foto`, `FkUbicacion`) VALUES
(1, 23, 'Porfirio', 'Claudio', 'img/09454.jpg', 1),
(2, 12, 'Gigantes', 'calma', 'img/03434.jpg', 2),
(3, 35, 'guadalajara', 'Jalisco', 'img/34343.jpg', 3),
(4, 12, 'roma', 'italia', 'img/0434.jpg', 4),
(5, 13, 'Brasilia', 'Brasil', 'img/0434.jpg', 5),
(6, 768, 'Hola', 'Saludo', 'img/0943.jpg', 6),
(7, 123, 'Angel', 'Terralta', 'img/0943.jpg', 7),
(8, 3456, 'Ciclope', 'Terralta', 'img/9434.jpg', 8),
(9, 21, 'foca', 'hecheverria', 'img/0943.jpg', 9),
(10, 506, 'Pollo', 'Rosticeria', 'img/9432.jpg', 10),
(11, 2, 'Cambio', 'Color', 'img/7.jpg', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `evento`
--

CREATE TABLE `evento` (
  `IdEvento` int(11) NOT NULL,
  `Fecha` date NOT NULL,
  `Hora` time NOT NULL,
  `Lugar` varchar(100) NOT NULL,
  `Informacion` text NOT NULL,
  `FkTipoEvento` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `evento`
--

INSERT INTO `evento` (`IdEvento`, `Fecha`, `Hora`, `Lugar`, `Informacion`, `FkTipoEvento`) VALUES
(1, '2018-03-16', '12:00:00', 'Atemajac de Brizuela', '', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `fotoalrededores`
--

CREATE TABLE `fotoalrededores` (
  `IdFotoAlrededores` int(11) NOT NULL,
  `Foto` varchar(100) NOT NULL,
  `FkDomicilio` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `gestioninventario`
--

CREATE TABLE `gestioninventario` (
  `IdGestionInventario` int(11) NOT NULL,
  `Fecha` date NOT NULL,
  `FkScouter` int(11) NOT NULL,
  `FkInventario` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `inventario`
--

CREATE TABLE `inventario` (
  `IdInventario` int(11) NOT NULL,
  `Producto` varchar(30) NOT NULL,
  `Cantidad` float NOT NULL,
  `Existencia` int(11) NOT NULL,
  `Descripcion` text NOT NULL,
  `Imagen` varchar(100) NOT NULL,
  `Comentario` text NOT NULL,
  `Extra` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `password`
--

CREATE TABLE `password` (
  `IdPassword` int(11) NOT NULL,
  `Password` varchar(30) NOT NULL,
  `Intentos` int(11) NOT NULL,
  `FkUsuario` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `problematica`
--

CREATE TABLE `problematica` (
  `IdProblematica` int(11) NOT NULL,
  `Fecha` date NOT NULL,
  `Nombre` text NOT NULL,
  `Sugerencia` text NOT NULL,
  `FkUsuario` int(11) NOT NULL,
  `FkTipoProblematica` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `recoger`
--

CREATE TABLE `recoger` (
  `IdRecoger` int(11) NOT NULL,
  `FkScouter` int(11) NOT NULL,
  `FkAsignacion` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `scouter`
--

CREATE TABLE `scouter` (
  `IdScouter` int(11) NOT NULL,
  `FechaInicio` date NOT NULL,
  `FechaFinal` date NOT NULL,
  `FkUsuario` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `scouter`
--

INSERT INTO `scouter` (`IdScouter`, `FechaInicio`, `FechaFinal`, `FkUsuario`) VALUES
(1, '2018-03-12', '9999-12-30', 1),
(2, '2018-03-12', '9999-12-30', 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `seccion`
--

CREATE TABLE `seccion` (
  `IdSeccion` int(11) NOT NULL,
  `Nombre` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `seccion`
--

INSERT INTO `seccion` (`IdSeccion`, `Nombre`) VALUES
(1, 'Manada'),
(2, 'Tropa'),
(3, 'Comunidad'),
(4, 'Clan'),
(5, 'Dirigente'),
(6, 'Civil');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipoevento`
--

CREATE TABLE `tipoevento` (
  `IdTipoEvento` int(11) NOT NULL,
  `Nombre` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tipoevento`
--

INSERT INTO `tipoevento` (`IdTipoEvento`, `Nombre`) VALUES
(1, 'Servicio'),
(2, 'Convivio');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipoproblematica`
--

CREATE TABLE `tipoproblematica` (
  `IdTipoProblematica` int(11) NOT NULL,
  `Nombre` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ubicacion`
--

CREATE TABLE `ubicacion` (
  `IdUbicacion` int(11) NOT NULL,
  `Longitud` double NOT NULL,
  `Latitud` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `ubicacion`
--

INSERT INTO `ubicacion` (`IdUbicacion`, `Longitud`, `Latitud`) VALUES
(1, 103.4534335352, -20.434343434343436),
(2, 104.333545454, -20.987554),
(3, 0, 0),
(4, 106.312456, 31.4702),
(5, 106.87979, 32.78),
(6, 106.454545, 32.5687),
(7, 106.856, 32.5473),
(8, 106.5934, 32.1232),
(9, 106.5903, 32.4567),
(10, 106.3456, 32.879),
(11, 106.5678, 32.8904),
(12, 103.43434, 32.6851),
(13, 106.312456, 31.4702),
(14, 106.87979, 32.78),
(15, 106.454545, 32.5687),
(16, 106.856, 32.5473),
(17, 106.5934, 32.1232),
(18, 106.5903, 32.4567),
(19, 106.3456, 32.879),
(20, 106.5678, 32.8904);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `IdUsuario` int(11) NOT NULL,
  `Nombre` varchar(50) NOT NULL,
  `ApellidoPaterno` varchar(50) NOT NULL,
  `ApellidoMaterno` varchar(50) NOT NULL,
  `Correo` varchar(50) NOT NULL,
  `Fotografia` varchar(100) NOT NULL,
  `FechaNacimiento` date NOT NULL,
  `Scout` tinyint(1) NOT NULL,
  `FkSeccion` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`IdUsuario`, `Nombre`, `ApellidoPaterno`, `ApellidoMaterno`, `Correo`, `Fotografia`, `FechaNacimiento`, `Scout`, `FkSeccion`) VALUES
(1, 'Jose Miguel', '1998-10-25', 'Reconocimos 1 plm', 'moringos7@gmail.com', 'Reconocimos 1 plm', '1998-10-25', 0, 4),
(3, 'Daniel', 'Castellanos', 'Miranda', 'fdanycast@gmail.com', 'Reconocimos 1 plm', '1998-10-11', 0, 6),
(4, 'Patricia Lorenza', 'González', 'Quintanar', 'patty_loren26@gmail.com', 'Reconocimos 1 plm', '2018-08-26', 1, 5),
(5, 'Martin Ricardo ', 'Del Rio', 'Grageda', 'langur@gmail.com', 'Reconocimos 1 plm', '2018-11-17', 1, 4),
(6, 'Marcela María', 'Pérez', 'González', 'mace@hotmail.com', 'Reconocimos 1 plm', '2018-11-27', 1, 4),
(8, 'Pancho', 'Hernandez', 'Chavez', 'pancho@gmail.com', 'Reconocimos 1 plm', '2018-07-29', 1, 5),
(9, 'Miguel Angel', 'Pérez', 'Murillo', 'tekton.formen@gmail.com', 'Reconocimos 1 plm', '2018-09-30', 1, 6);

--
-- Disparadores `usuario`
--
DELIMITER $$
CREATE TRIGGER `TriggerSeccion` BEFORE INSERT ON `usuario` FOR EACH ROW BEGIN 
declare vScout INTEGER;
declare VEdad INTEGER;
SET vScout = new.Scout;
IF vScout = 0 THEN
SET NEW.FkSeccion = (SELECT idseccion from seccion where Nombre = 'civil');
ELSE
    SET vEdad = (SELECT 		TIMESTAMPDIFF(YEAR,NEW.FechaNacimiento,CURDATE()));
    IF vEdad < 11 THEN
    	SET new.Fkseccion = (SELECT idseccion from seccion where Nombre = 'manada');
    ELSEIF vEdad < 15 THEN
    	SET new.Fkseccion = (SELECT idseccion from seccion where Nombre = 'tropa');
    ELSEIF vEdad < 18 THEN
    	SET new.Fkseccion = (SELECT idseccion from seccion where Nombre = 'comunidad');
    ElSEIF vEdad < 22 THEN
    	SET new.Fkseccion = (SELECT idseccion from seccion where Nombre = 'clan');
    ELSE
    	SET new.Fkseccion = (SELECT idseccion from seccion where Nombre = 'dirigente');
    END IF;
END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `voluntariofrecuente`
--

CREATE TABLE `voluntariofrecuente` (
  `IdVoluntarioFrecuente` int(11) NOT NULL,
  `FkUsuario` int(11) NOT NULL,
  `FkAdultoMayor` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `adultomayor`
--
ALTER TABLE `adultomayor`
  ADD PRIMARY KEY (`IdAdultoMayor`),
  ADD KEY `FkDependencia` (`FkDependencia`),
  ADD KEY `FkDomicilio` (`FkDomicilio`);

--
-- Indices de la tabla `asignacion`
--
ALTER TABLE `asignacion`
  ADD PRIMARY KEY (`IdAsignacion`),
  ADD KEY `FkAdultoMayor` (`FkAdultoMayor`),
  ADD KEY `FkUsuario` (`FkUsuario`);

--
-- Indices de la tabla `comentarioam`
--
ALTER TABLE `comentarioam`
  ADD PRIMARY KEY (`IdComentarioAM`),
  ADD KEY `FkAdultoMayor` (`FkAdultoMayor`);

--
-- Indices de la tabla `coordinador`
--
ALTER TABLE `coordinador`
  ADD PRIMARY KEY (`IdCoordinador`),
  ADD KEY `FkScouter` (`FkScouter`);

--
-- Indices de la tabla `dependencia`
--
ALTER TABLE `dependencia`
  ADD PRIMARY KEY (`IdDependencia`);

--
-- Indices de la tabla `domicilio`
--
ALTER TABLE `domicilio`
  ADD PRIMARY KEY (`IdDomicilio`),
  ADD KEY `FkUbicacion` (`FkUbicacion`);

--
-- Indices de la tabla `evento`
--
ALTER TABLE `evento`
  ADD PRIMARY KEY (`IdEvento`),
  ADD KEY `FkTipoEvento` (`FkTipoEvento`);

--
-- Indices de la tabla `fotoalrededores`
--
ALTER TABLE `fotoalrededores`
  ADD PRIMARY KEY (`IdFotoAlrededores`),
  ADD KEY `FkDomicilio` (`FkDomicilio`);

--
-- Indices de la tabla `gestioninventario`
--
ALTER TABLE `gestioninventario`
  ADD PRIMARY KEY (`IdGestionInventario`),
  ADD KEY `FkScouter` (`FkScouter`),
  ADD KEY `FkInventario` (`FkInventario`);

--
-- Indices de la tabla `inventario`
--
ALTER TABLE `inventario`
  ADD PRIMARY KEY (`IdInventario`);

--
-- Indices de la tabla `password`
--
ALTER TABLE `password`
  ADD PRIMARY KEY (`IdPassword`),
  ADD KEY `FkUsuario` (`FkUsuario`);

--
-- Indices de la tabla `problematica`
--
ALTER TABLE `problematica`
  ADD PRIMARY KEY (`IdProblematica`),
  ADD KEY `FkUsuario` (`FkUsuario`),
  ADD KEY `problematica_ibfk_2` (`FkTipoProblematica`);

--
-- Indices de la tabla `recoger`
--
ALTER TABLE `recoger`
  ADD PRIMARY KEY (`IdRecoger`),
  ADD KEY `FkScouter` (`FkScouter`),
  ADD KEY `FkAsignacion` (`FkAsignacion`);

--
-- Indices de la tabla `scouter`
--
ALTER TABLE `scouter`
  ADD PRIMARY KEY (`IdScouter`),
  ADD KEY `FkUsuario` (`FkUsuario`);

--
-- Indices de la tabla `seccion`
--
ALTER TABLE `seccion`
  ADD PRIMARY KEY (`IdSeccion`);

--
-- Indices de la tabla `tipoevento`
--
ALTER TABLE `tipoevento`
  ADD PRIMARY KEY (`IdTipoEvento`);

--
-- Indices de la tabla `tipoproblematica`
--
ALTER TABLE `tipoproblematica`
  ADD PRIMARY KEY (`IdTipoProblematica`);

--
-- Indices de la tabla `ubicacion`
--
ALTER TABLE `ubicacion`
  ADD PRIMARY KEY (`IdUbicacion`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`IdUsuario`),
  ADD KEY `FkSeccion` (`FkSeccion`);

--
-- Indices de la tabla `voluntariofrecuente`
--
ALTER TABLE `voluntariofrecuente`
  ADD PRIMARY KEY (`IdVoluntarioFrecuente`),
  ADD KEY `FkUsuario` (`FkUsuario`),
  ADD KEY `FkAdultoMayor` (`FkAdultoMayor`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `adultomayor`
--
ALTER TABLE `adultomayor`
  MODIFY `IdAdultoMayor` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `asignacion`
--
ALTER TABLE `asignacion`
  MODIFY `IdAsignacion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `comentarioam`
--
ALTER TABLE `comentarioam`
  MODIFY `IdComentarioAM` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `coordinador`
--
ALTER TABLE `coordinador`
  MODIFY `IdCoordinador` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=261;

--
-- AUTO_INCREMENT de la tabla `dependencia`
--
ALTER TABLE `dependencia`
  MODIFY `IdDependencia` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `domicilio`
--
ALTER TABLE `domicilio`
  MODIFY `IdDomicilio` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT de la tabla `evento`
--
ALTER TABLE `evento`
  MODIFY `IdEvento` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `fotoalrededores`
--
ALTER TABLE `fotoalrededores`
  MODIFY `IdFotoAlrededores` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `gestioninventario`
--
ALTER TABLE `gestioninventario`
  MODIFY `IdGestionInventario` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `inventario`
--
ALTER TABLE `inventario`
  MODIFY `IdInventario` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `password`
--
ALTER TABLE `password`
  MODIFY `IdPassword` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `problematica`
--
ALTER TABLE `problematica`
  MODIFY `IdProblematica` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `recoger`
--
ALTER TABLE `recoger`
  MODIFY `IdRecoger` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `scouter`
--
ALTER TABLE `scouter`
  MODIFY `IdScouter` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `seccion`
--
ALTER TABLE `seccion`
  MODIFY `IdSeccion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `tipoevento`
--
ALTER TABLE `tipoevento`
  MODIFY `IdTipoEvento` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `tipoproblematica`
--
ALTER TABLE `tipoproblematica`
  MODIFY `IdTipoProblematica` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `ubicacion`
--
ALTER TABLE `ubicacion`
  MODIFY `IdUbicacion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `IdUsuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de la tabla `voluntariofrecuente`
--
ALTER TABLE `voluntariofrecuente`
  MODIFY `IdVoluntarioFrecuente` int(11) NOT NULL AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `adultomayor`
--
ALTER TABLE `adultomayor`
  ADD CONSTRAINT `adultomayor_ibfk_1` FOREIGN KEY (`FkDependencia`) REFERENCES `dependencia` (`IdDependencia`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `adultomayor_ibfk_2` FOREIGN KEY (`FkDomicilio`) REFERENCES `domicilio` (`IdDomicilio`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `asignacion`
--
ALTER TABLE `asignacion`
  ADD CONSTRAINT `asignacion_ibfk_1` FOREIGN KEY (`FkAdultoMayor`) REFERENCES `adultomayor` (`IdAdultoMayor`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `asignacion_ibfk_2` FOREIGN KEY (`FkUsuario`) REFERENCES `usuario` (`IdUsuario`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `comentarioam`
--
ALTER TABLE `comentarioam`
  ADD CONSTRAINT `comentarioam_ibfk_1` FOREIGN KEY (`FkAdultoMayor`) REFERENCES `adultomayor` (`IdAdultoMayor`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `coordinador`
--
ALTER TABLE `coordinador`
  ADD CONSTRAINT `coordinador_ibfk_1` FOREIGN KEY (`FkScouter`) REFERENCES `scouter` (`IdScouter`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `domicilio`
--
ALTER TABLE `domicilio`
  ADD CONSTRAINT `domicilio_ibfk_1` FOREIGN KEY (`FkUbicacion`) REFERENCES `ubicacion` (`IdUbicacion`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `evento`
--
ALTER TABLE `evento`
  ADD CONSTRAINT `evento_ibfk_1` FOREIGN KEY (`FkTipoEvento`) REFERENCES `tipoevento` (`IdTipoEvento`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `fotoalrededores`
--
ALTER TABLE `fotoalrededores`
  ADD CONSTRAINT `fotoalrededores_ibfk_1` FOREIGN KEY (`FkDomicilio`) REFERENCES `domicilio` (`IdDomicilio`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `gestioninventario`
--
ALTER TABLE `gestioninventario`
  ADD CONSTRAINT `gestioninventario_ibfk_1` FOREIGN KEY (`FkScouter`) REFERENCES `scouter` (`IdScouter`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `gestioninventario_ibfk_2` FOREIGN KEY (`FkInventario`) REFERENCES `inventario` (`IdInventario`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `password`
--
ALTER TABLE `password`
  ADD CONSTRAINT `password_ibfk_1` FOREIGN KEY (`FkUsuario`) REFERENCES `usuario` (`IdUsuario`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `problematica`
--
ALTER TABLE `problematica`
  ADD CONSTRAINT `problematica_ibfk_1` FOREIGN KEY (`FkUsuario`) REFERENCES `usuario` (`IdUsuario`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `problematica_ibfk_2` FOREIGN KEY (`FkTipoProblematica`) REFERENCES `tipoproblematica` (`IdTipoProblematica`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `recoger`
--
ALTER TABLE `recoger`
  ADD CONSTRAINT `recoger_ibfk_1` FOREIGN KEY (`FkScouter`) REFERENCES `scouter` (`IdScouter`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `recoger_ibfk_2` FOREIGN KEY (`FkAsignacion`) REFERENCES `asignacion` (`IdAsignacion`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `scouter`
--
ALTER TABLE `scouter`
  ADD CONSTRAINT `scouter_ibfk_1` FOREIGN KEY (`FkUsuario`) REFERENCES `usuario` (`IdUsuario`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `usuario_ibfk_1` FOREIGN KEY (`FkSeccion`) REFERENCES `seccion` (`IdSeccion`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `voluntariofrecuente`
--
ALTER TABLE `voluntariofrecuente`
  ADD CONSTRAINT `voluntariofrecuente_ibfk_1` FOREIGN KEY (`FkUsuario`) REFERENCES `usuario` (`IdUsuario`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `voluntariofrecuente_ibfk_2` FOREIGN KEY (`FkAdultoMayor`) REFERENCES `adultomayor` (`IdAdultoMayor`) ON DELETE CASCADE ON UPDATE CASCADE;

DELIMITER $$
--
-- Eventos
--
CREATE DEFINER=`root`@`localhost` EVENT `CambioSeccion` ON SCHEDULE EVERY 100 SECOND STARTS '2018-03-28 19:50:52' ON COMPLETION NOT PRESERVE ENABLE DO BEGIN
	INSERT INTO coordinador values (null,2);
END$$

DELIMITER ;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
