package co.com.sofka.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;


@Configuration
public class RouterRest {
    @Bean
    public RouterFunction<ServerResponse> routerFunction(Handler handler) {
        return route(POST("/api/pet/create"), handler::listenPOSTCreatePetUseCase)
                .andRoute(GET("/api/pet/all"), handler::listenGETAllPetsUseCase)
                .andRoute(GET("/api/pet/{id}"), handler::listenGETPetByIdUseCase)
                .andRoute(PUT("api/pet/update"), handler::listenPUTUpdatePetUseCase)
                .andRoute(DELETE("/api/pet/delete/{id}"), handler::listenDELETERemovePetUseCase);
    }
}
