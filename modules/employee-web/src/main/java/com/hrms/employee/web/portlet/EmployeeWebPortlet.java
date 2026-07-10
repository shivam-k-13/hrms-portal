package com.hrms.employee.web.portlet;

import com.hrms.employee.model.Employee;
import com.hrms.employee.service.EmployeeLocalService;
import com.hrms.employee.web.constants.EmployeeWebPortletKeys;

import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;
import java.util.List;

import jakarta.portlet.Portlet;
import jakarta.portlet.PortletException;
import jakarta.portlet.RenderRequest;
import jakarta.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"jakarta.portlet.display-name=EmployeeWeb",
		"jakarta.portlet.init-param.template-path=/",
		"jakarta.portlet.init-param.view-template=/view.jsp",
		"jakarta.portlet.name=" + EmployeeWebPortletKeys.EMPLOYEEWEB,
		"jakarta.portlet.resource-bundle=content.Language",
		"jakarta.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class EmployeeWebPortlet extends MVCPortlet {

	@Override
	public void render(
			RenderRequest renderRequest,
			RenderResponse renderResponse)
		throws IOException, PortletException {

		ThemeDisplay themeDisplay =
			(ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);

		List<Employee> employees =
			_employeeLocalService.getEmployees(-1, -1);

		boolean canManageEmployees = hasRole(
			themeDisplay, "HRMS Admin") ||
			hasRole(themeDisplay, "HRMS HR");

		renderRequest.setAttribute("employees", employees);
		renderRequest.setAttribute(
			"canManageEmployees", canManageEmployees);

		super.render(renderRequest, renderResponse);
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