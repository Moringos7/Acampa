<?php 

/*
*------------*--------*--------*--------*-----------*------*
|IdInventario|Producto|Cantidad|Faltante|Descripcion|Imagen|
*/
require("../wsBDcredencial.php");
	$conexion = mysqli_connect($hostname,$username,$password,$database);
	$queryI = "SELECT IdInventario,Producto,Cantidad,Existencia,Descripcion,Imagen FROM inventario where Extra = 0";
	$queryAM = "SELECT count(*) FROM adultomayor";
	$queryAMD = "SELECT count(*) FROM adultomayor WHERE Diabetico = 1";
	$json = array();
	$Lista = array();

	$resultadoam = mysqli_query($conexion,$queryAM);
	$resultadoamd = mysqli_query($conexion,$queryAMD);
	$resultadoI = mysqli_query($conexion,$queryI);
	
	while($registro = mysqli_fetch_array($resultadoam,MYSQLI_NUM)){
		$nAdultoMayor = $registro[0];
	}
	while($registro = mysqli_fetch_array($resultadoamd,MYSQLI_NUM)){
		$nAdultoMayorDiabetico = $registro[0];
	}
	while($registro = mysqli_fetch_array($resultadoI,MYSQLI_ASSOC)){
		$registro['Producto'] = utf8_encode($registro['Producto']);
		$registro['Descripcion'] = utf8_encode($registro['Descripcion']);
		$registro['Imagen'] = utf8_encode($registro['Imagen']);

		if($registro['Producto'] == "Splenda"){
			$Faltante = $nAdultoMayorDiabetico - $registro['Existencia'];
		}else if($registro['Cantidad'] == 2){
			//echo "2";
			$Faltante = ($nAdultoMayor * 2) - $registro['Existencia'];
		}else {
			//echo "1 o menos"
			$Faltante = ($nAdultoMayor * 1) - $registro['Existencia'];
		}
		if($Faltante < 0){
			$Faltante = 0;
		}

		$Lista['IdInventario'] = $registro['IdInventario'];
		$Lista['Producto'] = $registro['Producto'];
		$Lista['Cantidad'] = $registro['Cantidad'];
		$Lista['Faltante'] = $Faltante;
		$Lista['Descripcion'] = $registro['Descripcion'];
		$Lista['Imagen'] = $registro['Imagen'];

		$json['ListaRapida'][] = $Lista;
	}
	mysqli_close($conexion);
	echo json_encode($json);


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