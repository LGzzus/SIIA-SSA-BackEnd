package com.example.siiassacore.model.respuesta;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "respuesta")
@Data
public class RespuestaVO {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    int id_Respuesta;
    //id_Respuesta	int_Id_Encuesta	int_id_Asesoria_Control	int_Id_Pregunta	int_Id_Opcion	str_Respuesta_Texto
    @Column(name = "int_Id_Encuesta")
    int int_Id_Encuesta;

    @Column(name = "int_Id_Asesoria_Control")
    int int_Id_Asesoria_Control;

    @Column(name = "int_Id_Pregunta")
    int int_Id_Pregunta;

    @Column(name = "int_Id_Opcion")
    int int_Id_Opcion;

    @Column(name = "str_Respuesta_Texto")
    String str_Respuesta_Texto;
}
