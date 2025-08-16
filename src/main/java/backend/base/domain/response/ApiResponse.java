package backend.base.domain.response;

public class ApiResponse<T> {

    private String code;

    private String message;

    private T data;
    
    // Constructors
    public ApiResponse() {}
    
    public ApiResponse(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
    
    // Getters and Setters
    public String getCode() {
        return code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public T getData() {
        return data;
    }
    
    public void setData(T data) {
        this.data = data;
    }
    
    // Builder pattern
    public static <T> ApiResponseBuilder<T> builder() {
        return new ApiResponseBuilder<>();
    }
    
    public static class ApiResponseBuilder<T> {
        private String code;
        private String message;
        private T data;
        
        public ApiResponseBuilder<T> code(String code) {
            this.code = code;
            return this;
        }
        
        public ApiResponseBuilder<T> message(String message) {
            this.message = message;
            return this;
        }
        
        public ApiResponseBuilder<T> data(T data) {
            this.data = data;
            return this;
        }
        
        public ApiResponse<T> build() {
            return new ApiResponse<>(code, message, data);
        }
    }

}
