package com.sinplavoley.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sinplavoley.entities.Jugador;

@Repository
public interface JugadorRepository extends JpaRepository<Jugador, Long> {
}

