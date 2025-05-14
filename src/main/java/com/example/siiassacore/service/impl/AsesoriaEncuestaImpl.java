package com.example.siiassacore.service.impl;

import ch.qos.logback.core.model.INamedModel;
import com.example.siiassacore.model.asesoriaEncuesta.AsesoriaEncuestaDTO;
import com.example.siiassacore.model.asesoriaEncuesta.AsesoriaEncuestaVO;
import com.example.siiassacore.model.preguntaOpcion.PreguntaOpcionDTO;
import com.example.siiassacore.model.preguntaOpcion.PreguntaOpcionVO;
import com.example.siiassacore.model.respuesta.RespuestaDTO;
import com.example.siiassacore.model.respuesta.RespuestaVO;
import com.example.siiassacore.repository.AsesoriaEncuestaRepository;
import com.example.siiassacore.repository.PreguntaRepository;
import com.example.siiassacore.repository.RespuestasRepository;
import com.example.siiassacore.service.AsesoriaEncuestaService;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Service
public class AsesoriaEncuestaImpl implements AsesoriaEncuestaService {

    private final static Logger LOG_debug = Logger.getLogger(AsesoriaServiceImpl.class.getName());

    @Autowired
    private AsesoriaEncuestaRepository encuestaRepository;

    @Autowired
    private PreguntaRepository preguntaRepository;

    @Autowired
    private  RespuestasRepository respuestasRepository;

    @Override
    public ResponseEntity<Map<String,Object>> hayencuestasPendientes(String str_Matricula) {
        Boolean encuestasP = false;
        try{
            LOG_debug.info("adasdasdasdasdasdasdasdass"+str_Matricula);
            List<AsesoriaEncuestaVO> listAsesoriasPendientes = encuestaRepository.listaEncuestasPendientes(str_Matricula);
            listAsesoriasPendientes.forEach( asesoriaEncuestaVO -> LOG_debug.info("adadasd" + asesoriaEncuestaVO));
            if(listAsesoriasPendientes.size() > 0){
                encuestasP = true;
            }
        }catch (Exception e) {
            encuestasP = false;
        }
        return null;
    }

    @Override
    public List<AsesoriaEncuestaDTO> listaEncuestas(int idProgramaEducativo, String str_Periodo, String str_Matricula) {
        LOG_debug.info("Parametros2...."+ idProgramaEducativo+ " "+str_Periodo + " "+str_Matricula);
        try {
            List<AsesoriaEncuestaDTO> encuestaDTOS = new ArrayList<>();
            List<Object[]> listAsesorias = encuestaRepository.listaEncuestaSeleccion(str_Matricula,str_Periodo,idProgramaEducativo);


            listAsesorias.forEach(la -> {
                LOG_debug.info("LAaaaaa " + la.length);
                LOG_debug.info("entra ");
                AsesoriaEncuestaVO encuestaVO =  (AsesoriaEncuestaVO) la[0];
                LOG_debug.info("entra2 ");
                String fecha =  String.valueOf( la[1]);
                LOG_debug.info("emcuesta "+encuestaVO);
                LOG_debug.info("Fecha "+fecha);

                String nombre =  String.valueOf( la[2]);
                LOG_debug.info("Nombre "+nombre);

                encuestaDTOS.add(AsesoriaEncuestaDTO.fromVO(encuestaVO, fecha.toString(),nombre.toString()));
                LOG_debug.info("dto "+encuestaDTOS);

            });

            LOG_debug.info("juasjuasjuas"+encuestaDTOS);
            return encuestaDTOS;
        }catch (Exception e){
            LOG_debug.info("Exception "+e.getMessage());
        }
        return null;
    }

    @Override
    public ResponseEntity<Map<String, Object>> obtenerPreguntaConRespuestas(int idEncuesta) {
        try{
            Map<String,Object> mapResponse = new HashMap<>();
            //LOG_debug.info("param recived" + idEncuesta);

            List<Object[]> preguntaOpcionVOS = preguntaRepository.obtenerPreguntas(idEncuesta);
            //LOG_debug.info("Encuesta Nombre" + preguntaOpcionVOS.get(0)[0]);

            mapResponse.put("nombreEncuesta",preguntaOpcionVOS.get(0)[0]);
            LOG_debug.info("Response"+mapResponse);

            Map<Integer,Map<String,Object>> preguntasMap = new HashMap<>();

            for(Object[] row : preguntaOpcionVOS){
                int preguntaId = (Integer) row[1];
                preguntasMap.putIfAbsent(preguntaId, new HashMap<>() {{
                    put("id",preguntaId);
                    put("contenido",row[2]);
                    put("tipo",row[3]);
                    put("opciones",new ArrayList<>());
                }});

                Map<String,Object> opcion = new HashMap<>();
                opcion.put("id",row[4]);

                opcion.put("texto",row[5]);

                LOG_debug.info("Opciones"+opcion);
                ((List<Map<String, Object>>) preguntasMap.get(preguntaId).get("opciones")).add(opcion);
            }

            LOG_debug.info("preguntas"+preguntasMap);

            mapResponse.put("preguntas", new ArrayList<>(preguntasMap.values()));

            LOG_debug.info("repsones"+mapResponse);
            return ResponseEntity.ok(mapResponse);
        }catch (Exception e){
            LOG_debug.info("err" + e.getMessage());
        }


        return null;
    }

    @Override
    public ResponseEntity<String> registrarRespuestasEncuestas(RespuestaDTO respuestaDTO) {
        try {
            LOG_debug.info("Body ServiceImpl -"+respuestaDTO);

            RespuestaVO respuestaVO = new RespuestaVO();

            respuestaVO.setInt_Id_Encuesta(respuestaDTO.getInt_Id_Encuesta());
            respuestaVO.setInt_Id_Asesoria_Control(respuestaDTO.getInt_Id_Asesoria_Control());
            respuestaVO.setInt_Id_Opcion(respuestaDTO.getInt_Id_Opcion());
            respuestaVO.setInt_Id_Pregunta(respuestaDTO.getInt_Id_Pregunta());
            respuestaVO.setStr_Respuesta_Texto(respuestaDTO.getStr_Respuesta_Texto());

            respuestasRepository.save(respuestaVO);
        }catch (Exception e){

        }
        return null;
    }
}
