<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="vets">
    <h2>Veterinarians</h2>
	
	<div class="form-group">
        <div class="col-sm-offset-11 col-sm-11">
        	 <a href="<spring:url value="/vets/new" htmlEscape="true" />"><spring:message code="newVetButton" text="+New Vet"/></a>
    	</div>
    </div>
    <table id="vetsTable" class="table table-striped">
        <thead>
        <tr>
            <th><spring:message code="name" text="Name"/></th>
            <th><spring:message code="specialties" text="Specialties"/></th>
            <th style="text-align: center;"><spring:message code="actions" text="Actions"/></th>
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
