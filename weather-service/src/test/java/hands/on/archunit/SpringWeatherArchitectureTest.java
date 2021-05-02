package hands.on.archunit;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.runner.RunWith;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;
import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;

@RunWith(ArchUnitRunner.class)
@AnalyzeClasses(packages = "hands.on.archunit")
public class SpringWeatherArchitectureTest {

    @ArchTest
    public static final ArchRule NO_CYCLES =
            slices().matching("hands.on.archunit.(*)..").should().beFreeOfCycles();

    @ArchTest
    public static final ArchRule SERVICE_CLASS_CONSTRAINTS =
            classes()
                    .that().resideInAPackage("..service..")
                    .should().haveSimpleNameEndingWith("Controller")
                    .andShould().beAnnotatedWith(RestController.class);

    @ArchTest
    public static final ArchRule REPO_CLASS_CONSTRAINTS =
            classes()
                    .that().resideInAPackage("..persistence..")
                    .should().haveSimpleNameEndingWith("Repository")
                    .andShould().beAnnotatedWith(Repository.class);

    @ArchTest
    public static final ArchRule LAYERED_ARCH =
            layeredArchitecture()
                    .layer("Application").definedBy("hands.on.archunit")
                    .layer("Service").definedBy("hands.on.archunit.service..")
                    .layer("Persistence").definedBy("hands.on.archunit.persistence..")
                    .layer("Integration").definedBy("hands.on.archunit.integration..")
                    .layer("Model").definedBy("hands.on.archunit.model..")

                    .whereLayer("Application").mayNotBeAccessedByAnyLayer()
                    .whereLayer("Integration").mayNotBeAccessedByAnyLayer()
                    .whereLayer("Service").mayNotBeAccessedByAnyLayer()
                    .whereLayer("Persistence").mayOnlyBeAccessedByLayers("Service")
                    .whereLayer("Model").mayOnlyBeAccessedByLayers("Service", "Persistence")

                    .because("Relaxed layered architecture for Spring Weather service");
}
