<?php 
require("../wsBDcredencial.php");

$conexion =mysqli_connect($hostname,$username,$password,$database);

$IdRecoger = $_GET["idrecoger"];
$FkScouter = $_GET["fkscouter"];
$FkAsignacion = $_GET["fkasignacion"];


$sql = "UPDATE recoger SET FkScouter = ? , FkAsignacion = ? WHERE IdRecoger = ?";

$stm = $conexion->prepare($sql);
$stm->bind_param('iii',$FkScouter,$FkAsignacion,$IdRecoger);
if($stm->execute()){
	echo "Actualizado";
}else{
	echo "NoActulizado";
}
mysqli_close($conexion);
?>