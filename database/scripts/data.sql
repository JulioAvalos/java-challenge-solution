CREATE DATABASE IF NOT EXISTS moviesdb;
use moviesdb;

-- MySQL dump 10.13  Distrib 8.0.16, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: moviesdb
-- ------------------------------------------------------
-- Server version	8.0.19

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `movie`
--

DROP TABLE IF EXISTS `movie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `movie` (
  `id_movie` bigint NOT NULL AUTO_INCREMENT,
  `availability` bit(1) DEFAULT NULL,
  `description` longtext,
  `image_url` longtext,
  `rental_price` decimal(19,2) DEFAULT NULL,
  `sale_price` decimal(19,2) DEFAULT NULL,
  `stock` int DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_movie`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie`
--

LOCK TABLES `movie` WRITE;
/*!40000 ALTER TABLE `movie` DISABLE KEYS */;
/*!40000 ALTER TABLE `movie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie_likes`
--

DROP TABLE IF EXISTS `movie_likes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `movie_likes` (
  `id_movie` bigint NOT NULL,
  `id_user` bigint NOT NULL,
  PRIMARY KEY (`id_movie`,`id_user`),
  KEY `FKpjh0y4mknar2kos5047xk78cc` (`id_user`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie_likes`
--

LOCK TABLES `movie_likes` WRITE;
/*!40000 ALTER TABLE `movie_likes` DISABLE KEYS */;
/*!40000 ALTER TABLE `movie_likes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie_update_history`
--

DROP TABLE IF EXISTS `movie_update_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `movie_update_history` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie_update_history`
--

LOCK TABLES `movie_update_history` WRITE;
/*!40000 ALTER TABLE `movie_update_history` DISABLE KEYS */;
/*!40000 ALTER TABLE `movie_update_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie_user`
--

DROP TABLE IF EXISTS `movie_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `movie_user` (
  `id_user` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `enabled` bit(1) NOT NULL,
  `password` varchar(255) NOT NULL,
  `updated_at` datetime DEFAULT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id_user`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie_user`
--

LOCK TABLES `movie_user` WRITE;
/*!40000 ALTER TABLE `movie_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `movie_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oauth_access_token`
--

DROP TABLE IF EXISTS `oauth_access_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `oauth_access_token` (
  `authentication_id` varchar(256) NOT NULL,
  `token_id` varchar(256) DEFAULT NULL,
  `token` longblob,
  `user_name` varchar(256) DEFAULT NULL,
  `client_id` varchar(256) DEFAULT NULL,
  `authentication` longblob,
  `refresh_token` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`authentication_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oauth_access_token`
--

LOCK TABLES `oauth_access_token` WRITE;
/*!40000 ALTER TABLE `oauth_access_token` DISABLE KEYS */;
/*!40000 ALTER TABLE `oauth_access_token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oauth_refresh_token`
--

DROP TABLE IF EXISTS `oauth_refresh_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `oauth_refresh_token` (
  `token_id` varchar(256) DEFAULT NULL,
  `token` longblob,
  `authentication` longblob
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oauth_refresh_token`
--

LOCK TABLES `oauth_refresh_token` WRITE;
/*!40000 ALTER TABLE `oauth_refresh_token` DISABLE KEYS */;
/*!40000 ALTER TABLE `oauth_refresh_token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchase`
--

DROP TABLE IF EXISTS `purchase`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `purchase` (
  `id_purchase` bigint NOT NULL AUTO_INCREMENT,
  `purchase_date` datetime DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `id_movie` bigint NOT NULL,
  `id_user` bigint NOT NULL,
  PRIMARY KEY (`id_purchase`),
  KEY `fk_movie_purchase` (`id_movie`),
  KEY `fk_user_purchase` (`id_user`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchase`
--

LOCK TABLES `purchase` WRITE;
/*!40000 ALTER TABLE `purchase` DISABLE KEYS */;
/*!40000 ALTER TABLE `purchase` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rental`
--

DROP TABLE IF EXISTS `rental`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `rental` (
  `id_rent` bigint NOT NULL AUTO_INCREMENT,
  `movie_returned` bit(1) DEFAULT NULL,
  `penalty_paid` decimal(19,2) DEFAULT NULL,
  `rental_date` datetime DEFAULT NULL,
  `return_date` datetime DEFAULT NULL,
  `id_movie` bigint NOT NULL,
  `id_user` bigint NOT NULL,
  PRIMARY KEY (`id_rent`),
  KEY `fk_movie_rent` (`id_movie`),
  KEY `fk_user_rent` (`id_user`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rental`
--

LOCK TABLES `rental` WRITE;
/*!40000 ALTER TABLE `rental` DISABLE KEYS */;
/*!40000 ALTER TABLE `rental` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rental_and_purchases`
--

DROP TABLE IF EXISTS `rental_and_purchases`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `rental_and_purchases` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rental_and_purchases`
--

LOCK TABLES `rental_and_purchases` WRITE;
/*!40000 ALTER TABLE `rental_and_purchases` DISABLE KEYS */;
/*!40000 ALTER TABLE `rental_and_purchases` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `role` (
  `id_role` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_role`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user_role` (
  `id_user` bigint NOT NULL,
  `id_role` bigint NOT NULL,
  PRIMARY KEY (`id_user`,`id_role`),
  KEY `FK2aam9nt2tv8vcfymi3jo9c314` (`id_role`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-04-01 16:54:13


-- USER'S DATA
INSERT INTO movie_user(id_user, username, password, enabled)
values (1, 'Javalos', '$2a$10$pDBBaL0g/7epijof.0KYy.8sBQPqNEBhgcrRU2/PYMlUXseNdnjwa', true);
INSERT INTO movie_user(id_user, username, password, enabled)
values (2, 'Fabiola', '$2a$10$pDBBaL0g/7epijof.0KYy.8sBQPqNEBhgcrRU2/PYMlUXseNdnjwa', true);
INSERT INTO movie_user(id_user, username, password, enabled)
values (3, 'Soraya', '$2a$10$pDBBaL0g/7epijof.0KYy.8sBQPqNEBhgcrRU2/PYMlUXseNdnjwa', true);
INSERT INTO movie_user(id_user, username, password, enabled)
values (4, 'Vikram', '$2a$10$pDBBaL0g/7epijof.0KYy.8sBQPqNEBhgcrRU2/PYMlUXseNdnjwa', true);
INSERT INTO movie_user(id_user, username, password, enabled)
values (5, 'Ximena', '$2a$10$pDBBaL0g/7epijof.0KYy.8sBQPqNEBhgcrRU2/PYMlUXseNdnjwa', true);
INSERT INTO movie_user(id_user, username, password, enabled)
values (6, 'Mariana', '$2a$10$pDBBaL0g/7epijof.0KYy.8sBQPqNEBhgcrRU2/PYMlUXseNdnjwa', true);
INSERT INTO movie_user(id_user, username, password, enabled)
values (7, 'Lizbeth', '$2a$10$pDBBaL0g/7epijof.0KYy.8sBQPqNEBhgcrRU2/PYMlUXseNdnjwa', true);
INSERT INTO movie_user(id_user, username, password, enabled)
values (8, 'Alejandra', '$2a$10$pDBBaL0g/7epijof.0KYy.8sBQPqNEBhgcrRU2/PYMlUXseNdnjwa', true);

-- ROLE'S DATA
INSERT INTO role (id_role, name, description) VALUES (1, 'ADMIN', 'Administrator');
INSERT INTO role (id_role, name, description) VALUES (2, 'USER', 'User');
INSERT INTO role (id_role, name, description) VALUES (3, 'DBA', 'DB Administrator');

-- ROLE ASSIGNATION DATA
INSERT INTO user_role (id_user, id_role) VALUES (1, 1);
INSERT INTO user_role (id_user, id_role) VALUES (1, 2);

INSERT INTO user_role (id_user, id_role) VALUES (2, 1);
INSERT INTO user_role (id_user, id_role) VALUES (2, 3);

INSERT INTO user_role (id_user, id_role) VALUES (3, 1);
INSERT INTO user_role (id_user, id_role) VALUES (4, 1);
INSERT INTO user_role (id_user, id_role) VALUES (5, 1);
INSERT INTO user_role (id_user, id_role) VALUES (6, 1);
INSERT INTO user_role (id_user, id_role) VALUES (7, 1);
INSERT INTO user_role (id_user, id_role) VALUES (8, 1);

-- MOVIE'S DATA
insert into movie (title, description, image_url, stock, rental_price, sale_price, availability)
values (
'The Dark Knight',
'With the help of allies Lt. Jim Gordon (Gary Oldman) and DA Harvey Dent (Aaron Eckhart), Batman (Christian Bale) has been able to keep a tight lid on crime in Gotham City. But when a vile young criminal calling himself the Joker (Heath Ledger) suddenly throws the town into chaos, the caped Crusader begins to tread a fine line between heroism and vigilantism',
'https://upload.wikimedia.org/wikipedia/en/8/8a/Dark_Knight.jpg',
 300, 1.99, 9.99, true);

insert into movie (title, description, image_url, stock, rental_price, sale_price, availability)
values (
'Sonic the hedgehog',
'Sonic tries to navigate the complexities of life on Earth with his newfound best friend -- a human named Tom Wachowski. They must soon join forces to prevent the evil Dr. Robotnik from capturing Sonic and using his powers for world domination.',
'https://upload.wikimedia.org/wikipedia/en/c/c1/Sonic_the_Hedgehog_poster.jpg',
 200, 2.99, 19.99, true);

insert into movie (title, description, image_url, stock, rental_price, sale_price, availability)
values (
'Joker (2019)',
'Forever alone in a crowd, failed comedian Arthur Fleck seeks connection as he walks the streets of Gotham City. Arthur wears two masks -- the one he paints for his day job as a clown, and the guise he projects in a futile attempt to feel like he''s part of the world around him. Isolated, bullied and disregarded by society, Fleck begins a slow descent into madness as he transforms into the criminal mastermind known as the Joker.',
'https://upload.wikimedia.org/wikipedia/en/e/e1/Joker_%282019_film%29_poster.jpg',
 200, 2.99, 19.99, true);

insert into movie (title, description, image_url, stock, rental_price, sale_price, availability)
values (
'Contagion',
'When Beth Emhoff (Gwyneth Paltrow) returns to Minnesota from a Hong Kong business trip, she attributes the malaise she feels to jet lag. However, two days later, Beth is dead, and doctors tell her shocked husband (Matt Damon) that they have no idea what killed her. Soon, many others start to exhibit the same symptoms, and a global pandemic explodes. Doctors try to contain the lethal microbe, but society begins to collapse as a blogger (Jude Law) fans the flames of paranoia.',
'https://upload.wikimedia.org/wikipedia/en/8/86/Contagion_Poster.jpg',
 100, 1.99, 9.99, true);

insert into movie (title, description, image_url, stock, rental_price, sale_price, availability)
values (
'Jumanji: The Next Level',
'When Spencer goes back into the fantastical world of Jumanji, pals Martha, Fridge and Bethany re-enter the game to bring him home. But the game is now broken -- and fighting back. Everything the friends know about Jumanji is about to change, as they soon discover there''s more obstacles and more danger to overcome.',
'https://upload.wikimedia.org/wikipedia/en/3/3c/JumanjiTheNextLevelTeaserPoster.jpg',
70, 2.99, 19.99, true);

insert into movie (title, description, image_url, stock, rental_price, sale_price, availability)
values (
'John Wick: Chapter 3 – Parabellum',
'After gunning down a member of the High Table -- the shadowy international assassin''s guild -- legendary hit man John Wick finds himself stripped of the organization''s protective services. Now stuck with a $14 million bounty on his head, Wick must fight his way through the streets of New York as he becomes the target of the world''s most ruthless killers.',
'https://upload.wikimedia.org/wikipedia/en/9/94/John_Wick_Chapter_3_Parabellum.png',
45, 2.99, 19.99, true);

insert into movie (title, description, image_url, stock, rental_price, sale_price, availability)
values (
'Blade Runner 2049',
'Officer K (Ryan Gosling), a new blade runner for the Los Angeles Police Department, unearths a long-buried secret that has the potential to plunge what''s left of society into chaos. His discovery leads him on a quest to find Rick Deckard (Harrison Ford), a former blade runner who''s been missing for 30 years.',
'https://upload.wikimedia.org/wikipedia/en/9/9b/Blade_Runner_2049_poster.png',
60, 2.99, 19.99, true);

insert into movie (title, description, image_url, stock, rental_price, sale_price, availability)
values (
'Untitled Movie',
'No description given.',
null,
20, 5.99, 59.99, true);

insert into movie (title, description, image_url, stock, rental_price, sale_price, availability)
values (
'Another Untitled Movie of 2020',
'No description given.',
null,
20, 7.99, 99.99, true);

insert into movie (title, description, image_url, stock, rental_price, sale_price, availability)
values (
'ABC3',
'ZXY3',
null,
10, 2.99, 19.99, false);

insert into movie (title, description, image_url, stock, rental_price, sale_price, availability)
values (
'ABC4',
'ZXY4',
null,
10, 2.99, 19.99, false);

insert into movie (title, description, image_url, stock, rental_price, sale_price, availability)
values (
'ABC5',
'ZXY5',
null,
10, 2.99, 19.99, false);

insert into movie (title, description, image_url, stock, rental_price, sale_price, availability)
values (
'ABC6',
'ZXY6',
null,
10, 2.99, 19.99, false);


-- LIKE'S DATA

-- likes for the dark knight movie.
insert into movie_likes(id_movie, id_user) values (1,1);
insert into movie_likes(id_movie, id_user) values (1,2);
insert into movie_likes(id_movie, id_user) values (1,3);
insert into movie_likes(id_movie, id_user) values (1,4);
insert into movie_likes(id_movie, id_user) values (1,5);
insert into movie_likes(id_movie, id_user) values (1,6);
insert into movie_likes(id_movie, id_user) values (1,7);
insert into movie_likes(id_movie, id_user) values (1,8);

-- likes for sonic the hegdehog movie
insert into movie_likes(id_movie, id_user) values (2,3);
insert into movie_likes(id_movie, id_user) values (2,4);
insert into movie_likes(id_movie, id_user) values (2,5);
insert into movie_likes(id_movie, id_user) values (2,6);

-- likes for joker movie
insert into movie_likes(id_movie, id_user) values (3,1);
insert into movie_likes(id_movie, id_user) values (3,2);
insert into movie_likes(id_movie, id_user) values (3,3);

-- likes for jumanji
insert into movie_likes(id_movie, id_user) values (4,1);
insert into movie_likes(id_movie, id_user) values (4,2);
insert into movie_likes(id_movie, id_user) values (4,3);
insert into movie_likes(id_movie, id_user) values (4,4);
insert into movie_likes(id_movie, id_user) values (4,5);
insert into movie_likes(id_movie, id_user) values (4,6);

-- likes for contagious
insert into movie_likes(id_movie, id_user) values (5,1);
insert into movie_likes(id_movie, id_user) values (5,2);
insert into movie_likes(id_movie, id_user) values (5,3);

-- likes for jonh wick
insert into movie_likes(id_movie, id_user) values (6,1);
insert into movie_likes(id_movie, id_user) values (6,2);
insert into movie_likes(id_movie, id_user) values (6,3);
insert into movie_likes(id_movie, id_user) values (6,4);


-- RENTAL'S DATA

insert into rental(id_user, id_movie, movie_returned, penalty_paid, rental_Date, return_date)
values(1,1, false, '0.00',  '2020-03-31 13:41:00.69',  '2020-04-03 13:41:00.69');
insert into rental(id_user, id_movie, movie_returned, penalty_paid, rental_Date, return_date)
values(2,1, false, '0.00', '2020-03-31 13:41:00.69',  '2020-04-03 13:41:00.69');
insert into rental(id_user, id_movie, movie_returned, penalty_paid, rental_Date, return_date)
values(3,1, true, '0.00', '2020-03-31 13:41:00.69',  '2020-04-03 13:41:00.69');

-- PURCHASE'S DATA

insert into purchase(quantity, id_movie, id_user, purchase_date)
values (7,2,2, '2020-03-31 13:41:00.69');
insert into purchase (quantity, id_movie, id_user, purchase_date)
values (7,2,2,'2020-03-31 13:41:00.69');

-- RENTAL HISTORY DATA

insert into rental_and_purchases (created_date, description) values ('2020-03-31 13:41:00.69','The user "Javalos" rented "The Dark Knight" at 2020-03-29 13:41:24');
insert into rental_and_purchases (created_date, description) values ('2020-03-31 13:41:00.69','The user "Fabiola" rented "The Dark Knight" at 2020-03-30 13:41:24');
insert into rental_and_purchases (created_date, description) values ('2020-03-31 13:41:00.69','The user "Alejandra" rented "The Dark Knight" at 2020-03-31 13:41:24');

-- MOVIE HISTORY DATA
insert into rental_and_purchases (created_date, description) values ('2020-03-31 13:41:00.69','The user "Fabiola" purchased 7 movie copies of "Sonic the hedgehog" at 2020-03-31 18:54:42');
insert into rental_and_purchases (created_date, description) values ('2020-03-31 13:41:00.69','The user "Mariana" purchased 3 movie copies of "Sonic the hedgehog" at 2020-03-31 18:54:53');
