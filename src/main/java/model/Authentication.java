package model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Authentication {
    private String access_token;
    private List<String> permissions;
    private String scope;
    private String token_type;
    private int expires_in;
}