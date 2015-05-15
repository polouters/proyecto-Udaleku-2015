/* Creacion del Paquete */
CREATE OR REPLACE PACKAGE paquete IS
	
	TYPE Paticipante_cusor IS REF CURSOR;
	TYPE Sorteo_cursor IS REF CURSOR;

	PROCEDURE consultaSolicitud(
		v_dniTutor IN Tutor.dni%TYPE,
	 	v_fechaNac IN Menor.fechaNac%TYPE,
	 	PaticipanteC OUT Paticipante_cusor,
	 	SorteoC OUT Sorteo_cursor
	 	/*
	 	v_nOrden OUT Sorteo.nOrden%TYPE,
	 	v_fecha OUT Sorteo.fecha%TYPE,
	 	v_hora OUT Sorte.hora%TYPE
	 	*/
	 	);

END paquete;

/

CREATE OR REPLACE PACKAGE BODY paquete IS

	PROCEDURE consultaSolicitud
	(v_dniTutor IN Tutor.dni%TYPE,
	 v_fechaNac IN Menor.fechaNac%TYPE,
	 PaticipanteC OUT Paticipante_cusor,
	 SorteoC OUT Sorteo_cursor
	 /*
	 v_nOrden OUT Sorteo.nOrden%TYPE,
	 v_fecha OUT Sorteo.fecha%TYPE,
	 v_hora OUT Sorte.hora%TYPE
	 */
	) IS

		CURSOR nSolicitud_cursor IS
			SELECT nSolicitud 
			FROM Inscripcion
			WHERE dni = v_dniTutor AND codMenor = ALL (SELECT codMenor
												       FROM Menor
												       WHERE fechaNac = V_fechaNac);

		v_diaFin Sorteo.diaFin%TYPE;

	BEGIN 

		FOR nSol_reg IN nSolicitud_cursor LOOP

			SELECT Sr.diaFin INTO v_diaFin
			FROM Sorteo Sr, Solicitud Sl
			WHERE Sl.nSolicitud = nSol_reg.nSolicitud;

			IF SYSDATE > v_diaFin THEN
				/*
				SELECT nOrden, fecha, hora INTO v_nOrden, v_fecha, v_hora
				FROM Solicitud
				WHERE nSolicitud = nSol_reg.nSolicitud;
				*/

				OPEN SorteoC FOR
					SELECT nOrden, fecha, hora
					FROM Solicitud
					WHERE nSolicitud = nSol_reg.nSolicitud;

			END IF;

			OPEN PaticipanteC FOR
				SELECT S.nSolicitud, S.situacion, I.idIns, M.codMenor, M.nombre, M.ape1, M.ape2, M.fechaNac
				FROM Solicitud S, Inscripcion I, Menor M
				WHERE I.nSolicitud = nSol_reg.nSolicitud AND I.codMenor = M.codMenor;

		END LOOP;	

	END consultaSolicitud;

END paquete;