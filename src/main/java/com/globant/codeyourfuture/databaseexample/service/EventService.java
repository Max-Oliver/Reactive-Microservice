package com.globant.codeyourfuture.databaseexample.service;

import com.globant.codeyourfuture.databaseexample.model.Event;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class EventService {

    /**
     * Conecta con un servidor de mensajeria y envia el mensaje y responde un Flux de mensajes
     * que deberia incluir el evento con delivered en true
     * @param event
     * @return
     */
    public Flux<Event> deliverEvent(final Event event) {
        return Flux.empty();
    }
}
