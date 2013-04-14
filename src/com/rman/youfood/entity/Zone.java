package com.rman.youfood.entity;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
@Entity
public class Zone {
	@Id @GeneratedValue
	private Long id;
	@ManyToOne
	@JoinColumn(name="restaurant")
	private Restaurant restaurant;
	@Column(unique=true, nullable=false)
	@OneToMany(mappedBy="zone",cascade= CascadeType.REMOVE)
	private Collection<Tabula> tables;
	private String name;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@XmlTransient
	public Restaurant getRestaurant() {
		return restaurant;
	}
	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	public Collection<Tabula> getTables() {
		return tables;
	}
	public void setTables(Collection<Tabula> tables) {
		this.tables = tables;
	}
}
