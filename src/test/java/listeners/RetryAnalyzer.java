package listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import java.util.HashMap;
import java.util.Map;

public class RetryAnalyzer implements IRetryAnalyzer {

    private static final int MAX_RETRY = 2;
    private static final Map<String, Integer> retryMap = new HashMap<>();

    @Override
    public boolean retry(ITestResult result) {
        String testName = result.getName();
        int currentRetry = retryMap.getOrDefault(testName, 0);

        if (currentRetry < MAX_RETRY) {
            currentRetry++;
            retryMap.put(testName, currentRetry);
            System.out.println("Retrying test: " + testName
                    + " | Attempt: " + currentRetry + "/" + MAX_RETRY);
            return true;
        } else {
            retryMap.remove(testName);
            return false;
        }
    }
}

