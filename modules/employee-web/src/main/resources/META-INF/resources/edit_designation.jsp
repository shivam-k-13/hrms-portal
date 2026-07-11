<%@ include file="/init.jsp" %>

<%-- Import --%>
<%@ page pageEncoding="UTF-8" %>
<%@ page import="com.hrms.employee.model.Designation" %>

<%-- Read request attribute --%>
<%
    Designation designation = (Designation) request.getAttribute("designation");
    
    // Fallback instantiation to protect against initialization edge cases
    if (designation == null) {
        designation = (Designation) request.getAttribute("com.hrms.employee.model.Designation");
    }
%>

<div class="container-fluid my-4">

    <h2>Edit Designation</h2>

    <%-- Create form action URL --%>
    <portlet:actionURL name="/designation/update" var="updateDesignationURL" />

    <form action="${updateDesignationURL}" method="post" class="designation-edit-form">

        <%-- Hidden Primary Key --%>
        <input type="hidden" name="<portlet:namespace />designationId" 
               value="<%= (designation != null) ? designation.getDesignationId() : "" %>" />

        <%-- Designation Code --%>
        <div class="form-group">
            <label for="designationCode">Designation Code:</label>
            <input type="text" id="designationCode" name="<portlet:namespace />designationCode" class="form-control" 
                   value="<%= (designation != null && designation.getDesignationCode() != null) ? designation.getDesignationCode() : "" %>" required />
        </div>

        <%-- Designation Name --%>
        <div class="form-group">
            <label for="designationName">Designation Name:</label>
            <input type="text" id="designationName" name="<portlet:namespace />designationName" class="form-control" 
                   value="<%= (designation != null && designation.getDesignationName() != null) ? designation.getDesignationName() : "" %>" required />
        </div>

        <%-- Description --%>
        <div class="form-group">
            <label for="description">Description:</label>
            <textarea id="description" name="<portlet:namespace />description" class="form-control" rows="3"><%= (designation != null && designation.getDescription() != null) ? designation.getDescription() : "" %></textarea>
        </div>

        <%-- Status --%>
        <div class="form-group">
            <label for="status">Status:</label>
            <input type="text" id="status" name="<portlet:namespace />status" class="form-control" 
                   value="<%= (designation != null && designation.getStatus() != null) ? designation.getStatus() : "" %>" />
        </div>

        <%-- Form Actions --%>
        <div class="form-actions mt-4">
            <button type="submit" class="btn btn-success">Update Designation</button>
            <a href="/web/hrms/designation-management" class="btn btn-secondary ml-2">Cancel</a>
        </div>

    </form>

</div>