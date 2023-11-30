package de.neuefische.ffmjava232diandspringdata;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class IntegrationPersonControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void getAllPersons() throws Exception {
        //GIVEN
        NewPersonDTO bodyObject = new NewPersonDTO("Test");
        String bodyJson = objectMapper.writeValueAsString(bodyObject);

        MvcResult result = mockMvc.perform(post("/api/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bodyJson))
                .andReturn();

        Person personInDB = objectMapper.readValue(result.getResponse().getContentAsString(), Person.class);
        String personsAsJson = objectMapper.writeValueAsString(List.of(personInDB));

        //WHEN
        mockMvc.perform(MockMvcRequestBuilders.get("/api/person"))

                //THEN
                .andExpect(status().isOk())
                .andExpect(content().json(personsAsJson));

    }
}
