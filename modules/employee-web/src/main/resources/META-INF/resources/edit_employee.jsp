<%@ include file="/init.jsp" %>

<%-- Imports --%>
<%@ page pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.hrms.employee.model.Employee" %>
<%@ page import="com.hrms.employee.model.Department" %>
<%@ page import="com.hrms.employee.model.Designation" %>

<%-- Read request attributes --%>
<%
    Employee employee = (Employee) request.getAttribute("employee");
    List<Department> departments = (List<Department>) request.getAttribute("departments");
    List<Designation> designations = (List<Designation>) request.getAttribute("designations");
    
    // Safety Fallback check
    if (employee == null) {
        employee = (Employee) request.getAttribute("com.hrms.employee.model.Employee");
    }
%>

<div class="container-fluid my-4">

    <h2>Edit Employee</h2>

    <portlet:actionURL name="/employee/update" var="updateEmployeeURL" />

    <form action="${updateEmployeeURL}" method="post" class="employee-edit-form">

        <%-- Hidden Field: employeeId --%>
        <input type="hidden" name="<portlet:namespace />employeeId" 
               value="<%= (employee != null) ? employee.getEmployeeId() : "" %>" />

        <%-- Text Field: employeeCode --%>
        <div class="form-group">
            <label for="employeeCode">Employee Code:</label>
            <input type="text" id="employeeCode" name="<portlet:namespace />employeeCode" class="form-control" 
                   value="<%= (employee != null && employee.getEmployeeCode() != null) ? employee.getEmployeeCode() : "" %>" required />
        </div>

        <%-- Text Field: firstName --%>
        <div class="form-group">
            <label for="firstName">First Name:</label>
            <input type="text" id="firstName" name="<portlet:namespace />firstName" class="form-control" 
                   value="<%= (employee != null && employee.getFirstName() != null) ? employee.getFirstName() : "" %>" required />
        </div>

        <%-- Text Field: lastName --%>
        <div class="form-group">
            <label for="lastName">Last Name:</label>
            <input type="text" id="lastName" name="<portlet:namespace />lastName" class="form-control" 
                   value="<%= (employee != null && employee.getLastName() != null) ? employee.getLastName() : "" %>" required />
        </div>

        <%-- Text Field: email --%>
        <div class="form-group">
            <label for="email">Email:</label>
            <input type="email" id="email" name="<portlet:namespace />email" class="form-control" 
                   value="<%= (employee != null && employee.getEmail() != null) ? employee.getEmail() : "" %>" required />
        </div>

        <%-- Text Field: phoneNumber --%>
        <div class="form-group">
            <label for="phoneNumber">Phone Number:</label>
            <input type="tel" id="phoneNumber" name="<portlet:namespace />phoneNumber" class="form-control" 
                   value="<%= (employee != null && employee.getPhoneNumber() != null) ? employee.getPhoneNumber() : "" %>" />
        </div>

        <%-- Preselected Dropdown Substitution: Department --%>
        <div class="form-group">
            <label for="department">Department:</label>
            <select id="department" name="<portlet:namespace />department" class="form-control">
                <option value="">Select Department</option>
                <% if (departments != null) { 
                    for (Department dept : departments) { 
                        boolean isSelected = employee != null && dept.getDepartmentName() != null && dept.getDepartmentName().equals(employee.getDepartment());
                %>
                        <option value="<%= dept.getDepartmentName() %>" <%= isSelected ? "selected" : "" %>><%= dept.getDepartmentName() %></option>
                <%  } 
                } %>
            </select>
        </div>

        <%-- Preselected Dropdown Substitution: Designation --%>
        <div class="form-group">
            <label for="designation">Designation:</label>
            <select id="designation" name="<portlet:namespace />designation" class="form-control">
                <option value="">Select Designation</option>
                <% if (designations != null) { 
                    for (Designation desig : designations) { 
                        boolean isSelected = employee != null && desig.getDesignationName() != null && desig.getDesignationName().equals(employee.getDesignation());
                %>
                        <option value="<%= desig.getDesignationName() %>" <%= isSelected ? "selected" : "" %>><%= desig.getDesignationName() %></option>
                <%  } 
                } %>
            </select>
        </div>

        <%-- Text Field: status --%>
        <div class="form-group">
            <label for="status">Status:</label>
            <input type="text" id="status" name="<portlet:namespace />status" class="form-control" 
                   value="<%= (employee != null && employee.getStatus() != null) ? employee.getStatus() : "" %>" />
        </div>
        
        <%-- Form Actions Block --%>
        <div class="form-actions mt-4">
            <button type="submit" class="btn btn-success">Update Employee</button>
            <a href="/web/hrms/employee-management" class="btn btn-secondary ml-2">Cancel</a>
        </div>
        
    </form>

</div>