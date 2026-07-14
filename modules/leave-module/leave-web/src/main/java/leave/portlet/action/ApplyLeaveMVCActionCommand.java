package leave.portlet.action;

import com.hrms.leave.service.LeaveRequestLocalService;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import jakarta.portlet.ActionRequest;
import jakarta.portlet.ActionResponse;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import leave.web.constants.LeaveWebPortletKeys;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
    immediate = true,
    property = {
        "jakarta.portlet.name=" + LeaveWebPortletKeys.LEAVE_WEB,
        "mvc.command.name=/leave/apply"
    },
    service = MVCActionCommand.class
)
public class ApplyLeaveMVCActionCommand implements MVCActionCommand {

    @Override
    public boolean processAction(
        ActionRequest actionRequest, ActionResponse actionResponse) {

        try {
            ThemeDisplay themeDisplay =
                (ThemeDisplay)actionRequest.getAttribute(
                    WebKeys.THEME_DISPLAY);

            if ((themeDisplay == null) || !themeDisplay.isSignedIn()) {
                SessionErrors.add(
                    actionRequest, "authentication-required");

                return false;
            }

            long employeeId = themeDisplay.getUserId();

            String leaveType = ParamUtil.getString(
                actionRequest, "leaveType");

            String fromDateString = ParamUtil.getString(
                actionRequest, "fromDate");

            String toDateString = ParamUtil.getString(
                actionRequest, "toDate");

            String reason = ParamUtil.getString(
                actionRequest, "reason");

            _validateBasicFields(
                leaveType, fromDateString, toDateString, reason);

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
                "yyyy-MM-dd");

            simpleDateFormat.setLenient(false);

            Date fromDate = simpleDateFormat.parse(fromDateString);
            Date toDate = simpleDateFormat.parse(toDateString);

            if (toDate.before(fromDate)) {
                throw new IllegalArgumentException(
                    "To date cannot be earlier than from date");
            }

            ServiceContext serviceContext =
                ServiceContextFactory.getInstance(actionRequest);

            _leaveRequestLocalService.applyLeave(
                employeeId, leaveType, fromDate, toDate, reason.trim(),
                serviceContext);

            SessionMessages.add(
                actionRequest, "leave-request-submitted");
        }
        catch (IllegalArgumentException exception) {
            SessionErrors.add(
                actionRequest, "invalid-leave-request");
        }
        catch (Exception exception) {
            SessionErrors.add(
                actionRequest, "leave-request-error");
        }

        return false;
    }

    private void _validateBasicFields(
        String leaveType, String fromDateString, String toDateString,
        String reason) {

        if (!_allowedLeaveTypes.contains(leaveType)) {
            throw new IllegalArgumentException("Invalid leave type");
        }

        if (Validator.isNull(fromDateString) ||
            Validator.isNull(toDateString)) {

            throw new IllegalArgumentException(
                "From date and to date are required");
        }

        if (Validator.isNull(reason)) {
            throw new IllegalArgumentException("Reason is required");
        }

        if (reason.trim().length() > 500) {
            throw new IllegalArgumentException(
                "Reason cannot exceed 500 characters");
        }
    }

    private static final Set<String> _allowedLeaveTypes = Set.of(
        "CASUAL", "EARNED", "LOP", "SICK");

    @Reference
    private LeaveRequestLocalService _leaveRequestLocalService;

}