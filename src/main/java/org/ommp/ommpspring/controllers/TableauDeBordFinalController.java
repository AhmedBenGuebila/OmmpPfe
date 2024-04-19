package org.ommp.ommpspring.controllers;

import org.ommp.ommpspring.IService.ITableauDeBordFinalService;
import org.ommp.ommpspring.IService.ITableauDeBordService;
import org.ommp.ommpspring.entities.Document;
import org.ommp.ommpspring.entities.TableauDeBord;
import org.ommp.ommpspring.entities.TableauDeBordFinal;
import org.ommp.ommpspring.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RequestMapping("/api/tableaux-de-bord-final")
public class TableauDeBordFinalController {

    @Autowired
    private ITableauDeBordFinalService tableauDeBordFinalService;

    @PostMapping
    public ResponseEntity<TableauDeBordFinal> createTableauDeBordFinal(@RequestBody TableauDeBordFinal tableauDeBordFinal) {
        TableauDeBordFinal newTableauDeBordFinal = tableauDeBordFinalService.saveTableauDeBordFinal(tableauDeBordFinal);
        return new ResponseEntity<>(newTableauDeBordFinal, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TableauDeBordFinal> updateTableauDeBordFinal(@PathVariable Long id, @RequestBody TableauDeBordFinal tableauDeBordFinal) {
        tableauDeBordFinal.setIdTBF(id);
        TableauDeBordFinal updatedTableauDeBordFinal = tableauDeBordFinalService.updateTableauDeBordFinal(tableauDeBordFinal);
        return new ResponseEntity<>(updatedTableauDeBordFinal, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTableauDeBordFinal(@PathVariable Long id) {
        tableauDeBordFinalService.deleteTableauDeBordFinal(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/getOne/{id}")
    public ResponseEntity<TableauDeBordFinal> getTableauDeBordFinalById(@PathVariable Long id) {
        Optional<TableauDeBordFinal> tableauDeBordFinalOptional = tableauDeBordFinalService.getTableauDeBordFinalById(id);
        return tableauDeBordFinalOptional.map(tableauDeBordFinal -> new ResponseEntity<>(tableauDeBordFinal, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/all")
    public ResponseEntity<List<TableauDeBordFinal>> getAllTableauxDeBord() {
        List<TableauDeBordFinal> tableauDeBordsFinal = tableauDeBordFinalService.getAllTableauDeBordFinal();
        return new ResponseEntity<>(tableauDeBordsFinal, HttpStatus.OK);
    }




}
