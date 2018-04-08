<?php 
require("../wsBDcredencial.php");

$conexion =mysqli_connect($hostname,$username,$password,$database);
$IdUsuario = $_GET["idusuario"];
$Nombre = $_GET["nombre"];
$ApellidoPaterno = $_GET["apellidomaterno"];
$ApellidoMaterno = $_GET["apellidopaterno"];
$Correo = $_GET["correo"];
$Fotografia = $_GET["fotografia"];
$FechaNacimiento = $_GET["fechanacimiento"];
$Scout = $_GET["scout"];
$FkSeccion = $_GET["fkseccion"];

$sql = "UPDATE 
usuario SET IdUsuario = ?, Nombre = ?, ApellidoPaterno = ?,
ApellidoMaterno = ?,Correo = ?,Fotografia = ?,FechaNacimiento = ?,
Scout = ?,FkSeccion = ? WHERE IdUsuario = ?";

$stm = $conexion->prepare($sql);
$stm->bind_param('issssssiii', $IdUsuario,$Nombre,$ApellidoPaterno,$ApellidoMaterno,$Correo,$Fotografia,$FechaNacimiento,$Scout,$FkSeccion,$IdUsuario);
if($stm->execute()){
	echo "Actualizado";
}else{
	echo "NoActulizado";
}
mysqli_close($conexion);
?>