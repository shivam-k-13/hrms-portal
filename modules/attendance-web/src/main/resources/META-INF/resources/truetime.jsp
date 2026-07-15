<%@ include file="/init.jsp" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>

<%
    String currentMonthName = (String) request.getAttribute("currentMonthName");
    String monthAvg = (String) request.getAttribute("monthAvg");
    List<Map<String, Object>> dailyLogs = (List<Map<String, Object>>) request.getAttribute("dailyLogs");
%>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" />
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700;800;900&display=swap" />

<style type="text/css">
    /* --- CORE HRMS CONSISTENT UI --- */
    .hrms-attendance-wrapper {
        background: #f8fafc;
        font-family: "Inter", sans-serif;
        font-size: 15px;
        padding: 2rem;
        min-height: calc(100vh - 80px);
    }

    /* Gradient Page Header (Matches Leave Module) */
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
    .page-header h2 { font-size: 1.8rem; font-weight: 900; margin: 0 0 0.25rem; color: white; }
    .page-header p { margin: 0; font-size: 0.95rem; color: #eaf4fc; font-weight: 500; }
    .header-icon { font-size: 3rem; opacity: 0.9; }

    /* Glass Card (Matches Leave Module) */
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
        display: flex;
        justify-content: space-between;
        align-items: center;
    }
    .card-body-padded { padding: 1.5rem; }

    /* --- TRUETIME GRAPH SPECIFIC UI --- */
    .tt-month-nav { display: flex; align-items: center; gap: 15px; font-size: 1.2rem; font-weight: 800; color: #0f172a; }
    .tt-month-nav i { color: #0088cc; cursor: pointer; transition: 0.2s; }
    .tt-month-nav i:hover { transform: scale(1.2); }
    
    .tt-stat-box { text-align: right; }
    .tt-stat-box p { margin: 0; font-size: 0.75rem; color: #64748b; font-weight: 700; text-transform: uppercase; }
    .tt-stat-box h3 { margin: 0; font-size: 1.4rem; font-weight: 900; color: #4d7c0f; }

    /* Graph Container */
    .tt-graph-container { display: flex; align-items: flex-end; height: 250px; padding-top: 30px; border-bottom: 2px solid #e2e8f0; margin-bottom: 5px; position: relative; }
    .tt-axis-line { position: absolute; width: 100%; height: 1px; background: #cbd5e1; top: 35%; left: 0; z-index: 1; border-top: 1px dashed #94a3b8;}
    .tt-axis-label { position: absolute; top: calc(35% - 22px); left: 0; font-size: 0.75rem; color: #64748b; font-weight: 800; z-index: 2;}

    .tt-day-col { flex: 1; display: flex; flex-direction: column; align-items: center; justify-content: flex-end; height: 100%; z-index: 5;}
    
    /* The Bar */
    .tt-bar { width: 12px; border-radius: 4px 4px 0 0; transition: 0.3s; cursor: pointer; min-height: 2px;}
    .tt-bar:hover { opacity: 0.8; transform: scaleY(1.05); }
    .tt-bar-excellent { background: #4d7c0f; } /* 10+ hours */
    .tt-bar-good { background: #84cc16; } /* 9-10 hours */
    .tt-bar-low { background: #ef4444; } /* < 7 hours */
    .tt-bar-empty { background: transparent; }

    /* X-Axis Dates */
    .tt-date-labels { display: flex; text-align: center; font-size: 0.75rem; font-weight: 700; color: #475569; }
    .tt-date-col { flex: 1; border-right: 1px solid #f1f5f9; display: flex; flex-direction: column;}
    .tt-date-day { padding: 6px 0; background: #f8fafc; border-bottom: 1px solid #e2e8f0;}
    .tt-date-name { padding: 6px 0; color: white; background: #3b82f6; } /* Liferay Blue */
    .tt-weekend .tt-date-day { background: #f1f5f9; color: #94a3b8; }
    .tt-weekend .tt-date-name { background: #1e3a8a; } /* Dark Blue for weekends */

    /* Legend */
    .tt-legend { display: flex; flex-wrap: wrap; gap: 1rem; margin-top: 1.5rem; font-size: 0.8rem; font-weight: 700; color: #475569; align-items: center; padding: 1rem; background: #f8fafc; border-radius: 12px; border: 1px solid #e2e8f0;}
    .tt-legend-item { display: flex; align-items: center; gap: 6px; }
    .tt-legend-box { width: 14px; height: 14px; border-radius: 4px; }
</style>

<div class="hrms-attendance-wrapper">

    <!-- Unified HRMS Header -->
    <header class="page-header">
        <div>
            <h2>TrueTime Attendance</h2>
            <p>Track your daily logs, shift compliance, and weekly averages.</p>
        </div>
        <div class="header-icon"><i class="fa-solid fa-clock-rotate-left"></i></div>
    </header>

    <!-- Unified HRMS Glass Card (Green Accent for Attendance) -->
    <div class="hrms-card" style="border-top: 5px solid #84cc16;">
        
        <div class="card-header-accent">
            <div class="tt-month-nav">
                <i class="fa-solid fa-chevron-left"></i>
                <span><%= currentMonthName != null ? currentMonthName : "Current Month" %> <i class="fa-regular fa-calendar" style="margin-left: 5px; color:#64748b; font-size: 1rem;"></i></span>
                <i class="fa-solid fa-chevron-right" style="color: #cbd5e1; cursor: default;"></i>
            </div>
            <div class="tt-stat-box">
                <p>Month Avg.</p>
                <h3><%= monthAvg != null ? monthAvg : "0h 0m" %></h3>
            </div>
        </div>

        <div class="card-body-padded">
            <!-- Graphical Bar Chart -->
            <div class="tt-graph-container">
                <div class="tt-axis-line"></div>
                <div class="tt-axis-label"><i class="fa-solid fa-caret-up" style="color: #84cc16; margin-right: 4px;"></i>9hr Target</div>
                
                <% if(dailyLogs != null) { 
                    for(Map<String, Object> day : dailyLogs) {
                        int workedMins = (Integer) day.get("workedMinutes");
                        // Calculate bar height relative to a max of 12 hours (720 mins)
                        int heightPercentage = workedMins > 0 ? Math.min((workedMins * 100) / 720, 100) : 0;
                        
                        String barClass = "tt-bar-empty";
                        if (workedMins >= 600) barClass = "tt-bar-excellent"; // 10+ hours
                        else if (workedMins >= 540) barClass = "tt-bar-good"; // 9-10 hours
                        else if (workedMins > 0 && workedMins < 420) barClass = "tt-bar-low"; // < 7 hours
                %>
                <div class="tt-day-col" title="<%= day.get("day") + " " + currentMonthName %>: <%= (workedMins/60) + "h " + (workedMins%60) + "m" %>">
                    <div class="tt-bar <%= barClass %>" style="height: <%= heightPercentage %>%;"></div>
                </div>
                <% }} %>
            </div>

            <!-- X-Axis Date Strip -->
            <div class="tt-date-labels">
                <% if(dailyLogs != null) { 
                    for(Map<String, Object> day : dailyLogs) {
                        boolean isWeekend = (Boolean) day.get("isWeekend");
                %>
                <div class="tt-date-col <%= isWeekend ? "tt-weekend" : "" %>">
                    <span class="tt-date-day"><%= day.get("day") %></span>
                    <span class="tt-date-name"><%= day.get("dayOfWeek") %></span>
                </div>
                <% }} %>
            </div>

            <!-- Graph Legends -->
            <div class="tt-legend">
                <strong style="color: #0f172a;"><i class="fa-solid fa-layer-group" style="color:#0088cc; margin-right: 4px;"></i> Legend:</strong>
                <div class="tt-legend-item"><div class="tt-legend-box tt-bar-excellent"></div> 10+ Hrs</div>
                <div class="tt-legend-item"><div class="tt-legend-box tt-bar-good"></div> 9-10 Hrs</div>
                <div class="tt-legend-item"><div class="tt-legend-box" style="background: #f59e0b;"></div> 7-9 Hrs</div>
                <div class="tt-legend-item"><div class="tt-legend-box tt-bar-low"></div> &lt;7 Hrs</div>
                <div class="tt-legend-item"><div class="tt-legend-box" style="background: #e2e8f0; border: 1px dashed #cbd5e1;"></div> No Time Log</div>
            </div>
        </div>

    </div>
</div>