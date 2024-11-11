package com.example.siiassacore.model.asesoriaControl;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "asesoria_control")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AsesoriaControlVO {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    int idAsesoria;

    @Column(name = "tipo_Asesoria")
    String tipoAsesoria;
    @Column(name = "str_Dia")
    String strDia;
    @Column(name = "date_dia_asesoria")
    Date dateDiaAsesoria;
    @Column(name = "time_Hora_Inicio")
    Time timeHoraInicio;
    @Column(name = "time_Hora_Fin")
    Time tiemHoraFin;
    @Column(name = "int_Id_Programa")
    int int_Id_Programa;
    @Column(name = "str_Id_Periodo")
    String strIdPerido;
    @Column(name = "fecha_Creacion")
    Date fechaCreacion;
    @Column(name = "fecha_Actualizacion")
    Date fechaActualizacion;

    @Column(name = "status_Atencion")
    boolean statusAtencion;

}
