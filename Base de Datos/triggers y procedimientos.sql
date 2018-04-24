--Eventos--
--Evento Actualización Contraseñas--
CREATE EVENT `ActualizacionIntentosPassword` ON SCHEDULE EVERY 1 MONTH STARTS
'2018-04-01 12:00:00.000000' ON COMPLETION NOT PRESERVE ENABLE DO Update password set Intentos = 3;


--Triggers-- 
--TriggerSeccionInsertar--
CREATE TRIGGER `TriggerSeccion` BEFORE INSERT ON `usuario` FOR EACH ROW 
BEGIN declare vScout INTEGER; declare VEdad INTEGER; 
SET vScout = new.Scout; 
IF vScout = 0 THEN SET NEW.FkSeccion = (SELECT idseccion from seccion where Nombre = 'civil');
	ELSE SET vEdad = (SELECT TIMESTAMPDIFF(YEAR,NEW.FechaNacimiento,CURDATE()));
	IF vEdad < 11 THEN SET new.Fkseccion = (SELECT idseccion from seccion where Nombre = 'manada');
	ELSEIF vEdad < 15 THEN SET new.Fkseccion = (SELECT idseccion from seccion where Nombre = 'tropa');
	ELSEIF vEdad < 18 THEN SET new.Fkseccion = (SELECT idseccion from seccion where Nombre = 'comunidad');
	ElSEIF vEdad < 22 THEN SET new.Fkseccion = (SELECT idseccion from seccion where Nombre = 'clan');
	ELSE SET new.Fkseccion = (SELECT idseccion from seccion where Nombre = 'dirigente'); 
	END IF; 
END IF;
END
--TriggerSeccionEditar--



--Procedimientos--
---Actualizacion Comnetarios---
CREATE PROCEDURE `ActualizacionComentarios`() NOT DETERMINISTIC CONTAINS SQL SQL SECURITY DEFINER 
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
CREATE PROCEDURE `ActualizacionScouter`() NOT DETERMINISTIC CONTAINS SQL SQL SSECURITY DEFINER 
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
END