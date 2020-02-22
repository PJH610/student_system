package com.letopo.auth.model;

import lombok.Data;

/**
 * @author jh
 * @version 1.0
 * @date 2020-02-20 11:02
 */

@Data
public class User {

    private String id;
    private String username;
    private String roles;
    private String permissions;
}