-- Fill the database --

INSERT INTO competitions (comp_name, location, comp_time,
                          comp_status, sport_kind, free_seats_status) VALUES
    ('FIFA World Cup', 'Russia, Moscow, Arena CSKA', '2019-09-22 17:20+0',
     'passed', 'football', FALSE),
    ('MN West Association Championship', 'USA, Wayzata, Minnesota',
     '2020-02-20 10:00+0', 'soon', 'synchronized_swimming', FALSE),
    ('2020 GYMNASTICS WORLD CUP', 'UK, Birmingham, Arena Birmingham',
     '2020-01-20 13:10+0', 'passed', 'gymnastics', TRUE),
    ('Biathlon. World Cup 2020', 'Italy, Antholz', '2020-02-13 15:00+0',
     'now', 'biathlon', TRUE),
    ('NHL regular season', 'USA, Tampa, Amali Arena', '2020-03-31 10:00+0',
     'soon', 'hockey', TRUE);

INSERT INTO trainers (trainer_name, birthday) VALUES
    ('Tyler Bertuzzi', '1990-12-15'),
    ('Roope Hinz', '1979-05-13'),
    ('James van rimsdike', '1981-11-11'),
    ('Denis Guryanov', '1980-01-24'),
    ('Andre Burakovsky', '1983-06-18');

INSERT INTO teams (trainer_id, team_name) VALUES
    (3, 'Vegas Golden Knights'),
    (4, 'CSKA'),
    (1, 'Calgary Flames'),
    (2, 'Edmonton oilers'),
    (5, 'Tampa Bay Lightning');

INSERT INTO sportsmen (trainer_id, team_id,
                       sportsman_name, birthday) VALUES
    (1, NULL, 'David Pastrniak', '1980-03-21'),
    (1, NULL, 'Auston Matthews', '1985-02-24'),
    (NULL, 1, 'Leon Dreisaitl', '1981-04-01'),
    (NULL, 2, 'Alexander Ovechkin', '1985-05-12'),
    (NULL, 2, 'Artemy Panarin', '1984-09-09'),
    (2, NULL, 'Stephen Stamkos', '1990-10-20'),
    (2, NULL, 'William Nyulander', '1998-03-21'),
    (NULL, 4, 'Patrick Layne', '1995-02-14'),
    (NULL, 5, 'John Tavares', '1997-07-29'),
    (5, NULL, 'Dominic Kubalik', '1996-05-31');

INSERT INTO comp_sportsmen (sportsman_id, comp_id, place, points) VALUES
    (6, 3, 2, 40),
    (1, 4, NULL, NULL),
    (2, 4, NULL, NULL),
    (7, 3, 1, 50),
    (10, 4, NULL, NULL);

INSERT INTO comp_teams (team_id, comp_id, place, points) VALUES
    (2, 1, 3, 10),
    (1, 2, NULL, NULL),
    (3, 2, NULL, NULL),
    (4, 5, NULL, NULL),
    (5, 5, NULL, NULL);

INSERT INTO sportsmen_teams (sportsman_id, team_id) VALUES
    (8, 1),
    (8, 3),
    (9, 4),
    (3, 1),
    (5, 3);

INSERT INTO seats_info (comp_id, seats_type, num_seats,
                        num_free_seats, price) VALUES
    (1, 'front', 1000, 0, 5000),
    (1, 'middle', 2000, 0, 1000),
    (1, 'back', 3000, 0, 500),
    (2, 'middle', 2000, 0, 2500),
    (3, 'front', 3000, 1543, 1000),
    (4, 'front', 2150, 387, 2000),
    (4, 'back', 5000, 1432, 1500),
    (5, 'front', 1200, 300, 4000),
    (5, 'middle', 3400, 1270, 2000),
    (5, 'back', 5000, 3456, 1250);