package xyz.iseeyou.sayhi.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by bici on 15/12/5.
 */
public class Comment extends BmobObject {
    private Blog blog;
    private String content;
    private User user;

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
