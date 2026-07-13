<%@ include file="/init.jsp" %>

<%-- Imports --%>
<%@ page pageEncoding="UTF-8" %>
<%@ page import="com.hrms.employee.model.Employee" %>

<%-- Read request attribute --%>
<%
    Employee employee = (Employee) request.getAttribute("employee");
%>

<div class="container-fluid my-4">

    <% if (employee == null) { %>
        <%-- Null Safety Fallback Alert --%>
        <div class="alert alert-danger" role="alert">
            <h4>Error</h4>
            <p>Employee not found. The requested profile record might have been removed or does not exist.</p>
            <a href="/web/hrms/employee-management" class="btn btn-secondary mt-2">Back to Employee Management</a>
        </div>
    <% } else { %>
        <%-- Employee Profile View --%>
        <div class="d-flex justify-content-between align-items-center mb-4">
            <h2>Employee Details</h2>
            <a href="/web/hrms/employee-management" class="btn btn-secondary">Back</a>
        </div>

        <div class="card shadow-sm border-dark">
            <div class="card-header bg-dark text-white">
                <h5 class="mb-0">Profile Reference: <%= employee.getEmployeeCode() %></h5>
            </div>
            <div class="card-body p-0">
                <table class="table table-striped table-bordered mb-0">
                    <tbody>
                        <tr>
                            <th scope="row" style="width: 30%;">Employee ID</th>
                            <td><%= employee.getEmployeeId() %></td>
                        </tr>
                        <tr>
                            <th scope="row">Employee Code</th>
                            <td><strong><%= employee.getEmployeeCode() %></strong></td>
                        </tr>
                        <tr>
                            <th scope="row">First Name</th>
                            <td><%= employee.getFirstName() %></td>
                        </tr>
                        <tr>
                            <th scope="row">Last Name</th>
                            <td><%= employee.getLastName() %></td>
                        </tr>
                        <tr>
                            <th scope="row">Email Address</th>
                            <td><a href="mailto:<%= employee.getEmail() %>"><%= employee.getEmail() %></a></td>
                        </tr>
                        <tr>
                            <th scope="row">Phone Number</th>
                            <td><%= (employee.getPhoneNumber() != null && !employee.getPhoneNumber().isEmpty()) ? employee.getPhoneNumber() : "-" %></td>
                        </tr>
                        <tr>
                            <th scope="row">Department</th>
                            <td><%= (employee.getDepartment() != null && !employee.getDepartment().isEmpty()) ? employee.getDepartment() : "-" %></td>
                        </tr>
                        <tr>
                            <th scope="row">Designation</th>
                            <td><%= (employee.getDesignation() != null && !employee.getDesignation().isEmpty()) ? employee.getDesignation() : "-" %></td>
                        </tr>
                        <tr>
                            <th scope="row">Status</th>
                            <td>
                                <% 
                                    String status = employee.getStatus();
                                    String badgeClass = "badge-secondary";
                                    if ("Active".equalsIgnoreCase(status)) {
                                        badgeClass = "badge-success";
                                    } else if ("Inactive".equalsIgnoreCase(status)) {
                                        badgeClass = "badge-danger";
                                    }
                                %>
                                <span class="badge <%= badgeClass %> p-2"><%= (status != null) ? status : "Unknown" %></span>
                            </td>
                        </tr>
                        <tr>
                            <th scope="row">Create Date</th>
                            <td><%= (employee.getCreateDate() != null) ? employee.getCreateDate() : "-" %></td>
                        </tr>
                        <tr>
                            <th scope="row">Modified Date</th>
                            <td><%= (employee.getModifiedDate() != null) ? employee.getModifiedDate() : "-" %></td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div class="card-footer bg-light text-right">
                <a href="/web/hrms/employee-management" class="btn btn-outline-dark">Return to List</a>
            </div>
        </div>
    <% } %>

</div>