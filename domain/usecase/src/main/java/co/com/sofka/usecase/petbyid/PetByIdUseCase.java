package co.com.sofka.usecase.petbyid;

import co.com.sofka.model.pet.Pet;
import co.com.sofka.model.pet.gateways.PetRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class PetByIdUseCase {
    private final PetRepository repository;

    public Mono<Pet> petById(String id) {
        return repository.findById(id);
    }
}
