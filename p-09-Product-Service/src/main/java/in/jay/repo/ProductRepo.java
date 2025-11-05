package in.jay.repo;



import org.springframework.data.jpa.repository.JpaRepository;

import in.jay.entity.Product;

public interface ProductRepo extends JpaRepository<Product,Integer>{

}
