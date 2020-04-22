package com.smu.multitenantapp.tenant.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "lookup")
public class Lookup {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "lookup_id")
	private Long id;

	@Column(name = "process")
	private String process;
	
	@Column(name = "Workflow")
	private String workflow;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProcess() {
		return process;
	}

	public void setProcess(String process) {
		this.process = process;
	}

	public String getWorkflow() {
		return workflow;
	}

	public void setWorkflow(String workflow) {
		this.workflow = workflow;
	}
	
}
