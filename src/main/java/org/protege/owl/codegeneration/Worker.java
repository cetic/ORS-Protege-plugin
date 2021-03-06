package org.protege.owl.codegeneration;

import java.io.File;
import java.util.Collection;
import java.util.Map;

import org.protege.owl.codegeneration.inference.CodeGenerationInference;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLEntity;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLOntology;


public interface Worker {
	
	OWLOntology getOwlOntology();
	
	void initialize();
	
	CodeGenerationInference getInference();
	
	Collection<OWLClass> getOwlClasses();
	
	Collection<OWLObjectProperty> getOwlObjectProperties();
	
	Collection<OWLDataProperty> getOwlDataProperties();
	
	Collection<OWLObjectProperty> getObjectPropertiesForClass(OWLClass owlClass);
	
	Collection<OWLDataProperty> getDataPropertiesForClass(OWLClass owlClass);
	
	File getInterfaceFile(OWLClass c);

	File getPOJOFile(OWLClass c);

	File getRSPOJOFile(OWLClass c);
	File getRDFPOJOFile(OWLClass c);
	File getMODELPOJOFile(OWLClass c);
	File getMANAGERPOJOFile(OWLClass c);
	
	File getImplementationFile(OWLClass c);
	
	File getVocabularyFile();

	File getSchemaFile();

	File getStorageMgtFile();

    File getSerializerMgtFile();

	File getNamespaceFile();

	File getAppconfigFile();
	
	File getFactoryFile();

	File getWrappedIndividualFile();
	File getWrappedIndividualPojoFile();
	File getWrappedIndividualRDFFile();
	File getRestWrappedIndividualFile();
	File getModelWrappedIndividualFile();
	File getEnumWrappedIndividualFile();
	
	void configureSubstitutions(CodeGenerationPhase phase,
			                    Map<SubstitutionVariable, String> substitutions,
			                    OWLClass owlClass,
			                    OWLEntity owlProperty);
	
	String getTemplate(CodeGenerationPhase phase, OWLClass owlClass, Object owlProperty);
}
