package co.devsu.bp.user.application.port.input;

import co.devsu.bp.user.domain.User;
import co.devsu.bp.util.exception.NotFoundException;

public interface UserServicePort {
    User createUser(User user);
    User getUserById(String userId) throws NotFoundException;
}
