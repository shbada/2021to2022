package com.expedia.b2b.interfaces.controller;

import com.expedia.b2b.application.accommodation.AccommodationFacade;
import com.expedia.b2b.domain.accommodation.AccommodationStore;
import com.expedia.b2b.interfaces.dto.RequestSaveAccommodationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/accommodation")
public class AccommodationController {
    private final AccommodationFacade accommodationFacade;

    @PostMapping("")
    public String saveAccommodation(@RequestBody RequestSaveAccommodationDto requestSaveAccommodationDto) {
        return "";
    }
}
