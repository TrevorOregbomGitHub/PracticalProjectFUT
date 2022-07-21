package com.qa.service;

import java.util.List;

import com.qa.entity.Footballer;

public interface FootballerService {

	Footballer getById(int id);

	List<Footballer> getAll();

	Footballer findByName(String name);

	Footballer create(Footballer footballer);

	Footballer update(int id, String name, String position, String cardType, Integer rating);

	void delete(int id);

}

