package com.example.siiassacore.service.impl;

import com.example.siiassacore.endpoint.AsesorEndpoint;
import com.example.siiassacore.model.asesor.AsesorDTO;
import com.example.siiassacore.model.asesor.AsesorVO;
import com.example.siiassacore.model.solicitud.SolicitudVO;
import com.example.siiassacore.repository.AsesorRepository;
import com.example.siiassacore.repository.SolicitudRepository;
import com.example.siiassacore.service.AsesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.logging.Logger;

@Service
public class AsesorServiceImpl implements AsesorService {
    private final static Logger LOG_Debug = Logger.getLogger(AsesorServiceImpl.class.getName());
    @Autowired
    AsesorRepository asesorRepository;
    @Autowired
    SolicitudRepository solicitudRepository;
    @Autowired
    RestTemplate restTemplate;

    //Metodo para asignar rol al docente
    @Override
    public ResponseEntity<Map<String, Object>> asignarRolAsesor(AsesorDTO asesorDTO) {
        String message = "";
        HttpStatus httpStatus = null;

        Map<String,Object> mapResponse = new HashMap<>();
        try{
            if(!validarRolAsesor(asesorDTO.getIntIdPersona(), asesorDTO.getStrIdPeriodo())){

                AsesorVO asesorVO = new AsesorVO();
                asesorVO.setInt_Id_Persona(asesorDTO.getIntIdPersona());
                asesorVO.setStr_NB_Tutor(asesorDTO.getStrNBTutor());
                asesorVO.setInt_Id_Programa(asesorDTO.getIntIdPrograma());
                asesorVO.setStr_Id_Periodo(asesorDTO.getStrIdPeriodo());
                asesorVO.setInt_Estatus(false); //Siempre estara en false hasta que firme la solicitud
                asesorRepository.save(asesorVO);


                SolicitudVO solicitudVO = new SolicitudVO();
                solicitudVO.setIng_Id_Persona(asesorDTO.getIntIdPersona());
                solicitudVO.setAsesor(asesorVO);
                solicitudVO.setStr_Solicitud_Estado(SolicitudVO.EstadoSolicitud.PENDIENTE);
                solicitudRepository.save(solicitudVO);
                message = "Rol asignado correctamente";
                httpStatus = HttpStatus.OK;
            }else{
                message = "El docente ya cuenta con el rol de asesor";

                httpStatus = HttpStatus.CONFLICT;
            }
            mapResponse.put("message",message);
            mapResponse.put("statusCode",httpStatus.value());

            return new ResponseEntity<>(mapResponse, httpStatus);
        }catch (Exception e ) {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

            mapResponse.put("message",e);
            mapResponse.put("statusCode",httpStatus.value());

            return new ResponseEntity<>(mapResponse,httpStatus);
        }
    }
    //Metodo para obtener a todos los docentes o asesores con ciertos atributos
    @Override
    public ResponseEntity<Map<String, Object>> obtenerAsesores(AsesorDTO asesorDTO) {
        String message = "";
        HttpStatus httpStatus = null;

        Map<String,Object> mapResponse = new HashMap<>();
        List<AsesorDTO> asesorDTOS = new ArrayList<>();
        List<AsesorVO> asesorVOS = new ArrayList<>();
        try{
            if(asesorDTO.getStrNBTutor() != "" && asesorDTO.getStrNBTutor() != null){
                asesorVOS = asesorRepository.obtenerAsesor(asesorDTO.getStrNBTutor(), asesorDTO.getStrIdPeriodo(),asesorDTO.getIntIdPrograma());
                asesorVOS.forEach(asesorVO -> asesorDTOS.add(AsesorDTO.fromVO(asesorVO)));

                message = "Se encontraron coincidencias";
                httpStatus = HttpStatus.OK;

            }else{
                asesorVOS = asesorRepository.todosAsesores(asesorDTO.getStrIdPeriodo(),asesorDTO.getIntIdPrograma());
                asesorVOS.forEach(asesorVO -> asesorDTOS.add(AsesorDTO.fromVO(asesorVO)));

                message = "Se encontraron coincidencias";
                httpStatus = HttpStatus.OK;
            }
            mapResponse.put("message",message);
            mapResponse.put("statusCode",httpStatus.value());
            mapResponse.put("lista_Asesores",asesorDTOS);

            return new ResponseEntity<>(mapResponse, httpStatus);
        }catch (Exception e) {
            message = "Ups ocurrio un error... Intentalo mas tarde";
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

            mapResponse.put("message",message);
            mapResponse.put("statusCode",httpStatus.value());
            return new ResponseEntity<>(mapResponse, httpStatus);
        }
    }

    //Verificar si ya contiene el rol de asesor
    @Override
    public ResponseEntity<Map<String, Object>> soyAsesor(AsesorDTO asesorDTO) {
        String mensaje = "";
        HttpStatus status = null;

        Map<String,Object> mapResponse = new HashMap<>();
        LOG_Debug.info(String.valueOf(asesorDTO));
        try{
            String nombreAsesor = asesorDTO.getStrNBTutor();

            List<AsesorDTO> asesorDTOList = new ArrayList<>();
            List<AsesorVO> asesorConsulta = asesorRepository.tieneRolAsesor(nombreAsesor);
            if(!asesorConsulta.isEmpty()){
                LOG_Debug.info(String.valueOf(asesorDTO));
                asesorConsulta.forEach(asesorVO -> { asesorDTOList.add(AsesorDTO.fromVO(asesorVO));});
                LOG_Debug.info(String.valueOf(asesorDTOList));
                String nombreAse = asesorDTOList.get(asesorDTOList.size()-1).getStrNBTutor();
                System.out.println("consulta: "+asesorDTOList.get(asesorDTOList.size()-1));

                if(Objects.equals(nombreAsesor, nombreAse)){
                    mensaje = "Es asesor";
                    mapResponse.put("mensaje",mensaje);
                    mapResponse.put("dataAsesor",asesorDTOList.get(asesorDTOList.size()-1));
                    status = HttpStatus.OK;

                }else {
                    System.out.println("no entro");
                    return null;
                }
            }
            return new ResponseEntity<>(mapResponse,status);
        }catch (Exception e){
            System.out.println("Arrojo Exception");
            System.out.println(e.getMessage());
            return null;
        }
    }


    //Metodo para validar que el docente ya tiene rol asesor
    public boolean validarRolAsesor(int idPlantillaEmpleado, String strIntPeriodo){
        try{
            List<AsesorDTO> asesorDTOS = new ArrayList<>();
            List<AsesorVO> asesorVOS = asesorRepository.validarAsesor(idPlantillaEmpleado,strIntPeriodo);

            asesorVOS.forEach(asesorVO -> asesorDTOS.add(AsesorDTO.fromVO(asesorVO)));
            //Esta linea se borra solo sera prueba
            if(Objects.equals(idPlantillaEmpleado, asesorDTOS.get(0).getIntIdPersona()) && Objects.equals(strIntPeriodo,asesorDTOS.get(0).getStrIdPeriodo())){
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            return false;
        }
    }
}
