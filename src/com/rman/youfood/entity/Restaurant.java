package com.rman.youfood.entity;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.rman.youfood.entity.Zone;

@XmlRootElement
@Entity
public class Restaurant {
	@Id @GeneratedValue
	private Long id;
	@Column(unique=true, nullable=false) //BDD constraints
	private String name;
	@OneToOne
	@JoinColumn(name="menu")
	private Menu menu;
	@OneToMany(mappedBy="restaurant",cascade= CascadeType.REMOVE)
	private Collection<Zone> zones;
	@OneToMany(mappedBy="restaurant",cascade= CascadeType.REMOVE)
	private Collection<Waiter> waiters;
	
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
	public Menu getMenu() {
		return menu;
	}
	public void setMenu(Menu menu) {
		this.menu = menu;
	}
	public Collection<Zone> getZones() {
		return zones;
	}
	public void setZones(Collection<Zone> zones) {
		this.zones = zones;
	}
	@XmlTransient
	public Collection<Waiter> getWaiters() {
		return waiters;
	}
	public void setWaiters(Collection<Waiter> waiters) {
		this.waiters = waiters;
	}
}
