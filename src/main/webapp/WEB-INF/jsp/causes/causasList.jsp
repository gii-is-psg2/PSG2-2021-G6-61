<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="causas">
    <h2>Causes list</h2>
	
	<div class="form-group">
        <div class="col-sm-offset-11">
        <a href="/causas/new">
        	<button class="btn btn-default">+ New Cause</button>
        </a>
    	</div>
    </div>
    <table id="causasTable" class="table table-striped">
        <thead>
        <tr>
            <th>Name</th>
            <th>Description</th>
            <th style="width: 150px;">Budget Target</th>
            <th style="width: 150px;">Budget Achieved</th>
            <th style="text-align: center;">Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${causas}" var="cause">
            <tr>
                <td>
                    <c:out value="${cause.nombre}"/>
                </td>
                <td>
                    <c:out value="${cause.descripcion}"/>
                </td>
                <td>
                    <c:out value="${cause.objetivoPresupuestario}"/>
                </td>
                <td>
                    
                </td>
               
                <td align="center">
                	<a href="<spring:url value="/causas/${cause.id}" htmlEscape="true" />"><i class="fas fa-eye"></i></a>
					
                </td> 
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>
