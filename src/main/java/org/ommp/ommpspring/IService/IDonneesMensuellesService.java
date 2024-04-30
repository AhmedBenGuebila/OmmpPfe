package org.ommp.ommpspring.IService;
import org.ommp.ommpspring.entities.DonneesMensuelles;
import org.ommp.ommpspring.entities.TableauDeBord;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
public interface IDonneesMensuellesService {
    boolean existeDonneesMensuellesPourMoisEtTableauDeBord(Long tableauDeBordId, DonneesMensuelles.Mois mois);
    boolean existeDonneesMensuellesPourTrimestreEtTableauDeBord(Long tableauDeBordId, DonneesMensuelles.Trimestre trimestre);
    boolean existeDonneesMensuellesPourSemestreEtTableauDeBord(Long tableauDeBordId, DonneesMensuelles.Semestre semestre);
    boolean existeDonneesMensuellesPourAnneeEtTableauDeBord(Long tableauDeBordId, DonneesMensuelles.Annee annee);

    DonneesMensuelles saveDonnees(DonneesMensuelles donneesMensuelles);

    DonneesMensuelles updateDonnees(DonneesMensuelles donneesMensuelles);

    void deleteDonnees(Long donneesId);

    Optional<DonneesMensuelles> getDonneesById(Long donneesId);

    List<DonneesMensuelles> getAllDonnees();

    List<DonneesMensuelles> getDonneesMensuellesByTableauDeBordId(Long tableauDeBordId) ;

}