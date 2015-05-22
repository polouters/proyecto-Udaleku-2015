/*  
  Descripcion: Creacion de las tablas, secuencias y el trigger
  Autor: Jon, Polo y Mikel
*/

/* Eliminar Secuencias */

/* idProv_Seq */
DROP SEQUENCE idProv_Seq;

/* idMunicipio_Seq */
DROP SEQUENCE idMunicipio_Seq;

/* idCalle_Seq */ 
DROP SEQUENCE idCalle_Seq;

/* idVivienda_Seq */
DROP SEQUENCE idVivienda_Seq;

/* idDireccion_Seq */
DROP SEQUENCE idDireccion_Seq;

/* idCentro_Seq */
DROP SEQUENCE idCentro_Seq;

/* codMenor_Seq */
DROP SEQUENCE codMenor_Seq;

/* idSorteo_Seq */
DROP SEQUENCE idSorteo_Seq;

/* nSolicitud_Seq */
DROP SEQUENCE nSolicitud_Seq;

/* idIns_Seq */
DROP SEQUENCE idIns_Seq;

-- -----------------------------------------------------
-- Table Provincia
-- -----------------------------------------------------
DROP TABLE  Provincia  CASCADE CONSTRAINTS;

CREATE TABLE  Provincia (
  idProv NUMBER(4) not null,
  nombre VARCHAR(45) NOT NULL,
  PRIMARY KEY (idProv));

CREATE SEQUENCE idProv_Seq MAXVALUE 3;

-- -----------------------------------------------------
-- Table Municipio
-- -----------------------------------------------------
DROP TABLE  Municipio  cascade CONSTRAINTS;

CREATE TABLE  Municipio (
  idMunicipio NUMBER(4) NOT NULL,
  nombre VARCHAR(45) NOT NULL,
  idProv NUMBER(4) NOT NULL,
  PRIMARY KEY (idMunicipio),
  CONSTRAINT fk_Municipio_Provincia
    FOREIGN KEY (idProv)
    REFERENCES Provincia (idProv));

CREATE SEQUENCE idMunicipio_Seq MAXVALUE 300;

-- -----------------------------------------------------
-- Table Calle
-- -----------------------------------------------------
DROP TABLE  Calle cascade CONSTRAINTS ;

CREATE TABLE  Calle (
  idCalle NUMBER(4) NOT NULL,
  nombre VARCHAR(45) NOT NULL,
  idMunicipio NUMBER(4) NOT NULL,
  PRIMARY KEY (idCalle),
  CONSTRAINT fk_Calle_Municipio1
    FOREIGN KEY (idMunicipio)
    REFERENCES Municipio (idMunicipio));

CREATE SEQUENCE idCalle_Seq MAXVALUE 1000;

-- -----------------------------------------------------
-- Table Vivienda
-- -----------------------------------------------------
DROP TABLE  Vivienda  cascade CONSTRAINTS ;

CREATE TABLE  Vivienda (
  idVivienda NUMBER(4) NOT NULL,
  numero VARCHAR(4) NOT NULL,
  piso VARCHAR(3) NULL,
  letra VARCHAR(1) NULL,
  mano VARCHAR(10) NULL,
  PRIMARY KEY (idVivienda));

CREATE SEQUENCE idVivienda_Seq MAXVALUE 1000;

-- -----------------------------------------------------
-- Table Direccion
-- -----------------------------------------------------
DROP TABLE  Direccion cascade CONSTRAINTS ;

CREATE TABLE  Direccion (
  idCalle NUMBER(4) NOT NULL,
  idVivienda NUMBER(4) NOT NULL,
  cp VARCHAR(5) NOT NULL,
  PRIMARY KEY (idCalle, idVivienda),
  CONSTRAINT fk_DIR_CALLE
    FOREIGN KEY (idCalle)
    REFERENCES Calle (idCalle),
  CONSTRAINT fk_DIR_VIV
    FOREIGN KEY (idVivienda)
    REFERENCES Vivienda (idVivienda));

CREATE SEQUENCE idDireccion_Seq MAXVALUE 1000;

-- -----------------------------------------------------
-- Table Centro
-- -----------------------------------------------------
DROP TABLE  Centro cascade CONSTRAINTS ;

 CREATE TABLE  Centro (
  idCentro NUMBER(4) NOT NULL,
  nombre VARCHAR(45) NOT NULL,
  modelo VARCHAR(1) NOT NULL,
  idProv NUMBER(4) NOT NULL,
  PRIMARY KEY (idCentro),
  CONSTRAINT fk_Centro_Provincia1
    FOREIGN KEY (idProv)
    REFERENCES Provincia (idProv));

CREATE SEQUENCE idCentro_Seq MAXVALUE 100;

