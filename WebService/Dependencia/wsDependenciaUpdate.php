<?php 
require("../wsBDcredencial.php");

$conexion =mysqli_connect($hostname,$username,$password,$database);

$IdDependencia = $_POST["iddependencia"];
$Nombre = $_POST["nombre"];

$sql = "UPDATE dependencia SET Nombre = ? WHERE IdDependencia = ?";

$stm = $conexion->prepare($sql);
$stm->bind_param('si',$Nombre,$IdDependencia);
if($stm->execute()){
	echo "Actualizado";
}else{
	echo "NoActulizado";
}
mysqli_close($conexion);
?>