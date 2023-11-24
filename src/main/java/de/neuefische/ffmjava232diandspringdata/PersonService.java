package de.neuefische.ffmjava232diandspringdata;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepo repo;
    private final IdService idService;

    public List<Person> getAllPersons() {
        return repo.findAll();
    }

    public Person getPersonById(String id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Not Found"));
    }

    public Person savePerson(NewPersonDTO person){
        Person newPerson = new Person(idService.randomId(), person.name());

        return repo.save(newPerson);
    }

    public Person findByName(String name){
        return repo.findByName(name)
                .orElseThrow(() -> new RuntimeException("Not Found"));
    }
}
