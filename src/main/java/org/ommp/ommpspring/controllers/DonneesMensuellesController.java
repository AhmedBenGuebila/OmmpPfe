package org.ommp.ommpspring.controllers;

import org.ommp.ommpspring.IService.IDonneesMensuellesService;
import org.ommp.ommpspring.IService.ITableauDeBordService;
import org.ommp.ommpspring.entities.DonneesMensuelles;
import org.ommp.ommpspring.entities.TableauDeBord;
import org.ommp.ommpspring.services.TableauDeBordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RequestMapping("/api/donnees-mensuelles")
public class DonneesMensuellesController {

    @Autowired
    private IDonneesMensuellesService donneesMensuellesService;
    @Autowired
    private ITableauDeBordService tableauDeBordService;

    @PostMapping("/create-and-assign/{tableauDeBordId}")
    public ResponseEntity<DonneesMensuelles> createAndAssignDonneesMensuellesToTableauDeBord(@PathVariable Long tableauDeBordId, @RequestBody DonneesMensuelles donneesMensuelles) {
        boolean existeDeja = donneesMensuellesService.existeDonneesMensuellesPourMoisEtTableauDeBord(tableauDeBordId, donneesMensuelles.getMois());
        if (existeDeja) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }

        Optional<TableauDeBord> tableauDeBordOptional = tableauDeBordService.getTBById(tableauDeBordId);
        if (tableauDeBordOptional.isPresent()) {
            TableauDeBord tableauDeBord = tableauDeBordOptional.get();
            donneesMensuelles.setTableauDeBord(tableauDeBord);

            if (donneesMensuelles.getValeur2() != 0) {
                double taux = (float)donneesMensuelles.getValeur1() / (float)donneesMensuelles.getValeur2();
                donneesMensuelles.setTaux(taux);
            } else {

                donneesMensuelles.setTaux(0);
            }
            DonneesMensuelles newDonneesMensuelles = donneesMensuellesService.saveDonnees(donneesMensuelles);
            return new ResponseEntity<>(newDonneesMensuelles, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/DonneesMensuellesTB/{tableauDeBordId}")
    public ResponseEntity<List<DonneesMensuelles>> getDonneesMensuellesByTableauDeBordId(@PathVariable Long tableauDeBordId) {
        List<DonneesMensuelles> donneesMensuellesList = donneesMensuellesService.getDonneesMensuellesByTableauDeBordId(tableauDeBordId);
        return new ResponseEntity<>(donneesMensuellesList, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<DonneesMensuelles> createDonneesMensuelles(@RequestBody DonneesMensuelles donneesMensuelles) {
        DonneesMensuelles newDonneesMensuelles = donneesMensuellesService.saveDonnees(donneesMensuelles);
        return new ResponseEntity<>(newDonneesMensuelles, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DonneesMensuelles> updateDonneesMensuelles(@PathVariable Long id, @RequestBody DonneesMensuelles donneesMensuelles) {
        donneesMensuelles.setId(id);
        DonneesMensuelles updatedDonneesMensuelles = donneesMensuellesService.updateDonnees(donneesMensuelles);
        return new ResponseEntity<>(updatedDonneesMensuelles, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDonneesMensuelles(@PathVariable Long id) {
        donneesMensuellesService.deleteDonnees(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DonneesMensuelles> getDonneesMensuellesById(@PathVariable Long id) {
        Optional<DonneesMensuelles> donneesMensuellesOptional = donneesMensuellesService.getDonneesById(id);
        return donneesMensuellesOptional.map(donneesMensuelles -> new ResponseEntity<>(donneesMensuelles, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<DonneesMensuelles>> getAllDonneesMensuelles() {
        List<DonneesMensuelles> donneesMensuellesList = donneesMensuellesService.getAllDonnees();
        return new ResponseEntity<>(donneesMensuellesList, HttpStatus.OK);
    }
}
