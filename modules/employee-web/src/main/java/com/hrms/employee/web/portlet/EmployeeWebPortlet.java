package com.hrms.employee.web.portlet;

import com.hrms.employee.model.Department;
import com.hrms.employee.model.Designation;
import com.hrms.employee.model.Employee;
import com.hrms.employee.service.DepartmentLocalService;
import com.hrms.employee.service.DesignationLocalService;
import com.hrms.employee.service.EmployeeLocalService;
import com.hrms.employee.web.constants.EmployeeWebPortletKeys;

import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import jakarta.portlet.Portlet;
import jakarta.portlet.PortletException;
import jakarta.portlet.RenderRequest;
import jakarta.portlet.RenderResponse;

import jakarta.servlet.http.HttpServletRequest;

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

		Layout layout = themeDisplay.getLayout();

		String currentPageFriendlyURL = layout.getFriendlyURL();

		String keyword = getOriginalRequestParameter(renderRequest, "keyword");
		String statusFilter = getOriginalRequestParameter(renderRequest, "statusFilter");

		int pageNumber = getIntParameter(renderRequest, "pageNumber", 1);
		int pageSize = getIntParameter(renderRequest, "pageSize", 5);

		List<Employee> allEmployees =
			_employeeLocalService.getEmployees(-1, -1);

		List<Employee> employees = allEmployees;

		if (currentPageFriendlyURL.contains("employee-management")) {
			employees = filterEmployees(employees, keyword, statusFilter);
		}

		int totalEmployeesCount = employees.size();
		int totalPages = (int)Math.ceil((double)totalEmployeesCount / pageSize);

		if (totalPages == 0) {
			totalPages = 1;
		}

		if (pageNumber < 1) {
			pageNumber = 1;
		}

		if (pageNumber > totalPages) {
			pageNumber = totalPages;
		}

		int start = (pageNumber - 1) * pageSize;
		int end = Math.min(start + pageSize, totalEmployeesCount);

		List<Employee> paginatedEmployees = new ArrayList<>();

		if (start < end) {
			paginatedEmployees = employees.subList(start, end);
		}

		List<Department> departments =
			_departmentLocalService.getDepartments(-1, -1);

		List<Designation> designations =
			_designationLocalService.getDesignations(-1, -1);

		boolean canManageEmployees =
			hasRole(themeDisplay, "HRMS Admin") ||
			hasRole(themeDisplay, "HRMS HR");

		Map<String, Integer> departmentWiseCount =
			getDepartmentWiseCount(allEmployees);

		Map<String, Integer> designationWiseCount =
			getDesignationWiseCount(allEmployees);

		int activeEmployees = getStatusCount(allEmployees, "Active");
		int inactiveEmployees = getStatusCount(allEmployees, "Inactive");

		renderRequest.setAttribute("employees", paginatedEmployees);
		renderRequest.setAttribute("allEmployees", allEmployees);
		renderRequest.setAttribute("departments", departments);
		renderRequest.setAttribute("designations", designations);

		renderRequest.setAttribute("keyword", keyword);
		renderRequest.setAttribute("statusFilter", statusFilter);

		renderRequest.setAttribute("pageNumber", pageNumber);
		renderRequest.setAttribute("pageSize", pageSize);
		renderRequest.setAttribute("totalPages", totalPages);
		renderRequest.setAttribute("totalEmployeesCount", totalEmployeesCount);

		renderRequest.setAttribute("reportTotalEmployees", allEmployees.size());
		renderRequest.setAttribute("reportActiveEmployees", activeEmployees);
		renderRequest.setAttribute("reportInactiveEmployees", inactiveEmployees);
		renderRequest.setAttribute("departmentWiseCount", departmentWiseCount);
		renderRequest.setAttribute("designationWiseCount", designationWiseCount);

		renderRequest.setAttribute("canManageEmployees", canManageEmployees);
		renderRequest.setAttribute("canManageDepartments", canManageEmployees);
		renderRequest.setAttribute("canManageDesignations", canManageEmployees);

		renderRequest.setAttribute(
			"currentPageFriendlyURL", currentPageFriendlyURL);

		super.render(renderRequest, renderResponse);
	}

	private Map<String, Integer> getDepartmentWiseCount(
		List<Employee> employees) {

		Map<String, Integer> departmentWiseCount = new LinkedHashMap<>();

		for (Employee employee : employees) {
			String department = employee.getDepartment();

			if ((department == null) || department.isEmpty()) {
				department = "Not Assigned";
			}

			departmentWiseCount.put(
				department,
				departmentWiseCount.getOrDefault(department, 0) + 1);
		}

		return departmentWiseCount;
	}

	private Map<String, Integer> getDesignationWiseCount(
		List<Employee> employees) {

		Map<String, Integer> designationWiseCount = new LinkedHashMap<>();

		for (Employee employee : employees) {
			String designation = employee.getDesignation();

			if ((designation == null) || designation.isEmpty()) {
				designation = "Not Assigned";
			}

			designationWiseCount.put(
				designation,
				designationWiseCount.getOrDefault(designation, 0) + 1);
		}

		return designationWiseCount;
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

	private int getIntParameter(
		RenderRequest renderRequest, String parameterName, int defaultValue) {

		try {
			String value = getOriginalRequestParameter(renderRequest, parameterName);

			if (value.isEmpty()) {
				return defaultValue;
			}

			return Integer.parseInt(value);
		}
		catch (Exception exception) {
			return defaultValue;
		}
	}

	private String getOriginalRequestParameter(
		RenderRequest renderRequest, String parameterName) {

		HttpServletRequest httpServletRequest =
			PortalUtil.getHttpServletRequest(renderRequest);

		HttpServletRequest originalRequest =
			PortalUtil.getOriginalServletRequest(httpServletRequest);

		Map<String, String[]> parameterMap =
			originalRequest.getParameterMap();

		for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
			String key = entry.getKey();

			if (key.endsWith(parameterName)) {
				String[] values = entry.getValue();

				if ((values != null) && (values.length > 0)) {
					return values[0];
				}
			}
		}

		return "";
	}

	private List<Employee> filterEmployees(
		List<Employee> employees, String keyword, String statusFilter) {

		List<Employee> filteredEmployees = new ArrayList<>();

		String lowerKeyword = keyword.toLowerCase();

		for (Employee employee : employees) {
			boolean matchesKeyword = true;
			boolean matchesStatus = true;

			if (!keyword.isEmpty()) {
				matchesKeyword =
					contains(employee.getEmployeeCode(), lowerKeyword) ||
					contains(employee.getFirstName(), lowerKeyword) ||
					contains(employee.getLastName(), lowerKeyword) ||
					contains(employee.getEmail(), lowerKeyword) ||
					contains(employee.getDepartment(), lowerKeyword) ||
					contains(employee.getDesignation(), lowerKeyword);
			}

			if (!statusFilter.isEmpty()) {
				matchesStatus = statusFilter.equalsIgnoreCase(
					employee.getStatus());
			}

			if (matchesKeyword && matchesStatus) {
				filteredEmployees.add(employee);
			}
		}

		return filteredEmployees;
	}

	private boolean contains(String value, String keyword) {
		return value != null &&
			value.toLowerCase().contains(keyword);
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
	private DepartmentLocalService _departmentLocalService;

	@Reference
	private DesignationLocalService _designationLocalService;

	@Reference
	private RoleLocalService _roleLocalService;

}