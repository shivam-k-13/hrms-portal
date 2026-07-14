<%@ include file="/init.jsp" %>

<%@ page import="com.hrms.leave.model.LeaveRequest" %>
<%@ page import="com.hrms.leave.service.LeaveRequestLocalServiceUtil" %>

<%@ page import="com.liferay.portal.kernel.util.HtmlUtil" %>

<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.List" %>

<portlet:actionURL name="/leave/apply" var="applyLeaveURL" />
<portlet:actionURL name="/leave/approve" var="approveLeaveURL" />
<portlet:actionURL name="/leave/reject" var="rejectLeaveURL" />

<%
	DateFormat leaveDateFormat = new SimpleDateFormat(
		"dd-MMM-yyyy", themeDisplay.getLocale());

	leaveDateFormat.setTimeZone(themeDisplay.getTimeZone());
%>

<div class="leave-web">
	<div class="leave-management">
		<div class="leave-management__container">

			<%-- Page Header --%>

			<div class="leave-page-header">
				<div class="leave-page-header__icon">
					<clay:icon symbol="calendar" />
				</div>

				<div>
					<h1 class="leave-page-header__title">
						<liferay-ui:message key="leave-management" />
					</h1>

					<p class="leave-page-header__subtitle">
						<liferay-ui:message
							key="apply-for-leave-and-track-your-requests"
						/>
					</p>
				</div>
			</div>

			<%-- Success Messages --%>

			<liferay-ui:success
				key="leave-request-submitted"
				message="leave-request-submitted-successfully"
			/>

			<liferay-ui:success
				key="leave-request-approved"
				message="leave-request-approved-successfully"
			/>

			<liferay-ui:success
				key="leave-request-rejected"
				message="leave-request-rejected-successfully"
			/>

			<%-- Error Messages --%>

			<liferay-ui:error
				key="leave-request-error"
				message="unable-to-process-leave-request"
			/>

			<liferay-ui:error
				key="invalid-leave-request"
				message="please-check-the-entered-leave-information"
			/>

			<liferay-ui:error
				key="invalid-leave-status"
				message="only-pending-leave-requests-can-be-processed"
			/>

			<liferay-ui:error
				key="rejection-comment-required"
				message="rejection-comment-is-required"
			/>

			<div class="leave-content-grid">

				<%-- Apply Leave Card --%>

				<section class="leave-card leave-application-card">
					<div class="leave-card__header">
						<div>
							<h2 class="leave-card__title">
								<clay:icon symbol="pencil" />

								<span>
									<liferay-ui:message key="apply-leave" />
								</span>
							</h2>

							<p class="leave-card__description">
								<liferay-ui:message
									key="complete-the-form-to-submit-a-leave-request"
								/>
							</p>
						</div>
					</div>

					<div class="leave-card__body">
						<aui:form action="<%= applyLeaveURL %>" cssClass="leave-application-form" method="post" name="leaveForm">
							<%-- Temporary Employee Information --%>

							<div class="employee-preview">
								<div class="employee-preview__icon">
									<clay:icon symbol="user" />
								</div>

								<div>
									<div class="employee-preview__title">
										<liferay-ui:message
											key="employee-information"
										/>
									</div>

									<div class="employee-preview__description">
										<liferay-ui:message
											key="employee-auto-identification-will-be-added-next"
										/>
									</div>
								</div>
							</div>

							<%-- Temporary Employee ID field.
								This will be removed after Employee Service integration.
							--%>

							<aui:input
								label="employee-id"
								min="1"
								name="employeeId"
								required="<%= true %>"
								type="number"
							>
								<aui:validator name="required" />
								<aui:validator name="digits" />
								<aui:validator name="min">1</aui:validator>
							</aui:input>

							<div class="leave-form-grid">
								<div>
									<aui:select
										label="leave-type"
										name="leaveType"
										required="<%= true %>"
									>
										<aui:option
											disabled="<%= true %>"
											selected="<%= true %>"
											value=""
										>
											<liferay-ui:message
												key="select-leave-type"
											/>
										</aui:option>

										<aui:option value="CASUAL">
											<liferay-ui:message
												key="casual-leave"
											/>
										</aui:option>

										<aui:option value="SICK">
											<liferay-ui:message
												key="sick-leave"
											/>
										</aui:option>

										<aui:option value="EARNED">
											<liferay-ui:message
												key="earned-leave"
											/>
										</aui:option>

										<aui:option value="LOP">
											<liferay-ui:message
												key="loss-of-pay"
											/>
										</aui:option>

										<aui:validator name="required" />
									</aui:select>
								</div>

								<div>
									<aui:input
										label="from-date"
										name="fromDate"
										required="<%= true %>"
										type="date"
									>
										<aui:validator name="required" />
									</aui:input>
								</div>

								<div>
									<aui:input
										label="to-date"
										name="toDate"
										required="<%= true %>"
										type="date"
									>
										<aui:validator name="required" />
									</aui:input>
								</div>

								<div>
									<label
										class="control-label"
										for="<portlet:namespace />totalDays"
									>
										<liferay-ui:message key="total-days" />
									</label>

									<div class="total-days-field">
										<span
											id="<portlet:namespace />totalDays"
										>
											0
										</span>

										<span>
											<liferay-ui:message key="days" />
										</span>
									</div>
								</div>
							</div>

							<aui:input
								label="reason"
								maxlength="500"
								name="reason"
								placeholder="briefly-explain-the-reason-for-your-leave"
								required="<%= true %>"
								type="textarea"
							>
								<aui:validator name="required" />

								<aui:validator name="maxLength">
									500
								</aui:validator>
							</aui:input>

							<p class="leave-field-help">
								<liferay-ui:message
									key="leave-reason-help-text"
								/>
							</p>

							<div class="leave-form-actions">
								<aui:button
									cssClass="btn btn-secondary"
									type="reset"
									value="reset"
								/>

								<aui:button
									cssClass="btn btn-primary"
									type="submit"
									value="apply-leave"
								/>
							</div>
						</aui:form>
					</div>
				</section>

				<%-- Leave Requests Card --%>

				<section class="leave-card leave-requests-card">
					<div
						class="leave-card__header leave-card__header--requests"
					>
						<div>
							<h2 class="leave-card__title">
								<clay:icon symbol="list-ul" />

								<span>
									<liferay-ui:message
										key="leave-requests"
									/>
								</span>
							</h2>

							<p class="leave-card__description">
								<liferay-ui:message
									key="review-and-manage-leave-requests"
								/>
							</p>
						</div>
					</div>

					<div class="leave-card__body leave-card__body--table">
						<div class="leave-table-scroll">
							<liferay-ui:search-container
								delta="<%= 10 %>"
								deltaConfigurable="<%= true %>"
								emptyResultsMessage="no-leave-requests-found"
								total="<%= LeaveRequestLocalServiceUtil.getLeaveRequestsCount() %>"
							>
								<%
									List<LeaveRequest> leaveRequests =
										LeaveRequestLocalServiceUtil.getLeaveRequests(
											searchContainer.getStart(),
											searchContainer.getEnd());
								%>

								<liferay-ui:search-container-results
									results="<%= leaveRequests %>"
								/>

								<liferay-ui:search-container-row
									className="com.hrms.leave.model.LeaveRequest"
									keyProperty="leaveRequestId"
									modelVar="leaveRequest"
								>
									<%
										String status = leaveRequest.getStatus();

										if (status == null) {
											status = "";
										}

										String statusCssClass =
											"leave-status--default";

										if ("PENDING".equals(status)) {
											statusCssClass =
												"leave-status--pending";
										}
										else if ("APPROVED".equals(status)) {
											statusCssClass =
												"leave-status--approved";
										}
										else if ("REJECTED".equals(status)) {
											statusCssClass =
												"leave-status--rejected";
										}

										String formattedFromDate = "-";

										if (leaveRequest.getFromDate() != null) {
											formattedFromDate =
												leaveDateFormat.format(
													leaveRequest.getFromDate());
										}

										String formattedToDate = "-";

										if (leaveRequest.getToDate() != null) {
											formattedToDate =
												leaveDateFormat.format(
													leaveRequest.getToDate());
										}

										long numberOfDays = 0;

										if ((leaveRequest.getFromDate() != null) &&
											(leaveRequest.getToDate() != null) &&
											!leaveRequest.getToDate().before(
												leaveRequest.getFromDate())) {

											long difference =
												leaveRequest.getToDate().getTime() -
													leaveRequest.getFromDate().getTime();

											numberOfDays =
												(difference /
													(24L * 60L * 60L * 1000L)) +
														1;
										}

										String leaveReason =
											leaveRequest.getReason();

										if (leaveReason == null) {
											leaveReason = "";
										}

										String leaveType =
											leaveRequest.getLeaveType();

										if (leaveType == null) {
											leaveType = "";
										}
									%>

									<liferay-ui:search-container-column-text
										name="request-id"
									>
										<span class="leave-request-id">
											#<%= leaveRequest.getLeaveRequestId() %>
										</span>
									</liferay-ui:search-container-column-text>

									<liferay-ui:search-container-column-text
										name="employee-id"
										value="<%= String.valueOf(leaveRequest.getEmployeeId()) %>"
									/>

									<liferay-ui:search-container-column-text
										name="leave-type"
									>
										<span class="leave-type-badge">
											<%= HtmlUtil.escape(leaveType) %>
										</span>
									</liferay-ui:search-container-column-text>

									<liferay-ui:search-container-column-text
										name="from-date"
									>
										<div class="leave-date-cell">
											<strong>
												<%= formattedFromDate %>
											</strong>
										</div>
									</liferay-ui:search-container-column-text>

									<liferay-ui:search-container-column-text
										name="to-date"
									>
										<div class="leave-date-cell">
											<strong>
												<%= formattedToDate %>
											</strong>
										</div>
									</liferay-ui:search-container-column-text>

									<liferay-ui:search-container-column-text
										name="days"
										value="<%= String.valueOf(numberOfDays) %>"
									/>

									<liferay-ui:search-container-column-text
										name="reason"
									>
										<div
											class="leave-reason"
											title="<%= HtmlUtil.escapeAttribute(leaveReason) %>"
										>
											<%= HtmlUtil.escape(leaveReason) %>
										</div>
									</liferay-ui:search-container-column-text>

									<liferay-ui:search-container-column-text
										name="status"
									>
										<span
											class="leave-status <%= statusCssClass %>"
										>
											<%= HtmlUtil.escape(status) %>
										</span>
									</liferay-ui:search-container-column-text>
								</liferay-ui:search-container-row>

								<liferay-ui:search-iterator
									displayStyle="list"
									markupView="lexicon"
								/>
							</liferay-ui:search-container>
						</div>
					</div>
				</section>
			</div>

			<%-- Information Banner --%>

			<div class="leave-information-banner">
				<clay:icon symbol="info-circle" />

				<span>
					<liferay-ui:message
						key="leave-application-policy-notice"
					/>
				</span>
			</div>
		</div>
	</div>
