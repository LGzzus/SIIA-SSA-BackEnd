package com.example.siiassacore.model.solicitud;

import com.example.siiassacore.model.asesor.AsesorVO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "solicitud")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SolicitudVO {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)

    @Column(name = "id_Solicitud")
    int int_Id_Solicitud;

    @Column(name = "lng_Id_Persona")
    int ing_Id_Persona;

    @ManyToOne
    @JoinColumn(name = "int_Id_Asesor")
    AsesorVO asesor;

    @Column(name = "str_Solicitud_Estado")
    @Enumerated(EnumType.STRING)
    private EstadoSolicitud str_Solicitud_Estado;

    public enum EstadoSolicitud{
        PENDIENTE,
        FIRMADA,
        RECHAZADA
    }
}
