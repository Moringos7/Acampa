<?php 
require("../wsBDcredencial.php");

$conexion =mysqli_connect($hostname,$username,$password,$database);

$IdVoluntarioFrecuente = $_GET["idvoluntariofrecuente"];
$FkUsuario = $_GET["fkusuario"];
$FkAdultoMayor = $_GET["fkadultomayor"];


$sql = "UPDATE voluntariofrecuente SET FkUsuario = ?, FkAdultoMayor = ? WHERE IdVoluntarioFrecuente = ?";

$stm = $conexion->prepare($sql);
$stm->bind_param('iii',$FkUsuario,$FkAdultoMayor,$IdVoluntarioFrecuente);
if($stm->execute()){
	echo "Actualizado";
}else{
	echo "NoActulizado";
}
mysqli_close($conexion);
?>