package br.com.products.controller;

import java.util.List;
import java.util.Optional;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.products.model.Product;
import br.com.products.repository.ProductCustomRepository;
import br.com.products.repository.ProductRepository;
import br.com.products.service.ProductService;
import br.com.products.service.exception.FieldValidatorException;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productsService;
	
	@SuppressWarnings("unused")
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ProductCustomRepository productCustomRepository;
	
	public ProductController() {
		
	}
	
	public ProductController(ProductCustomRepository productCustomRepository) {
		this.productCustomRepository = productCustomRepository;
	}
	
	@GetMapping
	public ResponseEntity<List<Product>> findByAll() {
		return ResponseEntity.ok(productsService.findProductAll());
	}


	@GetMapping("/{id}")
	public ResponseEntity<Optional<Product>> findProductById(@PathVariable String id) {
		return ResponseEntity.ok(productsService.findById(id));
	}

	@PostMapping
	public ResponseEntity<Optional<Product>> saveProduct(@RequestBody @Valid Product products) {
		Optional<Product> product = productsService.saveProduct(products);
		return ResponseEntity.ok().body(product);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable(value = "id") String id, @Valid @RequestBody Product newProduct) {
		Product product = productsService.updateProduct(id, newProduct);
		return ResponseEntity.ok().body(product);
	}
	
	@Transactional
	@DeleteMapping("/{id}")
	public void deleteProduct(@PathVariable(value="id") String id){
		Optional<Product> delete = productsService.deleteProduct(id);
		delete.orElseThrow(() -> new FieldValidatorException("Unable to delete. Product id not found."));
	}
	
	@GetMapping("/search")
	public List<Product> findByProductCustom (
			@RequestParam(value="q", required = false) String q,
			@RequestParam(value="min_price", required = false) Double minPrice,
			@RequestParam(value="max_price", required = false) Double maxPrice){
		
		List<Product> queryResultList = this.productCustomRepository.find(q, minPrice, maxPrice);
		return queryResultList;
		
	}



	//TO DO: Implementar Search com valor Min, Max e Q

}
