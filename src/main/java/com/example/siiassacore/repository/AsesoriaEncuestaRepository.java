package com.example.siiassacore.repository;

import com.example.siiassacore.model.asesoriaEncuesta.AsesoriaEncuestaVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AsesoriaEncuestaRepository extends JpaRepository<AsesoriaEncuestaVO,Integer> {

    @Query("SELECT ae FROM AsesoriaAsignacionVO aa JOIN AsesoriaControlVO ac ON aa.id_Asesoria = ac.idAsesoria JOIN AsesoriaEncuestaVO ae on ae.int_id_Asesoria_Asignacion = aa.id_Asesoria_Asignacion Where aa.str_Matricula_Alumno = :str_Matricula AND ae.boolean_Respondida = false")
    List<AsesoriaEncuestaVO> listaEncuestasPendientes(@Param("str_Matricula") String str_Matricula);

    @Query("SELECT ae,ac.dateDiaAsesoria,ase.str_NB_Tutor FROM AsesoriaAsignacionVO aa  JOIN AsesoriaControlVO ac ON aa.id_Asesoria = ac.idAsesoria "
            + "JOIN AsesoriaEncuestaVO ae on ae.int_id_Asesoria_Asignacion = aa.id_Asesoria_Asignacion "
            + "JOIN AsesorVO ase on ase.id_Asesor = aa.id_Asesor"
            + " WHERE aa.str_Matricula_Alumno = :str_Matricula AND ac.strIdPerido = :str_Periodo AND ac.int_Id_Programa = :int_IdPrograma ")
    List<Object[]> listaEncuestaSeleccion (@Param("str_Matricula")  String str_Matricula, @Param("str_Periodo") String str_Periodo, @Param("int_IdPrograma") int int_IdPrograma);
}
