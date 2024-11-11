package com.example.siiassacore.repository;

import com.example.siiassacore.model.asesor.AsesorVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AsesorRepository extends JpaRepository <AsesorVO, Integer> {

    @Query("SELECT a FROM AsesorVO a WHERE a.int_Id_Persona  = :int_Id_Plantilla_Empleado AND a.str_Id_Periodo = :str_Id_Periodo")
    List<AsesorVO> validarAsesor (@Param("int_Id_Plantilla_Empleado") int idPlantillaEmpleado , @Param("str_Id_Periodo") String strIdPeriodo);

    //obtiene el asesor de acuerdo a los parametros recibidos
    @Query("SELECT a FROM AsesorVO a WHERE a.str_NB_Tutor LIKE :str_NB_Tutor%  AND a.str_Id_Periodo = :str_Id_Periodo AND a.int_Id_Programa = :int_Id_Programa")
    List<AsesorVO> obtenerAsesor (@Param("str_NB_Tutor") String str_NB_Tutor,@Param("str_Id_Periodo") String str_Id_Periodo,@Param("int_Id_Programa") int int_Id_Programa);

    //Obtiene los asesores de acuerdo al periodo y programa educativo
    @Query("SELECT a FROM AsesorVO a WHERE a.str_Id_Periodo = :str_Id_Periodo AND a.int_Id_Programa = :int_Id_Programa")
    List<AsesorVO> todosAsesores (@Param("str_Id_Periodo") String str_Id_Periodo,@Param("int_Id_Programa") int int_Id_Programa);

    @Query("SELECT a FROM AsesorVO a WHERE a.str_NB_Tutor = :str_NB_Tutor")
    List<AsesorVO> tieneRolAsesor (@Param("str_NB_Tutor") String strNBTutor);

}
