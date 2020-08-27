
-- OAUTH2 TABLES
create table oauth_access_token (
  token_id VARCHAR(256),
  token bytea,
  authentication_id VARCHAR(256) PRIMARY KEY,
  user_name VARCHAR(256),
  client_id VARCHAR(256),
  authentication bytea,
  refresh_token VARCHAR(256)
);

create table oauth_refresh_token (
  token_id VARCHAR(256),
  token bytea,
  authentication bytea
);

-- USER'S DATA
INSERT INTO movie_user(id_user, username, password, enabled)
values (1, 'Javalos', '$2a$10$pDBBaL0g/7epijof.0KYy.8sBQPqNEBhgcrRU2/PYMlUXseNdnjwa', '1');
INSERT INTO movie_user(id_user, username, password, enabled)
values (2, 'Fabiola', '$2a$10$pDBBaL0g/7epijof.0KYy.8sBQPqNEBhgcrRU2/PYMlUXseNdnjwa', '1');
INSERT INTO movie_user(id_user, username, password, enabled)
values (3, 'Soraya', '$2a$10$pDBBaL0g/7epijof.0KYy.8sBQPqNEBhgcrRU2/PYMlUXseNdnjwa', '1');
INSERT INTO movie_user(id_user, username, password, enabled)
values (4, 'Vikram', '$2a$10$pDBBaL0g/7epijof.0KYy.8sBQPqNEBhgcrRU2/PYMlUXseNdnjwa', '1');
INSERT INTO movie_user(id_user, username, password, enabled)
values (5, 'Ximena', '$2a$10$pDBBaL0g/7epijof.0KYy.8sBQPqNEBhgcrRU2/PYMlUXseNdnjwa', '1');
INSERT INTO movie_user(id_user, username, password, enabled)
values (6, 'Mariana', '$2a$10$pDBBaL0g/7epijof.0KYy.8sBQPqNEBhgcrRU2/PYMlUXseNdnjwa', '1');
INSERT INTO movie_user(id_user, username, password, enabled)
values (7, 'Lizbeth', '$2a$10$pDBBaL0g/7epijof.0KYy.8sBQPqNEBhgcrRU2/PYMlUXseNdnjwa', '1');
INSERT INTO movie_user(id_user, username, password, enabled)
values (8, 'Alejandra', '$2a$10$pDBBaL0g/7epijof.0KYy.8sBQPqNEBhgcrRU2/PYMlUXseNdnjwa', '1');

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
insert into Movie (title, description, image_url, stock, rental_price, sale_price, availability)
values (
'The Dark Knight',
'With the help of allies Lt. Jim Gordon (Gary Oldman) and DA Harvey Dent (Aaron Eckhart), Batman (Christian Bale) has been able to keep a tight lid on crime in Gotham City. But when a vile young criminal calling himself the Joker (Heath Ledger) suddenly throws the town into chaos, the caped Crusader begins to tread a fine line between heroism and vigilantism',
'https://upload.wikimedia.org/wikipedia/en/8/8a/Dark_Knight.jpg',
 10, 1.99, 9.99, true);

insert into Movie (title, description, image_url, stock, rental_price, sale_price, availability)
values (
'Sonic the hedgehog',
'Sonic tries to navigate the complexities of life on Earth with his newfound best friend -- a human named Tom Wachowski. They must soon join forces to prevent the evil Dr. Robotnik from capturing Sonic and using his powers for world domination.',
'https://upload.wikimedia.org/wikipedia/en/c/c1/Sonic_the_Hedgehog_poster.jpg',
 10, 2.99, 19.99, true);

insert into Movie (title, description, image_url, stock, rental_price, sale_price, availability)
values (
'Joker (2019)',
'Forever alone in a crowd, failed comedian Arthur Fleck seeks connection as he walks the streets of Gotham City. Arthur wears two masks -- the one he paints for his day job as a clown, and the guise he projects in a futile attempt to feel like he''s part of the world around him. Isolated, bullied and disregarded by society, Fleck begins a slow descent into madness as he transforms into the criminal mastermind known as the Joker.',
'https://upload.wikimedia.org/wikipedia/en/e/e1/Joker_%282019_film%29_poster.jpg',
 10, 2.99, 19.99, true);

insert into Movie (title, description, image_url, stock, rental_price, sale_price, availability)
values (
'Contagion',
'When Beth Emhoff (Gwyneth Paltrow) returns to Minnesota from a Hong Kong business trip, she attributes the malaise she feels to jet lag. However, two days later, Beth is dead, and doctors tell her shocked husband (Matt Damon) that they have no idea what killed her. Soon, many others start to exhibit the same symptoms, and a global pandemic explodes. Doctors try to contain the lethal microbe, but society begins to collapse as a blogger (Jude Law) fans the flames of paranoia.',
'https://upload.wikimedia.org/wikipedia/en/8/86/Contagion_Poster.jpg',
 10, 1.99, 9.99, true);

