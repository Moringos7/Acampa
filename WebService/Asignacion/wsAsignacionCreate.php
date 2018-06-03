<?php
require("../wsBDcredencial.php");
$conexion = mysqli_connect($hostname,$username,$password,$database);
$Status= $_POST["status"];
$Fecha = $_POST["fecha"];
$FkUsuario = $_POST["fkusuario"];
$FkAdultoMayor = $_POST["fkadultomayor"];

$select = "SELECT IdAsignacion FROM asignacion WHERE FkAdultoMayor IS NULL AND Fecha = '$Fecha' AND FkUsuario = '$FkUsuario'";
$resultado = mysqli_query($conexion,$select);
if($registro = mysqli_fetch_array($resultado,MYSQLI_ASSOC)){
	echo "Solicitud Enviada";
}else{
	$sql = "INSERT INTO asignacion VALUES(null,?,?,?,?)";
	$stm = $conexion->prepare($sql);
	$stm->bind_param('isii',$Status,$Fecha,$FkUsuario,$FkAdultoMayor);
	if($stm->execute()){
		echo "Solicitud Enviada";
	}else{
		echo "Solicitud No Enviada";
	}
}
mysqli_close($conexion);
?>