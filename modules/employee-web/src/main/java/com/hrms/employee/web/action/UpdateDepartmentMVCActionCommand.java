package com.hrms.employee.web.action;

import com.hrms.employee.model.Department;
import com.hrms.employee.service.DepartmentLocalService;
import com.hrms.employee.web.constants.EmployeeWebPortletKeys;

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
		"mvc.command.name=/department/update"
	},
	service = MVCActionCommand.class
)
public class UpdateDepartmentMVCActionCommand extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest,
			ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay =
			(ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

		if (!hasManagePermission(themeDisplay)) {
			System.out.println("UPDATE DEPARTMENT BLOCKED - USER HAS NO PERMISSION");
			return;
		}

		long departmentId = ParamUtil.getLong(actionRequest, "departmentId");

		Department department =
			_departmentLocalService.getDepartment(departmentId);

		department.setDepartmentCode(
			ParamUtil.getString(actionRequest, "departmentCode"));

		department.setDepartmentName(
			ParamUtil.getString(actionRequest, "departmentName"));

		department.setDescription(
			ParamUtil.getString(actionRequest, "description"));

		department.setStatus(
			ParamUtil.getString(actionRequest, "status"));

		department.setModifiedDate(new Date());

		_departmentLocalService.updateDepartment(department);

		System.out.println("DEPARTMENT UPDATED : " + departmentId);
	}

	private boolean hasManagePermission(ThemeDisplay themeDisplay) {
		return hasRole(themeDisplay, "HRMS Admin") ||
			hasRole(themeDisplay, "HRMS HR");
	}

	private boolean hasRole(ThemeDisplay themeDisplay, String roleName) {
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
	private RoleLocalService _roleLocalService;

}