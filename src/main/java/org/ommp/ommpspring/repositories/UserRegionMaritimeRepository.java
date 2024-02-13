package org.ommp.ommpspring.repositories;

import org.ommp.ommpspring.entities.UserRegionMaritime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRegionMaritimeRepository extends JpaRepository<UserRegionMaritime, Long> {

}