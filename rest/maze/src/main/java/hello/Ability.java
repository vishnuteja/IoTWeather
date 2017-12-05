package hello;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Ability {
	
	@JsonProperty
	private boolean Water;
	@JsonProperty
	private boolean Wood;
	@JsonProperty
	private boolean Fire;
	@JsonProperty
	private boolean Grass;
	@JsonProperty("Quick Feet")
	private boolean QuickFeet;
	
	public boolean getWater() {
		return Water;
	}

	public void setWater(boolean water) {
		Water = water;
	}

	public boolean getWood() {
		return Wood;
	}

	public void setWood(boolean wood) {
		Wood = wood;
	}

	public boolean getFire() {
		return Fire;
	}

	public void setFire(boolean fire) {
		Fire = fire;
	}

	public boolean getGrass() {
		return Grass;
	}

	public void setGrass(boolean grass) {
		Grass = grass;
	}

	public boolean getQuickFeet() {
		return QuickFeet;
	}

	public void setQuickFeet(boolean quickFeet) {
		QuickFeet = quickFeet;
	}

	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		
		if(this.Water)
			sb.append(" Water ");
		if(this.Wood)
			sb.append(" Wood ");
		if(this.Fire)
			sb.append(" Fire ");
		if(this.Grass)
			sb.append(" Grass ");
		if(this.QuickFeet)
			sb.append(" QuickFeet ");
		
		return sb.toString();
	}
	
}
