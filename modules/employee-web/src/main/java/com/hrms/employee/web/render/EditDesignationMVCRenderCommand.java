package com.hrms.employee.web.render;

import com.hrms.employee.model.Designation;
import com.hrms.employee.service.DesignationLocalService;
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
		"mvc.command.name=/designation/edit"
	},
	service = MVCRenderCommand.class
)
public class EditDesignationMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(
		RenderRequest renderRequest,
		RenderResponse renderResponse) {

		long designationId = ParamUtil.getLong(
			renderRequest, "designationId");

		Designation designation =
			_designationLocalService.fetchDesignation(designationId);

		renderRequest.setAttribute("designation", designation);

		return "/edit_designation.jsp";
	}

	@Reference
	private DesignationLocalService _designationLocalService;

}