package de.neuefische.ffmjava232diandspringdata;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepo repo;

    public List<Person> getAllPersons() {
        return repo.findAll();
    }

    public Person getPersonById(String id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Not Found"));
    }

    public Person savePerson(Person person){
        return repo.save(person);
    }

    public Person findByName(String name){
        return repo.findByName(name)
                .orElseThrow(() -> new RuntimeException("Not Found"));
    }
}
