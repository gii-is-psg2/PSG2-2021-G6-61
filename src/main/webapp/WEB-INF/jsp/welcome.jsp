<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<!-- %@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %-->  

<petclinic:layout pageName="home">
    <h2>
    	<fmt:message key="welcome"/>
    </h2>
    <div class="row">
        <div class="col-md-12">
            <spring:url value="/resources/images/animalitos.png" htmlEscape="true" var="petsImage"/>
            <img class="img-responsive" src="${petsImage}" alt="Imagen Petclinic"/> 
            <a class="btn btn-default" href='<spring:url value="/manage/health" htmlEscape="true"/>'>Comprobar el estado de la aplicación</a>
        </div>
    </div>
    
</petclinic:layout>
