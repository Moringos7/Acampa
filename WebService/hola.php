<?php
	/*
	for ($i=0; $i < 9; $i++) { 
		$pass = $pass.rand(0,9);
	}
	echo $pass;*/
	require("wsBDcredencial.php");
	$conexion = mysqli_connect($hostname,$username,$password,$database);

	$Correo = "moringos7@gmail.com";
	$select = "SELECT IdUsuario FROM usuario WHERE Correo = '$Correo'"; 
    $resultado = mysqli_query($conexion,$select);
    if($verificacion = mysqli_fetch_array($resultado,MYSQLI_ASSOC)){
        $Id = $verificacion['IdUsuario'];
        echo $Id;
        $checkRegistro = true;
        $query = "SELECT Intentos FROM password WHERE FkUsuario = $Id"; 
        $resultado = mysqli_query($conexion,$query);
        if($valor = mysqli_fetch_array($resultado,MYSQLI_ASSOC)){
            echo $valor['Intentos'];
        }
    }
    mysqli_close($conexion);
?>