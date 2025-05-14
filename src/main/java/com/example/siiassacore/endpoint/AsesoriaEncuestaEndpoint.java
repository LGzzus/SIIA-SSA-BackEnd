package com.example.siiassacore.endpoint;

import com.example.siiassacore.model.asesoriaEncuesta.AsesoriaEncuestaDTO;
import com.example.siiassacore.model.respuesta.RespuestaDTO;
import com.example.siiassacore.service.AsesoriaEncuestaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@RestController
@RequestMapping("/asesoriaEncuastas")
public class AsesoriaEncuestaEndpoint {
    @Autowired
    AsesoriaEncuestaService encuestaService;

    private  final static Logger LOG_debug = Logger.getLogger(AsignarAsesoriaEndpoint.class.getName());

    @PostMapping("encuestasPendientes")
    public ResponseEntity<Map<String,Object>>  encuestasPendientes(@RequestParam String str_Matricula){
        LOG_debug.info(str_Matricula);
        return encuestaService.hayencuestasPendientes(str_Matricula);
    }

    @PostMapping("listaEncuestas")
    public List<AsesoriaEncuestaDTO> getListaEncuestas(@RequestParam int idProgramaEducativo, @RequestParam String str_Periodo, @RequestParam String str_Matricula){
        LOG_debug.info("Parametros...."+ idProgramaEducativo+ " "+str_Periodo + " "+str_Matricula);
        return encuestaService.listaEncuestas(idProgramaEducativo,str_Periodo,str_Matricula);
    }

    @GetMapping("/obtenerPreguntas")
    public ResponseEntity<Map<String,Object>> obtenerPreguntasConOpciones (@RequestParam int idEncuesta){
        LOG_debug.info("param"+idEncuesta);

        return encuestaService.obtenerPreguntaConRespuestas(idEncuesta);
    }

    @PostMapping("/registrarRespuesta")
    public ResponseEntity<String> registrarRespuesta (@RequestBody RespuestaDTO respuestaDTO){
        LOG_debug.info("Body - "+respuestaDTO);

        return encuestaService.registrarRespuestasEncuestas(respuestaDTO);
    }
}