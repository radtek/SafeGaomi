<%@ page session="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<meta http-equiv="content-type" content="text/html;charset=UTF-8" />
<script src="${contextPath}/js/jquery-1.8.3.min.js" type="text/javascript"></script>
<script>
	function toLogin() {
		alert("cookie会话超时，请重新登录。");
		window.location.href = "${contextPath}/login.html";
	}
	jQuery(document).ready(toLogin());
</script>
