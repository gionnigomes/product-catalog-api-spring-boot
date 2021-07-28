package br.com.products.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.products.model.Product;
import br.com.products.repository.ProductCustomRepository;
import br.com.products.repository.ProductRepository;
import br.com.products.service.ProductService;
import br.com.products.service.exception.FieldValidatorException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/products")
@Api(value = "Product Catalog - API Rest")
@CrossOrigin(origins = "*")
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
	@ApiOperation(value = "Returns full list of products")
	public ResponseEntity<List<Product>> findByAll() {
		return ResponseEntity.ok(productsService.findProductAll());
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "Returns list of products by id")
	public ResponseEntity<Optional<Product>> findProductById(@PathVariable String id) {
		return ResponseEntity.ok(productsService.findById(id));
	}

	
	@PostMapping
	@ApiOperation(value = "Register products")
	public ResponseEntity<Optional<Product>> saveProduct(@RequestBody @Valid Product products) {
		Optional<Product> product = productsService.saveProduct(products);
		return ResponseEntity.status(HttpStatus.CREATED).body(product);
	}

	@PutMapping("/{id}")
	@ApiOperation(value = "Update products")
	public ResponseEntity<Product> updateProduct(@PathVariable(value = "id") String id,
			@Valid @RequestBody Product newProduct) {
		Product product = productsService.updateProduct(id, newProduct);
		return ResponseEntity.ok().body(product);
	}

	
	@DeleteMapping("/{id}")
	@ApiOperation(value = "Delete products by id")
	public void deleteProduct(@PathVariable(value = "id") String id) {
		Optional<Product> delete = productsService.deleteProduct(id);
		delete.orElseThrow(() -> new FieldValidatorException("Unable to delete. Product id not found."));
	}

	@GetMapping("/search")
	@ApiOperation(value = "It performs custom and dynamic search, passing parameters such as q, min_price and max_price")
	public List<Product> findByProductCustom(@RequestParam(value = "q", required = false) String q,
			@RequestParam(value = "min_price", required = false) Double minPrice,
			@RequestParam(value = "max_price", required = false) Double maxPrice) {

		List<Product> queryResultList = this.productCustomRepository.find(q, minPrice, maxPrice);
		return queryResultList;

	}

}
