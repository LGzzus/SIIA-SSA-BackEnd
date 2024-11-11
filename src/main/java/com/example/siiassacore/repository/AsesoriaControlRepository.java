package com.example.siiassacore.repository;

import com.example.siiassacore.model.asesoriaAsignacion.AsesoriaAsignacionVO;
import com.example.siiassacore.model.asesoriaControl.AsesoriaControlVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public interface AsesoriaControlRepository extends JpaRepository<AsesoriaControlVO,Integer> {
    @Query("SELECT EXISTS ( SELECT ac FROM AsesoriaControlVO ac WHERE ac.timeHoraInicio = :horaInicio " +
            "AND ac.tiemHoraFin = :horaFin AND ac.dateDiaAsesoria = :fechaDiaAsesoria) AS conDisponibilidad")
    Boolean Disponibilidad(@Param("horaInicio") Time horaInicio, @Param("horaFin") Time horaFin, @Param("fechaDiaAsesoria") Date fechaDiaAsesoria);

    @Query("SELECT DISTINCT ac FROM AsesoriaControlVO ac JOIN AsesoriaAsignacionVO aa ON ac.idAsesoria = aa.id_Asesoria WHERE aa.id_Asesor = :idAsesor and  ac.strIdPerido = :strPeriodo")
    List<AsesoriaControlVO> obtenerAsesorias (@Param("idAsesor") int idAsesor, @Param("strPeriodo") String strPeriodo);

    @Query("SELECT ac FROM AsesoriaControlVO ac WHERE ac.idAsesoria = :idAsesoria")
    AsesoriaControlVO informacionAsesoria(@Param("idAsesoria") int idAsesoria);
}
