package com.marsoft.adminvic.jacoco.tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.marsoft.adminvic.jocaco.services.ActorJacocoService;
import com.marsoft.adminvic.persistence.entity.Actor;

@ExtendWith(MockitoExtension.class)
public class ActorJacocoTest {

	List<Actor> loadActors() {
		List<Actor> actorsList = new ArrayList<>();

		// create actor 1
		Actor actor1 = new Actor();
		actor1.setId(1L);
		actor1.setActorName("Tom Cruise");
		actorsList.add(actor1);

		// create actor 2
		Actor actor2 = new Actor();
		actor2.setId(2L);
		actor2.setActorName("Julia Roberts");
		actorsList.add(actor2);

		return actorsList;
	}

	@Test
	public void getActorNameTest() {
		ActorJacocoService actorJacocoService = new ActorJacocoService(loadActors());
		Actor actor = actorJacocoService.getActorsList().get(0);
		assertEquals("Tom Cruise", actorJacocoService.getActorName(actor));
	}

	@Test
	public void getActorById() {
		ActorJacocoService actorJacocoService = new ActorJacocoService(loadActors());
		Actor actor = actorJacocoService.getActorById(1l);
		assertEquals("Tom Cruise", actor.getActorName());
	}

}
