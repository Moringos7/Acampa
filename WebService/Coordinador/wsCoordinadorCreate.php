<?php
require("../wsBDcredencial.php");
$conexion = mysqli_connect($hostname,$username,$password,$database);
$Fkscouter= $_POST["status"];
$sql = "INSERT INTO coordinador  VALUES (null,?)";

$stm = $conexion->prepare($sql);
$stm->bind_param('i',$Fkscouter);
if($stm->execute()){
	echo "Creado";
}else{
	echo "No Creado";
}
mysqli_close($conexion);
?>