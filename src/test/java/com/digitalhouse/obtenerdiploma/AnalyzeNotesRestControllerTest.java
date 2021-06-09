package com.digitalhouse.obtenerdiploma;

import com.digitalhouse.obtenerdiploma.dto.StudentDTO;
import com.digitalhouse.obtenerdiploma.dto.SubjectDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AnalyzeNotesRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void should_return_bad_request_when_send_student_without_name() throws Exception {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setName("");
        studentDTO.setSubjects(List.of(new SubjectDTO("Matematica", 0), new SubjectDTO("Historia", 5)));
        String payload = objectMapper.writeValueAsString(studentDTO);

        mockMvc
                .perform(
                        post("/analyzeNotes")
                                .content(payload)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                ).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void should_return_ok_when_send_valid_payload() throws Exception {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setName("luiz ferreira");
        studentDTO.setSubjects(List.of(new SubjectDTO("Matematica", 0), new SubjectDTO("Historia", 5)));
        String payload = objectMapper.writeValueAsString(studentDTO);

        mockMvc
                .perform(
                        post("/analyzeNotes")
                                .content(payload)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void should_return_ok_when_no_subjects() throws Exception {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setName("luiz ferreira");
        String payload = objectMapper.writeValueAsString(studentDTO);

        mockMvc
                .perform(
                        post("/analyzeNotes")
                                .content(payload)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                ).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}
