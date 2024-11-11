package com.example.siiassacore.model.asesoriaControl;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AsesoriaControlDTO {

    int idAsesoria;
    String tipoAsesoria;
    String strDia;
    String timeHoraInicio;
    String tiemHoraFin;
    String strIdPeriod;
    String fechaCreacion;
    String fechaActualizacion;

}
