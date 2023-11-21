INSERT INTO db_pizzeria.pizzas (id, description, name, picture, price)VALUES(1, 'La classica con pomodoro, mozzarella e basilico fresco.', 'Margherita', 'https://upload.wikimedia.org/wikipedia/commons/thumb/c/c8/Pizza_Margherita_stu_spivack.jpg/390px-Pizza_Margherita_stu_spivack.jpg', 8.99);
INSERT INTO db_pizzeria.pizzas (id, description, name, picture, price) VALUES(2, 'Un mix piccante di pepperoni su una crosta di formaggio.', 'Pepperoni', 'https://sipbitego.com/wp-content/uploads/2021/08/Pepperoni-Pizza-Recipe-Sip-Bite-Go.jpg', 10.99);
INSERT INTO db_pizzeria.pizzas (id, description, name, picture, price) VALUES(3, 'Pieno di verdure fresche e colorate.', 'Vegetariana', 'https://www.guardini.com/images/guardinispa/ricette/full/pizza_set_2021_full.jpg', 9.99);
INSERT INTO db_pizzeria.pizzas (id, description, name, picture, price) VALUES(5, 'Un mix tropicale di prosciutto, ananas e formaggio.', 'Hawaii', 'https://img.kidspot.com.au/pZnR2nZu/kk/2015/03/hawaiian-pizza-recipe-605894-2.jpg', 11.99);
INSERT INTO db_pizzeria.pizzas (id, description, name, picture, price) VALUES(4, 'Una festa di formaggi con quattro varietà diverse.', 'Quattro Formaggi', 'https://wips.plug.it/cips/buonissimo.org/cms/2012/12/10798952_l.jpg', 12.99);
INSERT INTO db_pizzeria.ingredienti (name) VALUES('Pomodoro');
INSERT INTO db_pizzeria.ingredienti (name) VALUES('Mozzarella');
INSERT INTO db_pizzeria.ingredienti (name) VALUES('Formaggi');
INSERT INTO db_pizzeria.ingredienti (name) VALUES('Peperoni');
INSERT INTO db_pizzeria.ingredienti (name) VALUES('Salamino');
INSERT INTO db_pizzeria.ingredienti (name) VALUES('Ananas');
INSERT INTO db_pizzeria.ingredienti (name) VALUES('Basilico');
INSERT INTO db_pizzeria.ingredienti (name) VALUES('Melanzane');
INSERT INTO db_pizzeria.ingredienti (name) VALUES('Zucchine');

INSERT INTO pizzas_ingredienti (pizza_id,ingrediente_id) VALUES (1,1)
INSERT INTO pizzas_ingredienti (pizza_id,ingrediente_id) VALUES (1,2)
INSERT INTO pizzas_ingredienti (pizza_id,ingrediente_id) VALUES (1,3)
INSERT INTO pizzas_ingredienti (pizza_id,ingrediente_id) VALUES (1,4)

INSERT INTO offerte (name, start_date, end_date,pizza_id) VALUES ("ciccico", "2023/11/21","2023/12/31",1)
INSERT INTO offerte (name, start_date, end_date,pizza_id) VALUES ("ciao", "2023/11/21","2023/12/31",1)
INSERT INTO offerte (name, start_date, end_date,pizza_id) VALUES ("luca", "2023/11/21","2023/12/31",1)
INSERT INTO offerte (name, start_date, end_date,pizza_id) VALUES ("eccopòa", "2023/11/21","2023/12/31",2)

INSERT INTO ruoli (name) VALUES('ADMIN');
INSERT INTO ruoli (name) VALUES('USER');

INSERT INTO utenti (email, first_name, last_name, registered_at, password) VALUES('cighi@gmail.com', 'Cighi', 'Nudo', '2023-11-20 10:35', '{noop}cighi');
INSERT INTO utenti (email, first_name, last_name, registered_at, password) VALUES('pinni@gmail.com', 'Pinni', 'Renata', '2023-11-20 10:35','{noop}pinni');

INSERT INTO utenti_ruoli (utente_id, ruolo_id) VALUES(1, 1);
INSERT INTO utenti_ruoli (utente_id, ruolo_id) VALUES(1, 2);
INSERT INTO utenti_ruoli (utente_id, ruolo_id) VALUES(2, 2);

