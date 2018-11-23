package org.protege.owl.codegeneration;

//INTERFACE & IMPLEMENTATION
import static org.protege.owl.codegeneration.CodeGenerationPhase.CREATE_DATA_PROPERTY_IMPLEMENTATION;
import static org.protege.owl.codegeneration.CodeGenerationPhase.CREATE_FUNCTIONAL_DATA_PROPERTY_IMPLEMENTATION;
import static org.protege.owl.codegeneration.CodeGenerationPhase.CREATE_DATA_PROPERTY_INTERFACE;
import static org.protege.owl.codegeneration.CodeGenerationPhase.CREATE_FUNCTIONAL_DATA_PROPERTY_INTERFACE;
import static org.protege.owl.codegeneration.CodeGenerationPhase.CREATE_IMPLEMENTATION_HEADER;
import static org.protege.owl.codegeneration.CodeGenerationPhase.CREATE_IMPLEMENTATION_TAIL;
import static org.protege.owl.codegeneration.CodeGenerationPhase.CREATE_INTERFACE_HEADER;
import static org.protege.owl.codegeneration.CodeGenerationPhase.CREATE_INTERFACE_TAIL;
import static org.protege.owl.codegeneration.CodeGenerationPhase.CREATE_INTERFACE_ENUMCLASS;
import static org.protege.owl.codegeneration.CodeGenerationPhase.CREATE_INTERFACE_ENUMTAIL;
import static org.protege.owl.codegeneration.CodeGenerationPhase.CREATE_OBJECT_PROPERTY_IMPLEMENTATION;
import static org.protege.owl.codegeneration.CodeGenerationPhase.CREATE_FUNCTIONAL_OBJECT_PROPERTY_IMPLEMENTATION;
import static org.protege.owl.codegeneration.CodeGenerationPhase.CREATE_OBJECT_PROPERTY_INTERFACE;
import static org.protege.owl.codegeneration.CodeGenerationPhase.CREATE_FUNCTIONAL_OBJECT_PROPERTY_INTERFACE;

//FACTORY
import static org.protege.owl.codegeneration.CodeGenerationPhase.CREATE_FACTORY_CLASS;
import static org.protege.owl.codegeneration.CodeGenerationPhase.CREATE_FACTORY_HEADER;
import static org.protege.owl.codegeneration.CodeGenerationPhase.CREATE_FACTORY_TAIL;

//VOCABULARY
import static org.protege.owl.codegeneration.CodeGenerationPhase.CREATE_VOCABULARY_HEADER;
import static org.protege.owl.codegeneration.CodeGenerationPhase.CREATE_OBJECT_PROPERTY_VOCABULARY;
import static org.protege.owl.codegeneration.CodeGenerationPhase.CREATE_DATA_PROPERTY_VOCABULARY;
import static org.protege.owl.codegeneration.CodeGenerationPhase.CREATE_CLASS_VOCABULARY;
import static org.protege.owl.codegeneration.CodeGenerationPhase.CREATE_VOCABULARY_TAIL;

//SCHEMA
import static org.protege.owl.codegeneration.CodeGenerationPhase.CREATE_SCHEMA_FILE;
import static org.protege.owl.codegeneration.CodeGenerationPhase.CREATE_SCHEMA_HEADER;
import static org.protege.owl.codegeneration.CodeGenerationPhase.CREATE_SCHEMA_CLASS;
import static org.protege.owl.codegeneration.CodeGenerationPhase.CREATE_SCHEMA_OBJECT_PROPERTY;
import static org.protege.owl.codegeneration.CodeGenerationPhase.CREATE_SCHEMA_FUNCTIONAL_OBJECT_PROPERTY;
import static org.protege.owl.codegeneration.CodeGenerationPhase.CREATE_SCHEMA_DATA_PROPERTY;
import static org.protege.owl.codegeneration.CodeGenerationPhase.CREATE_SCHEMA_FUNCTIONAL_DATA_PROPERTY;
import static org.protege.owl.codegeneration.CodeGenerationPhase.CREATE_SCHEMA_TAIL;
import static org.protege.owl.codegeneration.CodeGenerationPhase.CREATE_SCHEMA_EOF;

//POJO
import static org.protege.owl.codegeneration.CodeGenerationPhase.CREATE_POJO_HEADER;
import static org.protege.owl.codegeneration.CodeGenerationPhase.CREATE_POJO_TAIL;
import static org.protege.owl.codegeneration.CodeGenerationPhase.CREATE_DATA_PROPERTY_POJO;
import static org.protege.owl.codegeneration.CodeGenerationPhase.CREATE_OBJECT_PROPERTY_POJO;
import static org.protege.owl.codegeneration.CodeGenerationPhase.CREATE_FUNCTIONAL_OBJECT_PROPERTY_POJO;
import static org.protege.owl.codegeneration.CodeGenerationPhase.CREATE_FUNCTIONAL_DATA_PROPERTY_POJO;

