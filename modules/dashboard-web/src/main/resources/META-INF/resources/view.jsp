<%@ include file="/init.jsp" %>

<%-- Liferay Theme Objects for dynamic User Data --%>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<liferay-theme:defineObjects />

<%-- Imports --%>
<%@ page pageEncoding="UTF-8" %>
<%@ page import="com.liferay.portal.kernel.util.HtmlUtil" %>

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

    // Dynamic Approval Hierarchy Variables (Fallbacks applied in UI if null)
    String managerName = (String) request.getAttribute("managerName");
    String hrName = (String) request.getAttribute("hrName");

    // Null safety fallbacks
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
    body, html { margin: 0; padding: 0; height: 100vh; overflow: hidden; background-color: #f8fafc; font-size: 14px; }
    
    .hrms-app-wrapper {
        display: flex; height: 100vh; font-family: "Inter", sans-serif; background-color: #f8fafc; position: relative;
    }

    /* CSS Particles */
    .bg-particles { position: absolute; inset: 0; z-index: 0; pointer-events: none; overflow: hidden; }
    .particle { position: absolute; border-radius: 50%; background: radial-gradient(circle, rgba(0,136,204,0.08) 0%, rgba(255,255,255,0) 70%); animation: floatParticle linear infinite; }
    .p1 { width: 400px; height: 400px; top: -100px; left: -100px; animation-duration: 25s; }
    .p2 { width: 500px; height: 500px; bottom: -150px; right: -100px; animation-duration: 30s; animation-direction: reverse; }
    @keyframes floatParticle { 0% { transform: translateY(0) rotate(0deg); } 50% { transform: translateY(-40px) rotate(180deg); } 100% { transform: translateY(0) rotate(360deg); } }

    /* Top Navbar */
    .top-navbar {
        background: #ffffff; padding: 1rem 2rem; display: flex; justify-content: space-between; align-items: center;
        box-shadow: 0 4px 15px rgba(0,0,0,0.03); z-index: 100;
    }
    .top-nav-links { display: flex; gap: 2rem; align-items: center; font-size: 0.9rem; font-weight: 600; color: #64748b;}
    .top-nav-links a { color: #64748b; text-decoration: none; transition: 0.2s;}
    .top-nav-links a:hover { color: #0088cc; }
    .user-avatar { width: 35px; height: 35px; border-radius: 50%; background: #eaf4fc; border: 1px solid #bae6fd; display: flex; align-items: center; justify-content: center; color: #0056b3; }

    /* Fixed Sidebar */
    .hrms-sidebar {
        width: 260px; background: rgba(255,255,255,0.9); backdrop-filter: blur(20px); border-right: 1px solid #e2e8f0;
        padding: 1.5rem 1rem; display: flex; flex-direction: column; z-index: 50; position: relative;
    }
    .sidebar-category { font-size: 0.75rem; font-weight: 800; color: #94a3b8; margin: 1rem 0 0.5rem 1rem; text-transform: uppercase; letter-spacing: 0.05em; }
    .nav-menu { list-style: none; padding: 0; margin: 0; display: flex; flex-direction: column; gap: 0.4rem; flex: 1; }
    .nav-menu a { display: flex; align-items: center; gap: 0.85rem; padding: 0.8rem 1rem; color: #475569; text-decoration: none; font-size: 0.95rem; font-weight: 600; border-radius: 12px; transition: 0.2s; }
    .nav-menu a i { font-size: 1.1rem; width: 20px; text-align: center; }
    .nav-menu a:hover { background: #f1f5f9; color: #0f172a; transform: translateX(4px); }
    .nav-menu a.active { background: linear-gradient(135deg, #0056b3 0%, #0088cc 100%); color: white; box-shadow: 0 4px 12px rgba(0, 136, 204, 0.2); }
    
    .btn-ai-summariser { background: linear-gradient(45deg, #0f172a, #1e293b) !important; color: #38bdf8 !important; justify-content: center; font-weight: 700 !important; margin-top: auto; border: 1px solid rgba(56, 189, 248, 0.2); }
    .btn-ai-summariser:hover { transform: translateY(-2px) !important; box-shadow: 0 4px 15px rgba(56, 189, 248, 0.2); color: #fff !important; }

    /* Main Content */
    .hrms-main-content { flex: 1; overflow-y: auto; display: flex; flex-direction: column; position: relative; z-index: 10; }
    .hrms-main-content::-webkit-scrollbar { width: 6px; } .hrms-main-content::-webkit-scrollbar-thumb { background: #cbd5e1; border-radius: 10px; }

    .dash-hero {
        background: linear-gradient(135deg, #1e293b 0%, #0f172a 100%); padding: 2.5rem 3rem 5rem;
        color: white; border-bottom-left-radius: 30px; border-bottom-right-radius: 30px; flex-shrink: 0; margin-bottom: -3rem; box-shadow: 0 10px 25px rgba(0,0,0,0.1);
    }
    .dash-hero h1 { font-size: 2.2rem; font-weight: 900; margin-bottom: 0.25rem; }
    .dash-hero p { color: #cbd5e1; font-size: 1rem; margin: 0; }

    .dashboard-container { padding: 0 3rem 3rem; display: flex; flex-direction: column; gap: 1.5rem; position: relative; z-index: 20; }
    .dashboard-grid { display: grid; gap: 1.5rem; grid-template-columns: repeat(4, 1fr); }

    /* Glass Cards with Shiny Effect */
    .hrms-card {
        background: rgba(255,255,255,0.9); border: 1px solid rgba(226,232,240,0.8); border-radius: 20px; padding: 1.5rem; box-shadow: 0 8px 32px rgba(31,38,135,0.05); backdrop-filter: blur(10px); display: flex; flex-direction: column; justify-content: space-between; position: relative; overflow: hidden; transition: 0.3s;
    }
    .hrms-card:hover { transform: translateY(-5px); box-shadow: 0 15px 35px rgba(0, 86, 179, 0.1); }
    .shiny-effect::before { content: ''; position: absolute; top: 0; left: -150%; width: 50%; height: 100%; background: linear-gradient(to right, rgba(255,255,255,0) 0%, rgba(255,255,255,0.5) 50%, rgba(255,255,255,0) 100%); transform: skewX(-25deg); transition: 0.6s; z-index: 1; pointer-events: none;}
    .hrms-card:hover::before { left: 150%; }

    .metric-label { font-size: 0.8rem; font-weight: 800; color: #64748b; text-transform: uppercase; letter-spacing: 0.05em; display: flex; justify-content: space-between; align-items: center; margin-bottom: 0.75rem; position: relative; z-index: 2;}
    .metric-value { font-size: 2.2rem; font-weight: 900; color: #0f172a; line-height: 1; margin-bottom: 0.5rem; position: relative; z-index: 2;}
    .card-desc { font-size: 0.85rem; color: #64748b; margin: 0; line-height: 1.4; position: relative; z-index: 2;}
    
    /* New Action Link for clickable cards */
    .card-action { margin-top: 1.2rem; font-weight: 700; font-size: 0.85rem; display: flex; align-items: center; transition: transform 0.2s; position: relative; z-index: 2;}
    .hrms-card:hover .card-action { transform: translateX(4px); }

    /* Accents */
    .accent-blue { border-top: 5px solid #3b82f6 !important; }
    .accent-green { border-top: 5px solid #10b981 !important; }
    .accent-orange { border-top: 5px solid #f59e0b !important; }
    .accent-purple { border-top: 5px solid #8b5cf6 !important; }
    .accent-teal { border-top: 5px solid #14b8a6 !important; }
    
    .placeholder-badge { font-size: 0.7rem; padding: 4px 10px; border-radius: 50px; font-weight: 700; background-color: #f1f5f9; color: #475569; border: 1px solid #e2e8f0; }

    /* Widgets Area */
    .widget-grid { display: grid; grid-template-columns: 1fr 1fr; gap: 1.5rem; }
    .widget-header { display: flex; align-items: center; gap: 0.5rem; font-size: 1.1rem; font-weight: 800; color: #0f172a; margin-bottom: 1rem; border-bottom: 2px solid #f1f5f9; padding-bottom: 0.5rem;}
    
    /* Approval Chain UI */
    .approval-chain { display: flex; flex-direction: column; gap: 1rem; }
    .chain-step { display: flex; align-items: center; gap: 1rem; background: #f8fafc; padding: 0.75rem 1rem; border-radius: 12px; border: 1px solid #e2e8f0;}
    .chain-icon { width: 35px; height: 35px; border-radius: 50%; background: #eaf4fc; color: #0088cc; display: flex; align-items: center; justify-content: center; font-size: 1rem; font-weight: 800;}
    
    /* Attendance List UI */
    .log-list { list-style: none; padding: 0; margin: 0; display: flex; flex-direction: column; gap: 0.5rem; }
    .log-item { display: flex; justify-content: space-between; align-items: center; padding: 0.75rem 1rem; background: #f8fafc; border-radius: 10px; border: 1px solid #e2e8f0;}
    .log-status { background: #ecfdf5; color: #047857; padding: 0.2rem 0.6rem; border-radius: 6px; font-size: 0.75rem; font-weight: 700;}

    /* Loader */
    .router-loader { display: flex; flex-direction: column; align-items: center; justify-content: center; height: 100vh; width: 100%; text-align: center; }
</style>

<div class="hrms-app-wrapper">
    <div class="bg-particles"><div class="particle p1"></div><div class="particle p2"></div></div>

    <%-- ========================================== --%>
    <%-- ROUTE: DASHBOARD ROUTER                    --%>
    <%-- ========================================== --%>
    <% if (currentURL.contains("dashboard-router")) { %>
        <% if (sAdmin || sHR || sManager || sEmployee) { %>
            <div class="router-loader">
                <div style="font-size: 3.5rem; color: #0088cc; margin-bottom: 1.5rem;"><i class="fa-solid fa-circle-notch fa-spin"></i></div>
                <h2 style="font-weight: 900; color: #0f172a; font-size: 2rem;">Authenticating Workspace...</h2>
                <p style="color: #64748b;">Establishing secure functional workspace layer parameters.</p>
            </div>
            <script>
                if (<%= sAdmin %>) window.location.replace('/web/hrms/admin-dashboard');
                else if (<%= sHR %>) window.location.replace('/web/hrms/hr-dashboard');
                else if (<%= sManager %>) window.location.replace('/web/hrms/manager-dashboard');
                else window.location.replace('/web/hrms/employee-dashboard');
            </script>
        <% } else { %>
            <div class="router-loader">
                <div style="font-size: 4rem; color: #ef4444; margin-bottom: 1rem;"><i class="fa-solid fa-triangle-exclamation"></i></div>
                <h2 style="font-weight: 900; color: #0f172a;">Access Denied</h2>
                <p style="color: #64748b;">Your profile lacks verified dashboard mappings. Contact platform operations.</p>
            </div>
        <% } %>

    <% } else { %>

        <!-- Unified Role-Strict Sidebar -->
        <aside class="hrms-sidebar">
            <a href="/web/hrms/" style="display: flex; align-items: center; gap: 0.5rem; text-decoration: none; color: #0f172a; font-weight: 900; font-size: 1.1rem; margin: 0.5rem 0 1.5rem 0.5rem;">
                <div style="background: linear-gradient(135deg, #0056b3, #0088cc); color: white; width: 32px; height: 32px; border-radius: 8px; display: flex; align-items: center; justify-content: center; font-size: 0.8rem;"><i class="fa-solid fa-layer-group"></i></div>
                HRMS Portal
            </a>
            
            <div class="sidebar-category">PORTAL MENU</div>
            <ul class="nav-menu">
                
                <%-- Admin Sidebar --%>
                <% if (sAdmin) { %>
                    <li><a href="/web/hrms/admin-dashboard" class="<%= currentURL.contains("admin") ? "active" : "" %>"><i class="fa-solid fa-shield-halved"></i> Admin Space</a></li>
                    <li><a href="/web/hrms/employee-management"><i class="fa-solid fa-address-book"></i> Directory</a></li>
                    <li><a href="/web/hrms/department-management"><i class="fa-solid fa-sitemap"></i> Departments</a></li>
                    <li><a href="/web/hrms/designation-management"><i class="fa-solid fa-briefcase"></i> Designations</a></li>
                    <li><a href="/web/hrms/employee-reports"><i class="fa-solid fa-chart-column"></i> Reports</a></li>
                <% } %>
                
                <%-- HR Sidebar --%>
                <% if (sHR && !sAdmin) { %>
                    <li><a href="/web/hrms/hr-dashboard" class="<%= currentURL.contains("hr") ? "active" : "" %>"><i class="fa-solid fa-users-gear"></i> HR Space</a></li>
                    <li><a href="/web/hrms/leave-approvals1" class="<%= currentURL.contains("leave-approvals1") ? "active" : "" %>"><i class="fa-solid fa-check-to-slot"></i> Approvals</a></li>
                    <li><a href="/web/hrms/employee-management"><i class="fa-solid fa-address-book"></i> Directory</a></li>
                    <li><a href="/web/hrms/employee-reports"><i class="fa-solid fa-chart-column"></i> Reports</a></li>
                <% } %>

                <%-- Manager Sidebar --%>
                <% if (sManager && !sAdmin && !sHR) { %>
                    <li><a href="/web/hrms/manager-dashboard" class="<%= currentURL.contains("manager") ? "active" : "" %>"><i class="fa-solid fa-chart-pie"></i> Manager Space</a></li>
                    <li><a href="/web/hrms/leave-approvals1" class="<%= currentURL.contains("leave-approvals1") ? "active" : "" %>"><i class="fa-solid fa-check-to-slot"></i> Approvals</a></li>
                <% } %>

                <%-- Employee Sidebar --%>
                <% if (sEmployee && !sAdmin && !sHR && !sManager) { %>
                    <li><a href="/web/hrms/employee-dashboard" class="<%= currentURL.contains("employee-dashboard") ? "active" : "" %>"><i class="fa-solid fa-house"></i> My Space</a></li>
                    <li><a href="/web/hrms/my-profile" class="<%= currentURL.contains("my-profile") ? "active" : "" %>"><i class="fa-solid fa-id-card"></i> My Profile</a></li>
                    <li><a href="/web/hrms/my-leaves" class="<%= currentURL.contains("my-leaves") ? "active" : "" %>"><i class="fa-solid fa-calendar-minus"></i> My Leaves</a></li>
                <% } %>

                <%-- Global AI Button --%>
                <% if (sEmployee || sAdmin || sHR || sManager) { %>
                    <li><a href="/web/hrms/ai_assistance" class="btn-ai-summariser <%= currentURL.contains("ai") ? "active" : "" %>"><i class="fa-solid fa-wand-magic-sparkles"></i> AI Summariser</a></li>
                <% } %>
            </ul>
        </aside>

        <!-- Main Content Area -->
        <main class="hrms-main-content">
            
            <header class="top-navbar">
                <div style="font-weight: 700; color: #64748b;">Enterprise Workspace</div>
                <div class="top-nav-links">
                    <a href="/web/hrms/dashboard-router">Home</a>
                    <a href="/web/hrms/my-profile">Profile</a>
                    <div class="user-avatar"><i class="fa-solid fa-user"></i></div>
                </div>
            </header>

            <div class="dash-hero">
                <h1>Welcome back, <%= HtmlUtil.escape(user.getFirstName()) %> ✨</h1>
                <p>
                    <%= currentURL.contains("admin") ? "Global system infrastructure node." : 
                        currentURL.contains("hr") ? "Resource deployment and management hub." : 
                        currentURL.contains("manager") ? "Departmental overview and approvals." : 
                        "Personal employee service desk." %>
                </p>
            </div>

            <div class="dashboard-container">
                
                <%-- ========================================== --%>
                <%-- ADMIN & HR VIEW                            --%>
                <%-- ========================================== --%>
                <% if (currentURL.contains("admin-dashboard") || currentURL.contains("hr-dashboard")) { %>
                    <div class="dashboard-grid">
                        <div class="hrms-card shiny-effect accent-blue" style="cursor:pointer;" onclick="window.location.href='/web/hrms/employee-management'">
                            <div>
                                <div class="metric-label">Total Employees <i class="fa-solid fa-users"></i></div>
                                <div class="metric-value"><%= cTotalEmp %></div>
                                <p class="card-desc">Review the complete company directory and profiles.</p>
                            </div>
                            <div class="card-action" style="color: #3b82f6;">Open Directory &rarr;</div>
                        </div>
                        
                        <div class="hrms-card shiny-effect accent-green" style="cursor:pointer;" onclick="window.location.href='/web/hrms/employee-management'">
                            <div>
                                <div class="metric-label">Active Accounts <i class="fa-solid fa-user-check"></i></div>
                                <div class="metric-value"><%= cActiveEmp %></div>
                                <p class="card-desc">Manage accounts currently verified and online.</p>
                            </div>
                            <div class="card-action" style="color: #10b981;">Manage Accounts &rarr;</div>
                        </div>
                        
                        <div class="hrms-card shiny-effect accent-orange" style="cursor:pointer;" onclick="window.location.href='/web/hrms/leave-approvals'">
                            <div>
                                <div class="metric-label">Pending Leaves <span class="placeholder-badge" style="background:#fef3c7;color:#b45309;">Action</span></div>
                                <div class="metric-value"><%= cPendingLeaves %></div>
                                <p class="card-desc">Leave applications currently awaiting your final policy approval.</p>
                            </div>
                            <div class="card-action" style="color: #f59e0b;">Review Queue &rarr;</div>
                        </div>
                        
                        <div class="hrms-card shiny-effect accent-purple" style="cursor:pointer;" onclick="window.location.href='/web/hrms/employee-reports'">
                            <div>
                                <div class="metric-label">Present Today <span class="placeholder-badge" style="background:#eff6ff;color:#1d4ed8;">Live</span></div>
                                <div class="metric-value"><%= cPresentToday %></div>
                                <p class="card-desc">Headcount of employees successfully checked into campus.</p>
                            </div>
                            <div class="card-action" style="color: #8b5cf6;">View Reports &rarr;</div>
                        </div>
                    </div>
                <% } %>

                <%-- ========================================== --%>
                <%-- MANAGER VIEW                               --%>
                <%-- ========================================== --%>
                <% if (currentURL.contains("manager-dashboard")) { %>
                    <div class="dashboard-grid" style="grid-template-columns: repeat(3, 1fr);">
                        <div class="hrms-card shiny-effect accent-blue" style="cursor:pointer;" onclick="window.location.href='/web/hrms/employee-reports'">
                            <div>
                                <div class="metric-label">Team Attendance <i class="fa-solid fa-users"></i></div>
                                <div class="metric-value"><%= cTeamAttend %></div>
                                <p class="card-desc">Monitor daily check-ins and roster availability for your direct reports.</p>
                            </div>
                            <div class="card-action" style="color: #3b82f6;">View Roster &rarr;</div>
                        </div>
                        
                        <div class="hrms-card shiny-effect accent-orange" style="cursor:pointer;" onclick="window.location.href='/web/hrms/leave-approvals'">
                            <div>
                                <div class="metric-label">Pending Approvals <span class="placeholder-badge" style="background:#fef3c7;color:#b45309;">Review</span></div>
                                <div class="metric-value"><%= cTeamPendingLeaves %></div>
                                <p class="card-desc">Team leave requests waiting for your Tier-1 managerial approval.</p>
                            </div>
                            <div class="card-action" style="color: #f59e0b;">Review Queue &rarr;</div>
                        </div>
                        
                        <div class="hrms-card shiny-effect accent-purple" style="cursor:pointer;" onclick="window.location.href='/web/hrms/employee-reports'">
                            <div>
                                <div class="metric-label">Shift Compliance <i class="fa-solid fa-chart-line"></i></div>
                                <div class="metric-value">98.5%</div>
                                <p class="card-desc">Your department's weekly adherence to scheduled working hours.</p>
                            </div>
                            <div class="card-action" style="color: #8b5cf6;">Open Analytics &rarr;</div>
                        </div>
                    </div>
                <% } %>

                <%-- ========================================== --%>
                <%-- EMPLOYEE VIEW (With Static Frontend Widgets) --%>
                <%-- ========================================== --%>
                <% if (currentURL.contains("employee-dashboard")) { %>
                    <div class="dashboard-grid">
                        <div class="hrms-card shiny-effect accent-blue" style="cursor:pointer;" onclick="window.location.href='/web/hrms/my-profile'">
                            <div>
                                <div class="metric-label">My Profile</div>
                                <p class="card-desc">Update your personal details, emergency contacts, and security settings.</p>
                            </div>
                            <div class="card-action" style="color: #3b82f6;">View Profile &rarr;</div>
                        </div>
                        
                        <div class="hrms-card shiny-effect accent-green" style="cursor:pointer;" onclick="window.location.href='/web/hrms/my-attendance'">
                            <div>
                                <div class="metric-label">My Attendance <span class="placeholder-badge">Staging</span></div>
                                <p class="card-desc">Review your daily check-in sequences, worked hours, and GPS logs.</p>
                            </div>
                            <div class="card-action" style="color: #10b981;">Open True Time &rarr;</div>
                        </div>
                        
                        <div class="hrms-card shiny-effect accent-orange" style="cursor:pointer;" onclick="window.location.href='/web/hrms/my-leaves'">
                            <div>
                                <div class="metric-label">My Leaves <span class="placeholder-badge" style="background:#fef3c7;color:#b45309;"><%= cMyLeaves %> Req</span></div>
                                <p class="card-desc">Apply for time off and track the status of your pending applications.</p>
                            </div>
                            <div class="card-action" style="color: #f59e0b;">Apply for Leave &rarr;</div>
                        </div>
                        
                        <div class="hrms-card shiny-effect accent-teal" style="cursor:pointer;" onclick="window.location.href='/web/hrms/my-payroll'">
                            <div>
                                <div class="metric-label">My Payslips <span class="placeholder-badge" style="background:#ecfdf5;color:#047857;"><%= cMyPayslips %> Iss</span></div>
                                <p class="card-desc">Securely access and download your monthly salary statements and tax data.</p>
                            </div>
                            <div class="card-action" style="color: #14b8a6;">Open Payroll &rarr;</div>
                        </div>
                    </div>

                    <!-- Decoupled Frontend Widgets for Employee -->
                    <div class="widget-grid">
                        
                        <!-- Leave Approver Chain Widget -->
                        <div class="hrms-card shiny-effect" style="border-top: 4px solid #64748b;">
                            <div class="widget-header">
                                <i class="fa-solid fa-sitemap" style="color: #64748b;"></i> Leave Approval Chain
                            </div>
                            <p style="font-size: 0.85rem; color: #64748b; margin-bottom: 1.5rem;">Your leave requests are routed to the following personnel for status approval.</p>
                            
                            <div class="approval-chain">
                                <div class="chain-step">
                                    <div class="chain-icon">1</div>
                                    <div>
                                        <div style="font-weight: 800; color: #0f172a; font-size: 0.95rem;">
                                            <%= managerName != null ? HtmlUtil.escape(managerName) : "Reporting Manager" %>
                                        </div>
                                        <div style="font-size: 0.8rem; color: #64748b;">Initial Review & Approval</div>
                                    </div>
                                </div>
                                <div style="width: 2px; height: 15px; background: #cbd5e1; margin: -0.5rem 0 -0.5rem 1.1rem;"></div>
                                <div class="chain-step">
                                    <div class="chain-icon" style="background: #fef3c7; color: #b45309;">2</div>
                                    <div>
                                        <div style="font-weight: 800; color: #0f172a; font-size: 0.95rem;">
                                            <%= hrName != null ? HtmlUtil.escape(hrName) : "HR Department" %>
                                        </div>
                                        <div style="font-size: 0.8rem; color: #64748b;">Final Policy Verification</div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- Static Mapped Attendance Logs -->
                        <div class="hrms-card shiny-effect" style="border-top: 4px solid #10b981;">
                            <div class="widget-header">
                                <i class="fa-solid fa-clock-rotate-left" style="color: #10b981;"></i> Recent Attendance Logs
                            </div>
                            <p style="font-size: 0.85rem; color: #64748b; margin-bottom: 1.5rem;">Pending connection to Live GPS Database.</p>
                            
                            <ul class="log-list">
                                <li class="log-item">
                                    <div style="display: flex; align-items: center; gap: 0.75rem;">
                                        <div style="width: 8px; height: 8px; background: #10b981; border-radius: 50%;"></div>
                                        <div>
                                            <div style="font-weight: 700; color: #0f172a; font-size: 0.9rem;">Check-in at 09:02 AM</div>
                                            <div style="font-size: 0.75rem; color: #64748b;">Via Portal GPS</div>
                                        </div>
                                    </div>
                                    <div class="log-status">Verified</div>
                                </li>
                                <li class="log-item">
                                    <div style="display: flex; align-items: center; gap: 0.75rem;">
                                        <div style="width: 8px; height: 8px; background: #f59e0b; border-radius: 50%;"></div>
                                        <div>
                                            <div style="font-weight: 700; color: #0f172a; font-size: 0.9rem;">Check-out at 06:15 PM</div>
                                            <div style="font-size: 0.75rem; color: #64748b;">Via Portal GPS</div>
                                        </div>
                                    </div>
                                    <div class="log-status" style="background: #fef3c7; color: #b45309;">Verified</div>
                                </li>
                            </ul>
                            <div style="margin-top: auto; text-align: center; padding-top: 1rem;">
                                <a href="/web/hrms/my-attendance" style="color: #0088cc; font-size: 0.85rem; font-weight: 700; text-decoration: none;">View Full History &rarr;</a>
                            </div>
                        </div>

                    </div>
                <% } %>
            </div>
        </main>
    <% } %>
</div>