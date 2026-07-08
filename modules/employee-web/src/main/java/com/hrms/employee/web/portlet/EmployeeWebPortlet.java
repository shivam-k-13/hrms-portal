package com.hrms.employee.web.portlet;

import com.hrms.employee.model.Employee;
import com.hrms.employee.service.EmployeeLocalService;
import com.hrms.employee.web.constants.EmployeeWebPortletKeys;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import java.io.IOException;
import java.util.List;

import jakarta.portlet.Portlet;
import jakarta.portlet.PortletException;
import jakarta.portlet.RenderRequest;
import jakarta.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
    property = {
        "com.liferay.portlet.display-category=category.sample",
        "com.liferay.portlet.header-portlet-css=/css/main.css",
        "com.liferay.portlet.instanceable=true",
        "jakarta.portlet.display-name=EmployeeWeb",
        "jakarta.portlet.init-param.template-path=/",
        "jakarta.portlet.init-param.view-template=/view.jsp",
        "jakarta.portlet.name=" + EmployeeWebPortletKeys.EMPLOYEEWEB,
        "jakarta.portlet.resource-bundle=content.Language",
        "jakarta.portlet.security-role-ref=power-user,user"
    },
    service = Portlet.class
)
public class EmployeeWebPortlet extends MVCPortlet {

    @Override
    public void render(
            RenderRequest renderRequest,
            RenderResponse renderResponse)
        throws IOException, PortletException {

        List<Employee> employees =
            _employeeLocalService.getEmployees(-1, -1);

        renderRequest.setAttribute("employees", employees);

        super.render(renderRequest, renderResponse);
    }

    @Reference
    private EmployeeLocalService _employeeLocalService;

}