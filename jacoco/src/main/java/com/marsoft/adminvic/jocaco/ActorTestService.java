package com.marsoft.adminvic.jocaco;

public class ActorTestService {

	public String getActor(String actorName) {
		StringBuilder result = new StringBuilder();
		if (actorName == null || actorName.trim().length() == 0) {
			result.append("Please provide a actor name!");
		} else {
			result.append("The actor is " + actorName);
		}
		return result.toString();
	}

}
