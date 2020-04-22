package com.safetyNet.safetyNetAlert.config;

import com.safetyNet.safetyNetAlert.SafetyNetAlertApplication;
import com.safetyNet.safetyNetAlert.SafetyNetData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Component
public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent> {
    private static final Logger logger = LogManager.getLogger(InitialDataLoader.class);
    boolean alreadySetup = false;
    @Autowired
    private SafetyNetData data;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (alreadySetup)
            return;

        data.initData();
        data.linkList();
        System.out.println(data.getPersons());
        alreadySetup = true;
    }
}
