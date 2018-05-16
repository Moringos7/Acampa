--Eventos--
-----6-----
--Triggers--
------2-----
--Procedimientos--
---------6--------
--Eventos--(6)
--Evento Actualización Contraseñas--
DELIMITER //
CREATE EVENT ActualizacionIntentosPassword ON SCHEDULE EVERY 1 MONTH STARTS 
'2018-00-00 00:00:00' ON COMPLETION  PRESERVE ENABLE DO 
BEGIN
CALL VerificacionIntentos();
END //
DELIMITER ;
--Evento Cambio Seccion por edad--
DELIMITER //
CREATE EVENT CambioSeccion ON SCHEDULE EVERY 1 DAY STARTS 
'2018-00-00 00:00:00' ON COMPLETION PRESERVE ENABLE DO 
BEGIN 
CALL ActualizacionSeccion(); 
END//
DELIMITER ;
--Eliminación Comentarios  de hace un año--
DELIMITER //
CREATE EVENT EliminacionComentarios ON SCHEDULE EVERY 1 YEAR STARTS 
'2018-00-00 00:00:00' ON COMPLETION  PRESERVE ENABLE DO
BEGIN 
CALL ActualizacionComentarios(); 
END//
DELIMITER ;
--Eliminacion de Usuarios--
DELIMITER //
CREATE EVENT EliminacionUsuarios ON SCHEDULE EVERY 1 MONTH STARTS 
'2018-04-27 19:20:00' ON COMPLETION PRESERVE ENABLE DO 
BEGIN 
CALL VerificarLogin(); 
END//
DELIMITER ;
--Evento Actualización Inventario Posterior a un Servicio---
DELIMITER //
CREATE EVENT ReinicioInventario ON SCHEDULE EVERY 1 MONTH STARTS 
'2018-00-00 00:00:00' ON COMPLETION PRESERVE ENABLE DO 
BEGIN 
CALL ResetInventario(); 
END //
DELIMITER ;
--Verificación Permisos de Scouter--
DELIMITER //
CREATE EVENT VerificacionScouter ON SCHEDULE EVERY 1 DAY STARTS 
'2018-00-00 00:00:00' ON COMPLETION  PRESERVE ENABLE DO 
BEGIN 
CALL ActualizacionScouter(); 
END//
DELIMITER ;
---********************************************************-----------
--Triggers--(2)

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

--Procedimientos-- (5)
---Actualizacion Comentarios---
DELIMITER //
CREATE PROCEDURE ActualizacionComentarios() NOT DETERMINISTIC CONTAINS SQL SQL SECURITY DEFINER 
BEGIN 
DECLARE vIdMax Integer;
DECLARE vCont Integer DEFAULT 1;
DECLARE vFecha Date;
DECLARE vAntiguedad int; 
SET vIdMax = (select Idcomentarioam from comentarioam order by idcomentarioam desc limit 1);
WHILE vCont <= vIdMax DO SET vFecha = (SELECT Fecha From comentarioam where idcomentarioam = vCont);
	SET vAntiguedad = (SELECT TIMESTAMPDIFF(YEAR,vFecha,CURDATE()));
	IF vAntiguedad >= 1 THEN 
		DELETE FROM comentarioam WHERE idcomentarioam = vCont; 
	END IF; 
	SET vCont = vCont + 1; 
END WHILE; 
END//
DELIMITER ;
---Actualizacion Scouter----
DELIMITER //
CREATE PROCEDURE ActualizacionScouter() NOT DETERMINISTIC CONTAINS SQL SQL SECURITY DEFINER 
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
DELIMITER ;
--Actualizacion Seccion-----
DELIMITER //
CREATE PROCEDURE ActualizacionSeccion() NOT DETERMINISTIC CONTAINS SQL SQL SECURITY DEFINER 
BEGIN
declare vEdad INTEGER; 
--declare vFecha DATE;
declare vUsuario INTEGER;
declare vFechaNacimiento DATE;
declare vSeccion INTEGER;
DECLARE vScout INTEGER;
declare i INTEGER DEFAULT 1;
--SET vFecha = CURDATE();
SET vUsuario = (select IdUsuario from usuario order by IdUsuario desc limit 1);
WHILE i <= vUsuario DO

	SET vFechaNacimiento = (SELECT FechaNacimiento FROM usuario where IdUsuario = i);
	SET vEdad = (SELECT TIMESTAMPDIFF(YEAR,vFechaNacimiento,CURDATE()));
    
    SET vScout = (SELECT Scout FROM usuario where IdUsuario = i);
    
    IF vScout = 0 THEN 
		SET vSeccion = (SELECT idseccion from seccion where Nombre = 'civil'); 
	ELSEIF vEdad < 11 THEN
		SET vSeccion = (SELECT idseccion from seccion where Nombre = 'manada');
	ELSEIF vEdad < 15 THEN
		SET  vSeccion = (SELECT idseccion from seccion where Nombre = 'tropa');
	ELSEIF vEdad < 18 THEN 
		SET vSeccion = (SELECT idseccion from seccion where Nombre = 'comunidad'); 
	ElSEIF vEdad < 22 THEN 
		SET vSeccion = (SELECT idseccion from seccion where Nombre = 'clan');
	ELSE 
		SET vSeccion = (SELECT idseccion from seccion where Nombre = 'dirigente');
	END IF;
    UPDATE usuario SET FKSeccion = vSeccion WHERE IdUsuario = i;
	SET i = i + 1;
END WHILE;
END//
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
DELIMITER ;
--VerificacionIntentos--
DELIMITER //
CREATE PROCEDURE VerificacionIntentos() NOT DETERMINISTIC CONTAINS SQL SQL SECURITY DEFINER
BEGIN 
declare vPass INTEGER; 
declare vIntentos INTEGER; 
declare i INTEGER DEFAULT 1; 
SET vPass = (select IdPassword from password order by IdPassword desc limit 1);
WHILE i <= vPass DO 
	SET vIntentos = (SELECT Intentos FROM password WHERE IdPassword = i);
	IF vIntentos > 0 THEN 
		UPDATE password SET Intentos = 3 WHERE IdPassword = i; 
	END IF; 
	SET i = i + 1;
END WHILE; 
END//
DELIMITER ;

--VerificarLogin---
DELIMITER //
CREATE PROCEDURE VerificarLogin() NOT DETERMINISTIC CONTAINS SQL SQL SECURITY DEFINER
BEGIN 
DECLARE vIdMax Integer; 
DECLARE vCont Integer DEFAULT 1; 
DECLARE vFecha Date;
DECLARE vMeses Integer;
DECLARE vUsuario Integer;
SET vIdMax = (select IdPassword from password order by idpassword desc limit 1);
WHILE vCont <= vIdMax DO 
	SET vFecha = (SELECT FechaLogin From password where idpassword = vCont);
	SET vMeses = (SELECT TIMESTAMPDIFF(MONTH,vFecha,CURDATE()));
	IF vMeses >= 6 THEN 
		SET vUsuario = (SELECT FkUsuario FROM password WHERE Idpassword = vCont); 
		DELETE FROM password WHERE IdPassword = vCont;
		DELETE FROM usuario WHERE IdUsuario = vUsuario; 
	END IF; 
	SET vCont = vCont + 1; 
END WHILE; 
END//
DELIMITER ;