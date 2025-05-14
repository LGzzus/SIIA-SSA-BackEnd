package com.example.siiassacore.service;

import org.springframework.http.ResponseEntity;

import java.util.Map;


public interface TicketService {

    ResponseEntity<Map<String,Object>> registrarTicket (String matricula, String tipoTicket, String contenidoTicket );
}
