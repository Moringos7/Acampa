<?php 
require("../wsBDcredencial.php");

$conexion = mysqli_connect($hostname,$username,$password,$database);

$jsonParam = file_get_contents('php://input');
$data = json_decode($jsonParam);
$Password = $data->password;
$FechaLogin = date('Y/m/d');
$Status = $data->status;
$FkUsuario = $data->fkusuario;


$sql = "UPDATE password SET Password = ?,FechaLogin = ?, Status = ? WHERE FkUsuario = ?";

$Password = hash('sha1',$Password, false); 

$stm = $conexion->prepare($sql);
$stm->bind_param('ssii',$Password,$FechaLogin,$Status,$FkUsuario);
if($stm->execute()){
	$valor['Actualizado']=true;
}else{
	$valor['Actualizado']=false;
}
$json['Check'][] = $valor;
echo json_encode($json);
mysqli_close($conexion);
?>