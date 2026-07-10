package com.hrms.employee.web.action;

import com.hrms.employee.service.EmployeeLocalService;
import com.hrms.employee.web.constants.EmployeeWebPortletKeys;

import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.ParamUtil;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import jakarta.portlet.ActionRequest;
import jakarta.portlet.ActionResponse;

@Component(
	property = {
		"jakarta.portlet.name=" + EmployeeWebPortletKeys.EMPLOYEEWEB,
		"mvc.command.name=/employee/delete"
	},
	service = MVCActionCommand.class
)
public class DeleteEmployeeMVCActionCommand extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest,
			ActionResponse actionResponse)
		throws Exception {

		long employeeId = ParamUtil.getLong(
			actionRequest, "employeeId");

		_employeeLocalService.deleteEmployee(employeeId);

		System.out.println(
			"EMPLOYEE DELETED : " + employeeId);
	}

	@Reference
	private EmployeeLocalService _employeeLocalService;

}
