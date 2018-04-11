<?php 
require("../wsBDcredencial.php");

if(isset($_POST["idinventario"])){
	$IdInventario = $_POST["idinventario"];
	$conexion = mysqli_connect($hostname,$username,$password,$database);
	$sql = "DELETE FROM inventario WHERE IdInventario = ?";
	$stm = $conexion->prepare($sql);
	$stm->bind_param('i',$IdInventario);
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
