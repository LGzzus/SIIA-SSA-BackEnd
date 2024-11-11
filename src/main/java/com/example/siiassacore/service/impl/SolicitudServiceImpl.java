package com.example.siiassacore.service.impl;

import com.example.siiassacore.model.asesor.AsesorDTO;
import com.example.siiassacore.model.asesor.AsesorVO;
import com.example.siiassacore.model.solicitud.SolicitudDTO;
import com.example.siiassacore.model.solicitud.SolicitudVO;
import com.example.siiassacore.repository.AsesorRepository;
import com.example.siiassacore.repository.SolicitudRepository;
import com.example.siiassacore.service.SolicitudService;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.util.*;
import java.util.logging.Logger;

@Service
public class SolicitudServiceImpl implements SolicitudService {
    private final static Logger LOG_Debug = Logger.getLogger(AsesorServiceImpl.class.getName());
    @Autowired
    SolicitudRepository solicitudRepository;
    @Autowired
    AsesorRepository asesorRepository;
    @Override
    public ResponseEntity<Map<String, Object>> solicitudPendiente(int lngIdPersona) {
        Map<String,Object> mapResponse = new HashMap<>();
        List<SolicitudVO> solicitudVOS = new ArrayList<>();
        List<SolicitudDTO> solicitudDTOS = new ArrayList<>();

        String message = "";
        HttpStatus httpStatus = null;
        LOG_Debug.info("id"+lngIdPersona);
        try{
            LOG_Debug.info("id2 "+lngIdPersona);
            solicitudVOS = solicitudRepository.solicitudPendiente(lngIdPersona);
            solicitudVOS.forEach(solicitudVOF -> {
                SolicitudDTO solicitudDTO = SolicitudDTO.solicitudFromVO(solicitudVOF);
                LOG_Debug.info(String.valueOf(solicitudDTO));
                solicitudDTOS.add(solicitudDTO);
            });
            LOG_Debug.info(String.valueOf(solicitudDTOS.get(0)));
            message = "Asesoria firmada";
            httpStatus = HttpStatus.OK;

            mapResponse.put("solicitudId",solicitudDTOS.get(0).getIntIdSolicitud());
        }
        catch (Exception e){
            message = "Ups... Ocurrio un error";
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

            mapResponse.put("message",message);
            mapResponse.put("statusCode", httpStatus.value());
        }
        return new ResponseEntity<>(mapResponse,httpStatus);
    }

    @Override
    public ResponseEntity<byte[]> firmarSolicitud(int idSolicitud) {
        LOG_Debug.info("id " + idSolicitud);

        String message = "";
        HttpStatus httpStatus = null;

        try {
            Optional<SolicitudVO> solicitudOptional = solicitudRepository.findById(idSolicitud);
            if (solicitudOptional.isPresent()) {
                SolicitudVO solicitudVO = solicitudOptional.get();
                if (solicitudVO.getStr_Solicitud_Estado() == SolicitudVO.EstadoSolicitud.FIRMADA) {
                    message = "La solicitud ya está firmada";
                    httpStatus = HttpStatus.BAD_REQUEST;
                } else {
                    LOG_Debug.info(String.valueOf(solicitudOptional));
                    solicitudVO.setStr_Solicitud_Estado(SolicitudVO.EstadoSolicitud.FIRMADA);
                    solicitudRepository.save(solicitudVO);

                    Optional<AsesorVO> asesorOptional = asesorRepository.findById(solicitudVO.getAsesor().getId_Asesor());
                    if (asesorOptional.isPresent()) {
                        AsesorVO asesorVO = asesorOptional.get();
                        asesorVO.setInt_Estatus(true); // Actualiza el estatus
                        asesorRepository.save(asesorVO); // Guarda el asesor actualizado
                    } else {
                        message = "Asesor no encontrado.";
                        httpStatus = HttpStatus.NOT_FOUND;
                        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                    }

                    // Generar el PDF
                    byte[] pdfBytes = crearDocumentoPDF(solicitudVO);
                    if (pdfBytes != null) {
                        HttpHeaders headers = new HttpHeaders();
                        headers.setContentType(new MediaType("application", "pdf"));
                        headers.setContentDispositionFormData("filename", "solicitud_firmada_" + idSolicitud + ".pdf");
                        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
                    } else {
                        message = "Asesoría firmada, pero no se pudo generar el PDF.";
                        httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
                    }
                }
            } else {
                message = "Solicitud no encontrada.";
                httpStatus = HttpStatus.NOT_FOUND;
            }
        } catch (Exception e) {
            message = "Ups... Ocurrió un error al firmar la asesoría.";
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            LOG_Debug.info("Error al firmar la asesoría: " + e);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<Map<String, Object>> rechazarSolicitud(int idSolicitud) {
        LOG_Debug.info("id "+idSolicitud);

        Map<String,Object> mapResponse = new HashMap<>();

        String message = "";
        HttpStatus httpStatus = null;
        try{
            Optional<SolicitudVO> solicitudOptional = solicitudRepository.findById(idSolicitud);
            if (solicitudOptional.isPresent()){
                SolicitudVO solicitudVO = solicitudOptional.get();
                if(solicitudVO.getStr_Solicitud_Estado() == SolicitudVO.EstadoSolicitud.FIRMADA){
                    message = "La solicitud ya esta firmada";
                    httpStatus = HttpStatus.BAD_REQUEST;
                }else{
                    solicitudVO.setStr_Solicitud_Estado(SolicitudVO.EstadoSolicitud.RECHAZADA);
                    solicitudRepository.save(solicitudVO);

                    message = "Asesoria firmada";
                    httpStatus = HttpStatus.OK;
                }
            }else {
                message = "Solicitud no encontrada.";
                httpStatus = HttpStatus.NOT_FOUND;
            }
        }catch (Exception e){
            message = "Ups... Ocurrió un error al firmar la asesoría.";
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            LOG_Debug.info("Error al firmar la asesoría"+ e);
        }
        mapResponse.put("message", message);
        mapResponse.put("statusCode", httpStatus.value());
        return new ResponseEntity<>(mapResponse, httpStatus);
    }


    private byte[] crearDocumentoPDF(SolicitudVO solicitudVO) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, baos);
            document.open();

            // Agregar contenido al PDF
            document.add(new Paragraph("Solicitud Firmada"));
            document.add(new Paragraph("ID de la Solicitud: " + solicitudVO.getInt_Id_Solicitud()));
            document.add(new Paragraph("Estado: " + solicitudVO.getStr_Solicitud_Estado()));

            // Agregar más contenido si es necesario

            document.close();
            return baos.toByteArray();
        } catch (DocumentException e) {
            LOG_Debug.info("Error al crear el documento PDF: " + e.getMessage());
            return null;
        }
    }


}
