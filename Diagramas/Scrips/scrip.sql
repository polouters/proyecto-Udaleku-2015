


DROP TABLE  Provincia  CASCADE CONSTRAINT;

CREATE TABLE  Provincia (
  idProv NUMBER(4) not null,
  nombre VARCHAR(45) NOT NULL,
  PRIMARY KEY (idProv));

CREATE SEQUENCE idProv_Seq MAXVALUE 3;

INSERT INTO Provincia VALUES (idProv_Seq.nextval,'Alava/Araba');
INSERT INTO Provincia VALUES (idProv_Seq.nextval,'Vizcaya/Bizkaia');
INSERT INTO Provincia VALUES (idProv_Seq.nextval,'Guipuzcoa/Gipuzkua');

-- -----------------------------------------------------
-- Table Municipio
-- -----------------------------------------------------
DROP TABLE  Municipio  cascade CONSTRAINT;

CREATE TABLE  Municipio (
  idMunicipio NUMBER(4) NOT NULL,
  nombre VARCHAR(45) NOT NULL,
  idProv NUMBER(4) NOT NULL,
  PRIMARY KEY (idMunicipio),
  CONSTRAINT fk_Municipio_Provincia
    FOREIGN KEY (idProv)
    REFERENCES Provincia (idProv));

CREATE SEQUENCE idMunicipio_Seq MAXVALUE 300;

/* Municipios de Araba*/
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Alegría-Dulantzi', 1);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Amurrio', 1);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Añana', 1);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Aramaio', 1);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Armiñón', 1);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Arraia-Maeztu', 1);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Arrazua-Ubarrundia', 1);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Artziniega', 1);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Asparrena', 1);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Ayala/Aiara', 1);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Baños de Ebro/Mañueta', 1);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Barrundia', 1);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Berantevilla', 1);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Bernedo', 1);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Campezo/Kanpezu', 1);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Elburgo/Burgelu', 1);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Elciego', 1);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Elvillar/Bilar', 1);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Erriberagoitia/Ribera Alta', 1);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Harana/Valle de Arana', 1);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Iruña Oka/Iruña de Oca', 1);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Iruraiz-Gauna', 1);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Kripan', 1);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Kuartango', 1);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Labastida/Bastida', 1);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Lagrán', 1);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Laguardia', 1);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Lanciego/Lantziego', 1);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Lantarón', 1);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Lapuebla de Labarca', 1);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Laudio/Llodio', 1);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Legutio', 1);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Leza', 1);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Moreda de Álava/Moreda Araba', 1);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Navaridas', 1);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Okondo', 1);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Oyón-Oion', 1);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Peñacerrada-Urizaharra', 1);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Ribera Baja/Erribera Beitia', 1);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Salvatierra/Agurain', 1);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Samaniego', 1);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'San Millán/Donemiliaga', 1);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Urkabustaiz', 1);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Valdegovía/Gaubea', 1);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Villabuena de Álava/Eskuernaga', 1);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Vitoria-Gasteiz', 1);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Yécora/Iekora', 1);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Zalduondo', 1);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Zambrana', 1);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Zigoitia', 1);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Zuia', 1);

/* Municipios de Bizkaia */
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Ziortza-Bolibar', 2);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Zierbena', 2);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Zeberio', 2);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Zeanuri', 2);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Zaratamo', 2);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Zamudio', 2);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Zalla', 2);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Zaldibar', 2);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Valle de Trápaga-Trapagaran', 2);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Urduña/Orduña', 2);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Urduliz', 2);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Ugao-Miraballes', 2);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Ubide', 2);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Trucios-Turtzioz', 2);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Sukarrieta', 2);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Sopuerta', 2);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Sopelana', 2);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Sondika', 2);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Sestao', 2);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Santurtzi', 2);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Portugalete', 2);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Plentzia', 2);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Otxandio', 2);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Ortuella', 2);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Orozko', 2);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Ondarroa', 2);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Nabarniz', 2);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Muxika', 2);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Muskiz', 2);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Murueta', 2);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Munitibar-Arbatzegi Gerrikaitz', 2);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Mungia', 2);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Mundaka', 2);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Morga', 2);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Mendexa', 2);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Mendata', 2);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Meñaka', 2);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Maruri-Jatabe', 2);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Markina-Xemein', 2);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Mañaria', 2);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Mallabia', 2);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Loiu', 2);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Lezama', 2);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Lemoiz', 2);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Lemoa', 2);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Lekeitio', 2);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Leioa', 2);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Laukiz', 2);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Larrabetzu', 2);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Lanestosa', 2);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Kortezubi', 2);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Karrantza Harana/Valle de Carranza', 2);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Izurtza', 2);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Iurreta', 2);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Ispaster', 2);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Igorre', 2);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Ibarrangelu', 2);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Güeñes', 2);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Gorliz', 2);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Gordexola', 2);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Gizaburuaga', 2);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Getxo', 2);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Gernika-Lumo', 2);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Gautegiz Arteaga', 2);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Gatika', 2);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Garai', 2);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Gamiz-Fika', 2);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Galdames', 2);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Galdakao', 2);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Fruiz', 2);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Forua', 2);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Etxebarria', 2);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Etxebarri', 2);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Errigoiti', 2);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Ermua', 2);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Ereño', 2);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Erandio', 2);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Elorrio', 2);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Elantxobe', 2);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Ea', 2);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Durango', 2);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Dima', 2);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Derio', 2);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Busturia', 2);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Bilbao', 2);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Berriz', 2);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Berriatua', 2);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Bermeo', 2);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Berango', 2);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Bedia', 2);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Basauri', 2);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Barrika', 2);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Barakaldo', 2);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Balmaseda', 2);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Bakio', 2);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Aulesti', 2);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Atxondo', 2);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Artzentales', 2);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Artea', 2);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Arrigorriaga', 2);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Arrieta', 2);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Arratzu', 2);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Arrankudiaga', 2);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Areatza', 2);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Arantzazu', 2);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Arakaldo', 2);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Amoroto', 2);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Amorebieta-Etxano', 2);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Alonsotegi', 2);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Ajangiz', 2);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Abanto y Ciérvana-Abanto Zierbena', 2);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Abadiño', 2);

