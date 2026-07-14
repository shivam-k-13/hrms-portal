package com.hrms.employee.web.render;

import com.hrms.employee.model.Department;
import com.hrms.employee.model.Designation;
import com.hrms.employee.model.Employee;
import com.hrms.employee.service.DepartmentLocalService;
import com.hrms.employee.service.DesignationLocalService;
import com.hrms.employee.service.EmployeeLocalService;
import com.hrms.employee.web.constants.EmployeeWebPortletKeys;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.util.ParamUtil;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import jakarta.portlet.RenderRequest;
import jakarta.portlet.RenderResponse;

@Component(
	property = {
		"jakarta.portlet.name=" + EmployeeWebPortletKeys.EMPLOYEEWEB,
		"mvc.command.name=/employee/edit"
	},
	service = MVCRenderCommand.class
)
public class EditEmployeeMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(
		RenderRequest renderRequest,
		RenderResponse renderResponse) {

		long employeeId = ParamUtil.getLong(
			renderRequest, "employeeId");

		Employee employee =
			_employeeLocalService.fetchEmployee(employeeId);

		List<Department> departments =
			_departmentLocalService.getDepartments(-1, -1);

		List<Designation> designations =
			_designationLocalService.getDesignations(-1, -1);

		renderRequest.setAttribute("employee", employee);
		renderRequest.setAttribute("departments", departments);
		renderRequest.setAttribute("designations", designations);

		return "/edit_employee.jsp";
	}

	@Reference
	private EmployeeLocalService _employeeLocalService;

	@Reference
	private DepartmentLocalService _departmentLocalService;

	@Reference
	private DesignationLocalService _designationLocalService;

}