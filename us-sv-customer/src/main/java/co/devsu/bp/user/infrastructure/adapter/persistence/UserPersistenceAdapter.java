package co.devsu.bp.user.infrastructure.adapter.persistence;

import co.devsu.bp.user.application.port.output.UserPersistencePort;
import co.devsu.bp.user.domain.User;
import co.devsu.bp.user.infrastructure.mapper.UserMapper;
import co.devsu.bp.user.infrastructure.repository.UserRepository;
import co.devsu.bp.util.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
class UserPersistenceAdapter implements UserPersistencePort {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    @Override
    public User createUser(User user) {
        log.info("Creating user with identifier: {}", user.getPhone());
        return userMapper.toDomainFromEntity(
            userRepository.save(
                userMapper.toCreateEntityFromDomain(user)
            )
        );
    }

    @Override
    public User getUserById(String userId) throws NotFoundException {
        log.info("Getting user by id: {}", userId);
        return userRepository.findById(userId)
            .map(userMapper::toDomainFromEntity)
            .orElseThrow(() -> new NotFoundException("User not found"));
    }
}
