package com.cabolabs.openehr.archetypes_in_software

import com.cabolabs.openehr.adl.ArchetypeManager
import com.cabolabs.openehr.adl.ArchetypeWalkthrough
import groovy.xml.MarkupBuilder
import se.acode.openehr.parser.*
import org.openehr.am.archetype.*
import org.openehr.am.archetype.constraintmodel.ConstraintRef
import org.openehr.am.openehrprofile.datatypes.text.CCodePhrase
import org.openehr.rm.common.resource.TranslationDetails



class Example1 {


//   private static String PS = File.separator
//   private static String path = "."+ PS +"ckm"+ PS +"local"+ PS +"archetypes"
      

   static void main(String[] args)
   {

// NOTE: This example used to have a hard-coded ADL path.
// Now it expects the ADL file path as the first CLI argument.

def usage = {
  println "Usage: groovy com.cabolabs.openehr.archetypes_in_software.Example1 <path-to-archetype.adl>"
}

if (!args || args.length < 1) {
  usage()
  System.exit(1)
}

def adlPath = args[0]
def adlFile = new File(adlPath)
if (!adlFile.exists() || !adlFile.isFile()) {
  System.err.println "ERROR: ADL file not found (or not a file): ${adlFile.absolutePath}"
  usage()
  System.exit(2)
}

      //def f = new File(path + PS + 'entry/observation/openEHR-EHR-OBSERVATION.blood_pressure.v2.adl')
      ADLParser parser = null

      try {
         parser = new ADLParser(adlFile)
      } catch (IOException e) {
         println e.message
      }
      Archetype archetype = null
      try {
         archetype = parser.archetype()
      } catch (Exception e) {
         println e.message
      }

      if (archetype) {
         assert archetype.archetypeId.value == 'openEHR-EHR-OBSERVATION.blood_pressure.v2'
         assert archetype.definition.getClass().getSimpleName() == 'CComplexObject'
         archetype.physicalPaths().sort().each { path ->
            println path
         }
         def path = '/data[at0001]/events[at0006]/data[at0003]/items[at0004]/value'
         assert archetype.node(path).getClass().getSimpleName() == 'CDvQuantity'
      }
   }
}
