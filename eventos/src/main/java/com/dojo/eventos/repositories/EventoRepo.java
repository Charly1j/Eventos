package com.dojo.eventos.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.dojo.eventos.models.EventosModel;

public interface EventoRepo extends CrudRepository<EventosModel, Long>{
	
	List<EventosModel> findByProvincia(String provincia);

}
