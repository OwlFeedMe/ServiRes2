
package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.ToStringBuilder;

public class BestOpponentAttack_ {

    @SerializedName("attackerTag")
    @Expose
    private String attackerTag;
    @SerializedName("defenderTag")
    @Expose
    private String defenderTag;
    @SerializedName("stars")
    @Expose
    private Integer stars;
    @SerializedName("destructionPercentage")
    @Expose
    private Integer destructionPercentage;
    @SerializedName("order")
    @Expose
    private Integer order;

    public String getAttackerTag() {
        return attackerTag;
    }

    public void setAttackerTag(String attackerTag) {
        this.attackerTag = attackerTag;
    }

    public String getDefenderTag() {
        return defenderTag;
    }

    public void setDefenderTag(String defenderTag) {
        this.defenderTag = defenderTag;
    }

    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }

    public Integer getDestructionPercentage() {
        return destructionPercentage;
    }

    public void setDestructionPercentage(Integer destructionPercentage) {
        this.destructionPercentage = destructionPercentage;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("attackerTag", attackerTag).append("defenderTag", defenderTag).append("stars", stars).append("destructionPercentage", destructionPercentage).append("order", order).toString();
    }

}
