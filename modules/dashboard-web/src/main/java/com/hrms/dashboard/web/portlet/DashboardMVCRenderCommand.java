package com.hrms.dashboard.web.portlet;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.model.Role;

import com.hrms.employee.service.EmployeeLocalServiceUtil;
import com.hrms.leave.service.LeaveRequestLocalServiceUtil;
import com.hrms.dashboard.web.constants.DashboardWebPortletKeys;

import jakarta.portlet.PortletException;
import jakarta.portlet.RenderRequest;
import jakarta.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import java.util.List;

@Component(
    immediate = true,
    property = {
        "jakarta.portlet.name=" + DashboardWebPortletKeys.DASHBOARDWEB, 
        "mvc.command.name=/" 
    },
    service = MVCRenderCommand.class
)
public class DashboardMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
	    
	    ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
	    long userId = themeDisplay.getUserId();
	    
	    try {
	        // 1. Determine User Role using live Liferay data
	        boolean isAdmin = themeDisplay.getPermissionChecker().isOmniadmin();
	        boolean isHR = false;
	        boolean isManager = false;
	        boolean isEmployee = true; // Everyone gets Employee view by default unless upgraded

            // Fetch the user's actual roles from the database
            List<Role> userRoles = RoleLocalServiceUtil.getUserRoles(userId);
            for (Role role : userRoles) {
                String roleName = role.getName();
                if (roleName.equalsIgnoreCase("HRMS Manager")) {
                    isManager = true;
                } else if (roleName.equalsIgnoreCase("HRMS HR")) {
                    isHR = true;
                }
            }

	        // 2. Calculate Specific Pending Leaves based on Role
	        int pendingActionCount = 0;
	        
	        if (isManager) {
	            // Managers only see leaves waiting for Tier 1 approval
	            pendingActionCount = LeaveRequestLocalServiceUtil.getLeaveRequestsByStatus("PENDING_MANAGER").size();
	        } else if (isHR || isAdmin) {
	            // HR only sees leaves that Managers have already approved
	            pendingActionCount = LeaveRequestLocalServiceUtil.getLeaveRequestsByStatus("PENDING_HR").size();
	        }

	        // 3. Employee Personal Metrics
	        int myTotalLeaves = LeaveRequestLocalServiceUtil.getLeaveRequestsByEmployeeId(userId).size();
	        
	        // 4. Global Metrics
	        int totalEmployees = EmployeeLocalServiceUtil.getEmployeesCount();

	        // 5. Send data to dashboard.jsp
	        renderRequest.setAttribute("totalEmployees", totalEmployees);
	        renderRequest.setAttribute("activeEmployees", totalEmployees); 
	        renderRequest.setAttribute("pendingLeaves", pendingActionCount); 
	        renderRequest.setAttribute("teamPendingLeaves", pendingActionCount); 
	        renderRequest.setAttribute("myLeaves", myTotalLeaves);
	        
	        // Router Flags
	        renderRequest.setAttribute("isAdmin", isAdmin);
	        renderRequest.setAttribute("isHR", isHR);
	        renderRequest.setAttribute("isManager", isManager);
	        renderRequest.setAttribute("isEmployee", isEmployee);

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return "/view.jsp"; 
	}
}