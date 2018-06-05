-- phpMyAdmin SQL Dump
-- version 4.8.0.1
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 31-05-2018 a las 20:04:52
-- Versión del servidor: 10.0.34-MariaDB-0ubuntu0.16.04.1
-- Versión de PHP: 7.0.30-0ubuntu0.16.04.1

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

DELIMITER $$
--
-- Procedimientos
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `ActualizacionComentarios` ()  BEGIN 
DECLARE vIdMax Integer;
DECLARE vCont Integer DEFAULT 1;
DECLARE vFecha Date;
DECLARE vAntiguedad int; 
SET vIdMax = (select Idcomentarioam from comentarioam order by idcomentarioam desc limit 1);
WHILE vCont <= vIdMax DO SET vFecha = (SELECT Fecha From comentarioam where idcomentarioam = vCont);
	SET vAntiguedad = (SELECT TIMESTAMPDIFF(YEAR,vFecha,CURDATE()));
	IF vAntiguedad >= 1 THEN 
		DELETE FROM comentarioam WHERE idcomentarioam = vCont; 
	END IF; 
	SET vCont = vCont + 1; 
END WHILE; 
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `ActualizacionScouter` ()  BEGIN 
DECLARE vIdMax Integer;
DECLARE vCont Integer DEFAULT 1; 
DECLARE vFecha Date;
DECLARE vFechaActual Date;
SET vFechaActual = CURDATE();
SET vIdMax = (select Idscouter from scouter order by idscouter desc limit 1);
WHILE vCont <= vIdMax DO 
	SET vFecha = (SELECT FechaFinal From scouter where idscouter = vCont);
	IF vFecha <= vFechaActual THEN 
		DELETE FROM scouter WHERE IdScouter = vCont; 
	END IF;
	SET vCont = vCont + 1;
END WHILE; 
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `ActualizacionSeccion` ()  BEGIN
declare vEdad INTEGER; 
declare vFecha DATE;
declare vUsuario INTEGER;
declare vFechaNacimiento DATE;
declare vSeccion INTEGER;
DECLARE vScout INTEGER;
declare i INTEGER DEFAULT 1;
SET vFecha = CURDATE();
SET vUsuario = (select IdUsuario from usuario order by IdUsuario desc limit 1);
WHILE i <= vUsuario DO

	SET vFechaNacimiento = (SELECT FechaNacimiento FROM usuario where IdUsuario = i);
	SET vEdad = (SELECT TIMESTAMPDIFF(YEAR,vFechaNacimiento,CURDATE()));
    
    SET vScout = (SELECT Scout FROM usuario where IdUsuario = i);
    
    IF vScout = 0 THEN 
		SET vSeccion = (SELECT idseccion from seccion where Nombre = 'civil'); 
	ELSEIF vEdad < 11 THEN
		SET vSeccion = (SELECT idseccion from seccion where Nombre = 'manada');
	ELSEIF vEdad < 15 THEN
		SET  vSeccion = (SELECT idseccion from seccion where Nombre = 'tropa');
	ELSEIF vEdad < 18 THEN 
		SET vSeccion = (SELECT idseccion from seccion where Nombre = 'comunidad'); 
	ElSEIF vEdad < 22 THEN 
		SET vSeccion = (SELECT idseccion from seccion where Nombre = 'clan');
	ELSE 
		SET vSeccion = (SELECT idseccion from seccion where Nombre = 'dirigente');
	END IF;
    UPDATE usuario SET FKSeccion = vSeccion WHERE IdUsuario = i;
	SET i = i + 1;
END WHILE;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `ResetInventario` ()  BEGIN
DECLARE vnInventario INTEGER; 
DECLARE vProducto VARCHAR(50);
DECLARE vCantidad FLOAT;
DECLARE vExistencia INTEGER;
DECLARE vAM INTEGER; 
DECLARE vAMD INTEGER;
DECLARE i INTEGER;

SET i = 1;
SET vnInventario = (SELECT COUNT(*) FROM inventario where Extra = 0);
SET vAM = (SELECT COUNT(*) FROM adultomayor);
SET vAMD = (SELECT COUNT(*) FROM adultomayor where Diabetico = 1);

while i <= vnInventario DO
	SET vCantidad = (SELECT Cantidad from Inventario where IdInventario = i);
	SET vProducto = (SELECT Producto FROM Inventario WHERE IdInventario = i);
	SET vExistencia = (SELECT Existencia FROM Inventario WHERE IdInventario = i);

	IF vCantidad > 1 THEN
		SET vExistencia = (vExistencia - (vCantidad * vAM));
	ELSEIF vProducto = 'Splenda' THEN
		SET vExistencia = (vExistencia - vAMD);
	ELSE
		SET vExistencia = (vExistencia - vAM);
	END IF;
	IF vExistencia < 0 THEN
		SET vExistencia = 0;
	END IF;
	UPDATE inventario SET Existencia = vExistencia WHERE IdInventario = i;
	SET i = i+1;
