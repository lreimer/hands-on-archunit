package hands.on.archunit;

import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.lang.ArchRule;

import org.junit.runner.RunWith;

import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;

@RunWith(ArchUnitRunner.class)
@AnalyzeClasses(packages = "hands.on.archunit")
public class ArchitectureTest {

    @ArchTest
    public static final ArchRule NO_CYCLES = 
                            slices().matching("hands.on.archunit.(*)..").should().beFreeOfCycles();
    
                            
}
