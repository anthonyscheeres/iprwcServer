package main.java.nl.iipsen2server.resources;

import main.java.nl.iipsen2server.controlllers.PhaseController;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

@Path("/ProjectPhase")
public class PhaseResource {


    PhaseController phaseController = new PhaseController();

/**
* @author Jesse Poleij
*
*/

    @POST
    @Path("/Phase")
    @Consumes(MediaType.APPLICATION_JSON)
    public void ChangePhase() {

    }
}
