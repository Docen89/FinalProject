package ru.demoqa.helpers;


import ru.demoqa.api.check.ActionsResponce;
import ru.demoqa.steps.api.StepsApi;

public class IsbnBook {

  private static IsbnBook instance;
  private String isbnBookValue;
  StepsApi stepsApi = new StepsApi();

  public IsbnBook() {
    ActionsResponce getIsbnBookValue = stepsApi.getListBook();
    isbnBookValue = getIsbnBookValue.getBodyFieldString("books[0].isbn");
  }

  public static synchronized IsbnBook getInstance() {
    if (instance == null) {
      instance = new IsbnBook();
    }
    return instance;
  }

  public String isbnBookValue() {
    return isbnBookValue;
  }
}

