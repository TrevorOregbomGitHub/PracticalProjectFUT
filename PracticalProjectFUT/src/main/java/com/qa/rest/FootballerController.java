package com.qa.rest;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.qa.entity.Footballer;
import com.qa.service.FootballerService;

@CrossOrigin
@RestController
public class FootballerController {

	@Autowired
	private FootballerService service; // dependency

	@GetMapping("/demoFootballer")
	public Footballer getDemoFootballer() {
		return new Footballer("Kylian Mbappe", "ST", "TOTS", 97);
	}

	@GetMapping("/getFootballer/{id}")
	public Footballer getById(@PathVariable int id) {
		return this.service.getById(id);
	}

	@GetMapping("/getFootballer")
	public List<Footballer> getAll() {
		return this.service.getAll();
	}

	@GetMapping("/getFootballerByName/{name}")
	public Footballer getFootballerByName(@PathVariable String name) {
		return this.service.findByName(name);
	}

	@PostMapping("/createFootballer")
	public ResponseEntity<Footballer> create(@RequestBody Footballer footballer) {
		System.out.println("Created: " + footballer);
		Footballer created = this.service.create(footballer);

		return new ResponseEntity<Footballer>(created, HttpStatus.CREATED);
	}

	@PatchMapping("/updateFootballer/{id}")
	public Footballer update(@PathVariable("id") int id, @PathParam("name") String name,
			@PathParam("position") String position, @PathParam("card type") String cardType,
			@PathParam("rating") Integer rating) {
		return this.service.update(id, name, position, cardType, rating);
	}

	// id = 4494
	@DeleteMapping("/removeFootballer/{id}")
	public ResponseEntity<?> delete(@PathVariable int id) {
		this.service.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
