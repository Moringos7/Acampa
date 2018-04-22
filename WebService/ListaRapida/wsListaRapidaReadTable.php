<?php 

require("../wsBDcredencial.php");
	$conexion = mysqli_connect($hostname,$username,$password,$database);
	$queryI = "SELECT IdInventario,Producto,Cantidad,Existencia,Descripcion,Imagen FROM inventario where Extra = 0";
	$queryAM = "SELECT count(*) FROM adultomayor";
	$Inventario = array();

	$resultadoI = mysqli_query($conexion,$queryI);
	$resultadoam = mysqli_query($conexion,$queryAM);

	while($registro = mysqli_fetch_array($resultadoam,MYSQLI_NUM)){
		$nAdultoMayor = $registro[0];
	}
	
	while($registro = mysqli_fetch_array($resultadoI,MYSQLI_ASSOC)){
		$regis['Producto'] = utf8_encode($registro['Producto']);

		$registro['Descripcion'] = utf8_encode($registro['Descripcion']);
		$registro['Imagen'] = utf8_encode($registro['Imagen']);

		$Inventario['Inventario'][] = $registro;
	}
	//var_dump($Inventario);
	

	mysqli_close($conexion);



/*
	if($Inv['Cantidad'] == 2){
			echo "2";

		}else if($Inv['Cantidad'] == 1){
			echo "1";
		}else if($Inv['Cantidad'] == .5){
			echo ".5";
		}else if($Inv['Cantidad'] == .25){
			echo ".25";
		}
		echo "<br>";
*/












?>