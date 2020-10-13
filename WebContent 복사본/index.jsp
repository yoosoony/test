<%@page import="common.EmpVO" %>
<%@page import="common.JDBCTemplate_practice_01" %>
<%@page import="java.util.List" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
JDBCTemplate_practice_01 jdbcTemp = new JDBCTemplate_practice_01();
List<EmpVO> emplist = jdbcTemp.getEmps(); //getEmps
%>

<p>EMP 목록</p>
<table border="1px">
	<tr>
		<td>empno</td>
		<td>ename</td>
		<td>job</td>
	</tr>
	<%
	//여기는 자바코드형태를 넣을 수 있다.
	for(int i = 0; i<emplist.size() ;i++){
		EmpVO empvo = emplist.get(i);
	%>
	<tr>
		<td><%=empvo.getEmpno() %></td> <!-- 형태 확인 똑바로해라,,, -->
		<td><%=empvo.getEname() %></td>
		<td><%=empvo.getJob()%></td>

	</tr>
	<%
	}
	%>
</table>
</body>
</html>