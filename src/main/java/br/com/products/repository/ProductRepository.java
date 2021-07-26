package br.com.products.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.products.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	List<Product> findByName(String productName);
	List<Product> findByNameContains (String name);
	Optional<Product> findById (String id);
	List<Product> findAll();
	void deleteById(String id);
	Optional<Product> save(Optional<Product> prodAux);

}
