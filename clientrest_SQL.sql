CREATE TABLE client (
	id bigserial NOT NULL, 
	fistName character varying(200), 
	lastName character varying(200),
	email character varying(200),
	phone character varying(200),  
	CONSTRAINT noticia_pk PRIMARY KEY (id) 
);
