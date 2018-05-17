package com.wang.aishenhuo.pc.api.myBatis.model;

/**
 * 
 * xcx_notice
 *
 * @mbggenerated 2018-05-17 09:51:53
 */
public class XcxNotice {
    /**
     *
     * xcx_notice.id
     *
     * @mbggenerated 2018-05-17 09:51:53
     */
    private Integer id;

    /**
     *
     * xcx_notice.title
     *
     * @mbggenerated 2018-05-17 09:51:53
     */
    private String title;

    /**
     *
     * xcx_notice.status
     *
     * @mbggenerated 2018-05-17 09:51:53
     */
    private Byte status;

    /**
     *
     * xcx_notice.content
     *
     * @mbggenerated 2018-05-17 09:51:53
     */
    private String content;

    /**
     * @mbggenerated 2018-05-17 09:51:53
     */
    public Integer getId() {
        return id;
    }

    /**
     * @mbggenerated 2018-05-17 09:51:53
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @mbggenerated 2018-05-17 09:51:53
     */
    public String getTitle() {
        return title;
    }

    /**
     * @mbggenerated 2018-05-17 09:51:53
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * @mbggenerated 2018-05-17 09:51:53
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * @mbggenerated 2018-05-17 09:51:53
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * @mbggenerated 2018-05-17 09:51:53
     */
    public String getContent() {
        return content;
    }

    /**
     * @mbggenerated 2018-05-17 09:51:53
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}