package de.neuefische.ffmjava232diandspringdata;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


public class PersonServiceTest {

    PersonRepo personRepo = mock(PersonRepo.class);
    IdService idService = mock(IdService.class);
    PersonService personService = new PersonService(personRepo, idService);

    @Test
    void testGetAllPersons_whenCalled_thenReturnsAllPersons() {
        //GIVEN
        List<Person> persons = List.of(new Person("123", "Florian"));
        when(personRepo.findAll()).thenReturn(persons);

        //WHEN
        List<Person> actual = personService.getAllPersons();

        //THEN
        List<Person> expected = List.of(new Person("123", "Florian"));

        verify(personRepo).findAll();
        assertEquals(expected, actual);
    }

    @Test
    void testSavePerson_whenNewPerson_thenReturnSavedPerson() {
        //GIVEN
        NewPersonDTO newPersonDTO = new NewPersonDTO("Test-Name");
        Person personToSave = new Person("test-id", "Test-Name");

        when(personRepo.save(personToSave)).thenReturn(personToSave);
        when(idService.randomId()).thenReturn("test-id");

        //WHEN
        Person actual = personService.savePerson(newPersonDTO);

        //THEN
        Person expected = new Person("test-id", "Test-Name");

        verify(personRepo).save(personToSave);
        assertEquals(expected, actual);
    }
}
