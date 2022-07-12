package co.com.sofka.usecase.allpets;

import co.com.sofka.model.pet.Pet;
import co.com.sofka.model.pet.gateways.PetRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class AllPetsUseCase {
    private final PetRepository repository;

    public Flux<Pet> allPets() {
        return repository.findAll();
    }
}
