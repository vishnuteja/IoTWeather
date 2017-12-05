package hello;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GameInfo {

	private String id;
	private String persona;
    private Ability abilities;
    private long xStartingPosition;
    private long yStartingPosition;
    private long xPos;
    private long yPos;
    private String startingDirection;
    private String baseUrl;
    private String controller;
    private boolean quickFeet;
    private boolean isAtExit;
    
    public GameInfo() {
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPersona() {
		return persona;
	}

	public void setPersona(String persona) {
		this.persona = persona;
	}
	
	public Ability getAbilities() {
		return abilities;
	}

	public void setAbilities(Ability abilities) {
		this.abilities = abilities;
	}

	public long getxStartingPosition() {
		return xStartingPosition;
	}

	public void setxStartingPosition(int xStartingPosition) {
		this.xStartingPosition = xStartingPosition;
	}

	public long getyStartingPosition() {
		return yStartingPosition;
	}

	public void setyStartingPosition(int yStartingPosition) {
		this.yStartingPosition = yStartingPosition;
	}

	public long getxPos() {
		return xPos;
	}

	public void setxPos(long xPos) {
		this.xPos = xPos;
	}

	public long getyPos() {
		return yPos;
	}

	public void setyPos(long yPos) {
		this.yPos = yPos;
	}

	public String getStartingDirection() {
		return startingDirection;
	}

	public void setStartingDirection(String startingDirection) {
		this.startingDirection = startingDirection;
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public String getController() {
		return controller;
	}

	public void setController(String controller) {
		this.controller = controller;
	}

	public boolean getQuickFeet() {
		return quickFeet;
	}

	public void setQuickFeet(boolean quickFeet) {
		this.quickFeet = quickFeet;
	}

	public boolean isAtExit() {
		return isAtExit;
	}

	public void setAtExit(boolean isAtExit) {
		this.isAtExit = isAtExit;
	}

	@Override
	public String toString() {
		return "GameInfo [id=" + id + ", persona=" + persona + ", abilities=" + abilities
				+ ", xStartingPosition=" + xStartingPosition + ", yStartingPosition=" + yStartingPosition + ", xPos="
				+ xPos + ", yPos=" + yPos + ", startingDirection=" + startingDirection + ", baseUrl=" + baseUrl
				+ ", controller=" + controller + ", quickFeet=" + quickFeet + ", isAtExit=" + isAtExit + "]";
	}
	
	
}
