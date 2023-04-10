package com.crud.prueba.ApiCrud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crud.prueba.ApiCrud.models.Usuario;

@Repository
public interface UserRepository extends JpaRepository<Usuario, Long> {

}
