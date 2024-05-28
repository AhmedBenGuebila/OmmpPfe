package org.ommp.ommpspring.services;

import org.ommp.ommpspring.IService.ITableauDeBordFinalService;
import org.ommp.ommpspring.entities.TableauDeBord;
import org.ommp.ommpspring.entities.TableauDeBordFinal;
import org.ommp.ommpspring.repositories.TableauDeBordFinalRepository;
import org.ommp.ommpspring.repositories.TableauDeBordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TableauDeBordFinalService implements ITableauDeBordFinalService {


    @Autowired
    private TableauDeBordFinalRepository tableauDeBordFinalRepository;

    @Autowired
    private TableauDeBordRepository tableauDeBordRepository;



    @Override
    public TableauDeBordFinal saveTableauDeBordFinal(TableauDeBordFinal tableauDeBordFinal) {
        return tableauDeBordFinalRepository.save(tableauDeBordFinal);
    }

    @Override
    public TableauDeBordFinal updateTableauDeBordFinal(TableauDeBordFinal tableauDeBordFinal) {
        Optional<TableauDeBordFinal> existingTBFOptional = tableauDeBordFinalRepository.findById(tableauDeBordFinal.getIdTBF());
        if (existingTBFOptional.isPresent()) {
            return tableauDeBordFinalRepository.save(tableauDeBordFinal);
        } else {
            throw new RuntimeException("Tableau de bord introuvable avec l'identifiant : " + tableauDeBordFinal.getIdTBF());
        }
    }

    @Override
    public void deleteTableauDeBordFinal(Long tableauDeBordFinalId) {
        tableauDeBordFinalRepository.deleteById(tableauDeBordFinalId);
    }

    @Override
    public Optional<TableauDeBordFinal> getTableauDeBordFinalById(Long tableauDeBordFinalId) {
        return tableauDeBordFinalRepository.findById(tableauDeBordFinalId);
    }



    @Override
    public Optional<TableauDeBordFinal> getTableauDeBordFinalByIdTB(Long tableauDeBordlId) {
        Optional<TableauDeBord> tableauDeBord=tableauDeBordRepository.findById(tableauDeBordlId);

        return tableauDeBordFinalRepository.findById(tableauDeBord.get().getTableauDeBordFinal().getIdTBF());
    }

    @Override
    public List<TableauDeBordFinal> getAllTableauDeBordFinal() {
        return tableauDeBordFinalRepository.findAll();
    }
}
