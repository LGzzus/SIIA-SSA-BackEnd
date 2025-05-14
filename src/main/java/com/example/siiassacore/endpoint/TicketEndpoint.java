package com.example.siiassacore.endpoint;

import com.example.siiassacore.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.logging.Logger;

@RestController
@RequestMapping("/tickets")
public class TicketEndpoint {

    @Autowired
    TicketService ticketService;

    private  final static Logger LOG_debug = Logger.getLogger(AsignarAsesoriaEndpoint.class.getName());

    @PostMapping("/registrarTicket")
    public ResponseEntity<Map<String,Object>> registrarTicket(@RequestParam String matricula, @RequestParam String tipoTicket, @RequestParam String contenidoTicket){
        LOG_debug.info("Data"+ matricula+tipoTicket+contenidoTicket);
        return ticketService.registrarTicket(matricula,tipoTicket,contenidoTicket);
    }
}