//RSPOJO
import static org.protege.owl.codegeneration.CodeGenerationPhase.CREATE_RSPOJO_HEADER;
import static org.protege.owl.codegeneration.CodeGenerationPhase.CREATE_RSPOJO_TAIL;

//RDFPOJO
import static org.protege.owl.codegeneration.CodeGenerationPhase.CREATE_RDFPOJO_HEADER;
import static org.protege.owl.codegeneration.CodeGenerationPhase.CREATE_RDFPOJO_TAIL;
import static org.protege.owl.codegeneration.CodeGenerationPhase.CREATE_DATA_PROPERTY_RDFPOJO;
import static org.protege.owl.codegeneration.CodeGenerationPhase.CREATE_OBJECT_PROPERTY_RDFPOJO;
import static org.protege.owl.codegeneration.CodeGenerationPhase.CREATE_FUNCTIONAL_OBJECT_PROPERTY_RDFPOJO;
import static org.protege.owl.codegeneration.CodeGenerationPhase.CREATE_FUNCTIONAL_DATA_PROPERTY_RDFPOJO;

//MODELPOJO
import static org.protege.owl.codegeneration.CodeGenerationPhase.CREATE_MODELPOJO_HEADER;
import static org.protege.owl.codegeneration.CodeGenerationPhase.CREATE_MODELPOJO_TAIL;
import static org.protege.owl.codegeneration.CodeGenerationPhase.CREATE_DATA_PROPERTY_MODELPOJO;
import static org.protege.owl.codegeneration.CodeGenerationPhase.CREATE_INTDATA_PROPERTY_MODELPOJO;
import static org.protege.owl.codegeneration.CodeGenerationPhase.CREATE_TYPEDDATA_PROPERTY_MODELPOJO;
import static org.protege.owl.codegeneration.CodeGenerationPhase.CREATE_STRINGDATA_PROPERTY_MODELPOJO;
import static org.protege.owl.codegeneration.CodeGenerationPhase.CREATE_OBJECT_PROPERTY_MODELPOJO;
import static org.protege.owl.codegeneration.CodeGenerationPhase.CREATE_FUNCTIONAL_OBJECT_PROPERTY_MODELPOJO;
import static org.protege.owl.codegeneration.CodeGenerationPhase.CREATE_FUNCTIONAL_DATA_PROPERTY_MODELPOJO;

//MANAGERPOJO
import static org.protege.owl.codegeneration.CodeGenerationPhase.CREATE_MANAGERPOJO_HEADER;
import static org.protege.owl.codegeneration.CodeGenerationPhase.CREATE_MANAGERPOJO_ENUM;
import static org.protege.owl.codegeneration.CodeGenerationPhase.CREATE_MANAGERPOJO_TAIL;

//namespace
import static org.protege.owl.codegeneration.CodeGenerationPhase.CREATE_NAMESPACE_HEADER;
import static org.protege.owl.codegeneration.CodeGenerationPhase.CREATE_NAMESPACE_TAIL;
import static org.protege.owl.codegeneration.CodeGenerationPhase.CREATE_CLASS_NAMESPACE;

//applicationconfig
import static org.protege.owl.codegeneration.CodeGenerationPhase.CREATE_APPCONFIG_HEADER;
import static org.protege.owl.codegeneration.CodeGenerationPhase.CREATE_APPCONFIG_TAIL;
import static org.protege.owl.codegeneration.CodeGenerationPhase.CREATE_CLASS_APPCONFIG;

//STORAGE MANAGER
import static org.protege.owl.codegeneration.CodeGenerationPhase.CREATE_STORAGEMGT_HEADER;
//import static org.protege.owl.codegeneration.CodeGenerationPhase.CREATE_OBJECT_PROPERTY_STORAGEMGT;
//import static org.protege.owl.codegeneration.CodeGenerationPhase.CREATE_DATA_PROPERTY_STORAGEMGT;
import static org.protege.owl.codegeneration.CodeGenerationPhase.CREATE_CLASS_STORAGEMGT;
import static org.protege.owl.codegeneration.CodeGenerationPhase.CREATE_ENUMCLASS_STORAGEMGT;
import static org.protege.owl.codegeneration.CodeGenerationPhase.CREATE_STORAGEMGT_ENUMTAIL;
import static org.protege.owl.codegeneration.CodeGenerationPhase.CREATE_STORAGEMGT_TAIL;

//SERIALIZER MANAGER
import static org.protege.owl.codegeneration.CodeGenerationPhase.CREATE_SERIALIZERMGT_HEADER;
//import static org.protege.owl.codegeneration.CodeGenerationPhase.CREATE_OBJECT_PROPERTY_STORAGEMGT;
//import static org.protege.owl.codegeneration.CodeGenerationPhase.CREATE_DATA_PROPERTY_STORAGEMGT;
import static org.protege.owl.codegeneration.CodeGenerationPhase.CREATE_CLASS_SERIALIZERMGT;
import static org.protege.owl.codegeneration.CodeGenerationPhase.CREATE_SERIALIZERMGT_TAIL;

