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
    
    // Search/Filter Attributes
    String keyword = (String) request.getAttribute("keyword");
    String statusFilter = (String) request.getAttribute("statusFilter");
    
    // Pagination Attributes
    Integer pageNumber = (Integer) request.getAttribute("pageNumber");
    Integer pageSize = (Integer) request.getAttribute("pageSize");
    Integer totalPages = (Integer) request.getAttribute("totalPages");
    Integer totalEmployeesCount = (Integer) request.getAttribute("totalEmployeesCount");
    
    // Null safety fallbacks
    String currentURL = (currentPageFriendlyURL != null) ? currentPageFriendlyURL : "";
    boolean sManageEmployees = (canManageEmployees != null) ? canManageEmployees : false;
    boolean sManageDepartments = (canManageDepartments != null) ? canManageDepartments : false;
    boolean sManageDesignations = (canManageDesignations != null) ? canManageDesignations : false;
    
    String currentKeyword = (keyword != null) ? keyword : "";
    String currentStatusFilter = (statusFilter != null) ? statusFilter : "";
    
    int currentPageNum = (pageNumber != null) ? pageNumber : 1;
    int currentPageSize = (pageSize != null) ? pageSize : 10;
    int totalPageCount = (totalPages != null) ? totalPages : 1;
    int totalEmpCount = (totalEmployeesCount != null) ? totalEmployeesCount : 0;
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

                <%-- Dynamic Dropdown Substitution: Department --%>
                <div class="form-group">
                    <label for="department">Department:</label>
                    <select id="department" name="<portlet:namespace />department" class="form-control">
                        <option value="">Select Department</option>
                        <% if (departments != null) { 
                            for (Department department : departments) { %>
                                <option value="<%= department.getDepartmentName() %>"><%= department.getDepartmentName() %></option>
                            <% } 
                        } %>
                    </select>
                </div>

                <%-- Dynamic Dropdown Substitution: Designation --%>
                <div class="form-group">
                    <label for="designation">Designation:</label>
                    <select id="designation" name="<portlet:namespace />designation" class="form-control">
                        <option value="">Select Designation</option>
                        <% if (designations != null) { 
                            for (Designation designation : designations) { %>
                                <option value="<%= designation.getDesignationName() %>"><%= designation.getDesignationName() %></option>
                            <% } 
                        } %>
                    </select>
                </div>

                <div class="form-group">
                    <label for="status">Status:</label>
                    <input type="text" id="status" name="<portlet:namespace />status" class="form-control" />
                </div>
                
                <button type="submit" class="btn btn-primary">Save Employee</button>
            </form>

            <hr />
        <% } %>

        <%-- ========================================== --%>
        <%-- SEARCH / FILTER SECTION                    --%>
        <%-- ========================================== --%>
        <portlet:renderURL var="employeeSearchURL" />
        
        <form action="${employeeSearchURL}" method="get" class="form-inline my-4 p-3 bg-light border rounded">
            <input type="hidden" name="<portlet:namespace />pageNumber" value="1" />
            <input type="hidden" name="<portlet:namespace />pageSize" value="<%= currentPageSize %>" />

            <div class="form-group mr-3">
                <label for="employeeKeyword" class="mr-2">Search:</label>
                <input type="text" id="employeeKeyword" name="<portlet:namespace />keyword" 
                       value="<%= currentKeyword %>" class="form-control" style="min-width: 350px;"
                       placeholder="Search by code, name, email, department, designation" />
            </div>
            
            <div class="form-group mr-3">
                <label for="employeeStatusFilter" class="mr-2">Status:</label>
                <select id="employeeStatusFilter" name="<portlet:namespace />statusFilter" class="form-control">
                    <option value="">All Status</option>
                    <option value="Active" <%= "Active".equalsIgnoreCase(currentStatusFilter) ? "selected" : "" %>>Active</option>
                    <option value="Inactive" <%= "Inactive".equalsIgnoreCase(currentStatusFilter) ? "selected" : "" %>>Inactive</option>
                </select>
            </div>
            
            <button type="submit" class="btn btn-info mr-2">Search</button>
            <a href="/web/hrms/employee-management" class="btn btn-secondary">Reset</a>
        </form>

        <h2>Employee List</h2>

        <table class="table table-striped table-bordered mb-3">
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
                    <th>View</th> <%-- New Global Column --%>
                    <% if (sManageEmployees) { %>
                        <th>Edit</th>
                        <th>Delete</th>
                    <% } %>
                </tr>
            </thead>
            <tbody>
                <% if (employees != null && !employees.isEmpty()) {
                    for (Employee employee : employees) { %>
                        
                        <portlet:renderURL var="viewEmployeeURL">
                            <portlet:param name="mvcRenderCommandName" value="/employee/view" />
                            <portlet:param name="employeeId" value="<%= String.valueOf(employee.getEmployeeId()) %>" />
                        </portlet:renderURL>

                        <% if (sManageEmployees) { %>
                            <portlet:renderURL var="editEmployeeURL">
                                <portlet:param name="mvcRenderCommandName" value="/employee/edit" />
                                <portlet:param name="employeeId" value="<%= String.valueOf(employee.getEmployeeId()) %>" />
                            </portlet:renderURL>

                            <portlet:actionURL name="/employee/delete" var="deleteEmployeeURL">
                                <portlet:param name="employeeId" value="<%= String.valueOf(employee.getEmployeeId()) %>" />
                            </portlet:actionURL>
                        <% } %>

                        <tr>
                            <td><%= employee.getEmployeeCode() %></td>
                            <td><%= employee.getFirstName() %></td>
                            <td><%= employee.getLastName() %></td>
                            <td><%= employee.getEmail() %></td>
                            <td><%= employee.getPhoneNumber() %></td>
                            <td><%= employee.getDepartment() %></td>
                            <td><%= employee.getDesignation() %></td>
                            <td><%= employee.getStatus() %></td>
                            <td>
                                <a href="${viewEmployeeURL}" class="btn btn-info btn-sm">View</a>
                            </td>
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
                        <%-- Colspan shifted to 9 / 11 to support the additional View structure element --%>
                        <td colspan="<%= sManageEmployees ? 11 : 9 %>" class="text-center">No employees found.</td>
                    </tr>
                <% } %>
            </tbody>
        </table>

        <%-- ========================================== --%>
        <%-- PAGINATION NAVIGATION UI                   --%>
        <%-- ========================================== --%>
        <div class="d-flex justify-content-between align-items-center my-4">
            <div>
                <span class="text-muted">Showing page <strong><%= currentPageNum %></strong> of <strong><%= totalPageCount %></strong></span>
                <span class="mx-2 text-muted">|</span>
                <span class="text-muted">Total employees: <strong><%= totalEmpCount %></strong></span>
            </div>

            <nav aria-label="Employee List Pagination">
                <ul class="pagination mb-0">
                    
                    <%-- Previous Button Block --%>
                    <% if (currentPageNum <= 1) { %>
                        <li class="page-item disabled">
                            <span class="page-link">Previous</span>
                        </li>
                    <% } else { %>
                        <portlet:renderURL var="prevPageURL">
                            <portlet:param name="keyword" value="<%= currentKeyword %>" />
                            <portlet:param name="statusFilter" value="<%= currentStatusFilter %>" />
                            <portlet:param name="pageNumber" value="<%= String.valueOf(currentPageNum - 1) %>" />
                            <portlet:param name="pageSize" value="<%= String.valueOf(currentPageSize) %>" />
                        </portlet:renderURL>
                        <li class="page-item">
                            <a class="page-link" href="${prevPageURL}">Previous</a>
                        </li>
                    <% } %>

                    <%-- Dynamic Page Range Matrix Generation Loop --%>
                    <% for (int i = 1; i <= totalPageCount; i++) { %>
                        <% if (i == currentPageNum) { %>
                            <li class="page-item active" aria-current="page">
                                <span class="page-link"><%= i %></span>
                            </li>
                        <% } else { %>
                            <portlet:renderURL var="numberedPageURL">
                                <portlet:param name="keyword" value="<%= currentKeyword %>" />
                                <portlet:param name="statusFilter" value="<%= currentStatusFilter %>" />
                                <portlet:param name="pageNumber" value="<%= String.valueOf(i) %>" />
                                <portlet:param name="pageSize" value="<%= String.valueOf(currentPageSize) %>" />
                            </portlet:renderURL>
                            <li class="page-item">
                                <a class="page-link" href="${numberedPageURL}"><%= i %></a>
                            </li>
                        <% } %>
                    <% } %>

                    <%-- Next Button Block --%>
                    <% if (currentPageNum >= totalPageCount) { %>
                        <li class="page-item disabled">
                            <span class="page-link">Next</span>
                        </li>
                    <% } else { %>
                        <portlet:renderURL var="nextPageURL">
                            <portlet:param name="keyword" value="<%= currentKeyword %>" />
                            <portlet:param name="statusFilter" value="<%= currentStatusFilter %>" />
                            <portlet:param name="pageNumber" value="<%= String.valueOf(currentPageNum + 1) %>" />
                            <portlet:param name="pageSize" value="<%= String.valueOf(currentPageSize) %>" />
                        </portlet:renderURL>
                        <li class="page-item">
                            <a class="page-link" href="${nextPageURL}">Next</a>
                        </li>
                    <% } %>
                    
                </ul>
            </nav>
        </div>
        
    <% } %>

</div>

