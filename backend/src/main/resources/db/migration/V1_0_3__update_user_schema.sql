
ALTER TABLE user
    DROP COLUMN created_date;

ALTER TABLE user
    DROP COLUMN display_name;

ALTER TABLE user
    DROP COLUMN enabled;

ALTER TABLE user
    DROP COLUMN expired;

ALTER TABLE user
    DROP COLUMN modified_date;

ALTER TABLE user
    DROP COLUMN password;

ALTER TABLE user
    DROP COLUMN password_expired;

ALTER TABLE user
    DROP COLUMN provider;


ALTER TABLE location
    ADD household BIGINT NULL;

ALTER TABLE location
    ADD CONSTRAINT FK_LOCATION_ON_HOUSEHOLD FOREIGN KEY (household) REFERENCES household (household_id);

ALTER TABLE location
    DROP COLUMN tenant_id;