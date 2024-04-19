package org.ommp.ommpspring.IService;

import org.ommp.ommpspring.entities.Site;
import org.ommp.ommpspring.entities.TableauDeBordFinal;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ITableauDeBordFinalService {
    TableauDeBordFinal saveTableauDeBordFinal(TableauDeBordFinal tableauDeBordFinal);

    TableauDeBordFinal updateTableauDeBordFinal(TableauDeBordFinal tableauDeBordFinal);

    void deleteTableauDeBordFinal(Long tableauDeBordFinalId);

    Optional<TableauDeBordFinal> getTableauDeBordFinalById(Long tableauDeBordFinalId);

    List<TableauDeBordFinal> getAllTableauDeBordFinal();
}
