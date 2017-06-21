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
    
    public static final String SETTING_OUTPUT_APP_TAG = "outputAppTag";
    public static final String SETTING_INPUT_FILE = "inputFile";
    public static final String SETTING_OUTPUT_FILE = "outputFile";
    
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
        appSettings.setProperty(SETTING_INPUT_FILE, "mymap.map");
        appSettings.setProperty(SETTING_OUTPUT_FILE, "mymap_converted.map");
        appSettings.setProperty("inputFormat", "q2");
        appSettings.setProperty("outputFormat", "q1");
        appSettings.setProperty("allowOverwrite", "false");
        appSettings.setProperty("outputBrushScaleX", "1.0");
        appSettings.setProperty("outputBrushScaleY", "1.0");
        appSettings.setProperty("outputBrushScaleZ", "1.0");
        appSettings.setProperty("outputTextureScaleVertical", "1.0");
        appSettings.setProperty("outputTextureScaleHorizontal", "1.0");
        appSettings.setProperty(SETTING_OUTPUT_APP_TAG, "[QMAPC] ");
    }
    
    public String getAppSettingString(String key) {
        return this.appSettings.getProperty(key);
    }
    
    public void setAppSetting(String key, String value) {
        this.appSettings.setProperty(key, value);
    }
    
    /**
     * Returns a tag to prepend to any output to stdout/err.
     * @return an output tag
     */
    public String getAppTag() {
        return this.getAppSettingString(SETTING_OUTPUT_APP_TAG);
    }
}
