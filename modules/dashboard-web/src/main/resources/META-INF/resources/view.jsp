<%@ include file="/init.jsp" %>

<%-- Imports --%>
<%@ page pageEncoding="UTF-8" %>

<%-- Read request attributes --%>
<%
    String currentPageFriendlyURL = (String) request.getAttribute("currentPageFriendlyURL");

    Integer totalEmployees = (Integer) request.getAttribute("totalEmployees");
    Integer activeEmployees = (Integer) request.getAttribute("activeEmployees");
    Integer inactiveEmployees = (Integer) request.getAttribute("inactiveEmployees");

    Integer presentToday = (Integer) request.getAttribute("presentToday");
    Integer absentToday = (Integer) request.getAttribute("absentToday");
    Integer teamAttendanceCount = (Integer) request.getAttribute("teamAttendanceCount");

    Integer pendingLeaves = (Integer) request.getAttribute("pendingLeaves");
    Integer myLeaves = (Integer) request.getAttribute("myLeaves");
    Integer teamPendingLeaves = (Integer) request.getAttribute("teamPendingLeaves");

    Integer generatedPayslips = (Integer) request.getAttribute("generatedPayslips");
    Integer myPayslips = (Integer) request.getAttribute("myPayslips");

    Boolean isAdmin = (Boolean) request.getAttribute("isAdmin");
    Boolean isHR = (Boolean) request.getAttribute("isHR");
    Boolean isManager = (Boolean) request.getAttribute("isManager");
    Boolean isEmployee = (Boolean) request.getAttribute("isEmployee");

    // Null safety fallbacks
    String currentURL = (currentPageFriendlyURL != null) ? currentPageFriendlyURL : "";
    
    int cTotalEmp = (totalEmployees != null) ? totalEmployees : 0;
    int cActiveEmp = (activeEmployees != null) ? activeEmployees : 0;
    int cInactiveEmp = (inactiveEmployees != null) ? inactiveEmployees : 0;
    
    int cPresentToday = (presentToday != null) ? presentToday : 0;
    int cAbsentToday = (absentToday != null) ? absentToday : 0;
    int cTeamAttend = (teamAttendanceCount != null) ? teamAttendanceCount : 0;
    
    int cPendingLeaves = (pendingLeaves != null) ? pendingLeaves : 0;
    int cMyLeaves = (myLeaves != null) ? myLeaves : 0;
    int cTeamPendingLeaves = (teamPendingLeaves != null) ? teamPendingLeaves : 0;
    
    int cGenPayslips = (generatedPayslips != null) ? generatedPayslips : 0;
    int cMyPayslips = (myPayslips != null) ? myPayslips : 0;

    boolean sAdmin = (isAdmin != null) ? isAdmin : false;
    boolean sHR = (isHR != null) ? isHR : false;
    boolean sManager = (isManager != null) ? isManager : false;
    boolean sEmployee = (isEmployee != null) ? isEmployee : false;
%>

