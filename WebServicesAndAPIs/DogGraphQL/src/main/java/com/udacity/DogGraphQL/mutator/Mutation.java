package com.udacity.DogGraphQL.mutator;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.udacity.DogGraphQL.entity.Dog;
import com.udacity.DogGraphQL.exception.BreedNotFoundException;
import com.udacity.DogGraphQL.exception.DogNotFoundException;
import com.udacity.DogGraphQL.repository.DogRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class Mutation implements GraphQLMutationResolver {
    private DogRepository dogRepository;

    public Mutation(DogRepository dogRepository){
        this.dogRepository = dogRepository;
    }

    public boolean deleteDogBreed(String breed){
        boolean deleted = false;

        Iterable<Dog> allDogs = dogRepository.findAll();

        //loop through all dogs to look up their breed
        for (Dog d:allDogs){
            if(d.getBreed().equals(breed)){
                dogRepository.delete(d);
                deleted = true;
            }
        }
        if (!deleted){
            throw new BreedNotFoundException("Breed Not Found", breed);
        }
        return true;
    }

    public Dog updateDogName(String newName, Long id){
        Optional<Dog> optionalDog = dogRepository.findById(id);

        if (optionalDog.isPresent()){
            Dog dog = optionalDog.get();
            dog.setName(newName);
            dogRepository.save(dog);
            return dog;

        } else{
            throw new DogNotFoundException("Dog not found", id);
        }
    }
}
