package com.actuator.overrideactuator;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.actuate.info.Info.Builder;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

@Component
public class CustomInfo implements InfoContributor{

	//Overriding info Actuator : http://localhost:8088/olxapp/info  method: get 
    @Override
    public void contribute(Builder builder) {
        Map<String, String> info = new HashMap<String, String>();
        info.put("vendorName", "Zensar");
        info.put("applicationName", "OLX-App");
        info.put("version", "2.4");
        info.put("releaseDate", LocalDate.now().toString());
        builder.withDetail("info", info);
    }

}
