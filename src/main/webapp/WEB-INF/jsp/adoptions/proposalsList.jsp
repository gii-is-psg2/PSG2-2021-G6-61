<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<petclinic:layout pageName="adoptions">
    <h2>Adoption Proposals for <c:out value="${pet.name}"/></h2>

    <table id="ownersTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 10%;">User</th>
            <th style="width: 30%;">Owner's first name and last name</th>
            <th style="width: 50%;">Description</th>
            <th style="text-align: center;"><spring:message code="actions" text="Actions"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${propuestas}" var="propuesta">
            <tr>
                <td>
                    <c:out value="${propuesta.owner.user.username}"/>
                </td>
                <td>
                    <c:out value="${propuesta.owner.firstName} ${propuesta.owner.lastName}"/>
                </td>
                <td>
                    <c:out value="${propuesta.description}"/>
                </td>
            
                <td align="center">
	            	<spring:url value="/proposals/{proposalId}/accept" var="proposalAcceptURL">
	                	<spring:param name="proposalId" value="${propuesta.id}"/>
	                </spring:url>
	                <a href="${fn:escapeXml(proposalAcceptURL)}"><span class="glyphicon glyphicon-ok" title="Accept proposal"></span></a>        
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>