package com.huang.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class teamMember {

    private int id;  //队伍ID
    private String captain;  //队伍队长
    private String member1;  //成员1
    private String member2;  //成员2
    private String member3;  //成员3
    private String member4;  //成员4
    private String member5;  //成员5
}
