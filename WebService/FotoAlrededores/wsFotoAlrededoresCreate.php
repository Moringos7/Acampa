<?php
require("../wsBDcredencial.php");
$conexion = mysqli_connect($hostname,$username,$password,$database);
$Foto = $_POST["foto"];
$FkDomicilio = $_POST["fkdomicilio"];

$sql = "INSERT INTO fotoalrededores VALUES(null,?,?)";

$stm = $conexion->prepare($sql);
$stm->bind_param('si',$Foto,$FkDomicilio);
if($stm->execute()){
	echo "Creado";
}else{
	echo "No Creado";
}
mysqli_close($conexion);
?>