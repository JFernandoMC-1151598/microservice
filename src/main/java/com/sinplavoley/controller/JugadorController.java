package com.sinplavoley.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.sinplavoley.entities.Jugador;
import com.sinplavoley.repository.JugadorRepository;

@Controller
public class JugadorController {

    @Autowired
    private JugadorRepository jugadorRepository;

    @GetMapping("/registrar")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("jugador", new Jugador());
        return "registrar";
    }

    @PostMapping("/registrar")
    public String registrarJugador(@ModelAttribute Jugador jugador) {
        jugadorRepository.save(jugador);
        return "redirect:/registrar";
    }

    @GetMapping("/admin/pendientes")
    public String listarAfiliacionesPendientes(Model model) {
        List<Jugador> jugadoresPendientes = jugadorRepository.findByEstadoAfiliacion("pendiente");
        model.addAttribute("jugadores", jugadoresPendientes);
        return "pendientes";
    }
    @GetMapping("/admin/afiliaciones")
    public String listarAfiliaciones(Model model) {
        List<Jugador> afiliaciones = jugadorRepository.findAll(); // Obtener todas las afiliaciones
        model.addAttribute("afiliaciones", afiliaciones); // Agregar la lista al modelo
        return "afiliaciones"; // Devolver el nombre de la vista para mostrar los datos
    }

    @PostMapping("/admin/afiliacion/{id}/aprobar")
    public String aprobarAfiliacion(@PathVariable Long id) {
        Jugador jugador = jugadorRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Id inválido:" + id));
        jugador.setEstadoAfiliacion("aprobado");
        jugadorRepository.save(jugador);
        return "redirect:/admin/pendientes";
    }

    @PostMapping("/admin/afiliacion/{id}/rechazar")
    public String rechazarAfiliacion(@PathVariable Long id) {
        Jugador jugador = jugadorRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Id inválido:" + id));
        jugador.setEstadoAfiliacion("rechazado");
        jugadorRepository.save(jugador);
        return "redirect:/admin/pendientes";
    }
}
