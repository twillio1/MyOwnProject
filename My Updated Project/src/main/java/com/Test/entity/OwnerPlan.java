package com.Test.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name="ownerPlans")
@Data
public class OwnerPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ownerPlanId;
    private long userId;
    private boolean subscriptionStatus;
    private LocalDateTime subscriptionStartDate;
    private  LocalDateTime subscriptionEndDate;

    private int noOfDays;
}
