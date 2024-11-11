package com.example.siiassacore.repository;

import com.example.siiassacore.model.horario.HorarioVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HorarioRepository extends JpaRepository<HorarioVO,Integer> {

    @Query("SELECT h FROM HorarioVO h WHERE h.id_Asesor.id_Asesor = :int_Id_Asesor")
    List<HorarioVO> obtenerHorarios (@Param("int_Id_Asesor") Integer int_Id_Asesor);

    @Query("SELECT h FROM HorarioVO h WHERE h.id_Asesor.id_Asesor = :int_Id_Asesor AND h.str_Dia = :str_Dia")
    List<HorarioVO> verificarExistenciaHorario(@Param("int_Id_Asesor") int int_Id_Asesor,@Param("str_Dia") String str_Dia);

    @Query("SELECT h.str_Dia FROM  HorarioVO  h where  h.id_Asesor.id_Asesor = :int_Id_Asesor")
    List<?> obtenerDiasHorario(@Param("int_Id_Asesor") int int_Id_Asesor);

    @Query("SELECT h.time_Hora_Inicio, h.time_Hora_Fin FROM HorarioVO h where h.id_Asesor.id_Asesor = :int_Id_Asesor AND h.str_Dia = :str_Dia ")
    List<?> horasDeAtencionDiaria(@Param("int_Id_Asesor") int int_Id_Asesor, @Param("str_Dia") String str_Dia);

}
