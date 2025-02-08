package automation.pipeline;

import automation.parser.SwaggerParser;
import automation.generator.LLMTestGenerator;
import automation.generator.TestCodeGenerator;
import automation.util.FileUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;




import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class TestAutomationPipeline {

    public static void main(String[] args) throws IOException {
        String seleniumClass = "src/main/resources/LoginLeafTaps.java";

        //1. convert Selenium code into String
    	String codeDetails = readSeleniumCode(seleniumClass);

        // 2. Generate test case outline using LLM
        LLMTestGenerator llmGen = new LLMTestGenerator();
        String llmRawOutput = llmGen.generateTestCases(codeDetails);

        // 3. Extract only the Java code from the LLM response
        TestCodeGenerator codeGen = new TestCodeGenerator();
        String finalTestCode = codeGen.extractJavaCode(llmRawOutput);

        // 4. Extract the class name from the generated Java code
        String className = extractClassName(finalTestCode);
        String outputFileName = (className != null && !className.isEmpty()) 
                                ? className + ".java" 
                                : "GeneratedPW.java";

        // 5. Write generated code to a Java file with a name matching the class name
        FileUtils.writeToFile(finalTestCode, "src/main/java/automation/tests/"+outputFileName);

        System.out.println("Generated test code written to " + outputFileName);
    }

    /**
     * Extracts the Java class name from the given code.
     * It searches for a pattern matching "public class <ClassName>".
     */
    private static String extractClassName(String javaCode) {
        Pattern pattern = Pattern.compile("public\\s+class\\s+(\\w+)");
        Matcher matcher = pattern.matcher(javaCode);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }
    
    private static String readSeleniumCode(String filePath) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filePath)));
    }
}
