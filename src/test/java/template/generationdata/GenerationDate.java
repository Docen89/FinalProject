package template.generationdata;

import com.github.javafaker.Faker;
import java.util.Locale;

public class GenerationDate {

  Faker faker = new Faker(new Locale("EN"));

  public  String newUserNameValue = faker.name().firstName();
  public  String newPasswordValue = faker.internet().password(6,10);

}
