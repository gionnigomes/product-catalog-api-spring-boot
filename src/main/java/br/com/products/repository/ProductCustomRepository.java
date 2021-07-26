package br.com.products.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.com.products.model.Product;

@Repository
public class ProductCustomRepository {
	
	private final EntityManager em;
	
	public ProductCustomRepository(EntityManager em) {
		this.em = em;
	}
	
	
	public List<Product> find (String name, Double min, Double max ){
		String 	query = "select P from product as P ";
		String cond = " where ";
		
		if(name != null) {
			query += cond + " lower(P.name) like :q or lower(P.description) like :q ";
			cond = " and ";
		}
		
		if(min != null) {
			query += cond + " P.price >= :min_price ";
			cond = " and ";
		}
		
		if (max != null) {
			query += cond + " P.price <= :max_price ";
		}
		
		TypedQuery<Product> q = em.createQuery(query, Product.class);
		
		if(name != null) {
			q.setParameter("q", "%"+name.toLowerCase()+"%");
		}
		
		if(min != null) {
			q.setParameter("min_price", min);
		}
		
		if (max != null) {
			q.setParameter("max_price", max);
		}
		
		return q.getResultList();
	}

}