//WRAPPEDINDIVIDUAL
import static org.protege.owl.codegeneration.CodeGenerationPhase.CREATE_WRAPPEDINDIVIDUAL;
import static org.protege.owl.codegeneration.CodeGenerationPhase.CREATE_WRAPPEDINDIVIDUALPOJO;
import static org.protege.owl.codegeneration.CodeGenerationPhase.CREATE_WRAPPEDINDIVIDUALRDF;
import static org.protege.owl.codegeneration.CodeGenerationPhase.CREATE_RESTWRAPPEDINDIVIDUAL;
import static org.protege.owl.codegeneration.CodeGenerationPhase.CREATE_MODELWRAPPEDINDIVIDUAL;
import static org.protege.owl.codegeneration.CodeGenerationPhase.CREATE_ENUMWRAPPEDINDIVIDUAL;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.EnumMap;
import java.util.Map;
import java.util.Map.Entry;

import org.protege.owl.codegeneration.inference.CodeGenerationInference;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLEntity;
import org.semanticweb.owlapi.model.EntityType;
import org.semanticweb.owlapi.model.OWLDatatype;
import org.semanticweb.owlapi.model.OWLObjectProperty;

/**
 * A class that can create Java interfaces in the Protege-OWL format
 * 
 * @author z.khan
 * 
 */
public class JavaCodeGenerator {
    private Worker worker;
    private CodeGenerationInference inference;
    private OWLOntology ontology;

    /**
     * Constructor
     */
    public JavaCodeGenerator(Worker worker) {
    	this.worker = worker;
        worker.initialize();
        inference = worker.getInference();
        ontology = worker.getOwlOntology();
    }

    /**
     * Initiates the code generation
     * 
     * @throws IOException
     */
    public void createAll() throws IOException {
        Collection<OWLClass> owlClassList = worker.getOwlClasses();
        //printVocabularyCode(owlClassList);
        printSchemaCode(owlClassList);
        //printNamespaceFile(owlClassList);
        printAppconfigFile(owlClassList);
        //printFactoryClassCode(owlClassList);
        printStorageMgtCode(owlClassList);
        printSerializerMgtCode(owlClassList);
        printWrappedIndividualCode(worker.getWrappedIndividualFile(), CREATE_WRAPPEDINDIVIDUAL);
        printWrappedIndividualCode(worker.getWrappedIndividualPojoFile(), CREATE_WRAPPEDINDIVIDUALPOJO);
        printWrappedIndividualCode(worker.getWrappedIndividualRDFFile(), CREATE_WRAPPEDINDIVIDUALRDF);
        printWrappedIndividualCode(worker.getRestWrappedIndividualFile(), CREATE_RESTWRAPPEDINDIVIDUAL);
        printWrappedIndividualCode(worker.getModelWrappedIndividualFile(), CREATE_MODELWRAPPEDINDIVIDUAL);
        printWrappedIndividualCode(worker.getEnumWrappedIndividualFile(), CREATE_ENUMWRAPPEDINDIVIDUAL);
        for (OWLClass owlClass : owlClassList) {
            createInterface(owlClass);
            createPOJO(owlClass);
            createRSPOJO(owlClass);
            createRDFPOJO(owlClass);
            createMODELPOJO(owlClass);
            createMANAGERPOJO(owlClass);
            //createImplementation(owlClass);
        }
    }

    /**
     * Generates interface code for the provided OWlClass
     * 
     * @param owlClass The class whose interface code is to generated
     * @throws IOException
     */
    private void createInterface(OWLClass owlClass) throws IOException {
        File baseFile = worker.getInterfaceFile(owlClass);
        FileWriter fileWriter = new FileWriter(baseFile);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printInterfaceCode(owlClass, printWriter);
        printWriter.close();
    }
    
    

    private void createPOJO(OWLClass owlClass) throws IOException {
        File baseFile = worker.getPOJOFile(owlClass);
        FileWriter fileWriter = new FileWriter(baseFile);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printPOJOCode(owlClass, printWriter, CREATE_POJO_HEADER,CREATE_OBJECT_PROPERTY_POJO, CREATE_FUNCTIONAL_OBJECT_PROPERTY_POJO,CREATE_DATA_PROPERTY_POJO, CREATE_FUNCTIONAL_DATA_PROPERTY_POJO, CREATE_POJO_TAIL);
        printWriter.close();
    }

    private void createRSPOJO(OWLClass owlClass) throws IOException {
        File baseFile = worker.getRSPOJOFile(owlClass);
        FileWriter fileWriter = new FileWriter(baseFile);
        PrintWriter printWriter = new PrintWriter(fileWriter);
    	Map<SubstitutionVariable, String> substitutions = new EnumMap<SubstitutionVariable, String>(SubstitutionVariable.class);
        fillAndWriteTemplate(printWriter, CREATE_RSPOJO_HEADER, substitutions, owlClass, null);
        fillAndWriteTemplate(printWriter, CREATE_RSPOJO_TAIL, substitutions, owlClass, null);
        printWriter.close();
    }

