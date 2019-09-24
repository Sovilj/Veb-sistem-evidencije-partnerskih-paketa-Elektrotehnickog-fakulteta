-- phpMyAdmin SQL Dump
-- version 4.7.9
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Jun 18, 2018 at 01:05 PM
-- Server version: 5.7.21
-- PHP Version: 5.6.35

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `projekat`
--

-- --------------------------------------------------------

--
-- Table structure for table `donatorski_ugovor`
--

DROP TABLE IF EXISTS `donatorski_ugovor`;
CREATE TABLE IF NOT EXISTS `donatorski_ugovor` (
  `id_d_ugov` int(32) NOT NULL AUTO_INCREMENT,
  `id_komp` int(32) NOT NULL,
  `id_paket` int(32) NOT NULL,
  `procVrednost` int(32) NOT NULL,
  `opis` text COLLATE utf32_unicode_520_ci NOT NULL,
  `kolicina` int(32) NOT NULL,
  `datumUgovora` date NOT NULL,
  `status` varchar(32) COLLATE utf32_unicode_520_ci NOT NULL,
  `datumIsporuke` date NOT NULL,
  `datumIsticanja` date NOT NULL,
  PRIMARY KEY (`id_d_ugov`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf32 COLLATE=utf32_unicode_520_ci;

--
-- Dumping data for table `donatorski_ugovor`
--

INSERT INTO `donatorski_ugovor` (`id_d_ugov`, `id_komp`, `id_paket`, `procVrednost`, `opis`, `kolicina`, `datumUgovora`, `status`, `datumIsporuke`, `datumIsticanja`) VALUES
(1, 1, 1, 40000, 'Donirana oprema za fakultet', 15, '2018-04-18', 'potpisane obe', '2018-05-31', '2019-08-24'),
(2, 7, 1, 56000, 'Donirana oprema', 45, '2017-02-23', 'potpisane obe', '2017-12-13', '2018-01-17'),
(3, 2, 1, 70000, 'donirnaa oprema racunarska', 45, '2015-03-18', 'arhiviran', '2015-05-31', '2016-06-01'),
(7, 6, 1, 300000, 'oprema racunarska', 36, '2014-06-11', 'predat arhivi', '2015-09-08', '2016-06-10');

-- --------------------------------------------------------

--
-- Table structure for table `ima`
--

DROP TABLE IF EXISTS `ima`;
CREATE TABLE IF NOT EXISTS `ima` (
  `id_ima` int(11) NOT NULL AUTO_INCREMENT,
  `id_stavka` int(32) NOT NULL,
  `id_paket` int(32) NOT NULL,
  PRIMARY KEY (`id_ima`)
) ENGINE=MyISAM AUTO_INCREMENT=56 DEFAULT CHARSET=utf32 COLLATE=utf32_unicode_520_ci;

--
-- Dumping data for table `ima`
--

INSERT INTO `ima` (`id_ima`, `id_stavka`, `id_paket`) VALUES
(1, 1, 1),
(2, 2, 1),
(3, 3, 1),
(4, 4, 1),
(5, 2, 2),
(6, 3, 2),
(7, 4, 2),
(9, 3, 3),
(11, 3, 4),
(12, 3, 5);

-- --------------------------------------------------------

--
-- Table structure for table `kompanija`
--

DROP TABLE IF EXISTS `kompanija`;
CREATE TABLE IF NOT EXISTS `kompanija` (
  `id_komp` int(32) NOT NULL AUTO_INCREMENT,
  `naziv` varchar(32) COLLATE utf32_unicode_520_ci NOT NULL,
  `adresa` varchar(32) COLLATE utf32_unicode_520_ci NOT NULL,
  `grad` varchar(32) COLLATE utf32_unicode_520_ci NOT NULL,
  `postanskiBroj` varchar(32) COLLATE utf32_unicode_520_ci NOT NULL,
  `drzava` varchar(32) COLLATE utf32_unicode_520_ci NOT NULL,
  `racun` varchar(32) COLLATE utf32_unicode_520_ci NOT NULL,
  `valuta` varchar(32) COLLATE utf32_unicode_520_ci NOT NULL,
  `pib` varchar(32) COLLATE utf32_unicode_520_ci NOT NULL,
  `kontakt1` varchar(32) COLLATE utf32_unicode_520_ci NOT NULL,
  `kontakt2` varchar(32) COLLATE utf32_unicode_520_ci NOT NULL,
  `kontakt3` varchar(32) COLLATE utf32_unicode_520_ci NOT NULL,
  `kontakt4` varchar(32) COLLATE utf32_unicode_520_ci NOT NULL,
  `kontakt5` varchar(32) COLLATE utf32_unicode_520_ci NOT NULL,
  `kont_osoba` varchar(32) COLLATE utf32_unicode_520_ci NOT NULL,
  `kont_telef` varchar(32) COLLATE utf32_unicode_520_ci NOT NULL,
  `kont_email` varchar(32) COLLATE utf32_unicode_520_ci NOT NULL,
  `logo_link` varchar(32) COLLATE utf32_unicode_520_ci NOT NULL,
  `webLink` varchar(64) COLLATE utf32_unicode_520_ci NOT NULL,
  `opis` text COLLATE utf32_unicode_520_ci NOT NULL,
  PRIMARY KEY (`id_komp`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=utf32 COLLATE=utf32_unicode_520_ci;

--
-- Dumping data for table `kompanija`
--

INSERT INTO `kompanija` (`id_komp`, `naziv`, `adresa`, `grad`, `postanskiBroj`, `drzava`, `racun`, `valuta`, `pib`, `kontakt1`, `kontakt2`, `kontakt3`, `kontakt4`, `kontakt5`, `kont_osoba`, `kont_telef`, `kont_email`, `logo_link`, `webLink`, `opis`) VALUES
(1, 'Elektroprivreda Sribije', 'Balkanksa 13', 'Beograd', '11000', 'Srbija', '20053658 ', 'RSD', '103920327', '064567391', 'eps.bg@posta.rs', 'eps2.bg@posta.rs', '', '', 'Mladen Mihajlovic', '065934573', 'mladen.mihajlovic@gmail.com', '/resources/slike/EPS_baner.png', 'http://eps.rs/', 'Електропривреда Србије (ЕПС) је једно од највећих јавних предузећа у Републици Србији чији је превасходна делатност у области енергетике: производња, дистрибуција и снабдевање електричном енергијом, као и производња угља.'),
(2, 'Nordeus', 'Bulevar Mihajla Pupina 165v', 'Beograd', '11000', 'Srbija', '23289131', 'RSD', '3124213124', '063123498', 'nordeus@games.com', 'nordeus2@games.com', '068234874', '067834593', 'Darko Darkovic', '069345839', 'darko.darkovic@yahoo.com', '/resources/slike/Nordeus.png', 'https://nordeus.com/', 'Нордеус је независна компанија за развој видео игара и творац игре “Top Eleven”, једне од најпопуларнијих спортских онлајн игара, коју игра преко 140 милиона играча широм света. Компанија је основана 2010. године у Београду од стране три електроинжењера Електротехничког факултета у Београду, који су напустили своје корпоративне каријере у иностранству и вратили се у Србију са заједничком визијом игре коју желе да праве. '),
(5, 'Klain Robotics', 'Cacciamali, 67', 'Brescia', '213132', 'Italija', '46357458345', 'EUR', '323245733', '390303582154', '390303584523', 'klain.robo@robot.it', '05684324532', '', 'Djankarlo Buffon', '394534634', 'buffon@yahoo.com', '/resources/slike/Klain.jpg', 'http://www.klainrobotics.com/', 'Kompanija koja se bavi robotikom'),
(6, 'Microsoft', 'Spanskih boraca3/3', 'Beograd', '11070', 'Srbija', '935732-2423', 'EUR', '4324235', '381 11 330 6600', '381 11 330 6601', 'microsoft@internet.com', '', '', 'Bill Gates', '34568294', 'gates@rich.com', '/resources/slike/microsoft.png', 'https://www.microsoft.com/sr-latn-rs/mdcs', 'Microsoft Development Center Serbia - MDCS, отворен је 2005. год. као четврти по реду развојни центар компаније Мајкрософт у свету. Тим MDCS-а чини више од 200 перспективних младих људи, врхунских стручњака из области компјутерских наука и примењене математике.'),
(7, 'RT-RK', 'Narodnog Fronta 17', 'Novi Sad', '21000', 'Srbija', '32526234', 'RSD', '1426323', '381 21 4801 100', '381 21 4801 200', 'rtrk@car.rs', '', '', 'Djole Djokovic', '065879324', 'djole.djo@gmail.com', '/resources/slike/RTRK.png', 'http://www.rt-rk.com/', 'Компанија РТ-РК д.о.о. је основана 1991. године у Новом Саду и бави се пружањем услуга развоја софтвера и понудом сопствених софтверских решења у области система заснованих на рачунару, са фокусом на области мултимедије, комуникација и софтвера за аутомобилску индустрију.');

-- --------------------------------------------------------

--
-- Table structure for table `korisnik`
--

DROP TABLE IF EXISTS `korisnik`;
CREATE TABLE IF NOT EXISTS `korisnik` (
  `username` varchar(32) COLLATE utf32_unicode_520_ci NOT NULL,
  `password` text COLLATE utf32_unicode_520_ci NOT NULL,
  `ime` varchar(32) COLLATE utf32_unicode_520_ci NOT NULL,
  `prezime` varchar(32) COLLATE utf32_unicode_520_ci NOT NULL,
  `email` varchar(32) COLLATE utf32_unicode_520_ci NOT NULL,
  `institucija` varchar(32) COLLATE utf32_unicode_520_ci NOT NULL,
  `pol` varchar(32) COLLATE utf32_unicode_520_ci NOT NULL,
  `datumRodjenja` date NOT NULL,
  `tip` int(32) NOT NULL,
  `zahtev` int(32) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=MyISAM DEFAULT CHARSET=utf32 COLLATE=utf32_unicode_520_ci;

--
-- Dumping data for table `korisnik`
--

INSERT INTO `korisnik` (`username`, `password`, `ime`, `prezime`, `email`, `institucija`, `pol`, `datumRodjenja`, `tip`, `zahtev`) VALUES
('stefan', 'f1dc735ee3581693489eaf286088b916', 'Stefan', 'Sovilj', 'stefan.sovilj@gmail.com', 'Microsoft', 'muski', '1996-06-13', 1, 0),
('marko', 'f1dc735ee3581693489eaf286088b916', 'Marko', 'Markovic', 'marko@gmail.com', 'nordeus', 'muski', '1996-02-06', 2, 0),
('milos', 'f1dc735ee3581693489eaf286088b916', 'Milos', 'Milosevic', 'milos.milosevic@gmail.com', 'ABB', 'muski', '1997-09-11', 3, 0),
('milica', 'f1dc735ee3581693489eaf286088b916', 'Milica', 'Milic', 'milica.milic@gmail.com', 'Elektro Distribucija', 'zenski', '1999-12-11', 3, 0),
('sofija', 'f1dc735ee3581693489eaf286088b916', 'Sofija', 'Slavkovic', 'sofija.slavkovic@gmail.com', 'skola', 'zenski', '1996-10-18', 3, 1),
('nemanja', 'f1dc735ee3581693489eaf286088b916', 'Nemanja', 'Nemanjic', 'nemanja.nemanjic@gmail.com', 'kompanija', 'muski', '1994-01-12', 3, 1),
('drazen', 'f1dc735ee3581693489eaf286088b916', 'Drazen', 'Draskovic', 'drazen.draskovic@gmail.com', 'etf', 'muski', '1992-05-24', 3, 1);

-- --------------------------------------------------------

--
-- Table structure for table `novcani_ugovor`
--

DROP TABLE IF EXISTS `novcani_ugovor`;
CREATE TABLE IF NOT EXISTS `novcani_ugovor` (
  `id_n_ugov` int(32) NOT NULL AUTO_INCREMENT,
  `id_komp` int(32) NOT NULL,
  `id_paket` int(32) NOT NULL,
  `vrednost` int(32) NOT NULL,
  `datumUgovora` date NOT NULL,
  `status` varchar(32) COLLATE utf32_unicode_520_ci NOT NULL,
  `poslataFaktura` int(32) NOT NULL,
  `izvrsenaUplata` int(32) NOT NULL,
  `datumUplate` date NOT NULL,
  `datumIsticanja` date NOT NULL,
  PRIMARY KEY (`id_n_ugov`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf32 COLLATE=utf32_unicode_520_ci;

--
-- Dumping data for table `novcani_ugovor`
--

INSERT INTO `novcani_ugovor` (`id_n_ugov`, `id_komp`, `id_paket`, `vrednost`, `datumUgovora`, `status`, `poslataFaktura`, `izvrsenaUplata`, `datumUplate`, `datumIsticanja`) VALUES
(1, 2, 1, 300000, '2018-05-26', 'potpisane obe', 1, 1, '2018-05-24', '2019-04-25'),
(2, 5, 3, 75000, '2017-08-22', 'potpisane obe', 1, 1, '2017-12-13', '2018-08-24'),
(3, 6, 2, 95000, '2018-01-17', 'potpisane obe', 1, 1, '2018-02-08', '2019-02-15');

-- --------------------------------------------------------

--
-- Table structure for table `oglas`
--

DROP TABLE IF EXISTS `oglas`;
CREATE TABLE IF NOT EXISTS `oglas` (
  `id_oglas` int(32) NOT NULL AUTO_INCREMENT,
  `naslov` varchar(32) COLLATE utf32_unicode_520_ci NOT NULL,
  `opis` text COLLATE utf32_unicode_520_ci NOT NULL,
  `strucna_praksa` int(32) NOT NULL,
  `zaposlenje` int(32) NOT NULL,
  `datum` date NOT NULL,
  `vreme` time NOT NULL,
  `id_komp` int(32) NOT NULL,
  PRIMARY KEY (`id_oglas`)
) ENGINE=MyISAM AUTO_INCREMENT=12 DEFAULT CHARSET=utf32 COLLATE=utf32_unicode_520_ci;

--
-- Dumping data for table `oglas`
--

INSERT INTO `oglas` (`id_oglas`, `naslov`, `opis`, `strucna_praksa`, `zaposlenje`, `datum`, `vreme`, `id_komp`) VALUES
(7, 'Strucna Praksa', 'Praksa za sve studente koji zele da se bave programiranjem igrica', 1, 0, '2018-05-26', '16:01:36', 2),
(8, 'Zaposlenje', 'Studenti sa iskustvom trazeni zbog posla', 0, 1, '2018-05-26', '17:14:04', 2),
(9, 'Posao', 'posao za pravljenje sajtova', 0, 1, '2018-05-26', '17:27:48', 2),
(10, 'Letnja praksa', 'praksa za preko raspusta za studente', 1, 0, '2018-05-26', '18:07:04', 6),
(11, 'Autonomna vozila', 'Posao u izgradnji vozila i navigacionih sistema', 0, 1, '2018-05-30', '12:33:51', 5);

-- --------------------------------------------------------

--
-- Table structure for table `paket`
--

DROP TABLE IF EXISTS `paket`;
CREATE TABLE IF NOT EXISTS `paket` (
  `id_paket` int(32) NOT NULL AUTO_INCREMENT,
  `naziv` varchar(32) COLLATE utf32_unicode_520_ci NOT NULL,
  `vrednost` int(32) NOT NULL,
  `trajanjeUgovora` int(32) NOT NULL,
  `maxBroj` int(32) NOT NULL,
  PRIMARY KEY (`id_paket`)
) ENGINE=MyISAM AUTO_INCREMENT=19 DEFAULT CHARSET=utf32 COLLATE=utf32_unicode_520_ci;

--
-- Dumping data for table `paket`
--

INSERT INTO `paket` (`id_paket`, `naziv`, `vrednost`, `trajanjeUgovora`, `maxBroj`) VALUES
(1, 'Zlatni partner', 100000, 2, 10),
(2, 'Srebrni partner', 90000, 1, 10),
(3, 'Veliki partner', 70000, 1, 10),
(4, 'Partner', 50000, 1, 10),
(5, 'Oglasavanje', 20000, 1, 10);

-- --------------------------------------------------------

--
-- Table structure for table `predavanje`
--

DROP TABLE IF EXISTS `predavanje`;
CREATE TABLE IF NOT EXISTS `predavanje` (
  `id_pred` int(32) NOT NULL AUTO_INCREMENT,
  `naslov_rs` varchar(32) COLLATE utf32_unicode_520_ci NOT NULL,
  `naslov_en` varchar(32) COLLATE utf32_unicode_520_ci NOT NULL,
  `opis_rs` text COLLATE utf32_unicode_520_ci NOT NULL,
  `opis_en` text COLLATE utf32_unicode_520_ci NOT NULL,
  `datum` date NOT NULL,
  `vreme` time NOT NULL,
  `sala` varchar(32) COLLATE utf32_unicode_520_ci NOT NULL,
  `predavac` varchar(32) COLLATE utf32_unicode_520_ci NOT NULL,
  `biografija` text COLLATE utf32_unicode_520_ci NOT NULL,
  `id_komp` int(32) NOT NULL,
  `proslo` int(32) NOT NULL,
  PRIMARY KEY (`id_pred`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf32 COLLATE=utf32_unicode_520_ci;

--
-- Dumping data for table `predavanje`
--

INSERT INTO `predavanje` (`id_pred`, `naslov_rs`, `naslov_en`, `opis_rs`, `opis_en`, `datum`, `vreme`, `sala`, `predavac`, `biografija`, `id_komp`, `proslo`) VALUES
(1, 'Igrice', '', 'Predavanje o igricama', '', '2018-08-16', '08:00:00', '34', 'Marko Markovic', '', 2, 0),
(5, 'Baze podataka', '', 'HIbernate i SQL', '', '2018-08-17', '09:15:00', '56', 'Darko Darkic', 'dr.mr profesor na Beogradskom univerzitetu', 2, 0),
(4, 'Web sajtovi', '', 'Hibernate i zasto je beskoristan ovoliko', '', '2018-06-14', '09:00:00', '35', 'Nesko Neskovic', 'profesor na MIT', 2, 0),
(6, 'Predavanje o motorima', '', 'Predavanje o motorima i kako oni rade', '', '2017-02-15', '07:00:00', '56', 'Marko Markic', 'akademski gradjanin', 1, 0),
(7, 'Distribucija struje', '', 'Elektrane,struja,distribucija usiroko', '', '2018-12-13', '10:00:00', '65', 'Markus Korelijus', '', 1, 0);

-- --------------------------------------------------------

--
-- Table structure for table `status`
--

DROP TABLE IF EXISTS `status`;
CREATE TABLE IF NOT EXISTS `status` (
  `id_status` int(11) NOT NULL AUTO_INCREMENT,
  `naziv` varchar(32) COLLATE utf32_unicode_520_ci NOT NULL,
  PRIMARY KEY (`id_status`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf32 COLLATE=utf32_unicode_520_ci;

--
-- Dumping data for table `status`
--

INSERT INTO `status` (`id_status`, `naziv`) VALUES
(1, 'pripremljen'),
(2, 'poslat kompaniji'),
(3, 'potpisan fakultet'),
(4, 'potpisana kompanija'),
(5, 'potpisane obe'),
(6, 'predat arhivi');

-- --------------------------------------------------------

--
-- Table structure for table `stavka`
--

DROP TABLE IF EXISTS `stavka`;
CREATE TABLE IF NOT EXISTS `stavka` (
  `id_stavka` int(32) NOT NULL AUTO_INCREMENT,
  `naziv` varchar(32) COLLATE utf32_unicode_520_ci NOT NULL,
  `opis` text COLLATE utf32_unicode_520_ci NOT NULL,
  PRIMARY KEY (`id_stavka`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf32 COLLATE=utf32_unicode_520_ci;

--
-- Dumping data for table `stavka`
--

INSERT INTO `stavka` (`id_stavka`, `naziv`, `opis`) VALUES
(1, 'Opis', 'Kratak opis kompanije prikayan na strani partneri'),
(2, 'Baner ', 'baner na zvanicnom sajtu fakulteta na strani partneri'),
(3, 'Logo', 'logo kompanije na reklamnom panou'),
(4, 'Predavanje', 'Mogucnost organizacije strucnih predavanja za predstavnike kompanija');

-- --------------------------------------------------------

--
-- Table structure for table `u_kontaktu`
--

DROP TABLE IF EXISTS `u_kontaktu`;
CREATE TABLE IF NOT EXISTS `u_kontaktu` (
  `id_kontakt` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(32) COLLATE utf32_unicode_520_ci NOT NULL,
  `id_komp` int(32) UNSIGNED NOT NULL,
  PRIMARY KEY (`id_kontakt`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf32 COLLATE=utf32_unicode_520_ci;

--
-- Dumping data for table `u_kontaktu`
--

INSERT INTO `u_kontaktu` (`id_kontakt`, `username`, `id_komp`) VALUES
(1, 'milos', 5),
(2, 'milos', 2),
(3, 'stefan', 1),
(4, 'milos', 6),
(5, 'milica', 7);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
