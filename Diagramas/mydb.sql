-- phpMyAdmin SQL Dump
-- version 4.3.8
-- http://www.phpmyadmin.net
--
-- Servidor: localhost:3306
-- Tiempo de generación: 07-05-2015 a las 04:49:19
-- Versión del servidor: 5.5.40
-- Versión de PHP: 5.4.37

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `mydb`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `calle`
--

CREATE TABLE IF NOT EXISTS `calle` (
  `idCalle` int(11) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `idMunicipio` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `centro`
--

CREATE TABLE IF NOT EXISTS `centro` (
  `idCentro` int(11) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `modelo` varchar(1) NOT NULL,
  `idProv` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `direcccion`
--

CREATE TABLE IF NOT EXISTS `direcccion` (
  `idCalle` int(11) NOT NULL,
  `idVivienda` int(11) NOT NULL,
  `cp` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `inscripcion`
--

CREATE TABLE IF NOT EXISTS `inscripcion` (
  `idIns` int(11) NOT NULL,
  `nSolicitud` int(11) NOT NULL,
  `dni` varchar(9) NOT NULL,
  `codMenor` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `menor`
--

CREATE TABLE IF NOT EXISTS `menor` (
  `codMenor` int(11) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `ape1` varchar(45) NOT NULL,
  `ape2` varchar(45) NOT NULL,
  `sexo` varchar(45) NOT NULL,
  `dni` varchar(9) DEFAULT NULL,
  `fechaNac` date NOT NULL,
  `discapacidad` varchar(45) NOT NULL,
  `idCalle` int(11) NOT NULL,
  `idVivienda` int(11) NOT NULL,
  `idCentro` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `municipio`
--

CREATE TABLE IF NOT EXISTS `municipio` (
  `idMunicipio` int(11) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `idProv` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `provincia`
--

CREATE TABLE IF NOT EXISTS `provincia` (
  `idProv` int(11) NOT NULL,
  `nombre` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `solicitud`
--

CREATE TABLE IF NOT EXISTS `solicitud` (
  `nSolicitud` int(11) NOT NULL,
  `fecha` date DEFAULT NULL,
  `hora` date DEFAULT NULL,
  `idSorteo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sorteo`
--

CREATE TABLE IF NOT EXISTS `sorteo` (
  `idSorteo` int(11) NOT NULL,
  `diaIninio` date NOT NULL,
  `diaFin` date NOT NULL,
  `diaSorteo` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tutor`
--

CREATE TABLE IF NOT EXISTS `tutor` (
  `dni` varchar(9) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `ape1` varchar(45) NOT NULL,
  `ape2` varchar(45) NOT NULL,
  `tlf1` varchar(45) NOT NULL,
  `tlf2` varchar(45) DEFAULT NULL,
  `tlf3` varchar(45) DEFAULT NULL,
  `tlf4` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vivienda`
--

CREATE TABLE IF NOT EXISTS `vivienda` (
  `idVivienda` int(11) NOT NULL,
  `numero` varchar(4) NOT NULL,
  `piso` varchar(3) DEFAULT NULL,
  `letra` varchar(1) DEFAULT NULL,
  `mano` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `calle`
--
ALTER TABLE `calle`
  ADD PRIMARY KEY (`idCalle`), ADD KEY `fk_Calle_Municipio1` (`idMunicipio`);

--
-- Indices de la tabla `centro`
--
ALTER TABLE `centro`
  ADD PRIMARY KEY (`idCentro`), ADD KEY `fk_Centro_Provincia1` (`idProv`);

--
-- Indices de la tabla `direcccion`
--
ALTER TABLE `direcccion`
  ADD PRIMARY KEY (`idCalle`,`idVivienda`), ADD KEY `fk_DIR_VIV` (`idVivienda`);

--
-- Indices de la tabla `inscripcion`
--
ALTER TABLE `inscripcion`
  ADD PRIMARY KEY (`idIns`), ADD KEY `fk_Inscripcion_Solicitud1` (`nSolicitud`), ADD KEY `fk_Inscripcion_Tutor1` (`dni`), ADD KEY `fk_Inscripcion_Menor1` (`codMenor`);

--
-- Indices de la tabla `menor`
--
ALTER TABLE `menor`
  ADD PRIMARY KEY (`codMenor`), ADD KEY `fk_Menor_Direcccion1` (`idCalle`,`idVivienda`), ADD KEY `fk_Menor_Centro1` (`idCentro`);

--
-- Indices de la tabla `municipio`
--
ALTER TABLE `municipio`
  ADD PRIMARY KEY (`idMunicipio`), ADD KEY `fk_Municipio_Provincia` (`idProv`);

--
-- Indices de la tabla `provincia`
--
ALTER TABLE `provincia`
  ADD PRIMARY KEY (`idProv`);

--
-- Indices de la tabla `solicitud`
--
ALTER TABLE `solicitud`
  ADD PRIMARY KEY (`nSolicitud`), ADD KEY `fk_Solicitud_Sorteo1` (`idSorteo`);

--
-- Indices de la tabla `sorteo`
--
ALTER TABLE `sorteo`
  ADD PRIMARY KEY (`idSorteo`);

--
-- Indices de la tabla `tutor`
--
ALTER TABLE `tutor`
  ADD PRIMARY KEY (`dni`);

--
-- Indices de la tabla `vivienda`
--
ALTER TABLE `vivienda`
  ADD PRIMARY KEY (`idVivienda`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `calle`
--
ALTER TABLE `calle`
ADD CONSTRAINT `fk_Calle_Municipio1` FOREIGN KEY (`idMunicipio`) REFERENCES `municipio` (`idMunicipio`);

--
-- Filtros para la tabla `centro`
--
ALTER TABLE `centro`
ADD CONSTRAINT `fk_Centro_Provincia1` FOREIGN KEY (`idProv`) REFERENCES `provincia` (`idProv`);

--
-- Filtros para la tabla `direcccion`
--
ALTER TABLE `direcccion`
ADD CONSTRAINT `fk_DIR_CALLE` FOREIGN KEY (`idCalle`) REFERENCES `calle` (`idCalle`),
ADD CONSTRAINT `fk_DIR_VIV` FOREIGN KEY (`idVivienda`) REFERENCES `vivienda` (`idVivienda`);

--
-- Filtros para la tabla `inscripcion`
--
ALTER TABLE `inscripcion`
ADD CONSTRAINT `fk_Inscripcion_Solicitud1` FOREIGN KEY (`nSolicitud`) REFERENCES `solicitud` (`nSolicitud`),
ADD CONSTRAINT `fk_Inscripcion_Tutor1` FOREIGN KEY (`dni`) REFERENCES `tutor` (`dni`),
ADD CONSTRAINT `fk_Inscripcion_Menor1` FOREIGN KEY (`codMenor`) REFERENCES `menor` (`codMenor`);

--
-- Filtros para la tabla `menor`
--
ALTER TABLE `menor`
ADD CONSTRAINT `fk_Menor_Direcccion1` FOREIGN KEY (`idCalle`, `idVivienda`) REFERENCES `direcccion` (`idCalle`, `idVivienda`),
ADD CONSTRAINT `fk_Menor_Centro1` FOREIGN KEY (`idCentro`) REFERENCES `centro` (`idCentro`);

--
-- Filtros para la tabla `municipio`
--
ALTER TABLE `municipio`
ADD CONSTRAINT `fk_Municipio_Provincia` FOREIGN KEY (`idProv`) REFERENCES `provincia` (`idProv`);

--
-- Filtros para la tabla `solicitud`
--
ALTER TABLE `solicitud`
ADD CONSTRAINT `fk_Solicitud_Sorteo1` FOREIGN KEY (`idSorteo`) REFERENCES `sorteo` (`idSorteo`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
