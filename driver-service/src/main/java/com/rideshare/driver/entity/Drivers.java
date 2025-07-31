package com.rideshare.driver.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "drivers")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Drivers {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "phone_number", nullable = false, unique = true, length = 15)
    private String phoneNumber;

    @Column(name = "email", unique = true, length = 100)
    private String email;

    @Column(name = "license_number", nullable = false, unique = true, length = 50)
    private String licenseNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private DriverStatus status;

    @OneToOne(mappedBy = "driver", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Vehicle vehicle;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    public void PrePersist() {
        this.createdAt = LocalDateTime.now();
        this.status = DriverStatus.OFFLINE;
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
