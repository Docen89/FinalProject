package configs;

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

  @Key("load.strategy")
  String loadStrategy();

  @Key("base.uri")
  String baseUri();

  @Key("browser.size")
  String browserSize();

}
