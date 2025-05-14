package com.example.siiassacore.model.pregunta;

import com.example.siiassacore.model.encuesta.EncuestaVO;
import com.example.siiassacore.model.preguntaOpcion.PreguntaOpcionVO;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "pregunta")
@Data
public class PreguntaVO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id_Pregunta")
    int id_Pregunta;

    @Column(name = "int_Id_Encuesta")
    private int int_Id_Encuesta;

    @Column(name = "str_Pregunta")
    String str_Pregunaa;

    @Column(name = "str_Tipo_Pregunta")
    String str_Tipo_Pregunta;

    @OneToMany(mappedBy = "pregunta")
    private List<PreguntaOpcionVO> opciones;

}
