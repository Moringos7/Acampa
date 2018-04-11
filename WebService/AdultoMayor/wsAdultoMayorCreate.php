<?php
require("../wsBDcredencial.php");
$conexion = mysqli_connect($hostname,$username,$password,$database);
$Nombre = $_POST["nombre"];
$ApellidoPaterno = $_POST["apellidomaterno"];
$ApellidoMaterno = $_POST["apellidopaterno"];
$Fotografia = $_POST["fotografia"];
$Diabetico = $_POST["diabetico"]; 
$FkDependencia = $_POST["fkdependencia"];
$FkDomicilio = $_POST["fkdomicilio"];

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