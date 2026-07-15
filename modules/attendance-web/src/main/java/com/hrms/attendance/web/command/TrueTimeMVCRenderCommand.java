package com.hrms.attendance.web.command;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import com.hrms.attendance.model.Attendance;
import com.hrms.attendance.service.AttendanceLocalServiceUtil;

import jakarta.portlet.PortletException;
import jakarta.portlet.RenderRequest;
import jakarta.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component(
    immediate = true,
    property = {
        "jakarta.portlet.name=com_hrms_attendance_web_portlet_AttendanceWebPortlet", 
        "mvc.command.name=/attendance/truetime"
    },
    service = MVCRenderCommand.class
)
public class TrueTimeMVCRenderCommand implements MVCRenderCommand {

    @Override
    public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
        
        ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
        long currentUserId = themeDisplay.getUserId();
        
        // 1. Dynamic Look-Up: Fetch the target employee from the URL (defaults to logged-in user)
        long targetEmployeeId = ParamUtil.getLong(renderRequest, "targetEmployeeId", currentUserId);
        
        // 2. Security Check: Only Omniadmins (or specific roles) can view others' data
        boolean isAdmin = themeDisplay.getPermissionChecker().isOmniadmin();
        if (targetEmployeeId != currentUserId && !isAdmin) {
            targetEmployeeId = currentUserId; // Force fallback to their own data if unauthorized
        }

        // 3. Date Configuration (Supports navigating past/future months via URL params)
        int monthParam = ParamUtil.getInteger(renderRequest, "month", LocalDate.now().getMonthValue());
        int yearParam = ParamUtil.getInteger(renderRequest, "year", LocalDate.now().getYear());
        YearMonth currentMonth = YearMonth.of(yearParam, monthParam);
        int daysInMonth = currentMonth.lengthOfMonth();
        
        // 4. Fetch Actual Database Records
        List<Map<String, Object>> dailyLogs = new ArrayList<>();
        int totalMinutesWorked = 0;
        int daysWorked = 0;

        for (int i = 1; i <= daysInMonth; i++) {
            LocalDate localDate = currentMonth.atDay(i);
            Map<String, Object> dayData = new HashMap<>();
            dayData.put("day", i);
            dayData.put("dayOfWeek", localDate.getDayOfWeek().name().substring(0, 1));
            
            boolean isWeekend = (localDate.getDayOfWeek().getValue() >= 6);
            dayData.put("isWeekend", isWeekend);
            
            int workedMinutes = 0;
            
            // Convert LocalDate to java.util.Date for Liferay Service call
            Date searchDate = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

            try {
                // Uses the EmployeeAndDate finder from your service.xml
                // Note: 'fetch' returns null if not found, whereas 'get' throws an exception
            	Attendance attendance = AttendanceLocalServiceUtil.fetchEmployeeAndDate(targetEmployeeId, searchDate);
                
                if (attendance != null && attendance.getCheckInTime() != null && attendance.getCheckOutTime() != null) {
                    long checkIn = attendance.getCheckInTime().getTime();
                    long checkOut = attendance.getCheckOutTime().getTime();
                    
                    workedMinutes = (int) ((checkOut - checkIn) / (1000 * 60)); // Convert milliseconds to minutes
                    
                    totalMinutesWorked += workedMinutes;
                    daysWorked++;
                }
            } catch (Exception e) {
                // If the record doesn't exist or errors out, workedMinutes safely remains 0
            }

            dayData.put("workedMinutes", workedMinutes);
            dailyLogs.add(dayData);
        }

        // 5. Calculate Exact Averages
        int avgMinutes = daysWorked > 0 ? (totalMinutesWorked / daysWorked) : 0;
        String monthAvg = (avgMinutes / 60) + "h " + String.format("%02d", (avgMinutes % 60)) + "m";

        // 6. Push data back to the UI (truetime.jsp)
        renderRequest.setAttribute("currentMonthName", currentMonth.getMonth().name() + " " + currentMonth.getYear());
        renderRequest.setAttribute("dailyLogs", dailyLogs);
        renderRequest.setAttribute("monthAvg", monthAvg);
        renderRequest.setAttribute("viewingEmployeeId", targetEmployeeId);

        return "/truetime.jsp"; 
    }
}