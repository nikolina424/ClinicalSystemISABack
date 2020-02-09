package com.example.demo.controller;

import com.example.demo.common.TimeProvider;
import com.example.demo.controller.ExaminationController;
import com.example.demo.model.Examination;
import com.example.demo.model.Room;
import com.example.demo.model.User;
import com.example.demo.service.ExaminationService;
import com.example.demo.service.RequestService;
import com.example.demo.service.RoomService;
import com.example.demo.service.UserService;
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
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.lang.reflect.Executable;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.example.demo.model.UserRole.ADMINC;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ExaminationControllerIntegrationTest {

    @SpyBean
    private ExaminationService examinationService;

    @SpyBean
    private RoomService roomService;

    @SpyBean
    private RequestService requestService;

    @SpyBean
    private UserService userService;

    @SpyBean
    private TimeProvider timeProvider;

    @SpyBean
    private ExaminationController examinationController;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Before
    public void setup() {

    }

    @Test
    public void testScheduleExaminationAdmin() throws Exception {

        doReturn(User.builder().role(ADMINC).build()).when(examinationController).getLoggedUser();
        Room room = Room.builder()
                .id(12L)
                .number(100)
                .build();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        ObjectMapper mapper = new ObjectMapper();

        mvc.perform(post("/scheduleExamination")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(ExaminationViewSchedule.builder()
                        .dateTime(dateFormat.parse("2020-02-20"))
                        .description("Novi pregled")
                        .doctor(this.userService.findOneById(5L))
                        .duration(2.0)
                        .price(20.0)
                        .room(room).build())))
                .andExpect(status().isOk());

        Examination ex = this.examinationService.findOneById(3L);
        Room findRoom = this.roomService.findOneById(12L);

        assertEquals(findRoom.getNumber(), room.getNumber());
        assertEquals(findRoom.getId(), room.getId());
        assertTrue(findRoom.isReserved());
        assertEquals(findRoom.getClinic(), room.getClinic());
        assertEquals(findRoom.getExamination().getId().longValue(), 3L);

        assertEquals(ex.getId().longValue(), 3L);
        assertEquals(ex.getDescription(), "Novi pregled");
        assertEquals(ex.getDuration(), 2.0, 0);
        assertEquals(ex.getPrice(), 20.0, 0);
        assertEquals(ex.getDoctor().getId().longValue(), 5L);
    }
}
