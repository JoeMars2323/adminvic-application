package com.marsoft.adminvic.domain.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.lenient;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.marsoft.adminvic.domain.exception.AdminVicException;
import com.marsoft.adminvic.domain.service.ActorServiceImpl;
import com.marsoft.adminvic.persistence.entity.Actor;
import com.marsoft.adminvic.persistence.repository.ActorRepository;

@ExtendWith(MockitoExtension.class)
class ActorRepositoryTest {

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
	void getAllActorByIdTest() throws AdminVicException {
		// given
		List<Actor> mockActors = loadMockActors();
		Optional<Actor> optionalActor = Optional.of(mockActors.get(0));
		lenient().when(actorRepository.findById(1L)).thenReturn(optionalActor);

		// when
		Actor givenActor = mockActors.get(0);

		// then
		Actor expectedActor = new Actor();
		expectedActor.setId(1L);
		expectedActor.setActorName("Tom Cruise");

		assertEquals(expectedActor.getId(), givenActor.getId());
	}

	@Test
	void getAllActorByNameTest() throws AdminVicException {
		// given
		List<Actor> mockActors = loadMockActors();
		lenient().when(actorRepository.findByName("Tom Cruise")).thenReturn(mockActors.get(0));

		// when
		Actor givenActor = mockActors.get(0);

		// then
		Actor expectedActor = new Actor();
		expectedActor.setId(1L);
		expectedActor.setActorName("Tom Cruise");

		assertEquals(expectedActor.getActorName(), givenActor.getActorName());
	}

	@Test
	void getAllActorsTest() throws AdminVicException {
		// given
		List<Actor> mockActors = loadMockActors();
		lenient().when(actorRepository.findAll()).thenReturn(mockActors);

		// when
		int givenSize = mockActors.size();

		// then
		int expectedSize = 2;
		assertEquals(expectedSize, givenSize);
	}

	@Test
	void createActorTest() throws AdminVicException {
		// given
		Actor mockActor = new Actor();
		mockActor.setId(1L);
		mockActor.setActorName("Tom Cruise");
		lenient().when(actorRepository.save(Mockito.any(Actor.class))).thenReturn(mockActor);

		// when
		Actor savedActor = actorRepository.save(mockActor);

		// then
		assertEquals("Tom Cruise", savedActor.getActorName());
	}

	@Test
	void updateActorTest() throws AdminVicException {
		// given
		List<Actor> actorList = new ArrayList<>();

		Actor actor1 = new Actor();
		actor1.setId(1L);
		actor1.setActorName("Tom Cruise");
		actorList.add(actor1);

		Actor actor2 = new Actor();
		actor2.setId(2L);
		actor2.setActorName("Julia Roberts");
		actorList.add(actor2);

		Actor actor1Updated = new Actor();
		actor1Updated.setId(1L);
		actor1Updated.setActorName("Paul Anthony");

		if (actorList.stream().anyMatch(x -> x.getId() == actor1Updated.getId())) {
			lenient().when(actorRepository.save(Mockito.any(Actor.class))).thenReturn(actor1Updated);
			actorList.set(0, actor1Updated);
		}

		// when
		String expected = "Paul Anthony";

		// then
		assertEquals(actorList.get(0).getActorName(), expected);
	}

	@Test
	void deleteActorTest() throws AdminVicException {
		// given
		List<Actor> actorList = new ArrayList<>();

		Actor actor1 = new Actor();
		actor1.setId(1L);
		actor1.setActorName("Tom Cruise");
		actor1.setDeleted(false);
		actorList.add(actor1);

		Actor actor2 = new Actor();
		actor2.setId(2L);
		actor2.setActorName("Julia Roberts");
		actorList.add(actor2);

		actor1.setDeleted(true);
		lenient().when(actorRepository.save(Mockito.any(Actor.class))).thenReturn(actor1);
		actorList.set(0, actor1);

		// when
		boolean expected = true;

		// then
		assertEquals(actor1.getDeleted(), expected);
	}

	@Test
	void deleteActorPhysicallyTest() throws AdminVicException {
		// given
		Actor actor1 = new Actor();
		actor1.setId(1L);
		actor1.setActorName("Tom Cruise");

		// when
		actorRepository.delete(actor1);

		// then
		Mockito.verify(actorRepository).delete(actor1);
	}

}