/* Municipio de Gipuzkua */
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Abaltzisketa', 3);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Aduna', 3);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Aia', 3);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Aizarnazabal', 3);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Albiztur', 3);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Alegia', 3);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Alkiza', 3);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Altzaga', 3);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Altzo', 3);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Amezketa', 3);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Andoain', 3);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Anoeta', 3);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Antzuola', 3);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Arama', 3);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Aretxabaleta', 3);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Arrasate/Mondragón', 3);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Asteasu', 3);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Astigarraga', 3);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Ataun', 3);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Azkoitia', 3);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Azpeitia', 3);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Baliarrain', 3);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Beasain', 3);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Beizama', 3);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Belauntza', 3);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Berastegi', 3);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Bergara', 3);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Berrobi', 3);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Bidegoian', 3);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Deba', 3);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Donostia-San Sebastián', 3);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Eibar', 3);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Elduain', 3);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Elgeta', 3);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Elgoibar', 3);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Errenteria', 3);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Errezil', 3);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Eskoriatza', 3);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Ezkio-Itsaso', 3);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Gabiria', 3);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Gaintza', 3);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Gaztelu', 3);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Getaria', 3);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Hernani', 3);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Hernialde', 3);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Hondarribia', 3);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Ibarra', 3);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Idiazabal', 3);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Ikaztegieta', 3);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Irun', 3);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Irura', 3);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Itsasondo', 3);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Larraul', 3);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Lasarte-Oria', 3);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Lazkao', 3);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Leaburu', 3);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Legazpi', 3);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Legorreta', 3);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Leintz-Gatzaga', 3);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Lezo', 3);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Lizartza', 3);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Mendaro', 3);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Mutiloa', 3);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Mutriku', 3);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Oiartzun', 3);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Olaberria', 3);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Oñati', 3);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Ordizia', 3);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Orendain', 3);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Orexa', 3);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Orio', 3);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Ormaiztegi', 3);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Pasaia', 3);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Segura', 3);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Soraluze/Placencia de las Armas', 3);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Tolosa', 3);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Urnieta', 3);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Urretxu', 3);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Usurbil', 3);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Villabona', 3);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Zaldibia', 3);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Zarautz', 3);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Zegama', 3);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Zerain', 3);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Zestoa', 3);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Zizurkil', 3);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Zumaia', 3);
INSERT INTO Municipio VALUES (idMunicipio_Seq.nextval,'Zumarraga', 3);

-- -----------------------------------------------------
-- Table Calle
-- -----------------------------------------------------
DROP TABLE  Calle cascade CONSTRAINT ;

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
DROP TABLE  Vivienda  cascade CONSTRAINT ;

CREATE TABLE  Vivienda (
  idVivienda NUMBER(4) NOT NULL,
  numero VARCHAR(4) NOT NULL,
  piso VARCHAR(3) NULL,
  letra VARCHAR(1) NULL,
  mano VARCHAR(10) NULL,
  PRIMARY KEY (idVivienda));

CREATE SEQUENCE idVivienda_Seq MAXVALUE 1000;

-- -----------------------------------------------------
-- Table Direcccion
-- -----------------------------------------------------
DROP TABLE  Direcccion cascade CONSTRAINT ;

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
DROP TABLE  Centro cascade CONSTRAINT ;

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
DROP TABLE  Menor cascade CONSTRAINT ;

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
  CONSTRAINT fk_Menor_Direcccion1
    FOREIGN KEY (idCalle , idVivienda)
    REFERENCES Direcccion (idCalle , idVivienda),
  CONSTRAINT fk_Menor_Centro1
    FOREIGN KEY (idCentro)
    REFERENCES Centro (idCentro));

CREATE SEQUENCE codMenor_Seq MAXVALUE 500;

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
  idSorteo NUMBER(4) NOT NULL,
  diaIninio DATE NOT NULL,
  diaFin DATE NOT NULL,
  diaSorteo DATE NOT NULL,
  PRIMARY KEY (idSorteo));

CREATE SEQUENCE idSorteo_Seq MAXVALUE 10;

-- -----------------------------------------------------
-- Table Solicitud
-- -----------------------------------------------------
DROP TABLE  Solicitud  cascade CONSTRAINT;

CREATE TABLE  Solicitud (
  nSolicitud NUMBER(4) NOT NULL,
  fecha DATE NULL,
  hora DATE NULL,
  idSorteo NUMBER(4) NOT NULL,
  PRIMARY KEY (nSolicitud),
  CONSTRAINT fk_Solicitud_Sorteo1
    FOREIGN KEY (idSorteo)
    REFERENCES Sorteo (idSorteo));

CREATE SEQUENCE nSolicitud_Seq MAXVALUE 1000;

-- -----------------------------------------------------
-- Table Inscripcion
-- -----------------------------------------------------
DROP TABLE  Inscripcion cascade CONSTRAINT ;

CREATE TABLE  Inscripcion (
  idIns NUMBER(4) NOT NULL,
  nSolicitud NUMBER(4) NOT NULL,
  dni VARCHAR(9) NOT NULL,
  codMenor NUMBER(4) NOT NULL,
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

CREATE SEQUENCE idIns_Seq MAXVALUE 1000;


