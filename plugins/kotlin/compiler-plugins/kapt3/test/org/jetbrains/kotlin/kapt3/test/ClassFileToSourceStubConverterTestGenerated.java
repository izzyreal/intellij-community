/*
 * Copyright 2010-2016 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jetbrains.kotlin.kapt3.test;

import com.intellij.testFramework.TestDataPath;
import org.jetbrains.kotlin.test.JUnit3RunnerWithInners;
import org.jetbrains.kotlin.test.KotlinTestUtils;
import org.jetbrains.kotlin.test.TargetBackend;
import org.jetbrains.kotlin.test.TestMetadata;
import org.junit.runner.RunWith;

import java.io.File;
import java.util.regex.Pattern;

/** This class is generated by {@link org.jetbrains.kotlin.generators.tests.TestsPackage}. DO NOT MODIFY MANUALLY */
@SuppressWarnings("all")
@TestMetadata("plugins/kapt3/testData/converter")
@TestDataPath("$PROJECT_ROOT")
@RunWith(JUnit3RunnerWithInners.class)
public class ClassFileToSourceStubConverterTestGenerated extends AbstractClassFileToSourceStubConverterTest {
    @TestMetadata("abstractMethods.kt")
    public void testAbstractMethods() throws Exception {
        String fileName = KotlinTestUtils.navigationMetadata("plugins/kapt3/testData/converter/abstractMethods.kt");
        doTest(fileName);
    }

    public void testAllFilesPresentInConverter() throws Exception {
        KotlinTestUtils.assertAllTestsPresentByMetadata(this.getClass(), new File("plugins/kapt3/testData/converter"), Pattern.compile("^(.+)\\.kt$"), TargetBackend.ANY, true);
    }

    @TestMetadata("annotations.kt")
    public void testAnnotations() throws Exception {
        String fileName = KotlinTestUtils.navigationMetadata("plugins/kapt3/testData/converter/annotations.kt");
        doTest(fileName);
    }

    @TestMetadata("annotations2.kt")
    public void testAnnotations2() throws Exception {
        String fileName = KotlinTestUtils.navigationMetadata("plugins/kapt3/testData/converter/annotations2.kt");
        doTest(fileName);
    }

    @TestMetadata("dataClass.kt")
    public void testDataClass() throws Exception {
        String fileName = KotlinTestUtils.navigationMetadata("plugins/kapt3/testData/converter/dataClass.kt");
        doTest(fileName);
    }

    @TestMetadata("defaultImpls.kt")
    public void testDefaultImpls() throws Exception {
        String fileName = KotlinTestUtils.navigationMetadata("plugins/kapt3/testData/converter/defaultImpls.kt");
        doTest(fileName);
    }

    @TestMetadata("enums.kt")
    public void testEnums() throws Exception {
        String fileName = KotlinTestUtils.navigationMetadata("plugins/kapt3/testData/converter/enums.kt");
        doTest(fileName);
    }

    @TestMetadata("fileFacadeJvmName.kt")
    public void testFileFacadeJvmName() throws Exception {
        String fileName = KotlinTestUtils.navigationMetadata("plugins/kapt3/testData/converter/fileFacadeJvmName.kt");
        doTest(fileName);
    }

    @TestMetadata("functions.kt")
    public void testFunctions() throws Exception {
        String fileName = KotlinTestUtils.navigationMetadata("plugins/kapt3/testData/converter/functions.kt");
        doTest(fileName);
    }

    @TestMetadata("genericRawSignatures.kt")
    public void testGenericRawSignatures() throws Exception {
        String fileName = KotlinTestUtils.navigationMetadata("plugins/kapt3/testData/converter/genericRawSignatures.kt");
        doTest(fileName);
    }

    @TestMetadata("genericSimple.kt")
    public void testGenericSimple() throws Exception {
        String fileName = KotlinTestUtils.navigationMetadata("plugins/kapt3/testData/converter/genericSimple.kt");
        doTest(fileName);
    }

