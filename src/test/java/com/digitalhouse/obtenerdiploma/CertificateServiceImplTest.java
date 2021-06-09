package com.digitalhouse.obtenerdiploma;

import com.digitalhouse.obtenerdiploma.dto.CertificateDTO;
import com.digitalhouse.obtenerdiploma.dto.StudentDTO;
import com.digitalhouse.obtenerdiploma.dto.SubjectDTO;
import com.digitalhouse.obtenerdiploma.service.CertificateService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class CertificateServiceImplTest {

    @Autowired
    private CertificateService certificateService;

    @Test
    public void test_function_analyze_notes() {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setName("testador");
        studentDTO.setSubjects(List.of(new SubjectDTO("Portugues", 7), new SubjectDTO("matematica", 8)));
        CertificateDTO certificateDTO = certificateService.analyzeNotes(studentDTO);

        assertNotNull(certificateDTO);
        assertNotNull(certificateDTO.getName());
        certificateDTO.getSubjects().forEach(subject -> {
            assertNotNull(subject.getSubject());
        });
        assertEquals(certificateService.analyzeNotes(studentDTO).getAverage(), 7.5);
    }

    @Test
    public void should_return_8i5_when_notes_equals_8_and_9() {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setName("testador");
        studentDTO.setSubjects(List.of(new SubjectDTO("Portugues", 8), new SubjectDTO("matematica", 9)));
        CertificateDTO certificateDTO = certificateService.analyzeNotes(studentDTO);

        assertNotNull(certificateDTO);
        assertNotNull(certificateDTO.getName());
        certificateDTO.getSubjects().forEach(subject -> {
            assertNotNull(subject.getSubject());
        });
        assertEquals(certificateService.analyzeNotes(studentDTO).getAverage(), 8.5);
    }

    @Test
    public void should_write_diploma_with_honor_when_user_average_greater_than_7() {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setName("testador");
        studentDTO.setSubjects(List.of(new SubjectDTO("Portugues", 10), new SubjectDTO("matematica", 9)));
        CertificateDTO certificateDTO = certificateService.analyzeNotes(studentDTO);
        assertEquals("¡Felicitaciones testador! Usted tiene el gran promedio de 9.5", certificateDTO.getMessage());
    }

    @Test
    public void should_write_diploma_without_honor_when_notes_less_than_9() {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setName("testador");
        studentDTO.setSubjects(List.of(new SubjectDTO("Portugues", 8), new SubjectDTO("matematica", 9)));
        CertificateDTO certificateDTO = certificateService.analyzeNotes(studentDTO);
        assertEquals("testador usted ha conseguido el promedio de 8.5", certificateDTO.getMessage());
    }
}

