<%@ include file="/init.jsp" %>

<%-- Imports needed --%>
<%@ page pageEncoding="UTF-8" %>
<%@ page import="com.hrms.employee.model.Employee" %>

<%-- Read request attributes --%>
<%
    Employee employee = (Employee) request.getAttribute("employee");
    String loggedInUserEmail = (String) request.getAttribute("loggedInUserEmail");
    
    // Null safety fallback for string rendering
    String userEmail = (loggedInUserEmail != null) ? loggedInUserEmail : "Unknown Email";
%>

<div class="container-fluid my-4">

    <%-- If employee is null --%>
    <% if (employee == null) { %>
        <div class="alert alert-warning shadow-sm" role="alert">
            No HRMS employee profile found for logged-in user email: <strong><%= userEmail %></strong>
        </div>
    <% } else { %>
        
        <%-- If employee is not null --%>
        <div class="profile-container">
            <h2 class="mb-4 text-primary">My Profile</h2>
            
            <div class="card shadow-sm border-0">
                <div class="card-header bg-light py-3">
                    <h5 class="card-title text-muted mb-0 font-weight-bold">Personal & Employment Details</h5>
                </div>
                <div class="card-body p-0">
                    <table class="table table-hover mb-0">
                        <tbody>
                            <tr>
                                <th scope="row" class="w-25 border-top-0 bg-light-cell text-muted font-weight-bold">Employee Code</th>
                                <td class="border-top-0"><%= (employee.getEmployeeCode() != null) ? employee.getEmployeeCode() : "" %></td>
                            </tr>
                            <tr>
                                <th scope="row" class="w-25 bg-light-cell text-muted font-weight-bold">First Name</th>
                                <td><%= (employee.getFirstName() != null) ? employee.getFirstName() : "" %></td>
                            </tr>
                            <tr>
                                <th scope="row" class="w-25 bg-light-cell text-muted font-weight-bold">Last Name</th>
                                <td><%= (employee.getLastName() != null) ? employee.getLastName() : "" %></td>
                            </tr>
                            <tr>
                                <th scope="row" class="w-25 bg-light-cell text-muted font-weight-bold">Email</th>
                                <td><%= (employee.getEmail() != null) ? employee.getEmail() : "" %></td>
                            </tr>
                            <tr>
                                <th scope="row" class="w-25 bg-light-cell text-muted font-weight-bold">Phone Number</th>
                                <td><%= (employee.getPhoneNumber() != null) ? employee.getPhoneNumber() : "" %></td>
                            </tr>
                            <tr>
                                <th scope="row" class="w-25 bg-light-cell text-muted font-weight-bold">Department</th>
                                <td><%= (employee.getDepartment() != null) ? employee.getDepartment() : "" %></td>
                            </tr>
                            <tr>
                                <th scope="row" class="w-25 bg-light-cell text-muted font-weight-bold">Designation</th>
                                <td><%= (employee.getDesignation() != null) ? employee.getDesignation() : "" %></td>
                            </tr>
                            <tr>
                                <th scope="row" class="w-25 bg-light-cell text-muted font-weight-bold">Status</th>
                                <td>
                                    <span class="badge badge-secondary p-2">
                                        <%= (employee.getStatus() != null) ? employee.getStatus() : "" %>
                                    </span>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        
    <% } %>

</div>

<style>
    /* Light styling polish matching Liferay Lexicon specs */
    .bg-light-cell {
        background-color: #f7f8f9;
        width: 30%;
    }
</style>