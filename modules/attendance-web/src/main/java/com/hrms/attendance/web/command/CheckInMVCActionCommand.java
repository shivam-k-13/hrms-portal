package com.hrms.attendance.web.command;

import com.hrms.attendance.model.Attendance;
import com.hrms.attendance.service.AttendanceLocalService;
import com.hrms.employee.model.Employee;
import com.hrms.employee.service.EmployeeLocalService;
import com.liferay.counter.kernel.service.CounterLocalService;
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

import jakarta.portlet.ActionRequest;
import jakarta.portlet.ActionResponse;
import jakarta.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
    property = {
        "jakarta.portlet.name=com_hrms_attendance_web_AttendanceWebPortlet",
        "mvc.command.name=/attendance/checkin"
    },
    service = MVCActionCommand.class
)
public class CheckInMVCActionCommand extends BaseMVCActionCommand {

    private static final Log _log = LogFactoryUtil.getLog(CheckInMVCActionCommand.class);

    @Reference
    private AttendanceLocalService _attendanceLocalService;

    @Reference
    private CounterLocalService _counterLocalService;

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

            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
            Date normalizedDate = cal.getTime();

            long attendanceId = _counterLocalService.increment(Attendance.class.getName());
            Attendance attendance = _attendanceLocalService.createAttendance(attendanceId);
            
            attendance.setEmployeeId(employeeId);
            attendance.setUserId(liferayUserId);
            attendance.setGroupId(themeDisplay.getScopeGroupId());
            attendance.setCompanyId(themeDisplay.getCompanyId());
            
            attendance.setAttendanceDate(normalizedDate);
            attendance.setCheckInTime(new Date()); 
            attendance.setCheckInIP(ipAddress);
            attendance.setCheckInLatitude(latitude);
            attendance.setCheckInLongitude(longitude);
            attendance.setStatus("PRESENT");
            
            _attendanceLocalService.addAttendance(attendance);

            SessionMessages.add(actionRequest, "attendance-check-in-success");
            _log.info("Check-in successful for employee: " + employeeId);

        } catch (Exception e) {
            _log.error("Error during Check-In", e);
            SessionErrors.add(actionRequest, "attendance-check-in-error");
            actionResponse.getRenderParameters().setValue("mvcPath", "/view.jsp");
        }
    }
}