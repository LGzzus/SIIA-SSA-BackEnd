package com.example.siiassacore.model.horario;

import com.example.siiassacore.model.asesor.AsesorVO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;

@Entity
@Table(name = "horarios_atencion")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HorarioVO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int int_Id_Horario;

    @ManyToOne
    @JoinColumn(name = "int_Id_Asesor")
    AsesorVO id_Asesor ;

    String str_Dia;
    Time time_Hora_Inicio;
    Time time_Hora_Fin;
}
