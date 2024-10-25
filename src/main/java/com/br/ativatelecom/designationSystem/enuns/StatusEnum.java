package com.br.ativatelecom.designationSystem.enuns;

import jakarta.persistence.Table;
import lombok.Getter;

@Table(name = "STATUS")
@Getter
public enum StatusEnum {
    AGENDADO("AGENDADO",1),
    AGENDAMENTO("AGENDAMENTO",2),
    CANCELADO("CANCELADO",3),
    ENTREGUE("ENTREGUE",4),
    ENVIO_RB("ENVIO RB",5),
    HOMOLOGADO("HOMOLOGADO",6),
    INSTALADO("INSTALADO",7),
    NEGOCIAÇÃO ("NEGOCIAÇÃO",8),
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

