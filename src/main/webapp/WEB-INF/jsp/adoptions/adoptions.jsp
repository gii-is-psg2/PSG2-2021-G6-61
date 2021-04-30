<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<petclinic:layout pageName="adoptions">
    <h2>Adoptions</h2>

    <table id="ownersTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 150px;">Name Pet</th>
            <th style="width: 200px;">Type </th>
            <th>Actual Owner Name</th>
            <th style="text-align: center;"><spring:message code="actions" text="Actions"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${pets}" var="adoption">
            <tr>
                <td>
                    <c:out value="${adoption.name}"/>
                </td>
                <td>
                    <c:out value="${adoption.type}"/>
                </td>
                <td>
                    <c:out value="${adoption.owner.user.username}"/>
                </td>
            
                <td align="center">
               		<sec:authorize access="hasAnyAuthority('owner')">
	                    <spring:url value="/proposals/{petId}/new" var="proposalURL">
	                    	<spring:param name="petId" value="${adoption.id}"/>
	                    </spring:url>
	                    <a href="${fn:escapeXml(proposalURL)}"><span class="glyphicon glyphicon-transfer" title="Propose adoption"></span></a>   
	                </sec:authorize>        
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>
