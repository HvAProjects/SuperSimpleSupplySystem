INSERT INTO user (user_id, provider_user_id, email)
VALUES (1, 'google-oauth2|106089153303411272812' , NULL);

INSERT INTO household_users (household_household_id, users_user_id)
VALUES (1,1),
       (2,1);

INSERT INTO location (id, name, household)
VALUES (1, 'Trapkast', '1'),
       (2, 'Koelkast', '1'),
       (3, 'Trapkast', '2');