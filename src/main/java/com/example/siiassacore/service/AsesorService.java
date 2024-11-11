package com.example.siiassacore.service;

import com.example.siiassacore.model.asesor.AsesorDTO;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface AsesorService {

    ResponseEntity<Map<String, Object>> asignarRolAsesor (AsesorDTO asesorDTO);

    ResponseEntity<Map<String, Object>> obtenerAsesores (AsesorDTO asesorDTO);

    ResponseEntity<Map<String, Object>> soyAsesor (AsesorDTO asesorDTO);
}
