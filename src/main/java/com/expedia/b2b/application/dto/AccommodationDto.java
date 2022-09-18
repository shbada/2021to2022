package com.expedia.b2b.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccommodationDto {
    private Long id;
    private String accommodationName;
    private int accommodationType;
    private String bizno;
    private String countryCd;
    private String city;
    private String state;
    private String street;
    private String zipcode;
    private String checkinDescription;
    private LocalTime checkinStartTime;
    private LocalTime checkoutEndTime;
    private int minCheckinAge;
    private String respeUserId;
    private String respeEmail;
    private String respeHpno;
    private String telNo;
    private String lastModifiedBy;
    private LocalDateTime lastModified;
    private String createdBy;
    private LocalDateTime createdAt;
}
