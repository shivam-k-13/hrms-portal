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

<%-- Premium HRMS Styles Blueprint --%>
<style type="text/css">
    .hrms-container { background-color: #f8fafc; font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Helvetica, Arial, sans-serif; }
    .hrms-card { border: none !important; border-radius: 12px !important; transition: transform 0.2s ease, box-shadow 0.2s ease; background: #ffffff; }
    .hrms-card:hover { transform: translateY(-2px); box-shadow: 0 10px 20px rgba(0,0,0,0.05) !important; }
    .metric-value { font-size: 2.25rem; font-weight: 700; color: #1e293b; line-height: 1.2; }
    .metric-label { font-size: 0.85rem; text-transform: uppercase; letter-spacing: 0.05em; font-weight: 600; color: #64748b; }
    .accent-blue { border-left: 5px solid #3b82f6 !important; }
    .accent-green { border-left: 5px solid #10b981 !important; }
    .accent-red { border-left: 5px solid #ef4444 !important; }
    .accent-orange { border-left: 5px solid #f59e0b !important; }
    .accent-purple { border-left: 5px solid #8b5cf6 !important; }
    .accent-teal { border-left: 5px solid #14b8a6 !important; }
    .placeholder-badge { font-size: 0.75rem; padding: 4px 8px; border-radius: 50px; font-weight: 500; background-color: #f1f5f9; color: #64748b; }
    .menu-link { display: flex; align-items: center; justify-content: space-between; padding: 1.25rem; border-radius: 10px; background: #ffffff; border: 1px solid #e2e8f0; color: #334155; font-weight: 600; text-decoration: none !important; transition: all 0.2s ease; }
    .menu-link:hover { background: #3b82f6; color: #ffffff; border-color: #3b82f6; }
    .menu-link-sub { border-left: 4px solid #64748b; }
</style>

<div class="container-fluid my-4 hrms-container p-4 rounded-lg">

    <%-- ========================================== --%>
    <%-- ROUTE: DASHBOARD ROUTER                    --%>
    <%-- ========================================== --%>
    <% if (currentURL.contains("dashboard-router")) { %>
        
        <% if (sAdmin || sHR || sManager || sEmployee) { %>
            <div class="card hrms-card shadow-sm p-4 text-center">
                <div class="spinner-border text-primary my-3" role="status"></div>
                <h4 class="text-dark font-weight-bold">Redirecting to your dashboard...</h4>
                <p class="text-muted mb-0">Establishing secure functional workspace layer parameters.</p>
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
            <div class="alert alert-danger shadow-sm border-0 rounded-lg p-4">
                <h4 class="font-weight-bold">Access Denied</h4>
                <p class="mb-0">Your profile configuration does not contain verified administrative or directional dashboard mappings. Please consult platform operations.</p>
            </div>
        <% } %>

    <%-- ========================================== --%>
    <%-- ROUTE: ADMIN DASHBOARD                     --%>
    <%-- ========================================== --%>
    <% } else if (currentURL.contains("admin-dashboard")) { %>
        
        <div class="mb-4">
            <h2 class="text-dark font-weight-bold m-0">Admin Dashboard</h2>
            <p class="text-muted small">Global system infrastructure, entity control, and macro analytics node.</p>
        </div>
        
        <div class="row mb-4">
            <div class="col-md-4 mb-3">
                <div class="card hrms-card accent-blue shadow-sm">
                    <div class="card-body p-4">
                        <div class="metric-label mb-2">Total Employees</div>
                        <div class="metric-value"><%= cTotalEmp %></div>
                    </div>
                </div>
            </div>
            <div class="col-md-4 mb-3">
                <div class="card hrms-card accent-green shadow-sm">
                    <div class="card-body p-4">
                        <div class="metric-label mb-2">Active Employees</div>
                        <div class="metric-value"><%= cActiveEmp %></div>
                    </div>
                </div>
            </div>
            <div class="col-md-4 mb-3">
                <div class="card hrms-card accent-red shadow-sm">
                    <div class="card-body p-4">
                        <div class="metric-label mb-2">Inactive Employees</div>
                        <div class="metric-value"><%= cInactiveEmp %></div>
                    </div>
                </div>
            </div>
        </div>

        <div class="row mb-5">
            <div class="col-md-4 mb-3">
                <div class="card hrms-card accent-purple shadow-sm">
                    <div class="card-body p-4">
                        <div class="d-flex justify-content-between align-items-start mb-2">
                            <div class="metric-label">Present Today</div>
                            <span class="placeholder-badge">Attendance</span>
                        </div>
                        <div class="metric-value mb-2"><%= cPresentToday %></div>
                        <span class="text-muted small font-italic">Coming from Attendance Module later</span>
                    </div>
                </div>
            </div>
            <div class="col-md-4 mb-3">
                <div class="card hrms-card accent-orange shadow-sm">
                    <div class="card-body p-4">
                        <div class="d-flex justify-content-between align-items-start mb-2">
                            <div class="metric-label">Pending Leaves</div>
                            <span class="placeholder-badge">Leave</span>
                        </div>
                        <div class="metric-value mb-2"><%= cPendingLeaves %></div>
                        <span class="text-muted small font-italic">Coming from Leave Module later</span>
                    </div>
                </div>
            </div>
            <div class="col-md-4 mb-3">
                <div class="card hrms-card accent-teal shadow-sm">
                    <div class="card-body p-4">
                        <div class="d-flex justify-content-between align-items-start mb-2">
                            <div class="metric-label">Generated Payslips</div>
                            <span class="placeholder-badge">Payroll</span>
                        </div>
                        <div class="metric-value mb-2"><%= cGenPayslips %></div>
                        <span class="text-muted small font-italic">Coming from Payroll Module later</span>
                    </div>
                </div>
            </div>
        </div>

        <h4 class="mb-3 text-dark font-weight-bold">System Management Navigation</h4>
        <div class="row">
            <div class="col-md-4 mb-3"><a href="/web/hrms/employee-management" class="menu-link shadow-sm"><span>Employee Management</span> <span>&rarr;</span></a></div>
            <div class="col-md-4 mb-3"><a href="/web/hrms/department-management" class="menu-link shadow-sm"><span>Department Management</span> <span>&rarr;</span></a></div>
            <div class="col-md-4 mb-3"><a href="/web/hrms/designation-management" class="menu-link shadow-sm"><span>Designation Management</span> <span>&rarr;</span></a></div>
            <div class="col-md-4 mb-3"><a href="/web/hrms/employee-reports" class="menu-link shadow-sm text-primary"><span>Employee Reports</span> <span>&rarr;</span></a></div>
            <div class="col-md-4 mb-3"><a href="/web/hrms/hr-dashboard" class="menu-link menu-link-sub shadow-sm text-muted"><span>HR Dashboard View</span> <span>&rarr;</span></a></div>
            <div class="col-md-4 mb-3"><a href="/web/hrms/manager-dashboard" class="menu-link menu-link-sub shadow-sm text-muted"><span>Manager Dashboard View</span> <span>&rarr;</span></a></div>
            <div class="col-md-4 mb-3"><a href="/web/hrms/employee-dashboard" class="menu-link menu-link-sub shadow-sm text-muted"><span>Employee Dashboard View</span> <span>&rarr;</span></a></div>
        </div>

    <%-- ========================================== --%>
    <%-- ROUTE: HR DASHBOARD                        --%>
    <%-- ========================================== --%>
    <% } else if (currentURL.contains("hr-dashboard")) { %>
        
        <div class="mb-4">
            <h2 class="text-dark font-weight-bold m-0">HR Dashboard</h2>
            <p class="text-muted small">Operational resource deployment management hub and talent analytics tracking floor.</p>
        </div>
        
        <div class="row mb-4">
            <div class="col-md-4 mb-3">
                <div class="card hrms-card accent-blue shadow-sm">
                    <div class="card-body p-4">
                        <div class="metric-label mb-2">Total Employees</div>
                        <div class="metric-value"><%= cTotalEmp %></div>
                    </div>
                </div>
            </div>
            <div class="col-md-4 mb-3">
                <div class="card hrms-card accent-green shadow-sm">
                    <div class="card-body p-4">
                        <div class="metric-label mb-2">Active Employees</div>
                        <div class="metric-value"><%= cActiveEmp %></div>
                    </div>
                </div>
            </div>
            <div class="col-md-4 mb-3">
                <div class="card hrms-card accent-red shadow-sm">
                    <div class="card-body p-4">
                        <div class="metric-label mb-2">Inactive Employees</div>
                        <div class="metric-value"><%= cInactiveEmp %></div>
                    </div>
                </div>
            </div>
        </div>

        <div class="row mb-5">
            <div class="col-md-4 mb-3">
                <div class="card hrms-card accent-purple shadow-sm">
                    <div class="card-body p-4">
                        <div class="d-flex justify-content-between align-items-start mb-2">
                            <div class="metric-label">Present Today</div>
                            <span class="placeholder-badge">Attendance Queue</span>
                        </div>
                        <div class="metric-value mb-2"><%= cPresentToday %></div>
                        <span class="text-muted small font-italic">Attendance structural framework syncing pending</span>
                    </div>
                </div>
            </div>
            <div class="col-md-4 mb-3">
                <div class="card hrms-card accent-orange shadow-sm">
                    <div class="card-body p-4">
                        <div class="d-flex justify-content-between align-items-start mb-2">
                            <div class="metric-label">Pending Leaves</div>
                            <span class="placeholder-badge">Leave Balances</span>
                        </div>
                        <div class="metric-value mb-2"><%= cPendingLeaves %></div>
                        <span class="text-muted small font-italic">Leave management ledger tracking placeholder</span>
                    </div>
                </div>
            </div>
            <div class="col-md-4 mb-3">
                <div class="card hrms-card accent-teal shadow-sm">
                    <div class="card-body p-4">
                        <div class="d-flex justify-content-between align-items-start mb-2">
                            <div class="metric-label">Generated Payslips</div>
                            <span class="placeholder-badge">Payroll Engine</span>
                        </div>
                        <div class="metric-value mb-2"><%= cGenPayslips %></div>
                        <span class="text-muted small font-italic">Remuneration register interface placeholder</span>
                    </div>
                </div>
            </div>
        </div>

        <h4 class="mb-3 text-dark font-weight-bold">Human Resources Navigation</h4>
        <div class="row">
            <div class="col-md-3 mb-3"><a href="/web/hrms/employee-management" class="menu-link shadow-sm"><span>Employee Management</span> <span>&rarr;</span></a></div>
            <div class="col-md-3 mb-3"><a href="/web/hrms/department-management" class="menu-link shadow-sm"><span>Department Management</span> <span>&rarr;</span></a></div>
            <div class="col-md-3 mb-3"><a href="/web/hrms/designation-management" class="menu-link shadow-sm"><span>Designation Management</span> <span>&rarr;</span></a></div>
            <div class="col-md-3 mb-3"><a href="/web/hrms/employee-reports" class="menu-link shadow-sm text-info"><span>Employee Reports</span> <span>&rarr;</span></a></div>
        </div>

    <%-- ========================================== --%>
    <%-- ROUTE: MANAGER DASHBOARD                   --%>
    <%-- ========================================== --%>
    <% } else if (currentURL.contains("manager-dashboard")) { %>
        
        <div class="mb-4">
            <h2 class="text-dark font-weight-bold m-0">Manager Dashboard</h2>
            <p class="text-muted small">Departmental overview dashboard monitoring localized operational availability targets.</p>
        </div>
        
        <div class="row mb-4">
            <div class="col-md-4 mb-3">
                <div class="card hrms-card accent-blue shadow-sm">
                    <div class="card-body p-4">
                        <div class="d-flex justify-content-between align-items-start mb-2">
                            <div class="metric-label">Team Attendance</div>
                            <span class="placeholder-badge">Syncing</span>
                        </div>
                        <div class="metric-value"><%= cTeamAttend %></div>
                    </div>
                </div>
            </div>
            <div class="col-md-4 mb-3">
                <div class="card hrms-card accent-orange shadow-sm">
                    <div class="card-body p-4">
                        <div class="d-flex justify-content-between align-items-start mb-2">
                            <div class="metric-label">Pending Leave Approvals</div>
                            <span class="placeholder-badge">Pending</span>
                        </div>
                        <div class="metric-value"><%= cTeamPendingLeaves %></div>
                    </div>
                </div>
            </div>
            <div class="col-md-4 mb-3">
                <div class="card hrms-card accent-purple shadow-sm">
                    <div class="card-body p-4">
                        <div class="d-flex justify-content-between align-items-start mb-2">
                            <div class="metric-label">Team Reports</div>
                            <span class="placeholder-badge">Analytical Matrix</span>
                        </div>
                        <div class="metric-value font-weight-bold text-muted my-1" style="font-size: 1.5rem;">Resource View</div>
                    </div>
                </div>
            </div>
        </div>

        <div class="card border-0 shadow-sm rounded-lg bg-light p-4">
            <div class="d-flex align-items-center">
                <div class="text-warning mr-3" style="font-size: 1.5rem;">&#9888;</div>
                <div>
                    <h6 class="font-weight-bold text-dark mb-1">Module Pipeline Status Warning</h6>
                    <p class="text-muted small mb-0">Operational real-time data sync parameters mapping to the core <strong>Attendance</strong> and <strong>Leave Management</strong> modules are currently under system staging pipeline setup.</p>
                </div>
            </div>
        </div>

    <%-- ========================================== --%>
    <%-- ROUTE: EMPLOYEE DASHBOARD                  --%>
    <%-- ========================================== --%>
    <% } else if (currentURL.contains("employee-dashboard")) { %>
        
        <div class="mb-4">
            <h2 class="text-dark font-weight-bold m-0">Employee Dashboard</h2>
            <p class="text-muted small">Personal employee service desk panel monitoring individual logs and compliance data files.</p>
        </div>
        
        <div class="row">
            <div class="col-md-3 mb-4">
                <div class="card hrms-card shadow-sm border-0 h-100">
                    <div class="card-body p-4 d-flex flex-column justify-content-between">
                        <div>
                            <div class="metric-label mb-2">My Profile</div>
                            <p class="text-muted small">Access corporate identity, registry items, and security logs.</p>
                        </div>
                        <a href="/web/hrms/my-profile" class="btn btn-primary btn-block rounded-lg font-weight-bold shadow-sm mt-3">View Profile &rarr;</a>
                    </div>
                </div>
            </div>
            <div class="col-md-3 mb-4">
                <div class="card hrms-card shadow-sm border-0 h-100">
                    <div class="card-body p-4 d-flex flex-column justify-content-between">
                        <div>
                            <div class="d-flex justify-content-between align-items-start mb-2">
                                <div class="metric-label">My Attendance</div>
                                <span class="placeholder-badge">Staging</span>
                            </div>
                            <p class="text-muted small">Inspect active attendance metrics, check-in sequences, and time cards.</p>
                        </div>
                        <a href="/web/hrms/my-attendance" class="btn btn-outline-primary btn-block rounded-lg font-weight-bold mt-3">Open Logs</a>
                    </div>
                </div>
            </div>
            <div class="col-md-3 mb-4">
                <div class="card hrms-card shadow-sm border-0 h-100">
                    <div class="card-body p-4 d-flex flex-column justify-content-between">
                        <div>
                            <div class="d-flex justify-content-between align-items-start mb-2">
                                <div class="metric-label">My Leaves</div>
                                <span class="badge badge-warning text-dark px-2 rounded font-weight-bold" style="font-size: 0.75rem;"><%= cMyLeaves %> Request(s)</span>
                            </div>
                            <p class="text-muted small">Track pending application requests, holiday schedules, and historical balances.</p>
                        </div>
                        <a href="/web/hrms/my-leaves" class="btn btn-outline-primary btn-block rounded-lg font-weight-bold mt-3">Open Leaves</a>
                    </div>
                </div>
            </div>
            <div class="col-md-3 mb-4">
                <div class="card hrms-card shadow-sm border-0 h-100">
                    <div class="card-body p-4 d-flex flex-column justify-content-between">
                        <div>
                            <div class="d-flex justify-content-between align-items-start mb-2">
                                <div class="metric-label">My Payslips</div>
                                <span class="badge badge-success px-2 rounded font-weight-bold" style="font-size: 0.75rem;"><%= cMyPayslips %> Issued</span>
                            </div>
                            <p class="text-muted small">Review monthly compensation statements, ledger statements, and download profiles.</p>
                        </div>
                        <a href="/web/hrms/my-payroll" class="btn btn-outline-primary btn-block rounded-lg font-weight-bold mt-3">Open Payroll</a>
                    </div>
                </div>
            </div>
        </div>

    <%-- ========================================== --%>
    <%-- DEFAULT LANDING HUB                        --%>
    <%-- ========================================== --%>
    <% } else { %>
        
        <div class="card hrms-card shadow-sm p-5 text-center bg-white border-0 my-4">
            <h1 class="display-4 font-weight-bold text-dark mb-3">Welcome to HRMS Hub</h1>
            <p class="lead text-muted max-width-600 mx-auto">Please leverage the portal global application navigation menus or explicit functional dashboard routes assigned to your enterprise structural level.</p>
        </div>
        
    <% } %>

</div>