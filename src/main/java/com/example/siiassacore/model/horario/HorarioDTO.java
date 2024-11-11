package com.example.siiassacore.model.horario;

import lombok.*;

import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HorarioDTO {
    int int_Id_Horario;
    int int_Id_Asesor;
    String str_Dia;
    String time_Hora_Inicio;
    String time_Hora_Fin;
    boolean sinDisponibilidad;
    public static HorarioDTO fromVO (HorarioVO horarioVO){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a", Locale.ENGLISH);

        LocalTime horaInicio = horarioVO.getTime_Hora_Inicio().toLocalTime();
        LocalTime horaFin = horarioVO.getTime_Hora_Fin().toLocalTime();
        return HorarioDTO.builder()
                .int_Id_Horario(horarioVO.int_Id_Horario)
                .int_Id_Asesor(horarioVO.int_Id_Horario)
                .str_Dia(horarioVO.str_Dia)
                .time_Hora_Inicio(horaInicio.format(formatter))
                .time_Hora_Fin(horaFin.format(formatter))
                .build();
    }
}
