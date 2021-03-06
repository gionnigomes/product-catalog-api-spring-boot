package br.com.products.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.products.model.Product;
import br.com.products.repository.ProductRepository;
import br.com.products.service.exception.FieldValidatorException;
import br.com.products.service.exception.ProductNotFoundException;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public List<Product> findProductAll() {
		return productRepository.findAll();
	}

	public Optional<Product> findById(String id) {
		Optional<Product> products = productRepository.findById(id);
		return Optional
				.of(products.orElseThrow(() -> new ProductNotFoundException("Product id " + id + " not found.")));
	}
	
	@Transactional
	public Optional<Product> saveProduct(Product products) {
		Product prod = productRepository.save(products);
		Optional<Product> prodAux = Optional.ofNullable(prod);
		prodAux.orElseThrow(() -> new FieldValidatorException("Product not save. Verify Fields."));
		return prodAux;
	}

	public List<Product> findProductByName(String name) {
		if (name == null) {
			return productRepository.findAll();
		} else {
			List<Product> products = productRepository.findByName(name);
			return products;
		}
	}

	@Transactional
	public Product updateProduct(String id, Product newProduct) {
		Optional<Product> oldProduct = findById(id);
		Product product = null;
		if (oldProduct.isPresent()) {
			product = oldProduct.get();
			product.setName(newProduct.getName());
			product.setDescription(newProduct.getDescription());
			product.setPrice(newProduct.getPrice());
			this.productRepository.save(product);
			return product;
		}
		return product;
	}

	@Transactional
	public Optional<Product> deleteProduct(String id) {
		Optional<Product> product = productRepository.findById(id);
		if (product.isPresent()) {
			productRepository.deleteById(id);
		}
		return product;
	}

}
