<?php
require("../wsBDcredencial.php");
$conexion = mysqli_connect($hostname,$username,$password,$database);
$FkScouter = $_POST["fkscouter"];
$FkAsignacion = $_POST["fkasignacion"];

$sql = "INSERT INTO recoger VALUES(null,?,?)";

$stm = $conexion->prepare($sql);
$stm->bind_param('ii',$FkScouter,$FkAsignacion);
if($stm->execute()){
	echo "Creado";
}else{
	echo "No Creado";
}
mysqli_close($conexion);
?>