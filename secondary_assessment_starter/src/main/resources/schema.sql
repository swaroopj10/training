DROP TABLE VESSELS;
DROP TABLE SEA_PORTS;

CREATE TABLE SEA_PORTS (
    ID INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 1) PRIMARY KEY,
	port_name VARCHAR(21),
	country VARCHAR(13),
	location VARCHAR(16),
	latitude VARCHAR(50),
	longitude VARCHAR(50)
);


CREATE TABLE VESSELS (
    ID INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 1) PRIMARY KEY,
	vessel_name VARCHAR(50),
	vessel_type VARCHAR(11),
	vessel_country VARCHAR(50),
	vessel_port INTEGER,
    CONSTRAINT FK_VESSEL_PORT_SEA_PORT FOREIGN KEY (vessel_port) REFERENCES SEA_PORTS(ID)
);