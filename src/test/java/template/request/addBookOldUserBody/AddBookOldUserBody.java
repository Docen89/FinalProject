package template.request.addBookOldUserBody;


import java.util.Collections;
import model.request.addBookOldUser.IsbnPartialModel;
import model.request.addBookOldUser.RequestBookModel;


public class AddBookOldUserBody {


  public Object completionBodyAddBook(String bookIsbn, String userID) {
    RequestBookModel requestBookModel = new RequestBookModel();
    IsbnPartialModel isbnPartialModel = new IsbnPartialModel();
    isbnPartialModel.setIsbn(bookIsbn);
    requestBookModel.setUserId(userID);
    requestBookModel.setCollectionOfIsbns(Collections.singletonList(isbnPartialModel));
    return requestBookModel;

  }


}
