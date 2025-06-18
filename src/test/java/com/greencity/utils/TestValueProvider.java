package com.greencity.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestValueProvider {
    Properties properties;

    public TestValueProvider() {
        try {
            FileInputStream fileInputStream = new FileInputStream("src/test/resources/config.properties");
            properties = new Properties();
            properties.load(fileInputStream);
        } catch (IOException err) {
            System.out.println(err.getMessage());
            System.out.println("Use system env");
        }
    }

    public String getBaseUIUrl() {
        return properties != null ? properties.getProperty("base.ui.url") : System.getenv("BASE_UI_URL");
    }

    public String getBaseAPIGreencityUrl() {
        return properties != null ? properties.getProperty("base.api.greencity.url") : System.getenv("BASE_API_GREENCITY_URL");
    }

    public String getBaseAPIUserUrl() {
        return properties != null ? properties.getProperty("base.api.user.url") : System.getenv("BASE_API_USER_URL");
    }

    public int getImplicitlyWait() {
        return properties != null ? Integer.parseInt(properties.getProperty("implicitlyWait")) : Integer.parseInt(System.getenv("IMPLICITLY_WAIT"));
    }


    public String getUserEmail() {
        return properties != null ? properties.getProperty("user.email") : System.getenv("USER_EMAIL");
    }

    public String getUserName() {
        return properties != null ? properties.getProperty("user.name") : System.getenv("USER_NAME");
    }

    public String getUserPassword() {
        return properties != null ? properties.getProperty("user.password") : System.getenv("USER_PASSWORD");
    }

    public String getSecretKey() {
        return properties != null ? properties.getProperty("user.secretKey") : System.getenv("USER_SECRET_KEY");
    }

    public String getAdminEmail() {
        return properties != null ? properties.getProperty("admin.email") : System.getenv("ADMIN_EMAIL");
    }

    public String getAdminName() {
        return properties != null ? properties.getProperty("admin.name") : System.getenv("ADMIN_NAME");
    }

    public String getAdminPassword() {
        return properties != null ? properties.getProperty("admin.password") : System.getenv("ADMIN_PASSWORD");
    }

    public String getJDBCGreenCityUsername() {
        return properties != null ? properties.getProperty("JDBCGreenCityUsername") : System.getenv("JDBC_GREENCITY_USERNAME");
    }

    public String getJDBCGreenCityPassword() {
        return properties != null ? properties.getProperty("JDBCGreenCityPassword") : System.getenv("JDBC_GREENCITY_PASSWORD");
    }

    public String getJDBCGreenCityURL() {
        return properties != null ? properties.getProperty("JDBCGreenCityURL") : System.getenv("JDBC_GREENCITY_URL");
    }
    public String getLocalStorageAccessToken() {
        return properties != null ? properties.getProperty("ls.accessToken") : System.getenv("LS_ACCESS_TOKEN");
    }
    public String getLocalStorageUserId() {
        return properties != null ? properties.getProperty("ls.userId") : System.getenv("LS_USER_ID");
    }
    public String getLocalStorageRefreshToken() {
        return properties != null ? properties.getProperty("ls.refreshToken") : System.getenv("LS_REFRESH_TOKEN");
    }
    public String getLocalStorageName() {
        return properties != null ? properties.getProperty("ls.name") : System.getenv("LS_NAME");
    }

    public String getLocalStorageAccessTokenUserB() {
        return properties != null ? properties.getProperty("ls.accessTokenUserB") : System.getenv("LS_ACCESS_TOKEN_USER_B");
    }
    public String getLocalStorageUserIdUserB() {
        return properties != null ? properties.getProperty("ls.userIdUserB") : System.getenv("LS_USER_ID_USER_B");
    }
    public String getLocalStorageRefreshTokenUserB() {
        return properties != null ? properties.getProperty("ls.refreshTokenUserB") : System.getenv("LS_REFRESH_TOKEN_USER_B");
    }
    public String getLocalStorageNameUserB() {
        return properties != null ? properties.getProperty("ls.nameUserB") : System.getenv("LS_NAME_USER_B");
    }
}