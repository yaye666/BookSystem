package model;

import java.util.Date;

public class Problem {
    private Integer pid;
    private Integer uid;
    private String title;
    private String page;
    private String content;
    private String link;
    private Integer status;
    private Date time;
    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Problem{" +
                "pid=" + pid +
                ", uid=" + uid +
                ", title='" + title + '\'' +
                ", page='" + page + '\'' +
                ", content='" + content + '\'' +
                ", link='" + link + '\'' +
                ", status='" + status + '\'' +
                ", time=" + time +
                '}';
    }
}
