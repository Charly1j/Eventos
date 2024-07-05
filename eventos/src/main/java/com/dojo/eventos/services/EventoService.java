package com.dojo.eventos.services;

import org.springframework.stereotype.Service;

import com.dojo.eventos.models.EventosModel;
import com.dojo.eventos.repositories.EventoRepo;

@Service
public class EventoService {
	
	private final EventoRepo eventoRepo;
	
	public EventoService(EventoRepo eRepo) {
		this.eventoRepo = eRepo;
	}
	
	
	public EventosModel crearEvento(EventosModel evento) {
		return eventoRepo.save(evento);
	}
	
//	public List<EventosModel> eventoProvinciaUsuario(String provincia){
//		return eventoRepo.
//	}

}
