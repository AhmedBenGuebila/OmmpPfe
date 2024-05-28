package org.ommp.ommpspring.controllers;

import org.ommp.ommpspring.IService.IDonneesMensuellesService;
import org.ommp.ommpspring.IService.ITableauDeBordService;
import org.ommp.ommpspring.entities.DonneesMensuelles;
import org.ommp.ommpspring.entities.TableauDeBord;
import org.ommp.ommpspring.entities.TableauDeBordFinal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
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


    @PostMapping("/calcul/{val1}/{val2}")
    public ResponseEntity<Double> calculerTaux(@PathVariable Double val1, @PathVariable Double val2, @RequestBody TableauDeBordFinal.MethodeDeCalcul M){
        if (val2 == 0) {

            return ResponseEntity.badRequest().body(null);
        }
        BigDecimal valeur1 = new BigDecimal(val1);
        BigDecimal valeur2 = new BigDecimal(val2);
        BigDecimal taux;
        switch (M) {
            case M1:
                taux = valeur1.divide(valeur2, 3, RoundingMode.HALF_UP).multiply(new BigDecimal(100));
                break;
            case M2:
                taux = valeur1.multiply(new BigDecimal(1000)).divide(valeur2, 3, RoundingMode.HALF_UP);
                break;
            case M3:
                taux = valeur1.multiply(new BigDecimal(1000000)).divide(valeur2, 3, RoundingMode.HALF_UP);
                break;
            case M4:
              //  taux =(valeur1 - valeur2)/valeur1
                taux=valeur1;
                break;
            case M5:

                return ResponseEntity.ok(val1);
            default:

                return ResponseEntity.badRequest().body(null);
        }

        return ResponseEntity.ok(taux.doubleValue());
    }



    @PostMapping("/create-and-assign/{tableauDeBordId}")
    public ResponseEntity<DonneesMensuelles> createAndAssignDonneesMensuellesToTableauDeBord(@PathVariable Long tableauDeBordId, @RequestBody DonneesMensuelles donneesMensuelles) {
        boolean existeDeja = true;

         if (tableauDeBordService.getTBById(tableauDeBordId).get().getTableauDeBordFinal().getFrequenceDeMesure().equals(TableauDeBordFinal.FrequenceDeMesure.MOIS)) {

            existeDeja = donneesMensuellesService.existeDonneesMensuellesPourMoisEtTableauDeBord(tableauDeBordId, donneesMensuelles.getMois());

        } else if (tableauDeBordService.getTBById(tableauDeBordId).get().getTableauDeBordFinal().getFrequenceDeMesure().equals(TableauDeBordFinal.FrequenceDeMesure.TRIMESTRE)) {
            existeDeja = donneesMensuellesService.existeDonneesMensuellesPourTrimestreEtTableauDeBord(tableauDeBordId, donneesMensuelles.getTrimestre());
        } else if (tableauDeBordService.getTBById(tableauDeBordId).get().getTableauDeBordFinal().getFrequenceDeMesure().equals(TableauDeBordFinal.FrequenceDeMesure.SEMESTRE)) {
            existeDeja = donneesMensuellesService.existeDonneesMensuellesPourSemestreEtTableauDeBord(tableauDeBordId, donneesMensuelles.getSemestre());
        } else if (tableauDeBordService.getTBById(tableauDeBordId).get().getTableauDeBordFinal().getFrequenceDeMesure().equals(TableauDeBordFinal.FrequenceDeMesure.ANNEE)) {
            existeDeja = donneesMensuellesService.existeDonneesMensuellesPourAnneeEtTableauDeBord(tableauDeBordId, donneesMensuelles.getAnnee());
        }


        if (existeDeja) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }

        Optional<TableauDeBord> tableauDeBordOptional = tableauDeBordService.getTBById(tableauDeBordId);
        if (tableauDeBordOptional.isPresent()) {
            TableauDeBord tableauDeBord = tableauDeBordOptional.get();
            donneesMensuelles.setTableauDeBord(tableauDeBord);

            if (donneesMensuelles.getTableauDeBord().getTableauDeBordFinal().getMethodeDeCalcul().equals(TableauDeBordFinal.MethodeDeCalcul.M1)){
                if (donneesMensuelles.getValeur2() != 0) {
                    BigDecimal val1 = new BigDecimal(donneesMensuelles.getValeur1());
                    BigDecimal val2 = new BigDecimal(donneesMensuelles.getValeur2());
                    BigDecimal taux = val1.divide(val2, 3, RoundingMode.HALF_UP);
                    taux = taux.multiply(new BigDecimal(100));
                    donneesMensuelles.setTaux(taux.doubleValue());
                } else {
                    donneesMensuelles.setTaux(0);
                }
            }
            if (donneesMensuelles.getTableauDeBord().getTableauDeBordFinal().getMethodeDeCalcul().equals(TableauDeBordFinal.MethodeDeCalcul.M2)){
                if (donneesMensuelles.getValeur2() != 0) {
                    BigDecimal val1 = new BigDecimal(donneesMensuelles.getValeur1()).multiply(new BigDecimal(1000));
                    BigDecimal val2 = new BigDecimal(donneesMensuelles.getValeur2());
                    BigDecimal taux = val1.divide(val2, 3, RoundingMode.HALF_UP);
                    donneesMensuelles.setTaux(taux.doubleValue());
                } else {
                    donneesMensuelles.setTaux(0);
                }
            }
            if (donneesMensuelles.getTableauDeBord().getTableauDeBordFinal().getMethodeDeCalcul().equals(TableauDeBordFinal.MethodeDeCalcul.M3)){
                if (donneesMensuelles.getValeur2() != 0) {
                    BigDecimal val1 = new BigDecimal(donneesMensuelles.getValeur1()).multiply(new BigDecimal(1000000));
                    BigDecimal val2 = new BigDecimal(donneesMensuelles.getValeur2());
                    BigDecimal taux = val1.divide(val2, 3, RoundingMode.HALF_UP);

                    donneesMensuelles.setTaux(taux.doubleValue());
                } else {
                    donneesMensuelles.setTaux(0);
                }
            }
            if (donneesMensuelles.getTableauDeBord().getTableauDeBordFinal().getMethodeDeCalcul().equals(TableauDeBordFinal.MethodeDeCalcul.M4)){
                donneesMensuelles.setTaux(donneesMensuelles.getValeur1());
            }
            if (donneesMensuelles.getTableauDeBord().getTableauDeBordFinal().getMethodeDeCalcul().equals(TableauDeBordFinal.MethodeDeCalcul.M5)){
                donneesMensuelles.setTaux(donneesMensuelles.getValeur1());
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

    @GetMapping("/by-tb/{tableauDeBordId}")
    public ResponseEntity<List<DonneesMensuelles>> getAllDonneesMensuellesByTBId(@PathVariable Long tableauDeBordId) {
        List<DonneesMensuelles> donneesMensuelles = donneesMensuellesService.getDonneesMensuellesByTableauDeBordId(tableauDeBordId);
        if (donneesMensuelles.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(donneesMensuelles);
        }
    }
}
