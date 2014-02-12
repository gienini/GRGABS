-- phpMyAdmin SQL Dump
-- version 4.0.9
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 12-02-2014 a las 14:08:38
-- Versión del servidor: 5.6.14
-- Versión de PHP: 5.5.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `bbdd`
--
CREATE DATABASE IF NOT EXISTS `bbdd` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `bbdd`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `activitats`
--

CREATE TABLE IF NOT EXISTS `activitats` (
  `ID_ACTIVITAT` int(11) NOT NULL AUTO_INCREMENT,
  `NOM` text NOT NULL,
  `DESCRIPCIO` text NOT NULL,
  `DIA` date NOT NULL,
  `HORA` time NOT NULL,
  `ESPAI` text NOT NULL,
  `SENIOR` tinyint(1) NOT NULL,
  PRIMARY KEY (`ID_ACTIVITAT`),
  UNIQUE KEY `ID_ACTIVITAT` (`ID_ACTIVITAT`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Volcado de datos para la tabla `activitats`
--

INSERT INTO `activitats` (`ID_ACTIVITAT`, `NOM`, `DESCRIPCIO`, `DIA`, `HORA`, `ESPAI`, `SENIOR`) VALUES
(1, 'Partida de golf', 'Partida de golf al camp numero 5', '2014-02-12', '08:00:00', 'Camp numero 5', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `socis`
--

CREATE TABLE IF NOT EXISTS `socis` (
  `DNI` varchar(9) NOT NULL,
  `NOM` text NOT NULL,
  `COGNOM1` text NOT NULL,
  `COGNOM2` text NOT NULL,
  `NICKNAME` varchar(20) NOT NULL,
  `PASW` varchar(20) NOT NULL,
  `ADRECA` varchar(25) NOT NULL,
  `DATA_NAIX` date NOT NULL,
  `DATA_ALTA` date NOT NULL,
  `FOTO` varchar(50) NOT NULL,
  UNIQUE KEY `DNI` (`DNI`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `socis`
--

INSERT INTO `socis` (`DNI`, `NOM`, `COGNOM1`, `COGNOM2`, `NICKNAME`, `PASW`, `ADRECA`, `DATA_NAIX`, `DATA_ALTA`, `FOTO`) VALUES
('47849992X', 'Jordi', 'Subirà', 'Lara', 'subi', '1234', 'C/Maria Trulls 5', '1992-08-08', '2014-02-12', '');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `socis_activitats`
--

CREATE TABLE IF NOT EXISTS `socis_activitats` (
  `DNI` varchar(9) NOT NULL,
  `ID_ACTIVITAT` int(11) NOT NULL,
  PRIMARY KEY (`DNI`),
  KEY `ID_ACTIVITAT` (`ID_ACTIVITAT`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `socis_activitats`
--

INSERT INTO `socis_activitats` (`DNI`, `ID_ACTIVITAT`) VALUES
('47849992X', 1);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `socis_activitats`
--
ALTER TABLE `socis_activitats`
  ADD CONSTRAINT `socis_activitats_ibfk_2` FOREIGN KEY (`ID_ACTIVITAT`) REFERENCES `activitats` (`ID_ACTIVITAT`),
  ADD CONSTRAINT `socis_activitats_ibfk_1` FOREIGN KEY (`DNI`) REFERENCES `socis` (`DNI`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
