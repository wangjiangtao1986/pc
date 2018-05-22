package com.wang.aishenhuo.pc.api.myBatis.model;

/**
 * 
 * xcx_appointment_count
 *
 * @mbggenerated 2018-05-22 17:46:16
 */
public class XcxAppointmentCount {
    /**
     *
     * xcx_appointment_count.uid
     *
     * @mbggenerated 2018-05-22 17:46:16
     */
    private String uid;

    /**
     *
     * xcx_appointment_count.count
     *
     * @mbggenerated 2018-05-22 17:46:16
     */
    private Long count;

    /**
     * @mbggenerated 2018-05-22 17:46:16
     */
    public String getUid() {
        return uid;
    }

    /**
     * @mbggenerated 2018-05-22 17:46:16
     */
    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    /**
     * @mbggenerated 2018-05-22 17:46:16
     */
    public Long getCount() {
        return count;
    }

    /**
     * @mbggenerated 2018-05-22 17:46:16
     */
    public void setCount(Long count) {
        this.count = count;
    }
}