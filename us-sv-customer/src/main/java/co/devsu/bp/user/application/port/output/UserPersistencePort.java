package co.devsu.bp.user.application.port.output;

import co.devsu.bp.user.domain.User;
import co.devsu.bp.util.exception.NotFoundException;

public interface UserPersistencePort {
    User createUser(User user);
    User getUserById(String userId) throws NotFoundException;
}
