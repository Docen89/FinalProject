package template.completion.request.deleteBookUserBody;

import static test.BaseTest.cfg;

import model.request.deleteBookUser.DeleteBookBody;

public class DeleteBookUserBody {

  public Object completionDeleteBookUserBody(){
    DeleteBookBody deleteBookBody = new DeleteBookBody();
    deleteBookBody.setUserId(cfg.userId());
    deleteBookBody.setIsbn(cfg.realIsbnValue());
    return deleteBookBody;
  }

}
