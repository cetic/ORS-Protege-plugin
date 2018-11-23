package org.protege.owl.codegeneration;


public enum SubstitutionVariable {
	PACKAGE("package"),
	FACTORY_CLASS_NAME("factoryClass"),
	FACTORY_EXTRA_IMPORT("factoryExtraImport"),
	FACTORY_PACKAGE("factoryPackage"),
    INTERFACE_LIST("superInterfaces"),
	INTERFACE_NAME("interfaceName"),
	IMPLEMENTATION_EXTRA_IMPORT("implementationExtraImport"),
	IMPLEMENTATION_NAME("implementationName"),
	POJO_NAME("pojoName"),
    SUPER_POJO_LIST("superPojos"),
    SUPER_POJO("superPojo"),
	RSPOJO_NAME("rsPojoName"),
    SUPER_RSPOJO_LIST("superRSPojos"),
    SUPER_RSPOJO("superRSPojo"),
	RDFPOJO_NAME("rdfPojoName"),
    SUPER_RDFPOJO_LIST("superRDFPojos"),
    SUPER_RDFPOJO("superRDFPojo"),
	MODELPOJO_NAME("modelPojoName"),
    SUPER_MODELPOJO_LIST("superModelPojos"),
    SUPER_MODELPOJO("superModelPojo"),
	MANAGERPOJO_NAME("managerPojoName"),
    SUPER_MANAGERPOJO_LIST("superManagerPojos"),
    SUPER_MANAGERPOJO("superManagerPojo"),
	CLASS_IRI("classIri"),
	NAMESPACE("namespace"),
    PROPERTY_IRI("propertyIri"),
	CAPITALIZED_CLASS("OwlClass"),
	UPPERCASE_CLASS("OWLClass"),
	VOCABULARY_CLASS("VocabClass"),
	PROPERTY("owlProperty"), 
	CAPITALIZED_PROPERTY("OwlProperty"),
	UPPERCASE_PROPERTY("OWLProperty"),
	VOCABULARY_PROPERTY("VocabProperty"),
	PROPERTY_RANGE("propertyRange"),
	PROPERTY_RANGE_MODEL("propertyRangeModel"),
	PROPERTY_RANGE_FOR_CLASS("propertyRangeForClass"),
	PROPERTY_RANGE_IMPLEMENTATION("propertyRangeImplementation"),
	JAVADOC("javadoc"),
	DATE("date"),
	USER("user");
	
	private String name;
	
	private SubstitutionVariable(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
