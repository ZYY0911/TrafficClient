package com.mad.trafficclient.bean;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/9/29 at 8:54
 */
public class Hld {


    /**
     * RedTime : 25
     * GreenTime : 55
     * YellowTime : 5
     */


    private int lk;
    private int RedTime;
    private int GreenTime;
    private int YellowTime;

    public int getLk() {
        return lk;
    }

    public void setLk(int lk) {
        this.lk = lk;
    }

    public int getRedTime() {
        return RedTime;
    }

    public void setRedTime(int RedTime) {
        this.RedTime = RedTime;
    }

    public int getGreenTime() {
        return GreenTime;
    }

    public void setGreenTime(int GreenTime) {
        this.GreenTime = GreenTime;
    }

    public int getYellowTime() {
        return YellowTime;
    }

    public void setYellowTime(int YellowTime) {
        this.YellowTime = YellowTime;
    }
}
