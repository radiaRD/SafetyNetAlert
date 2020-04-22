package com.safetyNet.safetyNetAlert;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SpringBootApplication
public class SafetyNetAlertApplication {
    private static final Logger logger = LogManager.getLogger(SafetyNetAlertApplication.class);

    public static void main(String[] args) {
        logger.info("Initializing SafetyNetAlert");
        SpringApplication.run(SafetyNetAlertApplication.class, args);
    }

}
