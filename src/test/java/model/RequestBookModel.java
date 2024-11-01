package model;
import java.util.List;
import lombok.Data;


@Data
public class RequestBookModel {
    private List<model.IsbnPartialModel> collectionOfIsbns;
    private String userId;
  }


