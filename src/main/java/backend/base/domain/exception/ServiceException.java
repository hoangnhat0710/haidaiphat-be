package backend.base.domain.exception;

import org.springframework.http.HttpStatus;

public class ServiceException extends RuntimeException{

    private String message;
    private HttpStatus httpStatus;

    public ServiceException() {

    }

    public ServiceException(String message,  HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public String generateStringResponse() {
        return String.format("Error: %s, Status code : %s", message, httpStatus.name());
    }
    
    // Getters and Setters
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
    
    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

}
