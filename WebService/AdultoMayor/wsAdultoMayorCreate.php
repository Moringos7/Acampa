<?php
require("../wsBDcredencial.php");
$conexion = mysqli_connect($hostname,$username,$password,$database);
$Nombre = $_GET["nombre"];
$ApellidoPaterno = $_GET["apellidomaterno"];
$ApellidoMaterno = $_GET["apellidopaterno"];
$Fotografia = $_GET["fotografia"];
$Diabetico = $_GET["diabetico"]; 
$FkDependencia = $_GET["fkdependencia"];
$FkDomicilio = $_GET["fkdomicilio"];

$sql = "INSERT INTO adultomayor VALUES(null,?,?,?,?,?,?,?)";

$stm = $conexion->prepare($sql);
$stm->bind_param('ssssiii',$Nombre,$ApellidoPaterno,$ApellidoMaterno,$Fotografia,$Diabetico,$FkDependencia,$FkDomicilio);
if($stm->execute()){
	echo "Creado";
}else{
	echo "No Creado";
}
mysqli_close($conexion);
?>