    private void createRDFPOJO(OWLClass owlClass) throws IOException {
        File baseFile = worker.getRDFPOJOFile(owlClass);
        FileWriter fileWriter = new FileWriter(baseFile);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printPOJOCode(owlClass, printWriter, CREATE_RDFPOJO_HEADER,CREATE_OBJECT_PROPERTY_RDFPOJO, CREATE_FUNCTIONAL_OBJECT_PROPERTY_RDFPOJO,CREATE_DATA_PROPERTY_RDFPOJO, CREATE_FUNCTIONAL_DATA_PROPERTY_RDFPOJO,  CREATE_RDFPOJO_TAIL );
        printWriter.close();
    }

    private void createMODELPOJO(OWLClass owlClass) throws IOException {
        File baseFile = worker.getMODELPOJOFile(owlClass);
        FileWriter fileWriter = new FileWriter(baseFile);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printMODELPOJOCode(owlClass, printWriter);
        printWriter.close();
    }

    private void createMANAGERPOJO(OWLClass owlClass) throws IOException {
        File baseFile = worker.getMANAGERPOJOFile(owlClass);
        FileWriter fileWriter = new FileWriter(baseFile);
        PrintWriter printWriter = new PrintWriter(fileWriter);
    	Map<SubstitutionVariable, String> substitutions = new EnumMap<SubstitutionVariable, String>(SubstitutionVariable.class);
        fillAndWriteTemplate(printWriter, CREATE_MANAGERPOJO_HEADER, substitutions, owlClass, null);
        //for (OWLClass enumClass:Utilities.findSubClasses(owlClass,ontology))
        for (OWLClass enumClass:inference.getSubClasses(owlClass))
             if (inference.getOwlClasses().contains(enumClass))
            fillAndWriteTemplate(printWriter, CREATE_MANAGERPOJO_ENUM, substitutions, enumClass, null);
        fillAndWriteTemplate(printWriter, CREATE_MANAGERPOJO_TAIL, substitutions, owlClass, null);
        printWriter.close();
    }

    private void printPOJOCode(OWLClass owlClass, PrintWriter printWriter, CodeGenerationPhase header ,CodeGenerationPhase object, CodeGenerationPhase func_object, CodeGenerationPhase data, CodeGenerationPhase func_data, CodeGenerationPhase tail  ) {
        Collection<OWLObjectProperty> owlObjectProperties = worker.getObjectPropertiesForClass(owlClass);
        Collection<OWLDataProperty> owlDataProperties = worker.getDataPropertiesForClass(owlClass);
        
    	Map<SubstitutionVariable, String> substitutions = new EnumMap<SubstitutionVariable, String>(SubstitutionVariable.class);
        
    	fillAndWriteTemplate(printWriter, header , substitutions, owlClass, null);
        
    	for (OWLObjectProperty owlObjectProperty : owlObjectProperties) {
    		if (inference.isFunctional(owlObjectProperty)) {
                fillAndWriteTemplate(printWriter, func_object, substitutions, owlClass, owlObjectProperty);    		
    		}
    		else {
    			fillAndWriteTemplate(printWriter, object, substitutions, owlClass, owlObjectProperty);
    		}
        }
        
        for (OWLDataProperty owlDataProperty :owlDataProperties) {
        	if (inference.isFunctional(owlDataProperty)) {
                fillAndWriteTemplate(printWriter, func_data , substitutions, owlClass, owlDataProperty);        		
        	}
        	else {
        		fillAndWriteTemplate(printWriter, data, substitutions, owlClass, owlDataProperty);
        	}
        }
        
        fillAndWriteTemplate(printWriter, tail, substitutions, owlClass, null);
    }

