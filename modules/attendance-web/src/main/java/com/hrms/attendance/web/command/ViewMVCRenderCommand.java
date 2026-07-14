package com.hrms.attendance.web.command;

import com.hrms.attendance.model.Attendance;
import com.hrms.attendance.service.AttendanceLocalService;
import com.hrms.employee.model.Employee;
import com.hrms.employee.service.EmployeeLocalService;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import jakarta.portlet.PortletException;
import jakarta.portlet.RenderRequest;
import jakarta.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
    property = {
        "jakarta.portlet.name=com_hrms_attendance_web_AttendanceWebPortlet",
        "mvc.command.name=/"
    },
    service = MVCRenderCommand.class
)
public class ViewMVCRenderCommand implements MVCRenderCommand {

    @Reference
    private AttendanceLocalService _attendanceLocalService;

    @Reference
    private EmployeeLocalService _employeeLocalService;

    @Override
    public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
        
        ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
        long liferayUserId = themeDisplay.getUserId();
        long employeeId = 0;
        
        try {
            // Real integration: Fetch from Shivam's module using custom impl method
            Employee employeeRecord = _employeeLocalService.getEmployeeByUserId(liferayUserId);
            employeeId = employeeRecord.getEmployeeId();
        } catch (Exception e) {
            // If not found, employeeId remains 0, resulting in a safe empty table
        }

        List<Attendance> allAttendances = _attendanceLocalService.getAttendances(-1, -1);
        List<Attendance> userAttendanceList = new ArrayList<>();

        boolean hasCheckedInToday = false;
        boolean hasCheckedOutToday = false;

        Calendar todayCal = Calendar.getInstance();
        int currentYear = todayCal.get(Calendar.YEAR);
        int currentDay = todayCal.get(Calendar.DAY_OF_YEAR);

        for (Attendance att : allAttendances) {
            if (att.getEmployeeId() == employeeId && employeeId != 0) {
                userAttendanceList.add(att);

                if (att.getAttendanceDate() != null) {
                    Calendar attCal = Calendar.getInstance();
                    attCal.setTime(att.getAttendanceDate());
                    
                    if (attCal.get(Calendar.YEAR) == currentYear && attCal.get(Calendar.DAY_OF_YEAR) == currentDay) {
                        if (att.getCheckInTime() != null) hasCheckedInToday = true;
                        if (att.getCheckOutTime() != null) hasCheckedOutToday = true;
                    }
                }
            }
        }

        renderRequest.setAttribute("userAttendanceList", userAttendanceList);
        renderRequest.setAttribute("hasCheckedInToday", hasCheckedInToday);
        renderRequest.setAttribute("hasCheckedOutToday", hasCheckedOutToday);

        return "/view.jsp";
    }
}