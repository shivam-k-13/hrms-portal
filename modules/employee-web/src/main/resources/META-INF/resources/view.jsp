	<%@ include file="/init.jsp" %>
	

<%-- Create a portlet action URL --%>
<portlet:actionURL name="/employee/add" var="addEmployeeURL" />

<%-- Display heading --%>
<h2>Employee Test</h2>

<%-- Create an HTML form --%>
<form action="${addEmployeeURL}" method="post">
    
    <div class="form-group">
        <label for="employeeCode">Employee Code:</label>
        <input type="text" id="employeeCode" name="<portlet:namespace />employeeCode" class="form-control" required />
    </div>

    <div class="form-group">
        <label for="firstName">First Name:</label>
        <input type="text" id="firstName" name="<portlet:namespace />firstName" class="form-control" required />
    </div>

    <div class="form-group">
        <label for="lastName">Last Name:</label>
        <input type="text" id="lastName" name="<portlet:namespace />lastName" class="form-control" required />
    </div>

    <div class="form-group">
        <label for="email">Email:</label>
        <input type="email" id="email" name="<portlet:namespace />email" class="form-control" required />
    </div>

    <div class="form-group">
        <label for="phoneNumber">Phone Number:</label>
        <input type="tel" id="phoneNumber" name="<portlet:namespace />phoneNumber" class="form-control" />
    </div>

    <div class="form-group">
        <label for="department">Department:</label>
        <input type="text" id="department" name="<portlet:namespace />department" class="form-control" />
    </div>

    <div class="form-group">
        <label for="designation">Designation:</label>
        <input type="text" id="designation" name="<portlet:namespace />designation" class="form-control" />
    </div>

    <div class="form-group">
        <label for="status">Status:</label>
        <input type="text" id="status" name="<portlet:namespace />status" class="form-control" />
    </div>
    
    <%-- Inside form: Button --%>
    <button type="submit" class="btn btn-primary">Test Action</button>
    
</form>
