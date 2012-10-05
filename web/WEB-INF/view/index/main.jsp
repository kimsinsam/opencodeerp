<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Open Accounting</title>
<script type="text/javascript" src="${imageHost}/js/jquery-1.7.2.min.js"></script> 
<script type="text/javascript" src="${imageHost}/js/jquery-ui-1.8.16.custom.min.js"></script>
</head>
<body>
    <div id="messageArea" style="display: none;">${message}</div>
	<h2>
	   안녕하세요 오픈소스 회계프로그램입니다.
	</h2>
    
<script type="text/javascript">
$(document).ready(function() {
    var messageUse = '${messageUse}';
    if(messageUse == 'true') {
    	$("#messageArea").animate({
    	    height: 'toggle',
    	    width: 'toggle'
    	  }, "fast", function() {
    	    $(this).slideUp(300).delay(800).hide()
    	  });

    }
});
</script>
</body>
</html>