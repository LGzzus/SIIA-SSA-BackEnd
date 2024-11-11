package com.example.siiassacore.endpoint;

import com.example.siiassacore.model.asesor.AsesorDTO;
import com.example.siiassacore.service.AsesorService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.logging.Logger;

@RestController
@RequestMapping("/asesor")
public class AsesorEndpoint {
    @Autowired
    AsesorService asesorService;
    private final static Logger LOG_Debug = Logger.getLogger(AsesorEndpoint.class.getName());

    //Service para asignar rol a los docentes
    @PostMapping("/asingarRolAsesor")
    public ResponseEntity<Map<String, Object>> asignarRolAsesor(@RequestBody AsesorDTO asesorDTO) {
        return asesorService.asignarRolAsesor(asesorDTO);
    }
    //Servicio Get para obtener los asesores ya sea por nombre o todos los asesores con periodo y programa
    @PostMapping("/obtenerAsesor-es")
    public ResponseEntity<Map<String, Object>> obtenerAsesores(@RequestBody AsesorDTO asesorDTO) {
        return asesorService.obtenerAsesores(asesorDTO);
    }

    //Metodo para verificar si es asesor
    @PostMapping("/soyAsesor")
    public ResponseEntity<Map<String, Object>> soyAsesor(@RequestBody AsesorDTO asesorDTO){
        return asesorService.soyAsesor(asesorDTO);
    }

}
