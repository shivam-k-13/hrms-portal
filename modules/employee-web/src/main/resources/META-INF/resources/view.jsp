<%@ include file="/init.jsp" %>

<%-- 2. Imports --%>
<%@ page pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.hrms.employee.model.Employee" %>

<%-- 3. Create Action URL --%>
<portlet:actionURL name="/employee/add" var="addEmployeeURL" />

<%-- 4. Read employee list and permissions from request attributes --%>
<%
    List<Employee> employees = (List<Employee>) request.getAttribute("employees");
    Boolean canManageEmployees = (Boolean) request.getAttribute("canManageEmployees");
    
    // Default to false if the attribute is null to prevent NullPointerException
    if (canManageEmployees == null) {
        canManageEmployees = false;
    }
%>

<%-- Conditionally show Add Employee form --%>
<% if (canManageEmployees) { %>
    <%-- 5. Display heading --%>
    <h2>Add Employee</h2>

    <%-- 6. Form --%>
    <form action="${addEmployeeURL}" method="post" class="employee-form">
        
        <div class="form-group">
            <label for="employeeCode">Employee Code:</label>
            <input type="text" id="employeeCode" name="<portlet:namespace />employeeCode" class="form-control" required />
        </div>

        <div class="form-group">
            <label for="firstName">First Name:</label>
            <input type="text" id="firstName" name="<portlet:namespace />firstName" class="form-control" required />
        </div>

        <div class="form-group">
            <label for="lastName">Last Name:</label>
            <input type="text" id="lastName" name="<portlet:namespace />lastName" class="form-control" required />
        </div>

        <div class="form-group">
            <label for="email">Email:</label>
            <input type="email" id="email" name="<portlet:namespace />email" class="form-control" required />
        </div>

        <div class="form-group">
            <label for="phoneNumber">Phone Number:</label>
            <input type="tel" id="phoneNumber" name="<portlet:namespace />phoneNumber" class="form-control" />
        </div>

        <div class="form-group">
            <label for="department">Department:</label>
            <input type="text" id="department" name="<portlet:namespace />department" class="form-control" />
        </div>

        <div class="form-group">
            <label for="designation">Designation:</label>
            <input type="text" id="designation" name="<portlet:namespace />designation" class="form-control" />
        </div>

        <div class="form-group">
            <label for="status">Status:</label>
            <input type="text" id="status" name="<portlet:namespace />status" class="form-control" />
        </div>
        
        <%-- Submit Button --%>
        <button type="submit" class="btn btn-primary">Save Employee</button>
        
    </form>

    <%-- 7. Horizontal Line --%>
    <hr />
<% } %>

<%-- 8. Display heading --%>
<h2>Employee List</h2>

<%-- 9. Create HTML Table --%>
<table class="table table-striped table-bordered">
    <thead>
        <tr>
            <th>Employee Code</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Email</th>
            <th>Phone Number</th>
            <th>Department</th>
            <th>Designation</th>
            <th>Status</th>
            <%-- Conditionally show Action headers --%>
            <% if (canManageEmployees) { %>
                <th>Edit</th>
                <th>Delete</th>
            <% } %>
        </tr>
    </thead>
    <tbody>
        <%
            if (employees != null && !employees.isEmpty()) {
                for (Employee employee : employees) {
        %>
                    <%-- Create Render URL for Editing --%>
                    <portlet:renderURL var="editEmployeeURL">
                        <portlet:param name="mvcRenderCommandName" value="/employee/edit" />
                        <portlet:param name="employeeId" value="<%= String.valueOf(employee.getEmployeeId()) %>" />
                    </portlet:renderURL>

                    <%-- Corrected Delete Action URL Pattern --%>
                    <portlet:actionURL name="/employee/delete" var="deleteEmployeeURL">
                        <portlet:param name="employeeId" value="<%= String.valueOf(employee.getEmployeeId()) %>" />
                    </portlet:actionURL>

                    <tr>
                        <td><%= employee.getEmployeeCode() %></td>
                        <td><%= employee.getFirstName() %></td>
                        <td><%= employee.getLastName() %></td>
                        <td><%= employee.getEmail() %></td>
                        <td><%= employee.getPhoneNumber() %></td>
                        <td><%= employee.getDepartment() %></td>
                        <td><%= employee.getDesignation() %></td>
                        <td><%= employee.getStatus() %></td>
                        
                        <%-- Conditionally show Action buttons per row --%>
                        <% if (canManageEmployees) { %>
                            <%-- Edit Button Column --%>
                            <td>
                                <a href="${editEmployeeURL}" class="btn btn-secondary btn-sm">Edit</a>
                            </td>
                            <%-- Delete Column --%>
                            <td>
                                <form action="${deleteEmployeeURL}" method="post" style="display:inline;">
                                    <button type="submit" class="btn btn-danger btn-sm" onclick="return confirm('Are you sure you want to delete this employee?');">
                                        Delete
                                    </button>
                                </form>
                            </td>
                        <% } %>
                    </tr>
        <%
                }
            } else {
        %>
                <tr>
                    <%-- Adjust dynamic colspan depending on whether columns are hidden --%>
                    <td colspan="<%= canManageEmployees ? 10 : 8 %>" class="text-center">No employees found.</td>
                </tr>
        <%
            }
        %>
    </tbody>
</table>