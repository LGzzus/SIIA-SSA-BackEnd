package com.example.siiassacore.endpoint;

import com.example.siiassacore.model.asesoriaAsignacion.AsesoriaAsignacionDTO;
import com.example.siiassacore.model.asesoriaGeneral.AsesoriaDTO;
import com.example.siiassacore.service.AsesoriaService;
import jakarta.persistence.Access;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;
import java.util.logging.Logger;

@RestController
@RequestMapping("/asesoriasAsignacion")
public class AsignarAsesoriaEndpoint {
    @Autowired
    AsesoriaService asesoriaService;

    private  final static Logger LOG_debug = Logger.getLogger(AsignarAsesoriaEndpoint.class.getName());

    @PostMapping("AsignarAsesoria")
    public ResponseEntity<Map<String,Object>> registrarAsesoria (@RequestBody AsesoriaDTO asesoriaDTO){
        LOG_debug.info("dataAsesoria: "+asesoriaDTO);
        return asesoriaService.registroAsignacionAsesoria(asesoriaDTO);
    }

    @PostMapping("obtenerAsesorias")
    public ResponseEntity<Map<String,Object>> obtenerAsesoriasPendientes(@RequestParam int idAsesor, @RequestParam String str_Id_Periodo){
        LOG_debug.info("Paraaamsssssssssssssssssss " + idAsesor +" "+str_Id_Periodo);
        return asesoriaService.obtenerAsesorias(idAsesor,str_Id_Periodo);
    }

    @PostMapping("AlumnosAsesoria")
    public  ResponseEntity<Map<String,Object>> alumnosAsesoria (@RequestParam int idAsesoria){
        LOG_debug.info("Paraaaam "+idAsesoria);
        return asesoriaService.alumnosAsesoria(idAsesoria);
    }

    @PostMapping("asistenciaAsesoria")
    public ResponseEntity<Map<String,Object>> asistenciaAsesoria (@RequestParam int idAsignacion){
        LOG_debug.info("El parammmmmmmammammmmmm"+idAsignacion);
        return asesoriaService.asistenciaAsesoria(idAsignacion);
    }
}
