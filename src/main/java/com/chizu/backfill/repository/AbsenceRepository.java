package com.chizu.backfill.repository;

import com.chizu.backfill.model.Absence;
import com.chizu.backfill.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author : chizubeokwuosa
 * @Description :
 * @created : chizubeokwuosa, Tuesday
 **/
@Repository
public interface AbsenceRepository extends JpaRepository<Absence, Long> {

    boolean existsByEmail(String email);

    Optional<Staff> findByEmail(String email);
}
