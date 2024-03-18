package br.com.apirestfull.server.utils;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Base64;

public class UtilsAuthentication {

    private static String BEARER = "Bearer ";
    private static final String BASIC = "Basic ";

    static public String decodeBase64(String base64) {
        byte[] decodeBytes = Base64.getDecoder().decode(base64);
        return new String(decodeBytes);
    }

    static public String[] extractAuthenticationHeader(HttpServletRequest request) {
        
        String authHeaderJWT = request.getHeader("Authorization");

        String[] dataAuthentication = new String[3];
        
        if (authHeaderJWT != null) {
            if (!authHeaderJWT.split(" ")[0].equals(BEARER)) {
                String authHeaderBasic = request.getHeader("Authorization");

                Boolean authBasic = authHeaderBasic.startsWith(BASIC);

                if (authBasic) {
                    String[] credentials = decodeBase64(request.getHeader("Authorization").replace(BASIC, ""))
                            .split(":");

                    String username = credentials[0];
                    String password = credentials[1];

                    dataAuthentication[0] = "Basic";
                    dataAuthentication[1] = username;
                    dataAuthentication[2] = password;
                } else {
                    dataAuthentication[0] = "Bearer";
                    dataAuthentication[1] = authHeaderJWT.split(" ")[1];
                }
            } 
        }
        
        if (dataAuthentication[0] == null) {
            dataAuthentication[0] = "undefined";
        }
                
        return dataAuthentication;
    }
}
