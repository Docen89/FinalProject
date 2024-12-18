package templates.request.addBookOldUserBody;


import java.util.Collections;
import models.request.addBookOldUser.IsbnPartialModel;
import models.request.addBookOldUser.RequestBookModel;


public class AddBookOldUserBody {


  public RequestBookModel BodyAddBook(String bookIsbn, String userID) {
    RequestBookModel requestBookModel = new RequestBookModel();
    IsbnPartialModel isbnPartialModel = new IsbnPartialModel();
    isbnPartialModel.setIsbn(bookIsbn);
    requestBookModel.setUserId(userID);
    requestBookModel.setCollectionOfIsbns(Collections.singletonList(isbnPartialModel));
    return requestBookModel;
  }

}