package com.example.siiassacore.repository;

import com.example.siiassacore.model.respuesta.RespuestaVO;
import com.example.siiassacore.model.ticket.TicketVO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<TicketVO, Integer> {
}
