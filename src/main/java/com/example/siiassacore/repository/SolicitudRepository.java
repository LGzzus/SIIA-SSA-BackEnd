package com.example.siiassacore.repository;

import com.example.siiassacore.model.asesor.AsesorVO;
import com.example.siiassacore.model.solicitud.SolicitudVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SolicitudRepository extends JpaRepository<SolicitudVO, Integer>{

    @Query ("SELECT s FROM SolicitudVO s WHERE s.ing_Id_Persona = :lngIdPersona AND s.str_Solicitud_Estado = 'PENDIENTE'")
    List<SolicitudVO> solicitudPendiente (@Param("lngIdPersona") int lngIdPersona);
}
