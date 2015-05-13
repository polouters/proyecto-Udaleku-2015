/* Procedimiento Consulta */

/*
 Autor: Mikel
 Descripcion: Procedimiento realiza varirias consultas para obtener los datos
 	de solicitudeud que busca el usuario, sus correspodientes inscripciones
*/

-- Creacion procedimiento "consultaSolicitud" que recibe dos parametros
CREATE OR REPLACE PROCEDURE consultaSolicitud
	(v_dniTutor Tutor.dni%TYPE,
	 V_fechaNac Menor.fechaNac.%TYPE) 
IS
	-- Declararcion el registro "participantes_reg" que recoge los datos de los participantes
	TYPE participantes_reg IS RECORD (
		codMenor Menor.codMenor%TYPE,
		nombre Menor.nombre%TYPE,
		ape1 Menor.ape1%TYPE,
		ape2 Menor.ape2%TYPE,
		fechaNac Menor.fechaNac%TYPE);

	-- Declaracion la tabla pl/sql partiendo del registro participantes_reg*/
	TYPE participantes IS TABLE OF participantes_reg INDEX BY BINARY_INTEGER;

	-- Declaracion el registro "solicitud_reg" que recoge los datos de las solicitudes y del sorteo 
	TYPE solicitud_reg IS RECORD (
		nSolicitud Solictud.nSolicitud%TYPE,
		situacion Solictud.situacion%TYPE,
		nOrden Solictud.nOrden%TYPE,
		fecha Solictud.fecha%TYPE,
		hora Solictud.hora%TYPE,
		participantesList participantes
		);

	-- Declaracion la tabla pl/sql partiendo del registro solicitudes_reg
	TYPE solictudes IS TABLE OF solicitud_reg INDEX BY BINARY_INTEGER;
	solictudList solictudes;

	-- Declaracion del cursor nSolicitud_cursor
	CURSOR nSolicitud_cursor IS
		SELECT nSolicitud 
		FROM Inscripcion
		WHERE dni = v_dniTutor AND codMenor = ALL (SELECT codMenor
												   FROM Menor
												   WHERE fechaNac = V_fechaNac;);
	-- Declaracion del cursor codMenor_cursor
	CURSOR codMenor_cursor IS
		SELECT codMenor
		FROM Inscripcion
		WHERE nSolicitud = solictudList(x_cont).nSolicitud;

	/* Crear variables:
		v_diaFin
		x_cont con el valor 1
		y_cont
	 */
	v_diaFin Sorteo.diaFin%TYPE;
	x_cont NUMBER(1):=1;
	y_cont NUMBER(1);

BEGIN
	-- Seleccionar la fecha fin del periodo de inscripcion
	SELECT diaFin INTO v_diaFin
	FROM Sorteo;
	-- Abrir el cursor nSolicitud_cursor
	OPEN nSolicitud_cursor;

	WHILE nSolicitud_cursor%NOTFOUND LOOP
		-- Recuperar los datos del cursor nSolicitud_cursor y guardarlos en la tabla solicitudList
		FETCH nSolicitud_cursor INTO solictudList(x_cont).nSolicitud;
		
		-- Asignar el valor 1 a la variable y_cont
		y_cont := 1;

			WHILE codMenor_cursor%NOTFOUND LOOP
				-- Abrir el cursor codMenor_cursor
				codMenor_cursor OPEN;
				-- Recuperar los datos del cursor codMenor_cursor y guardalos en la tabla solicitudList
				FETCH codMenor_cursor INTO solictudList(x_cont).participantesList(y_cont).codMenor;
				-- Obtener el nombre, los apellido, la fecha de nacimiento y guardarlos en la tabla solicitudList
				SELECT nombre, ape1, ape2, fechaNac INTO solictudList(x_cont).participantesList(y_cont).nombre,
														 solictudList(x_cont).participantesList(y_cont).ape1, 
														 solictudList(x_cont).participantesList(y_cont).ape2,
														 solictudList(x_cont).participantesList(y_cont).fechaNac

				FROM Menor
				WHERE codMenor = solictudList(x_cont).participantesList(y_cont).codMenor;
				-- Aumentar el valor en uno de la variable y_cont
				y_cont := y_cont +1;

			END LOOP;
		-- Cerrar codMenor_cursor
		CLOSE codMenor_cursor;
		-- Obtner la situacion de la solicitud
		SELECT situacion INTO solictudList(x_cont).situacion
		FROM Solictud
		WHERE nSolicitud = solictudList(x_cont).nSolicitud;
		-- Comprobar si el periodo de inscripcion a finalizado
		IF SYSDATE > v_diaFin THEN
			-- Obtener Orden de sorteo, la fecha y hora y guardarlos en la tabla solicitudList
			SELECT nOrden, fecha, hora INTO solictudList(x_cont).nOrden,
											solictudList(x_cont).fecha,
											solictudList(x_cont).hora
			FROM Solictud
			WHERE nSolicitud = solictudList(x_cont).nSolicitud;

		END IF;
		-- Aumentar el valor en uno de la variable x_cont
		x_cont = x_cont +1;

	END LOOP;
	-- Cerrar el cursor nSolicitud_cursor
	CLOSE nSolicitud_cursor;

END consultaSolicitud;