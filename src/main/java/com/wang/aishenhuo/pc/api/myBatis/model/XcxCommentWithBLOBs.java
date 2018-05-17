package com.wang.aishenhuo.pc.api.myBatis.model;

/**
 * 
 * xcx_comment
 *
 * @mbggenerated 2018-05-17 09:51:52
 */
public class XcxCommentWithBLOBs extends XcxComment {
    /**
     *
     * xcx_comment.content
     *
     * @mbggenerated 2018-05-17 09:51:52
     */
    private String content;

    /**
     *
     * xcx_comment.img
     *
     * @mbggenerated 2018-05-17 09:51:52
     */
    private String img;

    /**
     *
     * xcx_comment.reply
     *
     * @mbggenerated 2018-05-17 09:51:52
     */
    private String reply;

    /**
     * @mbggenerated 2018-05-17 09:51:52
     */
    public String getContent() {
        return content;
    }

    /**
     * @mbggenerated 2018-05-17 09:51:52
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * @mbggenerated 2018-05-17 09:51:52
     */
    public String getImg() {
        return img;
    }

    /**
     * @mbggenerated 2018-05-17 09:51:52
     */
    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    /**
     * @mbggenerated 2018-05-17 09:51:52
     */
    public String getReply() {
        return reply;
    }

    /**
     * @mbggenerated 2018-05-17 09:51:52
     */
    public void setReply(String reply) {
        this.reply = reply == null ? null : reply.trim();
    }
}