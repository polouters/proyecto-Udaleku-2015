/* Creacion del Paquete */
CREATE OR REPLACE PACKAGE paquete IS
	/*Declarar el cursor usado en el procedimiento consultaSolicitud*/
	TYPE Paticipante_cusor IS REF CURSOR;
	/*Declarar el procedimiento consultaSolicitud*/
	PROCEDURE consultaSolicitud(
		v_dniTutor IN Tutor.dni%TYPE,
	 	v_fechaNac IN Menor.fechaNac%TYPE,
	 	PaticipanteC OUT Paticipante_cusor
	 	);
	
	/*Declarar el procedimiento realizarSorteo*/
	PROCEDURE realizarSorteo;
	
	/* Declarar el procedimiento InsertVivienda */
	PROCEDURE InsertVivienda(
		v_Numero IN vivienda.numero%TYPE,
		v_piso IN vivienda.piso%TYPE,
		v_letra IN vivienda.letra%TYPE,
		v_mano IN vivienda.mano%TYPE,
		v_id OUT vivienda.idvivienda%TYPE
	);
	/* Declarar el procedimiento InsertarMenor */
	
	PROCEDURE InsertarMenor(
		v_nombre IN menor.nombre%TYPE,
		v_ape1 IN menor.ape1%TYPE,
		v_ape2 IN menor.ape2%TYPE,
		v_sexo IN menor.sexo%TYPE,
		v_dni IN menor.dni%TYPE,
		v_fecha IN menor.fechaNac%TYPE,
		v_discapacidad IN menor.discapacidad%TYPE,
		v_idcalle IN menor.idcalle%TYPE,
		v_idvivienda IN menor.idvivienda%TYPE,
		v_idcentro IN menor.idcentro%TYPE,
		v_codMenor OUT menor.codMenor%TYPE
	);
	/* Declarar el procedimiento InsertarInscripcion */
	
	PROCEDURE InsertarInscripcion(
		v_idSolicitud IN inscripcion.nSolicitud%TYPE,
		v_dniTutor IN inscripcion.dni%TYPE,
		v_codMenor IN inscripcion.codMenor%TYPE
	);
	
	/*Declarar el procedimiento InsertarSolicitud*/
	
	PROCEDURE InsertarSolicitud(
	v_idSolicitud OUT Solicitud.nSolicitud%TYPE
	);
	
END paquete;

/

