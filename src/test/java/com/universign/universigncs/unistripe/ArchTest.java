package com.universign.universigncs.unistripe;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.universign.universigncs.unistripe");

        noClasses()
            .that()
            .resideInAnyPackage("com.universign.universigncs.unistripe.service..")
            .or()
            .resideInAnyPackage("com.universign.universigncs.unistripe.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.universign.universigncs.unistripe.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
