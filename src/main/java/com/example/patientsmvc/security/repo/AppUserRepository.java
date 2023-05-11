package com.example.patientsmvc.security.repo;

import com.example.patientsmvc.security.entities.AppUser;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser,String> {
    AppUser findByUsername(String username);
}