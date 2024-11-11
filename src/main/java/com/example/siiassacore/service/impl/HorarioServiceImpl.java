package com.example.siiassacore.service.impl;


import com.example.siiassacore.endpoint.AsesorEndpoint;
import com.example.siiassacore.model.asesor.AsesorVO;
import com.example.siiassacore.model.horario.HorarioDTO;
import com.example.siiassacore.model.horario.HorarioVO;
import com.example.siiassacore.repository.AsesorRepository;
import com.example.siiassacore.repository.AsesoriaControlRepository;
import com.example.siiassacore.repository.HorarioRepository;
import com.example.siiassacore.service.HorarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.logging.Logger;

@Service
public class HorarioServiceImpl implements HorarioService {
    private final static Logger LOG_Debug = Logger.getLogger(AsesorEndpoint.class.getName());

    @Autowired
    AsesorRepository asesorRepository;
    @Autowired
    HorarioRepository horarioRepository;
    @Autowired
    AsesoriaControlRepository asesoriaControlRepository;
    @Override
    public ResponseEntity<Map<String, Object>> registrarHorario(HorarioDTO horarioDTO) {
        String mensaje ="";
        HttpStatus status = null;
        Map<String, Object> mapResponse = new HashMap<>();
        try {
            if(verificarExistenciaHorario(horarioDTO)){
                mensaje = "Ya hay un horario registrado";
                status = HttpStatus.valueOf(HttpStatus.OK.value());
                mapResponse.put("mensaje", mensaje);
                mapResponse.put("status",status.value());
            }else{
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a",Locale.ENGLISH);

                HorarioVO horarioVO = new HorarioVO();
                if(horarioDTO.getInt_Id_Horario() == 0){
                    AsesorVO asesorVO = asesorRepository.findById(horarioDTO.getInt_Id_Asesor()).orElse(null);

                    horarioVO.setId_Asesor(asesorVO);

                    horarioVO.setStr_Dia(horarioDTO.getStr_Dia());

                    LocalTime horaInicio = LocalTime.parse(horarioDTO.getTime_Hora_Inicio(),formatter);
                    horarioVO.setTime_Hora_Inicio(Time.valueOf(horaInicio));

                    LocalTime horarioFin = LocalTime.parse(horarioDTO.getTime_Hora_Fin(),formatter);
                    horarioVO.setTime_Hora_Fin(Time.valueOf(horarioFin));

                    horarioRepository.save(horarioVO);
                    mensaje = "El horario se registro correctamente";
                    status = HttpStatus.valueOf(HttpStatus.OK.value());
                    mapResponse.put("mensaje",mensaje);
                    mapResponse.put("status",status.value());
                }else {
                    horarioVO.setInt_Id_Horario(horarioDTO.getInt_Id_Horario());

                    AsesorVO asesorVO = asesorRepository.findById(horarioDTO.getInt_Id_Asesor()).orElse(null);
                    horarioVO.setId_Asesor(asesorVO);

                    horarioVO.setStr_Dia(horarioDTO.getStr_Dia());

                    LocalTime horaInicio = LocalTime.parse(horarioDTO.getTime_Hora_Inicio(),formatter);
                    horarioVO.setTime_Hora_Inicio(Time.valueOf(horaInicio));

                    LocalTime horarioFin = LocalTime.parse(horarioDTO.getTime_Hora_Fin(),formatter);
                    horarioVO.setTime_Hora_Fin(Time.valueOf(horarioFin));
                    horarioRepository.save(horarioVO);
                    mensaje = "El horario se registro correctamente";
                    status = HttpStatus.valueOf(HttpStatus.OK.value());
                    mapResponse.put("mensaje",mensaje);
                    mapResponse.put("status",status.value());
                }
            }
            return new ResponseEntity<>(mapResponse,status);
        }catch (Exception e){
            mensaje = e.getMessage();
            status = HttpStatus.valueOf(HttpStatus.CONFLICT.value());

            mapResponse.put("mensaje",mensaje);
            return new ResponseEntity<>(mapResponse,status);
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> obtenerHorariosAsesor(HorarioDTO horarioDTO) {
        Map<String,Object> mapResponse = new HashMap<>();
        String message = "";
        HttpStatus status = null;
        try{
             List<HorarioDTO> horarioDTOS = new ArrayList<>();
             List<HorarioVO> horarioVOList = horarioRepository.obtenerHorarios(horarioDTO.getInt_Id_Asesor());
             if(!horarioVOList.isEmpty()){
                 horarioVOList.forEach(horarioVO -> {horarioDTOS.add(HorarioDTO.fromVO(horarioVO));});

                 message = "Horarios obtenidos";
                 status = HttpStatus.valueOf(HttpStatus.OK.value());

                 mapResponse.put("mensaje",message);
                 mapResponse.put("status",status);
                 mapResponse.put("horarios",horarioDTOS);
                  return  new ResponseEntity<>(mapResponse,status);
                }else{
                    message = "No se encontraron horarios";
                    status = HttpStatus.valueOf(HttpStatus.FORBIDDEN.value());

                    mapResponse.put("mensaje",message);
                    mapResponse.put("status",status.value());

                    return new ResponseEntity<>(mapResponse,status);
                }
        }catch (Exception e){
            message = "Ocurrio un error" + e;
            status = HttpStatus.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value());

            mapResponse.put("mensaje",message);
            mapResponse.put("status",status);
            return new ResponseEntity<>(mapResponse,status);
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> eliminarHorario(HorarioDTO horarioDTO) {
        String mensaje  = "";
        HttpStatus status = null;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm: a",Locale.ENGLISH);
            //AccountVO accountDelete = accountRepository.getOne(Integer.parseInt(dataDeleteAccount.get("id_account").toString()));
            HorarioVO horarioVO = horarioRepository.getOne(horarioDTO.getInt_Id_Horario());

            horarioRepository.delete(horarioVO);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public ResponseEntity<Map<String, Object>> obtenerDiasHorarios(int int_Id_Asesor) {
        LOG_Debug.info("Param "+int_Id_Asesor);
        Map<String,Object> mapResponse = new HashMap<>();
        String message = "";
        HttpStatus status = null;
        try{
            List<Object> horarioDTO = new ArrayList<>();
            List<?> horarioVOS = horarioRepository.obtenerDiasHorario(int_Id_Asesor);
            if (!horarioVOS.isEmpty()) {
                LOG_Debug.info(" horarios " + horarioVOS);
                horarioVOS.forEach(horarioVO -> {
                    LOG_Debug.info("hor" + horarioVO);
                    HorarioDTO horarioDTOS = new HorarioDTO();

                    horarioDTOS.setStr_Dia(horarioVO.toString());

                    horarioDTO.add(horarioDTOS.getStr_Dia().toString());

                    LOG_Debug.info(horarioDTO.toString());
                });
                message = "Se encintraron dias de la semana";
                status = HttpStatus.valueOf(HttpStatus.OK.value());

                mapResponse.put("mensaje",message);
                mapResponse.put("status",status);
                mapResponse.put("dias",horarioDTO);
                return new ResponseEntity<>(mapResponse,status);
            }else {
                message = "No se encontraron horarios";
                status = HttpStatus.valueOf(HttpStatus.FORBIDDEN.value());

                mapResponse.put("mensaje",message);
                mapResponse.put("status",status.value());

                return new ResponseEntity<>(mapResponse,status);
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public ResponseEntity<Map<String, Object>> horasDeAtencionDiaria(int int_Id_Asesor, String str_Dia, String date_Fecha) {
        Map<String, Object> mapResponse = new HashMap<>();
        HttpStatus status;
        try {
            List<HorarioDTO> horas = new ArrayList<>();
            List<?> horasVO = horarioRepository.horasDeAtencionDiaria(int_Id_Asesor, str_Dia);

            if(horasVO.isEmpty()){
                return ResponseEntity.badRequest().body(null);
            }

            //Obtenemos el la hora inicio y la hora fin
            Object[] row = (Object[]) horasVO.get(0);

            Time horaInicio = (Time) row[0];
            Time horaFin = (Time) row[1];

            //convertimos estas horas en locales
            LocalTime inicioLT = horaInicio.toLocalTime();
            LocalTime finLT = horaFin.toLocalTime();

            LocalTime actualLT = inicioLT;

            //Formateamos la fecha
            SimpleDateFormat formatoEntrada = new SimpleDateFormat("dd/MM/yyyy");
            java.util.Date utilDate = formatoEntrada.parse(date_Fecha);
            Date sqlDate = new Date(utilDate.getTime());

            SimpleDateFormat formatoSalida = new SimpleDateFormat("yyyy-MM-dd");

            String date = formatoSalida.format(sqlDate);
            while (actualLT.isBefore(finLT)) {
                LocalTime horaSiguiente = actualLT.plusHours(1);
                Boolean disponible = asesoriaControlRepository.Disponibilidad(Time.valueOf(actualLT),Time.valueOf(horaSiguiente), Date.valueOf(date));
                if(!disponible){

                    HorarioDTO horarioDTO = new HorarioDTO();

                    horarioDTO.setTime_Hora_Inicio(actualLT.toString());
                    horarioDTO.setTime_Hora_Fin(horaSiguiente.toString());
                    horas.add(horarioDTO);
                }
                actualLT = horaSiguiente;
            }
            status = HttpStatus.OK;
            mapResponse.put("horas", horas);
            mapResponse.put("status", status);

            return new ResponseEntity<>(mapResponse, status);
        } catch (Exception e) {
            String message = e.getMessage();
            LOG_Debug.info("error "+e +" " + e.getCause());
            status = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(mapResponse, status);
        }
    }

    @Override
    public boolean verificarExistenciaHorario(HorarioDTO horarioDTO) {
        boolean flag = false;
        System.out.println("verificar "+horarioDTO);
        try {
            String dia = horarioDTO.getStr_Dia();
            int id_Asesor = horarioDTO.getInt_Id_Asesor();
            List<HorarioVO> listaHorarios = horarioRepository.verificarExistenciaHorario(id_Asesor,dia);
            List<HorarioDTO> horarioDTOS = new ArrayList<>();
            listaHorarios.forEach(horarioVO ->  {horarioDTOS.add(HorarioDTO.fromVO(horarioVO));});

            String diaReg = horarioDTOS.get(0).getStr_Dia();
            System.out.println("del asesor"+id_Asesor+"son"+dia+" "+diaReg);
            if(Objects.equals(dia,diaReg)){
                flag = true;
                System.out.println("is"+flag);
            }else{
                flag = false;
                System.out.println("is"+flag);
            }
            return flag;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
}
