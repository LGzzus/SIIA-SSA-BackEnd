package com.example.siiassacore.service;


import com.example.siiassacore.model.asesoriaEncuesta.AsesoriaEncuestaDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

public interface AsesoriaEncuestaService {
    ResponseEntity<Map<String,Object>> hayencuestasPendientes(String str_Matricula);

    List<AsesoriaEncuestaDTO> listaEncuestas ( int idProgramaEducativo,  String str_Periodo, String str_Matricula);
}
