package com.safetyNet.safetyNetAlert.config;

import com.safetyNet.safetyNetAlert.AgeCalculator;
import com.safetyNet.safetyNetAlert.SafetyNetData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;


@Component
public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent> {

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
