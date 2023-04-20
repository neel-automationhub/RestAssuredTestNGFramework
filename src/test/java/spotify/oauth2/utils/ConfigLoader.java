package spotify.oauth2.utils;

import java.util.Properties;

public class ConfigLoader {

    private final Properties properties;
    private static ConfigLoader configLoader;

    private ConfigLoader() {
        properties = PropertyUtils.propertyLoader("src/test/resources/config.properties");
    }

    public static ConfigLoader getInstance() {
        if (configLoader == null) {
            configLoader = new ConfigLoader();
        }
        return configLoader;
    }

    public String getClientId() {
        String clientId = properties.getProperty("client_id");
        if(clientId != null) return clientId;
        else throw new RuntimeException("property client_id is not specified in the config.properties file");
    }

    public String getClientSecret() {
        String clientSecret = properties.getProperty("client_secret");
        if(clientSecret != null) return clientSecret;
        else throw new RuntimeException("property client_secret is not specified in the config.properties file");
    }

    public String getGrantType() {
        String grantType = properties.getProperty("grant_type");
        if(grantType != null) return grantType;
        else throw new RuntimeException("property grant_type is not specified in the config.properties file");
    }

    public String getRefreshToken() {
        String refreshToken = properties.getProperty("refresh_token");
        if(refreshToken != null) return refreshToken;
        else throw new RuntimeException("property refresh_token is not specified in the config.properties file");
    }

}
