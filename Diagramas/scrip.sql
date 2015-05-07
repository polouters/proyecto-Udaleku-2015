


DROP TABLE  Provincia  CASCADE CONSTRAINT;

CREATE TABLE  Provincia (
  idProv INT not null,
  nombre VARCHAR(45) NOT NULL,
  PRIMARY KEY (idProv));


-- -----------------------------------------------------
-- Table Municipio
-- -----------------------------------------------------
DROP TABLE  Municipio  cascade CONSTRAINT;

CREATE TABLE  Municipio (
  idMunicipio INT NOT NULL,
  nombre VARCHAR(45) NOT NULL,
  idProv INT NOT NULL,
  PRIMARY KEY (idMunicipio),
  CONSTRAINT fk_Municipio_Provincia
    FOREIGN KEY (idProv)
    REFERENCES Provincia (idProv));


-- -----------------------------------------------------
-- Table Calle
-- -----------------------------------------------------
DROP TABLE  Calle cascade CONSTRAINT ;

CREATE TABLE  Calle (
  idCalle INT NOT NULL,
  nombre VARCHAR(45) NOT NULL,
  idMunicipio INT NOT NULL,
  PRIMARY KEY (idCalle),
  CONSTRAINT fk_Calle_Municipio1
    FOREIGN KEY (idMunicipio)
    REFERENCES Municipio (idMunicipio));


-- -----------------------------------------------------
-- Table Vivienda
-- -----------------------------------------------------
DROP TABLE  Vivienda  cascade CONSTRAINT ;

CREATE TABLE  Vivienda (
  idVivienda INT NOT NULL,
  numero VARCHAR(4) NOT NULL,
  piso VARCHAR(3) NULL,
  letra VARCHAR(1) NULL,
  mano VARCHAR(10) NULL,
  PRIMARY KEY (idVivienda));


-- -----------------------------------------------------
-- Table Direcccion
-- -----------------------------------------------------
DROP TABLE  Direcccion cascade CONSTRAINT ;

CREATE TABLE  Direcccion (
  idCalle INT NOT NULL,
  idVivienda INT NOT NULL,
  cp VARCHAR(5) NOT NULL,
  PRIMARY KEY (idCalle, idVivienda),
  CONSTRAINT fk_DIR_CALLE
    FOREIGN KEY (idCalle)
    REFERENCES Calle (idCalle),
  CONSTRAINT fk_DIR_VIV
    FOREIGN KEY (idVivienda)
    REFERENCES Vivienda (idVivienda));


-- -----------------------------------------------------
-- Table Centro
-- -----------------------------------------------------
DROP TABLE  Centro cascade CONSTRAINT ;

 CREATE TABLE  Centro (
  idCentro INT NOT NULL,
  nombre VARCHAR(45) NOT NULL,
  modelo VARCHAR(1) NOT NULL,
  idProv INT NOT NULL,
  PRIMARY KEY (idCentro),
  CONSTRAINT fk_Centro_Provincia1
    FOREIGN KEY (idProv)
    REFERENCES Provincia (idProv));


-- -----------------------------------------------------
-- Table Menor
-- -----------------------------------------------------
DROP TABLE  Menor cascade CONSTRAINT ;

CREATE TABLE  Menor (
  codMenor INT NOT NULL,
  nombre VARCHAR(45) NOT NULL,
  ape1 VARCHAR(45) NOT NULL,
  ape2 VARCHAR(45) NOT NULL,
  sexo VARCHAR(45) NOT NULL,
  dni VARCHAR(9) NULL,
  fechaNac DATE NOT NULL,
  discapacidad VARCHAR(45) NOT NULL,
  idCalle INT NOT NULL,
  idVivienda INT NOT NULL,
  idCentro INT NOT NULL,
  PRIMARY KEY (codMenor),
  CONSTRAINT fk_Menor_Direcccion1
    FOREIGN KEY (idCalle , idVivienda)
    REFERENCES Direcccion (idCalle , idVivienda),
  CONSTRAINT fk_Menor_Centro1
    FOREIGN KEY (idCentro)
    REFERENCES Centro (idCentro));


-- -----------------------------------------------------
-- Table Tutor
-- -----------------------------------------------------
DROP TABLE  Tutor cascade CONSTRAINT ;

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
DROP TABLE  Sorteo cascade CONSTRAINT ;

 CREATE TABLE  Sorteo (
  idSorteo INT NOT NULL,
  diaIninio DATE NOT NULL,
  diaFin DATE NOT NULL,
  diaSorteo DATE NOT NULL,
  PRIMARY KEY (idSorteo));


-- -----------------------------------------------------
-- Table Solicitud
-- -----------------------------------------------------
DROP TABLE  Solicitud  cascade CONSTRAINT;

CREATE TABLE  Solicitud (
  nSolicitud INT NOT NULL,
  fecha DATE NULL,
  hora DATE NULL,
  idSorteo INT NOT NULL,
  PRIMARY KEY (nSolicitud),
  CONSTRAINT fk_Solicitud_Sorteo1
    FOREIGN KEY (idSorteo)
    REFERENCES Sorteo (idSorteo));


-- -----------------------------------------------------
-- Table Inscripcion
-- -----------------------------------------------------
DROP TABLE  Inscripcion cascade CONSTRAINT ;

CREATE TABLE  Inscripcion (
  idIns INT NOT NULL,
  nSolicitud INT NOT NULL,
  dni VARCHAR(9) NOT NULL,
  codMenor INT NOT NULL,
  PRIMARY KEY (idIns),
  CONSTRAINT fk_Inscripcion_Solicitud1
    FOREIGN KEY (nSolicitud)
    REFERENCES Solicitud (nSolicitud),
  CONSTRAINT fk_Inscripcion_Tutor1
    FOREIGN KEY (dni)
    REFERENCES Tutor (dni),
  CONSTRAINT fk_Inscripcion_Menor1
    FOREIGN KEY (codMenor)
    REFERENCES Menor (codMenor));



