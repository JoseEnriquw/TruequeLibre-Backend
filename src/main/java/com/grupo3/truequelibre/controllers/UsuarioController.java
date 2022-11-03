package com.grupo3.truequelibre.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.grupo3.truequelibre.interfaces.IUsuarioServices;
import com.grupo3.truequelibre.services.UsuarioService.CreateUsuarioRequest;

@RestController
@RequestMapping(path="/usuario")
public class UsuarioController extends ControllerBase{

	@Autowired
	IUsuarioServices service;
	
	@GetMapping
	public ResponseEntity Get(){return Result(service.getAll());}
	
	@GetMapping("/{email}")
	public ResponseEntity GetByEmail(@PathVariable String email){return Result(service.getByEmail(email));}
	
	@PostMapping()
	public ResponseEntity Create(@RequestBody CreateUsuarioRequest usuario) {return Result(service.create(usuario));}
		
}
