package com.example.siiassacore.model.ticket;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Table(name = "tickets")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketVO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id_Ticket")
    int idTicket;

    @Column(name = "matricula")
    String matricula;

    @Column(name = "str_Tipo_Ticket")
    String str_Tipo_Ticket;

    @Column(name = "str_contenido")
    String str_Contenido;

    @Column(name = "date_Fecha_Creacion")
    Date date_Fecha_Creacion;

    @Column(name = "date_Fecha_update")
    Date date_Fecha_Update;

    @Column(name = "status")
    String Status;
}
