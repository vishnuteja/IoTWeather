package hello;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Advance {

	private long nTurns;
	private long yPos;
	private long nAdvances;
	private long xPos;
	private String surroundings;
	@JsonProperty("isAtExit")
	private boolean isAtExit;

	public long getnTurns() {
		return nTurns;
	}

	public void setnTurns(long nTurns) {
		this.nTurns = nTurns;
	}

	public long getyPos() {
		return yPos;
	}

	public void setyPos(long yPos) {
		this.yPos = yPos;
	}

	public long getnAdvances() {
		return nAdvances;
	}

	public void setnAdvances(long nAdvances) {
		this.nAdvances = nAdvances;
	}

	public long getxPos() {
		return xPos;
	}

	public void setxPos(long xPos) {
		this.xPos = xPos;
	}

	public String getSurroundings() {
		return surroundings;
	}

	public void setSurroundings(String surroundings) {
		this.surroundings = surroundings;
	}

	public boolean isAtExit() {
		return isAtExit;
	}

	public void setAtExit(boolean isAtExit) {
		this.isAtExit = isAtExit;
	}

	@Override
	public String toString() {
		return "Advance [nTurns=" + nTurns + ", yPos=" + yPos + ", nAdvances=" + nAdvances + ", xPos=" + xPos
				+ ", surroundings=" + surroundings + ", isAtExit=" + isAtExit + "]";
	}

}