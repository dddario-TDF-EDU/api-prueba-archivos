package ar.gob.ushuaia.servicio;

//import ar.gob.ushuaia.modelo.MultipartBody;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.core.Response;

//import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RequestScoped
public class ServicioCarpetas {

    public String crearCarpeta(String nombre) {
        try {
            Path path = Paths.get("/home/dario/Proyecto/prueba-carpetas/carpetas/" + nombre);
            if (!Files.exists(path)) {
                System.out.println("Carpeta creada");
                Files.createDirectory(path);
            }
            return path.toString();
        } catch (Exception e) {
            return null;
        }
    }

    public Response guardarArchivo(String nombreArchivo, InputStream archivo, String path) {
        Path directorioDestino = Path.of(path);
        Path archivoDestino = directorioDestino.resolve(nombreArchivo);
        System.out.println("llegue aca");

        // Escribir el archivo en el disco
        try (FileOutputStream outputStream = new FileOutputStream(archivoDestino.toFile())) {
            byte[] buffer = new byte[8192];
            int bytesRead;
            while ((bytesRead = archivo.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            System.out.println("llegue aca tmb");
            return Response.status(Response.Status.CREATED).build();
        } catch (IOException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al guardar el archivo.")
                    .build();
        }
    }

}
