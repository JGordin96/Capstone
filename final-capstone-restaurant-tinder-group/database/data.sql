-- *****************************************************************************
-- This script contains INSERT statements for populating tables with seed data
-- *****************************************************************************

BEGIN;

-- INSERT statements go here
INSERT INTO preferences (preference_id, preference) VALUES (0, 'Burger');
INSERT INTO preferences (preference_id, preference) VALUES (1, 'Sandwich');
INSERT INTO preferences (preference_id, preference) VALUES (2, 'Mexican');
INSERT INTO preferences (preference_id, preference) VALUES (3, 'Pizza');
INSERT INTO preferences (preference_id, preference) VALUES (4, 'Greek');
INSERT INTO preferences (preference_id, preference) VALUES (5, 'BBQ');
INSERT INTO preferences (preference_id, preference) VALUES (6, 'Sushi');
INSERT INTO preferences (preference_id, preference) VALUES (7, 'Chinese');
INSERT INTO preferences (preference_id, preference) VALUES (8, 'Italian');
INSERT INTO preferences (preference_id, preference) VALUES (9, 'Vegetarian');

COMMIT;