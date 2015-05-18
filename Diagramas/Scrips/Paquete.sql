/* Creacion del Paquete */
CREATE OR REPLACE PACKAGE paquete IS
	
	TYPE Paticipante_cusor IS REF CURSOR;

	PROCEDURE consultaSolicitud(
		v_dniTutor IN Tutor.dni%TYPE,
	 	v_fechaNac IN Menor.fechaNac%TYPE,
	 	PaticipanteC OUT Paticipante_cusor
	 	);

END paquete;

/

CREATE OR REPLACE PACKAGE BODY paquete IS

	PROCEDURE consultaSolicitud
	(v_dniTutor IN Tutor.dni%TYPE,
	 v_fechaNac IN Menor.fechaNac%TYPE,
	 PaticipanteC OUT Paticipante_cusor
	)IS

		CURSOR nSolicitud_cursor IS
			SELECT nSolicitud 
			FROM Inscripcion
			WHERE dni = v_dniTutor AND codMenor = ALL (SELECT codMenor
												       FROM Menor
												       WHERE fechaNac = V_fechaNac);

	BEGIN 

		FOR nSol_reg IN nSolicitud_cursor LOOP

			OPEN PaticipanteC FOR
				SELECT S.nSolicitud, S.situacion, M.nombre, M.ape1, M.ape2, M.fechaNac, S.nOrden, S.fecha
				FROM Solicitud S, Inscripcion I, Menor M
				WHERE I.nSolicitud = nSol_reg.nSolicitud AND I.codMenor = M.codMenor;

		END LOOP;	

	END consultaSolicitud;

END paquete;