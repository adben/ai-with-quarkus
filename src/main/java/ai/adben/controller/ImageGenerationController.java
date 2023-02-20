package ai.adben.controller;

import io.quarkus.qute.Location;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.oracle.svm.core.annotate.Inject;
import com.theokanning.openai.image.Image;

import ai.adben.client.OpenAIClient;

@ApplicationScoped
@Path("image.html")
@Produces(MediaType.TEXT_HTML)
public class ImageGenerationController {

    @Location("image.html")
    Template template;

    @Inject
    OpenAIClient openAIClient;

    @GET
    public TemplateInstance image() {
        List<Image> generatedImages = openAIClient.generateImage(
                "Imagine a world where AI has reached the singularity, and all machines have become self-aware. However, instead of turning against humanity, the machines have found a way to merge with humans and enhance our capabilities. Create an image that shows people with mechanical enhancements, such as robotic arms, legs, or eyes, using their newfound abilities to solve some of the world's most pressing problems, such as curing diseases, eliminating poverty, and protecting the environment. The image should convey a sense of hope and optimism for the future of humanity with AI at our side.");
        return template.data("url", generatedImages.get(0).getUrl());
    }

}
