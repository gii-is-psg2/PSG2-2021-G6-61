<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="causas">
    <h2>New Donation</h2>
        
    <div class="form-group">
        <div class="col-sm-offset-11">
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
            <th>Budget Target</th>
            <td><c:out value="${causa.objetivoPresupuestario}"/></td>
        </tr>
        <tr>
            <th>Budget Achieved</th>
            <td><c:out value="${causa.acumulado}"/></td>
        </tr>
        <tr>
            <th>Budget Remaining</th>
            <td><c:out value="${causa.objetivoPresupuestario-causa.acumulado}"/></td>
        </tr>
    </table>
    
    <h3>Donor - <c:out value="${donor}"/></h3>
	<form:form modelAttribute="donation" class="form-horizontal" id="add-donation-form">
        <div class="form-group has-feedback">
            <petclinic:inputField label="Quantity" name="cantidad"/>
        </div>
        <div class="form-group">
			<button class="btn btn-default" type="submit">Submit donation</button>	
        </div>
    </form:form>
</petclinic:layout>