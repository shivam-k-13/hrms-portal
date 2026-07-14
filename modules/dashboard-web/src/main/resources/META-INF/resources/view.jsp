<%@ include file="/init.jsp" %>
<%@ page pageEncoding="UTF-8" %>

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

    String currentURL = (currentPageFriendlyURL != null) ? currentPageFriendlyURL : "";
    int cTotalEmp = (totalEmployees != null) ? totalEmployees : 0;
    int cActiveEmp = (activeEmployees != null) ? activeEmployees : 0;
    int cInactiveEmp = (inactiveEmployees != null) ? inactiveEmployees : 0;
    int cPresentToday = (presentToday != null) ? presentToday : 0;
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

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" />
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700;800;900&display=swap" />

<style type="text/css">
    /* Landing Page Theme Variables */
    .hrms-app-container {
        --primary-blue: #0056b3;
        --secondary-blue: #0088cc;
        --primary-gradient: linear-gradient(135deg, #0f172a 0%, #1e3a8a 100%);
        --accent-gradient: linear-gradient(135deg, #0056b3 0%, #0088cc 100%);
        --background: #f8fafc;
        --surface: #ffffff;
        --text-primary: #1e293b;
        --text-secondary: #64748b;
        --border: #e2e8f0;
        --shadow: 0 10px 30px rgba(0, 86, 179, 0.06);
        --shadow-strong: 0 15px 35px rgba(0, 86, 179, 0.12);

        font-family: "Inter", sans-serif;
        background: var(--background);
        color: var(--text-primary);
        min-height: 100vh;
        display: flex;
        flex-direction: column;
        font-size: 15px; /* Medium, comfortable base font */
    }

    .hrms-app-container * { box-sizing: border-box; margin: 0; padding: 0; }

    /* Top Navbar */
    .top-navbar {
        background: var(--surface);
        padding: 1rem 2rem;
        display: flex;
        justify-content: space-between;
        align-items: center;
        box-shadow: 0 2px 10px rgba(0,0,0,0.05);
        z-index: 100;
        position: sticky;
        top: 0;
    }
    .top-navbar .logo {
        display: flex; align-items: center; gap: 0.75rem; text-decoration: none;
        font-size: 1.25rem; font-weight: 900; color: #0f172a;
    }
    .top-navbar .logo-icon {
        background: var(--accent-gradient); color: white;
        width: 38px; height: 38px; border-radius: 50%;
        display: flex; align-items: center; justify-content: center;
    }
    .top-nav-links { display: flex; gap: 2rem; align-items: center; }
    .top-nav-links a { color: var(--text-secondary); text-decoration: none; font-weight: 600; font-size: 0.9rem; }
    .top-nav-links a.active { color: var(--primary-blue); border-bottom: 2px solid var(--primary-blue); padding-bottom: 0.25rem; }
    .user-avatar { width: 35px; height: 35px; border-radius: 50%; background: #f1f5f9; border: 1px solid var(--border); display: flex; align-items: center; justify-content: center; color: var(--primary-blue); }

    /* Layout */
    .main-layout { display: flex; flex: 1; overflow: hidden; }

    /* Sidebar */
    .sidebar {
        width: 260px; background: var(--surface);
        border-right: 1px solid var(--border); padding: 2rem 1.5rem;
        display: flex; flex-direction: column; gap: 0.5rem;
        overflow-y: auto; z-index: 50;
    }
    .sidebar a {
        display: flex; align-items: center; gap: 0.75rem;
        padding: 0.85rem 1rem; color: var(--text-secondary);
        text-decoration: none; font-weight: 600; border-radius: 12px; transition: 0.2s;
    }
    .sidebar a:hover, .sidebar a.active {
        background: var(--accent-gradient); color: white;
        box-shadow: 0 4px 12px rgba(0, 136, 204, 0.2);
    }
    .btn-ai { margin-top: auto; background: #0f172a !important; color: #38bdf8 !important; justify-content: center; }

    /* Content Area */
    .content-area { flex: 1; overflow-y: auto; display: flex; flex-direction: column; }

    /* Hero Header Block */
    .dash-hero {
        background: var(--primary-gradient); padding: 3rem 3rem 6rem;
        color: white; border-bottom-left-radius: 30px; border-bottom-right-radius: 30px;
        position: relative; flex-shrink: 0; margin-bottom: -4rem;
    }
    .dash-hero h1 { font-size: 2.2rem; font-weight: 900; margin-bottom: 0.5rem; }
    .dash-hero p { color: #cbd5e1; font-size: 1.05rem; margin: 0; }

    /* Dashboard Cards Grid */
    .dashboard-grid {
        padding: 0 3rem 3rem; display: grid; gap: 1.5rem;
        grid-template-columns: repeat(auto-fit, minmax(280px, 1fr));
        position: relative; z-index: 10;
        align-items: start; /* PREVENTS VERTICAL STRETCHING */
    }

    /* Cards */
    .hrms-card {
        background: var(--surface); border: 1px solid var(--border);
        border-radius: 20px; padding: 1.75rem; box-shadow: var(--shadow);
        display: flex; flex-direction: column; transition: 0.3s;
    }
    .hrms-card:hover { transform: translateY(-4px); box-shadow: var(--shadow-strong); }
    
    .card-title { font-size: 0.85rem; font-weight: 800; color: var(--text-secondary); text-transform: uppercase; letter-spacing: 0.05em; margin-bottom: 1rem; display: flex; justify-content: space-between; align-items: center;}
    .card-value { font-size: 2.5rem; font-weight: 900; color: #0f172a; line-height: 1; margin-bottom: 0.5rem; }
    .card-desc { font-size: 0.9rem; color: var(--text-secondary); }

    /* Card Top Borders */
    .border-blue { border-top: 5px solid #3b82f6; }
    .border-green { border-top: 5px solid #10b981; }
    .border-orange { border-top: 5px solid #f59e0b; }
    .border-teal { border-top: 5px solid #14b8a6; }
    .border-purple { border-top: 5px solid #8b5cf6; }

    /* Badges */
    .badge { font-size: 0.75rem; padding: 0.35rem 0.75rem; border-radius: 999px; font-weight: 700; }
    .badge-blue { background: #eff6ff; color: #1d4ed8; }
    .badge-yellow { background: #fef3c7; color: #b45309; }
    .badge-green { background: #ecfdf5; color: #047857; }

    /* Buttons */
    .btn-action { margin-top: 1.5rem; padding: 0.75rem 1.25rem; border-radius: 10px; font-weight: 700; text-align: center; text-decoration: none; display: block; transition: 0.2s; }
    .btn-solid { background: var(--accent-gradient); color: white; border: none; }
    .btn-outline { background: transparent; color: var(--primary-blue); border: 2px solid var(--primary-blue); }
    .btn-solid:hover, .btn-outline:hover { opacity: 0.9; color: white; background: var(--accent-gradient); border-color: transparent;}
</style>

<div class="hrms-app-container">
    
    <!-- Top Navbar -->
    <header class="top-navbar">
        <a href="/web/hrms/" class="logo">
            <div class="logo-icon"><i class="fa-solid fa-layer-group"></i></div>
            NEXT GENHRMS
        </a>
        <div class="top-nav-links">
            <a href="#">Landing Page</a>
            <a href="#" class="active">Employee Dashboard</a>
            <a href="#">My Profile</a>
            <a href="#">AI Assistance</a>
            <div class="user-avatar"><i class="fa-solid fa-user"></i></div>
        </div>
    </header>

    <div class="main-layout">
        <!-- Sidebar -->
        <aside class="sidebar">
            <div style="font-size: 0.75rem; font-weight: 800; color: #94a3b8; margin-bottom: 0.5rem; padding-left: 1rem; text-transform: uppercase;">Portal Menu</div>
            
            <% if (sAdmin) { %> 
                <a href="/web/hrms/admin-dashboard" class="<%= currentURL.contains("admin") ? "active" : "" %>"><i class="fa-solid fa-shield-halved"></i> Admin Space</a> 
            <% } %>
            <% if (sHR || sAdmin) { %> 
                <a href="/web/hrms/hr-dashboard" class="<%= currentURL.contains("hr-dashboard") ? "active" : "" %>"><i class="fa-solid fa-users"></i> HR Space</a> 
            <% } %>
            <% if (sManager || sAdmin) { %> 
                <a href="/web/hrms/manager-dashboard" class="<%= currentURL.contains("manager") ? "active" : "" %>"><i class="fa-solid fa-chart-pie"></i> Manager Space</a> 
            <% } %>
            <% if (sEmployee || sManager || sHR || sAdmin) { %>
                <a href="/web/hrms/employee-dashboard" class="<%= currentURL.contains("employee-dashboard") ? "active" : "" %>"><i class="fa-solid fa-house"></i> My Space</a>
                <a href="/web/hrms/my-profile"><i class="fa-solid fa-id-card"></i> My Profile</a>
                <a href="/web/hrms/my-leaves"><i class="fa-solid fa-calendar-minus"></i> My Leaves</a>
                
                <a href="/web/hrms/ai_assistance" class="btn-ai"><i class="fa-solid fa-wand-magic-sparkles"></i> AI Summariser</a>
            <% } %>
        </aside>

        <!-- Main Content -->
        <main class="content-area">
            
            <% if (currentURL.contains("dashboard-router")) { %>
                <div style="padding: 4rem; text-align: center;">
                    <h2>Redirecting...</h2>
                    <p>Establishing secure workspace parameters.</p>
                </div>
                <script>
                    setTimeout(() => {
                        if (<%= sAdmin %>) window.location.href = '/web/hrms/admin-dashboard';
                        else if (<%= sHR %>) window.location.href = '/web/hrms/hr-dashboard';
                        else if (<%= sManager %>) window.location.href = '/web/hrms/manager-dashboard';
                        else if (<%= sEmployee %>) window.location.href = '/web/hrms/employee-dashboard';
                    }, 500);
                </script>
            <% } else { %>
                
                <div class="dash-hero">
                    <h1>Welcome, Mayank ✨</h1>
                    <p>
                        <%= currentURL.contains("admin") ? "Global system infrastructure node." : 
                            currentURL.contains("hr") ? "Resource deployment and management hub." : 
                            currentURL.contains("manager") ? "Departmental overview and approvals." : 
                            "Personal employee service desk." %>
                    </p>
                </div>

                <div class="dashboard-grid">
                    
                    <%-- EMPLOYEE DASHBOARD CONTENT --%>
                    <% if (currentURL.contains("employee-dashboard")) { %>
                        
                        <div class="hrms-card border-blue">
                            <div class="card-title">My Profile</div>
                            <p class="card-desc">Access corporate identity, registry items, and security logs.</p>
                            <a href="/web/hrms/my-profile" class="btn-action btn-solid">View Profile &rarr;</a>
                        </div>

                        <div class="hrms-card border-green">
                            <div class="card-title">Attendance <span class="badge badge-blue">Staging</span></div>
                            <p class="card-desc">Inspect active attendance metrics, check-in sequences, and time cards.</p>
                            <a href="/web/hrms/my-attendance" class="btn-action btn-outline">Open Logs</a>
                        </div>

                        <div class="hrms-card border-orange">
                            <div class="card-title">My Leaves <span class="badge badge-yellow"><%= cMyLeaves %> Req</span></div>
                            <p class="card-desc">Track pending application requests, holiday schedules, and balances.</p>
                            <a href="/web/hrms/my-leaves" class="btn-action btn-outline">Open Leaves</a>
                        </div>

                        <div class="hrms-card border-teal">
                            <div class="card-title">My Payslips <span class="badge badge-green"><%= cMyPayslips %> Issued</span></div>
                            <p class="card-desc">Review monthly compensation statements and download profiles.</p>
                            <a href="/web/hrms/my-payroll" class="btn-action btn-outline">Open Payroll</a>
                        </div>

                    <%-- MANAGER DASHBOARD CONTENT --%>
                    <% } else if (currentURL.contains("manager-dashboard")) { %>
                        
                        <div class="hrms-card border-blue">
                            <div class="card-title">Team Attendance <span class="badge badge-blue">Syncing</span></div>
                            <div class="card-value"><%= cTeamAttend %></div>
                            <p class="card-desc">Team members present today.</p>
                        </div>
                        
                        <div class="hrms-card border-orange">
                            <div class="card-title">Leave Approvals <span class="badge badge-yellow">Pending</span></div>
                            <div class="card-value"><%= cTeamPendingLeaves %></div>
                            <p class="card-desc">Awaiting your approval.</p>
                        </div>
                        
                        <div class="hrms-card border-purple">
                            <div class="card-title">Team Reports</div>
                            <div class="card-value" style="font-size: 1.8rem;">Matrix View</div>
                            <a href="/web/hrms/team-reports" class="btn-action btn-outline">Open Reports</a>
                        </div>

                    <%-- ADMIN & HR DASHBOARD CONTENT --%>
                    <% } else if (currentURL.contains("admin-dashboard") || currentURL.contains("hr-dashboard")) { %>
                        
                        <div class="hrms-card border-blue">
                            <div class="card-title">Total Staff</div>
                            <div class="card-value"><%= cTotalEmp %></div>
                            <p class="card-desc">Registered in system.</p>
                        </div>
                        
                        <div class="hrms-card border-green">
                            <div class="card-title">Active Employees</div>
                            <div class="card-value"><%= cActiveEmp %></div>
                        </div>
                        
                        <div class="hrms-card border-orange">
                            <div class="card-title">Pending Leaves <span class="badge badge-yellow">Action Required</span></div>
                            <div class="card-value"><%= cPendingLeaves %></div>
                        </div>
                        
                        <div class="hrms-card border-teal" style="grid-column: 1 / -1; flex-direction: row; align-items: center; justify-content: space-around; padding: 2rem;">
                            <a href="/web/hrms/employee-management" class="btn-action btn-outline" style="margin:0;">Manage Directory</a>
                            <a href="/web/hrms/department-management" class="btn-action btn-outline" style="margin:0;">Manage Departments</a>
                            <a href="/web/hrms/employee-reports" class="btn-action btn-solid" style="margin:0;">View Full Reports &rarr;</a>
                        </div>

                    <% } %>

                </div>
            <% } %>
        </main>
    </div>
</div>