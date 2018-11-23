package org.protege.owl.codegeneration;

import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLOntology;

import java.util.Collection;

/**
 * @author z.khan
 * 
 */
public interface WrappedIndividual extends Comparable<WrappedIndividual> {
 
	OWLOntology getOwlOntology();
	
	OWLNamedIndividual getOwlIndividual();
	
	void assertOwlType(OWLClassExpression type);
    
      public String getId();


      public void setId(String newId);


      public Collection<String> getType();

      public void setType(Collection<String> type);


}
