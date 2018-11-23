package org.protege.owl.codegeneration;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAnnotationProperty;
import org.semanticweb.owlapi.model.OWLDataFactory;

public class Constants {
    public static final String CODE_GENERATION_NS = "http://protege.org/code";
    
    
    public static final OWLAnnotationProperty JAVADOC;
    public static final OWLAnnotationProperty JAVANAME;
    /*
     * This allows one to ignore OWL properties in the generation of the output code.
     */
    public static final OWLAnnotationProperty IGNORE;
    
    static {
    	OWLDataFactory factory = OWLManager.getOWLDataFactory();
    
    	JAVADOC  = factory.getOWLAnnotationProperty(IRI.create(CODE_GENERATION_NS + "#javadoc"));
    	JAVANAME = factory.getOWLAnnotationProperty(IRI.create(CODE_GENERATION_NS + "#javaname"));
    	IGNORE   = factory.getOWLAnnotationProperty(IRI.create(CODE_GENERATION_NS + "#ignore"));
        
    	
    }
    
	public static final String UKNOWN_CODE_GENERATED_INTERFACE = "WrappedIndividual";
	public static final String ABSTRACT_CODE_GENERATOR_INDIVIDUAL_CLASS = "WrappedIndividualImpl";
	public static final String POJO_CODE_GENERATOR_INDIVIDUAL_CLASS = "WrappedIndividualPojo";
    public static final String RDF_CODE_GENERATOR_INDIVIDUAL_CLASS = "WrappedIndividualRDF";
	public static final String REST_CODE_GENERATOR_INDIVIDUAL_CLASS = "RestWrappedIndividual";
	public static final String MODEL_CODE_GENERATOR_INDIVIDUAL_CLASS = "ModelWrappedIndividual";
	public static final String MANAGER_CODE_GENERATOR_INDIVIDUAL_CLASS = "EnumWrappedIndividual";
	public static final String UNKNOWN_JAVA_DATA_TYPE = "Object";
	public static final String UNKNOWN_JAVA_DATA_TYPE_MODEL = "Value";
	public static final String UNKNOWN_JAVA_DATA_TYPE_FOR_DATA_PROPERTY = "String";

	public static final String VOCABULARY_CLASS_NAME = "Vocabulary";
	public static final String STORAGEMGT_CLASS_NAME = "StorageManager";
	public static final String FACTORY_CLASS_NAME = "MyFactory";

	public static final String NAMESPACE_FILE_NAME = "namespaceConfig.properties";
	public static final String APPCONFIG_FILE_NAME = "ApplicationConfig.java";
	public static final String JSONSCHEMA_FILE_NAME = "schema.json";


	private Constants() {}
}
