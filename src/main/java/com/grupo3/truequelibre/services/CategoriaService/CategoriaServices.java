package com.grupo3.truequelibre.services.CategoriaService;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.grupo3.truequelibre.dao.ICategoriaDao;
import com.grupo3.truequelibre.entity.Categoria;
import com.grupo3.truequelibre.interfaces.ICategoriaServices;
import com.grupo3.truequelibre.services.Categoria.UpdateCategoriaRequest;
import com.grupo3.truequelibre.tools.ConverterImagenes;
import com.grupo3.truequelibre.tools.Response;

@Service
@Validated
public class CategoriaServices implements ICategoriaServices{

	@Autowired
	ICategoriaDao categoriaDao;

	@Override
	public Response<List<Categoria>> getAll() {
		List<Categoria> listaCategorias = categoriaDao.findAll();
		return new Response<>(listaCategorias,HttpStatus.OK);		
	}

	@Override
	public Response<Categoria> getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response<?> create(CreateCategoriaRequest request) {
		Response<Categoria> response = new Response<>();
		
		Categoria categoria = new Categoria(request.descripcion(),request.imagen());
		categoria = categoriaDao.save(categoria);
		response.setBody(categoria);
		response.setStatus(HttpStatus.OK);
		
		return response;
	}

	@Override
	public Response<List<Categoria>> cargarImagenes() {
		String[] imagenes = {"https://acortar.link/DXjBfg","https://acortar.link/Vk4fMi","https://acortar.link/H2PaKV","https://acortar.link/FJkxye","https://acortar.link/fcRLQn","https://acortar.link/mllN2a","https://acortar.link/59XP6T","https://acortar.link/sVBAh4","https://acortar.link/82Mn1L"};
		Response<List<Categoria>> response = new Response<>();
		List<Categoria> listaCategorias = categoriaDao.findAll();
		for (int i=0;i<listaCategorias.size(); i++  ) {
			byte[] img = ConverterImagenes.convertURLtoBytes(imagenes[i]);
			listaCategorias.get(i).setImagenes(img);;
		}
		categoriaDao.saveAll(listaCategorias);
		response.setBody(listaCategorias);
		response.setStatus(HttpStatus.OK);
		return response;
	}

	@Override
	public Response<?> update(@Valid UpdateCategoriaRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response<?> delete(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
