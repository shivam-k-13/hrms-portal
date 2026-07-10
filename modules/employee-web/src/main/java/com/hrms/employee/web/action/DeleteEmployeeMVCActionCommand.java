package com.hrms.employee.web.action;

import com.hrms.employee.service.EmployeeLocalService;
import com.hrms.employee.web.constants.EmployeeWebPortletKeys;

import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import jakarta.portlet.ActionRequest;
import jakarta.portlet.ActionResponse;

@Component(
	property = {
		"jakarta.portlet.name=" + EmployeeWebPortletKeys.EMPLOYEEWEB,
		"mvc.command.name=/employee/delete"
	},
	service = MVCActionCommand.class
)
public class DeleteEmployeeMVCActionCommand extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest,
			ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay =
			(ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

		if (!hasManagePermission(themeDisplay)) {
			System.out.println("DELETE EMPLOYEE BLOCKED - USER HAS NO PERMISSION");
			return;
		}

		long employeeId = ParamUtil.getLong(
			actionRequest, "employeeId");

		_employeeLocalService.deleteEmployee(employeeId);

		System.out.println(
			"EMPLOYEE DELETED : " + employeeId);
	}

	private boolean hasManagePermission(ThemeDisplay themeDisplay) {
		return hasRole(themeDisplay, "HRMS Admin") ||
			hasRole(themeDisplay, "HRMS HR");
	}

	private boolean hasRole(
		ThemeDisplay themeDisplay, String roleName) {

		try {
			Role role = _roleLocalService.getRole(
				themeDisplay.getCompanyId(), roleName);

			return _roleLocalService.hasUserRole(
				themeDisplay.getUserId(), role.getRoleId());
		}
		catch (Exception exception) {
			return false;
		}
	}

	@Reference
	private EmployeeLocalService _employeeLocalService;

	@Reference
	private RoleLocalService _roleLocalService;

}