    @TestMetadata("inheritanceSimple.kt")
    public void testInheritanceSimple() throws Exception {
        String fileName = KotlinTestUtils.navigationMetadata("plugins/kapt3/testData/converter/inheritanceSimple.kt");
        doTest(fileName);
    }

    @TestMetadata("jvmOverloads.kt")
    public void testJvmOverloads() throws Exception {
        String fileName = KotlinTestUtils.navigationMetadata("plugins/kapt3/testData/converter/jvmOverloads.kt");
        doTest(fileName);
    }

    @TestMetadata("jvmStatic.kt")
    public void testJvmStatic() throws Exception {
        String fileName = KotlinTestUtils.navigationMetadata("plugins/kapt3/testData/converter/jvmStatic.kt");
        doTest(fileName);
    }

    @TestMetadata("jvmStaticFieldInParent.kt")
    public void testJvmStaticFieldInParent() throws Exception {
        String fileName = KotlinTestUtils.navigationMetadata("plugins/kapt3/testData/converter/jvmStaticFieldInParent.kt");
        doTest(fileName);
    }

    @TestMetadata("kt14996.kt")
    public void testKt14996() throws Exception {
        String fileName = KotlinTestUtils.navigationMetadata("plugins/kapt3/testData/converter/kt14996.kt");
        doTest(fileName);
    }

    @TestMetadata("kt14997.kt")
    public void testKt14997() throws Exception {
        String fileName = KotlinTestUtils.navigationMetadata("plugins/kapt3/testData/converter/kt14997.kt");
        doTest(fileName);
    }

    @TestMetadata("kt14998.kt")
    public void testKt14998() throws Exception {
        String fileName = KotlinTestUtils.navigationMetadata("plugins/kapt3/testData/converter/kt14998.kt");
        doTest(fileName);
    }

    @TestMetadata("methodPropertySignatureClash.kt")
    public void testMethodPropertySignatureClash() throws Exception {
        String fileName = KotlinTestUtils.navigationMetadata("plugins/kapt3/testData/converter/methodPropertySignatureClash.kt");
        doTest(fileName);
    }

    @TestMetadata("modifiers.kt")
    public void testModifiers() throws Exception {
        String fileName = KotlinTestUtils.navigationMetadata("plugins/kapt3/testData/converter/modifiers.kt");
        doTest(fileName);
    }

    @TestMetadata("multifileClass.kt")
    public void testMultifileClass() throws Exception {
        String fileName = KotlinTestUtils.navigationMetadata("plugins/kapt3/testData/converter/multifileClass.kt");
        doTest(fileName);
    }

    @TestMetadata("nestedClasses.kt")
    public void testNestedClasses() throws Exception {
        String fileName = KotlinTestUtils.navigationMetadata("plugins/kapt3/testData/converter/nestedClasses.kt");
        doTest(fileName);
    }

    @TestMetadata("nonExistentClass.kt")
    public void testNonExistentClass() throws Exception {
        String fileName = KotlinTestUtils.navigationMetadata("plugins/kapt3/testData/converter/nonExistentClass.kt");
        doTest(fileName);
    }

    @TestMetadata("primitiveTypes.kt")
    public void testPrimitiveTypes() throws Exception {
        String fileName = KotlinTestUtils.navigationMetadata("plugins/kapt3/testData/converter/primitiveTypes.kt");
        doTest(fileName);
    }

    @TestMetadata("severalPackageParts.kt")
    public void testSeveralPackageParts() throws Exception {
        String fileName = KotlinTestUtils.navigationMetadata("plugins/kapt3/testData/converter/severalPackageParts.kt");
        doTest(fileName);
    }

    @TestMetadata("strangeNames.kt")
    public void testStrangeNames() throws Exception {
        String fileName = KotlinTestUtils.navigationMetadata("plugins/kapt3/testData/converter/strangeNames.kt");
        doTest(fileName);
    }

    @TestMetadata("topLevel.kt")
    public void testTopLevel() throws Exception {
        String fileName = KotlinTestUtils.navigationMetadata("plugins/kapt3/testData/converter/topLevel.kt");
        doTest(fileName);
    }
}
