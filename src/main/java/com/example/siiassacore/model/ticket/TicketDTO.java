package com.example.siiassacore.model.ticket;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketDTO {
    int idTicket;
    String matricula;
    String str_Tipo_Ticket;
    String str_Contenido;
    String date_Fecha_Creacion;
    String date_Fecha_Update;
    String Status;


}
