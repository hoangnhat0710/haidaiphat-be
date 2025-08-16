package backend.base.controller;



import backend.base.domain.handler.ServiceExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice()
public class ApiExceptionHandler extends ServiceExceptionHandler {
}

