package config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources({"classpath:config.properties"})
public interface config extends Config {

  @Key("new.user.name.value")
  String newUserNameValue();

  @Key("new.password.value")
  String newPasswordValue();

  @Key("old.user.name.value")
  String oldUserNameValue();

  @Key("old.password.value")
  String oldPasswordValue();

  @Key("real.isbn.value")
  String realIsbnValue();

  @Key("not.real.isbn.value")
  String notRealIsbnValue();

  @Key("user.Id")
  String userId();

}
