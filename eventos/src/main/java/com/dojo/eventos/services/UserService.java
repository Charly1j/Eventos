package com.dojo.eventos.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.dojo.eventos.models.User;
import com.dojo.eventos.repositories.UserRepo;

@Service
public class UserService {
	
	//INYECCION DE DEPENDENCIAS
	private final UserRepo userRepo;
	public UserService(UserRepo uRepo) {
		this.userRepo = uRepo;
	}
	
	public User encontrarPorEmail(String email) {
		return userRepo.findByEmail(email);
	}
	
	public User encontrarPorId(Long id) {
		
		Optional<User> usuario = userRepo.findById(id);
		if(usuario.isPresent()) {
			return usuario.get();
		}
		return null;
	}
	
	
	//REGISTRAR AL USUARIO
	public User registrarUsuario(User usuario, BindingResult resultado) {
		User usuarioRegistrar = userRepo.findByEmail(usuario.getEmail());
		if (usuarioRegistrar != null) {
			resultado.rejectValue("email", "Matches", "Email ya existe");
		}
		if(!usuario.getPassword().equals(usuario.getPasswordConfirmation())) {
			resultado.rejectValue("password", "Matches", "Password no coincide");
		}
		if(resultado.hasErrors()) {
			return null;
		}
		String hashed = BCrypt.hashpw(usuario.getPassword(), BCrypt.gensalt());
		usuario.setPassword(hashed);
		return userRepo.save(usuario);
}
	
	// Autenticacion del usuario(LOGIN)
	public boolean autenticacionUser(String email, String password, BindingResult resultado) {
		User usuarioRegistrar = userRepo.findByEmail(email);
		
		if(usuarioRegistrar == null) {
			resultado.rejectValue("email", "Matches", "Email no valido");
			return false;
		}else{
			if(BCrypt.checkpw(password, usuarioRegistrar.getPassword())){
				return true;
				
			}else {
				resultado.rejectValue("password", "Matches", "Password no valido");
				return false;
			}
		}
		
	}
	
	

}