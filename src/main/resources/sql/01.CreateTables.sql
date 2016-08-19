DROP TABLE cm_userconnection;

CREATE TABLE cm_userconnection
(
  userid character varying(255) NOT NULL,
  providerid character varying(255) NOT NULL,
  provideruserid character varying(255) NOT NULL,
  rank integer NOT NULL,
  displayname character varying(255),
  profileurl character varying(512),
  imageurl character varying(512),
  accesstoken character varying(512) NOT NULL,
  secret character varying(512),
  refreshtoken character varying(512),
  expiretime bigint,
  CONSTRAINT cm_userconnection_pkey PRIMARY KEY (userid, providerid, provideruserid)

);