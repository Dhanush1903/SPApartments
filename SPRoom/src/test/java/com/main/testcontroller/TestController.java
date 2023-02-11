package com.main.testcontroller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.jupiter.api.Test;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.main.DTO.RoomDTO;
import com.main.controller.RoomController;
import com.main.service.RoomService;

@WebMvcTest(value = RoomController.class)
public class TestController {
	@Autowired
	MockMvc mockMvc;

	@MockBean
	RoomService roomService;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void testsaveRoom() throws Exception {

		RoomDTO dto = new RoomDTO();
		dto.setNoofRooms(5);
		dto.setRent(52);
		dto.setRid(1);
		dto.setRoomType("delux");

		Mockito.when(roomService.saveRoom(dto)).thenReturn(dto);

		mockMvc.perform(
				post("/save").content(objectMapper.writeValueAsString(dto)).contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.rid").value(5))
				.andExpect(jsonPath("$.noofRooms", is(5))).andExpect(jsonPath("$.rent", is(52)))
				.andExpect(jsonPath("$.roomType", is("delux"))).andDo(print());

		Mockito.verify(roomService.saveRoom(dto));
	}

}
