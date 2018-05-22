package com.wang.aishenhuo.pc.api.myBatis.model;

/**
 * 
 * xcx_dynamic
 *
 * @mbggenerated 2018-05-21 09:36:55
 */
public class XcxDynamicWithBLOBs extends XcxDynamic {
    /**
     *
     * xcx_dynamic.content
     *
     * @mbggenerated 2018-05-21 09:36:55
     */
    private String content;

    /**
     *
     * xcx_dynamic.img
     *
     * @mbggenerated 2018-05-21 09:36:55
     */
    private String img;

    /**
     * @mbggenerated 2018-05-21 09:36:55
     */
    public String getContent() {
        return content;
    }

    /**
     * @mbggenerated 2018-05-21 09:36:55
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * @mbggenerated 2018-05-21 09:36:55
     */
    public String getImg() {
        return img;
    }

    /**
     * @mbggenerated 2018-05-21 09:36:55
     */
    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }
}