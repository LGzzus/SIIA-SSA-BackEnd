package com.example.siiassacore.repository;

import com.example.siiassacore.model.asesoriaAsignacion.AsesoriaAsignacionVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AsesoriaAsignacionRepository extends JpaRepository<AsesoriaAsignacionVO,Integer> {

    @Query("SELECT aa FROM AsesoriaAsignacionVO aa WHERE aa.id_Asesoria = :idAsesoria")
    List<AsesoriaAsignacionVO> alumnosAsesoria (@Param("idAsesoria") int idAsesoir);

}
