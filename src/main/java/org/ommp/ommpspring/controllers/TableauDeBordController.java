package org.ommp.ommpspring.controllers;

import org.ommp.ommpspring.IService.IDonneesMensuellesService;
import org.ommp.ommpspring.IService.ITableauDeBordFinalService;
import org.ommp.ommpspring.IService.ITableauDeBordService;
import org.ommp.ommpspring.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RequestMapping("/api/tableaux-de-bord")
public class TableauDeBordController {

    @Autowired
    private ITableauDeBordService tableauDeBordService;
    @Autowired
    private ITableauDeBordFinalService tableauDeBordFinalService;

    @PostMapping
    public ResponseEntity<TableauDeBord> createTableauDeBord(@RequestBody TableauDeBord tableauDeBord) {
        TableauDeBord newTableauDeBord = tableauDeBordService.saveTB(tableauDeBord);
        return new ResponseEntity<>(newTableauDeBord, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TableauDeBord> updateTableauDeBord(@PathVariable Long id, @RequestBody TableauDeBord tableauDeBord) {
        tableauDeBord.setIdTB(id);
        TableauDeBord updatedTableauDeBord = tableauDeBordService.updateTB(tableauDeBord);
        return new ResponseEntity<>(updatedTableauDeBord, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTableauDeBord(@PathVariable Long id) {
        tableauDeBordService.deleteTB(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/getOne/{id}")
    public ResponseEntity<TableauDeBord> getTableauDeBordById(@PathVariable Long id) {
        Optional<TableauDeBord> tableauDeBordOptional = tableauDeBordService.getTBById(id);
        return tableauDeBordOptional.map(tableauDeBord -> new ResponseEntity<>(tableauDeBord, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/all")
    public ResponseEntity<List<TableauDeBord>> getAllTableauxDeBord() {
        List<TableauDeBord> tableauDeBords = tableauDeBordService.getAllTB();
        return new ResponseEntity<>(tableauDeBords, HttpStatus.OK);
    }
    @PostMapping("/final/{tableauDeBordFinalId}")
    public ResponseEntity<TableauDeBord> createTableauDeBordAndAssignToFinal(@PathVariable Long tableauDeBordFinalId, @RequestBody TableauDeBord tableauDeBord) {
        boolean regionOuPortExisteDeja = tableauDeBordService.regionOuPortExisteDeja(tableauDeBordFinalId, tableauDeBord.getRegion(), tableauDeBord.getPort());
        if (regionOuPortExisteDeja) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
        TableauDeBord createdTableauDeBord = tableauDeBordService.createTableauDeBordAndAssignToFinal(tableauDeBordFinalId, tableauDeBord);
        return new ResponseEntity<>(createdTableauDeBord, HttpStatus.CREATED);
    }


}
