package ru.demoqa.configs;


import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources({"classpath:config.properties"})
public interface config extends Config {

  @Key("dell.user.name.value")
  String killUserNameValue();

  @Key("dell.password.value")
  String killPasswordValue();

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

  @Key("gui.new.user.name.value")
  String guiNewUserNameValue();

  @Key("gui.new.password.value")
  String guiNewPasswordValue();


}
