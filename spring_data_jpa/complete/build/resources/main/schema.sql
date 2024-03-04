CREATE SEQUENCE IF NOT EXISTS SEQ_GEN_SEQUENCE;

CREATE TABLE IF NOT EXISTS LABORDER(
                      ID int not null AUTO_INCREMENT,
                      description varchar(100) not null,
                      PRIMARY KEY ( ID )
);


CREATE TABLE IF NOT EXISTS  LABORDERREPORT(
                          ID int not null AUTO_INCREMENT,
                          LABORDER_ID int not null,
                          PRIMARY KEY ( ID )
);

CREATE TABLE IF NOT EXISTS LABORDERDETAIL(
                                       ID int not null AUTO_INCREMENT,
                                       description varchar(100),
                                       LABORDER_ID int not null,
                                       PRIMARY KEY ( ID )
);

CREATE TABLE IF NOT EXISTS SUBDETAIL(
                                    ID int not null AUTO_INCREMENT,
                                    LABORDERDETAIL_ID int not null,
                                    PRIMARY KEY ( ID )
);
