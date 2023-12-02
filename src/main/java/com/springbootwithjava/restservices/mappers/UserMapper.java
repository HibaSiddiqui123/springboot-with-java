package com.springbootwithjava.restservices.mappers;

import com.springbootwithjava.restservices.dtos.UserMapstructDto;
import com.springbootwithjava.restservices.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "Spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    //User to user DTO

    @Mapping(source = "id", target="userid")
    UserMapstructDto userMapstructDto(User user);


    //LIST OF USERS TO LIST OF USERSMASPSTRUCTDTO
    List<UserMapstructDto> usersToUsersDtos(List<User> users);
}
