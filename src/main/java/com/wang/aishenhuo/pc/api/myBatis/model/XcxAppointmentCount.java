package com.wang.aishenhuo.pc.api.myBatis.model;

/**
 * 
 * xcx_appointment_count
 *
 * @mbggenerated 2018-05-21 09:36:55
 */
public class XcxAppointmentCount {
    /**
     *
     * xcx_appointment_count.uid
     *
     * @mbggenerated 2018-05-21 09:36:55
     */
    private String uid;

    /**
     *
     * xcx_appointment_count.count
     *
     * @mbggenerated 2018-05-21 09:36:55
     */
    private Long count;

    /**
     * @mbggenerated 2018-05-21 09:36:55
     */
    public String getUid() {
        return uid;
    }

    /**
     * @mbggenerated 2018-05-21 09:36:55
     */
    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    /**
     * @mbggenerated 2018-05-21 09:36:55
     */
    public Long getCount() {
        return count;
    }

    /**
     * @mbggenerated 2018-05-21 09:36:55
     */
    public void setCount(Long count) {
        this.count = count;
    }
}