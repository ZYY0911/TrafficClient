package com.mad.trafficclient.bean;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/9/29 at 10:23
 */
public class Sjfx   {

    /**
     * carnumber : 鲁 B10030
     * pcode : 1001A
     * paddr : 联想路
     * datetime : 2009-01-01 10:04:03
     */

    private String carnumber;
    private String pcode;
    private String paddr;
    private String datetime;

    public String getCarnumber() {
        return carnumber;
    }

    public void setCarnumber(String carnumber) {
        this.carnumber = carnumber;
    }

    public String getPcode() {
        return pcode;
    }

    public void setPcode(String pcode) {
        this.pcode = pcode;
    }

    public String getPaddr() {
        return paddr;
    }

    public void setPaddr(String paddr) {
        this.paddr = paddr;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }
}
