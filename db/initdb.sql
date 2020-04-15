-- Initialize database --

---- Create types ----

CREATE TYPE STATUS AS ENUM ('soon', 'now', 'passed');

CREATE TYPE SPORT AS ENUM ('football', 'synchronized_swimming',
    'gymnastics', 'biathlon', 'hockey');

CREATE TYPE SEATS AS ENUM ('front', 'middle', 'back');

---- Create tables -----

CREATE TABLE competitions (
    comp_id SERIAL PRIMARY KEY,
    comp_name VARCHAR(50) NOT NULL,
    location VARCHAR(50) NOT NULL,
    comp_time TIMESTAMP NOT NULL,
    comp_status STATUS NOT NULL,
    sport_kind SPORT NOT NULL,
    free_seats_status BOOLEAN NOT NULL -- true, if free seats exist
);

CREATE TABLE IF NOT EXISTS trainers (
    trainer_id SERIAL PRIMARY KEY,
    trainer_name VARCHAR(50) NOT NULL,
    birthday DATE NOT NULL
);

CREATE TABLE IF NOT EXISTS teams (
    team_id SERIAL PRIMARY KEY,
    trainer_id INTEGER REFERENCES trainers NOT NULL,
    team_name VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS sportsmen (
     sportsman_id SERIAL PRIMARY KEY,
     trainer_id INTEGER REFERENCES trainers,
     team_id INTEGER REFERENCES teams,
     sportsman_name VARCHAR(50) NOT NULL,
     birthday DATE NOT NULL
);

CREATE TABLE IF NOT EXISTS sportsmen_teams (
    sportsman_id INTEGER REFERENCES sportsmen NOT NULL,
    team_id INTEGER REFERENCES teams NOT NULL
);

CREATE TABLE IF NOT EXISTS comp_sportsmen (
    sportsman_id INTEGER REFERENCES sportsmen NOT NULL,
    comp_id INTEGER REFERENCES competitions NOT NULL,
    place SMALLINT, -- may be NULL if competitions weren't passed
    points SMALLINT -- may be NULL if competitions weren't passed
);

CREATE TABLE IF NOT EXISTS comp_teams (
    team_id INTEGER REFERENCES teams NOT NULL,
    comp_id INTEGER REFERENCES competitions NOT NULL,
    place SMALLINT, -- may be NULL if competitions weren't passed
    points SMALLINT -- may be NULL if competitions weren't passed
);

CREATE TABLE IF NOT EXISTS seats_info (
    comp_id INTEGER REFERENCES competitions NOT NULL,
    seats_type SEATS NOT NULL,
    num_seats SMALLINT NOT NULL CHECK (num_seats > 0),
    num_free_seats SMALLINT NOT NULL CHECK (num_free_seats >= 0),
    price NUMERIC NOT NULL CHECK (price >= 0.0),
    CHECK (num_free_seats <= num_seats)
);

