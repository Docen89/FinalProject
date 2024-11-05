package Config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources({"classpath:config.properties"})
public interface config extends Config {

  @Key("new.user.name.value")
  String newUserNameValue();

  @Key("new.user.password")
  String newUserPassword();

  @Key("user.name.value")
  String userNameValue();

  @Key("password.value")
  String passwordValue();

  @Key("user.path")
  String userPath();

  @Key("login.path")
  String loginPath();

  @Key("real.isbn.value")
  String realIsbnValue();

  @Key("not.real.isbn.value")
  String notRealIsbnValue();

  @Key("userId")
  String userId();

  @Key("base.uri")
  String baseUri();

  @Key("book.store.path")
  String bookStorePath();

  @Key("all.book.path")
  String allBookPath();

  @Key("del.view.book.path")
  String delViewBookPath();

  @Key("book.path")
  String bookPath();


}
