package com.example.siiassacore;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.DayOfWeek;

@Configuration
public class utils {
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    public DayOfWeek convertirDia(String diaEnEspanol) {
        switch (diaEnEspanol.toLowerCase()) {
            case "lunes":
                return DayOfWeek.MONDAY;
            case "martes":
                return DayOfWeek.TUESDAY;
            case "miércoles":
            case "miercoles":
                return DayOfWeek.WEDNESDAY;
            case "jueves":
                return DayOfWeek.THURSDAY;
            case "viernes":
                return DayOfWeek.FRIDAY;
            case "sábado":
            case "sabado":
                return DayOfWeek.SATURDAY;
            case "domingo":
                return DayOfWeek.SUNDAY;
            default:
                throw new IllegalArgumentException("Día inválido: " + diaEnEspanol);
        }
    }

}
