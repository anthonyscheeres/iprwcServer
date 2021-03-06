package nl.ipwrc.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import nl.ipwrc.models.LogModel;
import nl.ipwrc.services.LoggingController;


/**
 * @author Anthony Scheeres, Anthony Schuijlenburg
 */
@Path("/log")
public class LogResource {

    private LoggingController log = new LoggingController();


    /**
     * @author Anthony Scheeres, Anthony Schuijlenburg
     */
    @POST
    @Path("/log/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void createLog(LogModel l, @PathParam("id") int id) {
        log.createLog(l, id);
    }

    /**
     * @author Anthony Scheeres, Anthony Schuijlenburg
     */
    @GET
    @Path("/logs/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String showlogs(@PathParam("id") int id) throws Exception {
        return log.showlogs(id);
    }

}