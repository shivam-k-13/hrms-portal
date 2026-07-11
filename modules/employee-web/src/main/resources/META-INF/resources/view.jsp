<%@ include file="/init.jsp" %>

<%-- 2. Imports --%>
<%@ page pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.hrms.employee.model.Employee" %>
<%@ page import="com.hrms.employee.model.Department" %>
<%@ page import="com.hrms.employee.model.Designation" %>

<%-- Read request attributes --%>
<%
    String currentPageFriendlyURL = (String) request.getAttribute("currentPageFriendlyURL");
    List<Employee> employees = (List<Employee>) request.getAttribute("employees");
    List<Department> departments = (List<Department>) request.getAttribute("departments");
    List<Designation> designations = (List<Designation>) request.getAttribute("designations");
    
    Boolean canManageEmployees = (Boolean) request.getAttribute("canManageEmployees");
    Boolean canManageDepartments = (Boolean) request.getAttribute("canManageDepartments");
    Boolean canManageDesignations = (Boolean) request.getAttribute("canManageDesignations");
    
    // Null safety fallbacks
    String currentURL = (currentPageFriendlyURL != null) ? currentPageFriendlyURL : "";
    boolean sManageEmployees = (canManageEmployees != null) ? canManageEmployees : false;
    boolean sManageDepartments = (canManageDepartments != null) ? canManageDepartments : false;
    boolean sManageDesignations = (canManageDesignations != null) ? canManageDesignations : false;
%>

<div class="container-fluid my-4">

    <%-- ========================================== --%>
    <%-- ROUTE: DESIGNATION MANAGEMENT              --%>
    <%-- ========================================== --%>
    <% if (currentURL.contains("designation-management")) { %>
        
        <%-- Conditionally show Add Designation Form --%>
        <% if (sManageDesignations) { %>
            <h2>Add Designation</h2>
            
            <portlet:actionURL name="/designation/add" var="addDesignationURL" />
            
            <form action="${addDesignationURL}" method="post" class="designation-form mb-4">
                <div class="form-group">
                    <label for="designationCode">Designation Code:</label>
                    <input type="text" id="designationCode" name="<portlet:namespace />designationCode" class="form-control" required />
                </div>
                
                <div class="form-group">
                    <label for="designationName">Designation Name:</label>
                    <input type="text" id="designationName" name="<portlet:namespace />designationName" class="form-control" required />
                </div>
                
                <div class="form-group">
                    <label for="description">Description:</label>
                    <textarea id="description" name="<portlet:namespace />description" class="form-control" rows="3"></textarea>
                </div>
                
                <div class="form-group">
                    <label for="status">Status:</label>
                    <input type="text" id="status" name="<portlet:namespace />status" class="form-control" />
                </div>
                
                <button type="submit" class="btn btn-primary">Save Designation</button>
            </form>
            
            <hr />
        <% } %>
        
        <%-- Display Designation List --%>
        <h2>Designation List</h2>
        <table class="table table-striped table-bordered">
            <thead>
                <tr>
                    <th>Designation Code</th>
                    <th>Designation Name</th>
                    <th>Description</th>
                    <th>Status</th>
                    <% if (sManageDesignations) { %>
                        <th>Edit</th>
                        <th>Delete</th>
                    <% } %>
                </tr>
            </thead>
            <tbody>
                <% if (designations != null && !designations.isEmpty()) { 
                    for (Designation designation : designations) { %>
                        
                        <% if (sManageDesignations) { %>
                            <portlet:renderURL var="editDesignationURL">
                                <portlet:param name="mvcRenderCommandName" value="/designation/edit" />
                                <portlet:param name="designationId" value="<%= String.valueOf(designation.getDesignationId()) %>" />
                            </portlet:renderURL>

                            <portlet:actionURL name="/designation/delete" var="deleteDesignationURL">
                                <portlet:param name="designationId" value="<%= String.valueOf(designation.getDesignationId()) %>" />
                            </portlet:actionURL>
                        <% } %>

                        <tr>
                            <td><%= designation.getDesignationCode() %></td>
                            <td><%= designation.getDesignationName() %></td>
                            <td><%= designation.getDescription() %></td>
                            <td><%= designation.getStatus() %></td>
                            <% if (sManageDesignations) { %>
                                <td>
                                    <a href="${editDesignationURL}" class="btn btn-secondary btn-sm">Edit</a>
                                </td>
                                <td>
                                    <form action="${deleteDesignationURL}" method="post" style="display:inline;">
                                        <button type="submit" class="btn btn-danger btn-sm" onclick="return confirm('Are you sure you want to delete this designation?');">
                                            Delete
                                        </button>
                                    </form>
                                </td>
                            <% } %>
                        </tr>
                    <% } 
                } else { %>
                    <tr>
                        <td colspan="<%= sManageDesignations ? 6 : 4 %>" class="text-center">No designations found.</td>
                    </tr>
                <% } %>
            </tbody>
        </table>

    <%-- ========================================== --%>
    <%-- ROUTE: DEPARTMENT MANAGEMENT               --%>
    <%-- ========================================== --%>
    <% } else if (currentURL.contains("department-management")) { %>
        
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
        
        <h2>Department List</h2>
        <table class="table table-striped table-bordered">
            <thead>
                <tr>
                    <th>Department Code</th>
                    <th>Department Name</th>
                    <th>Description</th>
                    <th>Status</th>
                    <% if (sManageDepartments) { %>
                        <th>Edit</th>
                        <th>Delete</th>
                    <% } %>
                </tr>
            </thead>
            <tbody>
                <% if (departments != null && !departments.isEmpty()) { 
                    for (Department department : departments) { %>
                        
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
                        <td colspan="<%= sManageDepartments ? 6 : 4 %>" class="text-center">No departments found.</td>
                    </tr>
                <% } %>
            </tbody>
        </table>

    <%-- ========================================== --%>
    <%-- ROUTE: EMPLOYEE MANAGEMENT (DEFAULT)       --%>
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