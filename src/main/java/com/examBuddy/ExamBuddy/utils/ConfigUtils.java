package com.examBuddy.ExamBuddy.utils;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map.Entry;
import java.util.Properties;

@Data
@Service
@Slf4j
public class ConfigUtils {

	public Properties properties = new Properties();

    @Autowired
    private Environment environment;


    public ConfigUtils() {
        log.info("Config Utils started");
    }
	
	@PostConstruct
    void loadConfigsAndVersion() {
        loadCommonProperties();
        loadEnvProperties();
    }

	private void loadCommonProperties() {
        String[] profiles = environment.getActiveProfiles();
        log.info("Printing profiles");
        for (String profile : profiles) {
            log.info(profile);
        }
		try {
			Resource resource = new ClassPathResource("application.properties");
			this.properties = PropertiesLoaderUtils.loadProperties(resource);
		} catch (Exception e) {
			log.info("Error loading property file)", e);
			throw new RuntimeException(e);
		}
	}

	public String getProfile(){
        return environment.getActiveProfiles()[0];
    }

    private void loadEnvProperties() {
        if (environment.getActiveProfiles().length == 0 || environment.getActiveProfiles()[0].equals("local")) {
            log.info("No active profiles found");
            return;
        }
        String profile = environment.getActiveProfiles()[0];
        InputStream is = null;
        Properties properties = new Properties();
        try {
            if (profile != null && !profile.equals("local") && !profile.equals("default")) {
                final String appenginePropertiesFilePath = "/application-" + profile + ".properties";
                Resource resource = new ClassPathResource(appenginePropertiesFilePath);
    			properties = PropertiesLoaderUtils.loadProperties(resource);
    			for (Entry<Object, Object> entry : properties.entrySet()) {
                    this.properties.put(entry.getKey(), entry.getValue());
                }
            }
        } catch (Exception e) {
            log.error("Error loading environment specific properties\t" + e.getLocalizedMessage(), e);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                	log.error("Error closing environment specific properties file:\t" + e.getLocalizedMessage(), e);
                }
            }
        }
    }

	public Character getCharacterValue(String key) {
		Object value = getValue(key);
		if (null != value) {
			return value.toString().trim().charAt(0);
		}
		return null;
	}

	public Object getValue(String key) {
		Object value = this.properties.getProperty(key);
		if (value == null) {
			log.error("getValue - ConfigUtils returned a null value for {}", key);
		}
		return value;
	}

	public String getStringValue(String key) {
		Object value = getValue(key);
		if (null != value) {
			return value.toString().trim();
		}
		return null;
	}
	
	public String getStringValue(String key, Boolean log) {
		Object value = getValue(key, log);
		if (null != value) {
			return value.toString().trim();
		}
		return null;
	}
	
	public Object getValue(String key, Boolean log) {
		Object value = this.properties.getProperty(key);
		if (value == null && log) {
//            log.error("getValue - ConfigUtils returned a null value for " + key);
		}
		return value;
	}

	public Integer getIntValue(String key) {
		Object value = getValue(key);
		if (null != value) {
			return Integer.parseInt(value.toString().trim());
		}
		return null;
	}

	public Double getDoubleValue(String key) {
		Object value = getValue(key);
		if (null != value) {
			return Double.parseDouble(value.toString().trim());
		}
		return null;
	}

	public Long getLongValue(String key) {
		Object value = getValue(key);
		if (null != value) {
			return Long.parseLong(value.toString().trim());
		}
		return null;
	}

	public boolean getBooleanValue(String key) {
		Object value = getValue(key);
		if (null != value) {
			return Boolean.parseBoolean(value.toString().trim());
		}
		return false;
	}

	public Float getFloatValue(String key) {
		Object value = getValue(key);
		if (null != value) {
			return Float.parseFloat(value.toString().trim());
		}
		return null;
	}

	
}
