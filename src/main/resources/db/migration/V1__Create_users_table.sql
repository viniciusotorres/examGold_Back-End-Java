-- src/main/resources/db/migration/V1__Create_schools_and_users_tables.sql
CREATE TABLE schools (
                         id SERIAL PRIMARY KEY,
                         name VARCHAR(255) NOT NULL,
                         address VARCHAR(255),
                         phone VARCHAR(50),
                         email VARCHAR(255),
                         cnpj VARCHAR(20)
);

CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       birth_date VARCHAR(255) NOT NULL,
                       email VARCHAR(255) UNIQUE NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       username VARCHAR(255) NOT NULL,
                       school_id BIGINT,
                       CONSTRAINT fk_school FOREIGN KEY (school_id) REFERENCES schools(id)
);

ALTER TABLE users
    ADD COLUMN its_teacher BOOLEAN;

CREATE TABLE teams (
                       id_team SERIAL PRIMARY KEY,
                       name_team VARCHAR(255) NOT NULL,
                       description_team TEXT,
                       school_id BIGINT,
                       CONSTRAINT fk_school FOREIGN KEY (school_id) REFERENCES schools(id)
);

ALTER TABLE users ADD COLUMN team_id BIGINT;

ALTER TABLE users ADD CONSTRAINT fk_team FOREIGN KEY (team_id) REFERENCES teams(id_team);

