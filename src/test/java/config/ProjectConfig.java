package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "file:src/test/resources/local.properties"
})
public interface ProjectConfig extends Config {
    @Key("username")
    String username();

    @Key("password")
    String password();

    @Key("browser")
    String browser();

    @Key("browserWidth")
    Integer browserWidth();

    @Key("browserHeight")
    Integer browserHeight();

    @Key("pageLoadTimeOut")
    Integer pageLoadTimeOut();

    @Key("implicitlyWait")
    Integer implicitlyWait();

    @Key("baseUrl")
    String baseUrl();

    @Key("loginPagePath")
    String loginPagePath();
}