-- -----------------------------------------------------
-- Table Menor
-- -----------------------------------------------------
DROP TABLE  Menor cascade CONSTRAINTS ;

CREATE TABLE  Menor (
  codMenor NUMBER(4) NOT NULL,
  nombre VARCHAR(45) NOT NULL,
  ape1 VARCHAR(45) NOT NULL,
  ape2 VARCHAR(45) NOT NULL,
  sexo VARCHAR(45) NOT NULL,
  dni VARCHAR(9) NULL,
  fechaNac DATE NOT NULL,
  discapacidad VARCHAR(45) NOT NULL,
  idCalle NUMBER(4) NOT NULL,
  idVivienda NUMBER(4) NOT NULL,
  idCentro NUMBER(4) NOT NULL,
  PRIMARY KEY (codMenor),
  CONSTRAINT fk_Menor_Direccion1
    FOREIGN KEY (idCalle , idVivienda)
    REFERENCES Direccion (idCalle , idVivienda),
  CONSTRAINT fk_Menor_Centro1
    FOREIGN KEY (idCentro)
    REFERENCES Centro (idCentro));

CREATE SEQUENCE codMenor_Seq MAXVALUE 500;

CREATE OR REPLACE TRIGGER menor_tri BEFORE INSERT OR UPDATE ON Menor FOR EACH ROW
BEGIN
		IF (EXTRACT(YEAR FROM SYSDATE) - EXTRACT(YEAR FROM :new.fechaNac)) < 7 
		OR (EXTRACT(YEAR FROM SYSDATE) - EXTRACT(YEAR FROM :new.fechaNac)) > 13 THEN
		
				RAISE_APPLICATION_ERROR(-20100, 'El Menor debe ser ser mayor de 7 años o menor de 13 años.');
				
		END IF;
END diaSorteo_tri;

/

-- -----------------------------------------------------
-- Table Tutor
-- -----------------------------------------------------
DROP TABLE  Tutor cascade CONSTRAINTS ;

CREATE TABLE  Tutor (
  dni VARCHAR(9) NOT NULL,
  nombre VARCHAR(45) NOT NULL,
  ape1 VARCHAR(45) NOT NULL,
  ape2 VARCHAR(45) NOT NULL,
  tlf1 VARCHAR(45) NOT NULL,
  tlf2 VARCHAR(45) NULL,
  tlf3 VARCHAR(45) NULL,
  tlf4 VARCHAR(45) NULL,
  PRIMARY KEY (dni));

-- -----------------------------------------------------
-- Table Sorteo
-- -----------------------------------------------------
DROP TABLE  Sorteo cascade CONSTRAINTS ;

 CREATE TABLE  Sorteo (
  idSorteo NUMBER(4) NOT NULL,
  diaInicio DATE NOT NULL,
  diaFin DATE NOT NULL,
  diaSorteo DATE NOT NULL,
  PRIMARY KEY (idSorteo));

CREATE SEQUENCE idSorteo_Seq MAXVALUE 10;

-- -----------------------------------------------------
-- Table Solicitud
-- -----------------------------------------------------
DROP TABLE  Solicitud  cascade CONSTRAINTS;

CREATE TABLE  Solicitud (
  nSolicitud NUMBER(4) NOT NULL,
  fecha DATE NULL,
  hora DATE NULL,
  situacion VARCHAR(35),
  nOrden NUMBER(4),
  idSorteo NUMBER(4) NOT NULL,
  PRIMARY KEY (nSolicitud),
  CONSTRAINT fk_Solicitud_Sorteo1
    FOREIGN KEY (idSorteo) 
    REFERENCES Sorteo (idSorteo) );

CREATE SEQUENCE nSolicitud_Seq MAXVALUE 1000;

-- -----------------------------------------------------
-- Table Inscripcion
-- -----------------------------------------------------
DROP TABLE  Inscripcion cascade CONSTRAINTS ;

CREATE TABLE  Inscripcion (
  idIns NUMBER(4) NOT NULL,
  nSolicitud NUMBER(4) NOT NULL,
  dni VARCHAR(9) NOT NULL,
  codMenor NUMBER(4) NOT NULL,
  PRIMARY KEY (idIns),
  CONSTRAINT fk_Inscripcion_Solicitud1
    FOREIGN KEY (nSolicitud)
    REFERENCES Solicitud (nSolicitud) ON DELETE CASCADE,
  CONSTRAINT fk_Inscripcion_Tutor1
    FOREIGN KEY (dni)
    REFERENCES Tutor (dni) ON DELETE CASCADE,
  CONSTRAINT fk_Inscripcion_Menor1
    FOREIGN KEY (codMenor)
    REFERENCES Menor (codMenor) ON DELETE CASCADE);

CREATE SEQUENCE idIns_Seq MAXVALUE 1000;