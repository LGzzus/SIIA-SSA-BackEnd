package com.example.siiassacore.model.asesoriaAsignacion;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "asesoria_asignacion")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AsesoriaAsignacionVO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id_Asesoria_Asignacion")
    int id_Asesoria_Asignacion;
    @Column(name = "id_Asesoria")
    int id_Asesoria;
    @Column(name = "id_Asesor")
    int id_Asesor;
    @Column(name = "str_Matricula_Alumno")
    String str_Matricula_Alumno;
    @Column(name = "str_Nombre_Alumno")
    String str_Nombre_Alumno;
    @Column(name = "str_Asistencia")
    @Enumerated(EnumType.STRING)
    private Asistencia str_Asistencia;

    public enum Asistencia{
        Pendiente,
        Asistio,
        No_Asistio
    }
}
