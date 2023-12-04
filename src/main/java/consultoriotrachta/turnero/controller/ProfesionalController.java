package consultoriotrachta.turnero.controller;

import consultoriotrachta.turnero.dto.ProfesionalDto;
import consultoriotrachta.turnero.entity.Profesional;
import consultoriotrachta.turnero.service.ProfesionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profesionales")
public class ProfesionalController {

    @Autowired
    private ProfesionalService profesionalService;

    @GetMapping("/all")
    public List<ProfesionalDto> getAllProfesionales() {
        return profesionalService.obtenerTodosLosProfesionales();
    }

    @GetMapping("/buscarPorApellido")
    public List<ProfesionalDto> buscarPorApellido(@RequestParam String apellido) {
        return profesionalService.buscarPorApellido(apellido);
    }

    @PostMapping("/guardar")
    public ResponseEntity<Profesional> guardarProfesional(@RequestBody Profesional profesional) {
        Profesional profesionalGuardado = profesionalService.guardarProfesional(profesional);
        return ResponseEntity.ok(profesionalGuardado);
    }
}
