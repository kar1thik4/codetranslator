<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet" href="/css/stylescss.css" />
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style>
textarea.p {
	width: 555px;
	font-size: 14px;
	padding: 10px;
	line-height: 17px;
	border-radius: 5px;
	border: 1px solid #aaaaaa;
	border-width: 10px;
}

input {
	border: 2px solid;
}
</style>
<script type="text/javascript">
/* document.getElementById('textbox').addEventListener('keydown', function(e) {
	  if (e.key == 'Tab') {
	    e.preventDefault();
	    var start = this.selectionStart;
	    var end = this.selectionEnd;

	    // set textarea value to: text before caret + tab + text after caret
	    this.value = this.value.substring(0, start) +
	      "\t" + this.value.substring(end);

	    // put caret at right position again
	    this.selectionStart =
	      this.selectionEnd = start + 1;
	  }
	}); */
	
	document.querySelector("textarea").addEventListener('keydown',function(e) {
	    if(e.keyCode === 9) { // tab was pressed
	        // get caret position/selection
	        var start = this.selectionStart;
	        var end = this.selectionEnd;

	        var target = e.target;
	        var value = target.value;

	        // set textarea value to: text before caret + tab + text after caret
	        target.value = value.substring(0, start)
	                    + "\t"
	                    + value.substring(end);

	        // put caret at right position again (add one for the tab)
	        this.selectionStart = this.selectionEnd = start + 1;

	        // prevent the focus lose
	        e.preventDefault();
	    }
	},false)
</script>
<title>Welcome Page</title>
</head>
<body>
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">Online compiler</a>
			</div>
		</div>
	</nav>
	<form:form action="translate" modelAttribute="emp" method="POST">
		<table
			style="margin-top: 50px; margin-left: 300px; border-collapse: collapse">

			<tr>
				<td><form:textarea path="text" class="form-control p" rows="20"
						cols="80" id="textbox"></form:textarea></td>
			</tr>
		</table>
		<br>
		<br>
		<div style="margin-left: 300px">
			File Name
			<form:input width="48" path="targetLanguage" />
			<br> <br> <input type="submit" value="Run" />
		</div>
		<br>
		<br>
		<div>
			<textarea class="form-control p" rows="10"
				style="margin-top: 50px; margin-left: 300px; border: solid #66CD00"
				cols="80" id="textbox">${compiled}</textarea>
		</div>
	</form:form>
</body>
</html>