END while;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `VerificacionIntentos` ()  BEGIN
declare vPass INTEGER;
declare vIntentos INTEGER;
declare i INTEGER DEFAULT 1;

SET vPass = (select IdPassword from password order by IdPassword desc limit 1);

WHILE i <= vPass DO
	SET vIntentos = (SELECT Intentos FROM password WHERE IdPassword = i); 
    IF vIntentos > 0 THEN
    	UPDATE password SET Intentos =  3  WHERE IdPassword = i;
    END IF;
	SET i = i + 1;
END WHILE;




END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `VerificarLogin` ()  BEGIN
DECLARE vIdMax Integer;
DECLARE vCont Integer DEFAULT 1; 
DECLARE vFecha Date;
DECLARE vMeses Integer;
DECLARE vUsuario Integer;
SET vIdMax = (select IdPassword from password order by idpassword desc limit 1);
WHILE vCont <= vIdMax DO 
	SET vFecha = (SELECT FechaLogin From password where idpassword = vCont);    
    SET vMeses = (SELECT TIMESTAMPDIFF(MONTH,vFecha,CURDATE()));
	IF vMeses >= 6 THEN 
    	SET vUsuario = (SELECT FkUsuario FROM password WHERE Idpassword = vCont);
		DELETE FROM password WHERE IdPassword = vCont; 
        DELETE FROM usuario WHERE IdUsuario = vUsuario;
	END IF;
	SET vCont = vCont + 1;
END WHILE; 







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
  `ApellidoMaterno` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `Fotografia` varchar(100) CHARACTER SET latin1 NOT NULL,
  `Diabetico` tinyint(1) NOT NULL,
  `FkDependencia` int(11) DEFAULT NULL,
  `FkDomicilio` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

--
-- Volcado de datos para la tabla `adultomayor`
--

INSERT INTO `adultomayor` (`IdAdultoMayor`, `Nombre`, `ApellidoPaterno`, `ApellidoMaterno`, `Fotografia`, `Diabetico`, `FkDependencia`, `FkDomicilio`) VALUES
(11, 'Pascualito', 'Sevilla', 'Mártinez', 'img/adultomayor/x.jpg', 0, 2, 11),
(12, 'Sebastiana', 'Corona', 'Gómez', 'img/adultomayor/x.jpg', 0, 2, 12),
(13, 'Jovita', 'Benito', 'Rosales', 'img/adultomayor/x.jpg', 0, 2, 13),
(14, 'María del Carmen', 'Valerio', 'Martinez', 'img/adultomayor/x.jpg', 1, 2, 14),
(15, 'Juana', 'Saucedo', 'Fausto', 'img/adultomayor/x.jpg', 0, 2, 15),
(16, 'Emilia', 'Virgen', 'López', 'img/adultomayor/x.jpg', 1, 2, 16),
(17, 'Vicente', 'Prudencio', NULL, 'img/adultomayor/x.jpg', 1, 2, 17),
(18, 'Regina', 'Reynaga', 'Zuñiga', 'img/adultomayor/x.jpg', 1, 2, 18),
(19, 'Natalia', 'Candelario', 'Virgen', 'img/adultomayor/x.jpg', 1, 1, 19),
(20, 'Rosario', 'Daniel', 'Contreras', 'img/adultomayor/x.jpg', 1, 2, 20);

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

--
-- Volcado de datos para la tabla `coordinador`
--

INSERT INTO `coordinador` (`IdCoordinador`, `FkScouter`) VALUES
(1, 1);

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
(11, 23, 'Gómez Farias', 'El Jagüey', 'img/domicilio/x.jpg', 21),
(12, 245, 'Gómez Farias', 'El Jagüey', 'img/domicilio/x.jpg', 22),
(13, 222, 'Ramón Corona', 'Ocotitos', 'img/domicilio/x.jpg', 23),
(14, 288, 'Av. Brizuela', 'Atemajac', 'img/domicilio/x.jpg', 24),
(15, 168, 'Reforma', 'Atemajac', 'img/domicilio/x.jpg', 25),
(16, 174, 'Morelos', 'Santiaguito', 'img/domicilio/x.jpg', 26),
(17, 61, 'Galeana', 'Santiaguito', 'img/domicilio/x.jpg', 27),
(18, 30, 'Ogazón', 'Santiaguito', 'img/domicilio/x.jpg', 28),
(19, 218, 'Ogazón', 'Santiaguito', 'img/domicilio/x.jpg', 29),
(20, 13, 'Porfirio Diaz', 'Lirios', 'img/domicilio/x.jpg', 30);

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
(1, '2018-03-16', '12:00:00', 'Atemajac de Brizuela', '', 1),
(2, '2018-04-15', '08:00:00', '', '', NULL),
(3, '2018-04-15', '08:00:00', 'Parque Alcalde', 'Llevar todas las ganas de ayudar y servir a los demás', 1);

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

