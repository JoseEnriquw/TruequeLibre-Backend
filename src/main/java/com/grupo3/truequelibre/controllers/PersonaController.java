package com.grupo3.truequelibre.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.grupo3.truequelibre.interfaces.IPersonaServices;
import com.grupo3.truequelibre.services.PersonaService.UpdatePersonaRequest;
import com.grupo3.truequelibre.viewmodels.UpdatePersonaVM;

@RestController
@RequestMapping(path="api/v1/persona")
public class PersonaController extends ControllerBase{
	@Autowired
	IPersonaServices service;
	
	@GetMapping("/{id}")
	public ResponseEntity<?> Get(@PathVariable Integer id) {return Result( service.getById(id));}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> Update(@PathVariable Integer id,@RequestBody UpdatePersonaVM oferta)
	{return Result(service.updateImg(new UpdatePersonaRequest(id,oferta.imagen())));}


}
