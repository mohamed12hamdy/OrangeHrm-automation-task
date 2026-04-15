package data;

import org.testng.annotations.DataProvider;

public class HomeDataProvider {

    @DataProvider(name = "widgetsData")
    public Object[][] widgetsData() {
        return new Object[][] {
                { HomePageData.TIME_AT_WORK          },
                { HomePageData.MY_ACTIONS            },
                { HomePageData.QUICK_LAUNCH          },
                { HomePageData.BUZZ_LATEST_POSTS     },
                { HomePageData.EMPLOYEES_ON_LEAVE    },
                { HomePageData.EMPLOYEES_BY_SUB_UNIT },
                { HomePageData.EMPLOYEES_BY_LOCATION },
        };
    }
}
