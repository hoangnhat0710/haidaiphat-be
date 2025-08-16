package backend.base.config.exception;

import backend.base.domain.exception.ServiceException;
import backend.base.domain.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<ApiResponse<Object>> handleServiceException(ServiceException ex) {
        return ResponseEntity
                .status(ex.getHttpStatus())
                .body(ApiResponse.builder()
                        .code(ex.getHttpStatus().toString())
                        .message(ex.getMessage())
                        .data(null)
                        .build());
    }
} 