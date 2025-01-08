
-- Delete all existing data from the player table
DELETE FROM player;

-- Delete all existing data from the team table
DELETE FROM team;
-- Reset AUTO_INCREMENT for the player table
ALTER TABLE player AUTO_INCREMENT = 1;

-- Reset AUTO_INCREMENT for the team table
ALTER TABLE team AUTO_INCREMENT = 1;

-- Insert new data into the team table
INSERT INTO team (name, commission_rate, account_balance) VALUES ('Team A', 5, 1000000);
INSERT INTO team (name, commission_rate, account_balance) VALUES ('Team B', 10, 2000000);


-- Insert new data into the player table
INSERT INTO player (name, age, experience_months, team_id) VALUES ('Player 1', 25, 60, 1);
INSERT INTO player (name, age, experience_months, team_id) VALUES ('Player 2', 30, 120, 2);

