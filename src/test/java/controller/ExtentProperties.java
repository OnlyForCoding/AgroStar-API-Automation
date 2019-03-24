package controller;

import java.io.File;

public enum ExtentProperties {

    INSTANCE;

    private String reportPath;
    private String projectName;

    private ExtentProperties() {
        this.reportPath = "output" + File.separator + "Run_" + System.currentTimeMillis() + File.separator + "report.html";
        this.projectName = "default";
    }

    public String getReportPath() {
        return this.reportPath;
    }

    public void setReportPath(String reportPath) {
        this.reportPath = reportPath;
    }

    public String getProjectName() {
        return this.projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}
