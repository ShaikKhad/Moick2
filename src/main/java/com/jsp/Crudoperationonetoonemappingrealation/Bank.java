package com.jsp.Crudoperationonetoonemappingrealation;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Bank {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private  long id;
private String accountnumber;
private String bankname;
@OneToOne
@JoinColumn(name="people_id")
private People people;
public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
public String getAccountnumber() {
	return accountnumber;
}
public void setAccountnumber(String accountnumber) {
	this.accountnumber = accountnumber;
}
public String getBankname() {
	return bankname;
}
public void setBankname(String bankname) {
	this.bankname = bankname;
}
public People getPeople() {
	return people;
}
public void setPeople(People people) {
	this.people = people;
}
}