    private void printMODELPOJOCode(OWLClass owlClass, PrintWriter printWriter) {
    

        Collection<OWLObjectProperty> owlObjectProperties = worker.getObjectPropertiesForClass(owlClass);
        Collection<OWLDataProperty> owlDataProperties = worker.getDataPropertiesForClass(owlClass);
        
    	Map<SubstitutionVariable, String> substitutions = new EnumMap<SubstitutionVariable, String>(SubstitutionVariable.class);
        
    	fillAndWriteTemplate(printWriter,  CREATE_MODELPOJO_HEADER, substitutions, owlClass, null);
    	for (OWLObjectProperty owlObjectProperty : owlObjectProperties) {
    		if (inference.isFunctional(owlObjectProperty)) {
                fillAndWriteTemplate(printWriter,CREATE_FUNCTIONAL_OBJECT_PROPERTY_MODELPOJO, substitutions, owlClass, owlObjectProperty);    		
    		}
    		else {
    			fillAndWriteTemplate(printWriter, CREATE_OBJECT_PROPERTY_MODELPOJO, substitutions, owlClass, owlObjectProperty);
    		}
        }
        
        for (OWLDataProperty owlDataProperty :owlDataProperties) {
            CodeGenerationPhase data= CREATE_TYPEDDATA_PROPERTY_MODELPOJO; 
            CodeGenerationPhase func_data= CREATE_FUNCTIONAL_DATA_PROPERTY_MODELPOJO;
            //String type=owlDataProperty.getEntityType().getName();
            //System.out.println("CHECKTHISOUT "+type);
            //if (type.contains("Double") || type.contains("Float") ||type.contains("Boolean")  ){
               // data=CREATE_TYPEDDATA_PROPERTY_MODELPOJO;
               // func_data=CREATE_TYPEDDATA_PROPERTY_MODELPOJO;
            /*}
            else if (type.contains("int") || type.contains("Integer")){
                data=CREATE_INTDATA_PROPERTY_MODELPOJO;
                func_data=CREATE_INTDATA_PROPERTY_MODELPOJO;
            }
            else if (type.contains("Literal") || type.contains("String")){
                data=CREATE_STRINGDATA_PROPERTY_MODELPOJO;
                func_data=CREATE_STRINGDATA_PROPERTY_MODELPOJO;
            }*/
            //TODO XMLGREGORIANCALENDAR
            //else is binary use default template  
            if (inference.isFunctional(owlDataProperty)) {
                fillAndWriteTemplate(printWriter, func_data , substitutions, owlClass, owlDataProperty);        		
            }
            else {
                fillAndWriteTemplate(printWriter, data, substitutions, owlClass, owlDataProperty);
            }
        }
        
        fillAndWriteTemplate(printWriter, CREATE_MODELPOJO_TAIL, substitutions, owlClass, null);
    }

    /**
     * Writes the interface code for the provided OWlClass to the PrintStream
     * 
     * @param interfaceName 
     * @param owlClass
     * @param printWriter
     */
    private void printInterfaceCode(OWLClass owlClass, PrintWriter printWriter) {
        Collection<OWLObjectProperty> owlObjectProperties = worker.getObjectPropertiesForClass(owlClass);
        Collection<OWLDataProperty> owlDataProperties     = worker.getDataPropertiesForClass(owlClass);
        
    	Map<SubstitutionVariable, String> substitutions = new EnumMap<SubstitutionVariable, String>(SubstitutionVariable.class);
    	
    	fillAndWriteTemplate(printWriter, CREATE_INTERFACE_HEADER, substitutions, owlClass, null);

        //ENUM of Subtypes
        for (OWLClass enumClass:inference.getSubClasses(owlClass))
             if (inference.getOwlClasses().contains(enumClass))
            fillAndWriteTemplate(printWriter, CREATE_INTERFACE_ENUMCLASS, substitutions, enumClass, null);

            fillAndWriteTemplate(printWriter, CREATE_INTERFACE_ENUMTAIL, substitutions, owlClass, null);

        //End of enum

        for (OWLObjectProperty owlObjectProperty : owlObjectProperties) {
        	if (inference.isFunctional(owlObjectProperty)) {
        		fillAndWriteTemplate(printWriter, CREATE_FUNCTIONAL_OBJECT_PROPERTY_INTERFACE, substitutions, owlClass, owlObjectProperty);        		
        	}
        	else {
        		fillAndWriteTemplate(printWriter, CREATE_OBJECT_PROPERTY_INTERFACE, substitutions, owlClass, owlObjectProperty);
        	}
        }
        
        for (OWLDataProperty owlDataProperty :owlDataProperties) {
        	if (inference.isFunctional(owlDataProperty)) {
        		fillAndWriteTemplate(printWriter, CREATE_FUNCTIONAL_DATA_PROPERTY_INTERFACE, substitutions, owlClass, owlDataProperty);
        	}
        	else {
        		fillAndWriteTemplate(printWriter, CREATE_DATA_PROPERTY_INTERFACE, substitutions, owlClass, owlDataProperty);
        	}
        }
    	
        fillAndWriteTemplate(printWriter, CREATE_INTERFACE_TAIL, substitutions, owlClass, null);
    }
    
    
    private void createImplementation(OWLClass owlClass) throws IOException {
        File baseFile = worker.getImplementationFile(owlClass);
        FileWriter fileWriter = new FileWriter(baseFile);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printImplementationCode(owlClass, printWriter);
        printWriter.close();
    }

