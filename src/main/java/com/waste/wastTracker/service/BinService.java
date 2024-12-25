package com.waste.wastTracker.service;

import com.waste.wastTracker.errorHandler.ResourceNotFoundException;
import com.waste.wastTracker.model.Bin;
import com.waste.wastTracker.model.enums.BinStatus;
import com.waste.wastTracker.repo.BinRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class BinService {

    private final BinRepository binRepository;

    @Autowired
    public BinService(BinRepository binRepository) {
        this.binRepository = binRepository;
    }

    public List<Bin> getAllBins() {
        return binRepository.findAll();
    }

    public Bin getBinById(Long id){
        return binRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bin not found"));
    }

    public List<Bin> getBinByStatus(BinStatus status){
        return binRepository.findByStatus(status);
    }

    public List<Bin> getBinsNeedingCollection(Integer limit){
        return binRepository.findByFillLevelGreaterThan(limit);
    }
    @Transactional
    public Bin createBin(Bin bin){
        long binCount = binRepository.count() + 1;
        // Format: BIN-001, BIN-002, etc.
        String binNumber = String.format("BIN-%03d", binCount);
        bin.setBinNumber(binNumber);
        return binRepository.save(bin);
    }
    @Transactional
    public Bin updateBin(Bin bin) {
        getBinById(bin.getId());
        return binRepository.save(bin);
    }

    @Transactional
    public void deleteBin(Long id){
        Bin bin = getBinById(id);
        binRepository.delete(bin);
    }
}
