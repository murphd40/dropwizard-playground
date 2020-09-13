package playground.api.exceptionmapper;

import io.dropwizard.jersey.errors.ErrorMessage;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import playground.exception.ResourceNotFoundException;

public class ResourceNotFoundExceptionMapper implements ExceptionMapper<ResourceNotFoundException> {

  @Override
  public Response toResponse(ResourceNotFoundException exception) {
    return Response.status(Status.NOT_FOUND)
        .entity(new ErrorMessage(Status.NOT_FOUND.getStatusCode(), exception.getMessage()))
        .build();
  }
}
