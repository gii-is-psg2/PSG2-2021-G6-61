<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>


<petclinic:layout pageName="owners">
    <jsp:attribute name="customScript">
        <script>
            $(function () {
                $("#date").datepicker({dateFormat: 'yy/mm/dd'});
            });
        </script>
    </jsp:attribute>
    <jsp:body>
        <h2><c:if test="${adoption['new']}">New </c:if>Adoption</h2>

        <b>Pet</b>
        <table class="table table-striped">
            <thead>
            <tr>   
            	<th>Acciones</th>
                <th>Name</th>
                <th>Type</th>
                <th>Actual owner name </th>
                <th></th>
            </tr>
            </thead>
            <tr>     
            	<td><c:out value=""/></td>
                <td><c:out value="${adoption.pet.name}"/></td>
                <td><c:out value="${adoption.pet.type.name}"/></td>
                <td><c:out value="${adoption.pet.owner.firstName} ${adoption.pet.owner.lastName}"/></td>
            </tr>
        </table>

        <form:form modelAttribute="visit" class="form-horizontal">
            <div class="form-group has-feedback">
                <petclinic:inputField label="Date" name="date"/>
                <petclinic:inputField label="Description" name="description"/>
            </div>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <input type="hidden" name="petId" value="${visit.pet.id}"/>
                    <button class="btn btn-default" type="submit">Add Visit</button>
                </div>
            </div>
        </form:form>

        <br/>
    </jsp:body>

</petclinic:layout>
