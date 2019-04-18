package org.eocencle.dasislcy.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "demo")
public class DemoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 域名
     */
    private String domain;

    /**
     * 标题
     */
    private String title;

    /**
     * url
     */
    private String link;

    /**
     * 日期
     */
    private Date createdate;

    /**
     * 作者
     */
    private String author;

    /**
     * 站点地址
     */
    private String zddz;

    /**
     * 中文地址
     */
    private String zwdz;

    /**
     * 正负面
     */
    private String zfm;

    /**
     * 转载数
     */
    private Integer zzs;

    /**
     * 回复数
     */
    private Integer hfs;

    /**
     * 点击率
     */
    private Integer djl;

    /**
     * 是否垃圾信息
     */
    private String sfljxx;

    /**
     * 图片连接
     */
    private String piclink;

    /**
     * 正文
     */
    private String content;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取域名
     *
     * @return domain - 域名
     */
    public String getDomain() {
        return domain;
    }

    /**
     * 设置域名
     *
     * @param domain 域名
     */
    public void setDomain(String domain) {
        this.domain = domain == null ? null : domain.trim();
    }

    /**
     * 获取标题
     *
     * @return title - 标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置标题
     *
     * @param title 标题
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * 获取url
     *
     * @return link - url
     */
    public String getLink() {
        return link;
    }

    /**
     * 设置url
     *
     * @param link url
     */
    public void setLink(String link) {
        this.link = link == null ? null : link.trim();
    }

    /**
     * 获取日期
     *
     * @return createdate - 日期
     */
    public Date getCreatedate() {
        return createdate;
    }

    /**
     * 设置日期
     *
     * @param createdate 日期
     */
    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    /**
     * 获取作者
     *
     * @return author - 作者
     */
    public String getAuthor() {
        return author;
    }

    /**
     * 设置作者
     *
     * @param author 作者
     */
    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    /**
     * 获取站点地址
     *
     * @return zddz - 站点地址
     */
    public String getZddz() {
        return zddz;
    }

    /**
     * 设置站点地址
     *
     * @param zddz 站点地址
     */
    public void setZddz(String zddz) {
        this.zddz = zddz == null ? null : zddz.trim();
    }

    /**
     * 获取中文地址
     *
     * @return zwdz - 中文地址
     */
    public String getZwdz() {
        return zwdz;
    }

    /**
     * 设置中文地址
     *
     * @param zwdz 中文地址
     */
    public void setZwdz(String zwdz) {
        this.zwdz = zwdz == null ? null : zwdz.trim();
    }

    /**
     * 获取正负面
     *
     * @return zfm - 正负面
     */
    public String getZfm() {
        return zfm;
    }

    /**
     * 设置正负面
     *
     * @param zfm 正负面
     */
    public void setZfm(String zfm) {
        this.zfm = zfm == null ? null : zfm.trim();
    }

    /**
     * 获取转载数
     *
     * @return zzs - 转载数
     */
    public Integer getZzs() {
        return zzs;
    }

    /**
     * 设置转载数
     *
     * @param zzs 转载数
     */
    public void setZzs(Integer zzs) {
        this.zzs = zzs;
    }

    /**
     * 获取回复数
     *
     * @return hfs - 回复数
     */
    public Integer getHfs() {
        return hfs;
    }

    /**
     * 设置回复数
     *
     * @param hfs 回复数
     */
    public void setHfs(Integer hfs) {
        this.hfs = hfs;
    }

    /**
     * 获取点击率
     *
     * @return djl - 点击率
     */
    public Integer getDjl() {
        return djl;
    }

    /**
     * 设置点击率
     *
     * @param djl 点击率
     */
    public void setDjl(Integer djl) {
        this.djl = djl;
    }

    /**
     * 获取是否垃圾信息
     *
     * @return sfljxx - 是否垃圾信息
     */
    public String getSfljxx() {
        return sfljxx;
    }

    /**
     * 设置是否垃圾信息
     *
     * @param sfljxx 是否垃圾信息
     */
    public void setSfljxx(String sfljxx) {
        this.sfljxx = sfljxx == null ? null : sfljxx.trim();
    }

    /**
     * 获取图片连接
     *
     * @return piclink - 图片连接
     */
    public String getPiclink() {
        return piclink;
    }

    /**
     * 设置图片连接
     *
     * @param piclink 图片连接
     */
    public void setPiclink(String piclink) {
        this.piclink = piclink == null ? null : piclink.trim();
    }

    /**
     * 获取正文
     *
     * @return content - 正文
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置正文
     *
     * @param content 正文
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}