package com.huang.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameConditionVo {
    private String g_signup_sTime;
    private String g_signup_eTime;
    private String g_type;
    private String g_level;
}
