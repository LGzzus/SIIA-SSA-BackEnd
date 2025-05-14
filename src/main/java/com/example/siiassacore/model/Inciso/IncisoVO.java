package com.example.siiassacore.model.Inciso;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "inciso")
@Data
public class IncisoVO {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)

    @Column(name = "id_Inciso")
    int id_Inciso;

    @Column(name = "str_Contenido_Opcion")
    String str_Contenido_Opcion;
}