    private void printImplementationCode(OWLClass owlClass, PrintWriter printWriter) {
        Collection<OWLObjectProperty> owlObjectProperties = worker.getObjectPropertiesForClass(owlClass);
        Collection<OWLDataProperty> owlDataProperties = worker.getDataPropertiesForClass(owlClass);
        
    	Map<SubstitutionVariable, String> substitutions = new EnumMap<SubstitutionVariable, String>(SubstitutionVariable.class);
        
    	fillAndWriteTemplate(printWriter, CREATE_IMPLEMENTATION_HEADER, substitutions, owlClass, null);
        
    	for (OWLObjectProperty owlObjectProperty : owlObjectProperties) {
    		if (inference.isFunctional(owlObjectProperty)) {
                fillAndWriteTemplate(printWriter, CREATE_FUNCTIONAL_OBJECT_PROPERTY_IMPLEMENTATION, substitutions, owlClass, owlObjectProperty);    		
    		}
    		else {
    			fillAndWriteTemplate(printWriter, CREATE_OBJECT_PROPERTY_IMPLEMENTATION, substitutions, owlClass, owlObjectProperty);
    		}
        }
        
        for (OWLDataProperty owlDataProperty :owlDataProperties) {
        	if (inference.isFunctional(owlDataProperty)) {
                fillAndWriteTemplate(printWriter, CREATE_FUNCTIONAL_DATA_PROPERTY_IMPLEMENTATION, substitutions, owlClass, owlDataProperty);        		
        	}
        	else {
        		fillAndWriteTemplate(printWriter, CREATE_DATA_PROPERTY_IMPLEMENTATION, substitutions, owlClass, owlDataProperty);
        	}
        }
        
        fillAndWriteTemplate(printWriter, CREATE_IMPLEMENTATION_TAIL, substitutions, owlClass, null);
    }



    /** Initilizes the vocabulary code generation 
     * @param owlClassList
     * @throws IOException
     */
    private void printVocabularyCode(Collection<OWLClass> owlClassList) throws IOException {
        File vocabularyFile = worker.getVocabularyFile();
        FileWriter vocabularyfileWriter = new FileWriter(vocabularyFile);
        PrintWriter vocabularyPrintWriter = new PrintWriter(vocabularyfileWriter);
    	Map<SubstitutionVariable, String> substitutions = new EnumMap<SubstitutionVariable, String>(SubstitutionVariable.class);
        fillAndWriteTemplate(vocabularyPrintWriter, CREATE_VOCABULARY_HEADER, substitutions, null, null);

        for (OWLClass owlClass : owlClassList) {
            fillAndWriteTemplate(vocabularyPrintWriter, CREATE_CLASS_VOCABULARY, substitutions, owlClass, null);
        }

        for (OWLObjectProperty owlObjectProperty : worker.getOwlObjectProperties()) {
            fillAndWriteTemplate(vocabularyPrintWriter, CREATE_OBJECT_PROPERTY_VOCABULARY, substitutions, null, owlObjectProperty);
        }

        for (OWLDataProperty owlDataProperty : worker.getOwlDataProperties()) {
            fillAndWriteTemplate(vocabularyPrintWriter, CREATE_DATA_PROPERTY_VOCABULARY, substitutions, null, owlDataProperty);
        }
        
        fillAndWriteTemplate(vocabularyPrintWriter, CREATE_VOCABULARY_TAIL, substitutions, null, null);
    
        vocabularyPrintWriter.close();
    }

    /** Initilizes the vocabulary code generation 
     * @param owlClassList
     * @throws IOException
     */
    private void printSchemaCode(Collection<OWLClass> owlClassList) throws IOException {
        //Collection<OWLDataProperty> owlDataProperties     = worker.getDataPropertiesForClass(owlClass);
        File schemaFile = worker.getSchemaFile();
        FileWriter fileWriter = new FileWriter(schemaFile);
        PrintWriter printWriter = new PrintWriter(fileWriter);
    	Map<SubstitutionVariable, String> substitutions = new EnumMap<SubstitutionVariable, String>(SubstitutionVariable.class);
        fillAndWriteTemplate(printWriter, CREATE_SCHEMA_FILE, substitutions, null, null);

        for (OWLClass owlClass : owlClassList) {
            fillAndWriteTemplate(printWriter, CREATE_SCHEMA_HEADER, substitutions, null, null);
            fillAndWriteTemplate(printWriter, CREATE_SCHEMA_CLASS, substitutions, owlClass, null);

            for (OWLObjectProperty owlObjectProperty : worker.getObjectPropertiesForClass(owlClass)) {
                if (inference.isFunctional(owlObjectProperty)) {
                    fillAndWriteTemplate(printWriter, CREATE_SCHEMA_FUNCTIONAL_OBJECT_PROPERTY, substitutions, owlClass, owlObjectProperty);        		
                }
                else {
                    fillAndWriteTemplate(printWriter, CREATE_SCHEMA_OBJECT_PROPERTY, substitutions, owlClass, owlObjectProperty);
                    //fillAndWriteTemplate(printWriter, CREATE_SCHEMA_OBJECT_PROPERTY, substitutions, null, owlObjectProperty);
                }
            }

            for (OWLDataProperty owlDataProperty : worker.getDataPropertiesForClass(owlClass)) {
                if (inference.isFunctional(owlDataProperty)) {
                    fillAndWriteTemplate(printWriter, CREATE_SCHEMA_FUNCTIONAL_DATA_PROPERTY, substitutions, owlClass, owlDataProperty);        		
                }
                else {
                    fillAndWriteTemplate(printWriter, CREATE_SCHEMA_DATA_PROPERTY, substitutions, owlClass, owlDataProperty);
                    //fillAndWriteTemplate(vocabularyPrintWriter, CREATE_SCHEMA_DATA_PROPERTY, substitutions, null, owlDataProperty);
                }
            }
            fillAndWriteTemplate(printWriter, CREATE_SCHEMA_TAIL, substitutions, null, null);
        }
        fillAndWriteTemplate(printWriter, CREATE_SCHEMA_EOF, substitutions, null, null);
        
    
        printWriter.close();
    }


