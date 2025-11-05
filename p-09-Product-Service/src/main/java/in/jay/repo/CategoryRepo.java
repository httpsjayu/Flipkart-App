package in.jay.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.jay.entity.Category;

public interface CategoryRepo extends JpaRepository<Category,Integer> {

	
}
