package com.alura.aluraAPI.projections;

public interface UserDetailsProjection {

    String getUsername();

    String getPassword();

    Long getRoleId();

    String getAuthority();

    Boolean getIs_Non_Locked();

    Boolean getIs_Non_Expired();

    Boolean getIs_Credentials_Non_Expired();

    Boolean getIs_Enabled();
}
