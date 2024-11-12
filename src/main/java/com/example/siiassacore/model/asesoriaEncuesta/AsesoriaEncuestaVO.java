package com.example.siiassacore.model.asesoriaEncuesta;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDate;

@Entity
@Table(name = "asesoria_encuesta")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AsesoriaEncuestaVO {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)

    @Column(name = "id_Asesoria_Encuesta")
    int id_Asesoria_Encuesta;

    @Column(name = "int_id_Asesoria_Asignacion")
    int int_id_Asesoria_Asignacion;

    @Column(name = "int_Id_Encuesta")
    int int_Id_Encuesta;

    @Column(name = "timestap_Fecha")
    Timestamp date_Fecha_Asignacion;

    @Column(name = "timestap_Fecha_Actualizacion")
    Timestamp timestap_Fecha_Actualizacion;

    @Column(name = "boolean_Respondida")
    Boolean boolean_Respondida;

    @Transient
    private String date_dia_asesoria;

    @Transient
    private String str_NB_Asesor;
}
