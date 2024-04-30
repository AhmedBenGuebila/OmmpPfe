package org.ommp.ommpspring.services;

import org.ommp.ommpspring.IService.ITableauDeBordService;
import org.ommp.ommpspring.entities.*;
import org.ommp.ommpspring.repositories.DonneesMensuellesRepository;
import org.ommp.ommpspring.repositories.TableauDeBordFinalRepository;
import org.ommp.ommpspring.repositories.TableauDeBordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TableauDeBordService implements ITableauDeBordService {

    @Autowired
    private DonneesMensuellesRepository donneesMensuellesRepository;
    @Autowired
    private TableauDeBordRepository tableauDeBordRepository;
    @Autowired
    private TableauDeBordFinalRepository tableauDeBordFinalRepository;

    @Override
    public TableauDeBord saveTB(TableauDeBord tableauDeBord) {
        return tableauDeBordRepository.save(tableauDeBord);
    }

    @Override
    public TableauDeBord updateTB(TableauDeBord tableauDeBord) {
        Optional<TableauDeBord> existingTBOptional = tableauDeBordRepository.findById(tableauDeBord.getIdTB());
        if (existingTBOptional.isPresent()) {
            return tableauDeBordRepository.save(tableauDeBord);
        } else {
            throw new RuntimeException("Tableau de bord introuvable avec l'identifiant : " + tableauDeBord.getIdTB());
        }
    }

    @Override
    public void deleteTB(Long TBId) {
        tableauDeBordRepository.deleteById(TBId);
    }

    @Override
    public Optional<TableauDeBord> getTBById(Long TBId) {
        return tableauDeBordRepository.findById(TBId);
    }

    @Override
    public List<TableauDeBord> getAllTB() {
        return tableauDeBordRepository.findAll();
    }

    @Override
    public List<TableauDeBord> getAllTableauxDeBordByFinalId(Long tableauDeBordFinalId) {
        return tableauDeBordRepository.findByTableauDeBordFinalIdTBF(tableauDeBordFinalId);
    }

    @Override
    public TableauDeBord createTableauDeBordAndAssignToFinal(Long tableauDeBordFinalId, TableauDeBord tableauDeBord) {
        Optional<TableauDeBordFinal> optionalTableauDeBordFinal = tableauDeBordFinalRepository.findById(tableauDeBordFinalId);
        if (optionalTableauDeBordFinal.isPresent()) {
            TableauDeBordFinal tableauDeBordFinal = optionalTableauDeBordFinal.get();
            tableauDeBord.setTableauDeBordFinal(tableauDeBordFinal);
            return tableauDeBordRepository.save(tableauDeBord);
        } else {
            throw new RuntimeException("Tableau de bord final non trouvé avec l'ID : " + tableauDeBordFinalId);
        }
    }

    @Override
    public boolean regionOuPortExisteDeja(Long tableauDeBordFinalId, UserRegionMaritime.Region region, UserPort.Port port) {
        List<TableauDeBord> tableauxDeBord = tableauDeBordRepository.findByTableauDeBordFinalIdTBF(tableauDeBordFinalId);
        for (TableauDeBord tableauDeBord : tableauxDeBord) {
            if ((region == null || tableauDeBord.getRegion() == region) && (port == null || tableauDeBord.getPort() == port)) {
                return true;
            }
        }
        return false;
    }

}
