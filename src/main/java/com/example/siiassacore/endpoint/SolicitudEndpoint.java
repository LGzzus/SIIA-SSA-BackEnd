package com.example.siiassacore.endpoint;

import com.example.siiassacore.model.asesor.AsesorDTO;
import com.example.siiassacore.service.AsesorService;
import com.example.siiassacore.service.SolicitudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.logging.Logger;

@RestController
@RequestMapping("solicitud")
public class SolicitudEndpoint {
    private final static Logger LOG_Debug = Logger.getLogger(AsesorEndpoint.class.getName());
    @Autowired
    SolicitudService solicitudService;
    @PostMapping("/firmarSolicitud")
    public ResponseEntity<byte[]> firmarSolicitud (@RequestParam int idSolicitud){
        LOG_Debug.info("Data "+idSolicitud);
        return solicitudService.firmarSolicitud(idSolicitud);
    }
    @PostMapping("/rechazarSolicitud")
    public ResponseEntity<Map<String,Object>> rechazarSolicitud (@RequestParam int idSolicitud){
        LOG_Debug.info("Data "+idSolicitud);
        return solicitudService.rechazarSolicitud(idSolicitud);
    }
    @PostMapping("/solicitudesPendientes")
    public ResponseEntity<Map<String, Object>> solicitudPendiente(@RequestParam int lngIdPersona){
        LOG_Debug.info(String.valueOf(lngIdPersona));
        return solicitudService.solicitudPendiente(lngIdPersona);
    }

}