--
-- Volcado de datos para la tabla `gestioninventario`
--

INSERT INTO `gestioninventario` (`IdGestionInventario`, `Fecha`, `FkScouter`, `FkInventario`) VALUES
(1, '2018-10-02', 1, 4),
(2, '2018-10-07', 2, 3),
(3, '2018-04-01', 2, 2),
(4, '2018-04-05', 2, 1),
(5, '2018-04-02', 1, 3);

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

--
-- Volcado de datos para la tabla `inventario`
--

INSERT INTO `inventario` (`IdInventario`, `Producto`, `Cantidad`, `Existencia`, `Descripcion`, `Imagen`, `Comentario`, `Extra`) VALUES
(1, 'Azucar', 1, 5, 'Kilogramo de Azuzar', 'img/inventario/1.jpg', '', 0),
(2, 'Maseca', 1, 20, 'Kilogramo de Maceca', 'img/inventario/2.jpg', '', 0),
(3, 'Lentejas', 0.5, 20, 'Kilogramo de Lenteja', 'img/inventario/3.jpg', '', 0),
(4, 'Atun', 2, 20, 'Latas', 'img/inventario/4.jpg', '', 0),
(5, 'Manteca', 1, 20, 'Paquete de Kilogramo de Maseca', 'img/inventario/5.jpg', 'Fueron donandas por Asc', 1),
(6, 'Galletas', 1, 20, 'Paquete de Galletas', 'img/inventario/6.jpg', '', 0),
(7, 'Canela', 1, 20, 'Bara ', 'img/inventario/7.jpg', '', 0),
(8, 'Jabón de Tocador', 1, 20, 'Jabón de Tocador', 'img/inventario/8.jpg', '', 0),
(9, 'Jabón Zote', 1, 20, 'Jabón Zote o similar', 'img/inventario/9.jpg', '', 0),
(10, 'Gelatina', 1, 20, 'Sobre o Caja', 'img/inventario/10.jpg', '', 0),
(11, 'Sal', 1, 20, 'Bolsita de Sal', 'img/inventario/11.jpg', '', 0),
(12, 'Cerrillos', 1, 20, 'Caja de Cerrillos', 'img/inventario/12.jpg', '', 0),
(13, 'Frijol', 1, 20, 'Kilogramo de Frijol', 'img/inventario/13.jpg', '', 0),
(14, 'Aceite', 0.5, 20, 'Litro de Aceite', 'img/inventario/14.jpg', '', 0),
(15, 'Trigo Inflado', 0.25, 20, 'Kilogramo de Trigo Inflado', 'img/inventario/15.jpg', '', 0),
(16, 'Tablilla de Chocolate', 1, 20, 'Tablilla de chocolate', 'img/inventario/16.jpg', 'Puede ser de cualquier marca', 0),
(17, 'Pasta', 2, 20, 'Paquetes de pasta', 'img/inventario/17.jpg', '', 0),
(18, 'Arroz', 1, 20, 'Kilogramo de Arroz', 'img/inventario/18.jpg', '', 0),
(19, 'Splenda', 1, 20, 'Paquetes de 20 sobres de Splenda', 'img/inventario/19.jpg', '', 0);

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

--
-- Volcado de datos para la tabla `password`
--

INSERT INTO `password` (`IdPassword`, `Password`, `Intentos`, `FechaLogin`, `Status`, `FkUsuario`) VALUES
(1, '0acc00bf8abac7533d0e07b01b8079fb6ec4b4c5', 1, '2018-05-30', 1, 1),
(2, 'c6f7002cafe494df5c72fe647bc5767b563bfc3d', 3, '2018-05-29', 0, 5),
(3, '0acc00bf8abac7533d0e07b01b8079fb6ec4b4c5', 3, '2018-05-31', 1, 3),
(10, 'e1794a744abf405a0d1c8bee81a909de329ff7f4', 3, '2018-05-29', 0, 6),
(43, '0acc00bf8abac7533d0e07b01b8079fb6ec4b4c5', 2, '2018-05-29', 1, 56);

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

--
-- Volcado de datos para la tabla `problematica`
--

