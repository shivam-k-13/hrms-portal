package leave.web.portlet;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import jakarta.portlet.Portlet;

import org.osgi.service.component.annotations.Component;

/**
 * @author Mayank
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.hrms",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=false",
		"jakarta.portlet.display-name=Leave Approvals",
		"jakarta.portlet.init-param.template-path=/",
		"jakarta.portlet.init-param.view-template=/approvals.jsp",
		"jakarta.portlet.name=leave_web_approvals_portlet",
		"jakarta.portlet.resource-bundle=content.Language",
		"jakarta.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class LeaveApprovalsPortlet extends MVCPortlet {
}