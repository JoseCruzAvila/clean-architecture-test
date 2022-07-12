package co.com.sofka.usecase.removepet;

import co.com.sofka.model.pet.gateways.PetRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class RemovePetUseCase {
    private final PetRepository repository;

    public Mono<Void> removePetById(String id) {
        return repository.deleteById(id);
    }
}
