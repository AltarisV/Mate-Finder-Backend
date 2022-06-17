package htw.berlin.webtech.matefinder.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    /**
     * Configure "global" cross origin request processing. The configured CORS
     * mappings apply to annotated controllers, functional endpoints, and static
     * resources.
     * <p>Annotated controllers can further declare more fine-grained config via
     * {@link CrossOrigin @CrossOrigin}.
     * In such cases "global" CORS configuration declared here is
     * {@link CorsConfiguration#combine(CorsConfiguration) combined}
     * with local CORS configuration defined on a controller method.
     *
     * @param registry Does things
     * @see CorsRegistry
     * @see CorsConfiguration#combine(CorsConfiguration)
     * @since 4.2
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("*")
                .allowedOrigins("http://localhost:8080",
                                "https://drinkmate-finder.herokuapp.com"
                );
    }
}
