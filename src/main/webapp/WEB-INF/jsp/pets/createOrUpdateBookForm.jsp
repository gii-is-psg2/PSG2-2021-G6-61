<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>


<petclinic:layout pageName="owners">
    <jsp:attribute name="customScript">
        <script>
            
            $('#checkin, #checkout').datepicker({
            	  minDate: new Date(), dateFormat: 'yy/mm/dd'
            	});

            	jQuery(document).on('change', '#checkin', (event) => {
            	    const element = event.target;
            	    console.log(element.value);
            	    jQuery('#checkout').datepicker('option', 'minDate', element.value);
            	});
        </script>
    </jsp:attribute>
    <jsp:body>
        <h2><c:if test="${book['new']}">New </c:if>Book</h2>

        <b>Pet</b>
        <table class="table table-striped">
            <thead>
            <tr>
                <th>Name</th>
                <th>Birth Date</th>
                <th>Type</th>
                <th>Owner</th>
            </tr>
            </thead>
            <tr>
                <td><c:out value="${book.pet.name}"/></td>
                <td><petclinic:localDate date="${book.pet.birthDate}" pattern="yyyy/MM/dd"/></td>
                <td><c:out value="${book.pet.type.name}"/></td>
                <td><c:out value="${book.pet.owner.firstName} ${visit.pet.owner.lastName}"/></td>
            </tr>
        </table>

        <form:form modelAttribute="book" class="form-horizontal">
            <div class="form-group has-feedback">
           		<div class="control-group">
                    <petclinic:selectField name="room" label="Room Number " names="${rooms}" size="5"/>
              	</div>
            	<petclinic:inputField label="Check In" name="checkin"/>
                <petclinic:inputField label="Check Out" name="checkout"/>
                <petclinic:inputField label="Description" name="details"/>
            </div>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <input type="hidden" name="petId" value="${book.pet.id}"/>
                    <button class="btn btn-default" type="submit">Add Book</button>
                </div>
            </div>
        </form:form>

        <br/>
        <b>Previous Books</b>
        <table class="table table-striped">
            <tr>
            	<th>Room Number</th>
            	<th>Check In</th>
                <th>Check Out</th>
                <th>Description</th>
            </tr>
            <c:forEach var="book" items="${book.pet.books}">
                <c:if test="${!book['new']}">
                    <tr>
                    	<td><c:out value="${book.room.id}"/></td>
                        <td><petclinic:localDate date="${book.checkin}" pattern="yyyy/MM/dd"/></td>
                        <td><petclinic:localDate date="${book.checkout}" pattern="yyyy/MM/dd"/></td>
                        <td><c:out value="${book.details}"/></td>
                    </tr>
                </c:if>
            </c:forEach>
        </table>
    </jsp:body>

</petclinic:layout>
