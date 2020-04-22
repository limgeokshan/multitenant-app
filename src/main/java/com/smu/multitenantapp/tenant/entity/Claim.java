package com.smu.multitenantapp.tenant.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



@Entity
@Table(name = "claim")
public class Claim {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "claim_id")
	private Long id;

	@Column(name = "claimDate")
    @Temporal(TemporalType.TIMESTAMP)
	private Date claimDate = new Date();
	
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    //@JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User employee;

//    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
//    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private User manager;
    
	@Column(name = "amount")
	private double amount;
	
	@Column(name = "details")
	private String details;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getClaimDate() {
		return claimDate;
	}

	public void setClaimDate(Date claimDate) {
		this.claimDate = claimDate;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public User getEmployee() {
		return employee;
	}

	public void setEmployee(User employee) {
		this.employee = employee;
	}

	public User getManager() {
		return manager;
	}

	public void setManagers(User manager) {
		this.manager = manager;
	}
    


}
