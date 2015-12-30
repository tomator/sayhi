package xyz.iseeyou.sayhi.bean;

import java.util.List;

import cn.bmob.im.bean.BmobChatUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.datatype.BmobGeoPoint;
import cn.bmob.v3.datatype.BmobRelation;

/**
 * 重载BmobChatUser对象：若还有其他需要增加的属性可在此添加
 *
 * @author smile
 * @ClassName: TextUser
 * @Description: TODO
 * @date 2014-5-29 下午6:15:45
 */
public class User extends BmobChatUser implements Comparable {
    private static final long serialVersionUID = 1L;

    private BmobRelation blogs;
    private String sortLetters;

    /**
     * 个人可以设置的信息
     */
    private Boolean sex;
    private BmobGeoPoint location;
    private Integer hight;
    private Integer weight;
    private long birthday;
    private List<BmobFile> avatars;
    private String city;
    private String hobby;
    private String description;
    private Integer rank;
    private Integer report;
    private long onlineTime;

    @Override
    public int compareTo(Object another) {
        if(another instanceof User){
            User other = (User)another;
            if(other.getObjectId().equals(getObjectId())){
                return 0;
            }else {

            }
        }
        return -1;
    }

    public long getOnlineTime() {
        return onlineTime;
    }

    public void setOnlineTime(long onlineTime) {
        this.onlineTime = onlineTime;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Integer getReport() {
        return report;
    }

    public void setReport(Integer report) {
        this.report = report;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public long getBirthday() {
        return birthday;
    }

    public void setBirthday(long birthday) {
        this.birthday = birthday;
    }

    public List<BmobFile> getAvatars() {
        return avatars;
    }

    public void setAvatars(List<BmobFile> avatars) {
        this.avatars = avatars;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getHight() {
        return hight;
    }

    public void setHight(Integer hight) {
        this.hight = hight;
    }

    public BmobRelation getBlogs() {
        return blogs;
    }

    public void setBlogs(BmobRelation blogs) {
        this.blogs = blogs;
    }

    public BmobGeoPoint getLocation() {
        return location;
    }

    public void setLocation(BmobGeoPoint location) {
        this.location = location;
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public String getSortLetters() {
        return sortLetters;
    }

    public void setSortLetters(String sortLetters) {
        this.sortLetters = sortLetters;
    }

}
