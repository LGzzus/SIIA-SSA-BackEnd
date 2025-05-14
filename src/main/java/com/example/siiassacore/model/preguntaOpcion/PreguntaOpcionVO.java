package com.example.siiassacore.model.preguntaOpcion;

import com.example.siiassacore.model.Inciso.IncisoVO;
import com.example.siiassacore.model.encuesta.EncuestaVO;
import com.example.siiassacore.model.pregunta.PreguntaVO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "pregunta_opcion")
@Data

public class PreguntaOpcionVO {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)

    @Column(name = "id_Pregunta_Opcion")
    int id_Pregunta_Opcion;

    @ManyToOne
    @JoinColumn (name = "int_Id_Encuesta")
     private EncuestaVO encuesta;

    @ManyToOne
    @JoinColumn(name = "int_Id_Pregunta")
    private PreguntaVO pregunta;

    @ManyToOne
    @JoinColumn(name = "int_Id_Inciso")
    private IncisoVO opcion;


}
