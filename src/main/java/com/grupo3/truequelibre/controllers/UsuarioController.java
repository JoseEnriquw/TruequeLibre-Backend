package com.grupo3.truequelibre.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.grupo3.truequelibre.interfaces.IUsuarioServices;
import com.grupo3.truequelibre.services.UsuarioService.CreateUsuarioRequest;
import com.grupo3.truequelibre.services.UsuarioService.LoginUsuarioRequest;
import com.grupo3.truequelibre.services.UsuarioService.RecuperarUsuarioRequest;
import com.grupo3.truequelibre.services.UsuarioService.RecuperarVerificarUsuarioRequest;
import com.grupo3.truequelibre.services.UsuarioService.UpdateUsuarioRequest;
import com.grupo3.truequelibre.viewmodels.UpdateUsuarioVM;

@RestController
@RequestMapping(path="/usuario")
public class UsuarioController extends ControllerBase{

	@Autowired
	IUsuarioServices service;
	
	@GetMapping
	public ResponseEntity<?> Get(){return Result(service.getAll());}
	
	@GetMapping("/{email}")
	public ResponseEntity<?> GetByEmail(@PathVariable String email){return Result(service.getByEmail(email));}
	
	@PostMapping()
	public ResponseEntity<?> Create(@RequestBody CreateUsuarioRequest usuario) {return Result(service.create(usuario));}
	
	@PutMapping("/{email}")
	public ResponseEntity<?> Update(@PathVariable String email,@RequestBody UpdateUsuarioVM usuario) {return Result(service.update(new UpdateUsuarioRequest(email,usuario.nombre(),usuario.apellido(),usuario.direccion(),usuario.telefono(),usuario.localidad(),usuario.fechaNacimiento(),usuario.contrasenia())));}
	
	@DeleteMapping("/{email}")
	public ResponseEntity<?> Delete(@PathVariable String email) { return Result(service.delete(email));};
	
	@PostMapping("/login")
	public ResponseEntity<?> Login(@RequestBody LoginUsuarioRequest request){return Result(service.login(request)); };
	
	@GetMapping("/recuperar/{email}")
	public ResponseEntity<?> Recuperar(@PathVariable String email){return Result(service.recuperar(new RecuperarUsuarioRequest(email))); };
	
	@PostMapping("/recuperar")
	public ResponseEntity<?> VerificarRecuperar(@RequestBody RecuperarVerificarUsuarioRequest request){return Result(service.recuperarVerificar(request)); };
		
}
