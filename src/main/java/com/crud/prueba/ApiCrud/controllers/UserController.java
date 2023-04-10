package com.crud.prueba.ApiCrud.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.crud.prueba.ApiCrud.models.Usuario;
import com.crud.prueba.ApiCrud.repositories.UserRepository;
@RestController
@RequestMapping("/users")
public class UserController {
	 @Autowired
	    private UserRepository userRepository;


	    // Obtener todas las personas
		@GetMapping
		public List<Usuario> getAllUsers() {
			return userRepository.findAll();
		}

	    // Obtener una persona por ID
	    @GetMapping("/{id}")
	    public ResponseEntity<Usuario> buscarUsuario(@PathVariable Long id) {
	        Optional<Usuario> optionalUsuario = userRepository.findById(id);
	        if (optionalUsuario.isPresent()) {
	            return ResponseEntity.ok(optionalUsuario.get());
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }

	    // Crear una nueva persona
	    @PostMapping
	    public ResponseEntity<Usuario> crearUsuario(@Validated @RequestBody Usuario usuario) {
	        Usuario nuevoUsuario = userRepository.save(usuario);
	        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);
	    }

	    // Actualizar una persona existente
	    @PutMapping("/{id}")
	    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Long id, @Validated @RequestBody Usuario usuario) {
	        Optional<Usuario> optionalUsuario = userRepository.findById(id);
	        if (optionalUsuario.isPresent()) {
	            Usuario usuarioExistente = optionalUsuario.get();
	            usuarioExistente.setNombre(usuario.getNombre());
	            usuarioExistente.setEmail(usuario.getEmail());
	            Usuario usuarioActualizado = userRepository.save(usuarioExistente);
	            return ResponseEntity.ok(usuarioActualizado);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }

	    // Eliminar una persona por ID
	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
	        Optional<Usuario> optionalUsuario = userRepository.findById(id);
	        if (optionalUsuario.isPresent()) {
	            userRepository.delete(optionalUsuario.get());
	            return ResponseEntity.noContent().build();
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }

}
