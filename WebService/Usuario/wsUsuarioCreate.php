<?php
require("../wsBDcredencial.php");
$conexion = mysqli_connect($hostname,$username,$password,$database);
$Nombre = $_GET["nombre"];
$ApellidoPaterno = $_GET["apellidopaterno"];
$ApellidoMaterno = $_GET["apellidomaterno"];
$Fotografia = $_GET["fotografia"];
$Diabetico = $_GET["diabetico"];
$FkDependencia = $_GET["fkdependencia"];
$FkDomicilio = $_GET["fkdomicilio"];

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