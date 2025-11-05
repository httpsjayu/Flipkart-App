package in.jay.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.jay.entity.Cart;

public interface CartRepo extends JpaRepository <Cart,Integer> {

}
