<%@ include file="/init.jsp" %>
<%@ page import="com.hrms.leave.model.LeaveRequest" %>
<%@ page import="com.hrms.leave.service.LeaveRequestLocalServiceUtil" %>
<%@ page import="com.liferay.portal.kernel.util.HtmlUtil" %>
<%@ page import="com.liferay.portal.kernel.util.Validator" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.List" %>

<portlet:actionURL name="/leave/apply" var="applyLeaveURL" />

<%
    DateFormat leaveDateFormat = new SimpleDateFormat("dd-MMM-yyyy", themeDisplay.getLocale());
    leaveDateFormat.setTimeZone(themeDisplay.getTimeZone());

    // Automatically get the logged-in user's ID
    long currentUserId = themeDisplay.getUserId();
%>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" />
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700;800;900&display=swap" />

<style type="text/css">
    .hrms-leave-wrapper {
        background: #f8fafc;
        font-family: "Inter", sans-serif;
        font-size: 15px;
        padding: 2rem;
        min-height: calc(100vh - 80px);
    }

    /* Page Header & Inline Back Button */
    .page-header {
        background: linear-gradient(135deg, #0056b3 0%, #0088cc 100%);
        padding: 2rem 2.5rem;
        border-radius: 20px;
        color: white;
        margin-bottom: 1.5rem;
        box-shadow: 0 10px 25px rgba(0, 86, 179, 0.15);
        display: flex;
        justify-content: space-between;
        align-items: center;
    }
    
    .btn-back-inline {
        display: inline-flex; 
        align-items: center; 
        gap: 0.5rem; 
        color: white; 
        text-decoration: none; 
        font-weight: 600; 
        font-size: 0.85rem; 
        background: rgba(255, 255, 255, 0.15); 
        padding: 0.4rem 1rem; 
        border-radius: 999px; 
        border: 1px solid rgba(255, 255, 255, 0.2); 
        transition: 0.2s;
    }
    .btn-back-inline:hover { 
        background: rgba(255, 255, 255, 0.25); 
        transform: translateX(-4px); 
        color: white; 
    }
    
    .page-header h2 { font-size: 1.8rem; font-weight: 900; margin: 0 0 0.25rem; color: white; }
    .page-header p { margin: 0; font-size: 0.95rem; color: #eaf4fc; font-weight: 500; }
    .header-icon { font-size: 3rem; opacity: 0.9; }

    /* 2-Column Layout */
    .leave-layout {
        display: grid;
        grid-template-columns: 380px 1fr;
        gap: 1.5rem;
        align-items: start;
    }

    /* Glass Cards */
    .hrms-card {
        background: #ffffff; 
        border: 1px solid #e2e8f0; 
        border-radius: 20px;
        box-shadow: 0 10px 30px rgba(0, 86, 179, 0.06); 
        overflow: hidden;
    }
    .card-header-accent {
        background: #f1f5f9; 
        padding: 1.25rem 1.5rem; 
        border-bottom: 1px solid #e2e8f0;
        font-weight: 800; 
        color: #0f172a; 
        font-size: 1.1rem;
        display: flex;
        justify-content: space-between;
        align-items: center;
    }
    .card-body-padded { 
        padding: 1.5rem; 
    }

    /* Modern AUI Form Styling Overrides */
    .modern-aui-form .control-group { margin-bottom: 1.25rem; }
    .modern-aui-form .control-label { font-weight: 700; color: #475569; font-size: 0.85rem; margin-bottom: 0.4rem; display: block;}
    
    /* Input and Textarea Fixes */
    .modern-aui-form input[type="text"], 
    .modern-aui-form input[type="number"], 
    .modern-aui-form input[type="date"], 
    .modern-aui-form textarea {
        width: 100%; 
        padding: 0.75rem 1rem; 
        border: 2px solid #e2e8f0; 
        border-radius: 10px;
        font-family: "Inter", sans-serif; 
        font-size: 0.9rem; 
        transition: 0.2s; 
        outline: none; 
        background: #ffffff;
    }

    /* CUSTOM SELECT OVERRIDE FIX */
    .modern-aui-form select, .custom-status-filter {
        width: 100%; 
        padding: 0.75rem 2.5rem 0.75rem 1rem; /* Extra right padding for arrow */
        border: 2px solid #e2e8f0; 
        border-radius: 10px;
        font-family: "Inter", sans-serif; 
        font-size: 0.9rem; 
        transition: 0.2s; 
        outline: none; 
        background-color: #ffffff;
        -webkit-appearance: none;
        -moz-appearance: none;
        appearance: none;
        background-image: url('data:image/svg+xml;utf8,<svg fill="%2364748b" height="24" viewBox="0 0 24 24" width="24" xmlns="http://www.w3.org/2000/svg"><path d="M7 10l5 5 5-5z"/><path d="M0 0h24v24H0z" fill="none"/></svg>');
        background-repeat: no-repeat;
        background-position: right 0.75rem center;
        cursor: pointer;
    }
    .custom-status-filter {
        width: 160px;
        padding: 0.4rem 2.5rem 0.4rem 0.8rem;
        border-color: #cbd5e1;
        font-size: 0.85rem;
        font-weight: 600;
        color: #0f172a;
    }

    .modern-aui-form input:focus, 
    .modern-aui-form select:focus, 
    .modern-aui-form textarea:focus,
    .custom-status-filter:focus {
        border-color: #0088cc; 
        background-color: #ffffff; 
        box-shadow: 0 0 0 3px rgba(0, 136, 204, 0.1);
    }

    .total-days-display {
        background: #eaf4fc; 
        color: #0056b3; 
        font-weight: 800; 
        font-size: 1.2rem;
        padding: 0.75rem; 
        border-radius: 10px; 
        text-align: center; 
        border: 1px solid rgba(0,86,179,0.1);
    }

    /* Form Buttons */
    .form-actions { display: flex; gap: 1rem; margin-top: 2rem; }
    
    .btn-submit { 
        flex: 1; 
        background: linear-gradient(135deg, #0056b3 0%, #0088cc 100%); 
        color: white; 
        border: none; 
        padding: 0.85rem; 
        border-radius: 10px; 
        font-weight: 800; 
        font-size: 0.95rem; 
        cursor: pointer; 
        transition: 0.2s;
    }
    .btn-submit:hover { 
        box-shadow: 0 8px 15px rgba(0, 136, 204, 0.25); 
        transform: translateY(-2px); 
    }
    
    .btn-reset { 
        flex: 1; 
        background: #f1f5f9; 
        color: #475569; 
        border: 1px solid #cbd5e1; 
        padding: 0.85rem; 
        border-radius: 10px; 
        font-weight: 700; 
        cursor: pointer; 
        transition: 0.2s;
    }
    .btn-reset:hover { 
        background: #e2e8f0; 
        color: #0f172a; 
    }

    /* Liferay Table Overrides */
    .table-container { padding: 0 1.5rem 1.5rem; overflow-x: auto;}
    .table-container table { width: 100%; border-collapse: collapse; margin-top: 1rem; }
    
    .table-container th { 
        background: #f1f5f9; 
        padding: 1rem; 
        color: #64748b; 
        font-weight: 700; 
        font-size: 0.85rem; 
        text-align: left; 
        border-bottom: 2px solid #e2e8f0; 
    }
    .table-container td { 
        padding: 1rem; 
        color: #0f172a; 
        font-weight: 600; 
        font-size: 0.85rem; 
        border-bottom: 1px solid #f1f5f9; 
        vertical-align: middle;
    }
    
    /* Beautiful Status Badges */
    .leave-status { padding: 0.35rem 0.85rem; border-radius: 999px; font-size: 0.75rem; font-weight: 800; display: inline-block;}
    .leave-status--pending { background: #fef3c7; color: #b45309; border: 1px solid #fde68a; }
    .leave-status--approved { background: #ecfdf5; color: #047857; border: 1px solid #a7f3d0; }
    .leave-status--rejected { background: #fef2f2; color: #b91c1c; border: 1px solid #fecaca; }
    .leave-status--default { background: #f1f5f9; color: #475569; }

    .leave-type-badge { 
        font-weight: 700; 
        color: #0056b3; 
        background: #eaf4fc; 
        padding: 0.25rem 0.5rem; 
        border-radius: 6px; 
        font-size: 0.75rem;
    }
</style>

<div class="hrms-leave-wrapper">

    <!-- Hero Header -->
    <header class="page-header">
        <div>
            <div style="margin-bottom: 1rem;">
                <a href="/web/hrms/dashboard-router" class="btn-back-inline">
                    <i class="fa-solid fa-arrow-left"></i> Dashboard
                </a>
            </div>
            <h2><liferay-ui:message key="leave-management" /></h2>
            <p><liferay-ui:message key="apply-for-leave-and-track-your-requests" /></p>
        </div>
        <div class="header-icon"><i class="fa-solid fa-calendar-check"></i></div>
    </header>

    <!-- Success & Error Messaging -->
    <liferay-ui:success key="leave-request-submitted" message="leave-request-submitted-successfully" />
    <liferay-ui:success key="leave-request-approved" message="leave-request-approved-successfully" />
    <liferay-ui:success key="leave-request-rejected" message="leave-request-rejected-successfully" />
    <liferay-ui:error key="leave-request-error" message="unable-to-process-leave-request" />
    <liferay-ui:error key="invalid-leave-request" message="please-check-the-entered-leave-information" />
    <liferay-ui:error key="invalid-leave-status" message="only-pending-leave-requests-can-be-processed" />
    <liferay-ui:error key="rejection-comment-required" message="rejection-comment-is-required" />
    <liferay-ui:error key="authentication-required" message="please-sign-in-to-apply-for-leave" />

    <div class="leave-layout">

        <!-- Left Column: Apply Leave Form -->
        <div class="hrms-card" style="border-top: 5px solid #0088cc;">
            <div class="card-header-accent">
                <div><i class="fa-solid fa-file-signature" style="color: #0088cc; margin-right: 0.5rem;"></i> <liferay-ui:message key="apply-leave" /></div>
            </div>
            
            <div class="card-body-padded">
                
                <c:choose>
                    <c:when test="<%= themeDisplay.isSignedIn() %>">
                        
                        <!-- Employee Info Preview Banner -->
                        <div style="background: #f8fafc; padding: 1rem; border-radius: 12px; margin-bottom: 1.5rem; display: flex; gap: 1rem; align-items: center; border: 1px solid #e2e8f0;">
                            <div style="font-size: 2rem; color: #cbd5e1;"><i class="fa-solid fa-id-badge"></i></div>
                            <div>
                                <div style="font-weight: 800; color: #0f172a; font-size: 0.9rem;">
                                    <%= HtmlUtil.escape(user.getFullName()) %>
                                </div>
                                <div style="font-size: 0.75rem; color: #64748b;">
                                    <strong>ID:</strong> <%= user.getUserId() %>
                                    <c:if test="<%= Validator.isNotNull(user.getEmailAddress()) %>">
                                        | <%= HtmlUtil.escape(user.getEmailAddress()) %>
                                    </c:if>
                                </div>
                            </div>
                        </div>

                        <aui:form action="<%= applyLeaveURL %>" cssClass="modern-aui-form" method="post" name="leaveForm">
                            
                            <aui:select label="leave-type" name="leaveType" required="<%= true %>">
                                <aui:option disabled="<%= true %>" selected="<%= true %>" value="">
                                    <liferay-ui:message key="select-leave-type" />
                                </aui:option>
                                <aui:option value="CASUAL"><liferay-ui:message key="casual-leave" /></aui:option>
                                <aui:option value="SICK"><liferay-ui:message key="sick-leave" /></aui:option>
                                <aui:option value="EARNED"><liferay-ui:message key="earned-leave" /></aui:option>
                                <aui:option value="LOP"><liferay-ui:message key="loss-of-pay" /></aui:option>
                                <aui:validator name="required" />
                            </aui:select>

                            <div style="display: flex; gap: 1rem;">
                                <div style="flex: 1;">
                                    <aui:input label="from-date" name="fromDate" required="<%= true %>" type="date">
                                        <aui:validator name="required" />
                                    </aui:input>
                                </div>
                                <div style="flex: 1;">
                                    <aui:input label="to-date" name="toDate" required="<%= true %>" type="date">
                                        <aui:validator name="required" />
                                    </aui:input>
                                </div>
                            </div>

                            <div class="control-group">
                                <label class="control-label" for="<portlet:namespace />totalDays">
                                    <liferay-ui:message key="total-days" />
                                </label>
                                <div class="total-days-display">
                                    <span id="<portlet:namespace />totalDays">0</span> 
                                    <span style="font-size: 0.85rem; font-weight: 600;"><liferay-ui:message key="days" /></span>
                                </div>
                            </div>

                            <aui:input label="reason" maxlength="500" name="reason" placeholder="briefly-explain-the-reason-for-your-leave" required="<%= true %>" type="textarea" cssClass="lfr-textarea-container">
                                <aui:validator name="required" />
                                <aui:validator name="maxLength">500</aui:validator>
                            </aui:input>
                            <p style="font-size: 0.75rem; color: #94a3b8; margin-top: -0.5rem;">
                                <liferay-ui:message key="leave-reason-help-text" />
                            </p>

                            <div class="form-actions">
                                <button type="reset" class="btn-reset">Reset</button>
                                <button type="submit" class="btn-submit">Submit Request</button>
                            </div>
                            
                        </aui:form>
                        
                    </c:when>
                    <c:otherwise>
                        <div style="background: #fef3c7; color: #b45309; border: 1px solid #fde68a; padding: 1rem; border-radius: 12px; display: flex; align-items: center; gap: 0.75rem;">
                            <i class="fa-solid fa-triangle-exclamation" style="font-size: 1.5rem;"></i>
                            <span style="font-weight: 600; font-size: 0.9rem;">
                                <liferay-ui:message key="please-sign-in-to-apply-for-leave" />
                            </span>
                        </div>
                    </c:otherwise>
                </c:choose>
                
            </div>
        </div>

        <!-- Right Column: Leave History Table -->
        <div class="hrms-card" style="border-top: 5px solid #f59e0b;">
            <div class="card-header-accent">
                <div><i class="fa-solid fa-list-ul" style="color: #f59e0b; margin-right: 0.5rem;"></i> <liferay-ui:message key="leave-requests" /></div>
                
                <!-- NEW: JS Filter Dropdown -->
                <select id="leaveTableFilter" class="custom-status-filter" onchange="filterLeaveTable()">
                    <option value="ALL">All Requests</option>
                    <option value="PENDING">Pending</option>
                    <option value="APPROVED">Approved</option>
                    <option value="REJECTED">Rejected</option>
                </select>
            </div>

            <div class="table-container" id="leaveHistoryTableContainer">
                <c:choose>
                    <c:when test="<%= themeDisplay.isSignedIn() %>">
                        
                        <%
                            int currentUserLeaveRequestsCount = LeaveRequestLocalServiceUtil.getLeaveRequestsCountByEmployeeId(currentUserId);
                        %>
                        
                        <liferay-ui:search-container delta="<%= 10 %>" deltaConfigurable="<%= true %>" emptyResultsMessage="no-leave-requests-found" total="<%= currentUserLeaveRequestsCount %>">
                            
                            <%
                                List<LeaveRequest> leaveRequests = LeaveRequestLocalServiceUtil.getLeaveRequestsByEmployeeId(
                                    currentUserId,
                                    searchContainer.getStart(),
                                    searchContainer.getEnd()
                                );
                            %>

                            <liferay-ui:search-container-results results="<%= leaveRequests %>" />

                            <liferay-ui:search-container-row className="com.hrms.leave.model.LeaveRequest" keyProperty="leaveRequestId" modelVar="leaveRequest">
                                <%
                                    String status = leaveRequest.getStatus() != null ? leaveRequest.getStatus() : "";
                                    String statusCssClass = "leave-status--default";
                                    
                                    if ("PENDING".equals(status)) statusCssClass = "leave-status--pending";
                                    else if ("APPROVED".equals(status)) statusCssClass = "leave-status--approved";
                                    else if ("REJECTED".equals(status)) statusCssClass = "leave-status--rejected";

                                    String formattedFromDate = leaveRequest.getFromDate() != null ? leaveDateFormat.format(leaveRequest.getFromDate()) : "-";
                                    String formattedToDate = leaveRequest.getToDate() != null ? leaveDateFormat.format(leaveRequest.getToDate()) : "-";

                                    long numberOfDays = 0;
                                    if (leaveRequest.getFromDate() != null && leaveRequest.getToDate() != null && !leaveRequest.getToDate().before(leaveRequest.getFromDate())) {
                                        long difference = leaveRequest.getToDate().getTime() - leaveRequest.getFromDate().getTime();
                                        numberOfDays = (difference / (24L * 60L * 60L * 1000L)) + 1;
                                    }

                                    String leaveReason = leaveRequest.getReason() != null ? leaveRequest.getReason() : "";
                                    String leaveType = leaveRequest.getLeaveType() != null ? leaveRequest.getLeaveType() : "";
                                %>

                                <liferay-ui:search-container-column-text name="request-id">
                                    <span style="font-weight: 800; color: #64748b;">#<%= leaveRequest.getLeaveRequestId() %></span>
                                </liferay-ui:search-container-column-text>

                                <liferay-ui:search-container-column-text name="leave-type">
                                    <span class="leave-type-badge"><%= HtmlUtil.escape(leaveType) %></span>
                                </liferay-ui:search-container-column-text>

                                <liferay-ui:search-container-column-text name="Duration">
                                    <div><strong><%= formattedFromDate %></strong> to <strong><%= formattedToDate %></strong></div>
                                    <div style="font-size: 0.75rem; color: #64748b;"><%= String.valueOf(numberOfDays) %> Days</div>
                                </liferay-ui:search-container-column-text>

                                <liferay-ui:search-container-column-text name="reason">
                                    <div title="<%= HtmlUtil.escapeAttribute(leaveReason) %>" style="max-width: 150px; overflow: hidden; text-overflow: ellipsis; white-space: nowrap;">
                                        <%= HtmlUtil.escape(leaveReason) %>
                                    </div>
                                </liferay-ui:search-container-column-text>

                                <liferay-ui:search-container-column-text name="status">
                                    <!-- This span class 'leave-status-text' is used by the JS filter -->
                                    <span class="leave-status <%= statusCssClass %> leave-status-text"><%= HtmlUtil.escape(status) %></span>
                                </liferay-ui:search-container-column-text>

                            </liferay-ui:search-container-row>

                            <liferay-ui:search-iterator displayStyle="list" markupView="lexicon" />
                        </liferay-ui:search-container>
                        
                    </c:when>
                    <c:otherwise>
                        <div style="margin-top: 1.5rem; background: #fef3c7; color: #b45309; border: 1px solid #fde68a; padding: 1rem; border-radius: 12px; display: flex; align-items: center; gap: 0.75rem;">
                            <i class="fa-solid fa-lock" style="font-size: 1.5rem;"></i>
                            <span style="font-weight: 600; font-size: 0.9rem;">
                                <liferay-ui:message key="please-sign-in-to-view-leave-requests" />
                            </span>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
            
            <!-- Information Banner -->
            <div style="background: #f1f5f9; padding: 1rem 1.5rem; border-top: 1px solid #e2e8f0; font-size: 0.85rem; color: #64748b; display: flex; align-items: center; gap: 0.5rem;">
                <i class="fa-solid fa-circle-info" style="color: #0056b3;"></i> 
                <span><liferay-ui:message key="leave-application-policy-notice" /></span>
            </div>
        </div>

    </div>
</div>

<aui:script>
    /* Table Filtering Logic */
    window.filterLeaveTable = function() {
        var filterValue = document.getElementById("leaveTableFilter").value.toUpperCase();
        var tableContainer = document.getElementById("leaveHistoryTableContainer");
        if (!tableContainer) return;
        
        var rows = tableContainer.querySelectorAll("table tbody tr");
        
        rows.forEach(function(row) {
            // Skip the header row if somehow caught
            if (row.querySelector("th")) return;
            
            // If ALL is selected, show row
            if (filterValue === "ALL") {
                row.style.display = "";
                return;
            }
            
            // Extract status from the specific span class we added
            var statusSpan = row.querySelector(".leave-status-text");
            if (statusSpan) {
                var statusText = statusSpan.textContent.trim().toUpperCase();
                if (statusText === filterValue) {
                    row.style.display = "";
                } else {
                    row.style.display = "none";
                }
            }
        });
    };

    /* Date Calculation Logic */
    const fromDateInput = document.getElementById('<portlet:namespace />fromDate');
    const toDateInput = document.getElementById('<portlet:namespace />toDate');
    const totalDaysElement = document.getElementById('<portlet:namespace />totalDays');

    function updateTotalDays() {
        if (!fromDateInput || !toDateInput || !totalDaysElement) {
            return;
        }

        if (!fromDateInput.value || !toDateInput.value) {
            totalDaysElement.textContent = '0';
            return;
        }

        const fromDate = new Date(fromDateInput.value + 'T00:00:00');
        const toDate = new Date(toDateInput.value + 'T00:00:00');

        if (Number.isNaN(fromDate.getTime()) || Number.isNaN(toDate.getTime()) || toDate < fromDate) {
            totalDaysElement.textContent = '0';
            return;
        }

        const millisecondsPerDay = 24 * 60 * 60 * 1000;
        const totalDays = Math.floor((toDate.getTime() - fromDate.getTime()) / millisecondsPerDay) + 1;

        totalDaysElement.textContent = String(totalDays);
    }

    function resetTotalDays() {
        window.setTimeout(() => {
            if (totalDaysElement) {
                totalDaysElement.textContent = '0';
            }
            // Also reset filter if form resets
            const filterDrop = document.getElementById("leaveTableFilter");
            if(filterDrop) { filterDrop.value = "ALL"; window.filterLeaveTable(); }
        }, 0);
    }

    if (fromDateInput && toDateInput && totalDaysElement) {
        fromDateInput.addEventListener('change', updateTotalDays);
        toDateInput.addEventListener('change', updateTotalDays);

        const leaveForm = document.getElementById('<portlet:namespace />leaveForm');
        if (leaveForm) {
            leaveForm.addEventListener('reset', resetTotalDays);
        }

        updateTotalDays();
    }
</aui:script>