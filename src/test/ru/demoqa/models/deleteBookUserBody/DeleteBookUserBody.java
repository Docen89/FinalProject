package ru.demoqa.models.deleteBookUserBody;


import ru.demoqa.models.deleteBookUser.DeleteBookBody;

public class DeleteBookUserBody {

  public DeleteBookBody DeleteBookUserBody(String oldUserId, String realIsbnBook) {
    DeleteBookBody deleteBookBody = new DeleteBookBody();
    deleteBookBody.setUserId(oldUserId);
    deleteBookBody.setIsbn(realIsbnBook);
    return deleteBookBody;
  }

}
