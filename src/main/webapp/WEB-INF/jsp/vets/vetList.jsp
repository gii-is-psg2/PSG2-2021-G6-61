<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="vets">
    <h2>Veterinarians</h2>
	
	<div class="form-group">
        <div class="col-sm-offset-11">
        <a href="/vets/new">
        	<button class="btn btn-default">+New Vet</button>
        </a>
    	</div>
    </div>
    <table id="vetsTable" class="table table-striped">
        <thead>
        <tr>
            <th>Name</th>
            <th>Specialties</th>
            <th style="text-align: center;">Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${vets.vetList}" var="vet">
            <tr>
                <td>
                    <c:out value="${vet.firstName} ${vet.lastName}"/>
                </td>
                <td>
                    <c:forEach var="specialty" items="${vet.specialties}">
                        <c:out value="${specialty.name} "/>
                    </c:forEach>
                    <c:if test="${vet.nrOfSpecialties == 0}">none</c:if>
                </td>
                <td align="center">
                	<a href="<spring:url value="/vets/${vet.id}/edit" htmlEscape="true" />"><span class="glyphicon glyphicon-pencil"></span></a>
                	<a href="<spring:url value="/vets/${vet.id}/delete" htmlEscape="true" />"><span class="glyphicon glyphicon-remove"></span></a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <table class="table-buttons">
        <tr>
            <td>
                <a href="<spring:url value="/vets.xml" htmlEscape="true" />"><spring:message code="viewXML" text="View as XML"/></a>
            </td>            
        </tr>
    </table>
</petclinic:layout>
