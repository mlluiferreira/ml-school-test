package com.digitalhouse.obtenerdiploma.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class SubjectDTO {

  @Size(min = 8, max = 50, message = "name must be between 8 and 50")
  @Pattern(regexp="^[A-Za-z ]*$",message = "name must contains only letters")
  private String subject;

  @Min(value = 0, message = "note should not be less than 0")
  @Max(value = 10, message = "note should not be greater than 10")
  private Integer note;

  public SubjectDTO() {
  }

  public SubjectDTO(String subject, Integer note) {
    this.subject = subject;
    this.note = note;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public Integer getNote() {
    return note;
  }

  public void setNote(Integer note) {
    this.note = note;
  }

}
