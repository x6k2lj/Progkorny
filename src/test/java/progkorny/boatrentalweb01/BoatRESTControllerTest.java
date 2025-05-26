package progkorny.boatrentalweb01.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import progkorny.boatrentalweb01.model.Boat;
import progkorny.boatrentalweb01.service.BoatService;

import java.util.Optional;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(BoatRESTController.class)
public class BoatRESTControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private BoatService boatService;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void getAllBoats_returnsList() throws Exception {
		Boat boat = new Boat();
		boat.setId(1L);
		boat.setName("Teszt Hajó");

		when(boatService.getAllBoats()).thenReturn(List.of(boat));

		mockMvc.perform(get("/api/boats"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].name").value("Teszt Hajó"));
	}

	@Test
	void getBoatById_returnsBoat() throws Exception {
		Boat boat = new Boat();
		boat.setId(1L);
		boat.setName("Teszt Hajó");

		when(boatService.getBoatById(1L)).thenReturn(Optional.of(boat));

		mockMvc.perform(get("/api/boats/1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value("Teszt Hajó"));
	}

	@Test
	void getBoatById_notFound() throws Exception {
		when(boatService.getBoatById(1L)).thenReturn(Optional.empty());

		mockMvc.perform(get("/api/boats/1"))
				.andExpect(status().isNotFound());
	}

	@Test
	void createBoat_returnsCreatedBoat() throws Exception {
		Boat boat = new Boat();
		boat.setName("Új Hajó");

		Boat savedBoat = new Boat();
		savedBoat.setId(1L);
		savedBoat.setName("Új Hajó");

		when(boatService.createBoat(any(Boat.class))).thenReturn(savedBoat);

		mockMvc.perform(post("/api/boats")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(boat)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(1L))
				.andExpect(jsonPath("$.name").value("Új Hajó"));
	}

	@Test
	void updateBoat_successful() throws Exception {
		Boat updatedBoat = new Boat();
		updatedBoat.setId(1L);
		updatedBoat.setName("Frissített Hajó");

		when(boatService.updateBoat(eq(1L), any(Boat.class))).thenReturn(updatedBoat);

		mockMvc.perform(put("/api/boats/1")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(updatedBoat)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value("Frissített Hajó"));
	}

	@Test
	void updateBoat_conflict() throws Exception {
		Boat boat = new Boat();
		boat.setId(1L);
		boat.setName("Konfliktusos Hajó");

		when(boatService.updateBoat(eq(1L), any(Boat.class)))
				.thenThrow(new org.springframework.dao.OptimisticLockingFailureException("Optimistic locking error"));

		mockMvc.perform(put("/api/boats/1")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(boat)))
				.andExpect(status().isConflict())
				.andExpect(content().string("Hiba: A hajót időközben más is módosította."));
	}

	@Test
	void updateBoat_notFound() throws Exception {
		Boat boat = new Boat();
		boat.setName("Nem létező Hajó");

		when(boatService.updateBoat(eq(1L), any(Boat.class)))
				.thenThrow(new RuntimeException("Boat not found"));

		mockMvc.perform(put("/api/boats/1")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(boat)))
				.andExpect(status().isNotFound());
	}

	@Test
	void deleteBoat_successful() throws Exception {
		when(boatService.deleteBoat(1L)).thenReturn(true);

		mockMvc.perform(delete("/api/boats/1"))
				.andExpect(status().isNoContent());
	}

	@Test
	void deleteBoat_notFound() throws Exception {
		when(boatService.deleteBoat(1L)).thenReturn(false);

		mockMvc.perform(delete("/api/boats/1"))
				.andExpect(status().isNotFound());
	}
}
