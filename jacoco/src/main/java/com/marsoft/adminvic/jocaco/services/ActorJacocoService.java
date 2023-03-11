package com.marsoft.adminvic.jocaco.services;

import java.util.List;

import com.marsoft.adminvic.persistence.entity.Actor;

public class ActorJacocoService {

	private List<Actor> actorsList;

	public ActorJacocoService(List<Actor> actorsList) {
		this.actorsList = actorsList;
	}

	public String getActorName(Actor actor) {
		return actor.getActorName();
	}

	public Actor getActorById(Long id) {
		for (Actor a : actorsList) {
			if (a.getId().equals(id)) {
				return a;
			}
		}
		return null;
	}

	public List<Actor> getActorsList() {
		return actorsList;
	}

	public void setActor(List<Actor> actorsList) {
		this.actorsList = actorsList;
	}

}
