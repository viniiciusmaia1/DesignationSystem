package com.br.ativatelecom.designationSystem.enuns;

import jakarta.persistence.Table;
import lombok.Getter;

@Table(name = "PRODUTO")
@Getter
public enum ProdutoEnum {
    IP_CONNECT("IP CONNECT",1),
    VPN_VIP("VPN VIP",2);

    private final String produto;
    private final int id;

    ProdutoEnum(String produto, int id) {
        this.produto=produto;
        this.id=id;
    }
}
