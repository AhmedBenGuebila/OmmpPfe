package org.ommp.ommpspring.services;


import org.ommp.ommpspring.IService.ISiteService;
import org.ommp.ommpspring.entities.Site;
import org.ommp.ommpspring.repositories.SiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SiteService implements ISiteService {
    @Autowired
    private SiteRepository siteRepository;

    @Override
    public Site saveSite(Site site) {
        return siteRepository.save(site);
    }

    @Override
    public Site updateSite(Site site) {
        return siteRepository.save(site);
    }

    @Override
    public void deleteSite(Long siteId) {
        siteRepository.deleteById(siteId);
    }

    @Override
    public Optional<Site> getSiteById(Long siteId) {
        return siteRepository.findById(siteId);
    }

    @Override
    public List<Site> getAllSites() {
        return siteRepository.findAll();
    }
}
