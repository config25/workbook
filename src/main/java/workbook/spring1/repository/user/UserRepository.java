package workbook.spring1.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import workbook.spring1.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {


}
