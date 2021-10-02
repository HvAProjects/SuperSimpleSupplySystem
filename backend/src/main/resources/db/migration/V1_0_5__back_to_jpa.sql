CREATE TABLE user_roles
(
    roles_role_id BIGINT NOT NULL,
    users_user_id BIGINT NOT NULL,
    CONSTRAINT pk_user_roles PRIMARY KEY (roles_role_id, users_user_id)
);

ALTER TABLE notification
    ADD sender_user_id BIGINT NULL;

ALTER TABLE notification
    ADD CONSTRAINT FK_NOTIFICATION_ON_SENDER_USER FOREIGN KEY (sender_user_id) REFERENCES user (user_id);

ALTER TABLE user_roles
    ADD CONSTRAINT fk_userol_on_role FOREIGN KEY (roles_role_id) REFERENCES `role` (role_id);

ALTER TABLE user_roles
    ADD CONSTRAINT fk_userol_on_user FOREIGN KEY (users_user_id) REFERENCES user (user_id);

ALTER TABLE location
    DROP FOREIGN KEY FK_LOCATION_ON_HOUSEHOLD;

ALTER TABLE notification
    DROP FOREIGN KEY FK_NOTIFICATION_ON_PRODUCT;

ALTER TABLE user_role
    DROP FOREIGN KEY fk_user_role_on_role;

ALTER TABLE user_role
    DROP FOREIGN KEY fk_user_role_on_user;

ALTER TABLE user
    DROP KEY provider_user_id;

DROP TABLE user_role;

ALTER TABLE notification
    DROP COLUMN product_id;

ALTER TABLE location
    DROP COLUMN household;

ALTER TABLE location
    ADD household VARCHAR(255) NULL;