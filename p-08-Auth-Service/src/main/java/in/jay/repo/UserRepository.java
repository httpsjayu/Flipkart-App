package in.jay.repo;
import in.jay.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <User,Integer>{
   public User findByEmail(String email);
}
