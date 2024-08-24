package com.example.techeertree.dto;

public interface EntityMapper<REQUEST, RESPONSE, ENTITY> {
        ENTITY toEntity(final REQUEST request);
        RESPONSE toDto(final ENTITY entity);
}
