package com.waste.wastTracker.model;


import com.waste.wastTracker.model.enums.BinStatus;
import jakarta.persistence.*;


import java.io.Serializable;
import java.time.LocalDateTime;


@Entity
@Table(name = "bins")
public class Bin implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true, updatable = false)
    private Long id;

    @Column(nullable = false, unique = true, updatable = false)
    private String binNumber;

    private double latitude;

    private double longitude;

    private Integer fillLevel;

    @Enumerated(EnumType.STRING)
    private BinStatus status;

    private LocalDateTime lastCollected;

    public Bin() {}

    // Constructor with all fields
    public Bin(double latitude, double longitude, Integer fillLevel, BinStatus status, LocalDateTime lastCollected) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.fillLevel = fillLevel;
        this.status = status;
        this.lastCollected = lastCollected;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBinNumber() {
        return binNumber;
    }

    public void setBinNumber(String binNumber) {
        this.binNumber = binNumber;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Integer getFillLevel() {
        return fillLevel;
    }

    public void setFillLevel(Integer fillLevel) {
        this.fillLevel = fillLevel;
    }

    public BinStatus getStatus() {
        return status;
    }

    public void setStatus(BinStatus status) {
        this.status = status;
    }

    public LocalDateTime getLastCollected() {
        return lastCollected;
    }

    public void setLastCollected(LocalDateTime lastCollected) {
        this.lastCollected = lastCollected;
    }

    // ToString method
    @Override
    public String toString() {
        return "Bin{" +
                "id=" + id +
                ", binNumber='" + binNumber + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", fillLevel=" + fillLevel +
                ", status=" + status +
                ", lastCollected=" + lastCollected +
                '}';
    }
}
