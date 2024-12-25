package com.waste.wastTracker.controller;


import com.waste.wastTracker.model.Bin;
import com.waste.wastTracker.model.enums.BinStatus;
import com.waste.wastTracker.service.BinService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bins")
public class BinController {

    private final BinService binService;
    private static final Logger logger = LoggerFactory.getLogger(BinController.class);

    @Autowired
    public BinController(BinService binService) {
        this.binService = binService;
    }

    @GetMapping
    public ResponseEntity<List<Bin>> getAllBins() {
        List<Bin> bins = binService.getAllBins();
        return ResponseEntity.ok(bins);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bin> getBinById(@PathVariable Long id) {
        Bin bin = binService.getBinById(id);
        return ResponseEntity.ok(bin);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Bin>> getBinsByStatus(@PathVariable BinStatus status) {
        List<Bin> bins = binService.getBinByStatus(status);
        return ResponseEntity.ok(bins);
    }

    @PostMapping
    public ResponseEntity<Bin> createBin(@Valid @RequestBody Bin bin) {
        Bin newBin = binService.createBin(bin);
        return new ResponseEntity<>(newBin, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Bin> updateBin(
            @PathVariable Long id,
            @Valid @RequestBody Bin bin) {
        bin.setId(id);
        Bin updatedBin = binService.updateBin(bin);
        return ResponseEntity.ok(updatedBin);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBin(@PathVariable Long id) {
        binService.deleteBin(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/collection/{threshold}")
    public ResponseEntity<List<Bin>> getBinsNeedingCollection(@PathVariable Integer threshold) {
        List<Bin> bins = binService.getBinsNeedingCollection(threshold);
        return ResponseEntity.ok(bins);
    }
}
