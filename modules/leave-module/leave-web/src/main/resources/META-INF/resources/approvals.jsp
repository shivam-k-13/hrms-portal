<%@ include file="/init.jsp" %>

<%-- Core Liferay Imports to resolve ThemeDisplay safely --%>
<%@ page import="com.liferay.portal.kernel.theme.ThemeDisplay" %>
<%@ page import="com.liferay.portal.kernel.util.WebKeys" %>
<%@ page import="com.hrms.leave.model.LeaveRequest" %>
<%@ page import="com.hrms.leave.service.LeaveRequestLocalServiceUtil" %>
<%@ page import="com.liferay.portal.kernel.util.HtmlUtil" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>

<%
    ThemeDisplay safeThemeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
    DateFormat leaveDateFormat = new SimpleDateFormat("dd-MMM-yyyy", safeThemeDisplay.getLocale());
    leaveDateFormat.setTimeZone(safeThemeDisplay.getTimeZone());
%>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" />
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700;800;900&display=swap" />

<style type="text/css">
    .hrms-approvals-wrapper { background: #f8fafc; font-family: "Inter", sans-serif; font-size: 15px; padding: 2rem; min-height: calc(100vh - 80px); }
    .page-header { background: linear-gradient(135deg, #0056b3 0%, #0088cc 100%); padding: 2rem 2.5rem; border-radius: 20px; color: white; margin-bottom: 2rem; box-shadow: 0 10px 25px rgba(0, 86, 179, 0.15); display: flex; justify-content: space-between; align-items: center; }
    .btn-back-inline { display: inline-flex; align-items: center; gap: 0.5rem; color: white; text-decoration: none; font-weight: 600; font-size: 0.85rem; background: rgba(255, 255, 255, 0.15); padding: 0.4rem 1rem; border-radius: 999px; border: 1px solid rgba(255, 255, 255, 0.2); transition: 0.2s; }
    .btn-back-inline:hover { background: rgba(255, 255, 255, 0.25); transform: translateX(-4px); color: white; }
    .page-header h2 { font-size: 1.8rem; font-weight: 900; margin: 0 0 0.25rem; color: white; }
    .page-header p { margin: 0; font-size: 0.95rem; color: #eaf4fc; font-weight: 500; }
    .header-icon { font-size: 3rem; opacity: 0.9; }
    .hrms-card { background: #ffffff; border: 1px solid #e2e8f0; border-radius: 20px; box-shadow: 0 10px 30px rgba(0, 86, 179, 0.06); overflow: hidden; }
    .card-header-accent { background: #f1f5f9; padding: 1.25rem 1.5rem; border-bottom: 1px solid #e2e8f0; font-weight: 800; color: #0f172a; font-size: 1.1rem; display: flex; justify-content: space-between; align-items: center; }
    .btn-approve, .btn-reject { display: inline-flex; align-items: center; gap: 0.4rem; padding: 0.4rem 0.8rem; border-radius: 8px; font-size: 0.8rem; font-weight: 700; text-decoration: none; transition: 0.2s; border: none; cursor: pointer; }
    .btn-approve { background: #ecfdf5; color: #047857; border: 1px solid #a7f3d0; }
    .btn-approve:hover { background: #10b981; color: white; box-shadow: 0 4px 10px rgba(16, 185, 129, 0.2); }
    .btn-reject { background: #fef2f2; color: #b91c1c; border: 1px solid #fecaca; }
    .btn-reject:hover { background: #ef4444; color: white; box-shadow: 0 4px 10px rgba(239, 68, 68, 0.2); }
    .table-container { padding: 0 1.5rem 1.5rem; overflow-x: auto;}
    .table-container table { width: 100%; border-collapse: collapse; margin-top: 1rem; }
    .table-container th { background: #f1f5f9; padding: 1rem; color: #64748b; font-weight: 700; font-size: 0.85rem; text-align: left; border-bottom: 2px solid #e2e8f0; }
    .table-container td { padding: 1rem; color: #0f172a; font-weight: 600; font-size: 0.85rem; border-bottom: 1px solid #f1f5f9; vertical-align: middle;}
    .leave-type-badge { font-weight: 700; color: #0056b3; background: #eaf4fc; padding: 0.35rem 0.65rem; border-radius: 6px; font-size: 0.75rem;}
</style>

<div class="hrms-approvals-wrapper">
    <header class="page-header">
        <div>
            <div style="margin-bottom: 1rem;">
                <a href="/web/hrms/dashboard-router" class="btn-back-inline"><i class="fa-solid fa-arrow-left"></i> Dashboard</a>
            </div>
            <h2>Pending Leave Approvals</h2>
            <p>Review and process employee time-off requests.</p>
        </div>
        <div class="header-icon"><i class="fa-solid fa-calendar-check"></i></div>
    </header>

    <div class="hrms-card" style="border-top: 5px solid #f59e0b;">
        <div class="card-header-accent">
            <div><i class="fa-solid fa-list-check" style="color: #f59e0b; margin-right: 0.5rem;"></i> Action Required</div>
        </div>

        <div class="table-container">
            <c:choose>
                <c:when test="<%= safeThemeDisplay.isSignedIn() %>">
                    <% 
                        List<LeaveRequest> allRequests = LeaveRequestLocalServiceUtil.getLeaveRequests(-1, -1);
                        List<LeaveRequest> pendingRequests = new ArrayList<LeaveRequest>();
                        
                        for (LeaveRequest req : allRequests) {
                            String reqStatus = req.getStatus();
                            if ("PENDING".equals(reqStatus) || "PENDING_MANAGER".equals(reqStatus) || "PENDING_HR".equals(reqStatus)) {
                                pendingRequests.add(req);
                            }
                        }
                        
                        int totalRequests = pendingRequests.size(); 
                    %>
                    
                    <liferay-ui:search-container delta="10" emptyResultsMessage="No pending leave requests at this time." total="<%= totalRequests %>">
                        <%
                            int startIndex = searchContainer.getStart();
                            int endIndex = searchContainer.getEnd();
                            if (endIndex > totalRequests) {
                                endIndex = totalRequests;
                            }
                            List<LeaveRequest> paginatedRequests = pendingRequests.subList(startIndex, endIndex);
                        %>
                        <liferay-ui:search-container-results results="<%= paginatedRequests %>" />

                        <liferay-ui:search-container-row className="com.hrms.leave.model.LeaveRequest" keyProperty="leaveRequestId" modelVar="leaveRequestRow">
                            
                            <% LeaveRequest leaveRequest = (LeaveRequest) pageContext.getAttribute("leaveRequestRow"); %>

                            <%
                                String formattedFromDate = leaveRequest.getFromDate() != null ? leaveDateFormat.format(leaveRequest.getFromDate()) : "-";
                                String formattedToDate = leaveRequest.getToDate() != null ? leaveDateFormat.format(leaveRequest.getToDate()) : "-";
                                long numberOfDays = 0;
                                if (leaveRequest.getFromDate() != null && leaveRequest.getToDate() != null && !leaveRequest.getToDate().before(leaveRequest.getFromDate())) {
                                    long difference = leaveRequest.getToDate().getTime() - leaveRequest.getFromDate().getTime();
                                    numberOfDays = (difference / (24L * 60L * 60L * 1000L)) + 1;
                                }
                            %>

                            <liferay-ui:search-container-column-text name="Employee ID">
                                <div style="font-weight: 800; color: #0f172a; font-size: 0.95rem;">
                                    <i class="fa-regular fa-id-badge text-muted mr-1"></i> <%= leaveRequest.getEmployeeId() %>
                                </div>
                            </liferay-ui:search-container-column-text>

                            <liferay-ui:search-container-column-text name="Leave Type">
                                <span class="leave-type-badge"><%= HtmlUtil.escape(leaveRequest.getLeaveType()) %></span>
                            </liferay-ui:search-container-column-text>

                            <liferay-ui:search-container-column-text name="Duration">
                                <div><strong><%= formattedFromDate %></strong> to <strong><%= formattedToDate %></strong></div>
                                <div style="font-size: 0.75rem; color: #64748b; margin-top: 0.2rem;">
                                    <i class="fa-regular fa-clock"></i> <%= String.valueOf(numberOfDays) %> Days
                                </div>
                            </liferay-ui:search-container-column-text>

                            <liferay-ui:search-container-column-text name="Reason">
                                <div title="<%= HtmlUtil.escapeAttribute(leaveRequest.getReason()) %>" style="max-width: 250px; overflow: hidden; text-overflow: ellipsis; white-space: nowrap; color: #475569;">
                                    <%= HtmlUtil.escape(leaveRequest.getReason()) %>
                                </div>
                            </liferay-ui:search-container-column-text>

                            <liferay-ui:search-container-column-text name="Actions">
                                <portlet:actionURL name="/leave/approve" var="approveURL">
                                    <portlet:param name="leaveRequestId" value="<%= String.valueOf(leaveRequest.getLeaveRequestId()) %>" />
                                </portlet:actionURL>
                                <portlet:actionURL name="/leave/reject" var="rejectURL">
                                    <portlet:param name="leaveRequestId" value="<%= String.valueOf(leaveRequest.getLeaveRequestId()) %>" />
                                </portlet:actionURL>

                                <div style="display: flex; gap: 0.5rem;">
                                    <a href="${approveURL}" class="btn-approve"><i class="fa-solid fa-check"></i> Approve</a>
                                    <a href="${rejectURL}" class="btn-reject"><i class="fa-solid fa-xmark"></i> Reject</a>
                                </div>
                            </liferay-ui:search-container-column-text>

                        </liferay-ui:search-container-row>

                        <liferay-ui:search-iterator displayStyle="list" markupView="lexicon" />
                    </liferay-ui:search-container>
                </c:when>
                <c:otherwise>
                    <div style="margin-top: 1.5rem; background: #fef3c7; color: #b45309; border: 1px solid #fde68a; padding: 1rem; border-radius: 12px; display: flex; align-items: center; gap: 0.75rem;">
                        <i class="fa-solid fa-lock" style="font-size: 1.5rem;"></i>
                        <span style="font-weight: 600; font-size: 0.9rem;">Please sign in to process leaves.</span>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>