package com.huang.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin {

    private int ad_id;
    private String ad_name;
    private String ad_pwd;
    private String ad_identity;

}
