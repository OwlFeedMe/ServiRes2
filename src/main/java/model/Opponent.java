
package model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Opponent {

    @SerializedName("tag")
    @Expose
    private String tag;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("badgeUrls")
    @Expose
    private BadgeUrls_ badgeUrls;
    @SerializedName("clanLevel")
    @Expose
    private Integer clanLevel;
    @SerializedName("attacks")
    @Expose
    private Integer attacks;
    @SerializedName("stars")
    @Expose
    private Integer stars;
    @SerializedName("expEarned")
    @Expose
    private Integer expEarned;
    @SerializedName("members")
    @Expose
    private List<Member_> members = null;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BadgeUrls_ getBadgeUrls() {
        return badgeUrls;
    }

    public void setBadgeUrls(BadgeUrls_ badgeUrls) {
        this.badgeUrls = badgeUrls;
    }

    public Integer getClanLevel() {
        return clanLevel;
    }

    public void setClanLevel(Integer clanLevel) {
        this.clanLevel = clanLevel;
    }

    public Integer getAttacks() {
        return attacks;
    }

    public void setAttacks(Integer attacks) {
        this.attacks = attacks;
    }

    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }

    public Integer getExpEarned() {
        return expEarned;
    }

    public void setExpEarned(Integer expEarned) {
        this.expEarned = expEarned;
    }

    public List<Member_> getMembers() {
        return members;
    }

    public void setMembers(List<Member_> members) {
        this.members = members;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("tag", tag).append("name", name).append("badgeUrls", badgeUrls).append("clanLevel", clanLevel).append("attacks", attacks).append("stars", stars).append("expEarned", expEarned).append("members", members).toString();
    }

}
