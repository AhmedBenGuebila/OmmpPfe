package org.ommp.ommpspring.controllers;

import org.ommp.ommpspring.IService.ISiteService;
import org.ommp.ommpspring.entities.Site;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/sites")
public class SiteController {

    @Autowired
    private ISiteService siteService;

    @PostMapping
    public ResponseEntity<Site> createSite(@RequestBody Site site) {
        Site newSite = siteService.saveSite(site);
        return new ResponseEntity<>(newSite, HttpStatus.CREATED);
    }

    @PutMapping("/{siteId}")
    public ResponseEntity<Site> updateSite(@PathVariable Long siteId, @RequestBody Site site) {
        Optional<Site> existingSiteOptional = siteService.getSiteById(siteId);
        if (existingSiteOptional.isPresent()) {
            site.setIdSite(siteId);
            Site updatedSite = siteService.updateSite(site);
            return new ResponseEntity<>(updatedSite, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{siteId}")
    public ResponseEntity<Void> deleteSite(@PathVariable Long siteId) {
        Optional<Site> siteOptional = siteService.getSiteById(siteId);
        if (siteOptional.isPresent()) {
            siteService.deleteSite(siteId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{siteId}")
    public ResponseEntity<Site> getSiteById(@PathVariable Long siteId) {
        Optional<Site> siteOptional = siteService.getSiteById(siteId);
        return siteOptional.map(site -> new ResponseEntity<>(site, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<Site>> getAllSites() {
        List<Site> sites = siteService.getAllSites();
        return new ResponseEntity<>(sites, HttpStatus.OK);
    }
}
