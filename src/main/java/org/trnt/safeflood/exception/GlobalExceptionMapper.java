package org.trnt.safeflood.exception;

import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.jboss.logging.Logger;

@Provider
public class GlobalExceptionMapper implements ExceptionMapper<Exception> {

    private static final Logger LOG = Logger.getLogger(GlobalExceptionMapper.class);

    @Override
    public Response toResponse(Exception exception) {
        if (exception instanceof NotFoundException) {
            LOG.debug("Recurso não encontrado: " + exception.getMessage());
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new ErrorResponse("Recurso não encontrado"))
                    .build();
        }

        if (exception instanceof ResourceNotFoundException) {
            LOG.debug("Recurso não encontrado: " + exception.getMessage());
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new ErrorResponse(exception.getMessage()))
                    .build();
        }

        if (exception instanceof BusinessException) {
            LOG.debug("Erro de negócio: " + exception.getMessage());
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ErrorResponse(exception.getMessage()))
                    .build();
        }

        LOG.error("Erro interno do servidor: ", exception);
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(new ErrorResponse("Erro interno do servidor"))
                .build();
    }
} 