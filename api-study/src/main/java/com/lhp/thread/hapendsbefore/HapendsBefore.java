package com.lhp.thread.hapendsbefore;

import lombok.extern.slf4j.Slf4j;

/**
 * @author : lihp
 * @Description :
 * @date : 2022/8/26 14:46
 */
@Slf4j
public class HapendsBefore {
    public static void main(String[] args) {

        int userNum = getUserNum();    // 1
        int teacherNum = getTeacherNum();     // 2
        int totalNum = userNum + teacherNum;
    }

    public static int getUserNum() {
        return 1;
    }

    public static int getTeacherNum() {
        return 2;
    }
}
