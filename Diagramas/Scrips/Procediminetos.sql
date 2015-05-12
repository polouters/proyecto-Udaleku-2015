/* Procedimiento Consulta */

CREATE OR REPLACE PROCEDURE consultaSolicitud
	(v_dniTutor Tutor.dni%TYPE,
	 V_fechaNac Menor.fechaNac.%TYPE) 
IS
	TYPE particimantes_reg IS RECORD (
		codMenor Menor.codMenor%TYPE,
		nombre Menor.nombre%TYPE,
		ape1 Menor.ape1%TYPE,
		ape2 Menor.ape2%TYPE,
		fechaNac Menor.fechaNac%TYPE);

	TYPE particimantes IS TABLE OF particimantes_reg INDEX BY BINARY_INTEGER;

	TYPE solicitud_reg IS RECORD (
		nSolicitud Solictud.nSolicitud%TYPE,
		situacion Solictud.situacion%TYPE,
		nOrden Solictud.nOrden%TYPE,
		fecha Solictud.fecha%TYPE,
		hora Solictud.hora%TYPE,
		particimantesList particimantes
		);

	TYPE solictudes IS TABLE OF solicitud_reg INDEX BY BINARY_INTEGER;
	solictudList solictudes;

	v_diaFin Sorteo.diaFin%TYPE;
	x_cont NUMBER(1) := 1;
	y_cont NUMBER(1) := 1;

BEGIN
	
	WHILE x_cont <4 LOOP

		SELECT nSolicitud INTO solictudList(x_cont).nSolicitud
		FROM Inscripcion
		WHERE dni = v_dniTutor AND codMenor = ALL (SELECT codMenor
												   FROM Menor
												   WHERE fechaNac = V_fechaNac;);

			WHILE y_cont <3 LOOP

				SELECT codMenor INTO solictudList(x_cont).particimantesList(y_cont).codMenor
				FROM Inscripcion
				WHERE nSolicitud = solictudList(x_cont).nSolicitud;

				SELECT nombre, ape1, ape2, fechaNac INTO solictudList(x_cont).particimantesList(y_cont).nombre,
														 solictudList(x_cont).particimantesList(y_cont).ape1, 
														 solictudList(x_cont).particimantesList(y_cont).ape2,
														 solictudList(x_cont).particimantesList(y_cont).fechaNac
				FROM Menor
				WHERE codMenor = solictudList(x_cont).particimantesList(y_cont).codMenor;

				y_cont := y_cont +1;

			END LOOP;

		SELECT situacion INTO solictudList(x_cont).situacion
		FROM Solictud
		WHERE nSolicitud = solictudList(x_cont).nSolicitud;

		IF SYSDATE > v_diaFin THEN

			SELECT nOrden, fecha, hora INTO solictudList(x_cont).nOrden,
											solictudList(x_cont).fecha,
											solictudList(x_cont).hora
			FROM Solictud
			WHERE nSolicitud = solictudList(x_cont).nSolicitud;

		END IF;

		x_cont = x_cont +1;

	END LOOP;

END consultaSolicitud;