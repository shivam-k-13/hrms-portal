package com.hrms.employee.web.action;

import com.hrms.employee.model.Designation;
import com.hrms.employee.service.DesignationLocalService;
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
		"mvc.command.name=/designation/update"
	},
	service = MVCActionCommand.class
)
public class UpdateDesignationMVCActionCommand extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest,
			ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay =
			(ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

		if (!hasManagePermission(themeDisplay)) {
			System.out.println("UPDATE DESIGNATION BLOCKED - USER HAS NO PERMISSION");
			return;
		}

		long designationId = ParamUtil.getLong(actionRequest, "designationId");

		Designation designation =
			_designationLocalService.getDesignation(designationId);

		designation.setDesignationCode(
			ParamUtil.getString(actionRequest, "designationCode"));

		designation.setDesignationName(
			ParamUtil.getString(actionRequest, "designationName"));

		designation.setDescription(
			ParamUtil.getString(actionRequest, "description"));

		designation.setStatus(
			ParamUtil.getString(actionRequest, "status"));

		designation.setModifiedDate(new Date());

		_designationLocalService.updateDesignation(designation);

		System.out.println("DESIGNATION UPDATED : " + designationId);
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
	private DesignationLocalService _designationLocalService;

	@Reference
	private RoleLocalService _roleLocalService;

}