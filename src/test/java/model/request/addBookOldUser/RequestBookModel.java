package model.request.addBookOldUser;

import java.util.List;
import lombok.Data;


@Data
public class RequestBookModel {

  private List<IsbnPartialModel> collectionOfIsbns;
  private String userId;
}


