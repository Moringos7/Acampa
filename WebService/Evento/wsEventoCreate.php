<?php
require("../wsBDcredencial.php");
$conexion = mysqli_connect($hostname,$username,$password,$database);

$Fecha = $_POST["fecha"];
$Hora = $_POST["hora"];
$Lugar = $_POST["lugar"];
$Informacion = $_POST["informacion"];
$FkTipoEvento = $_POST["fktipoevento"];


$sql = "INSERT INTO evento VALUES(null,?,?,?,?,?)";

$stm = $conexion->prepare($sql);
$stm->bind_param('ssssi',$Fecha,$Hora,$Lugar,$Informacion,$FkTipoEvento);
if($stm->execute()){
	echo "Creado";
}else{
	echo "No Creado";
}
mysqli_close($conexion);
?>