</div>

<aui:script>
	const fromDateInput = document.getElementById(
		'<portlet:namespace />fromDate'
	);

	const toDateInput = document.getElementById(
		'<portlet:namespace />toDate'
	);

	const totalDaysElement = document.getElementById(
		'<portlet:namespace />totalDays'
	);

	function updateTotalDays() {
		if (!fromDateInput || !toDateInput || !totalDaysElement) {
			return;
		}

		if (!fromDateInput.value || !toDateInput.value) {
			totalDaysElement.textContent = '0';

			return;
		}

		const fromDate = new Date(fromDateInput.value + 'T00:00:00');
		const toDate = new Date(toDateInput.value + 'T00:00:00');

		if (
			Number.isNaN(fromDate.getTime()) ||
			Number.isNaN(toDate.getTime()) ||
			toDate < fromDate
		) {
			totalDaysElement.textContent = '0';

			return;
		}

		const millisecondsPerDay = 24 * 60 * 60 * 1000;

		const totalDays =
			Math.floor((toDate.getTime() - fromDate.getTime()) /
				millisecondsPerDay) + 1;

		totalDaysElement.textContent = String(totalDays);
	}

	function resetTotalDays() {
		window.setTimeout(() => {
			totalDaysElement.textContent = '0';
		}, 0);
	}

	if (fromDateInput && toDateInput && totalDaysElement) {
		fromDateInput.addEventListener('change', updateTotalDays);
		toDateInput.addEventListener('change', updateTotalDays);

		const leaveForm = document.getElementById(
			'<portlet:namespace />leaveForm'
		);

		if (leaveForm) {
			leaveForm.addEventListener('reset', resetTotalDays);
		}

		updateTotalDays();
	}
</aui:script>