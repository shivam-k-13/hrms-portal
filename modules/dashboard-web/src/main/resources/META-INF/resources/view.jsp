<%@ include file="/init.jsp" %>

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
    int employeeCount = (totalEmployees != null) ? totalEmployees : 0;
    boolean sAdmin = (isAdmin != null) ? isAdmin : false;
    boolean sHR = (isHR != null) ? isHR : false;
    boolean sManager = (isManager != null) ? isManager : false;
    boolean sEmployee = (isEmployee != null) ? isEmployee : false;

    // Track if a valid view was rendered to handle unknown URLs or access denial cleanly
    boolean authorizationChecked = false;
    boolean hasAccess = false;
%>

<div class="container-fluid my-4">

    <%-- ========================================== --%>
    <%-- 1. ADMIN DASHBOARD ROUTING --%>
    <%-- ========================================== --%>
    <% if (currentURL.contains("admin-dashboard")) { 
        authorizationChecked = true;
        if (sAdmin) { 
            hasAccess = true; %>
            <div class="dashboard-section">
                <h2 class="mb-4 text-primary">Admin Dashboard</h2>
                
                <div class="row mb-4">
                    <div class="col-md-4">
                        <div class="card bg-primary text-white shadow-sm text-center">
                            <div class="card-body">
                                <h5 class="card-title text-uppercase font-weight-bold">Total Employees</h5>
                                <p class="display-4 font-weight-bold mb-0"><%= employeeCount %></p>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-12">
                        <h4 class="mb-3">Quick Links</h4>
                        <div class="d-flex flex-wrap gap-2">
                            <a href="/web/hrms/employee-management" class="btn btn-primary m-1">Employee Management</a>
                            <a href="/web/hrms/hr-dashboard" class="btn btn-info text-white m-1">HR Dashboard</a>
                            <a href="/web/hrms/manager-dashboard" class="btn btn-warning text-white m-1">Manager Dashboard</a>
                            <a href="/web/hrms/employee-dashboard" class="btn btn-success m-1">Employee Dashboard</a>
                        </div>
                    </div>
                </div>
            </div>
        <% } 
    } %>

    <%-- ========================================== --%>
    <%-- 2. HR DASHBOARD ROUTING --%>
    <%-- ========================================== --%>
    <% if (currentURL.contains("hr-dashboard")) { 
        authorizationChecked = true;
        if (sAdmin || sHR) { 
            hasAccess = true; %>
            <div class="dashboard-section">
                <h2 class="mb-4 text-info">HR Dashboard</h2>
                
                <div class="row mb-4">
                    <div class="col-md-4">
                        <div class="card bg-info text-white shadow-sm text-center">
                            <div class="card-body">
                                <h5 class="card-title text-uppercase font-weight-bold">Total Employees</h5>
                                <p class="display-4 font-weight-bold mb-0"><%= employeeCount %></p>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-12">
                        <h4 class="mb-3">Quick Links</h4>
                        <div class="d-flex flex-wrap gap-2">
                            <a href="/web/hrms/employee-management" class="btn btn-outline-info m-1">Employee Management</a>
                            <span class="btn btn-outline-secondary disabled m-1">Attendance Management Placeholder</span>
                            <span class="btn btn-outline-secondary disabled m-1">Leave Management Placeholder</span>
                            <span class="btn btn-outline-secondary disabled m-1">Payroll Management Placeholder</span>
                        </div>
                    </div>
                </div>
            </div>
        <% } 
    } %>

    <%-- ========================================== --%>
    <%-- 3. MANAGER DASHBOARD ROUTING --%>
    <%-- ========================================== --%>
    <% if (currentURL.contains("manager-dashboard")) { 
        authorizationChecked = true;
        if (sAdmin || sManager) { 
            hasAccess = true; %>
            <div class="dashboard-section">
                <h2 class="mb-4 text-warning">Manager Dashboard</h2>
                
                <div class="row">
                    <div class="col-md-4 mb-3">
                        <div class="card shadow-sm h-100 border-left-warning">
                            <div class="card-body d-flex flex-column justify-content-center align-items-center py-4">
                                <h5 class="card-title text-muted mb-2">Team Attendance</h5>
                                <span class="badge badge-warning text-white p-2">Team Attendance Card</span>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4 mb-3">
                        <div class="card shadow-sm h-100 border-left-warning">
                            <div class="card-body d-flex flex-column justify-content-center align-items-center py-4">
                                <h5 class="card-title text-muted mb-2">Pending Leave Approvals</h5>
                                <span class="badge badge-warning text-white p-2">Pending Leave Approvals Card</span>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4 mb-3">
                        <div class="card shadow-sm h-100 border-left-warning">
                            <div class="card-body d-flex flex-column justify-content-center align-items-center py-4">
                                <h5 class="card-title text-muted mb-2">Team Reports</h5>
                                <span class="badge badge-warning text-white p-2">Team Reports Card</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        <% } 
    } %>

    <%-- ========================================== --%>
    <%-- 4. EMPLOYEE DASHBOARD ROUTING --%>
    <%-- ========================================== --%>
    <% if (currentURL.contains("employee-dashboard")) { 
        authorizationChecked = true;
        if (sAdmin || sEmployee) { 
            hasAccess = true; %>
            <div class="dashboard-section">
                <h2 class="mb-4 text-success">Employee Dashboard</h2>
                
                <div class="row">
                    <div class="col-md-3 col-sm-6 mb-3">
                        <a href="/web/hrms/my-profile" class="text-decoration-none text-dark card-link">
                            <div class="card shadow-sm text-center h-100 dynamic-hover-card">
                                <div class="card-body py-4">
                                    <h5 class="card-title font-weight-bold text-success">My Profile</h5>
                                    <p class="text-muted small mb-0">My Profile Card</p>
                                </div>
                            </div>
                        </a>
                    </div>
                    <div class="col-md-3 col-sm-6 mb-3">
                        <a href="/web/hrms/my-attendance" class="text-decoration-none text-dark card-link">
                            <div class="card shadow-sm text-center h-100 dynamic-hover-card">
                                <div class="card-body py-4">
                                    <h5 class="card-title font-weight-bold text-success">My Attendance</h5>
                                    <p class="text-muted small mb-0">My Attendance Card</p>
                                </div>
                            </div>
                        </a>
                    </div>
                    <div class="col-md-3 col-sm-6 mb-3">
                        <a href="/web/hrms/my-leaves" class="text-decoration-none text-dark card-link">
                            <div class="card shadow-sm text-center h-100 dynamic-hover-card">
                                <div class="card-body py-4">
                                    <h5 class="card-title font-weight-bold text-success">My Leaves</h5>
                                    <p class="text-muted small mb-0">My Leaves Card</p>
                                </div>
                            </div>
                        </a>
                    </div>
                    <div class="col-md-3 col-sm-6 mb-3">
                        <a href="/web/hrms/my-payslips" class="text-decoration-none text-dark card-link">
                            <div class="card shadow-sm text-center h-100 dynamic-hover-card">
                                <div class="card-body py-4">
                                    <h5 class="card-title font-weight-bold text-success">My Payslips</h5>
                                    <p class="text-muted small mb-0">My Payslips Card</p>
                                </div>
                            </div>
                        </a>
                    </div>
                </div>
            </div>
        <% } 
    } %>

    <%-- ========================================== --%>
    <%-- FALLBACK: ACCESS DENIED / UNKNOWN STATE --%>
    <%-- ========================================== --%>
    <% if (authorizationChecked && !hasAccess) { %>
        <div class="alert alert-danger role-security-alert my-4" role="alert">
            <strong class="lead font-weight-bold">Access denied for this dashboard.</strong>
        </div>
    <% } %>

</div>

<style>
    /* Keeps styling look clean when wrapped inside an anchor tag */
    .card-link {
        display: block;
        transition: transform 0.2s ease, box-shadow 0.2s ease;
    }
    .card-link:hover {
        text-decoration: none !important;
    }
    .dynamic-hover-card:hover {
        transform: translateY(-3px);
        box-shadow: 0 4px 10px rgba(0,0,0,0.15) !important;
    }
</style>