    /** Initilizes the StorageMgt code generation 
     * @param owlClassList
     * @throws IOException
     */
    private void printStorageMgtCode(Collection<OWLClass> owlClassList) throws IOException {
        File smgtFile = worker.getStorageMgtFile();
        FileWriter fileWriter = new FileWriter(smgtFile);
        PrintWriter printWriter = new PrintWriter(fileWriter);
    	Map<SubstitutionVariable, String> substitutions = new EnumMap<SubstitutionVariable, String>(SubstitutionVariable.class);
        fillAndWriteTemplate(printWriter, CREATE_STORAGEMGT_HEADER, substitutions, null, null);

        for (OWLClass owlClass : owlClassList) {
            fillAndWriteTemplate(printWriter, CREATE_CLASS_STORAGEMGT, substitutions, owlClass, null);
            Collection<OWLClass> enumClassList = inference.getSubClasses(owlClass);
                //Utilities.findSubClasses(owlClass, ontology); 
            fillAndWriteTemplate(printWriter, CREATE_ENUMCLASS_STORAGEMGT, substitutions, owlClass, null);
            for (OWLClass enumClass : enumClassList) {
                if (inference.getOwlClasses().contains(enumClass))
                    fillAndWriteTemplate(printWriter, CREATE_ENUMCLASS_STORAGEMGT, substitutions, enumClass, null);
            }
            fillAndWriteTemplate(printWriter, CREATE_STORAGEMGT_ENUMTAIL, substitutions, null, null);
        }

        /*for (OWLObjectProperty owlObjectProperty : worker.getOwlObjectProperties()) {
            fillAndWriteTemplate(printWriter, CREATE_OBJECT_PROPERTY_STORAGEMGT, substitutions, null, owlObjectProperty);
        }

        for (OWLDataProperty owlDataProperty : worker.getOwlDataProperties()) {
            fillAndWriteTemplate(printWriter, CREATE_DATA_PROPERTY_STORAGEMGT, substitutions, null, owlDataProperty);
        }*/
        
        fillAndWriteTemplate(printWriter, CREATE_STORAGEMGT_TAIL, substitutions, null, null);
    
        printWriter.close();
    }

    /** Initilizes the SerializerMgt code generation 
     * @param owlClassList
     * @throws IOException
     */
    private void printSerializerMgtCode(Collection<OWLClass> owlClassList) throws IOException {
        File smgtFile = worker.getSerializerMgtFile();
        FileWriter fileWriter = new FileWriter(smgtFile);
        PrintWriter printWriter = new PrintWriter(fileWriter);
    	Map<SubstitutionVariable, String> substitutions = new EnumMap<SubstitutionVariable, String>(SubstitutionVariable.class);
        fillAndWriteTemplate(printWriter, CREATE_SERIALIZERMGT_HEADER, substitutions, null, null);

        for (OWLClass owlClass : owlClassList) {
            fillAndWriteTemplate(printWriter, CREATE_CLASS_SERIALIZERMGT, substitutions, owlClass, null);
        }

        /*for (OWLObjectProperty owlObjectProperty : worker.getOwlObjectProperties()) {
            fillAndWriteTemplate(printWriter, CREATE_OBJECT_PROPERTY_STORAGEMGT, substitutions, null, owlObjectProperty);
        }

        for (OWLDataProperty owlDataProperty : worker.getOwlDataProperties()) {
            fillAndWriteTemplate(printWriter, CREATE_DATA_PROPERTY_STORAGEMGT, substitutions, null, owlDataProperty);
        }*/
        
        fillAndWriteTemplate(printWriter, CREATE_SERIALIZERMGT_TAIL, substitutions, null, null);
    
        printWriter.close();
    }

    /** Initilizes the namespace file generation 
     * @param owlClassList
     * @throws IOException
     */
    private void printNamespaceFile(Collection<OWLClass> owlClassList) throws IOException {
        File nsFile = worker.getNamespaceFile();
        FileWriter nsfileWriter = new FileWriter(nsFile);
        PrintWriter nsPrintWriter = new PrintWriter(nsfileWriter);
    	Map<SubstitutionVariable, String> substitutions = new EnumMap<SubstitutionVariable, String>(SubstitutionVariable.class);
        fillAndWriteTemplate(nsPrintWriter, CREATE_NAMESPACE_HEADER, substitutions, null, null);

        for (OWLClass owlClass : owlClassList) {
            fillAndWriteTemplate(nsPrintWriter, CREATE_CLASS_NAMESPACE, substitutions, owlClass, null);
        }

        /*for (OWLObjectProperty owlObjectProperty : worker.getOwlObjectProperties()) {
            fillAndWriteTemplate(vocabularyPrintWriter, CREATE_OBJECT_PROPERTY_VOCABULARY, substitutions, null, owlObjectProperty);
        }

        for (OWLDataProperty owlDataProperty : worker.getOwlDataProperties()) {
            fillAndWriteTemplate(vocabularyPrintWriter, CREATE_DATA_PROPERTY_VOCABULARY, substitutions, null, owlDataProperty);
        }*/
        
        fillAndWriteTemplate(nsPrintWriter, CREATE_NAMESPACE_TAIL, substitutions, null, null);
    
        nsPrintWriter.close();
    }

