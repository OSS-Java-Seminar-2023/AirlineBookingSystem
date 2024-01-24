package hr.OSSAirline.mappers;

import hr.OSSAirline.dto.UserDto;
import hr.OSSAirline.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "password", target = "password")
    UserDto toDto(User user);

    User toEntity(UserDto userDto);
}
