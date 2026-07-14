package com.hrms.employee.web.render;

import com.hrms.employee.model.Department;
import com.hrms.employee.service.DepartmentLocalService;
import com.hrms.employee.web.constants.EmployeeWebPortletKeys;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.util.ParamUtil;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import jakarta.portlet.RenderRequest;
import jakarta.portlet.RenderResponse;

@Component(
	property = {
		"jakarta.portlet.name=" + EmployeeWebPortletKeys.EMPLOYEEWEB,
		"mvc.command.name=/department/edit"
	},
	service = MVCRenderCommand.class
)
public class EditDepartmentMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(
		RenderRequest renderRequest,
		RenderResponse renderResponse) {

		long departmentId = ParamUtil.getLong(
			renderRequest, "departmentId");

		Department department =
			_departmentLocalService.fetchDepartment(departmentId);

		renderRequest.setAttribute("department", department);

		return "/edit_department.jsp";
	}

	@Reference
	private DepartmentLocalService _departmentLocalService;

}