package com.rman.youfood.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Instruction {
	
	@Id @GeneratedValue
	private Long id;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;
	
	@OneToMany(mappedBy="instruction",cascade= CascadeType.REMOVE)
	private List<InstructionMenu> menus;
	
	@ManyToOne
	private Tabula table;
	
	private boolean served;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public List<InstructionMenu> getMenus() {
		return menus;
	}
	public void setMenus(List<InstructionMenu> menus) {
		this.menus = menus;
	}
	public Tabula getTable() {
		return table;
	}
	public void setTable(Tabula table) {
		this.table = table;
	}
	public boolean getServed() {
		return served;
	}
	public void setServed(boolean served) {
		this.served = served;
	}
	
	
}
