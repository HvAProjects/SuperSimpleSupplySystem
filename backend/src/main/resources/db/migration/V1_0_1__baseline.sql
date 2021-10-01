CREATE TABLE household
(
    household_id BIGINT AUTO_INCREMENT NOT NULL,
    name         VARCHAR(255)          NULL,
    address      VARCHAR(255)          NULL,
    postal_code  VARCHAR(255)          NULL,
    city         VARCHAR(255)          NULL,
    country      VARCHAR(255)          NULL,
    CONSTRAINT pk_household PRIMARY KEY (household_id)
);

CREATE TABLE household_locations
(
    household_household_id BIGINT NOT NULL,
    locations_id           BIGINT NOT NULL
);

CREATE TABLE household_users
(
    household_household_id BIGINT NOT NULL,
    users_user_id          BIGINT NOT NULL,
    CONSTRAINT pk_household_users PRIMARY KEY (household_household_id, users_user_id)
);

CREATE TABLE location
(
    id        BIGINT AUTO_INCREMENT NOT NULL,
    name      VARCHAR(255)          NULL,
    tenant_id VARCHAR(255)          NULL,
    CONSTRAINT pk_location PRIMARY KEY (id)
);

CREATE TABLE location_products
(
    location_id BIGINT NOT NULL,
    products_id BIGINT NOT NULL
);

CREATE TABLE notification
(
    id                     BIGINT AUTO_INCREMENT NOT NULL,
    dtype                  VARCHAR(31)           NULL,
    user_email             VARCHAR(255)          NULL,
    date                   datetime              NULL,
    state                  INT                   NULL,
    notification_type      INT                   NULL,
    product_id             BIGINT                NULL,
    household_household_id BIGINT                NULL,
    CONSTRAINT pk_notification PRIMARY KEY (id)
);

CREATE TABLE product_type
(
    id              BIGINT AUTO_INCREMENT NOT NULL,
    dtype           VARCHAR(31)           NULL,
    barcode         VARCHAR(255)          NULL,
    name            VARCHAR(255)          NULL,
    quantity        VARCHAR(255)          NULL,
    brands          VARCHAR(255)          NULL,
    image_url       VARCHAR(255)          NULL,
    amount          BIGINT                NULL,
    expiration_date datetime              NULL,
    added_date_time datetime              NULL,
    location_id     BIGINT                NULL,
    CONSTRAINT pk_producttype PRIMARY KEY (id)
);

CREATE TABLE `role`
(
    role_id BIGINT AUTO_INCREMENT NOT NULL,
    name    VARCHAR(255)          NULL,
    CONSTRAINT pk_role PRIMARY KEY (role_id)
);

CREATE TABLE user
(
    user_id          BIGINT AUTO_INCREMENT NOT NULL,
    provider_user_id VARCHAR(255)         UNIQUE NULL,
    email            VARCHAR(255)          NULL,
    enabled          BIT                   NULL,
    password_expired BIT                   NULL,
    expired          BIT                   NULL,
    display_name     VARCHAR(255)          NULL,
    created_date     datetime              NOT NULL,
    modified_date    datetime              NULL,
    password         VARCHAR(255)          NULL,
    provider         VARCHAR(255)          NULL,
    CONSTRAINT pk_user PRIMARY KEY (user_id)
);

CREATE TABLE user_role
(
    role_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    CONSTRAINT pk_user_role PRIMARY KEY (role_id, user_id)
);

ALTER TABLE household_locations
    ADD CONSTRAINT uc_household_locations_locations UNIQUE (locations_id);

ALTER TABLE location_products
    ADD CONSTRAINT uc_location_products_products UNIQUE (products_id);

ALTER TABLE notification
    ADD CONSTRAINT FK_NOTIFICATION_ON_HOUSEHOLD_HOUSEHOLD FOREIGN KEY (household_household_id) REFERENCES household (household_id);

ALTER TABLE notification
    ADD CONSTRAINT FK_NOTIFICATION_ON_PRODUCT FOREIGN KEY (product_id) REFERENCES product_type (id);

ALTER TABLE product_type
    ADD CONSTRAINT FK_PRODUCTTYPE_ON_LOCATION FOREIGN KEY (location_id) REFERENCES location (id);

ALTER TABLE household_locations
    ADD CONSTRAINT fk_houloc_on_household FOREIGN KEY (household_household_id) REFERENCES household (household_id);

ALTER TABLE household_locations
    ADD CONSTRAINT fk_houloc_on_location FOREIGN KEY (locations_id) REFERENCES location (id);

ALTER TABLE household_users
    ADD CONSTRAINT fk_houuse_on_household FOREIGN KEY (household_household_id) REFERENCES household (household_id);

ALTER TABLE household_users
    ADD CONSTRAINT fk_houuse_on_user FOREIGN KEY (users_user_id) REFERENCES user (user_id);

ALTER TABLE location_products
    ADD CONSTRAINT fk_locpro_on_location FOREIGN KEY (location_id) REFERENCES location (id);

ALTER TABLE location_products
    ADD CONSTRAINT fk_locpro_on_product_type FOREIGN KEY (products_id) REFERENCES product_type (id);

ALTER TABLE user_role
    ADD CONSTRAINT fk_user_role_on_role FOREIGN KEY (role_id) REFERENCES `role` (role_id);

ALTER TABLE user_role
    ADD CONSTRAINT fk_user_role_on_user FOREIGN KEY (user_id) REFERENCES user (user_id);