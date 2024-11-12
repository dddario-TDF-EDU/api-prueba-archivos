package ar.gob.ushuaia.recurso;

//import ar.gob.ushuaia.modelo.MultipartBody;
import ar.gob.ushuaia.servicio.ServicioCarpetas;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
//import org.jboss.resteasy.reactive.RestPath;

import java.io.InputStream;

import static jakarta.ws.rs.core.Response.Status.*;

@RequestScoped
@Path("/subir-archivos")
public class RecursoArchivos {

    @Inject
    ServicioCarpetas servicioCarpetas;

    @Path("/{nombre}")
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.TEXT_PLAIN)
    public Response crearCarpeta(@PathParam("nombre") String nombre, @FormParam("file") InputStream archivo,
                                 @FormParam("fileName") String fileName) {
        String carpetaCreada = servicioCarpetas.crearCarpeta(nombre);
        System.out.println("Carpeta terminada");
        if (carpetaCreada == null) {
            return Response.status(INTERNAL_SERVER_ERROR).build();
        } else {
            return servicioCarpetas.guardarArchivo(fileName, archivo, carpetaCreada);
        }
    }
}
