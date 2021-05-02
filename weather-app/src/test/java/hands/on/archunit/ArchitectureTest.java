package hands.on.archunit;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.library.Architectures;
import org.junit.runner.RunWith;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.library.Architectures.*;
import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;

@RunWith(ArchUnitRunner.class)
@AnalyzeClasses(packages = "hands.on.archunit")
public class ArchitectureTest {

    @ArchTest
    public static final ArchRule NO_CYCLES =
            slices().matching("hands.on.archunit.(*)..").should().beFreeOfCycles();

    @ArchTest
    public static final ArchRule PRIVATE_CONSTRUCTOR =
            classes()
                    .that().haveSimpleNameEndingWith("App")
                    .should().haveOnlyPrivateConstructors();

    @ArchTest
    public static final ArchRule CMD_CLASS_NAMES =
            classes()
                    .that().resideInAPackage("..cmd..")
                    .should().haveSimpleNameEndingWith("Command");

    @ArchTest
    public static final ArchRule REPO_CLASS_NAMES =
            classes()
                    .that().resideInAPackage("..persistence..")
                    .should().haveSimpleNameEndingWith("Repository");

    @ArchTest
    public static final ArchRule LAYERED_ARCH =
            layeredArchitecture()
                    .layer("Application").definedBy("hands.on.archunit")
                    .layer("Command").definedBy("hands.on.archunit.cmd..")
                    .layer("Persistence").definedBy("hands.on.archunit.persistence..")
                    .layer("Model").definedBy("hands.on.archunit.model..")

                    .whereLayer("Application").mayNotBeAccessedByAnyLayer()
                    .whereLayer("Command").mayOnlyBeAccessedByLayers("Application")
                    .whereLayer("Persistence").mayOnlyBeAccessedByLayers("Command")
                    .whereLayer("Model").mayOnlyBeAccessedByLayers("Command", "Persistence")

                    .because("Relaxed layered architecture for Weather application");
}
