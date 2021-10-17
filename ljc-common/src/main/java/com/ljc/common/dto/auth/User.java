package com.ljc.common.dto.auth;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
public class User implements Serializable {

    private String username;
    private String password;
    private List<String> authorities;
    private Map<String, Object> additionalInformation;

}
