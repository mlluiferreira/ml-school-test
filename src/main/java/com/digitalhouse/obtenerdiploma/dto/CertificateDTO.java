package com.digitalhouse.obtenerdiploma.dto;

public class CertificateDTO extends StudentDTO {
  private String message;
  private Double average;

  public CertificateDTO() {
  }

  public CertificateDTO(StudentDTO student) {
    this.name = student.name;
    this.subjects = student.subjects;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Double getAverage() {
    return average;
  }

  public void setAverage(Double average) {
    this.average = average;
  }

  public void setStudent(StudentDTO student) {
    this.name = student.name;
    this.subjects = student.subjects;
  }
}
