package com.example.siiassacore.endpoint;

import com.example.siiassacore.model.horario.HorarioDTO;
import com.example.siiassacore.service.HorarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.Map;

@RestController
@RequestMapping("horario")
public class HorarioEndpoint {
   @Autowired
   HorarioService horarioService;

   @PostMapping("registrarHorario")
   public ResponseEntity<Map<String,Object>> registrarHorario (@RequestBody HorarioDTO horarioDTO){
      return horarioService.registrarHorario(horarioDTO);
   }

   @PostMapping("obtenerHorarios")
   public ResponseEntity<Map<String,Object>> obtenerHorarios (@RequestBody HorarioDTO horarioDTO){
      return horarioService.obtenerHorariosAsesor(horarioDTO);
   }

   @PostMapping("eliminarHorario")
   public ResponseEntity<Map<String,Object>> eliminarHorario(@RequestBody HorarioDTO horarioDTO){
      return horarioService.eliminarHorario(horarioDTO);
   }
   @PostMapping("obtenerDiasHorarios")
   public ResponseEntity<Map<String,Object>> obtenerDiasHorarios(@RequestParam int int_Id_Asesor){
      return horarioService.obtenerDiasHorarios(int_Id_Asesor);
   }
   @PostMapping("horasDeAtencionDiaria")
   public ResponseEntity<Map<String,Object>> horasDeAtencionDiaria(@RequestParam int int_Id_Asesor , @RequestParam String str_Dia, @RequestParam String date_Fecha){
      System.out.println(int_Id_Asesor + " y "+str_Dia+" y "+date_Fecha);
      return horarioService.horasDeAtencionDiaria(int_Id_Asesor,str_Dia,date_Fecha);
   }
}
