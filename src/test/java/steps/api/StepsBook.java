package steps.api;


import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static test.BaseTest.cfg;


import api.RestAssuredHaveBodyRequestDelete;
import api.RestAssuredHaveBodyRequestAuthPost;
import api.RestAssuredNoBodyRequestGet;
import io.qameta.allure.Step;
import template.completion.request.addBookOldUserBody.AddBookOldUserBody;
import template.completion.request.deleteBookUserBody.DeleteBookUserBody;


public class StepsBook {

  DeleteBookUserBody deleteBookUserBody = new DeleteBookUserBody();
  AddBookOldUserBody addBookOldUserBody = new AddBookOldUserBody();


  @Step("Добавляем книгу пользователю в профиль")
  public void addBookProfileUser() {
    new RestAssuredHaveBodyRequestAuthPost()
        .post("/BookStore/v1/Books",
            addBookOldUserBody.completionBodyAddBook(cfg.realIsbnValue(), cfg.userId()),
            cfg.oldUserNameValue(),
            cfg.oldPasswordValue())
        .responseStatusCode(201);

  }

  @Step("Проверяем сообщение в ответе на запрос повторного добавления книги в профиль")
  public void checkMessageResponseRepeatedAddBooKProfileUser() {
    new RestAssuredHaveBodyRequestAuthPost()
        .post("/BookStore/v1/Books",
            addBookOldUserBody.completionBodyAddBook(cfg.realIsbnValue(), cfg.userId()),
            cfg.oldUserNameValue(),
            cfg.oldPasswordValue())
        .responseStatusCode(400)
        .responseJson("message", containsString("ISBN already present in the User's Collection!"))
        .responseJson("code", containsString("1210"));

  }

  @Step("Удаляем книгу у пользователя из профиля")
  public void deleteBookProfileUser() {
    new RestAssuredHaveBodyRequestDelete()
        .delete("BookStore/v1/Book",
            cfg.oldUserNameValue(),
            cfg.oldPasswordValue(),
            deleteBookUserBody.completionDeleteBookUserBody())
        .responseStatusCode(204);

  }

  @Step("Запрашиваем существующую книгу")
  public void getRealBook() {
    new RestAssuredNoBodyRequestGet()
        .get("BookStore/v1/Book?ISBN=" + cfg.realIsbnValue())
        .responseStatusCode(200)
        .responseJson("isbn", equalTo("9781449337711"))
        .responseJson("title", equalTo("Designing Evolvable Web APIs with ASP.NET"));

  }

  @Step("Запрашиваем несуществующую книгу")
  public void getNoRealBook() {
    new RestAssuredNoBodyRequestGet()
        .get("BookStore/v1/Book?ISBN=" + cfg.notRealIsbnValue())
        .responseStatusCode(400)
        .responseJson("message", equalTo("ISBN supplied is not available in Books Collection!"))
        .responseJson("code", equalTo("1205"));

  }
}
