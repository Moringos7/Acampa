<?php
require("../wsBDcredencial.php");
$conexion = mysqli_connect($hostname,$username,$password,$database);

$FkUsuario = $_POST["fkusuario"];
$FkAdultoMayor = $_POST["fkadultomayor"];

$sql = "INSERT INTO voluntariofrecuente VALUES(null,?,?)";

$stm = $conexion->prepare($sql);
$stm->bind_param('ii',$FkUsuario,$FkAdultoMayor);
if($stm->execute()){
	echo "Creado";
}else{
	echo "No Creado";
}
mysqli_close($conexion);
?>