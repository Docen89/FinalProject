package ru.demoqa.generationdata;

import com.github.javafaker.Faker;
import java.util.Locale;

public class GenerationDate {

  Faker faker = new Faker(new Locale("EN"));

  public String newUserNameValue() {
    String newUserNameValue;
    newUserNameValue = faker.name().firstName();
    return newUserNameValue;
  }

  public String newPasswordValue() {
    String newPasswordValue;
    newPasswordValue = faker.internet().password(6, 10);
    return newPasswordValue;
  }

}
