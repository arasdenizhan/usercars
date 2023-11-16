package com.denzhn.usercars.helper;

public interface PopulateHelper<D,E> {
    E populateToEntity(D dto);
    D populateToDto(E entity);
}
