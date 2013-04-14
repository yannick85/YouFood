package com.rman.youfood.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@XmlRootElement
public class InstructionMenu {
	
	@Id @GeneratedValue
	private Long id;
	
	@ManyToOne
	private Product starter;
	
	@ManyToOne
	private Product principal;
	
	@ManyToOne
	private Product desert;
	
	@ManyToOne
	private Instruction instruction;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Product getStarter() {
		return starter;
	}
	public void setStarter(Product starter) {
		this.starter = starter;
	}
	public Product getPrincipal() {
		return principal;
	}
	public void setPrincipal(Product principal) {
		this.principal = principal;
	}
	public Product getDesert() {
		return desert;
	}
	public void setDesert(Product desert) {
		this.desert = desert;
	}
	@XmlTransient
	public Instruction getInstruction() {
		return instruction;
	}
	public void setInstruction(Instruction instruction) {
		this.instruction = instruction;
	}
	
	public String getDescription(){
		String desc = "";
		
		if(this.starter != null){
			desc = desc + (desc.equals("") ? "" : " - ") + this.starter.getTitle();
		}
		if(this.principal != null){
			desc = desc + (desc.equals("") ? "" : " - ") + this.principal.getTitle();
		}
		if(this.desert != null){
			desc = desc + (desc.equals("") ? "" : " - ") + this.desert.getTitle();
		}
		return desc.equals("") ? "Vous n'avez sélectionné aucun produit pour ce menu" : desc;
	}
}
