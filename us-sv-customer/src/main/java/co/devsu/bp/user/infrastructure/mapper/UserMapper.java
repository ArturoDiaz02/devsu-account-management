package co.devsu.bp.user.infrastructure.mapper;

import co.devsu.bp.user.domain.User;
import co.devsu.bp.user.infrastructure.repository.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toDomainFromEntity(UserEntity userEntity);

    @Mapping(target = "userId", ignore = true)
    UserEntity toCreateEntityFromDomain(User user);
}
