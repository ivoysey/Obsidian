package edu.cmu.cs.obsidian.tests


import _root_.org.junit.Test
import edu.cmu.cs.obsidian._
import org.junit.Assert.assertTrue
import org.scalatestplus.junit.JUnitSuite

import scala.sys.process._

class CompilerTests extends JUnitSuite {
    def testContract(contractName: String, contractPath: String, args: Array[String]): Int = {
        var result = true
        val inputArgs: Array[String] = Array(s"--output-path", s"obs_output/", contractPath) ++ args
        result = Main.compileProgram(inputArgs)
        assertTrue(result)
        val gradleCmd = s"gradle compileJava -b obs_output/$contractName/build.gradle"
        val gradleResult = gradleCmd.!
        assertTrue(gradleResult == 0)
        val deleteCmd = s"rm -rf obs_output/$contractName"
        deleteCmd.!
    }

    def testContract(contractName: String): Int =
        testContract(contractName, s"resources/tests/compilerTests/$contractName.obs", Array())

    @Test def multipleConstructors(): Unit = {
        testContract("MultipleConstructors")
    }

    @Test def intContainer(): Unit = {
        testContract("IntContainer")
    }

    @Test def primitiveTypes(): Unit = {
        testContract("PrimitiveTypes")
    }

    @Test def simple(): Unit = {
        testContract("Simple")
    }

    @Test def simple3(): Unit = {
        testContract("Simple3")
    }

    @Test def simpleVerification(): Unit = {
        testContract("SimpleVerification")
    }

    @Test def constructorWithArgs(): Unit = {
        testContract("ConstructorWithArgs")
    }

    @Test def stateTransactions(): Unit = {
        testContract("StateTransactions")
    }

    @Test def negativeNumbers(): Unit = {
        testContract("NegativeNumber")
    }

    @Test def giftCertificate(): Unit = {
        testContract("GiftCertificate")
    }

    @Test def transactionInConstructor(): Unit = {
        testContract("TransactionInConstructor")
    }

    @Test def testFFI(): Unit = {
        testContract(contractName = "TestFFI")
    }

    @Test def multipleConstructorGroup(): Unit = {
        testContract(contractName = "MultipleConstructorGroup")
    }

    @Test def insuranceCaseStudy(): Unit = {
        testContract("Insurer", "resources/case_studies/Insurance/Insurer.obs", Array())
    }

    @Test def stubExceptions(): Unit = {
        testContract("StubExceptions")
    }

    @Test def basicGenerics(): Unit = {
        testContract("BasicGenerics")
    }

    @Test def interfaces(): Unit = {
        testContract("InterfaceTest")
    }

    @Test def genericInterfaceParamsBasic(): Unit = {
        testContract("GenericInterfaceParamsBasic")
    }

    @Test def genericInterfaceParams(): Unit = {
        testContract("GenericInterfaceParams")
    }

    @Test def interfaceUse(): Unit = {
        testContract("InterfaceUse")
    }

    @Test def genericsStateVariables(): Unit = {
        testContract("GenericsStateVariables")
    }
}
