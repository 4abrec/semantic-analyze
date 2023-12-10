package com.example.semanticanalyze.data.repository;

import com.example.semanticanalyze.data.entity.Role;
import com.example.semanticanalyze.data.entity.RoleEnum;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, UUID> {

  Set<Role> findByNameIn(List<RoleEnum> names);
}
