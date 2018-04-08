<?php 
require("../wsBDcredencial.php");

$conexion =mysqli_connect($hostname,$username,$password,$database);

$IdAdultoMayor = $_GET["idadultomayor"];
$Nombre = $_GET["nombre"];
$ApellidoPaterno = $_GET["apellidomaterno"];
$ApellidoMaterno = $_GET["apellidopaterno"];
$Fotografia = $_GET["fotografia"];
$Diabetico = $_GET["diabetico"]; 
$FkDependencia = $_GET["fkdependencia"];
$FkDomicilio = $_GET["fkdomicilio"];


$sql = "UPDATE adultomayor SET Nombre = ?, ApellidoPaterno = ?,ApellidoMaterno = ?,Fotografia = ?,Diabetico = ?, FkDependencia = ?,FkDomicilio = ? WHERE IdAdultoMayor = ?";

$stm = $conexion->prepare($sql);
$stm->bind_param('ssssiiii',$Nombre,$ApellidoPaterno,$ApellidoMaterno,$Fotografia,$Diabetico,$FkDependencia,$FkDomicilio, $IdAdultoMayor);
if($stm->execute()){
	echo "Actualizado";
}else{
	echo "NoActulizado";
}
mysqli_close($conexion);
?>