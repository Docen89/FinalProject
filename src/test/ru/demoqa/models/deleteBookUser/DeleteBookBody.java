package ru.demoqa.models.deleteBookUser;

import lombok.Data;
import lombok.Setter;

@Data
@Setter

public class DeleteBookBody {

  private String isbn;
  private String userId;

}