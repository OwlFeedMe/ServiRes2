
package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.ToStringBuilder;

public class War {

    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("teamSize")
    @Expose
    private Integer teamSize;
    @SerializedName("preparationStartTime")
    @Expose
    private String preparationStartTime;
    @SerializedName("startTime")
    @Expose
    private String startTime;
    @SerializedName("endTime")
    @Expose
    private String endTime;
    @SerializedName("clan")
    @Expose
    private Clan clan;
    @SerializedName("opponent")
    @Expose
    private Opponent opponent;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getTeamSize() {
        return teamSize;
    }

    public void setTeamSize(Integer teamSize) {
        this.teamSize = teamSize;
    }

    public String getPreparationStartTime() {
        return preparationStartTime;
    }

    public void setPreparationStartTime(String preparationStartTime) {
        this.preparationStartTime = preparationStartTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Clan getClan() {
        return clan;
    }

    public void setClan(Clan clan) {
        this.clan = clan;
    }

    public Opponent getOpponent() {
        return opponent;
    }

    public void setOpponent(Opponent opponent) {
        this.opponent = opponent;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("state", state).append("teamSize", teamSize).append("preparationStartTime", preparationStartTime).append("startTime", startTime).append("endTime", endTime).append("clan", clan).append("opponent", opponent).toString();
    }

}
