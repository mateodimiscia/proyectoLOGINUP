package com.sistema.loginup.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.loginup.modelo.Rol;

public interface RolRepository extends JpaRepository<Rol,Long> {
}
