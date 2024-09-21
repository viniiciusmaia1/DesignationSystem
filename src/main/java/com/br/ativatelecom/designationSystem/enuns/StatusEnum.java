package com.br.ativatelecom.designationSystem.enuns;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDateTime;

@Table(name = "STATUS")
@Getter
public enum StatusEnum {
    AGENDADO("AGENDADO",1),
    AGENDAMENTO("AGENDAMENTO",2),
    CANCELADO("CANCELADO",3),
    ENTREGUE_PORTAL_OI("ENTREGUE PORTAL OI",4),
    ENVIO_RB("ENVIO RB",5),
    HOMOLOGADO("HOMOLOGADO",6),
    INSTALADO("INSTALADO",7),
    NEGOCIAÇÃO ("NEGOCIAÇÃO ",8),
    PENDENCIA_OI("PENDENCIA OI",9),
    REAGENDADO("REAGENDADO",10),
    VIABILIDADE("VIABILIDADE",11);

    private final String status;
    private final int id;

    StatusEnum(String status, int id) {
        this.status=status;
        this.id=id;
    }
}

