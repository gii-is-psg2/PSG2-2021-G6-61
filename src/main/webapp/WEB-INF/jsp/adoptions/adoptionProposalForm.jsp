<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="propuestas">

    <h2>Proposal</h2>
    <form:form modelAttribute="propuestaAdopcion" class="form-horizontal" id="proposal-form"> 
        <div class="form-group has-feedback">
            <petclinic:inputField label="Pet name" name="pet.name" readonly="true"/>
            <petclinic:inputField label="Pet type" name="pet.type" readonly="true"/>
            <petclinic:inputField label="Pet owner's name" name="pet.owner.firstName" readonly="true"/>
            <petclinic:inputField label="Pet owner's Username" name="pet.owner.lastName" readonly="true"/>
            <petclinic:inputField label="Pet owner's Username" name="pet.owner.User.username" readonly="true"/>
			<petclinic:textAreaField label="Description" name="description" maxlength="500"/>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-7">
            	<button class="btn btn-default" type="submit">Make proposal</button>
            </div>
            <div class="col-sm-offset-2 col-sm-1">
				<a href="/adoptions">
					<button class="btn btn-default" type="button">
						Cancel
					</button>
				</a>
			</div> 
        </div>
    </form:form>

</petclinic:layout>