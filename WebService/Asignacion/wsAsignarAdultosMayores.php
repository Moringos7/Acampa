<?php
	require("../wsBDcredencial.php");
	$conexion = mysqli_connect($hostname,$username,$password,$database);

	$Fecha = $_POST['Fecha'];
	$Cadena = $_POST['Id'];
	$IdUsuario = $_POST['IdUsuario'];
	$Cadena = explode("-", $Cadena);
	
	$select = "SELECT DISTINCT IdAsignacion FROM asignacion,adultomayor WHERE FkAdultoMayor IS null AND FkUsuario = '$IdUsuario' AND Fecha = '$Fecha' AND Status = 0";
	$resultado = mysqli_query($conexion,$select);
	if($registro = mysqli_fetch_array($resultado,MYSQLI_ASSOC)){
		
		$IdAsignacion = $registro['IdAsignacion'];
		$sql = "DELETE FROM asignacion WHERE IdAsignacion = ?";
		$stm = $conexion->prepare($sql);
		$stm->bind_param('i',$IdAsignacion);

		if($stm->execute()){
			$Status = 0;
			foreach ($Cadena as $FkAdultoMayor) {
				if($FkAdultoMayor != ""){
					$sql = "INSERT INTO asignacion VALUES(null,?,?,?,?)";
					$stm = $conexion->prepare($sql);
					$stm->bind_param('isii',$Status,$Fecha,$IdUsuario,$FkAdultoMayor);
					if($stm->execute()){
						
					}else{
					
					}
				}
			}
			echo "Enviado";	
		}else{
			echo "Fallo Envio";	
		}
	}else{
		echo "Fallo Envio";
	}
?>