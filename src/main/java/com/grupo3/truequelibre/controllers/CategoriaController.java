package com.grupo3.truequelibre.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.grupo3.truequelibre.interfaces.ICategoriaServices;

@RestController
@RequestMapping(path="api/v1/categoria")
public class CategoriaController extends ControllerBase {

	@Autowired
	ICategoriaServices service;

	@GetMapping
	public ResponseEntity<?> Get(){return Result(service.getAll());}
}
