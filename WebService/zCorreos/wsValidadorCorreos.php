<?php
	if(!isset($_POST["pass"])){
        
    }
    else if(!isset($_POST["asunto"])){
        
    }
    else if(!isset($_POST["correo"])){
        
    }else{
        $Correo = $_POST["correo"];
        $Asunto = $_POST["asunto"];
        $Pass = $_POST["pass"];
        switch ($Asunto) {
            case 'Inicio':

                $Body = "<h1 align='center'>Bienvenido a Acampa </h1>
                        <font align='justify' size = 4 >Tu solicitud ha sido aceptada, ingresa a la aplicacion con: </font><br><br>
                        <font SIZE=3 ><b>Usuario: </b> $Correo<font><br>
                        <font SIZE= 3><b>Contraseña: </b> $Pass<font><br>";
            break;
            case 'Recuperacion':
                $Body = "<h1 align='center'>Recuperacion de Contraseña </h1>
                        <font align='justify' size = 4 > Haz solicitado una recuperacion de contraseña </font><br><br>
                        <font align='justify' size = 4 > Tu nueva contraseña es: </font><br>
                        <font SIZE= 3><b>Contraseña: </b> $Pass<font><br><br>
                        <font align='justify' size = 3 > Recuerda que solo puedes restablecer tu contraseña 3 veces al mes, de lo contrario tendras que contactar al Coordinador: </font><br>
                ";
            break;
            default:
                
            break;
        }
    }      
?>