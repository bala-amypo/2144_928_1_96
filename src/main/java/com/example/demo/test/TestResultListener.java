package com.example.demo.test;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestResultListener implements ITestListener {

    private void logResult(ITestResult result, String status) {
        String methodName = result.getMethod().getMethodName();
        System.out.println(methodName + " - " + status);
    }

    @Override
    public void onTestStart(ITestResult result) {
        // Optional: Log when a test starts
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        // Logs: <methodName> - PASS
        logResult(result, "PASS");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        // Logs: <methodName> - FAIL
        logResult(result, "FAIL");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        // Logs: <methodName> - SKIP
        logResult(result, "SKIP");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // Not required by spec, but needed for interface
    }

    @Override
    public void onStart(ITestContext context) {
        // Optional: Log context start
    }

    @Override
    public void onFinish(ITestContext context) {
        // Optional: Log context finish
    }
}