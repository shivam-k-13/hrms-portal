<%@ include file="/init.jsp" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.TimeZone" %>

<%
    java.util.List<com.hrms.attendance.model.Attendance> userAttendanceList = 
        (java.util.List<com.hrms.attendance.model.Attendance>) request.getAttribute("userAttendanceList");
    
    if (userAttendanceList == null) {
        userAttendanceList = new java.util.ArrayList<>();
    }

    boolean hasCheckedInToday = request.getAttribute("hasCheckedInToday") != null ? (Boolean) request.getAttribute("hasCheckedInToday") : false;
    boolean hasCheckedOutToday = request.getAttribute("hasCheckedOutToday") != null ? (Boolean) request.getAttribute("hasCheckedOutToday") : false;
    
    // Explicitly enforce Indian Standard Time (Chennai)
    TimeZone istTimeZone = TimeZone.getTimeZone("Asia/Kolkata");
    
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
    dateFormat.setTimeZone(istTimeZone);
    
    SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a");
    timeFormat.setTimeZone(istTimeZone);
%>

<portlet:actionURL name="/attendance/checkin" var="checkInURL" />
<portlet:actionURL name="/attendance/checkout" var="checkOutURL" />

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" />
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700;800;900&display=swap" />

<style type="text/css">
    .hrms-attendance-wrapper {
        background: #f8fafc;
        font-family: "Inter", sans-serif;
        font-size: 15px;
        padding: 2rem;
        min-height: calc(100vh - 80px);
    }

    /* Page Header */
    .page-header {
        background: linear-gradient(135deg, #0056b3 0%, #0088cc 100%);
        padding: 2rem 2.5rem;
        border-radius: 20px;
        color: white;
        margin-bottom: 2rem;
        box-shadow: 0 10px 25px rgba(0, 86, 179, 0.15);
        display: flex;
        justify-content: space-between;
        align-items: center;
    }
    
    /* Inline Back Button */
    .btn-back-inline {
        display: inline-flex; 
        align-items: center; 
        gap: 0.5rem;
        color: white; 
        text-decoration: none; 
        font-weight: 600; 
        font-size: 0.85rem;
        background: rgba(255, 255, 255, 0.15); 
        padding: 0.4rem 1rem;
        border-radius: 999px; 
        border: 1px solid rgba(255, 255, 255, 0.2);
        transition: 0.2s;
    }
    .btn-back-inline:hover {
        background: rgba(255, 255, 255, 0.25);
        transform: translateX(-4px);
        color: white;
    }

    .page-header h2 { font-size: 1.8rem; font-weight: 900; margin: 0 0 0.25rem; color: white; }
    .page-header p { margin: 0; font-size: 0.95rem; color: #eaf4fc; font-weight: 500; }
    .header-icon { font-size: 3rem; opacity: 0.9; }

    /* 2-Column Layout */
    .attendance-layout {
        display: grid;
        grid-template-columns: 350px 1fr;
        gap: 2rem;
        align-items: start;
    }

    /* Glass Cards */
    .hrms-card {
        background: #ffffff;
        border: 1px solid #e2e8f0;
        border-radius: 20px;
        box-shadow: 0 10px 30px rgba(0, 86, 179, 0.06);
        overflow: hidden;
    }

    .card-header-accent {
        background: #f1f5f9;
        padding: 1.25rem 1.5rem;
        border-bottom: 1px solid #e2e8f0;
        font-weight: 800;
        color: #0f172a;
        font-size: 1.1rem;
    }

    .card-body-padded {
        padding: 2.5rem 2rem;
        text-align: center;
    }

    /* Buttons */
    .btn-action {
        display: inline-flex; align-items: center; justify-content: center; gap: 0.5rem;
        padding: 0.85rem 1.5rem; border-radius: 12px; font-weight: 700; font-size: 0.95rem;
        width: 100%; border: none; cursor: pointer; transition: 0.2s;
    }
    .btn-primary-gradient { background: linear-gradient(135deg, #0056b3 0%, #0088cc 100%); color: white; box-shadow: 0 8px 15px rgba(0, 136, 204, 0.25); }
    .btn-primary-gradient:hover { transform: translateY(-2px); box-shadow: 0 12px 20px rgba(0, 136, 204, 0.35); }
    .btn-outline-primary { background: transparent; color: #0088cc; border: 2px solid #0088cc; }
    .btn-outline-primary:hover { background: #eaf4fc; }

    /* Custom GPS Radar Animation */
    .gps-scanner {
        display: none; 
        flex-direction: column;
        align-items: center;
        justify-content: center;
        padding: 1rem 0;
    }
    .radar-container {
        position: relative;
        width: 100px;
        height: 100px;
        margin-bottom: 1.5rem;
    }
    .radar-pin {
        position: absolute;
        top: 50%; left: 50%;
        transform: translate(-50%, -50%);
        font-size: 2.2rem;
        color: #0088cc;
        z-index: 5;
    }
    .radar-ring {
        position: absolute;
        top: 50%; left: 50%;
        transform: translate(-50%, -50%);
        width: 0; height: 0;
        border-radius: 50%;
        border: 2px solid #0088cc;
        animation: radar-pulse 1.5s cubic-bezier(0.215, 0.61, 0.355, 1) infinite;
    }
    .radar-ring:nth-child(2) { animation-delay: 0.5s; }
    .radar-ring:nth-child(3) { animation-delay: 1s; }

    @keyframes radar-pulse {
        0% { width: 0; height: 0; opacity: 1; border-width: 3px; }
        100% { width: 100px; height: 100px; opacity: 0; border-width: 1px; }
    }
    
    .scanning-text {
        color: #0056b3;
        font-weight: 800;
        font-size: 0.95rem;
        animation: pulse-text 1.5s infinite;
    }
    @keyframes pulse-text { 0%, 100% { opacity: 1; } 50% { opacity: 0.5; } }

    /* Liferay Table Overrides */
    .table-container { padding: 0 1.5rem 1.5rem; }
    .table-container table { width: 100%; border-collapse: collapse; margin-top: 1rem; }
    .table-container th { background: #f1f5f9; padding: 1rem; color: #64748b; font-weight: 700; font-size: 0.85rem; text-align: left; border-bottom: 2px solid #e2e8f0; }
    .table-container td { padding: 1rem; color: #0f172a; font-weight: 600; font-size: 0.9rem; border-bottom: 1px solid #f1f5f9; }
</style>

<div class="hrms-attendance-wrapper">

    <!-- Hero Header -->
    <header class="page-header">
        <div>
            <div style="margin-bottom: 1rem;">
                <a href="/web/hrms/dashboard-router" class="btn-back-inline">
                    <i class="fa-solid fa-arrow-left"></i> Dashboard
                </a>
            </div>
            <h2>Campus Attendance</h2>
            <p>Secure Geofenced Time & Presence Tracking (IST)</p>
        </div>
        <div class="header-icon"><i class="fa-solid fa-map-location-dot"></i></div>
    </header>

    <div class="attendance-layout">
        
        <!-- Left Column: Geofence Action Card -->
        <div class="hrms-card" style="border-top: 5px solid #0088cc;">
            <div class="card-header-accent">
                <i class="fa-solid fa-satellite-dish" style="color: #0088cc; margin-right: 0.5rem;"></i> Identity Verification
            </div>
            
            <div class="card-body-padded">
                
                <% if (!hasCheckedInToday) { %>
                    <div id="<portlet:namespace/>checkInContainer">
                        <div style="font-size: 3rem; color: #94a3b8; margin-bottom: 1rem;"><i class="fa-regular fa-clock"></i></div>
                        <h4 style="color: #0f172a; font-weight: 800; margin-bottom: 0.5rem; font-size: 1.1rem;">Shift Not Started</h4>
                        <p style="color: #64748b; font-size: 0.85rem; margin-bottom: 2rem;">You have not checked in for today.</p>
                        
                        <form action="${checkInURL}" method="post" id="<portlet:namespace/>checkInForm">
                            <input type="hidden" name="<portlet:namespace/>latitude" id="<portlet:namespace/>checkInLat" />
                            <input type="hidden" name="<portlet:namespace/>longitude" id="<portlet:namespace/>checkInLng" />
                            
                            <button type="button" class="btn-action btn-primary-gradient" onclick="validateGeofence('<portlet:namespace/>checkInForm', '<portlet:namespace/>checkInLat', '<portlet:namespace/>checkInLng', '<portlet:namespace/>checkInContainer', '<portlet:namespace/>scannerUI')">
                                <i class="fa-solid fa-location-crosshairs"></i> Verify Location & Check In
                            </button>
                        </form>
                    </div>

                <% } else if (!hasCheckedOutToday) { %>
                    <div id="<portlet:namespace/>checkOutContainer">
                        <div style="font-size: 3rem; color: #10b981; margin-bottom: 1rem;"><i class="fa-regular fa-circle-check"></i></div>
                        <h4 style="color: #0f172a; font-weight: 800; margin-bottom: 0.5rem; font-size: 1.1rem;">Checked In Successfully</h4>
                        <p style="color: #64748b; font-size: 0.85rem; margin-bottom: 2rem;">Have a great workday! Remember to check out before leaving campus.</p>
                        
                        <form action="${checkOutURL}" method="post" id="<portlet:namespace/>checkOutForm">
                            <input type="hidden" name="<portlet:namespace/>latitude" id="<portlet:namespace/>checkOutLat" />
                            <input type="hidden" name="<portlet:namespace/>longitude" id="<portlet:namespace/>checkOutLng" />
                            
                            <button type="button" class="btn-action btn-outline-primary" onclick="validateGeofence('<portlet:namespace/>checkOutForm', '<portlet:namespace/>checkOutLat', '<portlet:namespace/>checkOutLng', '<portlet:namespace/>checkOutContainer', '<portlet:namespace/>scannerUI')">
                                <i class="fa-solid fa-right-from-bracket"></i> Verify & Check Out
                            </button>
                        </form>
                    </div>

                <% } else { %>
                    <div>
                        <div style="font-size: 3rem; color: #8b5cf6; margin-bottom: 1rem;"><i class="fa-solid fa-check-double"></i></div>
                        <h4 style="color: #0f172a; font-weight: 800; margin-bottom: 0.5rem; font-size: 1.1rem;">Attendance Completed</h4>
                        <p style="color: #64748b; font-size: 0.85rem;">Your shift for today has been securely recorded.</p>
                    </div>
                <% } %>

                <!-- Reusable GPS Scanning Animation (Hidden by default) -->
                <div id="<portlet:namespace/>scannerUI" class="gps-scanner">
                    <div class="radar-container">
                        <div class="radar-pin"><i class="fa-solid fa-location-dot"></i></div>
                        <div class="radar-ring"></div>
                        <div class="radar-ring"></div>
                        <div class="radar-ring"></div>
                    </div>
                    <div class="scanning-text">Acquiring GPS Signal...</div>
                    <p style="color: #94a3b8; font-size: 0.75rem; margin-top: 0.5rem;">Please allow location access if prompted.</p>
                </div>

            </div>
        </div>

        <!-- Right Column: History Table -->
        <div class="hrms-card" style="border-top: 5px solid #10b981;">
            <div class="card-header-accent">
                <i class="fa-solid fa-clock-rotate-left" style="color: #10b981; margin-right: 0.5rem;"></i> Attendance History
            </div>
            
            <div class="table-container">
                <liferay-ui:search-container total="<%= userAttendanceList.size() %>" emptyResultsMessage="No attendance records found for this period.">
                    <liferay-ui:search-container-results results="<%= userAttendanceList %>" />

                    <liferay-ui:search-container-row
                        className="com.hrms.attendance.model.Attendance"
                        modelVar="attendance"
                        keyProperty="attendanceId">

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
     * Triggers the GPS UI Animation, validates coordinates, and submits the respective form.
     */
    function validateGeofence(formId, latFieldId, lngFieldId, containerId, scannerId) {
        if (!navigator.geolocation) {
            alert("Geolocation is not supported by your browser. Please update your browser or check settings.");
            return;
        }

        // 1. Hide Action Buttons & Show GPS Radar Animation
        document.getElementById(containerId).style.display = 'none';
        document.getElementById(scannerId).style.display = 'flex';

        // 2. Fetch High-Accuracy GPS Data
        navigator.geolocation.getCurrentPosition(
            function(position) {
                const userLat = position.coords.latitude;
                const userLng = position.coords.longitude;
                
                // 3. Mathematical Geofence Validation
                const distance = getDistanceInMeters(userLat, userLng, CAMPUS_LAT, CAMPUS_LNG);
                
                if (distance <= MAX_DISTANCE_METERS) {
                    // Success: Inject coordinates and submit
                    document.getElementById(latFieldId).value = userLat;
                    document.getElementById(lngFieldId).value = userLng;
                    
                    // Optional: Slight delay so user sees the cool animation finishing
                    setTimeout(() => {
                        document.getElementById(formId).submit();
                    }, 800);
                    
                } else {
                    // Fail: Revert UI and show distance error
                    document.getElementById(scannerId).style.display = 'none';
                    document.getElementById(containerId).style.display = 'block';
                    alert("Geofence Error: You are " + Math.round(distance) + " meters away from the campus hub. You must be within " + MAX_DISTANCE_METERS + " meters to record attendance.");
                }
            },
            function(error) {
                // Fail: Revert UI and show permission error
                document.getElementById(scannerId).style.display = 'none';
                document.getElementById(containerId).style.display = 'block';
                alert("Location Error: We could not pinpoint your location. Please ensure GPS is enabled and browser permissions are granted.");
            },
            { enableHighAccuracy: true, timeout: 10000, maximumAge: 0 }
        );
    }
</script>