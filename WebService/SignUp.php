<?php  
require("wsBDcredencial.php");
$conexion = mysqli_connect($hostname,$username,$password,$database);

$Packetjson = file_get_contents('php://input');
$data = json_decode($Packetjson); 

$Nombre = $data->nombre;
$Imagen = $data->imagen;
$ApellidoP = $data->apellidop;
$ApellidoM = $data->apellidom;
$Correo =  $data->correo;
$Fecha = $data->fecha;
$Scout = $data->scout;
$IdUsuario = 'x';


if($Nombre != "" && $ApellidoP != "" && $ApellidoM != "" && $Correo != ""){
	$registro["CheckParam"] = true;
	$select = "SELECT IdUsuario FROM usuario WHERE Correo = '$Correo'";	
	$resultado = mysqli_query($conexion,$select);
	if(!($verificacion = mysqli_fetch_array($resultado,MYSQLI_ASSOC))){
		$registro["CheckExiste"] = false;
		if($Scout == "true"){
			$Scout = '1';
		}else{
			$Scout = '0';
		}		
		$Nombre =  utf8_decode($Nombre);
		$ApellidoP = utf8_decode($ApellidoP);
		$ApellidoM = utf8_decode($ApellidoM);
		$Correo = utf8_decode($Correo);
		$ruta = "https://acampa.000webhostapp.com/img/inventario/$IdUsuario.jpg";		
		$insert = "INSERT INTO usuario VALUES(null,?,?,?,?,?,?,?,null)";

		$stm = $conexion->prepare($insert);
		$stm->bind_param('ssssssi',$Nombre,$ApellidoP,$ApellidoM,$Correo,$ruta,$Fecha,$Scout);
		
		if($stm->execute()){
			$registro["CheckCreacion"] = true;
			if($Imagen != ""){
				$select = "SELECT IdUsuario FROM usuario WHERE Correo = '$Correo'";	
				$resultado = mysqli_query($conexion,$select);
				if($Id = mysqli_fetch_array($resultado,MYSQLI_ASSOC)){
					$IdUsuario = $Id['IdUsuario'];
					$registro["IdUsuario"] = $IdUsuario;
					$ruta = "https://acampa.000webhostapp.com/img/inventario/$IdUsuario.jpg";
					file_put_contents($ruta,base64_decode($Imagen));
					$update = "UPDATE usuario SET Fotografia = ? WHERE IdUsuario = ?";
					$stm = $conexion->prepare($update);
					$stm->bind_param('si',$ruta,$IdUsuario);
					$stm->execute();
					EnvioCorreo();
				}	
			}
		}else{
			$registro["CheckCreacion"] = false;
		}
	}else{
		$registro["CheckExiste"] = true;
	}
}else{	
	$registro["CheckParam"] = false;
}
$json['SignUp'][] = $registro;
echo json_encode($json);
/*$json = file_get_contents('php://input');
$data = json_decode($json); 
*/





/*
Verificar si la cuenta ya existe
Enviar datos a Usuario
Checar campo imagen 
	/si existe enviar imagen a  
*/
?>
