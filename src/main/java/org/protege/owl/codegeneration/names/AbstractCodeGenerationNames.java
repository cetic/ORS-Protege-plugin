package org.protege.owl.codegeneration.names;

import org.protege.owl.codegeneration.CodeGenerationOptions;
import org.semanticweb.owlapi.model.OWLClass;


public abstract class AbstractCodeGenerationNames implements CodeGenerationNames {
	
	
	public AbstractCodeGenerationNames(CodeGenerationOptions options) {
	}
    
    public String getImplementationName(OWLClass owlClass) {
        return "Default" + getInterfaceName(owlClass);
    }

    public String getPOJOName(OWLClass owlClass) {
        return getInterfaceName(owlClass)+"Pojo";
    }

    public String getRSPOJOName(OWLClass owlClass) {
        return "Rest" + getInterfaceName(owlClass);
    }

    public String getRDFPOJOName(OWLClass owlClass) {
        return getInterfaceName(owlClass)+"RDF";
    }

    public String getMODELPOJOName(OWLClass owlClass) {
        return "Model" + getInterfaceName(owlClass);
    }

    public String getMANAGERPOJOName(OWLClass owlClass) {
        return "Enum" + getInterfaceName(owlClass);
    }
}
