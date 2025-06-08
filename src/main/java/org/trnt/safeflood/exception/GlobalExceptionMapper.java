package org.trnt.safeflood.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.jboss.logging.Logger;

@Provider
public class GlobalExceptionMapper implements ExceptionMapper<Exception> {

    private static final Logger LOG = Logger.getLogger(GlobalExceptionMapper.class);

    @Override
    public Response toResponse(Exception exception) {
        LOG.error("Erro não tratado: ", exception);

        if (exception instanceof ResourceNotFoundException) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new ErrorResponse(exception.getMessage()))
                    .build();
        }

        if (exception instanceof BusinessException) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ErrorResponse(exception.getMessage()))
                    .build();
        }

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(new ErrorResponse("Erro interno do servidor"))
                .build();
    }
} 