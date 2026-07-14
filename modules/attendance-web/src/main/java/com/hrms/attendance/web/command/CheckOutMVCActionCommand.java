package com.hrms.attendance.web.command;

import com.hrms.attendance.model.Attendance;
import com.hrms.attendance.service.AttendanceLocalService;
import com.hrms.employee.model.Employee;
import com.hrms.employee.service.EmployeeLocalService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import jakarta.portlet.ActionRequest;
import jakarta.portlet.ActionResponse;
import jakarta.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
    property = {
        "jakarta.portlet.name=com_hrms_attendance_web_AttendanceWebPortlet",
        "mvc.command.name=/attendance/checkout"
    },
    service = MVCActionCommand.class
)
public class CheckOutMVCActionCommand extends BaseMVCActionCommand {

    private static final Log _log = LogFactoryUtil.getLog(CheckOutMVCActionCommand.class);

    @Reference
    private AttendanceLocalService _attendanceLocalService;

    @Reference
    private EmployeeLocalService _employeeLocalService;

    @Override
    protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
        try {
            ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
            long liferayUserId = themeDisplay.getUserId();
            
            HttpServletRequest httpRequest = PortalUtil.getHttpServletRequest(actionRequest);
            String ipAddress = httpRequest.getRemoteAddr();

            double latitude = ParamUtil.getDouble(actionRequest, "latitude");
            double longitude = ParamUtil.getDouble(actionRequest, "longitude");

            long employeeId = 0;
            try {
                // Real integration: Fetch from Shivam's module using custom impl method
                Employee employeeRecord = _employeeLocalService.getEmployeeByUserId(liferayUserId);
                employeeId = employeeRecord.getEmployeeId();
            } catch (Exception e) {
                _log.error("No HRMS Employee profile found for Liferay User: " + liferayUserId);
                SessionErrors.add(actionRequest, "employee-profile-missing");
                actionResponse.getRenderParameters().setValue("mvcPath", "/view.jsp");
                return; 
            }

            Calendar todayCal = Calendar.getInstance();
            int currentYear = todayCal.get(Calendar.YEAR);
            int currentDay = todayCal.get(Calendar.DAY_OF_YEAR);

            List<Attendance> allAttendances = _attendanceLocalService.getAttendances(-1, -1);
            Attendance todaysAttendance = null;

            for (Attendance att : allAttendances) {
                if (att.getEmployeeId() == employeeId && att.getAttendanceDate() != null) {
                    Calendar attCal = Calendar.getInstance();
                    attCal.setTime(att.getAttendanceDate());
                    
                    if (attCal.get(Calendar.YEAR) == currentYear && attCal.get(Calendar.DAY_OF_YEAR) == currentDay) {
                        todaysAttendance = att;
                        break;
                    }
                }
            }

            if (todaysAttendance != null) {
                todaysAttendance.setCheckOutTime(new Date());
                todaysAttendance.setCheckOutIP(ipAddress);
                todaysAttendance.setCheckOutLatitude(latitude);
                todaysAttendance.setCheckOutLongitude(longitude);
                
                _attendanceLocalService.updateAttendance(todaysAttendance);

                SessionMessages.add(actionRequest, "attendance-check-out-success");
                _log.info("Check-out successful for employee: " + employeeId);
            } else {
                _log.warn("Attempted check-out without check-in for employee: " + employeeId);
                SessionErrors.add(actionRequest, "attendance-no-check-in-found");
                actionResponse.getRenderParameters().setValue("mvcPath", "/view.jsp");
            }

        } catch (Exception e) {
            _log.error("Error during Check-Out", e);
            SessionErrors.add(actionRequest, "attendance-check-out-error");
            actionResponse.getRenderParameters().setValue("mvcPath", "/view.jsp");
        }
    }
}