package com.denzhn.usercars.helper.impl;

import com.denzhn.usercars.dto.UsersDto;
import com.denzhn.usercars.helper.PopulateHelper;
import com.denzhn.usercars.models.Users;
import org.springframework.stereotype.Component;

@Component
public class UsersPopulateHelper implements PopulateHelper<UsersDto, Users> {
    @Override
    public Users populateToEntity(UsersDto dto) {
        final Users users = new Users();
        users.setName(dto.getName());
        return users;
    }

    @Override
    public UsersDto populateToDto(Users entity) {
        return UsersDto.builder()
                .id(entity.getId())
                .name(entity.getName()).build();
    }
}
