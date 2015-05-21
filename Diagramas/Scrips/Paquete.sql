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
				SELECT DISTINCT I.nSolicitud, S.situacion, M.nombre, M.ape1, M.ape2, M.fechaNac, S.nOrden, S.fecha
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
		v_fecha Solicitud.fecha%TYPE;
		v_hora Solicitud.hora%TYPE;
		
	BEGIN 
	
		SELECT MIN(nSolicitud), MAX(nSolicitud) INTO v_min, v_max
		FROM solicitud;
		
		SELECT DBMS_RANDOM.VALUE(v_min,v_max) INTO v_ganador
		FROM dual;
		
		SELECT DBMS_RANDOM.VALUE(3,9) INTO v_cadencia
		FROM dual;

		v_cont := v_ganador; 
		
		WHILE v_Seq <= v_max LOOP
		
			IF v_cont > v_max THEN 
				v_cont := v_cont - v_max;
			END IF;
			
			SELECT nOrden INTO v_nOrden
			FROM solicitud
			WHERE nSolicitud = v_cont;
			
			IF v_nOrden IS NULL THEN
		
				UPDATE Solicitud
				SET nOrden = v_Seq, situacion = 'Adjudicada' ,fecha = to_date(v_fecha,'dd/mm/yyyy'), hora = to_date(v_hora , 'hh/mi/ss')
				WHERE nSolicitud = v_cont;
				
				v_Seq := v_Seq +1;
				v_cont := v_cont + v_cadencia;
			ELSE
				v_cont := v_cont + 1;
			END IF;
				
		END LOOP;
	
	END realizarSorteo;
		
END paquete;