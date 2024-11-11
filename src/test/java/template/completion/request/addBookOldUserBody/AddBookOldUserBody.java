package template.completion.request.addBookOldUserBody;

import static test.BaseTest.cfg;

import java.util.Collections;
import model.request.addBookOldUser.IsbnPartialModel;
import model.request.addBookOldUser.RequestBookModel;


public class AddBookOldUserBody {


  public Object completionBodyAddBook(){
    RequestBookModel requestBookModel = new RequestBookModel();
    IsbnPartialModel isbnPartialModel = new IsbnPartialModel();
    isbnPartialModel.setIsbn(cfg.realIsbnValue());
    requestBookModel.setUserId(cfg.userId());
    requestBookModel.setCollectionOfIsbns(Collections.singletonList(isbnPartialModel));
    return requestBookModel;
  }


}