<div class="container-fluid my-4">

    <%-- ========================================== --%>
    <%-- ROUTE: DASHBOARD ROUTER                    --%>
    <%-- ========================================== --%>
    <% if (currentURL.contains("dashboard-router")) { %>
        
        <% if (sAdmin || sHR || sManager || sEmployee) { %>
            <div class="alert alert-info shadow-sm">
                <h4>Redirecting to your dashboard...</h4>
                <p class="mb-0">Please wait while we establish your functional application layer context.</p>
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
            <div class="alert alert-danger shadow-sm">
                <h4>Access denied.</h4>
                <p class="mb-0">Your profile contains no dashboard role mappings. Please contact system support.</p>
            </div>
        <% } %>

    <%-- ========================================== --%>
    <%-- ROUTE: ADMIN DASHBOARD                     --%>
    <%-- ========================================== --%>
    <% } else if (currentURL.contains("admin-dashboard")) { %>
        
        <h2 class="mb-4 text-dark font-weight-bold">Admin Dashboard</h2>
        
        <div class="row mb-4">
            <div class="col-md-4 mb-3">
                <div class="card bg-primary text-white shadow-sm h-100">
                    <div class="card-body">
                        <h6 class="text-uppercase font-weight-bold card-subtitle mb-2 small">Total Employees</h6>
                        <h2 class="card-title display-4 font-weight-bold mb-0"><%= cTotalEmp %></h2>
                    </div>
                </div>
            </div>
            <div class="col-md-4 mb-3">
                <div class="card bg-success text-white shadow-sm h-100">
                    <div class="card-body">
                        <h6 class="text-uppercase font-weight-bold card-subtitle mb-2 small">Active Employees</h6>
                        <h2 class="card-title display-4 font-weight-bold mb-0"><%= cActiveEmp %></h2>
                    </div>
                </div>
            </div>
            <div class="col-md-4 mb-3">
                <div class="card bg-danger text-white shadow-sm h-100">
                    <div class="card-body">
                        <h6 class="text-uppercase font-weight-bold card-subtitle mb-2 small">Inactive Employees</h6>
                        <h2 class="card-title display-4 font-weight-bold mb-0"><%= cInactiveEmp %></h2>
                    </div>
                </div>
            </div>
        </div>

        <div class="row mb-5">
            <div class="col-md-4 mb-3">
                <div class="card bg-light border-dark shadow-sm h-100">
                    <div class="card-body">
                        <h6 class="text-uppercase font-weight-bold card-subtitle mb-2 text-muted small">Present Today</h6>
                        <h2 class="card-title font-weight-bold"><%= cPresentToday %></h2>
                        <span class="text-info small"><i class="font-italic">Coming from Attendance Module later</i></span>
                    </div>
                </div>
            </div>
            <div class="col-md-4 mb-3">
                <div class="card bg-light border-dark shadow-sm h-100">
                    <div class="card-body">
                        <h6 class="text-uppercase font-weight-bold card-subtitle mb-2 text-muted small">Pending Leaves</h6>
                        <h2 class="card-title font-weight-bold"><%= cPendingLeaves %></h2>
                        <span class="text-info small"><i class="font-italic">Coming from Leave Module later</i></span>
                    </div>
                </div>
            </div>
            <div class="col-md-4 mb-3">
                <div class="card bg-light border-dark shadow-sm h-100">
                    <div class="card-body">
                        <h6 class="text-uppercase font-weight-bold card-subtitle mb-2 text-muted small">Generated Payslips</h6>
                        <h2 class="card-title font-weight-bold"><%= cGenPayslips %></h2>
                        <span class="text-info small"><i class="font-italic">Coming from Payroll Module later</i></span>
                    </div>
                </div>
            </div>
        </div>

        <h3 class="mb-3 text-secondary font-weight-bold">Quick Links</h3>
        <div class="row">
            <div class="col-md-3 mb-3"><a href="/web/hrms/employee-management" class="btn btn-outline-dark btn-block p-3 font-weight-bold">Employee Management</a></div>
            <div class="col-md-3 mb-3"><a href="/web/hrms/department-management" class="btn btn-outline-dark btn-block p-3 font-weight-bold">Department Management</a></div>
            <div class="col-md-3 mb-3"><a href="/web/hrms/designation-management" class="btn btn-outline-dark btn-block p-3 font-weight-bold">Designation Management</a></div>
            <div class="col-md-3 mb-3"><a href="/web/hrms/employee-reports" class="btn btn-outline-dark btn-block p-3 font-weight-bold">Employee Reports</a></div>
        </div>

    <%-- ========================================== --%>
    <%-- ROUTE: HR DASHBOARD                        --%>
    <%-- ========================================== --%>
    <% } else if (currentURL.contains("hr-dashboard")) { %>
        
        <h2 class="mb-4 text-dark font-weight-bold">HR Dashboard</h2>
        
        <div class="row mb-4">
            <div class="col-md-4 mb-3">
                <div class="card bg-primary text-white shadow-sm h-100">
                    <div class="card-body">
                        <h6 class="text-uppercase font-weight-bold card-subtitle mb-2 small">Total Employees</h6>
                        <h2 class="card-title display-4 font-weight-bold mb-0"><%= cTotalEmp %></h2>
                    </div>
                </div>
            </div>
            <div class="col-md-4 mb-3">
                <div class="card bg-success text-white shadow-sm h-100">
                    <div class="card-body">
                        <h6 class="text-uppercase font-weight-bold card-subtitle mb-2 small">Active Employees</h6>
                        <h2 class="card-title display-4 font-weight-bold mb-0"><%= cActiveEmp %></h2>
                    </div>
                </div>
            </div>
            <div class="col-md-4 mb-3">
                <div class="card bg-danger text-white shadow-sm h-100">
                    <div class="card-body">
                        <h6 class="text-uppercase font-weight-bold card-subtitle mb-2 small">Inactive Employees</h6>
                        <h2 class="card-title display-4 font-weight-bold mb-0"><%= cInactiveEmp %></h2>
                    </div>
                </div>
            </div>
        </div>

        <div class="row mb-5">
            <div class="col-md-4 mb-3">
                <div class="card bg-light border-dark shadow-sm h-100">
                    <div class="card-body">
                        <h6 class="text-uppercase font-weight-bold card-subtitle mb-2 text-muted small">Present Today</h6>
                        <h2 class="card-title font-weight-bold text-dark"><%= cPresentToday %></h2>
                        <span class="badge badge-secondary">Daily Core Operational Metric</span>
                    </div>
                </div>
            </div>
            <div class="col-md-4 mb-3">
                <div class="card bg-light border-dark shadow-sm h-100">
                    <div class="card-body">
                        <h6 class="text-uppercase font-weight-bold card-subtitle mb-2 text-muted small">Pending Leaves</h6>
                        <h2 class="card-title font-weight-bold text-dark"><%= cPendingLeaves %></h2>
                        <span class="badge badge-secondary">Global Backlog Approval Queue</span>
                    </div>
                </div>
            </div>
            <div class="col-md-4 mb-3">
                <div class="card bg-light border-dark shadow-sm h-100">
                    <div class="card-body">
                        <h6 class="text-uppercase font-weight-bold card-subtitle mb-2 text-muted small">Generated Payslips</h6>
                        <h2 class="card-title font-weight-bold text-dark"><%= cGenPayslips %></h2>
                        <span class="badge badge-secondary">Current Calculation Cycle Log</span>
                    </div>
                </div>
            </div>
        </div>

        <h3 class="mb-3 text-secondary font-weight-bold">Quick Links</h3>
        <div class="row">
            <div class="col-md-3 mb-3"><a href="/web/hrms/employee-management" class="btn btn-outline-info btn-block p-3 font-weight-bold">Employee Management</a></div>
            <div class="col-md-3 mb-3"><a href="/web/hrms/department-management" class="btn btn-outline-info btn-block p-3 font-weight-bold">Department Management</a></div>
            <div class="col-md-3 mb-3"><a href="/web/hrms/designation-management" class="btn btn-outline-info btn-block p-3 font-weight-bold">Designation Management</a></div>
            <div class="col-md-3 mb-3"><a href="/web/hrms/employee-reports" class="btn btn-outline-info btn-block p-3 font-weight-bold">Employee Reports</a></div>
        </div>

    <%-- ========================================== --%>
    <%-- ROUTE: MANAGER DASHBOARD                   --%>
    <%-- ========================================== --%>
    <% } else if (currentURL.contains("manager-dashboard")) { %>
        
        <h2 class="mb-4 text-dark font-weight-bold">Manager Dashboard</h2>
        
        <div class="row">
            <div class="col-md-4 mb-3">
                <div class="card bg-info text-white shadow-sm h-100">
                    <div class="card-body">
                        <h6 class="text-uppercase font-weight-bold card-subtitle mb-2 small">Team Attendance</h6>
                        <h2 class="card-title display-4 font-weight-bold mb-2"><%= cTeamAttend %></h2>
                        <p class="card-text small text-white-50">Active departmental staff recorded present on service floors today.</p>
                    </div>
                </div>
            </div>
            <div class="col-md-4 mb-3">
                <div class="card bg-warning text-dark shadow-sm h-100">
                    <div class="card-body">
                        <h6 class="text-uppercase font-weight-bold card-subtitle mb-2 small">Pending Leave Approvals</h6>
                        <h2 class="card-title display-4 font-weight-bold mb-2"><%= cTeamPendingLeaves %></h2>
                        <p class="card-text small text-muted">Outstanding localized requests requiring active authorization oversight.</p>
                    </div>
                </div>
            </div>
            <div class="col-md-4 mb-3">
                <div class="card bg-secondary text-white shadow-sm h-100">
                    <div class="card-body">
                        <h6 class="text-uppercase font-weight-bold card-subtitle mb-2 small">Team Reports</h6>
                        <h2 class="card-title font-weight-bold my-3"><i class="font-italic">Metrics Log View</i></h2>
                        <p class="card-text small text-white-50">Analytical distribution summary models mapping cross-team resource availability.</p>
                    </div>
                </div>
            </div>
        </div>

    <%-- ========================================== --%>
    <%-- ROUTE: EMPLOYEE DASHBOARD                  --%>
    <%-- ========================================== --%>
    <% } else if (currentURL.contains("employee-dashboard")) { %>
        
        <h2 class="mb-4 text-dark font-weight-bold">Employee Dashboard</h2>
        
        <div class="row">
            <div class="col-md-3 mb-3">
                <div class="card h-100 border-dark text-center shadow-sm">
                    <div class="card-body d-flex flex-column justify-content-between">
                        <div>
                            <h5 class="card-title font-weight-bold text-dark">My Profile</h5>
                            <p class="card-text text-muted small">Inspect personal corporate profile configurations and dynamic history indices.</p>
                        </div>
                        <a href="/web/hrms/my-profile" class="btn btn-dark btn-block mt-3">View Profile</a>
                    </div>
                </div>
            </div>
            <div class="col-md-3 mb-3">
                <div class="card h-100 border-dark text-center shadow-sm">
                    <div class="card-body d-flex flex-column justify-content-between">
                        <div>
                            <h5 class="card-title font-weight-bold text-dark">My Attendance</h5>
                            <p class="card-text text-muted small">Track monthly operational time tracking logging parameters and adjustments.</p>
                        </div>
                        <div class="mt-2"><span class="badge badge-info p-2 w-100">Tracking Active</span></div>
                        <a href="/web/hrms/my-attendance" class="btn btn-dark btn-block mt-3">Open Attendance</a>
                    </div>
                </div>
            </div>
            <div class="col-md-3 mb-3">
                <div class="card h-100 border-dark text-center shadow-sm">
                    <div class="card-body d-flex flex-column justify-content-between">
                        <div>
                            <h5 class="card-title font-weight-bold text-dark">My Leaves</h5>
                            <p class="card-text text-muted small">Review individual history tracks, request balancing matrices, or view balances.</p>
                        </div>
                        <div class="mt-2"><span class="badge badge-warning text-dark p-2 w-100"><%= cMyLeaves %> Request(s) Logged</span></div>
                        <a href="/web/hrms/my-leaves" class="btn btn-dark btn-block mt-3">Open Leaves</a>
                    </div>
                </div>
            </div>
            <div class="col-md-3 mb-3">
                <div class="card h-100 border-dark text-center shadow-sm">
                    <div class="card-body d-flex flex-column justify-content-between">
                        <div>
                            <h5 class="card-title font-weight-bold text-dark">My Payslips</h5>
                            <p class="card-text text-muted small">Securely access generated remuneration ledgers and export specific payment slips.</p>
                        </div>
                        <div class="mt-2"><span class="badge badge-success p-2 w-100"><%= cMyPayslips %> Statement(s) Available</span></div>
                        <a href="/web/hrms/my-payroll" class="btn btn-dark btn-block mt-3">Open Payroll</a>
                    </div>
                </div>
            </div>
        </div>

    <%-- ========================================== --%>
    <%-- DEFAULT LANDING HUB                        --%>
    <%-- ========================================== --%>
    <% } else { %>
        
        <div class="jumbotron shadow-sm border">
            <h1 class="display-4 font-weight-bold">Welcome to HRMS Hub</h1>
            <p class="lead text-secondary">Please utilize the default application menus or administrative pathways assigned to your employee account infrastructure.</p>
        </div>
        
    <% } %>

</div>