package com.example.siiassacore.model.asesoriaAsignacion;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AsesoriaAsignacionDTO {
   int idAsesoriaAsignacion;
   int idAsesoria;
   int idAsesor;
   String strMatricula;
   String booleanAsistencia;

}
