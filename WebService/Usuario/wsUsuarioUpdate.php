<?php 
require("../wsBDcredencial.php");

$conexion =mysqli_connect($hostname,$username,$password,$database);
$IdUsuario = $_POST["idusuario"];
$Nombre = $_POST["nombre"];
$ApellidoPaterno = $_POST["apellidomaterno"];
$ApellidoMaterno = $_POST["apellidopaterno"];
$Correo = $_POST["correo"];
$Fotografia = $_POST["fotografia"];
$FechaNacimiento = $_POST["fechanacimiento"];
$Scout = $_POST["scout"];
$FkSeccion = $_POST["fkseccion"];

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