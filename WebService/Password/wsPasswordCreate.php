<?php
require("../wsBDcredencial.php");
$conexion = mysqli_connect($hostname,$username,$password,$database);

$jsonParam = file_get_contents('php://input');
$data = json_decode($jsonParam);
$Password = $data->password;
$FkUsuario = $data->fkusuario;
$FechaLogin = date('Y/m/d');
$Intentos = 3;
$Status = 1;

$sql = "INSERT INTO password VALUES(null,?,?,?,?,?)";
$Password = hash('sha1',$Password, false); 
$stm = $conexion->prepare($sql);
$stm->bind_param('sisii',$Password,$Intentos,$FechaLogin,$Status,$FkUsuario);

if($stm->execute()){
	$valor['Creado']=true;
}else{
	$valor['Creado']=false;
	
}
$json['Check'][] = $valor;
echo json_encode($json);
mysqli_close($conexion);
?>