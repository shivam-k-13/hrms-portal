package leave.portlet.action;

import com.hrms.leave.service.LeaveRequestLocalService;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import jakarta.portlet.ActionRequest;
import jakarta.portlet.ActionResponse;

import leave.web.constants.LeaveWebPortletKeys;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	immediate = true,
	property = {
		"jakarta.portlet.name=" + LeaveWebPortletKeys.LEAVE_WEB,
		"mvc.command.name=/leave/reject"
	},
	service = MVCActionCommand.class
)
public class RejectLeaveMVCActionCommand implements MVCActionCommand {

	@Override
	public boolean processAction(
		ActionRequest actionRequest, ActionResponse actionResponse) {

		try {
			long leaveRequestId = ParamUtil.getLong(
				actionRequest, "leaveRequestId");

			String approverComments = ParamUtil.getString(
				actionRequest, "approverComments");

			if (leaveRequestId <= 0) {
				throw new IllegalArgumentException(
					"Invalid leave request ID");
			}

			if (Validator.isNull(approverComments)) {
				SessionErrors.add(
					actionRequest, "rejection-comment-required");

				return false;
			}

			if (approverComments.trim().length() > 500) {
				throw new IllegalArgumentException(
					"Rejection comment cannot exceed 500 characters");
			}

			ThemeDisplay themeDisplay =
				(ThemeDisplay)actionRequest.getAttribute(
					WebKeys.THEME_DISPLAY);

			_leaveRequestLocalService.rejectLeave(
				leaveRequestId, themeDisplay.getUserId(),
				approverComments.trim());

			SessionMessages.add(
				actionRequest, "leave-request-rejected");
		}
		catch (IllegalStateException exception) {
			SessionErrors.add(
				actionRequest, "invalid-leave-status");
		}
		catch (Exception exception) {
			SessionErrors.add(
				actionRequest, "leave-request-error");
		}

		return false;
	}

	@Reference
	private LeaveRequestLocalService _leaveRequestLocalService;

}