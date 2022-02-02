package helpers;

import config.Project;
import org.testng.annotations.DataProvider;

public class DataProviderForLogin {
    @DataProvider(name = "login",parallel = true)
    public static Object[][] createData() {
        return new Object[][] {
                {"hee1","dss1"},
                {"hee2","dss2"},
                {"hee3","dss3"},
                {"hee4","dss4"},
                {"hee5","dss5"},
                {"hee6","dss6"},
                {Project.config.username(),Project.config.password()},
        };
    }
}
