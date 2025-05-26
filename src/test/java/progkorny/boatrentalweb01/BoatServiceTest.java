package progkorny.boatrentalweb01.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import progkorny.boatrentalweb01.model.Boat;
import progkorny.boatrentalweb01.repository.BoatRepository;

class BoatServiceTest {

	@InjectMocks
	private BoatService boatService;

	@Mock
	private BoatRepository boatRepository;

	private Boat boat;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		boat = new Boat();
		boat.setId(1L);
		boat.setName("Sea Breeze");
		boat.setBrand("Yamaha");
		boat.setLength(10.5);
		boat.setModel("X100");
		boat.setBuildYear(2021);
		boat.setDailyRate(100.0);
		boat.setAvailable(true);
		boat.setNumberOfSeats(5);
		boat.setVersion(1);
	}

	@Test
	void testGetAllBoats() {
		List<Boat> boats = List.of(boat);
		when(boatRepository.findAll()).thenReturn(boats);

		List<Boat> result = boatService.getAllBoats();

		assertEquals(1, result.size());
		verify(boatRepository).findAll();
	}

	@Test
	void testGetBoatById_Found() {
		when(boatRepository.findById(1L)).thenReturn(Optional.of(boat));

		Optional<Boat> result = boatService.getBoatById(1L);

		assertTrue(result.isPresent());
		assertEquals("Sea Breeze", result.get().getName());
	}

	@Test
	void testGetBoatById_NotFound() {
		when(boatRepository.findById(99L)).thenReturn(Optional.empty());

		Optional<Boat> result = boatService.getBoatById(99L);

		assertTrue(result.isEmpty());
	}

	@Test
	void testCreateBoat() {
		when(boatRepository.save(any(Boat.class))).thenReturn(boat);

		Boat result = boatService.createBoat(boat);

		assertNotNull(result);
		assertEquals("Sea Breeze", result.getName());
		verify(boatRepository).save(boat);
	}

	@Test
	void testUpdateBoat_Success() {
		Boat updatedBoat = new Boat();
		updatedBoat.setName("Updated Name");
		updatedBoat.setBrand("Updated Brand");
		updatedBoat.setLength(11.0);
		updatedBoat.setModel("Updated Model");
		updatedBoat.setBuildYear(2022);
		updatedBoat.setDailyRate(120.0);
		updatedBoat.setAvailable(false);
		updatedBoat.setNumberOfSeats(6);
		updatedBoat.setVersion(2);

		when(boatRepository.findById(1L)).thenReturn(Optional.of(boat));
		when(boatRepository.save(any(Boat.class))).thenAnswer(invocation -> invocation.getArgument(0));

		Boat result = boatService.updateBoat(1L, updatedBoat);

		assertEquals("Updated Name", result.getName());
		assertEquals("Updated Brand", result.getBrand());
		assertEquals(11.0, result.getLength());
		assertEquals("Updated Model", result.getModel());
		assertEquals(2022, result.getBuildYear());
		assertEquals(120.0, result.getDailyRate());
		assertFalse(result.isAvailable());
		assertEquals(6, result.getNumberOfSeats());
		assertEquals(2, result.getVersion());

		verify(boatRepository).findById(1L);
		verify(boatRepository).save(result);
	}

	@Test
	void testUpdateBoat_NotFound() {
		when(boatRepository.findById(99L)).thenReturn(Optional.empty());

		Boat updatedBoat = new Boat();

		RuntimeException ex = assertThrows(RuntimeException.class,
				() -> boatService.updateBoat(99L, updatedBoat));

		assertEquals("Boat not found", ex.getMessage());
	}

	@Test
	void testDeleteBoat_Exists() {
		when(boatRepository.existsById(1L)).thenReturn(true);
		doNothing().when(boatRepository).deleteById(1L);

		boolean result = boatService.deleteBoat(1L);

		assertTrue(result);
		verify(boatRepository).existsById(1L);
		verify(boatRepository).deleteById(1L);
	}

	@Test
	void testDeleteBoat_NotExists() {
		when(boatRepository.existsById(99L)).thenReturn(false);

		boolean result = boatService.deleteBoat(99L);

		assertFalse(result);
		verify(boatRepository).existsById(99L);
		verify(boatRepository, never()).deleteById(anyLong());
	}
}
