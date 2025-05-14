package com.example.siiassacore.service.impl;

import com.example.siiassacore.endpoint.AsignarAsesoriaEndpoint;
import com.example.siiassacore.model.ticket.TicketVO;
import com.example.siiassacore.repository.TicketRepository;
import com.example.siiassacore.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.PrivateKey;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Map;
import java.util.logging.Logger;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    private  final static Logger LOG_debug = Logger.getLogger(TicketServiceImpl.class.getName());
    @Override
    public ResponseEntity<Map<String, Object>> registrarTicket(String matricula, String tipoTicket, String contenidoTicket) {
        LOG_debug.info("DAtaService"+matricula+tipoTicket+contenidoTicket);

        try {
            TicketVO ticketVO = new TicketVO();

            ticketVO.setMatricula(matricula);
            ticketVO.setStr_Tipo_Ticket(tipoTicket);
            ticketVO.setStr_Contenido(contenidoTicket);
            LocalDateTime fechaCreacion = LocalDateTime.now();
            Date sqlDate = Date.valueOf(fechaCreacion.toLocalDate());
            ticketVO.setDate_Fecha_Creacion(sqlDate);
            ticketVO.setStatus("Registrado");

            ticketRepository.save(ticketVO);

        }catch (Exception e){
            LOG_debug.info("Ups... Ocurrio un error");
        }
        return null;
    }
}
