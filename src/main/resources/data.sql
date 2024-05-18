-- MASS OF TESTS
-- USERS
INSERT INTO users_tb(name, email, password, exchange) VALUES('Gregory', 'gregory@test.com', 11111111, 'Correio')
INSERT INTO users_tb(name, email, password, exchange) VALUES('Beatriz', 'beatriz@test.com.br', 22222222, 'Presencial')

-- GAMES
INSERT INTO games_tb(name, brand, user_id) VALUES('Splatoon', 'Nintendo', 1)
INSERT INTO games_tb(name, brand, user_id) VALUES('Mario Kart Deluxe 8', 'Nintendo', 1)
INSERT INTO games_tb(name, brand, user_id) VALUES('Zelda - BOTW', 'Nintendo', 1)
INSERT INTO games_tb(name, brand, user_id) VALUES('Zelda - TOTK', 'Nintendo', 1)
INSERT INTO games_tb(name, brand, user_id) VALUES('The Last of US - part I', 'PS4', 2)
INSERT INTO games_tb(name, brand, user_id) VALUES('The Last of US - part II', 'PS5', 2)
