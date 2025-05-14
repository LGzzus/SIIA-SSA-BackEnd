package com.example.siiassacore.repository;

import com.example.siiassacore.model.pregunta.PreguntaVO;
import com.example.siiassacore.model.preguntaOpcion.PreguntaOpcionDTO;
import com.example.siiassacore.model.preguntaOpcion.PreguntaOpcionVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface PreguntaRepository extends JpaRepository<PreguntaOpcionVO, Integer> {

    @Query("SELECT p.encuesta.str_NB_Encuesta,p.pregunta.id_Pregunta,p.pregunta.str_Pregunaa,p.pregunta.str_Tipo_Pregunta,po.id_Inciso,po.str_Contenido_Opcion FROM PreguntaOpcionVO p JOIN p.pregunta pp JOIN p.opcion po where p.encuesta.id_Encuesta = :idEncuesta")
    List<Object[]> obtenerPreguntas(@Param("idEncuesta") Integer idEncuesta);

}
