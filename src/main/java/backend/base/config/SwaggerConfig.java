package backend.base.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class SwaggerConfig {
    
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("HaiDaiPhat Backend API")
                        .description("REST API documentation for HaiDaiPhat Backend Platform")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("HaiDaiPhat Team")
                                .url("https://haidaiphat.com")
                                .email("info@haidaiphat.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://www.apache.org/licenses/LICENSE-2.0")))
                .servers(Arrays.asList(
                        new Server().url("https://haidaiphatebm.vn/api").description("Production Server"),
                        new Server().url("http://localhost:8080/api").description("Local Development Server")
                ));
    }
} 