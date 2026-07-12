package com.hrms.employee.web.render;

import com.hrms.employee.model.Employee;
import com.hrms.employee.service.EmployeeLocalService;
import com.hrms.employee.web.constants.EmployeeWebPortletKeys;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.util.ParamUtil;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import jakarta.portlet.RenderRequest;
import jakarta.portlet.RenderResponse;

@Component(property = { "jakarta.portlet.name=" + EmployeeWebPortletKeys.EMPLOYEEWEB,
		"mvc.command.name=/employee/view" }, service = MVCRenderCommand.class)
public class ViewEmployeeMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) {

		long employeeId = ParamUtil.getLong(renderRequest, "employeeId");

		Employee employee = _employeeLocalService.fetchEmployee(employeeId);

		renderRequest.setAttribute("employee", employee);

		return "/employee_details.jsp";
	}

	@Reference
	private EmployeeLocalService _employeeLocalService;

}