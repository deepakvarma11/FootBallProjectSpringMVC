-- Create database if not exists
CREATE DATABASE IF NOT EXISTS football_match_db;

-- Use the database
USE football_match_db;

-- Create teams table
CREATE TABLE IF NOT EXISTS teams (
    team_name VARCHAR(100) PRIMARY KEY,
    team_city VARCHAR(100) NOT NULL
);

-- Create matches table
CREATE TABLE IF NOT EXISTS matches (
    match_id INT AUTO_INCREMENT PRIMARY KEY,
    match_date VARCHAR(50) NOT NULL,
    first_team_name VARCHAR(100) NOT NULL,
    second_team_name VARCHAR(100) NOT NULL,
    first_team_goals INT NOT NULL DEFAULT 0,
    second_team_goals INT NOT NULL DEFAULT 0,
    FOREIGN KEY (first_team_name) REFERENCES teams(team_name),
    FOREIGN KEY (second_team_name) REFERENCES teams(team_name)
);

-- Insert sample teams data
INSERT IGNORE INTO teams (team_name, team_city) VALUES 
    ('Bengaluru FC', 'Bengaluru'),
    ('Chennaiyin FC', 'Chennai'),
    ('Delhi Dynamos', 'Delhi'),
    ('FC Goa', 'Goa'),
    ('Kerala Blasters', 'Kerala'),
    ('Mumbai City FC', 'Mumbai'),
    ('ATK Mohun Bagan', 'Kolkata'),
    ('Hyderabad FC', 'Hyderabad');

-- Insert sample match data
INSERT IGNORE INTO matches (match_date, first_team_name, second_team_name, first_team_goals, second_team_goals) VALUES 
    ('2024-01-15', 'Bengaluru FC', 'Chennaiyin FC', 2, 1),
    ('2024-01-20', 'FC Goa', 'Kerala Blasters', 1, 1),
    ('2024-01-25', 'Mumbai City FC', 'Delhi Dynamos', 3, 0),
    ('2024-02-01', 'Bengaluru FC', 'FC Goa', 2, 2),
    ('2024-02-05', 'Chennaiyin FC', 'Kerala Blasters', 1, 0);
