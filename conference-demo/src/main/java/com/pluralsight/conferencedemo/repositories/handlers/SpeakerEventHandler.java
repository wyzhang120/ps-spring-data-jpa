package com.pluralsight.conferencedemo.repositories.handlers;

import com.pluralsight.conferencedemo.models.Speaker;
import com.pluralsight.conferencedemo.repositories.SpeakerJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolationException;
import java.util.HashSet;

@Component
@RepositoryEventHandler
public class SpeakerEventHandler {
    @Autowired
    private SpeakerJpaRepository repository;

    @HandleBeforeCreate
    public void handleSpeakerCreate(Speaker s) {
        Speaker foundSpeaker = repository.findFirstByFirstName(s.getFirstName());
        String errorMessage = "First name needs to be unique";
        if (foundSpeaker != null) {
            System.out.println(errorMessage);
            throw new ConstraintViolationException(errorMessage, new HashSet<>());
        }
    }
}