CREATE OR REPLACE PACKAGE BODY paquete IS

	/*Procedimiento consultaSolicitud*/
	PROCEDURE consultaSolicitud
	(v_dniTutor IN Tutor.dni%TYPE,
	 v_fechaNac IN Menor.fechaNac%TYPE,
	 PaticipanteC OUT Paticipante_cusor
	)IS

		CURSOR nSolicitud_cursor IS
			SELECT nSolicitud 
			FROM Inscripcion
			WHERE dni = v_dniTutor AND codMenor = ANY (SELECT codMenor
												       FROM Menor
												       WHERE fechaNac = V_fechaNac);

	BEGIN 

		FOR nSol_reg IN nSolicitud_cursor LOOP
	
			OPEN PaticipanteC FOR
				SELECT DISTINCT I.nSolicitud, S.situacion, M.nombre, M.ape1, M.ape2, M.fechaNac, S.nOrden, S.fecha,to_char(S.hora,'hh24:mi:ss')
				FROM Solicitud S, Inscripcion I,Menor M
				WHERE I.nsolicitud = nSol_reg.nSolicitud AND I.nSolicitud = S.nSolicitud AND I.codMenor = M.codMenor;

		END LOOP;	

	END consultaSolicitud;
	
	/*Procedimiento realizarSorteo*/
	PROCEDURE realizarSorteo 
	IS
		v_ganador Solicitud.nSolicitud%TYPE;
		v_min Solicitud.nSolicitud%TYPE;
		v_max Solicitud.nSolicitud%TYPE;
		v_nOrden Solicitud.nOrden%TYPE;
		v_cadencia NUMBER(1);
		v_Seq NUMBER(5) := 1;
		v_cont NUMBER(5);
		v_fecha Solicitud.fecha%TYPE := SYSDATE +1;
		v_hora Solicitud.hora%TYPE := to_date('01/01/0001 08:00:00','dd/mm/yyyy hh24:mi:ss');
		v_Tiempo NUMBER(3):=1;
		
	BEGIN 
	
		SELECT MIN(nSolicitud), MAX(nSolicitud) INTO v_min, v_max
		FROM solicitud;
		
		SELECT DBMS_RANDOM.VALUE(v_min,v_max) INTO v_ganador
		FROM dual;
		
		SELECT DBMS_RANDOM.VALUE(3,9) INTO v_cadencia
		FROM dual;

		v_cont := v_ganador; 
		
		SELECT nOrden INTO v_nOrden    
        FROM solicitud
        WHERE nSolicitud = v_cont;
        
        IF v_nOrden IS NOT NULL THEN 
            RAISE_APPLICATION_ERROR(-20001,'El sorteo ya se a realizado');
        END IF;
		
		WHILE v_Seq <= v_max LOOP
		
			IF v_cont > v_max THEN 
				v_cont := v_cont - v_max;
			END IF;
			
			SELECT nOrden INTO v_nOrden
			FROM solicitud
			WHERE nSolicitud = v_cont;
			
			IF v_nOrden IS NULL THEN
		
				UPDATE Solicitud
				SET nOrden = v_Seq, situacion = 'Adjudicada' ,fecha = v_fecha, hora = v_hora
				WHERE nSolicitud = v_cont;
				
				v_Seq := v_Seq +1;
				v_cont := v_cont + v_cadencia;
				v_hora := v_hora + 15/1440;
				
				IF v_tiempo > 16 THEN
					v_fecha := v_fecha + 1;
					v_hora := to_date('01/01/0001 08:00:00','dd/mm/yyyy hh24:mi:ss');
					v_Tiempo:=0;
				END IF;
				
				v_tiempo := v_Tiempo + 1;			
			ELSE
				v_cont := v_cont + 1;
			END IF;
				
		END LOOP;
	
	END realizarSorteo;
	
	/* Procedimiento para Insertar una vivienda */
	
	PROCEDURE InsertVivienda(
		v_Numero IN vivienda.numero%TYPE,
		v_piso IN vivienda.piso%TYPE,
		v_letra IN vivienda.letra%TYPE,
		v_mano IN vivienda.mano%TYPE,
		v_id OUT vivienda.idvivienda%TYPE
	)IS
		
	BEGIN
	
		INSERT INTO vivienda (idvivienda,numero,piso,letra,mano)
		VALUES(idVivienda_Seq.nextval,
			   v_Numero,
			   v_piso,
			   v_letra,
			   v_mano
				);
				
		SELECT idVivienda_Seq.CURRVAL INTO v_id FROM DUAL;
		
	
	END InsertVivienda;
	
	/* Procedimiento para Insertar a un menor */
	
	PROCEDURE InsertarMenor(
		v_nombre IN menor.nombre%TYPE,
		v_ape1 IN menor.ape1%TYPE,
		v_ape2 IN menor.ape2%TYPE,
		v_sexo IN menor.sexo%TYPE,
		v_dni IN menor.dni%TYPE,
		v_fecha IN menor.fechaNac%TYPE,
		v_discapacidad IN menor.discapacidad%TYPE,
		v_idcalle IN menor.idcalle%TYPE,
		v_idvivienda IN menor.idvivienda%TYPE,
		v_idcentro IN menor.idcentro%TYPE,
		v_codMenor OUT menor.codMenor%TYPE
	)IS
	
	BEGIN
	
		INSERT INTO menor VALUES(
		codMenor_Seq.nextval,
		v_nombre,
		v_ape1,
		v_ape2,
		v_sexo,
		v_dni,
		v_fecha,
		v_discapacidad,
		v_idcalle,
		v_idvivienda,
		v_idcentro
		);
		
		
		SELECT codMenor_Seq.CURRVAL INTO v_codMenor FROM DUAL;
		
	END InsertarMenor;
	
	
	/* Procedimiento para insertar la inscripcion */
	
	PROCEDURE InsertarInscripcion(
		v_idSolicitud IN inscripcion.nSolicitud%TYPE,
		v_dniTutor IN inscripcion.dni%TYPE,
		v_codMenor IN inscripcion.codMenor%TYPE
	)IS
	
	BEGIN
	
		INSERT INTO inscripcion
		VALUES(
		idIns_Seq.NEXTVAL,
		v_idSolicitud,
		v_dniTutor,
		v_codMenor
		);
		
		
	END InsertarInscripcion;
	
	/* Procedimiento para insertar una solicitud */

	PROCEDURE InsertarSolicitud(
	v_idSolicitud OUT Solicitud.nSolicitud%TYPE
	)IS
	
	BEGIN
		
		
		INSERT INTO solicitud(NSOLICITUD,IDSORTEO)
		VALUES(
		nSolicitud_Seq.NEXTVAL,
		1
		);
		
		SELECT nSolicitud_Seq.CURRVAL INTO v_idSolicitud FROM DUAL;
	
	END InsertarSolicitud;
	
END paquete;