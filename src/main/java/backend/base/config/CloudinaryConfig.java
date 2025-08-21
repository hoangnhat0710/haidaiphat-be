package backend.base.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {
    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
            "cloud_name", "djrhyx8mi",
            "api_key", "254533582432618",
            "api_secret", "UsJrJLSfQVJAmgQzlPEaTBRyPlQ"
        ));
    }
} 