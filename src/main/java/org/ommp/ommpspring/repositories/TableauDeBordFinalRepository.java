package org.ommp.ommpspring.repositories;

import org.ommp.ommpspring.entities.TableauDeBord;
import org.ommp.ommpspring.entities.TableauDeBordFinal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TableauDeBordFinalRepository extends JpaRepository<TableauDeBordFinal, Long> {
}
