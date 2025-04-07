package com.br.ativatelecom.designationSystem.utils;

import java.util.Set;

public class LimitListValidator {

    private static final Set<Integer> LIMITS_PERMITIDOS = Set.of(20, 40, 70);

    public static boolean isLimiteValido(int limit) {
        return LIMITS_PERMITIDOS.contains(limit);
    }
}