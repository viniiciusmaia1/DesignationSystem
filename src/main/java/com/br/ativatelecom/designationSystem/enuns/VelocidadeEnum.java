package com.br.ativatelecom.designationSystem.enuns;

public enum VelocidadeEnum {
    DOIS(2),
    QUATRO(4),
    SEIS(6),
    OITO(8),
    DEZ(10),
    DEZESSEIS(16);

    public int valor;
    VelocidadeEnum(int valorVelocidade){
        valor=valorVelocidade;
    }
}
