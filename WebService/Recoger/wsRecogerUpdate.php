<?php 
require("../wsBDcredencial.php");

$conexion =mysqli_connect($hostname,$username,$password,$database);

$IdRecoger = $_POST["idrecoger"];
$FkScouter = $_POST["fkscouter"];
$FkAsignacion = $_POST["fkasignacion"];


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