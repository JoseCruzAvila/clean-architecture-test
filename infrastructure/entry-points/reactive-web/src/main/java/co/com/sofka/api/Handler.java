package co.com.sofka.api;

import co.com.sofka.model.pet.Pet;
import co.com.sofka.usecase.allpets.AllPetsUseCase;
import co.com.sofka.usecase.createpet.CreatePetUseCase;
import co.com.sofka.usecase.petbyid.PetByIdUseCase;
import co.com.sofka.usecase.removepet.RemovePetUseCase;
import co.com.sofka.usecase.updatepet.UpdatePetUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class Handler {
    private final CreatePetUseCase createPetUseCase;
    private final AllPetsUseCase allPetsUseCase;
    private final PetByIdUseCase petByIdUseCase;
    private final UpdatePetUseCase updatePetUseCase;
    private final RemovePetUseCase removePetUseCase;

    public Mono<ServerResponse> listenPOSTCreatePetUseCase(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(Pet.class)
                .flatMap(createPetUseCase::savePet)
                .flatMap(pet -> ServerResponse.ok()
                                            .contentType(MediaType.APPLICATION_JSON)
                                            .body(BodyInserters.fromValue(pet)));
    }

    public Mono<ServerResponse> listenGETAllPetsUseCase(ServerRequest serverRequest) {
        return ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(allPetsUseCase.allPets(), Pet.class));
    }

    public Mono<ServerResponse> listenGETPetByIdUseCase(ServerRequest serverRequest) {
        String petId = serverRequest.pathVariable("id");

        return ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(petByIdUseCase.petById(petId), Pet.class));
    }

    public Mono<ServerResponse> listenPUTUpdatePetUseCase(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(Pet.class)
                .flatMap(updatePetUseCase::updatePet)
                .flatMap(pet -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue(pet)));
    }

    public Mono<ServerResponse> listenDELETERemovePetUseCase(ServerRequest serverRequest) {
        String petId = serverRequest.pathVariable("id");
        try {
            return removePetUseCase.removePetById(petId)
                    .flatMap(pet -> ServerResponse.ok()
                            .contentType(MediaType.TEXT_PLAIN)
                            .body(BodyInserters.fromValue("Successfully deleted " + petId)));
        } catch (Exception e) {
            return ServerResponse.notFound().build();
        }
    }
}
