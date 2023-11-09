package org.example.dto;

import lombok.Data;
import org.example.enums.Status;

@Data
public class Skill {

  private Long id;
  private String name;
  private Status status;
}
