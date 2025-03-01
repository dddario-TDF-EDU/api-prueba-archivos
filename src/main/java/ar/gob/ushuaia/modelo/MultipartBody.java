package ar.gob.ushuaia.modelo;


import java.io.InputStream;

import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.core.MediaType;

//import org.jboss.resteasy.annotations.providers.multipart.PartType;
import org.jboss.resteasy.reactive.PartType;
//import org.jboss.resteasy.reactive.PartType;


public class MultipartBody {

    @FormParam("file")
    @PartType(MediaType.APPLICATION_OCTET_STREAM)
    public InputStream file;

    @FormParam("fileName")
    @PartType(MediaType.TEXT_PLAIN)
    public String fileName;
}