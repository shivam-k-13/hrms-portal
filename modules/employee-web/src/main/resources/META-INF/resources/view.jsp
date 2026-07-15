<%@ include file="/init.jsp" %>

<%-- 2. Imports --%>
<%@ page pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
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
    
    // Reports Attributes
    Integer reportTotalEmployees = (Integer) request.getAttribute("reportTotalEmployees");
    Integer reportActiveEmployees = (Integer) request.getAttribute("reportActiveEmployees");
    Integer reportInactiveEmployees = (Integer) request.getAttribute("reportInactiveEmployees");
    Map<String, Integer> departmentWiseCount = (Map<String, Integer>) request.getAttribute("departmentWiseCount");
    Map<String, Integer> designationWiseCount = (Map<String, Integer>) request.getAttribute("designationWiseCount");
    
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

    int rTotal = (reportTotalEmployees != null) ? reportTotalEmployees : 0;
    int rActive = (reportActiveEmployees != null) ? reportActiveEmployees : 0;
    int rInactive = (reportInactiveEmployees != null) ? reportInactiveEmployees : 0;
%>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" />
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700;800;900&display=swap" />

<style type="text/css">
    .hrms-wrapper { background: #f8fafc; font-family: "Inter", sans-serif; font-size: 14px; padding: 2rem; min-height: calc(100vh - 80px); }
    
    /* Headers */
    .page-header { background: linear-gradient(135deg, #1e293b 0%, #0f172a 100%); padding: 2rem 2.5rem; border-radius: 20px; color: white; margin-bottom: 2rem; box-shadow: 0 10px 25px rgba(0,0,0,0.1); display: flex; justify-content: space-between; align-items: center; }
    .page-header h2 { font-size: 1.8rem; font-weight: 900; margin: 0 0 0.25rem; color: white; }
    .page-header p { margin: 0; font-size: 0.95rem; color: #cbd5e1; font-weight: 500; }
    .btn-back-inline { display: inline-flex; align-items: center; gap: 0.5rem; color: white; text-decoration: none; font-weight: 600; font-size: 0.85rem; background: rgba(255, 255, 255, 0.15); padding: 0.4rem 1rem; border-radius: 999px; border: 1px solid rgba(255, 255, 255, 0.2); transition: 0.2s; }
    .btn-back-inline:hover { background: rgba(255, 255, 255, 0.25); transform: translateX(-4px); color: white; }
    .header-icon { font-size: 3rem; opacity: 0.9; color: #38bdf8; }
    
    /* Cards */
    .hrms-card { background: #ffffff; border: 1px solid #e2e8f0; border-radius: 20px; box-shadow: 0 10px 30px rgba(0, 86, 179, 0.06); overflow: hidden; margin-bottom: 2rem; }
    .card-header-accent { background: #f1f5f9; padding: 1.25rem 1.5rem; border-bottom: 1px solid #e2e8f0; font-weight: 800; color: #0f172a; font-size: 1.1rem; display: flex; justify-content: space-between; align-items: center; }
    .card-body-padded { padding: 1.5rem; }

    /* Forms */
    .form-grid { display: grid; grid-template-columns: 1fr 1fr; gap: 1.5rem; }
    .form-group { margin-bottom: 1.25rem; }
    .form-group.full-width { grid-column: span 2; }
    .form-group label { font-weight: 700; color: #475569; font-size: 0.85rem; margin-bottom: 0.4rem; display: block; }
    .form-control { width: 100%; padding: 0.75rem 1rem; border: 2px solid #e2e8f0; border-radius: 10px; font-family: "Inter", sans-serif; font-size: 0.9rem; transition: 0.2s; outline: none; background: #ffffff; }
    .form-control:focus { border-color: #0088cc; box-shadow: 0 0 0 3px rgba(0, 136, 204, 0.1); }
    .form-control[type="file"] { padding: 0.5rem 1rem; }
    select.form-control { -webkit-appearance: none; -moz-appearance: none; appearance: none; background-image: url('data:image/svg+xml;utf8,<svg fill="%2364748b" height="24" viewBox="0 0 24 24" width="24" xmlns="http://www.w3.org/2000/svg"><path d="M7 10l5 5 5-5z"/><path d="M0 0h24v24H0z" fill="none"/></svg>'); background-repeat: no-repeat; background-position: right 0.75rem center; cursor: pointer; }
    .btn-submit { background: linear-gradient(135deg, #0056b3 0%, #0088cc 100%); color: white; border: none; padding: 0.75rem 1.5rem; border-radius: 10px; font-weight: 800; font-size: 0.95rem; cursor: pointer; transition: 0.2s; }
    .btn-submit:hover { box-shadow: 0 8px 15px rgba(0, 136, 204, 0.25); transform: translateY(-2px); }

    /* Tables */
    .table-container { padding: 0 1.5rem 1.5rem; overflow-x: auto; }
    .hrms-table { width: 100%; border-collapse: collapse; margin-top: 1rem; }
    .hrms-table th { background: #f1f5f9; padding: 1rem; color: #64748b; font-weight: 700; font-size: 0.85rem; text-align: left; border-bottom: 2px solid #e2e8f0; text-transform: uppercase; letter-spacing: 0.05em; }
    .hrms-table td { padding: 1rem; color: #0f172a; font-weight: 600; font-size: 0.85rem; border-bottom: 1px solid #f1f5f9; vertical-align: middle; }
    
    /* Search Bar */
    .search-bar-wrapper { display: flex; gap: 1rem; padding: 1rem 1.5rem; background: #ffffff; border-bottom: 1px solid #e2e8f0; align-items: flex-end; }
    
    /* Action Buttons */
    .btn-action { display: inline-flex; align-items: center; justify-content: center; gap: 0.4rem; padding: 0.4rem 0.8rem; border-radius: 8px; font-size: 0.8rem; font-weight: 700; text-decoration: none; transition: 0.2s; border: none; cursor: pointer; }
    .btn-edit { background: #e0f2fe; color: #0284c7; border: 1px solid #bae6fd; }
    .btn-edit:hover { background: #0284c7; color: white; }
    .btn-delete { background: #fef2f2; color: #b91c1c; border: 1px solid #fecaca; }
    .btn-delete:hover { background: #ef4444; color: white; }
    .btn-view { background: #f3e8ff; color: #7e22ce; border: 1px solid #e9d5ff; }
    .btn-view:hover { background: #7e22ce; color: white; }

    /* Status Badges */
    .status-badge { padding: 0.35rem 0.85rem; border-radius: 999px; font-size: 0.75rem; font-weight: 800; display: inline-block; }
    .status-active { background: #ecfdf5; color: #047857; border: 1px solid #a7f3d0; }
    .status-inactive { background: #fef2f2; color: #b91c1c; border: 1px solid #fecaca; }
    .status-default { background: #f1f5f9; color: #475569; border: 1px solid #e2e8f0; }

    /* Dashboard/Report Cards */
    .dashboard-grid { display: grid; gap: 1.5rem; grid-template-columns: repeat(3, 1fr); margin-bottom: 2rem; }
    .metric-label { font-size: 0.8rem; font-weight: 800; color: #64748b; text-transform: uppercase; letter-spacing: 0.05em; display: flex; justify-content: space-between; align-items: center; margin-bottom: 0.75rem; }
    .metric-value { font-size: 2.5rem; font-weight: 900; color: #0f172a; line-height: 1; }
    .accent-blue { border-top: 5px solid #3b82f6 !important; }
    .accent-green { border-top: 5px solid #10b981 !important; }
    .accent-orange { border-top: 5px solid #f59e0b !important; }

    /* Custom Pagination */
    .hrms-pagination { display: flex; justify-content: space-between; align-items: center; padding: 1rem 1.5rem; background: #f8fafc; border-top: 1px solid #e2e8f0; }
    .page-controls { display: flex; gap: 0.25rem; list-style: none; padding: 0; margin: 0; }
    .page-item .page-link { display: flex; align-items: center; justify-content: center; min-width: 32px; height: 32px; padding: 0 0.5rem; border-radius: 6px; font-weight: 600; font-size: 0.85rem; color: #475569; text-decoration: none; border: 1px solid transparent; transition: 0.2s; }
    .page-item:not(.disabled) .page-link:hover { background: #e2e8f0; }
    .page-item.active .page-link { background: #0088cc; color: white; border-color: #0088cc; }
    .page-item.disabled .page-link { opacity: 0.5; cursor: not-allowed; }
</style>

<div class="hrms-wrapper">

    <%-- ========================================== --%>
    <%-- ROUTE: DESIGNATION MANAGEMENT              --%>
    <%-- ========================================== --%>
    <% if (currentURL.contains("designation-management")) { %>
        
        <header class="page-header">
            <div>
                <div style="margin-bottom: 1rem;">
                    <a href="/web/hrms/dashboard-router" class="btn-back-inline"><i class="fa-solid fa-arrow-left"></i> Dashboard</a>
                </div>
                <h2>Designation Management</h2>
                <p>Add and review corporate designations.</p>
            </div>
            <div class="header-icon"><i class="fa-solid fa-briefcase"></i></div>
        </header>

        <% if (sManageDesignations) { %>
            <div class="hrms-card" style="border-top: 5px solid #0088cc;">
                <div class="card-header-accent">
                    <div><i class="fa-solid fa-plus-circle" style="color: #0088cc; margin-right: 0.5rem;"></i> Add New Designation</div>
                </div>
                <div class="card-body-padded">
                    <portlet:actionURL name="/designation/add" var="addDesignationURL" />
                    <form action="${addDesignationURL}" method="post">
                        <div class="form-grid">
                            <div class="form-group">
                                <label for="designationCode">Designation Code</label>
                                <input type="text" id="designationCode" name="<portlet:namespace />designationCode" class="form-control" required placeholder="e.g. SE-01" />
                            </div>
                            <div class="form-group">
                                <label for="designationName">Designation Name</label>
                                <input type="text" id="designationName" name="<portlet:namespace />designationName" class="form-control" required placeholder="e.g. Software Engineer" />
                            </div>
                            <div class="form-group">
                                <label for="status">Status</label>
                                <select id="status" name="<portlet:namespace />status" class="form-control">
                                    <option value="Active">Active</option>
                                    <option value="Inactive">Inactive</option>
                                </select>
                            </div>
                            <div class="form-group full-width">
                                <label for="description">Description</label>
                                <textarea id="description" name="<portlet:namespace />description" class="form-control" rows="2" placeholder="Brief job role description..."></textarea>
                            </div>
                        </div>
                        <div style="margin-top: 1rem;">
                            <button type="submit" class="btn-submit"><i class="fa-solid fa-save"></i> Save Designation</button>
                        </div>
                    </form>
                </div>
            </div>
        <% } %>
        
        <div class="hrms-card">
            <div class="card-header-accent">
                <div><i class="fa-solid fa-list" style="margin-right: 0.5rem;"></i> Designation Directory</div>
            </div>
            <div class="table-container">
                <table class="hrms-table">
                    <thead>
                        <tr>
                            <th>Code</th>
                            <th>Name</th>
                            <th>Description</th>
                            <th>Status</th>
                            <% if (sManageDesignations) { %>
                                <th style="width: 150px;">Actions</th>
                            <% } %>
                        </tr>
                    </thead>
                    <tbody>
                        <% if (designations != null && !designations.isEmpty()) { 
                            for (Designation designation : designations) { 
                                String stat = (designation.getStatus() != null) ? designation.getStatus() : "";
                                String badgeClass = stat.equalsIgnoreCase("Active") ? "status-active" : (stat.equalsIgnoreCase("Inactive") ? "status-inactive" : "status-default");
                        %>
                            <tr>
                                <td style="font-weight: 800; color: #64748b;"><%= designation.getDesignationCode() %></td>
                                <td style="font-weight: 700;"><%= designation.getDesignationName() %></td>
                                <td><%= designation.getDescription() %></td>
                                <td><span class="status-badge <%= badgeClass %>"><%= stat %></span></td>
                                <% if (sManageDesignations) { %>
                                    <td>
                                        <portlet:renderURL var="editDesignationURL">
                                            <portlet:param name="mvcRenderCommandName" value="/designation/edit" />
                                            <portlet:param name="designationId" value="<%= String.valueOf(designation.getDesignationId()) %>" />
                                        </portlet:renderURL>
                                        <portlet:actionURL name="/designation/delete" var="deleteDesignationURL">
                                            <portlet:param name="designationId" value="<%= String.valueOf(designation.getDesignationId()) %>" />
                                        </portlet:actionURL>
                                        <div style="display: flex; gap: 0.5rem;">
                                            <a href="${editDesignationURL}" class="btn-action btn-edit" title="Edit"><i class="fa-solid fa-pen"></i></a>
                                            <form action="${deleteDesignationURL}" method="post" style="margin:0;">
                                                <button type="submit" class="btn-action btn-delete" title="Delete" onclick="return confirm('Are you sure you want to delete this designation?');"><i class="fa-solid fa-trash"></i></button>
                                            </form>
                                        </div>
                                    </td>
                                <% } %>
                            </tr>
                        <%  } 
                        } else { %>
                            <tr><td colspan="<%= sManageDesignations ? 5 : 4 %>" style="text-align: center; color: #64748b; padding: 2rem;">No designations found in the database.</td></tr>
                        <% } %>
                    </tbody>
                </table>
            </div>
        </div>

    <%-- ========================================== --%>
    <%-- ROUTE: DEPARTMENT MANAGEMENT               --%>
    <%-- ========================================== --%>
    <% } else if (currentURL.contains("department-management")) { %>
        
        <header class="page-header">
            <div>
                <div style="margin-bottom: 1rem;">
                    <a href="/web/hrms/dashboard-router" class="btn-back-inline"><i class="fa-solid fa-arrow-left"></i> Dashboard</a>
                </div>
                <h2>Department Management</h2>
                <p>Organize organizational structures and units.</p>
            </div>
            <div class="header-icon"><i class="fa-solid fa-sitemap"></i></div>
        </header>

        <% if (sManageDepartments) { %>
            <div class="hrms-card" style="border-top: 5px solid #10b981;">
                <div class="card-header-accent">
                    <div><i class="fa-solid fa-plus-circle" style="color: #10b981; margin-right: 0.5rem;"></i> Add New Department</div>
                </div>
                <div class="card-body-padded">
                    <portlet:actionURL name="/department/add" var="addDepartmentURL" />
                    <form action="${addDepartmentURL}" method="post">
                        <div class="form-grid">
                            <div class="form-group">
                                <label for="departmentCode">Department Code</label>
                                <input type="text" id="departmentCode" name="<portlet:namespace />departmentCode" class="form-control" required placeholder="e.g. IT-01" />
                            </div>
                            <div class="form-group">
                                <label for="departmentName">Department Name</label>
                                <input type="text" id="departmentName" name="<portlet:namespace />departmentName" class="form-control" required placeholder="e.g. Information Technology" />
                            </div>
                            <div class="form-group">
                                <label for="status">Status</label>
                                <select id="status" name="<portlet:namespace />status" class="form-control">
                                    <option value="Active">Active</option>
                                    <option value="Inactive">Inactive</option>
                                </select>
                            </div>
                            <div class="form-group full-width">
                                <label for="description">Description</label>
                                <textarea id="description" name="<portlet:namespace />description" class="form-control" rows="2" placeholder="Brief functional description..."></textarea>
                            </div>
                        </div>
                        <div style="margin-top: 1rem;">
                            <button type="submit" class="btn-submit" style="background: linear-gradient(135deg, #047857 0%, #10b981 100%);"><i class="fa-solid fa-save"></i> Save Department</button>
                        </div>
                    </form>
                </div>
            </div>
        <% } %>
        
        <div class="hrms-card">
            <div class="card-header-accent">
                <div><i class="fa-solid fa-list" style="margin-right: 0.5rem;"></i> Department Directory</div>
            </div>
            <div class="table-container">
                <table class="hrms-table">
                    <thead>
                        <tr>
                            <th>Code</th>
                            <th>Name</th>
                            <th>Description</th>
                            <th>Status</th>
                            <% if (sManageDepartments) { %>
                                <th style="width: 150px;">Actions</th>
                            <% } %>
                        </tr>
                    </thead>
                    <tbody>
                        <% if (departments != null && !departments.isEmpty()) { 
                            for (Department department : departments) { 
                                String stat = (department.getStatus() != null) ? department.getStatus() : "";
                                String badgeClass = stat.equalsIgnoreCase("Active") ? "status-active" : (stat.equalsIgnoreCase("Inactive") ? "status-inactive" : "status-default");
                        %>
                            <tr>
                                <td style="font-weight: 800; color: #64748b;"><%= department.getDepartmentCode() %></td>
                                <td style="font-weight: 700;"><%= department.getDepartmentName() %></td>
                                <td><%= department.getDescription() %></td>
                                <td><span class="status-badge <%= badgeClass %>"><%= stat %></span></td>
                                <% if (sManageDepartments) { %>
                                    <td>
                                        <portlet:renderURL var="editDepartmentURL">
                                            <portlet:param name="mvcRenderCommandName" value="/department/edit" />
                                            <portlet:param name="departmentId" value="<%= String.valueOf(department.getDepartmentId()) %>" />
                                        </portlet:renderURL>
                                        <portlet:actionURL name="/department/delete" var="deleteDepartmentURL">
                                            <portlet:param name="departmentId" value="<%= String.valueOf(department.getDepartmentId()) %>" />
                                        </portlet:actionURL>
                                        <div style="display: flex; gap: 0.5rem;">
                                            <a href="${editDepartmentURL}" class="btn-action btn-edit" title="Edit"><i class="fa-solid fa-pen"></i></a>
                                            <form action="${deleteDepartmentURL}" method="post" style="margin:0;">
                                                <button type="submit" class="btn-action btn-delete" title="Delete" onclick="return confirm('Are you sure you want to delete this department?');"><i class="fa-solid fa-trash"></i></button>
                                            </form>
                                        </div>
                                    </td>
                                <% } %>
                            </tr>
                        <%  } 
                        } else { %>
                            <tr><td colspan="<%= sManageDepartments ? 5 : 4 %>" style="text-align: center; color: #64748b; padding: 2rem;">No departments found in the database.</td></tr>
                        <% } %>
                    </tbody>
                </table>
            </div>
        </div>

    <%-- ========================================== --%>
    <%-- ROUTE: EMPLOYEE REPORTS                    --%>
    <%-- ========================================== --%>
    <% } else if (currentURL.contains("employee-reports")) { %>

        <header class="page-header">
            <div>
                <div style="margin-bottom: 1rem;">
                    <a href="/web/hrms/dashboard-router" class="btn-back-inline"><i class="fa-solid fa-arrow-left"></i> Dashboard</a>
                </div>
                <h2>Employee Analytics & Reports</h2>
                <p>System-wide organizational matrices and headcount.</p>
            </div>
            <div class="header-icon"><i class="fa-solid fa-chart-column"></i></div>
        </header>

        <%-- Summary KPI Metrics Ribbon --%>
        <div class="dashboard-grid">
            <div class="hrms-card accent-blue">
                <div class="metric-label">Total Employees <i class="fa-solid fa-users"></i></div>
                <div class="metric-value"><%= rTotal %></div>
            </div>
            <div class="hrms-card accent-green">
                <div class="metric-label">Active Employees <i class="fa-solid fa-user-check"></i></div>
                <div class="metric-value"><%= rActive %></div>
            </div>
            <div class="hrms-card accent-orange">
                <div class="metric-label">Inactive Employees <i class="fa-solid fa-user-slash"></i></div>
                <div class="metric-value"><%= rInactive %></div>
            </div>
        </div>

        <%-- Breakdown Distribution Data Layer Tables --%>
        <div class="form-grid">
            <%-- Department Breakdown Matrix --%>
            <div class="hrms-card">
                <div class="card-header-accent">
                    <div><i class="fa-solid fa-chart-pie" style="color: #64748b; margin-right: 0.5rem;"></i> Department Distribution</div>
                </div>
                <div class="table-container" style="padding: 0;">
                    <table class="hrms-table" style="margin:0;">
                        <thead>
                            <tr>
                                <th style="border-top: none;">Department</th>
                                <th style="border-top: none; text-align: right;">Count</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% if (departmentWiseCount != null && !departmentWiseCount.isEmpty()) { 
                                for (Map.Entry<String, Integer> entry : departmentWiseCount.entrySet()) { %>
                                    <tr>
                                        <td><%= (entry.getKey() != null && !entry.getKey().isEmpty()) ? entry.getKey() : "Unassigned" %></td>
                                        <td style="text-align: right; font-weight: 800; font-size: 1rem;"><%= entry.getValue() %></td>
                                    </tr>
                                <% } 
                            } else { %>
                                <tr><td colspan="2" style="text-align: center; color: #64748b; padding: 2rem;">No data available.</td></tr>
                            <% } %>
                        </tbody>
                    </table>
                </div>
            </div>

            <%-- Designation Breakdown Matrix --%>
            <div class="hrms-card">
                <div class="card-header-accent">
                    <div><i class="fa-solid fa-layer-group" style="color: #64748b; margin-right: 0.5rem;"></i> Role Distribution</div>
                </div>
                <div class="table-container" style="padding: 0;">
                    <table class="hrms-table" style="margin:0;">
                        <thead>
                            <tr>
                                <th style="border-top: none;">Designation</th>
                                <th style="border-top: none; text-align: right;">Count</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% if (designationWiseCount != null && !designationWiseCount.isEmpty()) { 
                                for (Map.Entry<String, Integer> entry : designationWiseCount.entrySet()) { %>
                                    <tr>
                                        <td><%= (entry.getKey() != null && !entry.getKey().isEmpty()) ? entry.getKey() : "Unassigned" %></td>
                                        <td style="text-align: right; font-weight: 800; font-size: 1rem;"><%= entry.getValue() %></td>
                                    </tr>
                                <% } 
                            } else { %>
                                <tr><td colspan="2" style="text-align: center; color: #64748b; padding: 2rem;">No data available.</td></tr>
                            <% } %>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

    <%-- ========================================== --%>
    <%-- ROUTE: EMPLOYEE MANAGEMENT (DEFAULT)       --%>
    <%-- ========================================== --%>
    <% } else { %>
        
        <header class="page-header">
            <div>
                <div style="margin-bottom: 1rem;">
                    <a href="/web/hrms/dashboard-router" class="btn-back-inline"><i class="fa-solid fa-arrow-left"></i> Dashboard</a>
                </div>
                <h2>Employee Directory</h2>
                <p>Manage enterprise identities and profiles.</p>
            </div>
            <div class="header-icon"><i class="fa-solid fa-address-book"></i></div>
        </header>

        <% if (sManageEmployees) { %>
            <div class="hrms-card" style="border-top: 5px solid #8b5cf6;">
                <div class="card-header-accent">
                    <div><i class="fa-solid fa-user-plus" style="color: #8b5cf6; margin-right: 0.5rem;"></i> Onboard New Employee</div>
                </div>
                <div class="card-body-padded">
                    <portlet:actionURL name="/employee/add" var="addEmployeeURL" />
                    <form action="${addEmployeeURL}" method="post">
                        <div class="form-grid">
                            <div class="form-group">
                                <label for="employeeCode">Employee Code</label>
                                <input type="text" id="employeeCode" name="<portlet:namespace />employeeCode" class="form-control" required placeholder="e.g. EMP-101" />
                            </div>
                            <div class="form-group">
                                <label for="email">Corporate Email</label>
                                <input type="email" id="email" name="<portlet:namespace />email" class="form-control" required placeholder="user@company.com" />
                            </div>
                            
                            <div class="form-group">
                                <label for="firstName">First Name</label>
                                <input type="text" id="firstName" name="<portlet:namespace />firstName" class="form-control" required />
                            </div>
                            <div class="form-group">
                                <label for="lastName">Last Name</label>
                                <input type="text" id="lastName" name="<portlet:namespace />lastName" class="form-control" required />
                            </div>

                            <div class="form-group">
                                <label for="department">Department</label>
                                <select id="department" name="<portlet:namespace />department" class="form-control">
                                    <option value="">Select Assignment</option>
                                    <% if (departments != null) { 
                                        for (Department department : departments) { %>
                                            <option value="<%= department.getDepartmentName() %>"><%= department.getDepartmentName() %></option>
                                        <% } 
                                    } %>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="designation">Designation / Role</label>
                                <select id="designation" name="<portlet:namespace />designation" class="form-control">
                                    <option value="">Select Role</option>
                                    <% if (designations != null) { 
                                        for (Designation designation : designations) { %>
                                            <option value="<%= designation.getDesignationName() %>"><%= designation.getDesignationName() %></option>
                                        <% } 
                                    } %>
                                </select>
                            </div>

                            <div class="form-group">
                                <label for="phoneNumber">Phone Number</label>
                                <input type="tel" id="phoneNumber" name="<portlet:namespace />phoneNumber" class="form-control" placeholder="+1 (555) 000-0000" />
                            </div>
                            <div class="form-group">
                                <label for="status">Account Status</label>
                                <select id="status" name="<portlet:namespace />status" class="form-control">
                                    <option value="Active">Active</option>
                                    <option value="Inactive">Inactive</option>
                                </select>
                            </div>
                        </div>
                        <div style="margin-top: 1rem;">
                            <button type="submit" class="btn-submit" style="background: linear-gradient(135deg, #6d28d9 0%, #8b5cf6 100%);"><i class="fa-solid fa-user-check"></i> Complete Onboarding</button>
                        </div>
                    </form>
                </div>
            </div>
        <% } %>

        <div class="hrms-card">
            
            <%-- SEARCH / FILTER --%>
            <portlet:renderURL var="employeeSearchURL" />
            <form action="${employeeSearchURL}" method="get" class="search-bar-wrapper">
                <input type="hidden" name="<portlet:namespace />pageNumber" value="1" />
                <input type="hidden" name="<portlet:namespace />pageSize" value="<%= currentPageSize %>" />

                <div class="form-group" style="margin:0; flex: 2;">
                    <label for="employeeKeyword">Search Directory</label>
                    <div style="position: relative;">
                        <i class="fa-solid fa-search" style="position: absolute; left: 1rem; top: 1rem; color: #94a3b8;"></i>
                        <input type="text" id="employeeKeyword" name="<portlet:namespace />keyword" value="<%= currentKeyword %>" class="form-control" style="padding-left: 2.5rem;" placeholder="Search by name, ID, or email..." />
                    </div>
                </div>
                
                <div class="form-group" style="margin:0; flex: 1;">
                    <label for="employeeStatusFilter">Status Filter</label>
                    <select id="employeeStatusFilter" name="<portlet:namespace />statusFilter" class="form-control">
                        <option value="">All Statuses</option>
                        <option value="Active" <%= "Active".equalsIgnoreCase(currentStatusFilter) ? "selected" : "" %>>Active Only</option>
                        <option value="Inactive" <%= "Inactive".equalsIgnoreCase(currentStatusFilter) ? "selected" : "" %>>Inactive Only</option>
                    </select>
                </div>
                
                <div style="display: flex; gap: 0.5rem; margin-bottom: 2px;">
                    <button type="submit" class="btn-submit" style="padding: 0.75rem 1.5rem;"><i class="fa-solid fa-filter"></i> Apply</button>
                    <a href="/web/hrms/employee-management" class="btn-submit" style="background: #f1f5f9; color: #475569; border: 1px solid #cbd5e1;"><i class="fa-solid fa-rotate-right"></i> Reset</a>
                </div>
            </form>

            <%-- TABLE --%>
            <div class="table-container" style="padding-top: 1rem;">
                <table class="hrms-table">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Employee</th>
                            <th>Contact</th>
                            <th>Assignment</th>
                            <th>Status</th>
                            <th style="width: 140px;">Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% if (employees != null && !employees.isEmpty()) {
                            for (Employee employee : employees) { 
                                String stat = (employee.getStatus() != null) ? employee.getStatus() : "";
                                String badgeClass = stat.equalsIgnoreCase("Active") ? "status-active" : (stat.equalsIgnoreCase("Inactive") ? "status-inactive" : "status-default");
                        %>
                            <tr>
                                <td style="font-weight: 800; color: #64748b;"><%= employee.getEmployeeCode() %></td>
                                <td>
                                    <div style="font-weight: 700; color: #0f172a;"><%= employee.getFirstName() %> <%= employee.getLastName() %></div>
                                    <div style="font-size: 0.75rem; color: #64748b;"><%= employee.getDesignation() %></div>
                                </td>
                                <td>
                                    <div style="font-weight: 600;"><i class="fa-regular fa-envelope" style="color:#94a3b8;"></i> <%= employee.getEmail() %></div>
                                    <div style="font-size: 0.75rem; color: #64748b; margin-top:2px;"><i class="fa-solid fa-phone" style="color:#94a3b8;"></i> <%= (employee.getPhoneNumber() != null && !employee.getPhoneNumber().isEmpty()) ? employee.getPhoneNumber() : "N/A" %></div>
                                </td>
                                <td><%= employee.getDepartment() %></td>
                                <td><span class="status-badge <%= badgeClass %>"><%= stat %></span></td>
                                <td>
                                    <portlet:renderURL var="viewEmployeeURL">
                                        <portlet:param name="mvcRenderCommandName" value="/employee/view" />
                                        <portlet:param name="employeeId" value="<%= String.valueOf(employee.getEmployeeId()) %>" />
                                    </portlet:renderURL>

                                    <div style="display: flex; gap: 0.5rem;">
                                        <a href="${viewEmployeeURL}" class="btn-action btn-view" title="View Profile"><i class="fa-regular fa-eye"></i></a>
                                        
                                        <% if (sManageEmployees) { %>
                                            <portlet:renderURL var="editEmployeeURL">
                                                <portlet:param name="mvcRenderCommandName" value="/employee/edit" />
                                                <portlet:param name="employeeId" value="<%= String.valueOf(employee.getEmployeeId()) %>" />
                                            </portlet:renderURL>
                                            <portlet:actionURL name="/employee/delete" var="deleteEmployeeURL">
                                                <portlet:param name="employeeId" value="<%= String.valueOf(employee.getEmployeeId()) %>" />
                                            </portlet:actionURL>
                                            
                                            <a href="${editEmployeeURL}" class="btn-action btn-edit" title="Edit"><i class="fa-solid fa-pen"></i></a>
                                            <form action="${deleteEmployeeURL}" method="post" style="margin:0;">
                                                <button type="submit" class="btn-action btn-delete" title="Delete" onclick="return confirm('Are you sure you want to completely remove this employee?');"><i class="fa-solid fa-user-minus"></i></button>
                                            </form>
                                        <% } %>
                                    </div>
                                </td>
                            </tr>
                        <%  }
                        } else { %>
                            <tr><td colspan="6" style="text-align: center; color: #64748b; padding: 3rem;">No employees found matching the current criteria.</td></tr>
                        <% } %>
                    </tbody>
                </table>
            </div>

            <%-- PAGINATION --%>
            <div class="hrms-pagination">
                <div style="font-size: 0.85rem; color: #64748b;">
                    Showing page <strong style="color:#0f172a;"><%= currentPageNum %></strong> of <strong style="color:#0f172a;"><%= totalPageCount %></strong> 
                    <span style="margin: 0 0.5rem;">|</span> 
                    Total records: <strong style="color:#0f172a;"><%= totalEmpCount %></strong>
                </div>

                <ul class="page-controls">
                    <% if (currentPageNum > 1) { %>
                        <portlet:renderURL var="prevPageURL">
                            <portlet:param name="keyword" value="<%= currentKeyword %>" />
                            <portlet:param name="statusFilter" value="<%= currentStatusFilter %>" />
                            <portlet:param name="pageNumber" value="<%= String.valueOf(currentPageNum - 1) %>" />
                            <portlet:param name="pageSize" value="<%= String.valueOf(currentPageSize) %>" />
                        </portlet:renderURL>
                        <li class="page-item"><a class="page-link" href="${prevPageURL}"><i class="fa-solid fa-chevron-left"></i></a></li>
                    <% } else { %>
                        <li class="page-item disabled"><span class="page-link"><i class="fa-solid fa-chevron-left"></i></span></li>
                    <% } %>

                    <% for (int i = 1; i <= totalPageCount; i++) { %>
                        <li class="page-item <%= (i == currentPageNum) ? "active" : "" %>">
                            <% if (i == currentPageNum) { %>
                                <span class="page-link"><%= i %></span>
                            <% } else { %>
                                <portlet:renderURL var="numberedPageURL">
                                    <portlet:param name="keyword" value="<%= currentKeyword %>" />
                                    <portlet:param name="statusFilter" value="<%= currentStatusFilter %>" />
                                    <portlet:param name="pageNumber" value="<%= String.valueOf(i) %>" />
                                    <portlet:param name="pageSize" value="<%= String.valueOf(currentPageSize) %>" />
                                </portlet:renderURL>
                                <a class="page-link" href="${numberedPageURL}"><%= i %></a>
                            <% } %>
                        </li>
                    <% } %>

                    <% if (currentPageNum < totalPageCount) { %>
                        <portlet:renderURL var="nextPageURL">
                            <portlet:param name="keyword" value="<%= currentKeyword %>" />
                            <portlet:param name="statusFilter" value="<%= currentStatusFilter %>" />
                            <portlet:param name="pageNumber" value="<%= String.valueOf(currentPageNum + 1) %>" />
                            <portlet:param name="pageSize" value="<%= String.valueOf(currentPageSize) %>" />
                        </portlet:renderURL>
                        <li class="page-item"><a class="page-link" href="${nextPageURL}"><i class="fa-solid fa-chevron-right"></i></a></li>
                    <% } else { %>
                        <li class="page-item disabled"><span class="page-link"><i class="fa-solid fa-chevron-right"></i></span></li>
                    <% } %>
                </ul>
            </div>
            
        </div>
    <% } %>
</div>