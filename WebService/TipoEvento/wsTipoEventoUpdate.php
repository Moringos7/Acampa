<?php 
require("../wsBDcredencial.php");

$conexion =mysqli_connect($hostname,$username,$password,$database);

$IdTipoEvento = $_POST["idtipoevento"];
$Nombre = $_POST["nombre"];


$sql = "UPDATE tipoevento SET Nombre = ? WHERE IdTipoEvento = ?";

$stm = $conexion->prepare($sql);
$stm->bind_param('si',$Nombre,$IdTipoEvento);
if($stm->execute()){
	echo "Actualizado";
}else{
	echo "NoActulizado";
}
mysqli_close($conexion);
?>