insert into Movie (title, description, image_url, stock, rental_price, sale_price, availability)
values (
'Jumanji: The Next Level',
'When Spencer goes back into the fantastical world of Jumanji, pals Martha, Fridge and Bethany re-enter the game to bring him home. But the game is now broken -- and fighting back. Everything the friends know about Jumanji is about to change, as they soon discover there''s more obstacles and more danger to overcome.',
'https://upload.wikimedia.org/wikipedia/en/3/3c/JumanjiTheNextLevelTeaserPoster.jpg',
10, 2.99, 19.99, true);

insert into Movie (title, description, image_url, stock, rental_price, sale_price, availability)
values (
'John Wick: Chapter 3 – Parabellum',
'After gunning down a member of the High Table -- the shadowy international assassin''s guild -- legendary hit man John Wick finds himself stripped of the organization''s protective services. Now stuck with a $14 million bounty on his head, Wick must fight his way through the streets of New York as he becomes the target of the world''s most ruthless killers.',
'https://upload.wikimedia.org/wikipedia/en/9/94/John_Wick_Chapter_3_Parabellum.png',
10, 2.99, 19.99, true);

insert into Movie (title, description, image_url, stock, rental_price, sale_price, availability)
values (
'Blade Runner 2049',
'Officer K (Ryan Gosling), a new blade runner for the Los Angeles Police Department, unearths a long-buried secret that has the potential to plunge what''s left of society into chaos. His discovery leads him on a quest to find Rick Deckard (Harrison Ford), a former blade runner who''s been missing for 30 years.',
'https://upload.wikimedia.org/wikipedia/en/9/9b/Blade_Runner_2049_poster.png',
10, 2.99, 19.99, true);

insert into Movie (title, description, image_url, stock, rental_price, sale_price, availability)
values (
'ABC',
'ZXY',
'https://upload.wikimedia.org/wikipedia/en/9/94/John_Wick_Chapter_3_Parabellum.png',
10, 2.99, 19.99, false);

insert into Movie (title, description, image_url, stock, rental_price, sale_price, availability)
values (
'ABC2',
'ZXY2',
'https://upload.wikimedia.org/wikipedia/en/9/94/John_Wick_Chapter_3_Parabellum.png',
10, 2.99, 19.99, false);

insert into Movie (title, description, image_url, stock, rental_price, sale_price, availability)
values (
'ABC3',
'ZXY3',
'https://upload.wikimedia.org/wikipedia/en/9/94/John_Wick_Chapter_3_Parabellum.png',
10, 2.99, 19.99, false);

insert into Movie (title, description, image_url, stock, rental_price, sale_price, availability)
values (
'ABC4',
'ZXY4',
'https://upload.wikimedia.org/wikipedia/en/9/94/John_Wick_Chapter_3_Parabellum.png',
10, 2.99, 19.99, false);


insert into Movie (title, description, image_url, stock, rental_price, sale_price, availability)
values (
'ABC5',
'ZXY5',
'https://upload.wikimedia.org/wikipedia/en/9/94/John_Wick_Chapter_3_Parabellum.png',
10, 2.99, 19.99, false);



insert into Movie (title, description, image_url, stock, rental_price, sale_price, availability)
values (
'ABC6',
'ZXY6',
'https://upload.wikimedia.org/wikipedia/en/9/94/John_Wick_Chapter_3_Parabellum.png',
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
values(1,1, false, '0.00', {ts '2020-03-31 13:41:00.69'},  {ts '2020-04-03 13:41:00.69'});
insert into rental(id_user, id_movie, movie_returned, penalty_paid, rental_Date, return_date)
values(2,1, false, '0.00', {ts '2020-03-31 13:41:00.69'},  {ts '2020-04-03 13:41:00.69'});
insert into rental(id_user, id_movie, movie_returned, penalty_paid, rental_Date, return_date)
values(3,1, true, '0.00',  {ts '2020-03-31 13:41:00.69'},  {ts '2020-04-03 13:41:00.69'});

-- PURCHASE'S DATA

insert into purchase (quantity, id_movie, id_user, purchase_date)
values (7,2,2, {ts '2020-03-31 13:41:00.69'});
insert into purchase (quantity, id_movie, id_user, purchase_date)
values (7,2,2, {ts '2020-03-31 13:41:00.69'});

-- RENTAL HISTORY DATA

insert into rental_and_purchases (created_date, description) values ({ts '2020-03-31 13:41:00.69'},'The user "Javalos" rented "The Dark Knight" at 2020-03-29 13:41:24');
insert into rental_and_purchases (created_date, description) values ({ts '2020-03-31 13:41:00.69'},'The user "Fabiola" rented "The Dark Knight" at 2020-03-30 13:41:24');
insert into rental_and_purchases (created_date, description) values ({ts '2020-03-31 13:41:00.69'},'The user "Alejandra" rented "The Dark Knight" at 2020-03-31 13:41:24');

-- MOVIE HISTORY DATA
insert into rental_and_purchases (created_date, description) values ({ts '2020-03-31 13:41:00.69'},'The user "Fabiola" purchased 7 movie copies of "Sonic the hedgehog" at 2020-03-31 18:54:42');
insert into rental_and_purchases (created_date, description) values ({ts '2020-03-31 13:41:00.69'},'The user "Mariana" purchased 3 movie copies of "Sonic the hedgehog" at 2020-03-31 18:54:53');
