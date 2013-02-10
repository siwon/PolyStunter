-- phpMyAdmin SQL Dump
-- version 3.5.1
-- http://www.phpmyadmin.net
--
-- Client: localhost
-- Généré le: Dim 27 Janvier 2013 à 16:22
-- Version du serveur: 5.5.24-log
-- Version de PHP: 5.4.3

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

CREATE DATABASE IF NOT EXISTS polystunter DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

USE polystunter;

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données: `polystunter`
--

-- --------------------------------------------------------

--
-- Structure de la table `notification`
--

CREATE TABLE IF NOT EXISTS `notification` (
  `idNotification` int(11) NOT NULL AUTO_INCREMENT,
  `idSender` int(11) NOT NULL DEFAULT '0',
  `typeNotification` enum('BUILDING_SITE','ACCIDENT','DEMONSTRATION','OTHER') DEFAULT NULL,
  `informationNotification` text,
  `latitudeNotification` double DEFAULT NULL,
  `longitudeNotification` double DEFAULT NULL,
  `dateNotification` timestamp,
  PRIMARY KEY (`idNotification`,`idSender`),
  KEY `idSender` (`idSender`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

-- --------------------------------------------------------

--
-- Structure de la table `orderedproducts`
--

CREATE TABLE IF NOT EXISTS `orderedproducts` (
  `idOrder` int(11) NOT NULL DEFAULT '0',
  `idProduct` int(11) NOT NULL DEFAULT '0',
  `quantityOrdered` int(11) DEFAULT NULL,
  PRIMARY KEY (`idOrder`,`idProduct`),
  KEY `idProduct` (`idProduct`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `orders`
--

CREATE TABLE IF NOT EXISTS `orders` (
  `idOrder` int(11) NOT NULL AUTO_INCREMENT,
  `idCustomer` int(11) NOT NULL DEFAULT '0',
  `forwardingAddressOrder` text,
  `deliveryDateOrder` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `costOrder` double DEFAULT NULL,
  `statusOrder` enum('READY','DELIVERED') DEFAULT NULL,
  PRIMARY KEY (`idOrder`,`idCustomer`),
  KEY `idCustomer` (`idCustomer`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `product`
--

CREATE TABLE IF NOT EXISTS `product` (
  `idProduct` int(11) NOT NULL AUTO_INCREMENT,
  `idSeller` int(11) NOT NULL DEFAULT '0',
  `priceProduct` double DEFAULT NULL,
  `nameProduct` varchar(32) DEFAULT NULL,
  `referenceProduct` varchar(32) DEFAULT NULL,
  `quantityProduct` int(11) DEFAULT NULL,
  `informationProduct` text,
  `idWarehouse` int(11) DEFAULT NULL,
  `photoProduct` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`idProduct`,`idSeller`),
  KEY `idSeller` (`idSeller`),
  KEY `idWarehouse` (`idWarehouse`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

-- --------------------------------------------------------

--
-- Structure de la table `productsinbasket`
--

CREATE TABLE IF NOT EXISTS `productsinbasket` (
  `idBasket` int(11) NOT NULL DEFAULT '0',
  `idProduct` int(11) NOT NULL DEFAULT '0',
  `quantityToOrder` int(11) DEFAULT NULL,
  PRIMARY KEY (`idBasket`,`idProduct`),
  KEY `idProduct` (`idProduct`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `idUser` int(11) NOT NULL AUTO_INCREMENT,
  `loginUser` varchar(32) DEFAULT NULL,
  `passwordUser` varchar(64) DEFAULT NULL,
  `mailUser` varchar(150) DEFAULT NULL,
  `statusUser` enum('CUSTOMER','SELLER','DELIVERYMAN','ADMIN') DEFAULT NULL,
  PRIMARY KEY (`idUser`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=5 ;

--
-- Contenu de la table `user`
--

INSERT INTO `user` (`idUser`, `loginUser`, `passwordUser`, `mailUser`, `statusUser`) VALUES
(1, 'client', '948fe603f61dc036b5c596dc09fe3ce3f3d30dc90f024c85f3c82db2ccab679d', 'client', 'CUSTOMER'),
(2, 'livreur', '21bef81c50399dd680bcc9961659fa67ff8c2a6cd551baa22f5fe5d431c2f5fd', 'livreur', 'DELIVERYMAN'),
(3, 'vendeur', '3fe6dd5dd172cef095720595fe45bac03bdfd9844d68a5b7dfc20320437aba92', 'vendeur', 'SELLER'),
(4, 'admin', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', 'admin', 'ADMIN');

-- --------------------------------------------------------

--
-- Structure de la table `warehouse`
--

CREATE TABLE IF NOT EXISTS `warehouse` (
  `idWarehouse` int(11) NOT NULL AUTO_INCREMENT,
  `idSeller` int(11) DEFAULT NULL,
  `nameWarehouse` varchar(32) DEFAULT NULL,
  `streetWarehouse` varchar(64) DEFAULT NULL,
  `zipCodeWarehouse` int(11) DEFAULT NULL,
  `cityWarehouse` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`idWarehouse`),
  KEY `idSeller` (`idSeller`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=11 ;


--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `notification`
--
ALTER TABLE `notification`
  ADD CONSTRAINT `notification_ibfk_1` FOREIGN KEY (`idSender`) REFERENCES `user` (`idUser`) ON DELETE CASCADE;

--
-- Contraintes pour la table `orderedproducts`
--
ALTER TABLE `orderedproducts`
  ADD CONSTRAINT `orderedproducts_ibfk_1` FOREIGN KEY (`idOrder`) REFERENCES `orders` (`idOrder`) ON DELETE CASCADE,
  ADD CONSTRAINT `orderedproducts_ibfk_2` FOREIGN KEY (`idProduct`) REFERENCES `product` (`idProduct`);

--
-- Contraintes pour la table `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`idCustomer`) REFERENCES `user` (`idUser`);

--
-- Contraintes pour la table `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `product_ibfk_1` FOREIGN KEY (`idSeller`) REFERENCES `user` (`idUser`) ON DELETE CASCADE,
  ADD CONSTRAINT `product_ibfk_2` FOREIGN KEY (`idWarehouse`) REFERENCES `warehouse` (`idWarehouse`) ON DELETE CASCADE;

--
-- Contraintes pour la table `productsinbasket`
--
ALTER TABLE `productsinbasket`
  ADD CONSTRAINT `productsinbasket_ibfk_1` FOREIGN KEY (`idProduct`) REFERENCES `product` (`idProduct`) ON DELETE CASCADE,
  ADD CONSTRAINT `productsinbasket_ibfk_2` FOREIGN KEY (`idBasket`) REFERENCES `user` (`idUser`) ON DELETE CASCADE;

--
-- Contraintes pour la table `warehouse`
--
ALTER TABLE `warehouse`
  ADD CONSTRAINT `warehouse_ibfk_1` FOREIGN KEY (`idSeller`) REFERENCES `user` (`idUser`) ON DELETE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
