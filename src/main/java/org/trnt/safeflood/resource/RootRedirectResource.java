package org.trnt.safeflood.resource;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/")
public class RootRedirectResource {

    @GET
    public Response redirect() {
        return Response.status(Response.Status.FOUND)
                .header("Location", "/q/dev-ui/welcome")
                .build();
    }
}
