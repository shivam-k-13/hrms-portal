<%@ include file="/init.jsp" %>

<%-- Imports --%>
<%@ page pageEncoding="UTF-8" %>

<%-- Read request attributes --%>
<%
    String currentPageFriendlyURL = (String) request.getAttribute("currentPageFriendlyURL");
    Integer totalEmployees = (Integer) request.getAttribute("totalEmployees");
    Boolean isAdmin = (Boolean) request.getAttribute("isAdmin");
    Boolean isHR = (Boolean) request.getAttribute("isHR");
    Boolean isManager = (Boolean) request.getAttribute("isManager");
    Boolean isEmployee = (Boolean) request.getAttribute("isEmployee");

    // Null safety fallbacks
    String currentURL = (currentPageFriendlyURL != null) ? currentPageFriendlyURL : "";
    int empCount = (totalEmployees != null) ? totalEmployees : 0;
    boolean sAdmin = (isAdmin != null) ? isAdmin : false;
    boolean sHR = (isHR != null) ? isHR : false;
    boolean sManager = (isManager != null) ? isManager : false;
    boolean sEmployee = (isEmployee != null) ? isEmployee : false;
%>

<div class="container-fluid my-4">

    <%-- ========================================== --%>
    <%-- 1. ROUTE: DASHBOARD ROUTER                 --%>
    <%-- ========================================== --%>
    <% if (currentURL.contains("dashboard-router")) { %>
        
        <% if (sAdmin || sHR || sManager || sEmployee) { %>
            <div class="alert alert-info">
                <h4>Redirecting to your dashboard...</h4>
                <p>Please wait while we take you to your dedicated area.</p>
            </div>

            <script type="text/javascript">
                (function() {
                    if (<%= sAdmin %>) {
                        window.location.href = '/web/hrms/admin-dashboard';
                    } else if (<%= sHR %>) {
                        window.location.href = '/web/hrms/hr-dashboard';
                    } else if (<%= sManager %>) {
                        window.location.href = '/web/hrms/manager-dashboard';
                    } else if (<%= sEmployee %>) {
                        window.location.href = '/web/hrms/employee-dashboard';
                    }
                })();
            </script>
        <% } else { %>
            <div class="alert alert-danger">
                <h4>Access denied.</h4>
                <p>You do not have a role assigned to view any corporate dashboards. Please contact your system administrator.</p>
            </div>
        <% } %>

    <%-- ========================================== --%>
    <%-- 2. ROUTE: ADMIN DASHBOARD                  --%>
    <%-- ========================================== --%>
    <% } else if (currentURL.contains("admin-dashboard")) { %>
        
        <h2 class="mb-4">Admin Dashboard</h2>
        
        <div class="row mb-4">
            <div class="col-md-4">
                <div class="card text-white bg-primary mb-3">
                    <div class="card-body">
                        <h5 class="card-title">Total Employees</h5>
                        <p class="card-text display-4"><%= empCount %></p>
                    </div>
                </div>
            </div>
        </div>

        <h3 class="mb-3">Quick Links</h3>
        <div class="row">
            <div class="col-md-3 mb-3">
                <div class="card h-100">
                    <div class="card-body text-center">
                        <h5 class="card-title">Employee Management</h5>
                        <a href="/web/hrms/employee-management" class="btn btn-outline-primary mt-2">Go to Module</a>
                    </div>
                </div>
            </div>
            <div class="col-md-3 mb-3">
                <div class="card h-100">
                    <div class="card-body text-center">
                        <h5 class="card-title">HR Dashboard</h5>
                        <a href="/web/hrms/hr-dashboard" class="btn btn-outline-primary mt-2">View Dashboard</a>
                    </div>
                </div>
            </div>
            <div class="col-md-3 mb-3">
                <div class="card h-100">
                    <div class="card-body text-center">
                        <h5 class="card-title">Manager Dashboard</h5>
                        <a href="/web/hrms/manager-dashboard" class="btn btn-outline-primary mt-2">View Dashboard</a>
                    </div>
                </div>
            </div>
            <div class="col-md-3 mb-3">
                <div class="card h-100">
                    <div class="card-body text-center">
                        <h5 class="card-title">Employee Dashboard</h5>
                        <a href="/web/hrms/employee-dashboard" class="btn btn-outline-primary mt-2">View Dashboard</a>
                    </div>
                </div>
            </div>
        </div>

    <%-- ========================================== --%>
    <%-- 3. ROUTE: HR DASHBOARD                     --%>
    <%-- ========================================== --%>
    <% } else if (currentURL.contains("hr-dashboard")) { %>
        
        <h2 class="mb-4">HR Dashboard</h2>
        
        <div class="row mb-4">
            <div class="col-md-4">
                <div class="card text-white bg-info mb-3">
                    <div class="card-body">
                        <h5 class="card-title">Total Employees</h5>
                        <p class="card-text display-4"><%= empCount %></p>
                    </div>
                </div>
            </div>
        </div>

        <h3 class="mb-3">Quick Links</h3>
        <div class="row">
            <div class="col-md-4 mb-3">
                <div class="card h-100">
                    <div class="card-body text-center">
                        <h5 class="card-title">Employee Management</h5>
                        <a href="/web/hrms/employee-management" class="btn btn-outline-info mt-2">Manage</a>
                    </div>
                </div>
            </div>
            <div class="col-md-4 mb-3">
                <div class="card h-100">
                    <div class="card-body text-center">
                        <h5 class="card-title">Department Management</h5>
                        <a href="/web/hrms/department-management" class="btn btn-outline-info mt-2">Manage</a>
                    </div>
                </div>
            </div>
            <div class="col-md-4 mb-3">
                <div class="card h-100">
                    <div class="card-body text-center">
                        <h5 class="card-title">Designation Management</h5>
                        <a href="/web/hrms/designation-management" class="btn btn-outline-info mt-2">Manage</a>
                    </div>
                </div>
            </div>
        </div>

    <%-- ========================================== --%>
    <%-- 4. ROUTE: MANAGER DASHBOARD                --%>
    <%-- ========================================== --%>
    <% } else if (currentURL.contains("manager-dashboard")) { %>
        
        <h2 class="mb-4">Manager Dashboard</h2>
        
        <div class="row">
            <div class="col-md-4 mb-3">
                <div class="card text-white bg-success h-100">
                    <div class="card-body">
                        <h5 class="card-title">Team Attendance</h5>
                        <p class="card-text">Monitor check-ins, check-outs, and daily operational presence tracking.</p>
                    </div>
                </div>
            </div>
            <div class="col-md-4 mb-3">
                <div class="card text-white bg-warning h-100">
                    <div class="card-body">
                        <h5 class="card-title">Pending Leave Approvals</h5>
                        <p class="card-text">Review, authorize, or deny incoming time-off requests submitted by staff.</p>
                    </div>
                </div>
            </div>
            <div class="col-md-4 mb-3">
                <div class="card text-white bg-secondary h-100">
                    <div class="card-body">
                        <h5 class="card-title">Team Reports</h5>
                        <p class="card-text">Generate aggregate metrics on resource bandwidth and departmental production.</p>
                    </div>
                </div>
            </div>
        </div>

    <%-- ========================================== --%>
    <%-- 5. ROUTE: EMPLOYEE DASHBOARD               --%>
    <%-- ========================================== --%>
    <% } else if (currentURL.contains("employee-dashboard")) { %>
        
        <h2 class="mb-4">Employee Dashboard</h2>
        
        <div class="row">
            <div class="col-md-3 mb-3">
                <div class="card h-100 border-dark text-center shadow-sm">
                    <div class="card-body d-flex flex-column justify-content-between">
                        <div>
                            <h5 class="card-title font-weight-bold">My Profile</h5>
                            <p class="card-text text-muted small">Update your dynamic corporate information records.</p>
                        </div>
                        <a href="/web/hrms/my-profile" class="btn btn-dark btn-block mt-3">Open Profile</a>
                    </div>
                </div>
            </div>
            <div class="col-md-3 mb-3">
                <div class="card h-100 border-dark text-center shadow-sm">
                    <div class="card-body d-flex flex-column justify-content-between">
                        <div>
                            <h5 class="card-title font-weight-bold">My Attendance</h5>
                            <p class="card-text text-muted small">View daily history logs and total work time tracking.</p>
                        </div>
                        <a href="/web/hrms/my-attendance" class="btn btn-dark btn-block mt-3">Open Attendance</a>
                    </div>
                </div>
            </div>
            <div class="col-md-3 mb-3">
                <div class="card h-100 border-dark text-center shadow-sm">
                    <div class="card-body d-flex flex-column justify-content-between">
                        <div>
                            <h5 class="card-title font-weight-bold">My Leaves</h5>
                            <p class="card-text text-muted small">Request time-off allocations or check balances.</p>
                        </div>
                        <a href="/web/hrms/my-leaves" class="btn btn-dark btn-block mt-3">Open Leaves</a>
                    </div>
                </div>
            </div>
            <div class="col-md-3 mb-3">
                <div class="card h-100 border-dark text-center shadow-sm">
                    <div class="card-body d-flex flex-column justify-content-between">
                        <div>
                            <h5 class="card-title font-weight-bold">My Payroll</h5>
                            <p class="card-text text-muted small">Access salary structures and download monthly slips.</p>
                        </div>
                        <a href="/web/hrms/my-payroll" class="btn btn-dark btn-block mt-3">Open Payroll</a>
                    </div>
                </div>
            </div>
        </div>

    <%-- ========================================== --%>
    <%-- DEFAULT LANDING                            --%>
    <%-- ========================================== --%>
    <% } else { %>
        
        <div class="jumbotron">
            <h1 class="display-4">Welcome to the Portal Hub</h1>
            <p class="lead">Please utilize your system menu pathways or route extensions to interact with HRMS business data layers.</p>
        </div>
        
    <% } %>

</div>