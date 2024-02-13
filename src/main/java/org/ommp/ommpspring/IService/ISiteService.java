package org.ommp.ommpspring.IService;

import org.ommp.ommpspring.entities.Site;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ISiteService {
    Site saveSite(Site site);

    Site updateSite(Site site);

    void deleteSite(Long siteId);

    Optional<Site> getSiteById(Long siteId);

    List<Site> getAllSites();
}
