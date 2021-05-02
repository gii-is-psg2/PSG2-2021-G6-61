<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="causas">
    <h2>Cause<c:if test="${causa.finalizada}"> (Finished)</c:if></h2>
	
	<div class="form-group">
        <div class="col-sm-offset-9">
        <c:if test="${!causa.finalizada}">
			<a href="<spring:url value="/causas/${causa.id}/newDonation" htmlEscape="true" />"><button class="btn btn-default">New donation</button></a>
		</c:if>
        <a href="/causas">
        	<button class="btn btn-default">Go back to list</button>
        </a>
    	</div>
    </div>
    
    <table class="table table-striped">
        <tr>
            <th>Name</th>
            <td><b><c:out value="${causa.nombre}"/></b></td>
        </tr>
        <tr>
            <th>Description</th>
            <td><c:out value="${causa.descripcion}"/></td>
        </tr>
        <tr>
            <th>Budget Target</th>
            <td><c:out value="${causa.objetivoPresupuestario}"/></td>
        </tr>
        <tr>
            <th>Budget Achieved</th>
            <td><c:out value="${causa.acumulado}"/></td>
        </tr>
        <tr>
            <th>Active non profit organization</th>
            <td><c:out value="${causa.organizacion}"/></td>
        </tr>
    </table>
    
    
    <table id="donationsTable" class="table table-striped">
        <thead>
        <tr>
            <th>Date</th>
            <th>Donor</th>
            <th>Quantity</th>
        </tr>
        </thead>
        <tbody>
    		<c:forEach items="${donaciones}" var="donaciones">
    			<tr>
    				<td><c:out value="${donaciones.fecha}"></c:out></td>
    				<td><c:out value="${donaciones.donante}"></c:out></td>
    				<td><c:out value="${donaciones.cantidad}"></c:out></td>
    			</tr>
    		</c:forEach>
        </tbody>
    </table>
</petclinic:layout>
