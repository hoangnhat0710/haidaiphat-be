package backend.base.domain.handler;

import backend.base.domain.exception.ServiceException;
import backend.base.domain.utils.ServiceExceptionUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ServiceExceptionHandler {

    private static HttpHeaders header;

    @ExceptionHandler({Exception.class})
    protected ResponseEntity<Object> handleRuntimeException(final Exception ex) {
        ex.printStackTrace();
        return new ResponseEntity<>(ServiceExceptionUtils.internalServerError().generateStringResponse(), header, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({ServiceException.class})
    protected Object handleServiceException(HttpServletRequest req, HttpServletResponse res, final ServiceException ex) throws IOException {
        HttpStatus httpStatus = ex.getHttpStatus();
        String response = ex.getMessage();
        return new ResponseEntity<>(response, header, httpStatus);
    }
}
