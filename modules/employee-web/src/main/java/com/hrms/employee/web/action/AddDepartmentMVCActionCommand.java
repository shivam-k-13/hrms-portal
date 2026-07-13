package com.hrms.employee.web.action;

import com.hrms.employee.model.Department;
import com.hrms.employee.service.DepartmentLocalService;
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
		"mvc.command.name=/department/add"
	},
	service = MVCActionCommand.class
)
public class AddDepartmentMVCActionCommand extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest,
			ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay =
			(ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

		if (!hasManagePermission(themeDisplay)) {
			System.out.println("ADD DEPARTMENT BLOCKED - USER HAS NO PERMISSION");
			return;
		}

		long departmentId = _counterLocalService.increment(
			Department.class.getName());

		String departmentCode = ParamUtil.getString(
			actionRequest, "departmentCode");

		String departmentName = ParamUtil.getString(
			actionRequest, "departmentName");

		String description = ParamUtil.getString(
			actionRequest, "description");

		String status = ParamUtil.getString(
			actionRequest, "status");

		Department department =
			_departmentLocalService.createDepartment(departmentId);

		department.setDepartmentCode(departmentCode);
		department.setDepartmentName(departmentName);
		department.setDescription(description);
		department.setStatus(status);

		department.setGroupId(themeDisplay.getScopeGroupId());
		department.setCompanyId(themeDisplay.getCompanyId());
		department.setUserId(themeDisplay.getUserId());
		department.setUserName(themeDisplay.getUser().getFullName());

		Date now = new Date();

		department.setCreateDate(now);
		department.setModifiedDate(now);

		_departmentLocalService.addDepartment(department);

		System.out.println("DEPARTMENT SAVED SUCCESSFULLY");
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
	private DepartmentLocalService _departmentLocalService;

	@Reference
	private CounterLocalService _counterLocalService;

	@Reference
	private RoleLocalService _roleLocalService;

}