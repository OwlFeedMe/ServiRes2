
package model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Member {

    @SerializedName("tag")
    @Expose
    private String tag;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("townhallLevel")
    @Expose
    private Integer townhallLevel;
    @SerializedName("mapPosition")
    @Expose
    private Integer mapPosition;
    @SerializedName("attacks")
    @Expose
    private List<Attack> attacks = null;
    @SerializedName("opponentAttacks")
    @Expose
    private Integer opponentAttacks;
    @SerializedName("bestOpponentAttack")
    @Expose
    private BestOpponentAttack bestOpponentAttack;

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

    public Integer getTownhallLevel() {
        return townhallLevel;
    }

    public void setTownhallLevel(Integer townhallLevel) {
        this.townhallLevel = townhallLevel;
    }

    public Integer getMapPosition() {
        return mapPosition;
    }

    public void setMapPosition(Integer mapPosition) {
        this.mapPosition = mapPosition;
    }

    public List<Attack> getAttacks() {
        return attacks;
    }

    public void setAttacks(List<Attack> attacks) {
        this.attacks = attacks;
    }

    public Integer getOpponentAttacks() {
        return opponentAttacks;
    }

    public void setOpponentAttacks(Integer opponentAttacks) {
        this.opponentAttacks = opponentAttacks;
    }

    public BestOpponentAttack getBestOpponentAttack() {
        return bestOpponentAttack;
    }

    public void setBestOpponentAttack(BestOpponentAttack bestOpponentAttack) {
        this.bestOpponentAttack = bestOpponentAttack;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("tag", tag).append("name", name).append("townhallLevel", townhallLevel).append("mapPosition", mapPosition).append("attacks", attacks).append("opponentAttacks", opponentAttacks).append("bestOpponentAttack", bestOpponentAttack).toString();
    }

}
