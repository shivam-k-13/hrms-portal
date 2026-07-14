package com.hrms.employee.web.action;

import com.hrms.employee.model.Employee;
import com.hrms.employee.service.EmployeeLocalService;
import com.hrms.employee.web.constants.EmployeeWebPortletKeys;

import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
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
		"mvc.command.name=/employee/update"
	},
	service = MVCActionCommand.class
)
public class UpdateEmployeeMVCActionCommand extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest,
			ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay =
			(ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

		if (!hasManagePermission(themeDisplay)) {
			System.out.println("UPDATE EMPLOYEE BLOCKED - USER HAS NO PERMISSION");
			return;
		}

		long employeeId = ParamUtil.getLong(actionRequest, "employeeId");

		Employee employee = _employeeLocalService.getEmployee(employeeId);

		employee.setEmployeeCode(
			ParamUtil.getString(actionRequest, "employeeCode"));

		employee.setFirstName(
			ParamUtil.getString(actionRequest, "firstName"));

		employee.setLastName(
			ParamUtil.getString(actionRequest, "lastName"));

		String email = ParamUtil.getString(actionRequest, "email");
		employee.setEmail(email);

		employee.setPhoneNumber(
			ParamUtil.getString(actionRequest, "phoneNumber"));

		employee.setDepartment(
			ParamUtil.getString(actionRequest, "department"));

		employee.setDesignation(
			ParamUtil.getString(actionRequest, "designation"));

		employee.setStatus(
			ParamUtil.getString(actionRequest, "status"));

		// --- DYNAMICALLY RE-ALIGN USER ID ON UPDATE ---
		long targetUserId = 0;
		String targetUserName = "";

		try {
			User targetUser = _userLocalService.getUserByEmailAddress(themeDisplay.getCompanyId(), email);
			targetUserId = targetUser.getUserId();
			targetUserName = targetUser.getFullName();
		} catch (Exception e) {
			System.out.println("WARNING ON UPDATE: No Liferay login account found for email: " + email);
		}

		employee.setUserId(targetUserId);
		employee.setUserName(targetUserName);
		// ----------------------------------------------

		_employeeLocalService.updateEmployee(employee);

		System.out.println("EMPLOYEE UPDATED : " + employeeId);
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

	@Reference
	private UserLocalService _userLocalService;

}