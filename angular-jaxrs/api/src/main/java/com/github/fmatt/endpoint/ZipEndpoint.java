package com.github.fmatt.endpoint;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.StreamingOutput;

import java.io.BufferedOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Path("zip")
public class ZipEndpoint {

    Logger logger = Logger.getLogger(this.getClass().getName());

    @GET
    public Response getZipFile() {
        try {
            StringBuilder file1 = new StringBuilder();
            file1.append("ABC\n");
            file1.append("DEF\n");
            file1.append("GHI\n");

            StringBuilder file2 = new StringBuilder();
            file2.append("111\n");
            file2.append("222\n");
            file2.append("333\n");

            StreamingOutput streamingOutput = getStreamingOutput(file1, file2);

            return Response
                    .ok(streamingOutput)
                    .header(HttpHeaders.CONTENT_TYPE, "application/zip")
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"files.zip\"")
                    .build();
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);

            return Response.status(Response.Status.BAD_REQUEST).entity("Error retrieving file.").build();
        }
    }

    private static StreamingOutput getStreamingOutput(StringBuilder file1, StringBuilder file2) {
        return outputStream -> {
            ZipOutputStream zipOutputStream = new ZipOutputStream(new BufferedOutputStream(outputStream));

            ZipEntry zipEntry1 = new ZipEntry("file1.txt");
            zipOutputStream.putNextEntry(zipEntry1);
            zipOutputStream.write(file1.toString().getBytes(StandardCharsets.UTF_8));
            zipOutputStream.closeEntry();

            ZipEntry zipEntry2 = new ZipEntry("file2.txt");
            zipOutputStream.putNextEntry(zipEntry2);
            zipOutputStream.write(file2.toString().getBytes(StandardCharsets.UTF_8));
            zipOutputStream.closeEntry();

            zipOutputStream.close();
            outputStream.flush();
            outputStream.close();
        };
    }

}
