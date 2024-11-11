package com.example.siiassacore.model.asesor;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "asesor")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AsesorVO {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)

    @Column(name = "id_Asesor")
    int id_Asesor;
    @Column(name = "int_Id_Persona")
    int int_Id_Persona;
    @Column(name = "str_NB_Tutor")
    String str_NB_Tutor;

    @Column(name = "int_Id_Programa")
    int int_Id_Programa;

    @Column(name = "str_Id_Periodo")
    String str_Id_Periodo;

    @Column(name = "boolean_estatus")
    boolean int_Estatus;

}
