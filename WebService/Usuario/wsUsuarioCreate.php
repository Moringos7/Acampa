<?php
require("../wsBDcredencial.php");
$conexion = mysqli_connect($hostname,$username,$password,$database);
$Nombre = $_POST["nombre"];
$ApellidoPaterno = $_POST["apellidomaterno"];
$ApellidoMaterno = $_POST["apellidopaterno"];
$Correo = $_POST["correo"];
$Fotografia = $_POST["fotografia"];
$FechaNacimiento = $_POST["fechanacimiento"];
$Scout = $_POST["scout"];
$FkSeccion = $_POST["fkseccion"];

$sql = "INSERT INTO usuario VALUES(null,?,?,?,?,?,?,?,?)";


$stm = $conexion->prepare($sql);
$stm->bind_param('ssssssii',$Nombre,$ApellidoPaterno,$ApellidoMaterno,$Correo,$Fotografia,$FechaNacimiento,$Fkseccion,$Scout);
if($stm->execute()){
	echo "Creado";
}else{
	echo "No Creado";
}
mysqli_close($conexion);
?>