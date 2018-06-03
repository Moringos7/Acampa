<?php
    $conexion = mysqli_connect($hostname,$username,$password,$database);
    switch ($Asunto) {
        case 'Inicio':
                $Body = "<h1 align='center'>Solicitud Enviada </h1>
                        <font align='justify' size = 4 >Tu solicitud ha sido enviada, espera aceptación del Coordinador </font><br>
                            ";

                $select = "SELECT IdUsuario FROM usuario WHERE Correo = '$Correo'"; 
                $resultado = mysqli_query($conexion,$select);
                if($verificacion = mysqli_fetch_array($resultado,MYSQLI_ASSOC)){
                    $Password = "0P1A2B3C4D5E6F7G8H9UG75YRDF3TBF5BYORTTVE";
                    $FkUsuario = $verificacion['IdUsuario'];
                    $FechaLogin = "0000/00/00";
                    $Intentos = 3;
                    $Status = 0;
                    $sql = "INSERT INTO password VALUES(null,?,?,?,?,?)";
                    $Password = hash('sha1',$Password, false); 

                    $stm = $conexion->prepare($sql);
                    $stm->bind_param('sisii',$Password,$Intentos,$FechaLogin,$Status,$FkUsuario);
                    if($stm->execute()){
                        $checkRegistro = true;
                        $checkMaximo = true;
                    }
                    else{
                        $checkRegistro = false;
                    }
                }else{
                    $checkRegistro = false;
                }
        break;
        case 'Aceptado':
            $select = "SELECT IdUsuario FROM usuario WHERE Correo = '$Correo'"; 
            $resultado = mysqli_query($conexion,$select);
            if($verificacion = mysqli_fetch_array($resultado,MYSQLI_ASSOC)){
                
                $Id = $verificacion['IdUsuario'];
                $FechaLogin = date('Y/m/d');

                for ($i=0; $i < 9; $i++) { 
                    $Pass = $Pass.rand(0,9);
                }
                
                $sql = "UPDATE password SET Password = ?,FechaLogin = ?,Status = 0 WHERE FkUsuario = ?";
                $Password = hash('sha1',$Pass, false); 
                
                $stm = $conexion->prepare($sql);
                $stm->bind_param('ssi',$Password,$FechaLogin,$Id);
                if($stm->execute()){
                    $checkRegistro = true;
                    $checkMaximo = true;
                    $Body = "<h1 align='center'>Bienvenido a Acampa </h1>
                    <font align='justify' size = 4 >Tu solicitud ha sido aceptada, ingresa a la aplicacion con: </font><br><br>
                    <font SIZE=3 ><b>Usuario: </b> $Correo<font><br>
                    <font SIZE= 3><b>Contraseña: </b> $Pass<font><br>";
                }else{
                    $checkMaximo = false;
                }

            }else{
                $checkRegistro = false;
                $checkMaximo = false;
            }
            
        break;
        case 'Recuperacion':
            $select = "SELECT IdUsuario FROM usuario WHERE Correo = '$Correo'"; 
            $resultado = mysqli_query($conexion,$select);
            if($verificacion = mysqli_fetch_array($resultado,MYSQLI_ASSOC)){
                $Id = $verificacion['IdUsuario'];
                $checkRegistro = true;
                $query = "SELECT Intentos FROM password WHERE FkUsuario = $Id"; 
                $resultado = mysqli_query($conexion,$query);

                if($valor = mysqli_fetch_array($resultado,MYSQLI_ASSOC)){
                    $Intentos = $valor['Intentos'];
                    if( $Intentos > 0){      
                        $Intentos = $Intentos - 1;
                        for ($i=0; $i < 9; $i++) { 
                            $Pass = $Pass.rand(0,9);
                        }
                        $sql = "UPDATE password SET Password = ?, Intentos = ?,Status = 0 WHERE FkUsuario = ?";
                        $Password = hash('sha1',$Pass, false); 
                        $stm = $conexion->prepare($sql);
                        $stm->bind_param('sii',$Password,$Intentos,$Id);
                        if($stm->execute()){
                            $checkMaximo = true;
                            $checkRegistro = true;
                            $Body = "<h1 align='center'>Recuperación de Contraseña </h1>
                                    <font align='justify' size = 4 > Haz solicitado una recuperacion de contraseña </font><br><br>
                                    <font align='justify' size = 4 > Tu nueva contraseña es: </font><br>
                                    <font SIZE= 3><b>Cuenta: </b> $Correo<font><br><br>
                                    <font SIZE= 3><b>Contraseña: </b> $Pass<font><br><br>
                                    <font align='justify' size = 3 > Recuerda que solo puedes restablecer tu contraseña 3 veces al mes, de lo contrario tendras que contactar al Coordinador: </font><br>";
                        }else{
                            $checkMaximo = false;
                        }
                    }else{
                        $checkMaximo = true;
                       $Body = "<h1 align='center'>Recuperacion de Contraseña </h1>
                        <font align='justify' size = 4 > Haz solicitado una recuperacion de contraseña </font><br><br>
                        <font align='justify' size = 4 > Lamentablemente haz excedido el numero de intentos. </font><br>
                        <font align='justify' size = 3 > Contacta al Coordinador </font><br> ";
                    }
                }else{
                    $checkRegistro = false;
                    $checkMaximo = false;
                    $registro['mensaje'] = "Comprobacion intentos";

                }
            }else{
                $checkRegistro = false;
                $checkMaximo = false;
                $registro['mensaje'] = "Comprobacion cuenta";
            }

        break;
        default:
            $checkRegistro = false;
            $checkMaximo = false;
            $registro['mensaje'] = "DEFAULT";
        break;
    }  
?>