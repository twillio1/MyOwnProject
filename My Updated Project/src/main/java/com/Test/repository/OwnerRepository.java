package com.Test.repository;

import com.Test.entity.OwnerPlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<OwnerPlan,Long> {
}
