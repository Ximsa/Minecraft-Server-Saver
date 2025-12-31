package com.ximsa.serversaver;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class Config {

    public static float activeTickrate = 20.0f;
    public static float idleTickrate = 1.0f;

    public static void synchronizeConfiguration(File configFile) {
        Configuration configuration = new Configuration(configFile);
        activeTickrate = configuration.getFloat(
            "activeTickrate",
            Configuration.CATEGORY_GENERAL,
            activeTickrate,
            0f,
            1000f,
            "Tickrate when at least 1 player is online");
        idleTickrate = configuration.getFloat(
            "idleTickrate",
            Configuration.CATEGORY_GENERAL,
            idleTickrate,
            0f,
            1000f,
            "Tickrate when no player is online");

        if (configuration.hasChanged()) {
            configuration.save();
        }
    }
}
