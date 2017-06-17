/*
 * This file is part of qmapc. See the LICENSE file for license information.
 */
package org.rcmd.qmapc;

import java.util.Properties;

/**
 * Singleton settings class.
 * @author spirit
 */
public class Settings {
    
    private static Settings instance;
    
    private final Properties appSettings;
    
    private Settings(){
         this.appSettings = new Properties();
         initDefaults();
    }
    
    public static synchronized Settings getInstance(){
        if(instance == null){
            instance = new Settings();
        }
        return instance;
    }
    
    private void initDefaults() {        
        appSettings.setProperty("inputFile", "mymap.map");
        appSettings.setProperty("outputFile", "mymap_converted.map");
        appSettings.setProperty("inputFormat", "q2");
        appSettings.setProperty("outputFormat", "q1");
        appSettings.setProperty("allowOverwrite", "false");
        appSettings.setProperty("outputBrushScaleX", "1.0");
        appSettings.setProperty("outputBrushScaleY", "1.0");
        appSettings.setProperty("outputBrushScaleZ", "1.0");
        appSettings.setProperty("outputTextureScaleVertical", "1.0");
        appSettings.setProperty("outputTextureScaleHorizontal", "1.0");
    }
    
    public String getAppSettingString(String key) {
        return this.appSettings.getProperty(key);
    }
    
    public void setAppSetting(String key, String value) {
        this.appSettings.setProperty(key, value);
    }
}
