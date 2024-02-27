INSERT INTO category(name) VALUES ('Eurogames');
INSERT INTO category(name) VALUES ('Ameritrash');
INSERT INTO category(name) VALUES ('Familiar');

INSERT INTO author(name, nationality) VALUES ('Alan R. Moon', 'US');
INSERT INTO author(name, nationality) VALUES ('Vital Lacerda', 'PT');
INSERT INTO author(name, nationality) VALUES ('Simone Luciani', 'IT');
INSERT INTO author(name, nationality) VALUES ('Perepau Llistosella', 'ES');
INSERT INTO author(name, nationality) VALUES ('Michael Kiesling', 'DE');
INSERT INTO author(name, nationality) VALUES ('Phil Walker-Harding', 'US');

INSERT INTO game(title, age, category_id, author_id) VALUES ('On Mars', '14', 1, 2);
INSERT INTO game(title, age, category_id, author_id) VALUES ('Aventureros al tren', '8', 3, 1);
INSERT INTO game(title, age, category_id, author_id) VALUES ('1920: Wall Street', '12', 1, 4);
INSERT INTO game(title, age, category_id, author_id) VALUES ('Barrage', '14', 1, 3);
INSERT INTO game(title, age, category_id, author_id) VALUES ('Los viajes de Marco Polo', '12', 1, 3);
INSERT INTO game(title, age, category_id, author_id) VALUES ('Azul', '8', 3, 5);
INSERT INTO game(title, age, category_id, author_id) VALUES ('Machine', '13', 3, 4);

INSERT INTO clients(name) VALUES ('Alicia');
INSERT INTO clients(name) VALUES ('Jorge');
INSERT INTO clients(name) VALUES ('Paco');
INSERT INTO clients(name) VALUES ('Francisco');
INSERT INTO clients(name) VALUES ('Aitor');
INSERT INTO clients(name) VALUES ('Miguel');
INSERT INTO clients(name) VALUES ('Raquel');
INSERT INTO clients(name) VALUES ('Ana');

INSERT INTO prestamo(game_id, clients_id, fechainicio, fechadevolucion) VALUES (1, 1, '2024-02-12', '2024-02-12');
INSERT INTO prestamo(game_id, clients_id, fechainicio, fechadevolucion) VALUES (2, 2, '2024-03-12', '2024-09-12');
INSERT INTO prestamo(game_id, clients_id, fechainicio, fechadevolucion) VALUES (3, 3, '2024-02-12', '2024-02-13');
INSERT INTO prestamo(game_id, clients_id, fechainicio, fechadevolucion) VALUES (4, 4, '2024-02-12', '2025-02-12');
INSERT INTO prestamo(game_id, clients_id, fechainicio, fechadevolucion) VALUES (5, 5, '2024-02-12', '2024-05-12');
INSERT INTO prestamo(game_id, clients_id, fechainicio, fechadevolucion) VALUES (6, 6, '2024-02-12', '2024-02-22');
