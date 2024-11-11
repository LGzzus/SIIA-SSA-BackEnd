package com.example.siiassacore.service.impl;

import com.example.siiassacore.model.asesoriaAsignacion.AsesoriaAsignacionVO;
import com.example.siiassacore.model.asesoriaControl.AsesoriaControlVO;
import com.example.siiassacore.model.asesoriaEncuesta.AsesoriaEncuestaVO;
import com.example.siiassacore.model.asesoriaGeneral.AsesoriaDTO;
import com.example.siiassacore.repository.AsesoriaAsignacionRepository;
import com.example.siiassacore.repository.AsesoriaControlRepository;
import com.example.siiassacore.repository.AsesoriaEncuestaRepository;
import com.example.siiassacore.service.AsesoriaService;
import com.example.siiassacore.utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.logging.Logger;

@Service
public class AsesoriaServiceImpl implements AsesoriaService {
    @Autowired
    private AsesoriaControlRepository asesoriaControlRepository;
    @Autowired
    private AsesoriaAsignacionRepository asesoriaAsignacionRepository;
    @Autowired
    private AsesoriaEncuestaRepository asesoriaEncuestaRepository;
    @Autowired
    private utils utils;

    private final static Logger LOG_debug = Logger.getLogger(AsesoriaServiceImpl.class.getName());

