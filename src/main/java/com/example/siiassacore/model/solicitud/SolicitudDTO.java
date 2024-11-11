package com.example.siiassacore.model.solicitud;

import com.example.siiassacore.model.asesor.AsesorDTO;
import com.example.siiassacore.model.asesor.AsesorVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SolicitudDTO {
    int intIdSolicitud;
    int lngIdPersona;
    int intIdAsesor;
    String statusFirmada;

    public static SolicitudDTO solicitudFromVO (SolicitudVO solicitudVO){
        return SolicitudDTO.builder()
                .intIdSolicitud(solicitudVO.int_Id_Solicitud)
                .lngIdPersona(solicitudVO.ing_Id_Persona)
                .intIdAsesor(solicitudVO.asesor.getId_Asesor())
                .statusFirmada(solicitudVO.getStr_Solicitud_Estado().name())
                .build();
    }

}
