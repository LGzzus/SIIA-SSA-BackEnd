package com.example.siiassacore.service;

import com.example.siiassacore.model.asesoriaGeneral.AsesoriaDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

public interface AsesoriaService {
    ResponseEntity<Map<String,Object>> registroAsignacionAsesoria(@RequestBody  AsesoriaDTO asesoriaDTO);

    ResponseEntity<Map<String,Object>> obtenerAsesorias(@RequestParam int idAsesor, @RequestParam String str_Id_Periodo);

    ResponseEntity<Map<String,Object>> alumnosAsesoria(@RequestParam int idAsesoria);

    ResponseEntity<Map<String,Object>> asistenciaAsesoria(@RequestParam int idAsignacion);
}
