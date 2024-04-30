package org.ommp.ommpspring.services;

import org.ommp.ommpspring.IService.IDonneesMensuellesService;
import org.ommp.ommpspring.entities.DonneesMensuelles;
import org.ommp.ommpspring.entities.TableauDeBord;
import org.ommp.ommpspring.repositories.DonneesMensuellesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DonneesMensuellesService implements IDonneesMensuellesService {

    @Autowired
    private DonneesMensuellesRepository donneesMensuellesRepository;
    @Override
    public List<DonneesMensuelles> getDonneesMensuellesByTableauDeBordId(Long tableauDeBordId) {
        return donneesMensuellesRepository.findByTableauDeBordIdTB(tableauDeBordId);
    }


    @Override
    public boolean existeDonneesMensuellesPourMoisEtTableauDeBord(Long tableauDeBordId, DonneesMensuelles.Mois mois) {
        Optional<DonneesMensuelles> donneesMensuelles = donneesMensuellesRepository.findByTableauDeBordIdTBAndAndMois(tableauDeBordId, mois);
        return donneesMensuelles.isPresent();
    }
    @Override
    public boolean existeDonneesMensuellesPourTrimestreEtTableauDeBord(Long tableauDeBordId, DonneesMensuelles.Trimestre mois) {
        Optional<DonneesMensuelles> donneesMensuelles = donneesMensuellesRepository.findByTableauDeBordIdTBAndAndTrimestre(tableauDeBordId, mois);
        return donneesMensuelles.isPresent();
    }
    @Override
    public boolean existeDonneesMensuellesPourSemestreEtTableauDeBord(Long tableauDeBordId, DonneesMensuelles.Semestre mois) {
        Optional<DonneesMensuelles> donneesMensuelles = donneesMensuellesRepository.findByTableauDeBordIdTBAndAndSemestre(tableauDeBordId, mois);
        return donneesMensuelles.isPresent();
    }
    @Override
    public boolean existeDonneesMensuellesPourAnneeEtTableauDeBord(Long tableauDeBordId, DonneesMensuelles.Annee mois) {
        Optional<DonneesMensuelles> donneesMensuelles = donneesMensuellesRepository.findByTableauDeBordIdTBAndAndAnnee(tableauDeBordId, mois);
        return donneesMensuelles.isPresent();
    }


    @Override
    public DonneesMensuelles saveDonnees(DonneesMensuelles donneesMensuelles) {
        return donneesMensuellesRepository.save(donneesMensuelles);
    }

    @Override
    public DonneesMensuelles updateDonnees(DonneesMensuelles donneesMensuelles) {
        Optional<DonneesMensuelles> existingDataOptional = donneesMensuellesRepository.findById(donneesMensuelles.getId());
        if (existingDataOptional.isPresent()) {
            DonneesMensuelles existingData = existingDataOptional.get();
            existingData.setMois(donneesMensuelles.getMois());
            existingData.setValeur2(donneesMensuelles.getValeur2());
            existingData.setValeur1(donneesMensuelles.getValeur1());
            existingData.setTaux(donneesMensuelles.getTaux());
            return donneesMensuellesRepository.save(existingData);
        } else {
            throw new RuntimeException("Erreur lors de la mise a jour");
        }
    }


    @Override
    public void deleteDonnees(Long donneesId) {
        donneesMensuellesRepository.deleteById(donneesId);
    }

    @Override
    public Optional<DonneesMensuelles> getDonneesById(Long donneesId) {
        return donneesMensuellesRepository.findById(donneesId);
    }

    @Override
    public List<DonneesMensuelles> getAllDonnees() {
        return donneesMensuellesRepository.findAll();
    }
}
