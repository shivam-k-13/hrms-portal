package com.hrms.employee.profile.web.portlet;

import com.hrms.employee.model.Employee;
import com.hrms.employee.service.EmployeeLocalService;
import com.hrms.employee.profile.web.constants.EmployeeProfileWebPortletKeys;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
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
		"jakarta.portlet.display-name=EmployeeProfileWeb",
		"jakarta.portlet.init-param.template-path=/",
		"jakarta.portlet.init-param.view-template=/view.jsp",
		"jakarta.portlet.name=" + EmployeeProfileWebPortletKeys.EMPLOYEEPROFILEWEB,
		"jakarta.portlet.resource-bundle=content.Language",
		"jakarta.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class EmployeeProfileWebPortlet extends MVCPortlet {

	@Override
	public void render(
			RenderRequest renderRequest,
			RenderResponse renderResponse)
		throws IOException, PortletException {

		ThemeDisplay themeDisplay =
			(ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);

		String loggedInUserEmail = themeDisplay.getUser().getEmailAddress();

		Employee loggedInEmployee = null;

		List<Employee> employees =
			_employeeLocalService.getEmployees(-1, -1);

		for (Employee employee : employees) {
			if (loggedInUserEmail.equalsIgnoreCase(employee.getEmail())) {
				loggedInEmployee = employee;

				break;
			}
		}

		renderRequest.setAttribute("employee", loggedInEmployee);
		renderRequest.setAttribute("loggedInUserEmail", loggedInUserEmail);

		super.render(renderRequest, renderResponse);
	}

	@Reference
	private EmployeeLocalService _employeeLocalService;

}