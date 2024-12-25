package com.waste.wastTracker.repo;

import com.waste.wastTracker.model.Bin;
import com.waste.wastTracker.model.enums.BinStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BinRepository extends JpaRepository<Bin, Long> {

    List<Bin> findByFillLevelGreaterThan(Integer threshold);

    List<Bin> findByStatus(BinStatus status);


}
