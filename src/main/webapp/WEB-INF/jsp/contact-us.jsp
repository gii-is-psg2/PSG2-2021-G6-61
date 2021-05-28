<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<petclinic:layout pageName="contact-us">
	<h2>Contact Us</h2>

	<c:if test="${not empty contacts}">
		<table id="contactsTable" class="table table-striped" title="Contact-us">
			<caption>Employees</caption>
			<thead>
				<tr>
					<th scope="col">Name</th>
					<th scope="col">Telephone</th>
					<th scope="col">Email</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${contacts}" var="contact">
					<tr>
						<td>
							<c:out value="${contact.friendlyname}"/>
		                </td>
		                <td>
		                    <c:out value="${contact.phone}"/>
		                </td>
		                <td>
		                    <c:out value="${contact.email}"/>
		                </td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:if>
</petclinic:layout>

