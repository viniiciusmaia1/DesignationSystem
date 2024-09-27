package com.br.ativatelecom.designationSystem.converter;

import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Component
public class GenericConverter {

    public <D, E> D convertToDto(E entity, Class<D> dtoClass) {
        D dto;
        try {
            dto = dtoClass.getDeclaredConstructor().newInstance();
            for (Field entityField : entity.getClass().getDeclaredFields()) {
                entityField.setAccessible(true);
                Object value = entityField.get(entity);

                try {
                    Field dtoField = dtoClass.getDeclaredField(entityField.getName());
                    dtoField.setAccessible(true);
                    dtoField.set(dto, value);
                } catch (NoSuchFieldException ignored) {

                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Falha ao converter de entidade para DTO", e);
        }
        return dto;
    }

    public <D, E> E convertToEntity(D dto, Class<E> entityClass) {
        E entity;
        try {
            entity = entityClass.getDeclaredConstructor().newInstance();
            for (Field dtoField : dto.getClass().getDeclaredFields()) {
                dtoField.setAccessible(true);
                Object value = dtoField.get(dto);

                try {
                    Field entityField = entityClass.getDeclaredField(dtoField.getName());
                    entityField.setAccessible(true);
                    entityField.set(entity, value);
                } catch (NoSuchFieldException ignored) {

                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Falha ao converter de DTO para entidade", e);
        }
        return entity;
    }
}
