package com.hrms.dashboard.web.portlet;

import com.hrms.dashboard.web.constants.DashboardWebPortletKeys;
import com.hrms.employee.service.EmployeeLocalService;

import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;

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
		"jakarta.portlet.display-name=DashboardWeb",
		"jakarta.portlet.init-param.template-path=/",
		"jakarta.portlet.init-param.view-template=/view.jsp",
		"jakarta.portlet.name=" + DashboardWebPortletKeys.DASHBOARDWEB,
		"jakarta.portlet.resource-bundle=content.Language",
		"jakarta.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class DashboardWebPortlet extends MVCPortlet {

	@Override
	public void render(
			RenderRequest renderRequest,
			RenderResponse renderResponse)
		throws IOException, PortletException {

		ThemeDisplay themeDisplay =
			(ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);

		Layout layout = themeDisplay.getLayout();

		String currentPageFriendlyURL = layout.getFriendlyURL();

		int totalEmployees = _employeeLocalService.getEmployeesCount();

		boolean isAdmin = hasRole(themeDisplay, "HRMS Admin");
		boolean isHR = hasRole(themeDisplay, "HRMS HR");
		boolean isManager = hasRole(themeDisplay, "HRMS Manager");
		boolean isEmployee = hasRole(themeDisplay, "HRMS Employee");

		renderRequest.setAttribute("currentPageFriendlyURL", currentPageFriendlyURL);
		renderRequest.setAttribute("totalEmployees", totalEmployees);

		renderRequest.setAttribute("isAdmin", isAdmin);
		renderRequest.setAttribute("isHR", isHR);
		renderRequest.setAttribute("isManager", isManager);
		renderRequest.setAttribute("isEmployee", isEmployee);

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