    /** Initilizes the WrappedIndividual file generation 
     * @param owlClassList
     * @throws IOException
     */
    private void printWrappedIndividualCode(File file, CodeGenerationPhase template) throws IOException {
        FileWriter fileWriter = new FileWriter(file);
        PrintWriter printWriter = new PrintWriter(fileWriter);
    	Map<SubstitutionVariable, String> substitutions = new EnumMap<SubstitutionVariable, String>(SubstitutionVariable.class);
        fillAndWriteTemplate(printWriter, template, substitutions, null, null);
        printWriter.close();
    }

    /** Initilizes the application configuration file generation 
     * @param owlClassList
     * @throws IOException
     */
    private void printAppconfigFile(Collection<OWLClass> owlClassList) throws IOException {
        File nsFile = worker.getAppconfigFile();
        FileWriter nsfileWriter = new FileWriter(nsFile);
        PrintWriter nsPrintWriter = new PrintWriter(nsfileWriter);
    	Map<SubstitutionVariable, String> substitutions = new EnumMap<SubstitutionVariable, String>(SubstitutionVariable.class);
        fillAndWriteTemplate(nsPrintWriter, CREATE_APPCONFIG_HEADER, substitutions, null, null);

        for (OWLClass owlClass : owlClassList) {
            fillAndWriteTemplate(nsPrintWriter, CREATE_CLASS_APPCONFIG, substitutions, owlClass, null);
        }

        /*for (OWLObjectProperty owlObjectProperty : worker.getOwlObjectProperties()) {
            fillAndWriteTemplate(vocabularyPrintWriter, CREATE_OBJECT_PROPERTY_VOCABULARY, substitutions, null, owlObjectProperty);
        }

        for (OWLDataProperty owlDataProperty : worker.getOwlDataProperties()) {
            fillAndWriteTemplate(vocabularyPrintWriter, CREATE_DATA_PROPERTY_VOCABULARY, substitutions, null, owlDataProperty);
        }*/
        
        fillAndWriteTemplate(nsPrintWriter, CREATE_APPCONFIG_TAIL, substitutions, null, null);
    
        nsPrintWriter.close();
    }

    /** Initializes the code generation for factory classes 
     * @param owlClassList
     * @throws IOException
     */
    private void printFactoryClassCode(Collection<OWLClass> owlClassList) throws IOException {
        FileWriter factoryFileWriter = null;
        PrintWriter factoryPrintWriter = null;
        File factoryFile = worker.getFactoryFile();
        factoryFileWriter = new FileWriter(factoryFile);
        factoryPrintWriter = new PrintWriter(factoryFileWriter);
        
    	Map<SubstitutionVariable, String> substitutions = new EnumMap<SubstitutionVariable, String>(SubstitutionVariable.class);
        
    	fillAndWriteTemplate(factoryPrintWriter, CREATE_FACTORY_HEADER, substitutions, null, null);

        for (OWLClass owlClass : owlClassList) {
            fillAndWriteTemplate(factoryPrintWriter, CREATE_FACTORY_CLASS, substitutions, owlClass, null);
        }
        
        fillAndWriteTemplate(factoryPrintWriter, CREATE_FACTORY_TAIL, substitutions, null, null);
        
        factoryPrintWriter.close();
    }

    private void fillAndWriteTemplate(PrintWriter writer, 
    		                               CodeGenerationPhase phase, 
    		                               Map<SubstitutionVariable, String> substitutions, 
    		                               OWLClass owlClass, OWLEntity owlProperty) {
        if (owlProperty!=null && owlProperty.toStringID().contains("owl#top")) return;
    	worker.configureSubstitutions(phase, substitutions, owlClass, owlProperty);
        String template = worker.getTemplate(phase, owlClass, owlProperty);
    	fillTemplate(writer, template, substitutions);
    }
	
	public static void fillTemplate(PrintWriter writer, String template, Map<SubstitutionVariable, String> substitutions) {
		for (Entry<SubstitutionVariable, String> entry : substitutions.entrySet()) {
			SubstitutionVariable var = entry.getKey();
			String replacement = entry.getValue();
			template = template.replaceAll("\\$\\{" + var.getName() + "\\}", replacement);
		}
		writer.append(template);
	}


}
