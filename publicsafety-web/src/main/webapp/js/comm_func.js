

/**
 * 检查Cookie是否启用
 * @returns {Boolean}
 */
function CookieEnable() {
	var result = false;
	if (navigator.cookiesEnabled)
		return true;
	document.cookie = "testcookie=yes;";
	var cookieSet = document.cookie;
	if (cookieSet.indexOf("testcookie=yes") > -1)
		result = true;
	document.cookie = "";
	return result;
}
