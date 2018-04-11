<?php 
require("../wsBDcredencial.php");

$conexion =mysqli_connect($hostname,$username,$password,$database);

$IdAdultoMayor = $_POST["idadultomayor"];
$Nombre = $_POST["nombre"];
$ApellidoPaterno = $_POST["apellidomaterno"];
$ApellidoMaterno = $_POST["apellidopaterno"];
$Fotografia = $_POST["fotografia"];
$Diabetico = $_POST["diabetico"]; 
$FkDependencia = $_POST["fkdependencia"];
$FkDomicilio = $_POST["fkdomicilio"];


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