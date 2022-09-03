package com.huang.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Members {

    private int m_id;
    private String m_name;
    private String m_captain;
    private String m_member;
    private int m_tid;
    private int m_mid;

}
