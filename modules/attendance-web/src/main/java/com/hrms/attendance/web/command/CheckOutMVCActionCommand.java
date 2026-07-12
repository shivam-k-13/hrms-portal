package com.hrms.attendance.web.command;

import com.hrms.attendance.model.Attendance;
import com.hrms.attendance.service.AttendanceLocalService;
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

// Correct Liferay 2026 Jakarta Imports
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

    @Override
    protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
        try {
            ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
            long liferayUserId = themeDisplay.getUserId();
            
            HttpServletRequest httpRequest = PortalUtil.getHttpServletRequest(actionRequest);
            String ipAddress = httpRequest.getRemoteAddr();

            double latitude = ParamUtil.getDouble(actionRequest, "latitude");
            double longitude = ParamUtil.getDouble(actionRequest, "longitude");

            // BYPASS: Use Liferay ID directly until Shivam finishes his Employee API
            long employeeId = liferayUserId;

            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
            Date normalizedDate = cal.getTime();

            Attendance attendance = _attendanceLocalService.fetchEmployeeAndDate(employeeId, normalizedDate);

            if (attendance != null) {
                attendance.setCheckOutTime(new Date());
                attendance.setCheckOutIP(ipAddress);
                attendance.setCheckOutLatitude(latitude);
                attendance.setCheckOutLongitude(longitude);
                
                _attendanceLocalService.updateAttendance(attendance);

                SessionMessages.add(actionRequest, "attendance-check-out-success");
                _log.info("Check-out successful for user: " + liferayUserId);
            } else {
                _log.warn("Check-Out attempted without prior Check-In for user: " + liferayUserId);
                SessionErrors.add(actionRequest, "attendance-no-check-in-found");
            }

        } catch (Exception e) {
            _log.error("Error during Check-Out", e);
            SessionErrors.add(actionRequest, "attendance-check-out-error");
            actionResponse.getRenderParameters().setValue("mvcPath", "/view.jsp");
        }
    }
}