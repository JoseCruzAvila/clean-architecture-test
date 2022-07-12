package co.com.sofka.usecase.updatepet;

import co.com.sofka.model.pet.Pet;
import co.com.sofka.model.pet.gateways.PetRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class UpdatePetUseCase {
    private final PetRepository repository;

    public Mono<Pet> updatePet(Pet pet) {
        return repository.save(pet);
    }
}
