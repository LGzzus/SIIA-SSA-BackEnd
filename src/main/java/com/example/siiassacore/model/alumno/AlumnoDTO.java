package com.example.siiassacore.model.alumno;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlumnoDTO {
    String strMatriculaAlumno;
    String strNombreAlumno;
}
