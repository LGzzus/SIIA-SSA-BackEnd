package com.example.siiassacore.model.encuesta;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Entity
@Table (name = "encuesta")
@Data
public class EncuestaVO {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)

    @Column(name = "id_Encuesta")
    int id_Encuesta;

    @Column(name = "str_NB_Encuesta")
    String str_NB_Encuesta;

    @Column(name = "str_Descripcion")
    String str_Descripcion;

    @Column(name = "date_Fecha_Creacion")
    Date date_Fecha_Creacion;

}
