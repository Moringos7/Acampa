<?php  
require("../wsBDcredencial.php");

if(isset($_POST["password"])){
	if(isset($_POST["fkusuario"])){
		$Password = $_POST["password"];
		$FkUsuario = $_POST["fkusuario"];
		$conexion = mysqli_connect($hostname,$username,$password,$database);
		$query = "SELECT Password FROM password WHERE FkUsuario = '$FkUsuario'";	
		$resultado = mysqli_query($conexion,$query);
		$Password = hash('sha1', $Password, false);
		
		if($registro = mysqli_fetch_array($resultado,MYSQLI_ASSOC)){
			//echo $registro['Password'];
			$Estado = strcmp ($registro['Password'],$Password);	
			Validacion($Estado,$FkUsuario);
		}else{
			Validacion(1,0);
		}
	}else{
		Validacion(1,0);
	}
	
}else{
	Validacion(1,0);
}

function Validacion($estado,$FkUsuario){
	require("../wsBDcredencial.php");
	$conexion = mysqli_connect($hostname,$username,$password,$database);
	if($estado == 0){
		$Fecha = date('Y/m/d');
		$query = "UPDATE password SET FechaLogin = ? WHERE FkUsuario = ?";
		$stm = $conexion->prepare($query);
		$stm->bind_param('si',$Fecha,$FkUsuario);
		if($stm->execute()){
			$registro["Validacion"] = true;
		}else{
			$registro["Validacion"] = false;	
		}
	}else{
		$registro["Validacion"] = false;
	}
	$json['Password'][] = $registro;
	echo json_encode($json);
}

?>