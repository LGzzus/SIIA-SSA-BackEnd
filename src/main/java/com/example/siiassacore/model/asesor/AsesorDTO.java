package com.example.siiassacore.model.asesor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AsesorDTO {
    int idAsesor;
    int intIdPersona;
    String strNBTutor;
    int intIdPrograma;
    String strIdPeriodo;
    boolean intStatus;

    public static AsesorDTO fromVO (AsesorVO asesorVO){
        return AsesorDTO.builder()
                .idAsesor(asesorVO.id_Asesor)
                .intIdPersona(asesorVO.int_Id_Persona)
                .strNBTutor(asesorVO.str_NB_Tutor)
                .intIdPrograma(asesorVO.int_Id_Programa)
                .strIdPeriodo(asesorVO.str_Id_Periodo)
                .intStatus(asesorVO.int_Estatus)
                .build();
    }
}
