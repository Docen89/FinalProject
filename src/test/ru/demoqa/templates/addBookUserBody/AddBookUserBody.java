package ru.demoqa.templates.addBookUserBody;


import ru.demoqa.models.addBookUser.RequestBookModel;
import ru.demoqa.models.addBookUser.IsbnPartialModel;
import java.util.Collections;


public class AddBookUserBody {


  public RequestBookModel BodyAddBook(String bookIsbn, String userID) {
    RequestBookModel requestBookModel = new RequestBookModel();
    IsbnPartialModel isbnPartialModel = new IsbnPartialModel();
    isbnPartialModel.setIsbn(bookIsbn);
    requestBookModel.setUserId(userID);
    requestBookModel.setCollectionOfIsbns(Collections.singletonList(isbnPartialModel));
    return requestBookModel;
  }

}
