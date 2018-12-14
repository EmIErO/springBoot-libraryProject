package com.codecool.library.controller;

import com.codecool.library.repository.BorrowingRepository;
import com.codecool.library.repository.SpecimenRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BorrowingController {

    static final Logger LOGGER = LogManager.getLogger("BorrowingController:");

    @Autowired
    private BorrowingRepository borrowingRepo;
    @Autowired
    private SpecimenRepository specimenRepo;

}
