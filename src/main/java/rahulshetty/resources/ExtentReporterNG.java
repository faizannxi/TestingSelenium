package rahulshetty.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	static ExtentReports repo;
	public static ExtentReports getReportEntry() {
		String path = System.getProperty("user.dir") + "/reports/index.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setReportName("Web Automation Results");
        reporter.config().setDocumentTitle("Test Results");
        
        repo = new ExtentReports();
        repo.attachReporter(reporter);
        repo.setSystemInfo("Tester", "Faizan Ahmad");
		return repo;
	}

}
