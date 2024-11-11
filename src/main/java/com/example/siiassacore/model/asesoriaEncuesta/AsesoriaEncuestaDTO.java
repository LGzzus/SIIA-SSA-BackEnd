package com.example.siiassacore.model.asesoriaEncuesta;

import com.example.siiassacore.model.asesor.AsesorDTO;
import com.example.siiassacore.model.asesor.AsesorVO;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AsesoriaEncuestaDTO {
    int id_Asesoria_Encuesta;
    int id_Asesoria_Asignacion;
    int id_Encuesta;
    Timestamp date_Fecha_Asignacion;
    Timestamp timestap_Fecha_Actualizacion;
    Boolean boolean_Respondida;

    public static AsesoriaEncuestaDTO fromVO (AsesoriaEncuestaVO asesoriaEncuestaVO){
        return AsesoriaEncuestaDTO.builder()
                .id_Asesoria_Encuesta(asesoriaEncuestaVO.id_Asesoria_Encuesta)
                .id_Asesoria_Asignacion(asesoriaEncuestaVO.int_id_Asesoria_Asignacion)
                .id_Encuesta(asesoriaEncuestaVO.int_Id_Encuesta)
                .date_Fecha_Asignacion(asesoriaEncuestaVO.date_Fecha_Asignacion)
                .timestap_Fecha_Actualizacion(asesoriaEncuestaVO.timestap_Fecha_Actualizacion)
                .boolean_Respondida(asesoriaEncuestaVO.boolean_Respondida)
                .build();
    }

}
