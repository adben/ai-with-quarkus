package ai.adben.controller;

import io.quarkus.qute.Location;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@ApplicationScoped
@Path("image.html")
@Produces(MediaType.TEXT_HTML)
public class ImageGenerationController {

    @Location("image.html")
    Template template;

    @GET
    public TemplateInstance image() {
        return template.data("url", "result.toString()");
    }

}
