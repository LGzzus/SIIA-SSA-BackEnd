package com.example.siiassacore.model.respuesta;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RespuestaDTO {
    /*
    int_Id_Encuesta:int_Id_Opcion: int_Id_Pregunta:int_id_Asesoria_Control:str_Respuesta_Texto
    */
    int int_Id_Encuesta;
    int int_Id_Opcion;
    int int_Id_Pregunta;
    int int_Id_Asesoria_Control;
    String str_Respuesta_Texto;
}
