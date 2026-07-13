<%@ include file="/init.jsp" %>

<%-- Import --%>
<%@ page pageEncoding="UTF-8" %>
<%@ page import="com.hrms.employee.model.Department" %>

<%-- Read request attribute --%>
<%
    Department department = (Department) request.getAttribute("department");
    
    // Fallback safety instantiation to prevent blank screen NullPointerExceptions
    if (department == null) {
        department = (Department) request.getAttribute("com.hrms.employee.model.Department");
    }
%>

<div class="container-fluid my-4">

    <h2>Edit Department</h2>

    <%-- Create portlet action URL --%>
    <portlet:actionURL name="/department/update" var="updateDepartmentURL" />

    <%-- Create form --%>
    <form action="${updateDepartmentURL}" method="post" class="department-edit-form">

        <%-- Hidden Field: departmentId --%>
        <input type="hidden" name="<portlet:namespace />departmentId" 
               value="<%= (department != null) ? department.getDepartmentId() : "" %>" />

        <%-- Text Field: departmentCode --%>
        <div class="form-group">
            <label for="departmentCode">Department Code:</label>
            <input type="text" id="departmentCode" name="<portlet:namespace />departmentCode" class="form-control" 
                   value="<%= (department != null && department.getDepartmentCode() != null) ? department.getDepartmentCode() : "" %>" required />
        </div>

        <%-- Text Field: departmentName --%>
        <div class="form-group">
            <label for="departmentName">Department Name:</label>
            <input type="text" id="departmentName" name="<portlet:namespace />departmentName" class="form-control" 
                   value="<%= (department != null && department.getDepartmentName() != null) ? department.getDepartmentName() : "" %>" required />
        </div>

        <%-- Textarea Field: description --%>
        <div class="form-group">
            <label for="description">Description:</label>
            <textarea id="description" name="<portlet:namespace />description" class="form-control" rows="3"><%= (department != null && department.getDescription() != null) ? department.getDescription() : "" %></textarea>
        </div>

        <%-- Text Field: status --%>
        <div class="form-group">
            <label for="status">Status:</label>
            <input type="text" id="status" name="<portlet:namespace />status" class="form-control" 
                   value="<%= (department != null && department.getStatus() != null) ? department.getStatus() : "" %>" />
        </div>
        
        <%-- Actions Block --%>
        <div class="form-actions mt-4">
            <%-- Submit button --%>
            <button type="submit" class="btn btn-success">Update Department</button>
            
            <%-- Cancel link --%>
            <a href="/web/hrms/department-management" class="btn btn-secondary ml-2">Cancel</a>
        </div>
        
    </form>

</div>