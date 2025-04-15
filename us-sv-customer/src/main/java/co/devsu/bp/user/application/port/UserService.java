package co.devsu.bp.user.application.port;

import co.devsu.bp.user.application.port.input.UserServicePort;
import co.devsu.bp.user.application.port.output.UserPersistencePort;
import co.devsu.bp.user.domain.User;
import co.devsu.bp.util.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
class UserService implements UserServicePort {

    private final UserPersistencePort userPersistence;

    @Override
    public User createUser(User user) {
        return userPersistence.createUser(user);
    }

    @Override
    public User getUserById(String userId) throws NotFoundException {
        return userPersistence.getUserById(userId);
    }
}
