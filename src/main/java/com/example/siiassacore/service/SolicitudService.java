package com.example.siiassacore.service;

import com.example.siiassacore.model.asesor.AsesorDTO;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface SolicitudService {
    ResponseEntity <Map<String,Object>> solicitudPendiente (int idAsesor);

    ResponseEntity <byte[]> firmarSolicitud (int idSolicitud);

    ResponseEntity <Map<String, Object>> rechazarSolicitud (int idSolicitud);
}
