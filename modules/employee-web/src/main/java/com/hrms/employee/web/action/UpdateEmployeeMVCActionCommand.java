package com.hrms.employee.web.action;

import com.hrms.employee.model.Employee;
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
		"mvc.command.name=/employee/update"
	},
	service = MVCActionCommand.class
)
public class UpdateEmployeeMVCActionCommand
	extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest,
			ActionResponse actionResponse)
		throws Exception {

		long employeeId = ParamUtil.getLong(
			actionRequest, "employeeId");

		Employee employee =
			_employeeLocalService.getEmployee(employeeId);

		employee.setEmployeeCode(
			ParamUtil.getString(actionRequest, "employeeCode"));

		employee.setFirstName(
			ParamUtil.getString(actionRequest, "firstName"));

		employee.setLastName(
			ParamUtil.getString(actionRequest, "lastName"));

		employee.setEmail(
			ParamUtil.getString(actionRequest, "email"));

		employee.setPhoneNumber(
			ParamUtil.getString(actionRequest, "phoneNumber"));

		employee.setDepartment(
			ParamUtil.getString(actionRequest, "department"));

		employee.setDesignation(
			ParamUtil.getString(actionRequest, "designation"));

		employee.setStatus(
			ParamUtil.getString(actionRequest, "status"));

		_employeeLocalService.updateEmployee(employee);

		System.out.println(
			"EMPLOYEE UPDATED : " + employeeId);
	}

	@Reference
	private EmployeeLocalService _employeeLocalService;

}