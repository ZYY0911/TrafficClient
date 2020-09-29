package com.mad.trafficclient.bean;

/**
 * @LogIn Name zhangyingyu
 * @Create by 张瀛煜 on 2020-09-29 at 15:52
 */
public class Yhzx {

    /**
     * pname : 戚明艳
     * pcardid : 15020519681120713X
     * psex : 男
     * username : user1
     * ptel : 13156034256
     * pregistdate : 2018-8-22 10:07:12
     * UserRole : RO2
     */

    private String pname;
    private String pcardid;
    private String psex;
    private String username;
    private String ptel;
    private String pregistdate;
    private String UserRole;
    private boolean is;
    private boolean sc;
    private String time;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isIs() {
        return is;
    }

    public void setIs(boolean is) {
        this.is = is;
    }

    public boolean isSc() {
        return sc;
    }

    public void setSc(boolean sc) {
        this.sc = sc;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPcardid() {
        return pcardid;
    }

    public void setPcardid(String pcardid) {
        this.pcardid = pcardid;
    }

    public String getPsex() {
        return psex;
    }

    public void setPsex(String psex) {
        this.psex = psex;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPtel() {
        return ptel;
    }

    public void setPtel(String ptel) {
        this.ptel = ptel;
    }

    public String getPregistdate() {
        return pregistdate;
    }

    public void setPregistdate(String pregistdate) {
        this.pregistdate = pregistdate;
    }

    public String getUserRole() {
        return UserRole;
    }

    public void setUserRole(String UserRole) {
        this.UserRole = UserRole;
    }
}
