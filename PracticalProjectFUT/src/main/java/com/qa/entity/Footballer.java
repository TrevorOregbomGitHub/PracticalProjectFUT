package com.qa.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // Footballer Table
public class Footballer {

	@Id // PK
	@GeneratedValue(strategy = GenerationType.IDENTITY) // AI

	private Integer id;
	private String name;
	private String position;
	private String cardType;
	private int rating;

	public Footballer() {
		super();
	}

	public Footballer(String name, String position, String cardType, int rating) {
		super();
		this.name = name;
		this.position = position;
		this.cardType = cardType;
		this.rating = rating;
	}

	public Footballer(Integer id, String name, String position, String cardType, int rating) {
		super();
		this.id = id;
		this.name = name;
		this.position = position;
		this.cardType = cardType;
		this.rating = rating;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	@Override
	public String toString() {
		return "Footballer [id=" + id + ", name=" + name + ", position=" + position + ", cardType=" + cardType
				+ ", rating=" + rating + "]";
	}

}