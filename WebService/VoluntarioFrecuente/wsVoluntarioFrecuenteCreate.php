<?php
require("../wsBDcredencial.php");
$conexion = mysqli_connect($hostname,$username,$password,$database);

$FkUsuario = $_GET["fkusuario"];
$FkAdultoMayor = $_GET["fkadultomayor"];

$sql = "INSERT INTO voluntariofrecuente VALUES(null,?,?,)";

$stm = $conexion->prepare($sql);
$stm->bind_param('ii',$FkUsuario,$FkAdultoMayor);
if($stm->execute()){
	echo "Creado";
}else{
	echo "No Creado";
}
mysqli_close($conexion);
?>