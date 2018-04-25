--//---Por programar
--
--Verificacion Scouter diario
CREATE EVENT VerificacionScouter ON SCHEDULE EVERY 1 DAY STARTS 
'2018-04-25 07:45:00' ON COMPLETION  PRESERVE ENABLE DO 
BEGIN
	CALL ActualizacionScouter(); 
END
--Verificacion Comentario Anual
--Verificacion Seccion por cumpleaños
--Eventos--
--Evento Actualización Contraseñas--
DELIMITER //
CREATE EVENT ActualizacionIntentosPassword ON SCHEDULE EVERY 1 MONTH STARTS 
'2018-00-00 00:00:00' ON COMPLETION  PRESERVE ENABLE DO Update password set Intentos = 3;
--Evento Actualización Inventario Posterior a un Servicio---
DELIMITER //
CREATE EVENT ReinicioInventario ON SCHEDULE EVERY 1 MONTH STARTS 
'2018-00-00 10:00:00' ON COMPLETION PRESERVE ENABLE DO 
BEGIN 
CALL ResetInventario(); 
END //

--Triggers-- 
--TriggerSeccionInsertar--
DELIMITER //
CREATE TRIGGER TriggerSeccionInsertar BEFORE INSERT ON usuario FOR EACH ROW 
BEGIN 
declare vScout INTEGER; 
declare VEdad INTEGER; 
SET vScout = new.Scout; 
IF vScout = 0 THEN 
	SET NEW.FkSeccion = (SELECT idseccion from seccion where Nombre = 'civil');
ELSE 
	SET vEdad = (SELECT TIMESTAMPDIFF(YEAR,NEW.FechaNacimiento,CURDATE()));
	IF vEdad < 11 THEN 
		SET new.Fkseccion = (SELECT idseccion from seccion where Nombre = 'manada');
	ELSEIF vEdad < 15 THEN
		SET new.Fkseccion = (SELECT idseccion from seccion where Nombre = 'tropa');
	ELSEIF vEdad < 18 THEN
		SET new.Fkseccion = (SELECT idseccion from seccion where Nombre = 'comunidad');
	ElSEIF vEdad < 22 THEN
		SET new.Fkseccion = (SELECT idseccion from seccion where Nombre = 'clan');
	ELSE 
		SET new.Fkseccion = (SELECT idseccion from seccion where Nombre = 'dirigente'); 
	END IF; 
END IF;
END//
DELIMITER ;

--TriggerSeccionEditar--
DELIMITER //
CREATE TRIGGER TriggerSeccionEditar BEFORE UPDATE ON usuario FOR EACH ROW 
BEGIN 
declare vScout INTEGER;
declare VEdad INTEGER; 
SET vScout = new.Scout;
IF vScout = 0 THEN 
	SET NEW.FkSeccion = (SELECT idseccion from seccion where Nombre = 'civil'); 
ELSE 
	SET vEdad = (SELECT TIMESTAMPDIFF(YEAR,NEW.FechaNacimiento,CURDATE()));
	IF vEdad < 11 THEN
		SET new.Fkseccion = (SELECT idseccion from seccion where Nombre = 'manada');
	ELSEIF vEdad < 15 THEN
		SET new.Fkseccion = (SELECT idseccion from seccion where Nombre = 'tropa');
	ELSEIF vEdad < 18 THEN 
		SET new.Fkseccion = (SELECT idseccion from seccion where Nombre = 'comunidad'); 
	ElSEIF vEdad < 22 THEN 
		SET new.Fkseccion = (SELECT idseccion from seccion where Nombre = 'clan');
	ELSE 
		SET new.Fkseccion = (SELECT idseccion from seccion where Nombre = 'dirigente');
	END IF;
END IF; 
END//
DELIMITER ;

--Procedimientos--
--Actualización Invetario--
DELIMITER //
CREATE PROCEDURE ResetInventario() NOT DETERMINISTIC CONTAINS SQL SQL SECURITY DEFINER 
BEGIN
DECLARE vnInventario INTEGER; 
DECLARE vProducto VARCHAR(50);
DECLARE vCantidad FLOAT;
DECLARE vExistencia INTEGER;
DECLARE vAM INTEGER; 
DECLARE vAMD INTEGER;
DECLARE i INTEGER;

SET i = 1;
SET vnInventario = (SELECT COUNT(*) FROM inventario where Extra = 0);
SET vAM = (SELECT COUNT(*) FROM adultomayor);
SET vAMD = (SELECT COUNT(*) FROM adultomayor where Diabetico = 1);

while i <= vnInventario DO
	SET vCantidad = (SELECT Cantidad from Inventario where IdInventario = i);
	SET vProducto = (SELECT Producto FROM Inventario WHERE IdInventario = i);
	SET vExistencia = (SELECT Existencia FROM Inventario WHERE IdInventario = i);

	IF vCantidad > 1 THEN
		SET vExistencia = (vExistencia - (vCantidad * vAM));
	ELSEIF vProducto = 'Splenda' THEN
		SET vExistencia = (vExistencia - vAMD);
	ELSE
		SET vExistencia = (vExistencia - vAM);
	END IF;
	IF vExistencia < 0 THEN
		SET vExistencia = 0;
	END IF;
	UPDATE inventario SET Existencia = vExistencia WHERE IdInventario = i;
	SET i = i+1;
END while;

END //
--**********PENDIENTE REVISION**************--
---Actualizacion Comentarios---
DELIMITER //
CREATE PROCEDURE ActualizacionComentarios() NOT DETERMINISTIC CONTAINS SQL SQL SECURITY DEFINER 
BEGIN 
DECLARE vIdMax Integer; 
DECLARE vCont Integer DEFAULT 1;
DECLARE vFecha Date;
DECLARE vAntiguedad int;
SET vIdMax = (select Idcomentarioam from comentarioam order by idcomentarioam desc limit 1); 
WHILE vCont <= vIdMax DO 
	SET vFecha = (SELECT Fecha From comentarioam where idcomentarioam = vCont);
	SET vAntiguedad = (SELECT TIMESTAMPDIFF(YEAR,vFecha,CURDATE())); 
	IF vAntiguedad >= 1 THEN 
		DELETE FROM comentarioam WHERE idcomentarioam = vCont; 
	END IF; 
	SET vCont = vCont + 1; 
END WHILE; 
END
---Actualizacion Scouter----
DELIMITER //
CREATE PROCEDURE ActualizacionScouter() NOT DETERMINISTIC CONTAINS SQL SQL SSECURITY DEFINER 
BEGIN 
DECLARE vIdMax Integer;
DECLARE vCont Integer DEFAULT 1; 
DECLARE vFecha Date;
DECLARE vFechaActual Date;
SET vFechaActual = CURDATE();
SET vIdMax = (select Idscouter from scouter order by idscouter desc limit 1);
WHILE vCont <= vIdMax DO 
	SET vFecha = (SELECT FechaFinal From scouter where idscouter = vCont);
	IF vFecha <= vFechaActual THEN 
		DELETE FROM scouter WHERE IdScouter = vCont; 
	END IF;
	SET vCont = vCont + 1;
END WHILE; 
END//
