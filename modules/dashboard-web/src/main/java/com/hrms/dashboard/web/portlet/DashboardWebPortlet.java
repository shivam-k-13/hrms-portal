package com.hrms.dashboard.web.portlet;

import com.hrms.dashboard.web.constants.DashboardWebPortletKeys;
import com.hrms.employee.model.Employee;
import com.hrms.employee.service.EmployeeLocalService;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import jakarta.portlet.Portlet;
import jakarta.portlet.PortletException;
import jakarta.portlet.RenderRequest;
import jakarta.portlet.RenderResponse;

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

		List<Employee> employees =
			_employeeLocalService.getEmployees(-1, -1);

		int totalEmployees = employees.size();
		int activeEmployees = getStatusCount(employees, "Active");
		int inactiveEmployees = getStatusCount(employees, "Inactive");

		boolean isAdmin = hasRole(themeDisplay, "HRMS Admin");
		boolean isHR = hasRole(themeDisplay, "HRMS HR");
		boolean isManager = hasRole(themeDisplay, "HRMS Manager");
		boolean isEmployee = hasRole(themeDisplay, "HRMS Employee");

		/*
		 * TODO: Attendance Module Integration Later
		 *
		 * After Mayank completes attendance-service:
		 *
		 * @Reference
		 * private AttendanceLocalService _attendanceLocalService;
		 *
		 * int presentToday = _attendanceLocalService.getPresentTodayCount();
		 * int absentToday = _attendanceLocalService.getAbsentTodayCount();
		 * int teamAttendanceCount = _attendanceLocalService.getTeamAttendanceCount(managerUserId);
		 */

		int presentToday = 0;
		int absentToday = 0;
		int teamAttendanceCount = 0;

		/*
		 * TODO: Leave Module Integration Later
		 *
		 * After Akash completes leave-service:
		 *
		 * @Reference
		 * private LeaveRequestLocalService _leaveRequestLocalService;
		 *
		 * int pendingLeaves = _leaveRequestLocalService.getPendingLeaveCount();
		 * int myLeaves = _leaveRequestLocalService.getMyLeaveCount(userId);
		 * int teamPendingLeaves = _leaveRequestLocalService.getTeamPendingLeaveCount(managerUserId);
		 */

		int pendingLeaves = 0;
		int myLeaves = 0;
		int teamPendingLeaves = 0;

		/*
		 * TODO: Payroll Module Integration Later
		 *
		 * After payroll module is completed:
		 *
		 * int generatedPayslips = _payslipLocalService.getGeneratedPayslipCount();
		 * int myPayslips = _payslipLocalService.getMyPayslipCount(userId);
		 */

		int generatedPayslips = 0;
		int myPayslips = 0;

		renderRequest.setAttribute(
			"currentPageFriendlyURL", currentPageFriendlyURL);

		renderRequest.setAttribute(
			"totalEmployees", totalEmployees);

		renderRequest.setAttribute(
			"activeEmployees", activeEmployees);

		renderRequest.setAttribute(
			"inactiveEmployees", inactiveEmployees);

		renderRequest.setAttribute(
			"presentToday", presentToday);

		renderRequest.setAttribute(
			"absentToday", absentToday);

		renderRequest.setAttribute(
			"teamAttendanceCount", teamAttendanceCount);

		renderRequest.setAttribute(
			"pendingLeaves", pendingLeaves);

		renderRequest.setAttribute(
			"myLeaves", myLeaves);

		renderRequest.setAttribute(
			"teamPendingLeaves", teamPendingLeaves);

		renderRequest.setAttribute(
			"generatedPayslips", generatedPayslips);

		renderRequest.setAttribute(
			"myPayslips", myPayslips);

		renderRequest.setAttribute("isAdmin", isAdmin);
		renderRequest.setAttribute("isHR", isHR);
		renderRequest.setAttribute("isManager", isManager);
		renderRequest.setAttribute("isEmployee", isEmployee);

		super.render(renderRequest, renderResponse);
	}

	private int getStatusCount(
		List<Employee> employees, String status) {

		int count = 0;

		for (Employee employee : employees) {
			if (status.equalsIgnoreCase(employee.getStatus())) {
				count++;
			}
		}

		return count;
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