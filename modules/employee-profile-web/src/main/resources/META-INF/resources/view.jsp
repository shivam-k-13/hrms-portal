<%@ include file="/init.jsp" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page import="com.hrms.employee.model.Employee" %>

<%
    Employee employee = (Employee) request.getAttribute("employee");
    String loggedInUserEmail = (String) request.getAttribute("loggedInUserEmail");
    String userEmail = (loggedInUserEmail != null) ? loggedInUserEmail : "Unknown Email";
%>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" />
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700;800;900&display=swap" />

<style type="text/css">
    .hrms-profile-wrapper {
        background: #f8fafc;
        font-family: "Inter", sans-serif;
        font-size: 15px;
        min-height: 100vh;
        display: flex;
        justify-content: center;
        align-items: flex-start;
        padding: 4rem 2rem;
        position: relative;
    }

    /* Floating Back Button */
    .btn-back-float {
        position: absolute;
        top: 2rem;
        left: 2rem;
        display: inline-flex; 
        align-items: center; 
        gap: 0.5rem;
        background: #ffffff; 
        color: #0f172a; 
        border: 1px solid #e2e8f0;
        padding: 0.6rem 1.2rem; 
        border-radius: 999px; 
        font-weight: 700;
        font-size: 0.9rem; 
        text-decoration: none; 
        box-shadow: 0 4px 15px rgba(0,0,0,0.05);
        transition: 0.2s; 
        z-index: 100;
    }
    .btn-back-float:hover { 
        background: #f1f5f9; 
        color: #0056b3; 
        transform: translateX(-4px); 
    }

    .profile-card {
        background: #ffffff;
        border: 1px solid #e2e8f0;
        border-radius: 20px;
        width: 100%;
        max-width: 900px;
        box-shadow: 0 15px 40px rgba(0, 86, 179, 0.08);
        overflow: hidden;
    }

    .profile-header {
        background: linear-gradient(135deg, #0056b3 0%, #0088cc 100%);
        padding: 2.5rem 3rem;
        color: white;
        display: flex;
        align-items: center;
        gap: 2rem;
    }
    
    .profile-avatar-large {
        width: 90px;
        height: 90px;
        background: rgba(255,255,255,0.2);
        border: 4px solid rgba(255,255,255,0.4);
        border-radius: 50%;
        display: flex;
        align-items: center;
        justify-content: center;
        font-size: 2.5rem;
    }

    .profile-header h2 { margin: 0 0 0.25rem; font-size: 2rem; font-weight: 900; }
    .profile-header p { margin: 0; font-size: 1rem; color: #eaf4fc; font-weight: 500; }

    .profile-body { padding: 3rem; }

    .detail-table { width: 100%; border-collapse: collapse; }
    .detail-table th, .detail-table td { padding: 1.25rem 1rem; border-bottom: 1px solid #f1f5f9; text-align: left; }
    .detail-table th { width: 35%; color: #64748b; font-weight: 600; font-size: 0.95rem; }
    .detail-table td { color: #0f172a; font-weight: 700; font-size: 1.05rem; }
    .detail-table tr:last-child th, .detail-table tr:last-child td { border-bottom: none; }

    .status-badge {
        background: #ecfdf5; color: #047857;
        padding: 0.4rem 1rem; border-radius: 999px;
        font-size: 0.85rem; font-weight: 800; display: inline-block;
        border: 1px solid #a7f3d0;
    }
</style>

<div class="hrms-profile-wrapper">
    
    <!-- Floating Back Button -->
    <a href="/web/hrms/dashboard-router" class="btn-back-float">
        <i class="fa-solid fa-arrow-left"></i> Back to Dashboard
    </a>

    <div class="profile-card">
        <% if (employee == null) { %>
            <div class="profile-header" style="background: linear-gradient(135deg, #ef4444 0%, #b91c1c 100%);">
                <div class="profile-avatar-large"><i class="fa-solid fa-user-xmark"></i></div>
                <div>
                    <h2>Profile Not Found</h2>
                    <p>No records found for <%= userEmail %></p>
                </div>
            </div>
            <div class="profile-body">
                <p style="color: #64748b;">Please contact your HR administrator to ensure your account is properly linked to an employee profile in the NextGen HRMS system.</p>
            </div>
        <% } else { %>
            
            <div class="profile-header">
                <div class="profile-avatar-large"><i class="fa-solid fa-user"></i></div>
                <div>
                    <h2><%= (employee.getFirstName() != null) ? employee.getFirstName() : "Employee" %> <%= (employee.getLastName() != null) ? employee.getLastName() : "" %></h2>
                    <p><%= (employee.getDesignation() != null) ? employee.getDesignation() : "Staff Member" %> &bull; <%= (employee.getDepartment() != null) ? employee.getDepartment() : "General" %></p>
                </div>
            </div>

            <div class="profile-body">
                <h3 style="color: #0f172a; font-weight: 800; margin-bottom: 1.5rem; font-size: 1.3rem;">Employment Details</h3>
                <table class="detail-table">
                    <tbody>
                        <tr>
                            <th>Employee Code</th>
                            <td><%= (employee.getEmployeeCode() != null) ? employee.getEmployeeCode() : "N/A" %></td>
                        </tr>
                        <tr>
                            <th>Email Address</th>
                            <td><%= (employee.getEmail() != null) ? employee.getEmail() : "N/A" %></td>
                        </tr>
                        <tr>
                            <th>Phone Number</th>
                            <td><%= (employee.getPhoneNumber() != null) ? employee.getPhoneNumber() : "N/A" %></td>
                        </tr>
                        <tr>
                            <th>Department</th>
                            <td><%= (employee.getDepartment() != null) ? employee.getDepartment() : "N/A" %></td>
                        </tr>
                        <tr>
                            <th>Current Designation</th>
                            <td><%= (employee.getDesignation() != null) ? employee.getDesignation() : "N/A" %></td>
                        </tr>
                        <tr>
                            <th>Account Status</th>
                            <td>
                                <span class="status-badge">
                                    <%= (employee.getStatus() != null) ? employee.getStatus() : "Active" %>
                                </span>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        <% } %>
    </div>
</div>