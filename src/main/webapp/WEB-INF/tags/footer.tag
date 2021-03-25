<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%-- Placed at the end of the document so the pages load faster --%>
<spring:url value="/webjars/jquery/2.2.4/jquery.min.js" var="jQuery"/>
<script src="${jQuery}"></script>

<%-- jquery-ui.js file is really big so we only load what we need instead of loading everything --%>
<spring:url value="/webjars/jquery-ui/1.11.4/jquery-ui.min.js" var="jQueryUiCore"/>
<script src="${jQueryUiCore}"></script>

<%-- Bootstrap --%>
<spring:url value="/webjars/bootstrap/3.3.6/js/bootstrap.min.js" var="bootstrapJs"/>
<script src="${bootstrapJs}"></script>

<button id="es"><img alt="ES" height="30" width="30" align="middle" src="/resources/images/spain.png"></button>

<button id="en"><img alt="EN" height="30" width="30" align="middle" src="/resources/images/uk.jpg"></button>

<script>
	$('#en').click( function() {
		window.location.replace('?lang=' + "en");
	});
	$('#es').click( function() {
		window.location.replace('?lang=' + "es");
	});
</script>