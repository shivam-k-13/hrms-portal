<%@ include file="/init.jsp" %>
<%@ page import="java.text.SimpleDateFormat" %>

<%
    java.util.List<com.hrms.attendance.model.Attendance> userAttendanceList = 
        (java.util.List<com.hrms.attendance.model.Attendance>) request.getAttribute("userAttendanceList");
    
    if (userAttendanceList == null) {
        userAttendanceList = new java.util.ArrayList<>();
    }

    boolean hasCheckedInToday = request.getAttribute("hasCheckedInToday") != null ? (Boolean) request.getAttribute("hasCheckedInToday") : false;
    boolean hasCheckedOutToday = request.getAttribute("hasCheckedOutToday") != null ? (Boolean) request.getAttribute("hasCheckedOutToday") : false;
    
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
    SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a");
%>

<portlet:actionURL name="/attendance/checkin" var="checkInURL" />
<portlet:actionURL name="/attendance/checkout" var="checkOutURL" />

<div class="container-fluid mt-4">
    
    <!-- Professional Blue/White Widget -->
    <div class="card shadow-sm border-0 mb-5">
        <div class="card-header bg-primary text-white text-center py-4">
            <h3 class="mb-0 text-white">Campus Attendance</h3>
            <p class="mb-0" style="opacity: 0.85;">Secure Geofenced Time Tracking</p>
        </div>
        
        <div class="card-body text-center py-5 bg-white">
            
            <% if (!hasCheckedInToday) { %>
                <h4 class="text-secondary mb-4">You have not checked in today.</h4>
                <form action="${checkInURL}" method="post" name="<portlet:namespace/>checkInForm" id="<portlet:namespace/>checkInForm">
                    <input type="hidden" name="<portlet:namespace/>latitude" id="<portlet:namespace/>checkInLat" />
                    <input type="hidden" name="<portlet:namespace/>longitude" id="<portlet:namespace/>checkInLng" />
                    
                    <button type="button" class="btn btn-primary btn-lg px-5 py-3 shadow-sm" onclick="validateGeofenceAndSubmit('<portlet:namespace/>checkInForm', '<portlet:namespace/>checkInLat', '<portlet:namespace/>checkInLng')">
                        <i class="icon-map-marker mr-2"></i> Verify Location & Check In
                    </button>
                </form>

            <% } else if (!hasCheckedOutToday) { %>
                <h4 class="text-success mb-4"><i class="icon-ok-sign"></i> Checked In Successfully</h4>
                <p class="text-muted mb-4">Have a great workday! Don't forget to check out before you leave campus.</p>
                
                <form action="${checkOutURL}" method="post" name="<portlet:namespace/>checkOutForm" id="<portlet:namespace/>checkOutForm">
                    <input type="hidden" name="<portlet:namespace/>latitude" id="<portlet:namespace/>checkOutLat" />
                    <input type="hidden" name="<portlet:namespace/>longitude" id="<portlet:namespace/>checkOutLng" />
                    
                    <button type="button" class="btn btn-outline-primary btn-lg px-5 py-3" onclick="validateGeofenceAndSubmit('<portlet:namespace/>checkOutForm', '<portlet:namespace/>checkOutLat', '<portlet:namespace/>checkOutLng')">
                        <i class="icon-time mr-2"></i> Check Out
                    </button>
                </form>

            <% } else { %>
                <h4 class="text-primary mb-3"><i class="icon-calendar"></i> Attendance Completed</h4>
                <p class="text-muted">Your shift for today has been successfully recorded.</p>
            <% } %>

        </div>
    </div>

    <!-- Data Table Fix -->
    <div class="card shadow-sm border-0">
        <div class="card-header bg-white border-bottom">
            <h4 class="card-title text-primary mb-0">Your Attendance History</h4>
        </div>
        <div class="card-body p-0">
            <liferay-ui:search-container total="<%= userAttendanceList.size() %>" emptyResultsMessage="No attendance records found.">
                <liferay-ui:search-container-results results="<%= userAttendanceList %>" />

                <liferay-ui:search-container-row
                    className="com.hrms.attendance.model.Attendance"
                    modelVar="attendance"
                    keyProperty="attendanceId">

                    <!-- Fixed Date & Time Formatting -->
                    <liferay-ui:search-container-column-text name="Date" 
                        value="<%= attendance.getAttendanceDate() != null ? dateFormat.format(attendance.getAttendanceDate()) : \"-\" %>" />
                        
                    <liferay-ui:search-container-column-text name="Check In" 
                        value="<%= attendance.getCheckInTime() != null ? timeFormat.format(attendance.getCheckInTime()) : \"-\" %>" />
                        
                    <liferay-ui:search-container-column-text name="Check Out" 
                        value="<%= attendance.getCheckOutTime() != null ? timeFormat.format(attendance.getCheckOutTime()) : \"-\" %>" />
                        
                    <liferay-ui:search-container-column-text name="Status" property="status" />

                </liferay-ui:search-container-row>
                <liferay-ui:search-iterator />
            </liferay-ui:search-container>
        </div>
    </div>
</div>

<script>
    // System Constants for Geofencing
    const CAMPUS_LAT = 12.852153777008303;
    const CAMPUS_LNG = 80.227646;
    const MAX_DISTANCE_METERS = 200;

    /**
     * Haversine formula to calculate the exact distance in meters between two coordinates.
     */
    function getDistanceInMeters(lat1, lon1, lat2, lon2) {
        const R = 6371e3; // Earth radius in meters
        const p1 = lat1 * Math.PI/180;
        const p2 = lat2 * Math.PI/180;
        const dp = (lat2-lat1) * Math.PI/180;
        const dl = (lon2-lon1) * Math.PI/180;

        const a = Math.sin(dp/2) * Math.sin(dp/2) + Math.cos(p1) * Math.cos(p2) * Math.sin(dl/2) * Math.sin(dl/2);
        const c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        
        return R * c;
    }

    /**
     * Captures GPS, validates geofence, and submits.
     */
    function validateGeofenceAndSubmit(formId, latFieldId, lngFieldId) {
        if (navigator.geolocation) {
            
            const btn = document.querySelector('#' + formId + ' button');
            const originalText = btn.innerHTML;
            btn.innerHTML = '<span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span> Validating Location...';
            btn.disabled = true;

            navigator.geolocation.getCurrentPosition(
                function(position) {
                    const userLat = position.coords.latitude;
                    const userLng = position.coords.longitude;
                    
                    // Validate Geofence
                    const distance = getDistanceInMeters(userLat, userLng, CAMPUS_LAT, CAMPUS_LNG);
                    
                    if (distance <= MAX_DISTANCE_METERS) {
                        document.getElementById(latFieldId).value = userLat;
                        document.getElementById(lngFieldId).value = userLng;
                        document.getElementById(formId).submit();
                    } else {
                        btn.innerHTML = originalText;
                        btn.disabled = false;
                        alert("Geofence Error: You are " + Math.round(distance) + " meters away from the campus hub. You must be within " + MAX_DISTANCE_METERS + " meters to check in.");
                    }
                },
                function(error) {
                    btn.innerHTML = originalText;
                    btn.disabled = false;
                    alert("Location Error: Please ensure GPS is enabled and permissions are granted.");
                },
                { enableHighAccuracy: true, timeout: 10000, maximumAge: 0 }
            );
        } else {
            alert("Geolocation is not supported by your browser.");
        }
    }
</script>