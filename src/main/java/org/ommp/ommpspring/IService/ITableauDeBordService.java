package org.ommp.ommpspring.IService;

import org.ommp.ommpspring.entities.*;
import org.ommp.ommpspring.repositories.TableauDeBordFinalRepository;
import org.ommp.ommpspring.repositories.TableauDeBordRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
public interface ITableauDeBordService {
    TableauDeBord saveTB(TableauDeBord tableauDeBord);

    TableauDeBord updateTB(TableauDeBord tableauDeBord);

    void deleteTB(Long TBId);

    Optional<TableauDeBord> getTBById(Long TBId);

    List<TableauDeBord> getAllTB();

    List<TableauDeBord> getAllTableauxDeBordByFinalId(Long tableauDeBordFinalId);

    TableauDeBord createTableauDeBordAndAssignToFinal(Long tableauDeBordFinalId, TableauDeBord tableauDeBord);

    boolean regionOuPortExisteDeja(Long tableauDeBordFinalId, UserRegionMaritime.Region region, UserPort.Port port);
}