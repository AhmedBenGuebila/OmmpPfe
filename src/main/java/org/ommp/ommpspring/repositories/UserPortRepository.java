package org.ommp.ommpspring.repositories;

import org.ommp.ommpspring.entities.UserPort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPortRepository extends JpaRepository<UserPort, Long> {

}