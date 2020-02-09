package com.example.demo.controller;

import com.example.demo.common.TimeProvider;
import com.example.demo.controller.ExaminationController;
import com.example.demo.model.Examination;
import com.example.demo.model.Room;
import com.example.demo.model.User;
import com.example.demo.service.ExaminationService;
import com.example.demo.service.RequestService;
import com.example.demo.service.RoomService;
import com.example.demo.view.ExaminationViewSchedule;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static com.example.demo.model.UserRole.ADMINC;
import static com.example.demo.model.UserRole.PATIENT;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ExaminationControllerTest {

	@SpyBean
	private ExaminationService examinationService;

	@SpyBean
	private RoomService roomService;

	@SpyBean
	private RequestService requestService;

	@SpyBean
	private TimeProvider timeProvider;

	@SpyBean
	private ExaminationController examinationController;

	@Autowired
	private MockMvc mvc;

	@Before
	public void setup() {

	}

	@Test
	public void testScheduleExaminationAdmin() throws Exception {

		Room room = Room.builder().id(100L).build();

		doReturn(User.builder().role(ADMINC).build()).when(examinationController).getLoggedUser();
		doReturn(Examination.builder().id(100L).build()).when(examinationService).save((ExaminationViewSchedule) any());
		doReturn(null).when(roomService).save(any());
		doNothing().when(roomService).setExToRoom(any(), any());

		ObjectMapper mapper = new ObjectMapper();

		mvc.perform(post("/scheduleExamination")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(ExaminationViewSchedule.builder().room(room).build())))
				.andExpect(status().isOk());

		verify(examinationService, times(1)).save((ExaminationViewSchedule) any());
		verify(roomService, times(1)).save(any());
	}

	@Test
	public void testScheduleExaminationNotAdmin() throws Exception {

		Room room = Room.builder().id(100L).build();

		doReturn(User.builder().role(PATIENT).build()).when(examinationController).getLoggedUser();
		doReturn(Examination.builder().id(100L).build()).when(examinationService).save((ExaminationViewSchedule) any());
		doReturn(null).when(roomService).save(any());
		doNothing().when(roomService).setExToRoom(any(), any());

		ObjectMapper mapper = new ObjectMapper();

		mvc.perform(post("/scheduleExamination")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(ExaminationViewSchedule.builder().room(room).build())))
				.andExpect(status().isBadRequest());
	}
}
