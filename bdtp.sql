-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : ven. 15 avr. 2022 à 16:46
-- Version du serveur :  8.0.21
-- Version de PHP : 7.3.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `bdtp`
--

-- --------------------------------------------------------

--
-- Structure de la table `categorie`
--

DROP TABLE IF EXISTS `categorie`;
CREATE TABLE IF NOT EXISTS `categorie` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `Type` varchar(50) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `categorie`
--

INSERT INTO `categorie` (`ID`, `Type`) VALUES
(1, 'Pain'),
(2, 'Viennoiseries'),
(3, 'Patisseries'),
(4, 'Bonbons');

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

DROP TABLE IF EXISTS `client`;
CREATE TABLE IF NOT EXISTS `client` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `Nom` varchar(50) NOT NULL,
  `Prenom` varchar(50) NOT NULL,
  `Adresse` varchar(255) NOT NULL,
  `City` varchar(50) NOT NULL,
  `Mail` varchar(50) NOT NULL,
  `Tel` int NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=891 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `client`
--

INSERT INTO `client` (`ID`, `Nom`, `Prenom`, `Adresse`, `City`, `Mail`, `Tel`) VALUES
(1, 'lulu', 'lucie', '7 rue de la patisserie', 'paris', 'lulu@gmail.com', 789865432),
(7, 'L', 'L', 'L', 'L', 'L', 7),
(72, 'lulu', 'lulu', 'lulu', 'lulu', 'lulu', 7),
(87, 'l', 'l', 'l', 'l', 'l', 7),
(777, 'Mystere', 'Mysterieux', 'Secrete', '77077', 'james@bond.com', 777777777);

-- --------------------------------------------------------

--
-- Structure de la table `commande`
--

DROP TABLE IF EXISTS `commande`;
CREATE TABLE IF NOT EXISTS `commande` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `ListeProd` text NOT NULL,
  `Total` float NOT NULL,
  `Client` varchar(700) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `ID_CLIENT` int NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `COMMANDE_CLIENT_FK` (`ID_CLIENT`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `commande`
--

INSERT INTO `commande` (`ID`, `ListeProd`, `Total`, `Client`, `ID_CLIENT`) VALUES
(1, 'Baguette', 1.15, 'Bien blanche donc pas trop cuite XD', 1);

-- --------------------------------------------------------

--
-- Structure de la table `produit`
--

DROP TABLE IF EXISTS `produit`;
CREATE TABLE IF NOT EXISTS `produit` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `Nom` varchar(50) NOT NULL,
  `Description` varchar(50) NOT NULL,
  `Prix` float NOT NULL,
  `ID_CATEGORIE` int NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `PRODUIT_CATEGORIE_FK` (`ID_CATEGORIE`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `produit`
--

INSERT INTO `produit` (`ID`, `Nom`, `Description`, `Prix`, `ID_CATEGORIE`) VALUES
(2, 'Baguette', 'Made in france', 1.15, 1),
(3, 'Choux Vanille Bourbon', 'Miam', 1, 3),
(4, 'Poudre de perlimpinpin', 'Un gout particulier XD', 0.7, 4);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `commande`
--
ALTER TABLE `commande`
  ADD CONSTRAINT `COMMANDE_CLIENT_FK` FOREIGN KEY (`ID_CLIENT`) REFERENCES `client` (`ID`);

--
-- Contraintes pour la table `produit`
--
ALTER TABLE `produit`
  ADD CONSTRAINT `PRODUIT_CATEGORIE_FK` FOREIGN KEY (`ID_CATEGORIE`) REFERENCES `categorie` (`ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
