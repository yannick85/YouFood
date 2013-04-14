package com.rman.youfood.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
@Entity
public class Menu {
	@Id @GeneratedValue
	private Long id;
	@Column(nullable=false)
	private String title;
	@ManyToMany(fetch=FetchType.EAGER,cascade=CascadeType.REMOVE)
	@JoinTable(name="productmenuassoc")
	private Collection<Product> products;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@XmlTransient
	public Collection<Product> getProducts() {
		return products;
	}
	public void setProducts(Collection<Product> products) {
		this.products = products;
	}
	public void removeProduct(Product rproduct){
		Boolean bool = true;
		Product tokill = null;
		for (Iterator<Product> iterator = products.iterator(); iterator.hasNext() && bool;) {
			Product product = (Product) iterator.next();
			if(product.getId() == rproduct.getId()){
				tokill = product;
				bool=false;
			}
		}
		products.remove(tokill);
	}
	public List<Product> getProductByType(Integer type){
		List<Product> productsType = new ArrayList<Product>();
		for (Product product : products) {
			if(product.getType() == type){
				productsType.add(product); 
			}
		}
		return productsType;
	}
}
