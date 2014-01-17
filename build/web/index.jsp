<%-- 
    Document   : index
    Created on : Jan 15, 2014, 12:16:16 PM
    Author     : Ryan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Test Login Page</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
        <script>
            $(document).ready(function() { 
//                var echo = function() { console.log("ECHO!!!!!"); };
            });
            
            function echo() { 
                
                $.ajax({
                    url:'j_security_check',
                    type: 'POST',
                    data: { j_username: 'ryan', j_password: 'ryan' }
                }).done(function(msg) { console.log('Data Saved: '+msg)});
                console.log("SENDING POST!");
            }
        </script>
    </head>
    <body>
        <h2>Hello World!</h2>
        <br/><br/>
       
            <strong>Please enter your name:</strong>
            <input type="text" name="j_username" size="25">
            <br/><br/>
            <strong> Please enter your password:</strong>
            <input type="password" size="15" name="j_password">
            <br/><br/>
            <button onclick="echo()">Click me!</button>

       
    </body>
</html>
