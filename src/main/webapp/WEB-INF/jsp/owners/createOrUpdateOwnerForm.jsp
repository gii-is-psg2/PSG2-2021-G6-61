<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="adoptions">
    <h2>
        <c:if test="${adoptions['new']}">New </c:if> Adoptions
    </h2>
    <form:form modelAttribute="adoptions" class="form-horizontal" id="add-owner-form">
        <div class="form-group has-feedback">
            <petclinic:inputField label="Pet Name" name="petName"/>
            <petclinic:inputField label="Pet Type" name="petType"/>
            <petclinic:inputField label="Actual Owner Name" name="owner"/>
            <petclinic:inputField label="Username" name="user.username"/>
            <petclinic:inputField label="Password" name="user.password"/>
             <c:choose>
	            <c:when test="${owner['new']}"> 
	            	<petclinic:checkboxField label="Client of the Pet Clinic" name="esCliente"/>
	            </c:when>
	            <c:otherwise>
	                <form:checkbox class="form-control" path="esCliente" style="display:none;width:20px; height:20px;"/>
	            </c:otherwise>
            </c:choose>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${adoptions['new']}">
                        <button class="btn btn-default" type="submit">Add adoptions</button>
                    </c:when>
                    <c:otherwise>
                        <button class="btn btn-default" type="submit">Update adoptions</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </form:form>
</petclinic:layout>
