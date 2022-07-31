package com.radar.adjustment.repositories;

import com.radar.adjustment.data.Adjustment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdjustmentRepository extends JpaRepository<Adjustment, Integer> {

}
