package com.marsoft.adminvic.jacoco.junit;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.marsoft.adminvic.domain.service.ActorServiceImpl;
import com.marsoft.adminvic.jocaco.ActorTestService;
import com.marsoft.adminvic.persistence.entity.Actor;
import com.marsoft.adminvic.persistence.repository.ActorRepository;

@ExtendWith(MockitoExtension.class)
public class ActorTest {

	@Mock
	ActorRepository actorRepository;

	@InjectMocks
	ActorServiceImpl actorService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	List<Actor> loadMockActors() {
		List<Actor> mockActors = new ArrayList<>();

		// create actor 1
		Actor actor1 = new Actor();
		actor1.setId(1L);
		actor1.setActorName("Tom Cruise");
		mockActors.add(actor1);

		// create actor 2
		Actor actor2 = new Actor();
		actor2.setId(2L);
		actor2.setActorName("Julia Roberts");
		mockActors.add(actor2);

		return mockActors;
	}

	@Test
	public void testActorName() {
		ActorTestService obj = new ActorTestService();
		assertEquals("The actor is Tom Cruise", obj.getActor(loadMockActors().get(0).getActorName()));

	}

}
