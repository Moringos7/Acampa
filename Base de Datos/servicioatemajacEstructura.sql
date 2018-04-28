-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 28-04-2018 a las 02:23:55
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
-- Base de datos: `servicioatemajac`
--

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

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `coordinador`
--

CREATE TABLE `coordinador` (
  `IdCoordinador` int(11) NOT NULL,
  `FkScouter` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `dependencia`
--

CREATE TABLE `dependencia` (
  `IdDependencia` int(11) NOT NULL,
  `Nombre` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
  `Password` varchar(100) NOT NULL,
  `Intentos` int(11) NOT NULL,
  `FechaLogin` date NOT NULL,
  `Status` tinyint(1) NOT NULL DEFAULT '1',
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
  `FkUsuario` int(11) DEFAULT NULL,
  `FkTipoProblematica` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `recoger`
--

CREATE TABLE `recoger` (
  `IdRecoger` int(11) NOT NULL,
  `FkScouter` int(11) DEFAULT NULL,
  `FkAsignacion` int(11) DEFAULT NULL
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

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `seccion`
--

CREATE TABLE `seccion` (
  `IdSeccion` int(11) NOT NULL,
  `Nombre` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipoevento`
--

CREATE TABLE `tipoevento` (
  `IdTipoEvento` int(11) NOT NULL,
  `Nombre` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
-- Disparadores `usuario`
--
DELIMITER $$
CREATE TRIGGER `TriggerSeccionEditar` BEFORE UPDATE ON `usuario` FOR EACH ROW BEGIN 
declare vScout INTEGER;
declare VEdad INTEGER; 
SET vScout = new.Scout;
IF vScout = 0 THEN 
	SET NEW.FkSeccion = (SELECT idseccion from seccion where Nombre = 'civil'); 
ELSE 
	SET vEdad = (SELECT TIMESTAMPDIFF(YEAR,NEW.FechaNacimiento,CURDATE()));
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
DELIMITER $$
CREATE TRIGGER `TriggerSeccionInsertar` BEFORE INSERT ON `usuario` FOR EACH ROW BEGIN 
declare vScout INTEGER; 
declare VEdad INTEGER; 
SET vScout = new.Scout; 
IF vScout = 0 THEN 
	SET NEW.FkSeccion = (SELECT idseccion from seccion where Nombre = 'civil');
ELSE 
	SET vEdad = (SELECT TIMESTAMPDIFF(YEAR,NEW.FechaNacimiento,CURDATE()));
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
  MODIFY `IdAdultoMayor` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `asignacion`
--
ALTER TABLE `asignacion`
  MODIFY `IdAsignacion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT de la tabla `comentarioam`
--
ALTER TABLE `comentarioam`
  MODIFY `IdComentarioAM` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `coordinador`
--
ALTER TABLE `coordinador`
  MODIFY `IdCoordinador` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `dependencia`
--
ALTER TABLE `dependencia`
  MODIFY `IdDependencia` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `domicilio`
--
ALTER TABLE `domicilio`
  MODIFY `IdDomicilio` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `evento`
--
ALTER TABLE `evento`
  MODIFY `IdEvento` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `fotoalrededores`
--
ALTER TABLE `fotoalrededores`
  MODIFY `IdFotoAlrededores` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `gestioninventario`
--
ALTER TABLE `gestioninventario`
  MODIFY `IdGestionInventario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `inventario`
--
ALTER TABLE `inventario`
  MODIFY `IdInventario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT de la tabla `password`
--
ALTER TABLE `password`
  MODIFY `IdPassword` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de la tabla `problematica`
--
ALTER TABLE `problematica`
  MODIFY `IdProblematica` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `recoger`
--
ALTER TABLE `recoger`
  MODIFY `IdRecoger` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=46;

--
-- AUTO_INCREMENT de la tabla `scouter`
--
ALTER TABLE `scouter`
  MODIFY `IdScouter` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

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
  MODIFY `IdTipoProblematica` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `ubicacion`
--
ALTER TABLE `ubicacion`
  MODIFY `IdUbicacion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `IdUsuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT de la tabla `voluntariofrecuente`
--
ALTER TABLE `voluntariofrecuente`
  MODIFY `IdVoluntarioFrecuente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `adultomayor`
--
ALTER TABLE `adultomayor`
  ADD CONSTRAINT `adultomayor_ibfk_1` FOREIGN KEY (`FkDependencia`) REFERENCES `dependencia` (`IdDependencia`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `adultomayor_ibfk_2` FOREIGN KEY (`FkDomicilio`) REFERENCES `domicilio` (`IdDomicilio`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Filtros para la tabla `asignacion`
--
ALTER TABLE `asignacion`
  ADD CONSTRAINT `asignacion_ibfk_1` FOREIGN KEY (`FkAdultoMayor`) REFERENCES `adultomayor` (`IdAdultoMayor`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `asignacion_ibfk_2` FOREIGN KEY (`FkUsuario`) REFERENCES `usuario` (`IdUsuario`) ON DELETE SET NULL ON UPDATE CASCADE;

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
  ADD CONSTRAINT `domicilio_ibfk_1` FOREIGN KEY (`FkUbicacion`) REFERENCES `ubicacion` (`IdUbicacion`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Filtros para la tabla `evento`
--
ALTER TABLE `evento`
  ADD CONSTRAINT `evento_ibfk_1` FOREIGN KEY (`FkTipoEvento`) REFERENCES `tipoevento` (`IdTipoEvento`) ON DELETE SET NULL ON UPDATE CASCADE;

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
  ADD CONSTRAINT `problematica_ibfk_1` FOREIGN KEY (`FkTipoProblematica`) REFERENCES `tipoproblematica` (`IdTipoProblematica`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `problematica_ibfk_2` FOREIGN KEY (`FkUsuario`) REFERENCES `usuario` (`IdUsuario`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Filtros para la tabla `recoger`
--
ALTER TABLE `recoger`
  ADD CONSTRAINT `recoger_ibfk_2` FOREIGN KEY (`FkAsignacion`) REFERENCES `asignacion` (`IdAsignacion`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `recoger_ibfk_3` FOREIGN KEY (`FkScouter`) REFERENCES `scouter` (`IdScouter`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Filtros para la tabla `scouter`
--
ALTER TABLE `scouter`
  ADD CONSTRAINT `scouter_ibfk_1` FOREIGN KEY (`FkUsuario`) REFERENCES `usuario` (`IdUsuario`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `usuario_ibfk_1` FOREIGN KEY (`FkSeccion`) REFERENCES `seccion` (`IdSeccion`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Filtros para la tabla `voluntariofrecuente`
--
ALTER TABLE `voluntariofrecuente`
  ADD CONSTRAINT `voluntariofrecuente_ibfk_1` FOREIGN KEY (`FkUsuario`) REFERENCES `usuario` (`IdUsuario`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `voluntariofrecuente_ibfk_2` FOREIGN KEY (`FkAdultoMayor`) REFERENCES `adultomayor` (`IdAdultoMayor`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
