<%@ include file="/init.jsp" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page import="com.hrms.employee.model.Designation" %>

<%
    Designation designation = (Designation) request.getAttribute("designation");
    if (designation == null) {
        designation = (Designation) request.getAttribute("com.hrms.employee.model.Designation");
    }
    String currentStatus = (designation != null && designation.getStatus() != null) ? designation.getStatus() : "";
%>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" />
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700;800;900&display=swap" />

<style type="text/css">
    .hrms-wrapper { background: #f8fafc; font-family: "Inter", sans-serif; font-size: 14px; padding: 2rem; min-height: calc(100vh - 80px); }
    .page-header { background: linear-gradient(135deg, #1e293b 0%, #0f172a 100%); padding: 2rem 2.5rem; border-radius: 20px; color: white; margin-bottom: 2rem; box-shadow: 0 10px 25px rgba(0,0,0,0.1); display: flex; justify-content: space-between; align-items: center; }
    .page-header h2 { font-size: 1.8rem; font-weight: 900; margin: 0 0 0.25rem; color: white; }
    .page-header p { margin: 0; font-size: 0.95rem; color: #cbd5e1; font-weight: 500; }
    .btn-back-inline { display: inline-flex; align-items: center; gap: 0.5rem; color: white; text-decoration: none; font-weight: 600; font-size: 0.85rem; background: rgba(255, 255, 255, 0.15); padding: 0.4rem 1rem; border-radius: 999px; border: 1px solid rgba(255, 255, 255, 0.2); transition: 0.2s; }
    .btn-back-inline:hover { background: rgba(255, 255, 255, 0.25); transform: translateX(-4px); color: white; text-decoration: none; }
    .header-icon { font-size: 3rem; opacity: 0.9; color: #38bdf8; }
    
    .hrms-card { background: #ffffff; border: 1px solid #e2e8f0; border-radius: 20px; box-shadow: 0 10px 30px rgba(0, 86, 179, 0.06); overflow: hidden; margin-bottom: 2rem; }
    .card-header-accent { background: #f1f5f9; padding: 1.25rem 1.5rem; border-bottom: 1px solid #e2e8f0; font-weight: 800; color: #0f172a; font-size: 1.1rem; display: flex; justify-content: space-between; align-items: center; }
    .card-body-padded { padding: 1.5rem; }

    .form-grid { display: grid; grid-template-columns: 1fr 1fr; gap: 1.5rem; }
    .form-group { margin-bottom: 1.25rem; }
    .form-group.full-width { grid-column: span 2; }
    .form-group label { font-weight: 700; color: #475569; font-size: 0.85rem; margin-bottom: 0.4rem; display: block; }
    .form-control { width: 100%; padding: 0.75rem 1rem; border: 2px solid #e2e8f0; border-radius: 10px; font-family: "Inter", sans-serif; font-size: 0.9rem; transition: 0.2s; outline: none; background: #ffffff; }
    .form-control:focus { border-color: #0088cc; box-shadow: 0 0 0 3px rgba(0, 136, 204, 0.1); }
    select.form-control { -webkit-appearance: none; -moz-appearance: none; appearance: none; background-image: url('data:image/svg+xml;utf8,<svg fill="%2364748b" height="24" viewBox="0 0 24 24" width="24" xmlns="http://www.w3.org/2000/svg"><path d="M7 10l5 5 5-5z"/><path d="M0 0h24v24H0z" fill="none"/></svg>'); background-repeat: no-repeat; background-position: right 0.75rem center; cursor: pointer; }
    
    .form-actions { display: flex; gap: 1rem; margin-top: 1rem; }
    .btn-submit { flex: 1; background: linear-gradient(135deg, #0056b3 0%, #0088cc 100%); color: white; border: none; padding: 0.85rem 1.5rem; border-radius: 10px; font-weight: 800; font-size: 0.95rem; cursor: pointer; transition: 0.2s; display: inline-flex; align-items: center; justify-content: center; gap: 0.5rem; text-decoration: none;}
    .btn-submit:hover { box-shadow: 0 8px 15px rgba(0, 136, 204, 0.25); transform: translateY(-2px); color: white;}
    .btn-cancel { flex: 1; background: #f1f5f9; color: #475569; border: 1px solid #cbd5e1; padding: 0.85rem 1.5rem; border-radius: 10px; font-weight: 700; font-size: 0.95rem; cursor: pointer; transition: 0.2s; display: inline-flex; align-items: center; justify-content: center; gap: 0.5rem; text-decoration: none;}
    .btn-cancel:hover { background: #e2e8f0; color: #0f172a; text-decoration: none;}
</style>

<div class="hrms-wrapper">

    <header class="page-header">
        <div>
            <div style="margin-bottom: 1rem;">
                <a href="/web/hrms/designation-management" class="btn-back-inline"><i class="fa-solid fa-arrow-left"></i> Back to Directory</a>
            </div>
            <h2>Edit Designation</h2>
            <p>Update corporate role information and status.</p>
        </div>
        <div class="header-icon"><i class="fa-solid fa-briefcase"></i></div>
    </header>

    <div class="hrms-card" style="border-top: 5px solid #0088cc;">
        <div class="card-header-accent">
            <div><i class="fa-solid fa-id-card-clip" style="color: #0088cc; margin-right: 0.5rem;"></i> Designation Details</div>
        </div>
        <div class="card-body-padded">
            
            <portlet:actionURL name="/designation/update" var="updateDesignationURL" />

            <form action="${updateDesignationURL}" method="post">
                <input type="hidden" name="<portlet:namespace />designationId" value="<%= (designation != null) ? designation.getDesignationId() : "" %>" />

                <div class="form-grid">
                    <div class="form-group">
                        <label for="designationCode">Designation Code</label>
                        <input type="text" id="designationCode" name="<portlet:namespace />designationCode" class="form-control" value="<%= (designation != null && designation.getDesignationCode() != null) ? designation.getDesignationCode() : "" %>" required />
                    </div>

                    <div class="form-group">
                        <label for="designationName">Designation Name</label>
                        <input type="text" id="designationName" name="<portlet:namespace />designationName" class="form-control" value="<%= (designation != null && designation.getDesignationName() != null) ? designation.getDesignationName() : "" %>" required />
                    </div>

                    <div class="form-group">
                        <label for="status">Status</label>
                        <select id="status" name="<portlet:namespace />status" class="form-control">
                            <option value="Active" <%= currentStatus.equalsIgnoreCase("Active") ? "selected" : "" %>>Active</option>
                            <option value="Inactive" <%= currentStatus.equalsIgnoreCase("Inactive") ? "selected" : "" %>>Inactive</option>
                        </select>
                    </div>

                    <div class="form-group full-width">
                        <label for="description">Description</label>
                        <textarea id="description" name="<portlet:namespace />description" class="form-control" rows="3"><%= (designation != null && designation.getDescription() != null) ? designation.getDescription() : "" %></textarea>
                    </div>
                </div>

                <div class="form-actions">
                    <a href="/web/hrms/designation-management" class="btn-cancel"><i class="fa-solid fa-xmark"></i> Cancel</a>
                    <button type="submit" class="btn-submit"><i class="fa-solid fa-save"></i> Save Changes</button>
                </div>
            </form>
        </div>
    </div>
</div>