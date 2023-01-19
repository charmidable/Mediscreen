use mediscreen;

DROP TABLE IF EXISTS patient;

CREATE TABLE patient
(
    id          INT         NOT NULL AUTO_INCREMENT,
    firstname   VARCHAR(12) NOT NULL,
    lastname    VARCHAR(12) NOT NULL,
    address     VARCHAR(50)         ,
    phone       VARCHAR(12)         ,
    sex         VARCHAR(6)  NOT NULL,
    birthdate   DATE        NOT NULL,
    PRIMARY KEY (id)
);