INSERT INTO roles (id, name) values (1, 'ROLE_USER') ON CONFLICT DO NOTHING;
INSERT INTO roles (id, name) values (2, 'ROLE_ADMIN') ON CONFLICT DO NOTHING;

INSERT INTO genres (id, name) values (1, 'horror') ON CONFLICT DO NOTHING;
INSERT INTO genres (id, name) values (2, 'fantasy') ON CONFLICT DO NOTHING;
INSERT INTO genres (id, name) values (3, 'thriller') ON CONFLICT DO NOTHING;
INSERT INTO genres (id, name) values (4, 'mystery') ON CONFLICT DO NOTHING;

INSERT INTO publishers (id, name) values (1, 'Almaty kitap') ON CONFLICT DO NOTHING;
INSERT INTO publishers (id, name) values (2, 'Random publisher') ON CONFLICT DO NOTHING;

INSERT INTO authors (id, surname, name, patronymic, date_of_birth)
VALUES (1, 'Пушкин', 'Александр', 'Сергеевич', '1859-09-12') ON CONFLICT DO NOTHING;
INSERT INTO authors (id, surname, name, patronymic, date_of_birth)
VALUES (2, 'Amirgaliyev', 'Arman', 'Symbatovich', '2003-09-07') ON CONFLICT DO NOTHING;

