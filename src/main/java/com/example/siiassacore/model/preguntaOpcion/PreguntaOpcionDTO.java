package com.example.siiassacore.model.preguntaOpcion;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PreguntaOpcionDTO {

    String nombreEncuesta;
    Integer idPregunta;
    String textoPregunta;
    Integer idInciso;
    String contenidoOpcion;

}
