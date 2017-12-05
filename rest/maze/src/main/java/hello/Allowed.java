package hello;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
class Allowed
{
	@JsonProperty
	private boolean isAllowed;

	public boolean getAllowed() {
		return isAllowed;
	}

	public void setAllowed(boolean isAllowed) {
		this.isAllowed = isAllowed;
	}

	@Override
	public String toString() {
		return "Allowed [isAllowed=" + isAllowed + "]";
	}
	
}
