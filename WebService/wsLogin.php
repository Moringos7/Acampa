<?php  
require("wsBDcredencial.php");
// ValidacionPassword,ValidacionCorreo,Nombre,ApellidoPaterno,ApellidoMaterno,Scouter,Coordinador,FkSeccion
$json = file_get_contents('php://input');
$data = json_decode($json); 
$Password = $data->password;
$Correo = $data->correo;
if($Password != ""){
	if($Correo != ""){
		$conexion = mysqli_connect($hostname,$username,$password,$database);
		//$Password = $_POST["password"];
		////
		//$Correo = $_POST["correo"];
		$queryFk = "SELECT IdUsuario FROM usuario WHERE Correo ='$Correo'";
		$result = mysqli_query($conexion,$queryFk);
		if(!($registro = mysqli_fetch_array($result,MYSQLI_ASSOC))){
			$Vcorreo = false;
			Validacion($Vcorreo,1,0,0);
		}else{
			$Vcorreo = true;
			$FkUsuario = $registro['IdUsuario'];
			$query = "SELECT Password,Status FROM password WHERE FkUsuario = '$FkUsuario'";	
			$resultado = mysqli_query($conexion,$query);
			$Password = hash('sha1', $Password, false);
			if($registro = mysqli_fetch_array($resultado,MYSQLI_ASSOC)){	
				Validacion($Vcorreo,strcmp ($registro['Password'],$Password),$FkUsuario,$registro['Status']);
			}else{
				Validacion($Vcorreo,1,0,0);
			}
		}	
	}else{
		Validacion(false,1,0,0);
	}
}else{
	Validacion(false,1,0,0);
}
function Validacion($ValidacionCorreo,$estadoPassword,$FkUsuario,$Status){
	require("wsBDcredencial.php");
	$conexion = mysqli_connect($hostname,$username,$password,$database);
	//Correo y contraseña
	/*
	*		Si estado es igual a 0 indica que las contraseñas son iguales
	*/
	$registro["ValidacionCorreo"] = $ValidacionCorreo;
	$registro["Status"] = $Status;
	if($estadoPassword == 0){
		$Fecha = date('Y/m/d');
		$query = "UPDATE password SET FechaLogin = ? WHERE FkUsuario = ?";
		$stm = $conexion->prepare($query);
		$stm->bind_param('si',$Fecha,$FkUsuario);
		if($stm->execute()){
			$registro["ValidacionPassword"] = true;
			$select = "SELECT * FROM usuario WHERE IdUsuario = '$FkUsuario'";	
			$resultado = mysqli_query($conexion,$select);
			if($reg = mysqli_fetch_array($resultado,MYSQLI_ASSOC)){
				$registro['IdUsuario'] = $reg['IdUsuario'];
				$registro['Nombre'] = utf8_encode($reg['Nombre']);
				$registro['ApellidoPaterno'] =  utf8_encode($reg['ApellidoPaterno']);
				$registro['ApellidoMaterno'] = 	utf8_encode($reg['ApellidoMaterno']);
				$registro['Fotografia'] = utf8_encode($reg['Fotografia']);
				$registro['FkSeccion'] = $reg['FkSeccion'];
			}
			$checkScouter = "SELECT IdScouter FROM scouter WHERE FkUsuario = '$FkUsuario'";
			$resultadoScouter = mysqli_query($conexion,$checkScouter);
			if(!($scouter = mysqli_fetch_array($resultadoScouter,MYSQLI_NUM))){
				$registro['Scouter'] = null;
				$registro['Coordinador'] = null;
			}else{
				$registro['Scouter'] = $scouter[0];
				$IdScouter = $scouter[0];
				$checkCoordi = "SELECT IdCoordinador FROM coordinador WHERE FkScouter = '$IdScouter'";
				$resultCoordi = mysqli_query($conexion,$checkCoordi);
				if($coordinador = mysqli_fetch_array($resultCoordi,MYSQLI_NUM)){
					$registro['Coordinador'] = $coordinador[0];
				}else{
					$registro['Coordinador'] = null;
				}
			}
		}else{
			$registro["ValidacionPassword"] = false;
			$registro["ValidacionCorreo"] = false;	
		}
	}else{
		$registro["ValidacionPassword"] = false;
	}
	$json['Password'][] = $registro;
	echo json_encode($json);
}
?>