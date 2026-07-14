package mvc.render;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;

import jakarta.portlet.RenderRequest;
import jakarta.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

@Component(
    property = {
        "jakarta.portlet.name=ai_assistance_web_AiAssistanceWebPortlet",
        "mvc.command.name=/"
    },
    service = MVCRenderCommand.class
)
public class ViewDocumentsMVCRenderCommand implements MVCRenderCommand {

    @Override
    public String render(
        RenderRequest renderRequest, RenderResponse renderResponse) {

        return "/view.jsp";
    }

}