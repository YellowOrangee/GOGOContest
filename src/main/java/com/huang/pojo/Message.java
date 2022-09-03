package com.huang.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    private int m_id;
    private String m_body;  //消息内容
    private String m_type;  //消息类型（比赛/组队）
    private String m_time;  //消息通知时间
    private int m_uid;
    private int m_xid;    //论坛/比赛等id，通过type具体判断是哪个的id
}
