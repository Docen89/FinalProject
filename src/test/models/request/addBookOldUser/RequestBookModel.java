package models.request.addBookOldUser;
import java.util.List;
import lombok.Data;


@Data
public class RequestBookModel {
    private List<models.request.addBookOldUser.IsbnPartialModel> collectionOfIsbns;
    private String userId;
  }


