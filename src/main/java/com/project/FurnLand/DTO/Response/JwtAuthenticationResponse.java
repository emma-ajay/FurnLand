package com.project.FurnLand.DTO.Response;
public class JwtAuthenticationResponse {
    private String accessToken;
    private String tokenType = "Bearer";
    private String email;
    private Object roles;

    public JwtAuthenticationResponse(String accessToken, String email, Object roles) {
        this.accessToken = accessToken;
        this.email= email;
        this.roles =roles;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;

    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Object getRoles() {
        return roles;
    }

    public void setRoles(Object roles) {
        this.roles = roles;
    }
}
