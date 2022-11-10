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
import com.grupo3.truequelibre.interfaces.IPublicacionServices;
import com.grupo3.truequelibre.services.PublicacionService.CreatePublicacionRequest;
import com.grupo3.truequelibre.services.PublicacionService.GetAllByCategoriaFilterRequest;
import com.grupo3.truequelibre.services.PublicacionService.GetAllByCategoriaRequest;
import com.grupo3.truequelibre.services.PublicacionService.GetByIdRequest;
import com.grupo3.truequelibre.services.PublicacionService.UpdatePublicacionRequest;
import com.grupo3.truequelibre.viewmodels.UpdatePublicacionVM;

@RestController
@RequestMapping(path="api/v1/publicacion")
public class PublicacionController extends ControllerBase {
	@Autowired
	IPublicacionServices service;
	
	@GetMapping
	public ResponseEntity<?> GetAll(){return Result(service.getAll());}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> Get(@PathVariable Integer id) {return Result( service.getById(new GetByIdRequest(id)));}
	
	@GetMapping("/categoria/{idCategoria}")
	public ResponseEntity<?> GetAll(@PathVariable Integer idCategoria) {return Result(
			service.getAllByCategoria(
			new GetAllByCategoriaRequest(idCategoria)
			));}
	
	@GetMapping("/filtrar")
	public ResponseEntity<?> GetAll(@RequestBody GetAllByCategoriaFilterRequest request){return Result(service.getAllByCategoriaFilter(request));}
	
	@PostMapping()
	public ResponseEntity<?> Create(@RequestBody CreatePublicacionRequest request){return Result(service.create(request));}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> Update(@PathVariable Integer id,@RequestBody UpdatePublicacionVM body){
		return Result( service.update( new  UpdatePublicacionRequest
				(
						id,
						body.nombre(),
						body.descripcion(),
						body.idCategoria(),
						body.idCategoriaPretendida(),
						body.idCondicion(),
						body.idUbicacion(),
						body.idUbicacionPretendida(),
						body.idEstado(),
						body.imagenes()
				)));}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> Delete(@PathVariable Integer id){return Result(service.delete(new GetByIdRequest(id)));}
	
	@GetMapping("/cargarDropdown")
	public ResponseEntity<?> getDataDropDown(){return Result(service.getDataDropdown());}
	
	@GetMapping("/cargarImagenes")
	public ResponseEntity<?> cargarImagenes(){return Result(service.cargarImagenes());}
}
