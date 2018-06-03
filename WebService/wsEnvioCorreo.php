<?php
    use PHPMailer\PHPMailer\PHPMailer;
    use PHPMailer\PHPMailer\Exception;
    require 'vendor/autoload.php';
    require("wsBDcredencial.php");
    $Packetjson = file_get_contents('php://input');
    $data = json_decode($Packetjson); 
    $Asunto = $data->asunto;
    $Correo = $data->correo;
    $Pass = $data->pass;
    //$Asunto = "Inicio";
    //$Correo = "moringos7@gmail.com";

    include 'wsValidadorCorreos.php';

    if($checkRegistro && $checkMaximo){
        $mail = new PHPMailer(true);  // Passing `true` enables exceptions
        try {
            //Server settings
            $mail->SMTPDebug = 0;                                 // Enable verbose debug output
            $mail->isSMTP();                                      // Set mailer to use SMTP
            $mail->Host = 'smtp.gmail.com';  // Specify main and backup SMTP servers
            $mail->SMTPAuth = true;                               // Enable SMTP authentication
            $mail->Username = 'servicioatemajac@gmail.com';                 // SMTP username
            $mail->Password = 'AtemajacBrizuela';                           // SMTP password
            $mail->SMTPSecure = 'tls';                            // Enable TLS encryption, `ssl` also accepted
            $mail->Port = 587;                                    // TCP port to connect to

            //Recipients
            $mail->setFrom("servicioatemajac@gmail.com",'Aplicacion Acampa');
            $mail->addAddress($Correo);     // Add a recipient
            $mail->addAddress('servicioatemajac@gmail.com');
            //$mail->addReplyTo('info@example.com', 'Information');
            //$mail->addCC('cc@example.com');
            //$mail->addBCC('bcc@example.com');

            //Attachments
            //$mail->addAttachment('/var/tmp/file.tar.gz');         // Add attachments
            //$mail->addAttachment('/tmp/image.jpg', 'new.jpg');    // Optional name

            //Content
            $mail->isHTML(true);                                  // Set email format to HTML
            $mail->Subject = $Asunto;
            $mail->Body    = $Body;
            //$mail->AltBody = 'This is the body in plain text for non-HTML mail clients';

            $mail->send();
            $registro["Enviado"] = true;
            $registro["Registrado"] = $checkRegistro;
            $registro["Maximo"] = $checkMaximo;
                    
        } catch (Exception $e) {
            //echo 'NoEnviado: ', $mail->ErrorInfo;
            $registro["Enviado"] = false;
            $registro["Registrado"] = false;
            $registro["Maximo"] = false;
        }
    }else{
        $registro["Enviado"] = false;
        $registro["Registrado"] = $checkRegistro;
        $registro["Maximo"] = $checkMaximo;
    }
    $json['Correo'][] = $registro;
    echo json_encode($json);

?>