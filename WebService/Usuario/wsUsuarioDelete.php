<?php 
require("../wsBDcredencial.php");
$json = file_get_contents('php://input');
$data = json_decode($json); 

if(isset($data->idusuario){
	$IdUsuario = $data->idusuario;
	$conexion = mysqli_connect($hostname,$username,$password,$database);
	$sql = "DELETE FROM usuario WHERE IdUsuario = ? ";
	$stm = $conexion->prepare($sql);
	$stm->bind_param('i',$IdUsuario);
	if($stm->execute()){
		echo "Eliminado";
	}else{
		echo "NoEliminado";
	}
	mysqli_close($conexion);
}else{
	echo "NoExiste";
}
?>
