<?php  

require("../wsBDcredencial.php");

	$conexion = mysqli_connect($hostname,$username,$password,$database);
	$sql = "SELECT Count(*) as Cuenta FROM scouter";
	$resultado = mysqli_query($conexion,$select);
	if($registro = mysqli_fetch_array($resultado,MYSQLI_ASSOC)){
		echo $registro['Cuenta'];
	}
	echo "Hola";

?>