    @Override
    public ResponseEntity<Map<String, Object>> registroAsignacionAsesoria(AsesoriaDTO asesoriaDTO) {
        String message = "";
        HttpStatus httpStatus = null;

        Map<String,Object> mapResponse = new HashMap<>();
        try {
            if (asesoriaDTO != null) {
                LOG_debug.info("Data received: " + asesoriaDTO);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                //Asesoria Control
                AsesoriaControlVO asesoriaControlVO = new AsesoriaControlVO();
                asesoriaControlVO.setTipoAsesoria(asesoriaDTO.getTipoAsesoria());
                asesoriaControlVO.setStrDia(asesoriaDTO.getDiaSeleccionado());

                SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
                java.util.Date utilDate = inputFormat.parse(asesoriaDTO.getFechaCorta());
                Date sqlDate = new Date(utilDate.getTime());
                SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");

                asesoriaControlVO.setDateDiaAsesoria(Date.valueOf(outputFormat.format(sqlDate)));
                LocalDate hoy = LocalDate.now();

                DayOfWeek diaHoy = hoy.getDayOfWeek();

                DayOfWeek diaSeleccionado = utils.convertirDia(asesoriaDTO.getDiaSeleccionado());

                int diasParaelSiguiente = diaSeleccionado.getValue() - diaHoy.getValue();

                if(diasParaelSiguiente <= 0){
                    diasParaelSiguiente += 7;
                }

                LocalDate proximoDia = hoy.plusDays(diasParaelSiguiente);

                LOG_debug.info("ProximoDia"+proximoDia);

                LocalTime localTimeHI = LocalTime.parse(asesoriaDTO.getHoraInicio(),formatter);
                asesoriaControlVO.setTimeHoraInicio(Time.valueOf(localTimeHI));

                LocalTime localTimeHF = LocalTime.parse(asesoriaDTO.getHoraFin(),formatter);
                asesoriaControlVO.setTiemHoraFin(Time.valueOf(localTimeHF));

                asesoriaControlVO.setInt_Id_Programa(asesoriaDTO.getIntIdProgramaCampus());
                asesoriaControlVO.setStrIdPerido(asesoriaDTO.getStrIdPeriodo());
                asesoriaControlVO.setFechaCreacion(Date.valueOf(LocalDate.now()));
                asesoriaControlVO.setStatusAtencion(false);
                AsesoriaControlVO asesoriaControlDTO = asesoriaControlRepository.save(asesoriaControlVO);

                LOG_debug.info("AsesoriaControl saved: " + asesoriaControlDTO);

                asesoriaDTO.getAlumnos().forEach(alumnoDTO -> {
                    LOG_debug.info(""+alumnoDTO);
                    AsesoriaAsignacionVO asesoriaAsignacionVO = new AsesoriaAsignacionVO();

                    asesoriaAsignacionVO.setId_Asesoria(asesoriaControlDTO.getIdAsesoria());
                    asesoriaAsignacionVO.setId_Asesor(asesoriaDTO.getIdAsesor());
                    asesoriaAsignacionVO.setStr_Matricula_Alumno(alumnoDTO.getStrMatriculaAlumno());
                    LOG_debug.info(alumnoDTO.getStrNombreAlumno());
                    asesoriaAsignacionVO.setStr_Nombre_Alumno(alumnoDTO.getStrNombreAlumno());
                    LOG_debug.info(asesoriaAsignacionVO.getStr_Nombre_Alumno());

                    asesoriaAsignacionRepository.save(asesoriaAsignacionVO);
                });
                message = "Asesoria registrada con exito";
                httpStatus = HttpStatus.OK;

                mapResponse.put("message",message);
                mapResponse.put("statusCode",httpStatus.value());

                return new ResponseEntity<>(mapResponse,httpStatus);
                //ResponseEntity.ok().body(Map.of("message", "Asesoria registrada con éxito"));
            } else {
                message = "Datos de asesoria no válido";
                httpStatus = HttpStatus.BAD_REQUEST;

                mapResponse.put("message",message);
                mapResponse.put("statusCode",httpStatus.value());

                return new ResponseEntity<>(mapResponse,httpStatus);
                //return ResponseEntity.badRequest().body(Map.of("error", "Datos de asesoria no válidos"));
            }
        } catch (Exception e) {
            LOG_debug.severe("Error during asesoria registration: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(500).body(Map.of("error", "Error interno del servidor"));
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> obtenerAsesorias(int idAsesor, String str_Id_Periodo) {
        LOG_debug.info("Paraaamsssssssssssssssssss2 " + idAsesor +" "+str_Id_Periodo);
        Map<String,Object> mapResponse = new HashMap<>();
        HttpStatus httpStatus;
        try {
            if(idAsesor != 0 && str_Id_Periodo != null){
                List<AsesoriaControlVO> asesorias = asesoriaControlRepository.obtenerAsesorias(idAsesor,str_Id_Periodo);
                mapResponse.put("asesorias",asesorias);

                if(asesorias.isEmpty()){
                    httpStatus = HttpStatus.NOT_FOUND;
                    mapResponse.put("message","No se encontraron asesorias");
                    LOG_debug.info("Esta vacia la lista");
                }else{
                    httpStatus = HttpStatus.OK;
                    mapResponse.put("message", "Asesorias Obtenidas");
                }


            } else {
                httpStatus = HttpStatus.BAD_REQUEST;
                mapResponse.put("message", "solicitud errone");
            }
            return new ResponseEntity<>(mapResponse, httpStatus);
        }catch (Exception e){
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            mapResponse.put("error", "Error interno del servidor: " + e.getMessage());
            return new ResponseEntity<>(mapResponse, httpStatus);
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> alumnosAsesoria(int idAsesoria) {
        Map<String,Object> mapResponse = new HashMap<>();
        HttpStatus httpStatus = null;
        try {
            if(idAsesoria > 0 ){
                List<AsesoriaAsignacionVO> asignacionVOS = asesoriaAsignacionRepository.alumnosAsesoria(idAsesoria);
                LOG_debug.info("Listaaaaaaaa " + asignacionVOS);
                mapResponse.put("alumnos",asignacionVOS);
                /*Optional<AsesoriaControlVO> asesoriaControlVO = asesoriaControlRepository.findById(idAsesoria);
                LOG_debug.info("dsaddada"+String.valueOf(asesoriaControlVO));*/
                if(asignacionVOS.isEmpty()){
                    httpStatus = HttpStatus.NOT_FOUND;
                    mapResponse.put("message","No hay alumnos relacionados");
                }
                httpStatus = HttpStatus.OK;
                mapResponse.put("message","Alumnos encontrados");
            }
            return new ResponseEntity<>(mapResponse, httpStatus);
        }catch (Exception e){
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            mapResponse.put("error", "Error interno del servidor: " + e.getMessage());
            return new ResponseEntity<>(mapResponse, httpStatus);
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> asistenciaAsesoria(int idAsignacion) {
        LOG_debug.info("Paraaaaaaaaaaaaam"+idAsignacion);

        String message = "";
        HttpStatus httpStatus = null;
        try {
            Optional<AsesoriaAsignacionVO> asistenciaAsesoria = asesoriaAsignacionRepository.findById(idAsignacion);
            if(asistenciaAsesoria.isPresent()){
                AsesoriaAsignacionVO asignacionVO = asistenciaAsesoria.get();
                LOG_debug.info("vo"+String.valueOf(asignacionVO));
                asignacionVO.setStr_Asistencia(AsesoriaAsignacionVO.Asistencia.Asistio);
                asesoriaAsignacionRepository.save(asignacionVO);

                //Asignacion de la encuesta para asesorias
                AsesoriaEncuestaVO asesoriaEncuestaVO = new AsesoriaEncuestaVO();
                asesoriaEncuestaVO.setInt_id_Asesoria_Asignacion(idAsignacion);
                asesoriaEncuestaVO.setInt_Id_Encuesta(1);
                asesoriaEncuestaVO.setDate_Fecha_Asignacion(Timestamp.valueOf(LocalDateTime.now()));
                LOG_debug.info("voAE"+String.valueOf(asesoriaEncuestaVO));
                asesoriaEncuestaRepository.save(asesoriaEncuestaVO);
            }
        }catch (Exception e){

        }
        return null;
    }


}
