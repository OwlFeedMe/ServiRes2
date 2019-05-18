package com.dekses.jersey.docker.demo;

import com.sun.jersey.api.core.DefaultResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;



import java.util.HashMap;
import java.util.Map;

public class MyResourceConfig extends DefaultResourceConfig {

    public MyResourceConfig() {
        super(ClanWar.class);
        Map<String, Object> maps = new HashMap<String, Object>();
        maps.put(ResourceConfig.PROPERTY_CONTAINER_RESPONSE_FILTERS, CORSFilter.class);
       
        setPropertiesAndFeatures(maps);
    }
}
