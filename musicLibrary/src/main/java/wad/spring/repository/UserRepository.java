package wad.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wad.spring.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
    public User findByOpenIdIdentifier(String openIdIdentifier);
}
