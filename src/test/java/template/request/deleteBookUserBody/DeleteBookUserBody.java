package java.template.request.deleteBookUserBody;


import model.request.deleteBookUser.DeleteBookBody;

public class DeleteBookUserBody {

  public Object completionDeleteBookUserBody(String oldUserId,String realIsbnBook){
    DeleteBookBody deleteBookBody = new DeleteBookBody();
    deleteBookBody.setUserId(oldUserId);
    deleteBookBody.setIsbn(realIsbnBook);
    return deleteBookBody;
  }

}
