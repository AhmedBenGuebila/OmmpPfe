package org.ommp.ommpspring.repositories;

import org.ommp.ommpspring.entities.DonneesMensuelles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DonneesMensuellesRepository extends JpaRepository<DonneesMensuelles, Long>{


    List<DonneesMensuelles> findByTableauDeBordIdTB(Long tableauDeBordId);

    Optional<DonneesMensuelles> findByTableauDeBordIdTBAndAndMois(Long tableauDeBordId, DonneesMensuelles.Mois mois);
    Optional<DonneesMensuelles> findByTableauDeBordIdTBAndAndAnnee(Long tableauDeBordId, DonneesMensuelles.Annee annee);
    Optional<DonneesMensuelles> findByTableauDeBordIdTBAndAndSemestre(Long tableauDeBordId, DonneesMensuelles.Semestre semestre);
    Optional<DonneesMensuelles> findByTableauDeBordIdTBAndAndTrimestre(Long tableauDeBordId, DonneesMensuelles.Trimestre mois);

}
