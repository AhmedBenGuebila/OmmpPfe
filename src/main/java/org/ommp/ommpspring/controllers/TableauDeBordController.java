package org.ommp.ommpspring.controllers;

import org.ommp.ommpspring.EmailService;
import org.ommp.ommpspring.IService.IDonneesMensuellesService;
import org.ommp.ommpspring.IService.ITableauDeBordFinalService;
import org.ommp.ommpspring.IService.ITableauDeBordService;
import org.ommp.ommpspring.IService.IUserService;
import org.ommp.ommpspring.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RequestMapping("/api/tableaux-de-bord")
public class TableauDeBordController {
    @Autowired
    private EmailService emailService;
    @Autowired
    private ITableauDeBordService tableauDeBordService;
    @Autowired
    private ITableauDeBordFinalService tableauDeBordFinalService;
    @Autowired
    private IUserService userService;


    @GetMapping("/tableauxDeBordByUser/{userId}")
    public ResponseEntity<Set<TableauDeBord>> getTableauxDeBordByUserId(@PathVariable Long userId) {
        try {
            Set<TableauDeBord> tableauDeBords = tableauDeBordService.getTableauxDeBordByUserId(userId);
            return new ResponseEntity<>(tableauDeBords, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/affecter/{TBId}/{userId}")
    public ResponseEntity<TableauDeBord> affecterUtilisateurAuTableauDeBord(@PathVariable Long TBId, @PathVariable Long userId) throws ChangeSetPersister.NotFoundException {
        TableauDeBord updatedTableauDeBord = tableauDeBordService.affecterUtilisateur(TBId, userId);

        if (updatedTableauDeBord != null) {
            Optional<User> userOptional = userService.getUserById(userId);
            User user = userOptional.get();
            emailService.sendSimpleMessage(user.getEmail(),"l'Office Des ports et des Regions maritime Document", "Bonjour " + user.getNom() + ",\n\n" +
                    "Vous avez un tableau de bord a remplir.\n\n" +
                    "Voici les détails du tableau de bord :\n" +
                    "Process: " + updatedTableauDeBord.getTableauDeBordFinal().getNom() + "\n" +
                    "Annee : " + updatedTableauDeBord.getTableauDeBordFinal().getAnnee() + "\n" +
                    "Frequence de mesure : " + updatedTableauDeBord.getTableauDeBordFinal().getFrequenceDeMesure() + "\n\n" +

                    "Vous pouvez vous connecter à travers ce lien.\n\n" +
                    "http://localhost:4200/#/authentifications/login\n"+
                    "Cordialement,\n" +
                    "L'équipe OMMP" );
            return new ResponseEntity<>(updatedTableauDeBord, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/desaffecter/{TBId}/{userId}")
    public ResponseEntity<TableauDeBord> desaffecterUtilisateurDuTableauDeBord(@PathVariable Long TBId, @PathVariable Long userId) {
        TableauDeBord updatedTableauDeBord = tableauDeBordService.desaffecterUtilisateur(TBId, userId);
        if (updatedTableauDeBord != null) {
            Optional<User> userOptional = userService.getUserById(userId);
            User user = userOptional.get();
            emailService.sendSimpleMessage(user.getEmail(),"l'Office Des ports et des Regions maritime Document", "Bonjour " + user.getNom() + ",\n\n" +
                    "Vous n'avez pas access a ce tableau de bord.\n\n" +
                    "Voici les détails du tableau de bord :\n" +
                    "Process: " + updatedTableauDeBord.getTableauDeBordFinal().getNom() + "\n" +
                    "Annee : " + updatedTableauDeBord.getTableauDeBordFinal().getAnnee() + "\n\n" +


                    "Vous pouvez vous connecter à travers ce lien.\n\n" +
                    "http://localhost:4200/#/authentifications/login\n"+
                    "Cordialement,\n" +
                    "L'équipe OMMP" );
            return new ResponseEntity<>(updatedTableauDeBord, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

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

    @GetMapping("/by-final/{tableauDeBordFinalId}")
    public ResponseEntity<List<TableauDeBord>> getAllTableauxDeBordByFinalId(@PathVariable Long tableauDeBordFinalId) {
        List<TableauDeBord> tableauxDeBord = tableauDeBordService.getAllTableauxDeBordByFinalId(tableauDeBordFinalId);
        if (tableauxDeBord.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(tableauxDeBord);
        }
    }


}
