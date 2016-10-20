<%@ page session="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<meta http-equiv="content-type" content="text/html;charset=UTF-8" />
<script src="${contextPath}/js/jquery-1.8.3.min.js" type="text/javascript"></script>
<script src="${contextPath}/js/comm_func.js" type="text/javascript"></script>
<script>
	function toLogin() {
		if (CookieEnable()) {
			window.location.href = "${contextPath}/login.html";
		} else {
			alert("cookie尚未启用。");
		}
	}
	jQuery(document).ready(toLogin());
	function checkBeforeLogin() {
		toLogin();
	}
</script>
cookie未启用，请启用cookie后，点击
<a href="javascript:checkBeforeLogin()">此处</a>
重新登录。
