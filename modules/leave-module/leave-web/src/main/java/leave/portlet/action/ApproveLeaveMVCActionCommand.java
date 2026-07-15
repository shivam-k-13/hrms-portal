package leave.portlet.action;

import com.hrms.leave.model.LeaveRequest;
import com.hrms.leave.service.LeaveRequestLocalService;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import jakarta.portlet.ActionRequest;
import jakarta.portlet.ActionResponse;

import leave.web.constants.LeaveWebPortletKeys;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import java.util.Date;

@Component(
    immediate = true,
    property = {
        "jakarta.portlet.name=" + LeaveWebPortletKeys.LEAVE_WEB,
        "jakarta.portlet.name=leave_web_approvals_portlet",
        "mvc.command.name=/leave/approve"
    },
    service = MVCActionCommand.class
)
public class ApproveLeaveMVCActionCommand implements MVCActionCommand {

    @Override
    public boolean processAction(ActionRequest actionRequest, ActionResponse actionResponse) {
        try {
            long leaveRequestId = ParamUtil.getLong(actionRequest, "leaveRequestId");
            String approverComments = ParamUtil.getString(actionRequest, "approverComments", "Approved");

            if (leaveRequestId <= 0) {
                throw new IllegalArgumentException("Invalid leave request ID");
            }

            ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
            
            // MULTI-TIER APPROVAL LOGIC
            LeaveRequest leaveRequest = _leaveRequestLocalService.getLeaveRequest(leaveRequestId);
            String currentStatus = leaveRequest.getStatus();
            
            if ("PENDING_MANAGER".equals(currentStatus) || "PENDING".equals(currentStatus)) {
                // Tier 1 Approval -> Push to HR
                leaveRequest.setStatus("PENDING_HR");
            } else if ("PENDING_HR".equals(currentStatus)) {
                // Tier 2 Approval -> Finalize
                leaveRequest.setStatus("APPROVED");
            }
            
            leaveRequest.setApproverUserId(themeDisplay.getUserId());
            leaveRequest.setApproverComments(approverComments);
            leaveRequest.setModifiedDate(new Date());
            
            _leaveRequestLocalService.updateLeaveRequest(leaveRequest);

            SessionMessages.add(actionRequest, "leave-request-approved");
        } catch (Exception exception) {
            SessionErrors.add(actionRequest, "leave-request-error");
        }

        return false;
    }

    @Reference
    private LeaveRequestLocalService _leaveRequestLocalService;
}