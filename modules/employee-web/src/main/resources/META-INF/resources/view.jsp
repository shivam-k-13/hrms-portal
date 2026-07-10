<%@ include file="/init.jsp" %>

<%-- Imports --%>
<%@ page pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.hrms.employee.model.Employee" %>
<%@ page import="com.hrms.employee.model.Department" %>

<%-- Read request attributes --%>
<%
    String currentPageFriendlyURL = (String) request.getAttribute("currentPageFriendlyURL");
    List<Employee> employees = (List<Employee>) request.getAttribute("employees");
    List<Department> departments = (List<Department>) request.getAttribute("departments");
    Boolean canManageEmployees = (Boolean) request.getAttribute("canManageEmployees");
    Boolean canManageDepartments = (Boolean) request.getAttribute("canManageDepartments");
    
    // Null safety fallbacks
    String currentURL = (currentPageFriendlyURL != null) ? currentPageFriendlyURL : "";
    boolean sManageEmployees = (canManageEmployees != null) ? canManageEmployees : false;
    boolean sManageDepartments = (canManageDepartments != null) ? canManageDepartments : false;
%>

<div class="container-fluid my-4">

    <%-- ========================================== --%>
    <%-- ROUTE: DEPARTMENT MANAGEMENT               --%>
    <%-- ========================================== --%>
    <% if (currentURL.contains("department-management")) { %>
        
        <%-- Conditionally show Add Department Form --%>
        <% if (sManageDepartments) { %>
            <h2>Add Department</h2>
            
            <portlet:actionURL name="/department/add" var="addDepartmentURL" />
            
            <form action="${addDepartmentURL}" method="post" class="department-form mb-4">
                <div class="form-group">
                    <label for="departmentCode">Department Code:</label>
                    <input type="text" id="departmentCode" name="<portlet:namespace />departmentCode" class="form-control" required />
                </div>
                
                <div class="form-group">
                    <label for="departmentName">Department Name:</label>
                    <input type="text" id="departmentName" name="<portlet:namespace />departmentName" class="form-control" required />
                </div>
                
                <div class="form-group">
                    <label for="description">Description:</label>
                    <textarea id="description" name="<portlet:namespace />description" class="form-control" rows="3"></textarea>
                </div>
                
                <div class="form-group">
                    <label for="status">Status:</label>
                    <input type="text" id="status" name="<portlet:namespace />status" class="form-control" />
                </div>
                
                <button type="submit" class="btn btn-primary">Save Department</button>
            </form>
            
            <hr />
        <% } %>
        
        <%-- Always display Department List --%>
        <h2>Department List</h2>
        <table class="table table-striped table-bordered">
            <thead>
                <tr>
                    <th>Department Code</th>
                    <th>Department Name</th>
                    <th>Description</th>
                    <th>Status</th>
                    <%-- Show Edit and Delete headers only if permitted --%>
                    <% if (sManageDepartments) { %>
                        <th>Edit</th>
                        <th>Delete</th>
                    <% } %>
                </tr>
            </thead>
            <tbody>
                <% if (departments != null && !departments.isEmpty()) { 
                    for (Department department : departments) { %>
                        
                        <%-- Create correct action and render URLs dynamically per row if permitted --%>
                        <% if (sManageDepartments) { %>
                            <portlet:renderURL var="editDepartmentURL">
                                <portlet:param name="mvcRenderCommandName" value="/department/edit" />
                                <portlet:param name="departmentId" value="<%= String.valueOf(department.getDepartmentId()) %>" />
                            </portlet:renderURL>

                            <portlet:actionURL name="/department/delete" var="deleteDepartmentURL">
                                <portlet:param name="departmentId" value="<%= String.valueOf(department.getDepartmentId()) %>" />
                            </portlet:actionURL>
                        <% } %>

                        <tr>
                            <td><%= department.getDepartmentCode() %></td>
                            <td><%= department.getDepartmentName() %></td>
                            <td><%= department.getDescription() %></td>
                            <td><%= department.getStatus() %></td>
                            <%-- Conditionally render Edit and Delete columns --%>
                            <% if (sManageDepartments) { %>
                                <td>
                                    <a href="${editDepartmentURL}" class="btn btn-secondary btn-sm">Edit</a>
                                </td>
                                <td>
                                    <form action="${deleteDepartmentURL}" method="post" style="display:inline;">
                                        <button type="submit" class="btn btn-danger btn-sm" onclick="return confirm('Are you sure you want to delete this department?');">
                                            Delete
                                        </button>
                                    </form>
                                </td>
                            <% } %>
                        </tr>
                    <% } 
                } else { %>
                    <tr>
                        <%-- Adjust colspan dynamically based on column presence (4 base columns + 2 action columns) --%>
                        <td colspan="<%= sManageDepartments ? 6 : 4 %>" class="text-center">No departments found.</td>
                    </tr>
                <% } %>
            </tbody>
        </table>

    <%-- ========================================== --%>
    <%-- ROUTE: EMPLOYEE MANAGEMENT (DEFAULT/EXISTING)--%>
    <%-- ========================================== --%>
    <% } else { %>
        
        <portlet:actionURL name="/employee/add" var="addEmployeeURL" />

        <% if (sManageEmployees) { %>
            <h2>Add Employee</h2>

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
                
                <button type="submit" class="btn btn-primary">Save Employee</button>
            </form>

            <hr />
        <% } %>

        <h2>Employee List</h2>

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
                    <% if (sManageEmployees) { %>
                        <th>Edit</th>
                        <th>Delete</th>
                    <% } %>
                </tr>
            </thead>
            <tbody>
                <% if (employees != null && !employees.isEmpty()) {
                    for (Employee employee : employees) { %>
                        
                        <portlet:renderURL var="editEmployeeURL">
                            <portlet:param name="mvcRenderCommandName" value="/employee/edit" />
                            <portlet:param name="employeeId" value="<%= String.valueOf(employee.getEmployeeId()) %>" />
                        </portlet:renderURL>

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
                            
                            <% if (sManageEmployees) { %>
                                <td>
                                    <a href="${editEmployeeURL}" class="btn btn-secondary btn-sm">Edit</a>
                                </td>
                                <td>
                                    <form action="${deleteEmployeeURL}" method="post" style="display:inline;">
                                        <button type="submit" class="btn btn-danger btn-sm" onclick="return confirm('Are you sure you want to delete this employee?');">
                                            Delete
                                        </button>
                                    </form>
                                </td>
                            <% } %>
                        </tr>
                    <% }
                } else { %>
                    <tr>
                        <td colspan="<%= sManageEmployees ? 10 : 8 %>" class="text-center">No employees found.</td>
                    </tr>
                <% } %>
            </tbody>
        </table>
        
    <% } %>

</div>