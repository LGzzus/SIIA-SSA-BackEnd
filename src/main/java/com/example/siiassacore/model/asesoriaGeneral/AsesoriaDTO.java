package com.example.siiassacore.model.asesoriaGeneral;

import com.example.siiassacore.model.alumno.AlumnoDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AsesoriaDTO {
 /*alumnos: (2) [{…}, {…}]
diaSeleccionado: "Martes"
horaFin:"16:00"
horaInicio:"15:00"
idAsesor:1
intIdProgramaCampus:2
strIdPeriodo:"2010-4-3"
tipoAsesoria:"Grupal"*/
    String diaSeleccionado;
    String fechaCorta;
    String horaInicio;
    String horaFin;
    int idAsesor;
    int intIdProgramaCampus;
    String strIdPeriodo;
    String tipoAsesoria;
    List<AlumnoDTO> alumnos;

}
