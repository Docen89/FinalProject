package ru.demoqa.models.addBookOldUserBody;


import ru.demoqa.models.addBookOldUser.RequestBookModel;
import ru.demoqa.models.addBookOldUser.IsbnPartialModel;
import java.util.Collections;


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
