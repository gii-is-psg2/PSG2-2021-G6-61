<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="causas">
    <h2>
        New  Cause
    </h2>
    <form:form modelAttribute="causa" class="form-horizontal" id="add-causa-form">
        <div class="form-group has-feedback">
            <petclinic:inputField label="Name" name="nombre"/>
            <petclinic:inputField label="Description" name="descripcion"/>
            <petclinic:inputField label="Budget Target" name="objetivoPresupuestario"/>
            <petclinic:inputField label="Active non profit organization" name="organizacion"/>

        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button class="btn btn-default" type="submit">Add Cause</button>
                 <a href="/causas">
					<button class="btn btn-default" type="button">
						Cancel
					</button>
				</a>  
            </div>
        </div>
    </form:form>
</petclinic:layout>
