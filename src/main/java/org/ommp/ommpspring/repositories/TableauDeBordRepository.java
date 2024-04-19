package org.ommp.ommpspring.repositories;

import org.ommp.ommpspring.entities.TableauDeBord;
import org.ommp.ommpspring.entities.UserPort;
import org.ommp.ommpspring.entities.UserSiege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TableauDeBordRepository extends JpaRepository<TableauDeBord, Long> {

    List<TableauDeBord> findByTableauDeBordFinalIdTBF(Long tableauDeBordFinalId);
}