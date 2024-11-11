package com.example.siiassacore.service;


import com.example.siiassacore.model.horario.HorarioDTO;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

public interface HorarioService {

    ResponseEntity<Map<String,Object>> registrarHorario(@RequestBody HorarioDTO horarioDTO);

    ResponseEntity<Map<String,Object>> obtenerHorariosAsesor (@RequestBody HorarioDTO horarioDTO);
    ResponseEntity<Map<String,Object>> eliminarHorario(@RequestBody HorarioDTO horarioDTO);
    ResponseEntity<Map<String, Object>> obtenerDiasHorarios(@RequestParam int int_Id_Asesor);
    ResponseEntity<Map<String,Object>> horasDeAtencionDiaria(@RequestParam int int_Id_Asesor ,@RequestParam String str_Dia, @RequestParam String date_Fecha);
    boolean verificarExistenciaHorario(@RequestBody HorarioDTO horarioDTO);
}
