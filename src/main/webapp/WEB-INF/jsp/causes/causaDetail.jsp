<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="vets">
    <h2>Veterinarians</h2>
	
	<div class="form-group">
        <div class="col-sm-offset-11">
        <a href="/causas">
        	<button class="btn btn-default">Return</button>
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
            <td></td>
        </tr>
        <tr>
            <th>Active non profit organization</th>
            <td><c:out value="${causa.organizacion}"/></td>
        </tr>
    </table>
    
    
    <table id="donationsTable" class="table table-striped">
        <thead>
        <tr>
            <th>Atributo donacion</th>
            <th>Atributo donacion</th>
            <th>Atributo donacion</th>
            <th style="text-align: center;">Actions</th>
        </tr>
        </thead>
        <tbody>
    
        </tbody>
    </table>
</petclinic:layout>
