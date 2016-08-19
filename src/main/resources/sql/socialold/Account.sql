CREATE TABLE account
(
  id serial NOT NULL,
  userid character varying,
  password character varying NOT NULL,
  firstname character varying NOT NULL,
  lastname character varying NOT NULL,
  CONSTRAINT account_pkey PRIMARY KEY (id),
  CONSTRAINT account_username_key UNIQUE (username)
)
WITH (
  OIDS=FALSE
);