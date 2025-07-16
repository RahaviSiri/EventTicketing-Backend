package eventticketing.eventease_backend.repositries;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import eventticketing.eventease_backend.models.User;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Long countByEmail(String email);
    User findByEmail(String email);
    Optional<User> findById(Long id);
}
