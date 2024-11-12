package com.example.siiassacore.service.impl;

import com.example.siiassacore.model.asesoriaEncuesta.AsesoriaEncuestaDTO;
import com.example.siiassacore.model.asesoriaEncuesta.AsesoriaEncuestaVO;
import com.example.siiassacore.repository.AsesoriaEncuestaRepository;
import com.example.siiassacore.service.AsesoriaEncuestaService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Service
public class AsesoriaEncuestaImpl implements AsesoriaEncuestaService {

    private final static Logger LOG_debug = Logger.getLogger(AsesoriaServiceImpl.class.getName());

    @Autowired
    private AsesoriaEncuestaRepository encuestaRepository;
    @Override
    public ResponseEntity<Map<String,Object>> hayencuestasPendientes(String str_Matricula) {
        Boolean encuestasP = false;
        try{
            LOG_debug.info("adasdasdasdasdasdasdasdass"+str_Matricula);
            List<AsesoriaEncuestaVO> listAsesoriasPendientes = encuestaRepository.listaEncuestasPendientes(str_Matricula);
            listAsesoriasPendientes.forEach( asesoriaEncuestaVO -> LOG_debug.info("adadasd" + asesoriaEncuestaVO));
            if(listAsesoriasPendientes.size() > 0){
                encuestasP = true;
            }
        }catch (Exception e) {
            encuestasP = false;
        }
        return null;
    }

    @Override
    public List<AsesoriaEncuestaDTO> listaEncuestas(int idProgramaEducativo, String str_Periodo, String str_Matricula) {
        LOG_debug.info("Parametros2...."+ idProgramaEducativo+ " "+str_Periodo + " "+str_Matricula);
        try {
            List<AsesoriaEncuestaDTO> encuestaDTOS = new ArrayList<>();
            List<Object[]> listAsesorias = encuestaRepository.listaEncuestaSeleccion(str_Matricula,str_Periodo,idProgramaEducativo);


            listAsesorias.forEach(la -> {
                LOG_debug.info("LAaaaaa " + la.length);
                LOG_debug.info("entra ");
                AsesoriaEncuestaVO encuestaVO =  (AsesoriaEncuestaVO) la[0];
                LOG_debug.info("entra2 ");
                String fecha =  String.valueOf( la[1]);
                LOG_debug.info("emcuesta "+encuestaVO);
                LOG_debug.info("Fecha "+fecha);

                String nombre =  String.valueOf( la[2]);
                LOG_debug.info("Nombre "+nombre);

                encuestaDTOS.add(AsesoriaEncuestaDTO.fromVO(encuestaVO, fecha.toString(),nombre.toString()));
                LOG_debug.info("dto "+encuestaDTOS);

            });

            LOG_debug.info("juasjuasjuas"+encuestaDTOS);
            return encuestaDTOS;
        }catch (Exception e){
            LOG_debug.info("Exception "+e.getMessage());
        }
        return null;
    }
}
