package Model;
import java.util.List;
import lombok.Data;


@Data
public class RequestBookModel {
    private List<Model.IsbnPartialModel> collectionOfIsbns;
    private String userId;
  }


