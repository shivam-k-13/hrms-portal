<%@ include file="/init.jsp" %>

<%-- Import Employee model --%>
<%@ page import="com.hrms.employee.model.Employee" %>

<%
    // Fetch the target employee placed in request scope by the MVCRenderCommand
    Employee employee = (Employee) request.getAttribute("employee");
    
    // Fallback instantiation to prevent empty form NullPointerExceptions if missing
    if (employee == null) {
        employee = (Employee) request.getAttribute("com.hrms.employee.model.Employee");
    }
%>

<h2>Edit Employee</h2>

<%-- Create Portlet Action URL for Update --%>
<portlet:actionURL name="/employee/update" var="updateEmployeeURL" />

<form action="${updateEmployeeURL}" method="post" class="employee-edit-form">

    <%-- Hidden field containing the primary key id --%>
    <input type="hidden" name="<portlet:namespace />employeeId" value="<%= (employee != null) ? employee.getEmployeeId() : "" %>" />

    <div class="form-group">
        <label for="employeeCode">Employee Code:</label>
        <input type="text" id="employeeCode" name="<portlet:namespace />employeeCode" class="form-control" 
               value="<%= (employee != null) ? employee.getEmployeeCode() : "" %>" required />
    </div>

    <div class="form-group">
        <label for="firstName">First Name:</label>
        <input type="text" id="firstName" name="<portlet:namespace />firstName" class="form-control" 
               value="<%= (employee != null) ? employee.getFirstName() : "" %>" required />
    </div>

    <div class="form-group">
        <label for="lastName">Last Name:</label>
        <input type="text" id="lastName" name="<portlet:namespace />lastName" class="form-control" 
               value="<%= (employee != null) ? employee.getLastName() : "" %>" required />
    </div>

    <div class="form-group">
        <label for="email">Email:</label>
        <input type="email" id="email" name="<portlet:namespace />email" class="form-control" 
               value="<%= (employee != null) ? employee.getEmail() : "" %>" required />
    </div>

    <div class="form-group">
        <label for="phoneNumber">Phone Number:</label>
        <input type="tel" id="phoneNumber" name="<portlet:namespace />phoneNumber" class="form-control" 
               value="<%= (employee != null) ? employee.getPhoneNumber() : "" %>" />
    </div>

    <div class="form-group">
        <label for="department">Department:</label>
        <input type="text" id="department" name="<portlet:namespace />department" class="form-control" 
               value="<%= (employee != null) ? employee.getDepartment() : "" %>" />
    </div>

    <div class="form-group">
        <label for="designation">Designation:</label>
        <input type="text" id="designation" name="<portlet:namespace />designation" class="form-control" 
               value="<%= (employee != null) ? employee.getDesignation() : "" %>" />
    </div>

    <div class="form-group">
        <label for="status">Status:</label>
        <input type="text" id="status" name="<portlet:namespace />status" class="form-control" 
               value="<%= (employee != null) ? employee.getStatus() : "" %>" />
    </div>
    
    <%-- Form Actions --%>
    <button type="submit" class="btn btn-success">Update Employee</button>
    
    <%-- Optional Cancel button returning smoothly back to view page state --%>
    <portlet:renderURL var="cancelURL" />
    <a href="${cancelURL}" class="btn btn-secondary">Cancel</a>
    
</form>