<%@ page language="java" import="java.util.*" %>

<html>
   <head>
      <title>
         Error Occurred
      </title>
   </head>
   <body>
      <h1>An Application Error has occurred</h1>
<p>
    <b>The status code is:</b> <%= request.getAttribute("javax.servlet.error.status_code") %><br>
    <b>The error message again is:</b> <%= request.getAttribute("javax.servlet.error.message") %><br>
    The exception class is: <%= request.getAttribute("javax.servlet.error.exception") %><br>
</p>
   </body>
</html>