INSERT INTO `problematica` (`IdProblematica`, `Fecha`, `Nombre`, `Sugerencia`, `FkUsuario`, `FkTipoProblematica`) VALUES
(1, '2018-03-12', 'No Funciona el boton color rojo de la primera interfaz', 'Cámbienlo a color azul', 5, 1),
(2, '2018-01-19', 'El Viejito no vive en esa casa', 'Pongan el domicilio: Porfirio diaz 24  ', 9, 2),
(4, '2018-04-26', 'jojojojoj', 'ojojojojoj', NULL, 1);

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

--
-- Volcado de datos para la tabla `tipoproblematica`
--

INSERT INTO `tipoproblematica` (`IdTipoProblematica`, `Nombre`) VALUES
(1, 'Fallo Aplicacion'),
(2, 'Vatos Incorrectos');

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
(21, -103.731535, 20.13793),
(22, -103.731709, 20.137833),
(23, -103.727815, 20.137428),
(24, -103.727261, 20.135919),
(25, -103.729534, 20.140039),
(26, -103.726993, 20.143354),
(27, -103.725605, 20.141702),
(28, -103.723677, 20.141654),
(29, -103.723706, 20.141568),
(30, -103.730462, 20.140903);

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
(1, 'José Miguel', 'Pérez', 'González', 'moringos7@gmail.com', 'img/img1.jpg', '1998-10-25', 1, 4),
(3, 'Daniel', 'Castellanos', 'Miranda', 'fdanycast@gmail.com', 'img/img2.jpg', '2005-10-11', 1, 2),
(5, 'Ma Ricardo ', 'Del Rio', 'Grageda', 'langur@gmail.com', 'img/img3.jpg', '2002-11-17', 1, 3),
(6, 'Marcela María', 'Pérez', 'González', 'mace@hotmail.com', 'img/img4.jpg', '1999-11-27', 1, 4),
(8, 'Pancho', 'Hernandez', 'Chavez', 'pancho@gmail.com', 'img/img5.jpg', '1980-07-29', 1, 5),
(9, 'Miguel Angel', 'Pérez', 'Murillo', 'tekton.formen@gmail.com', 'img/img6.jpg', '1968-09-30', 0, 6),
(34, 'Manuel', 'Pérez', 'González', 'manuel.perez.24.06.01@gmail.com', 'img/usuarios/x.jpg', '2001-06-24', 0, 6),
(56, 'Prueba', 'potter', 'pérez', 'moringosprueba@gmail.com', 'img/usuarios/x.jpg', '1998-05-29', 0, 6);

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
  MODIFY `IdAdultoMayor` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

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
  MODIFY `IdDomicilio` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

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
  MODIFY `IdPassword` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=44;

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
  MODIFY `IdScouter` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

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
  MODIFY `IdUbicacion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `IdUsuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=57;

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
  ADD CONSTRAINT `asignacion_ibfk_1` FOREIGN KEY (`FkAdultoMayor`) REFERENCES `adultomayor` (`IdAdultoMayor`) ON DELETE SET NULL ON UPDATE CASCADE,
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

DELIMITER $$
--
-- Eventos
--
CREATE DEFINER=`root`@`localhost` EVENT `EliminacionUsuarios` ON SCHEDULE EVERY 1 MINUTE STARTS '2018-04-27 19:20:00' ON COMPLETION PRESERVE ENABLE DO BEGIN
CALL VerificarLogin();
END$$

CREATE DEFINER=`root`@`localhost` EVENT `CambioSeccion` ON SCHEDULE EVERY 1 DAY STARTS '2017-11-30 00:00:00' ON COMPLETION PRESERVE ENABLE DO BEGIN 
CALL ActualizacionSeccion(); 
END$$

CREATE DEFINER=`root`@`localhost` EVENT `ActualizacionIntentosPassword` ON SCHEDULE EVERY 1 MONTH STARTS '2018-04-26 10:15:00' ON COMPLETION PRESERVE ENABLE DO BEGIN
CALL VerificacionIntentos();
END$$

CREATE DEFINER=`root`@`localhost` EVENT `ReinicioInventario` ON SCHEDULE EVERY 1 MONTH STARTS '2017-11-30 00:00:00' ON COMPLETION PRESERVE ENABLE DO BEGIN 
CALL ResetInventario(); 
END$$

CREATE DEFINER=`root`@`localhost` EVENT `EliminacionComentarios` ON SCHEDULE EVERY 1 YEAR STARTS '2017-11-30 00:00:00' ON COMPLETION PRESERVE ENABLE DO BEGIN 
CALL ActualizacionComentarios(); 
END$$

CREATE DEFINER=`root`@`localhost` EVENT `VerificacionScouter` ON SCHEDULE EVERY 1 DAY STARTS '2017-11-30 00:00:00' ON COMPLETION PRESERVE ENABLE DO BEGIN 
CALL ActualizacionScouter(); 
END$$

DELIMITER ;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
