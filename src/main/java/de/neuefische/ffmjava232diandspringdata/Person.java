package de.neuefische.ffmjava232diandspringdata;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("Person") // -> verändert den Namen der Collection in eurer Database!
public record Person(String id, String name) {
}
