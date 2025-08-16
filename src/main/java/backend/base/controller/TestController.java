package backend.base.controller;

import backend.base.domain.response.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
    
    @GetMapping("/hello")
    public String test() {
        return "Hello World from HaiDaiPhat Backend!";
    }
    
    @GetMapping("/info")
    public ApiResponse<String> getInfo() {
        ApiResponse<String> response = new ApiResponse<>();
        response.setCode("200");
        response.setMessage("Success");
        response.setData("HaiDaiPhat Backend API v1.0.0");
        return response;
    }
}
