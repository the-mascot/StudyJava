<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
x: ${param.num1 } y : ${param.num2 }<p>
덧셈 : x + y =${param.num1+param.num2 }<p>
뺄셈 : x - y =${param.num1-param.num2 }<p>
덧셈 : x * y =${param.num1*param.num2 }<p>
덧셈 : x / y =${param.num1/param.num2 }<p>
x >= y : ${param.num1>=param.num2 }<p>
x < y : ${param.num1<param.num2 }<p>
</body>
</html>