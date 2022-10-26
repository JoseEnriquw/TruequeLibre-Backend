package com.grupo3.truequelibre.interfaces;

import java.util.List;
import java.util.Optional;

public interface IGenericServices<Entity,ID> {
	
	List<Entity> getAll();
	Entity getById(ID id);
	void save(Entity entity);
	void delete(ID id);

}
