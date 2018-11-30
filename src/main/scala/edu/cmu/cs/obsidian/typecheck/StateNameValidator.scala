package edu.cmu.cs.obsidian.typecheck

import com.helger.jcodemodel.JPackage
import edu.cmu.cs.obsidian.codegen.{Client, Server}
import edu.cmu.cs.obsidian.parser._
import edu.cmu.cs.obsidian.util._

import scala.collection.Map
import scala.util.parsing.input.Position
import scala.collection.immutable.{HashSet, TreeMap, TreeSet}


/* Important Note: be sure to take into account the fact that AST nodes need a location.
 * To construct a new AST node in this file, explicitly set the location using [setLoc] */


// Checks to make sure all state names mentioned in type names are actually valid states.
object StateNameValidator extends IdentityAstTransformer {

    override def transformType(
            table: SymbolTable,
            lexicallyInsideOf: DeclarationTable,
            context: Context,
            t: ObsidianType,
            pos: Position): (ObsidianType, List[ErrorRecord]) = {

        t match {
            case np: NonPrimitiveType =>
                lexicallyInsideOf.lookupContract(np.contractName) match {
                    case Some(ct) =>
                        np match {
                            case StateType(_, stateNames, _) =>
                                var errors = List.empty[ErrorRecord]
                                for (stateName <- stateNames) {
                                    if (ct.state(stateName).isEmpty) {
                                        errors = ErrorRecord(StateUndefinedError(np.contractName, stateName), pos, currentContractSourcePath) +: errors
                                    }
                                }
                                if (errors.isEmpty) {
                                    (np, errors)
                                }
                                else {
                                    (BottomType(), errors)
                                }
                            case _ => (np, List.empty)
                        }
                    case None => (BottomType(), List(ErrorRecord(ContractUndefinedError(np.contractName), pos, currentContractSourcePath)))
                }
            case _ => (t, List.empty)
        }
    }



}