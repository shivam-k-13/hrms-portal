package com.hrms.employee.web.action;

import com.hrms.employee.model.Employee;
import com.hrms.employee.service.EmployeeLocalService;
import com.hrms.employee.web.constants.EmployeeWebPortletKeys;

import com.liferay.counter.kernel.service.CounterLocalService;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.Date;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import jakarta.portlet.ActionRequest;
import jakarta.portlet.ActionResponse;

@Component(
	property = {
		"jakarta.portlet.name=" + EmployeeWebPortletKeys.EMPLOYEEWEB,
		"mvc.command.name=/employee/add"
	},
	service = MVCActionCommand.class
)
public class AddEmployeeMVCActionCommand extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest,
			ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay =
			(ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

		if (!hasManagePermission(themeDisplay)) {
			System.out.println("ADD EMPLOYEE BLOCKED - USER HAS NO PERMISSION");
			return;
		}

		long employeeId = _counterLocalService.increment(
			Employee.class.getName());

		String employeeCode = ParamUtil.getString(actionRequest, "employeeCode");
		String firstName = ParamUtil.getString(actionRequest, "firstName");
		String lastName = ParamUtil.getString(actionRequest, "lastName");
		String email = ParamUtil.getString(actionRequest, "email");
		String phoneNumber = ParamUtil.getString(actionRequest, "phoneNumber");
		String department = ParamUtil.getString(actionRequest, "department");
		String designation = ParamUtil.getString(actionRequest, "designation");
		String status = ParamUtil.getString(actionRequest, "status");

		Employee employee =
			_employeeLocalService.createEmployee(employeeId);

		employee.setEmployeeCode(employeeCode);
		employee.setFirstName(firstName);
		employee.setLastName(lastName);
		employee.setEmail(email);
		employee.setPhoneNumber(phoneNumber);
		employee.setDepartment(department);
		employee.setDesignation(designation);
		employee.setStatus(status);

		employee.setCompanyId(themeDisplay.getCompanyId());
		employee.setGroupId(themeDisplay.getScopeGroupId());
		employee.setUserId(themeDisplay.getUserId());
		employee.setUserName(themeDisplay.getUser().getFullName());

		Date now = new Date();

		employee.setCreateDate(now);
		employee.setModifiedDate(now);

		_employeeLocalService.addEmployee(employee);

		System.out.println("EMPLOYEE SAVED SUCCESSFULLY");
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
	private CounterLocalService _counterLocalService;

	@Reference
	private RoleLocalService _roleLocalService;

}