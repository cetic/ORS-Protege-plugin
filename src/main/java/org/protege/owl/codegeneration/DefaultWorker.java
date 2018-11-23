package org.protege.owl.codegeneration;

import static org.protege.owl.codegeneration.SubstitutionVariable.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.Collection;
import java.util.Date;
import java.util.EnumMap;
import java.util.Map;
import java.util.TreeSet;

import org.protege.owl.codegeneration.inference.CodeGenerationInference;
import org.protege.owl.codegeneration.inference.SimpleInference;
import org.protege.owl.codegeneration.names.CodeGenerationNames;
import org.protege.owl.codegeneration.names.NamingUtilities;
import org.protege.owl.codegeneration.property.JavaPropertyDeclarationCache;
import org.semanticweb.owlapi.model.OWLAnnotation;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLEntity;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.search.EntitySearcher;

public class DefaultWorker implements Worker {
	private EnumMap<CodeGenerationPhase, String> templateMap = new EnumMap<CodeGenerationPhase, String>(CodeGenerationPhase.class);
	private OWLOntology owlOntology;
	private CodeGenerationOptions options;
	private CodeGenerationNames names;
    private CodeGenerationInference inference;
    private JavaPropertyDeclarationCache propertyDeclarations;
    
    public static void generateCode(OWLOntology ontology, CodeGenerationOptions options, CodeGenerationNames names) throws IOException {
    	generateCode(ontology, options, names, new SimpleInference(ontology));
    }
    
    public static void generateCode(OWLOntology ontology, CodeGenerationOptions options, CodeGenerationNames names, CodeGenerationInference inference) throws IOException {
    	Worker worker = new DefaultWorker(ontology, options, names, inference);
    	JavaCodeGenerator generator = new JavaCodeGenerator(worker);
    	generator.createAll();
    }    
	
    
    public DefaultWorker(OWLOntology ontology, 
    		             CodeGenerationOptions options,
    		             CodeGenerationNames names,
			             CodeGenerationInference inference) {
		this.owlOntology = ontology;
		this.options = options;
		this.names = names;
		this.inference = inference;
		propertyDeclarations = new JavaPropertyDeclarationCache(inference, names);
	}

    public OWLOntology getOwlOntology() {
		return owlOntology;
	}
    
    @Override
    public CodeGenerationInference getInference() {
    	return inference;
    }
    
    public Collection<OWLClass> getOwlClasses() {
    	return new TreeSet<OWLClass>(inference.getOwlClasses());
    }
    
    public Collection<OWLObjectProperty> getOwlObjectProperties() {
    	return Utilities.filterIgnored(owlOntology.getObjectPropertiesInSignature(true), owlOntology);
    }
    
    public Collection<OWLDataProperty> getOwlDataProperties() {
    	return Utilities.filterIgnored(owlOntology.getDataPropertiesInSignature(true), owlOntology);
    }
    
    public Collection<OWLObjectProperty> getObjectPropertiesForClass(OWLClass owlClass) {
    	return Utilities.filterIgnored(propertyDeclarations.getObjectPropertiesForClass(owlClass), owlOntology);
    }
    
    public Collection<OWLDataProperty> getDataPropertiesForClass(OWLClass owlClass) {
    	return Utilities.filterIgnored(propertyDeclarations.getDataPropertiesForClass(owlClass), owlOntology);
    }

    public void initialize() {
        File folder = options.getOutputFolder();
        if (folder != null && !folder.exists()) {
            folder.mkdirs();
        }
        File packageFile;
        String pack = options.getPackage();
        if (pack != null) {
            String packPath = pack.replace('.', '/');
            packageFile = folder == null ? new File(packPath) : new File(folder, packPath);
            packageFile.mkdirs();
        } else {
        	packageFile= new File("");
        }
        File implFile = new File(packageFile, "impl");
        implFile.mkdirs();
        File pojoFile = new File(packageFile, "pojo");
        pojoFile.mkdirs();
        File nsFile = new File(packageFile, "namespace");
        nsFile.mkdirs();
        File schemaFile = new File(packageFile, "schema");
        schemaFile.mkdirs();
        File appconfFile = new File(packageFile, "rs");
        appconfFile.mkdirs();
        File managerFile = new File(packageFile, "manager");
        managerFile.mkdirs();
        File modelFile = new File(packageFile, "model");
        modelFile.mkdirs();
        File rdfFile = new File(packageFile, "rdf");
        rdfFile.mkdirs();
        String subPackage = options.getFactorySubPackage();
        String subPackagePath = subPackage.replace('.', '/');
        File factoryDirectory = new File(packageFile, subPackagePath);
        factoryDirectory.mkdirs();
    }
    
    public File getInterfaceFile(OWLClass owlClass) {
        String interfaceName = names.getInterfaceName(owlClass);
        return getInterfaceFile(interfaceName);
    }

    public File getImplementationFile(OWLClass owlClass) {
    	String implName = names.getImplementationName(owlClass);
    	return getImplementationFile(implName);
    }
    
    public File getPOJOFile(OWLClass owlClass) {
        String pojoName = names.getPOJOName(owlClass);
        return getANYPOJOFile(pojoName, "pojo");
    }

    public File getRSPOJOFile(OWLClass owlClass) {
        String pojoName = names.getRSPOJOName(owlClass);
        return getANYPOJOFile(pojoName, "rs");
    }
    
    public File getRDFPOJOFile(OWLClass owlClass) {
        String pojoName = names.getRDFPOJOName(owlClass);
        return getANYPOJOFile(pojoName, "rdf");
    }

    public File getMODELPOJOFile(OWLClass owlClass) {
        String pojoName = names.getMODELPOJOName(owlClass);
        return getANYPOJOFile(pojoName, "model");
    }

    public File getMANAGERPOJOFile(OWLClass owlClass) {
        String pojoName = names.getMANAGERPOJOName(owlClass);
        return getANYPOJOFile(pojoName, "manager");
    }

    public File getVocabularyFile() {
    	return new File(options.getOutputFolder(),
    			options.getVocabularyFqn().replace('.', '/') +".java");
    }

    public File getStorageMgtFile() {
    	return new File(options.getOutputFolder(), getPack()+"manager/"+ "StorageManager.java");
    }

    public File getSerializerMgtFile() {
    	return new File(options.getOutputFolder(), getPack()+"model/"+ "SerializerManager.java");
    }

    public File getNamespaceFile() {
        String pack = options.getPackage();
        if (pack != null) {
            pack = pack.replace('.', '/') + "/";
        } else {
            pack = "";
        }
    	return new File(options.getOutputFolder(),pack+"namespace/"+options.getNamespaceFile());
    }

    public File getAppconfigFile() {
        String pack = options.getPackage();
        if (pack != null) {
            pack = pack.replace('.', '/') + "/";
        } else {
            pack = "";
        }
    	return new File(options.getOutputFolder(),pack+"rs/"+options.getAppconfigFile());
    }

    public File getSchemaFile() {
    	return new File(options.getOutputFolder(),getPack()+"schema/schema.json");
        /*String pack = options.getPackage();
        if (pack != null) {
            pack = pack.replace('.', '/') + "/";
        } else {
            pack = "";
        }
    	return new File(options.getOutputFolder(),pack+"schema/"+options.getAppconfigFile());
        */
    }

    public File getWrappedIndividualFile() {
    	return new File(options.getOutputFolder(),getPack()+"WrappedIndividual.java");
    }
    public File getWrappedIndividualPojoFile() {
    	return new File(options.getOutputFolder(),getPack()+"pojo/WrappedIndividualPojo.java");
    }
    public File getWrappedIndividualRDFFile() {
    	return new File(options.getOutputFolder(),getPack()+"rdf/WrappedIndividualRDF.java");
    }
    public File getRestWrappedIndividualFile() {
    	return new File(options.getOutputFolder(),getPack()+"rs/RestWrappedIndividual.java");
    }
    public File getModelWrappedIndividualFile() {
    	return new File(options.getOutputFolder(),getPack()+"model/ModelWrappedIndividual.java");
    }
    public File getEnumWrappedIndividualFile() {
    	return new File(options.getOutputFolder(),getPack()+"manager/EnumWrappedIndividual.java");
    }

    private String getPack(){
        String pack = options.getPackage();
        if (pack != null) {
            pack = pack.replace('.', '/') + "/";
        } else {
            pack = "";
        }
        return pack;
    }

    
    public File getFactoryFile() {
    	return new File(options.getOutputFolder(),
    			options.getFactoryFqn().replace('.', '/')+".java");
    }


	public String getTemplate(CodeGenerationPhase phase, OWLClass owlClass, Object owlProperty) {
    	String resource = "/" + phase.getTemplateName();
		String template = templateMap.get(phase);
		if (template == null) {
			try {
				URL u = CodeGenerationOptions.class.getResource(resource);
				Reader reader = new InputStreamReader(u.openStream());
				StringBuffer buffer = new StringBuffer();
				int charsRead;
				char[] characters = new char[1024];
				while (true) {
					charsRead = reader.read(characters);
					if (charsRead < 0) {
						break;
					}
					buffer.append(characters, 0, charsRead);
				}
				template = buffer.toString();
				templateMap.put(phase, template);
				reader.close();
			}
			catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return template;
    }
	
	public void configureSubstitutions(CodeGenerationPhase phase,
									   Map<SubstitutionVariable, String> substitutions, 
									   OWLClass owlClass,
									   OWLEntity owlProperty) {
		switch (phase) {
		case CREATE_SCHEMA_FILE:
		case CREATE_SCHEMA_HEADER:
		case CREATE_VOCABULARY_HEADER:
		case CREATE_STORAGEMGT_HEADER:
		case CREATE_SERIALIZERMGT_HEADER:
		case CREATE_NAMESPACE_HEADER:
		case CREATE_APPCONFIG_HEADER:
		case CREATE_WRAPPEDINDIVIDUAL:
		case CREATE_WRAPPEDINDIVIDUALRDF:
		case CREATE_MODELWRAPPEDINDIVIDUAL:
		case CREATE_RESTWRAPPEDINDIVIDUAL:
		case CREATE_ENUMWRAPPEDINDIVIDUAL:
		//	configureCommonSubstitutions(substitutions, owlClass, owlProperty);
        //    break;
		case CREATE_FACTORY_HEADER:
			configureCommonSubstitutions(substitutions, owlClass, owlProperty);
			break;
		case CREATE_CLASS_VOCABULARY:
		case CREATE_CLASS_STORAGEMGT:
		case CREATE_ENUMCLASS_STORAGEMGT:
		case CREATE_CLASS_SERIALIZERMGT:
		case CREATE_CLASS_NAMESPACE:
		case CREATE_CLASS_APPCONFIG:
		case CREATE_FACTORY_CLASS:
		case CREATE_SCHEMA_CLASS:
			configureClassSubstitutions(substitutions, owlClass);
			break;
		case CREATE_OBJECT_PROPERTY_VOCABULARY:
		case CREATE_DATA_PROPERTY_VOCABULARY:
		//case CREATE_OBJECT_PROPERTY_STORAGEMGT:
		//case CREATE_DATA_PROPERTY_STORAGEMGT:
			configurePropertySubstitutions(substitutions, owlProperty);
			break;
		case CREATE_INTERFACE_HEADER:
		case CREATE_INTERFACE_ENUMCLASS:
		case CREATE_INTERFACE_ENUMTAIL:
		case CREATE_POJO_HEADER:
		case CREATE_RSPOJO_HEADER:
		case CREATE_RDFPOJO_HEADER:
		case CREATE_MODELPOJO_HEADER:
		case CREATE_MANAGERPOJO_HEADER:
		case CREATE_MANAGERPOJO_ENUM:
		case CREATE_MANAGERPOJO_TAIL:
		case CREATE_IMPLEMENTATION_HEADER:
			configureCommonSubstitutions(substitutions, owlClass, owlProperty);
			configureClassSubstitutions(substitutions, owlClass);
			break;
			//configureCommonSubstitutions(substitutions, owlClass, owlProperty);
			//configureClassSubstitutions(substitutions, owlClass);
			//break;
		case CREATE_DATA_PROPERTY_INTERFACE:
		case CREATE_FUNCTIONAL_DATA_PROPERTY_INTERFACE:
		case CREATE_DATA_PROPERTY_IMPLEMENTATION:
		case CREATE_FUNCTIONAL_DATA_PROPERTY_IMPLEMENTATION:
		case CREATE_DATA_PROPERTY_POJO:
		case CREATE_DATA_PROPERTY_RDFPOJO:
		case CREATE_DATA_PROPERTY_MODELPOJO:
        case CREATE_TYPEDDATA_PROPERTY_MODELPOJO:
		case CREATE_FUNCTIONAL_DATA_PROPERTY_POJO:
		case CREATE_FUNCTIONAL_DATA_PROPERTY_RDFPOJO:
		case CREATE_FUNCTIONAL_DATA_PROPERTY_MODELPOJO:
		case CREATE_OBJECT_PROPERTY_INTERFACE:
		case CREATE_FUNCTIONAL_OBJECT_PROPERTY_INTERFACE:
		case CREATE_OBJECT_PROPERTY_POJO:
		case CREATE_OBJECT_PROPERTY_RDFPOJO:
		case CREATE_OBJECT_PROPERTY_MODELPOJO:
		case CREATE_FUNCTIONAL_OBJECT_PROPERTY_POJO:
		case CREATE_FUNCTIONAL_OBJECT_PROPERTY_RDFPOJO:
		case CREATE_FUNCTIONAL_OBJECT_PROPERTY_MODELPOJO:
		case CREATE_OBJECT_PROPERTY_IMPLEMENTATION:
		case CREATE_FUNCTIONAL_OBJECT_PROPERTY_IMPLEMENTATION:
		case CREATE_SCHEMA_DATA_PROPERTY:
		case CREATE_SCHEMA_FUNCTIONAL_DATA_PROPERTY:
		case CREATE_SCHEMA_OBJECT_PROPERTY:
		case CREATE_SCHEMA_FUNCTIONAL_OBJECT_PROPERTY:
			configureClassSubstitutions(substitutions, owlClass);
			configurePropertySubstitutions(substitutions, owlProperty);
	        propertyDeclarations.get(owlClass, owlProperty).configureSubstitutions(substitutions);
			break;
			//configureClassSubstitutions(substitutions, owlClass);
			//configurePropertySubstitutions(substitutions, owlProperty);
	       //propertyDeclarations.get(owlClass, owlProperty).configureSubstitutions(substitutions);
		//	break;
		case CREATE_FACTORY_TAIL:
		case CREATE_IMPLEMENTATION_TAIL:
		case CREATE_POJO_TAIL:
		case CREATE_RSPOJO_TAIL:
		case CREATE_RDFPOJO_TAIL:
		case CREATE_MODELPOJO_TAIL:
		case CREATE_INTERFACE_TAIL:
		case CREATE_VOCABULARY_TAIL:
		case CREATE_SCHEMA_TAIL:
		case CREATE_NAMESPACE_TAIL:
		case CREATE_APPCONFIG_TAIL:
		case CREATE_STORAGEMGT_TAIL:
		case CREATE_STORAGEMGT_ENUMTAIL:
			break;
		case CREATE_SCHEMA_EOF:
			configureCommonSubstitutions(substitutions, owlClass, owlProperty);
            break;
		default:
			break;
		}
		
	}

	private void configureCommonSubstitutions(Map<SubstitutionVariable, String> substitutions, 
											  OWLClass owlClass,
											  OWLEntity owlProperty) {
        substitutions.put(PACKAGE, options.getPackage());
        substitutions.put(DATE, new Date().toString());
        substitutions.put(USER, System.getProperty("user.name"));
        substitutions.put(FACTORY_CLASS_NAME, options.getFactoryClassName());
        substitutions.put(FACTORY_PACKAGE, options.getFactoryPackage());
        substitutions.put(FACTORY_EXTRA_IMPORT, options.getExtraFactoryImport());
        substitutions.put(IMPLEMENTATION_EXTRA_IMPORT, options.getExtraImplementationImport());
        if (options.getNamespace()!=null)
        substitutions.put(NAMESPACE, options.getNamespace());
    }

	private void configureClassSubstitutions(Map<SubstitutionVariable, String> substitutions, 
											  OWLClass owlClass) {
		String upperCaseClassName = names.getClassName(owlClass).toUpperCase();
        String pojoname = names.getPOJOName(owlClass);
        String rspojoname = names.getRSPOJOName(owlClass);
        String rdfpojoname = names.getRDFPOJOName(owlClass);
        String modelpojoname = names.getMODELPOJOName(owlClass);
        String managerpojoname = names.getMANAGERPOJOName(owlClass);
        substitutions.put(INTERFACE_NAME, names.getInterfaceName(owlClass));
        substitutions.put(IMPLEMENTATION_NAME, names.getImplementationName(owlClass));
        substitutions.put(POJO_NAME, pojoname);
        substitutions.put(RSPOJO_NAME, rspojoname);
        substitutions.put(RDFPOJO_NAME,rdfpojoname);
        substitutions.put(MODELPOJO_NAME, modelpojoname);
        substitutions.put(MANAGERPOJO_NAME, managerpojoname);
        substitutions.put(JAVADOC, getJavadoc(owlClass));
        substitutions.put(UPPERCASE_CLASS, upperCaseClassName);
        substitutions.put(SubstitutionVariable.VOCABULARY_CLASS, "CLASS_" + upperCaseClassName);
        substitutions.put(CLASS_IRI, owlClass.getIRI().toString());
        substitutions.put(INTERFACE_LIST, getSuperInterfaceList(owlClass));
        substitutions.put(SUPER_POJO_LIST, getSuperPOJOList(owlClass, pojoname, Constants.ABSTRACT_CODE_GENERATOR_INDIVIDUAL_CLASS));
        substitutions.put(SUPER_POJO, getSuperPOJO(owlClass));
        substitutions.put(SUPER_RSPOJO_LIST, getSuperPOJOList(owlClass, rspojoname, Constants.REST_CODE_GENERATOR_INDIVIDUAL_CLASS));
        substitutions.put(SUPER_RSPOJO, getSuperRSPOJO(owlClass));
        substitutions.put(SUPER_RDFPOJO_LIST, getSuperPOJOList(owlClass, rdfpojoname, Constants.RDF_CODE_GENERATOR_INDIVIDUAL_CLASS));
        substitutions.put(SUPER_RDFPOJO, getSuperRDFPOJO(owlClass));
        substitutions.put(SUPER_MODELPOJO_LIST, getSuperPOJOList(owlClass, modelpojoname, Constants.MODEL_CODE_GENERATOR_INDIVIDUAL_CLASS));
        substitutions.put(SUPER_MODELPOJO, getSuperMODELPOJO(owlClass));
        substitutions.put(SUPER_MANAGERPOJO_LIST, getSuperPOJOList(owlClass, managerpojoname, Constants.MANAGER_CODE_GENERATOR_INDIVIDUAL_CLASS));
        substitutions.put(SUPER_MANAGERPOJO, getSuperMANAGERPOJO(owlClass));
    }

	private void configurePropertySubstitutions(Map<SubstitutionVariable, String> substitutions,
                                                OWLEntity owlProperty) {
        String propertyName;
        if (owlProperty instanceof OWLObjectProperty) {
            OWLObjectProperty owlObjectProperty = (OWLObjectProperty) owlProperty;
            propertyName = names.getObjectPropertyName(owlObjectProperty);
        }
        else {
            OWLDataProperty owlDataProperty = (OWLDataProperty) owlProperty;
            propertyName = names.getDataPropertyName(owlDataProperty);
        }
        String propertyCapitalized = NamingUtilities.convertInitialLetterToUpperCase(propertyName);
        String propertyUpperCase = propertyName.toUpperCase();
        if (owlProperty instanceof OWLObjectProperty) {
            substitutions.put(SubstitutionVariable.VOCABULARY_PROPERTY, "OBJECT_PROPERTY_" + propertyUpperCase);
        }
        else {
            substitutions.put(SubstitutionVariable.VOCABULARY_PROPERTY, "DATA_PROPERTY_" + propertyUpperCase);
        }
        substitutions.put(JAVADOC, getJavadoc(owlProperty));
        substitutions.put(PROPERTY, propertyName);
        substitutions.put(CAPITALIZED_PROPERTY, propertyCapitalized);
        substitutions.put(UPPERCASE_PROPERTY, propertyUpperCase);
        substitutions.put(PROPERTY_IRI, owlProperty.getIRI().toString());
    }
	

	
	/* ******************************************************************************
	 * 
	 */
	private File getInterfaceFile(String name) {
	    String pack = options.getPackage();
	    if (pack != null) {
	        pack = pack.replace('.', '/') + "/";
	    } else {
	        pack = "";
	    }
	    return new File(options.getOutputFolder(), pack + name + ".java");
	}
	


	private String getSuperInterfaceList(OWLClass owlClass) {
	    String base = getBaseInterface(owlClass);
	    if (base == null) {
	    	return Constants.UKNOWN_CODE_GENERATED_INTERFACE;
	    }
	    else {
	    	return base;
	    }
	}

	private String getSuperPOJO(OWLClass owlClass) {
        String basePOJOString = "";
        if (!inference.getSuperClasses(owlClass).isEmpty()){
            OWLClass superClass = (OWLClass)inference.getSuperClasses(owlClass).toArray()[0];
            if (inference.getOwlClasses().contains(superClass)) {
                basePOJOString = names.getPOJOName(superClass);
            } 
        }
        if (basePOJOString.equals("")) 
	    	return Constants.POJO_CODE_GENERATOR_INDIVIDUAL_CLASS;
        return basePOJOString;
	}

	private String getSuperPOJOList(OWLClass owlClass, String name, String defInd) {
	    String base = getBasePOJO(owlClass, name);
	    if (base == null) {
	    	return defInd; //Constants.ABSTRACT_CODE_GENERATOR_INDIVIDUAL_CLASS;
	    }
	    else {
	    	return base;
	    }
	}

	private String getSuperRSPOJO(OWLClass owlClass) {
        String basePOJOString = "";
        if (!inference.getSuperClasses(owlClass).isEmpty()){
            OWLClass superClass = (OWLClass)inference.getSuperClasses(owlClass).toArray()[0];
            if (inference.getOwlClasses().contains(superClass)) {
                basePOJOString = names.getRSPOJOName(superClass);
            } 
        }
        if (basePOJOString.equals("")) 
	    	return Constants.REST_CODE_GENERATOR_INDIVIDUAL_CLASS;
        return basePOJOString;
	}

	private String getSuperRDFPOJO(OWLClass owlClass) {
        String basePOJOString = "";
        if (!inference.getSuperClasses(owlClass).isEmpty()){
            OWLClass superClass = (OWLClass)inference.getSuperClasses(owlClass).toArray()[0];
            if (inference.getOwlClasses().contains(superClass)) {
                basePOJOString = names.getRDFPOJOName(superClass);
            } 
        }
        if (basePOJOString.equals("")) 
	    	return Constants.RDF_CODE_GENERATOR_INDIVIDUAL_CLASS;
        return basePOJOString;
	}

	private String getSuperMODELPOJO(OWLClass owlClass) {
        String basePOJOString = "";
        if (!inference.getSuperClasses(owlClass).isEmpty()){
            OWLClass superClass = (OWLClass)inference.getSuperClasses(owlClass).toArray()[0];
            if (inference.getOwlClasses().contains(superClass)) {
                basePOJOString = names.getMODELPOJOName(superClass);
            } 
        }
        if (basePOJOString.equals("")) 
	    	return Constants.MODEL_CODE_GENERATOR_INDIVIDUAL_CLASS;
        return basePOJOString;
	}

	private String getSuperMANAGERPOJO(OWLClass owlClass) {
        String basePOJOString = "";
        if (!inference.getSuperClasses(owlClass).isEmpty()){
            OWLClass superClass = (OWLClass)inference.getSuperClasses(owlClass).toArray()[0];
            if (inference.getOwlClasses().contains(superClass)) {
                basePOJOString = names.getMANAGERPOJOName(superClass);
            } 
        }
        if (basePOJOString.equals("")) 
	    	return Constants.MANAGER_CODE_GENERATOR_INDIVIDUAL_CLASS;
        return basePOJOString;
	}


	/*private String getSuperRSPOJOList(OWLClass owlClass) {
	    String base = getBasePOJO(owlClass, names.getRSPOJOName(superClass));
	    if (base == null) {
	    	return Constants.REST_CODE_GENERATOR_INDIVIDUAL_CLASS;
	    }
	    else {
	    	return base;
	    }
	}*/
	
    /** Returns base pojo of the provided OWLClass
     * @param owlClass The OWLClass whose superclass is to be returned
     * @return
     */
    private String getBasePOJO(OWLClass owlClass, String name) {
        String basePOJOString = "";
        for (OWLClass superClass : inference.getSuperClasses(owlClass)) {
            if (inference.getOwlClasses().contains(superClass)) {
                basePOJOString += (basePOJOString.equals("") ? "" : ", ") + name; //names.getPOJOName(superClass);
            }
        }
        if (basePOJOString.equals("")) {
            return null;
        } else {
            return basePOJOString;
        }
    }

    /*
    private String getBaseRSPOJO(OWLClass owlClass) {
        String baseRSPOJOString = "";
        for (OWLClass superClass : inference.getSuperClasses(owlClass)) {
            if (inference.getOwlClasses().contains(superClass)) {
                baseRSPOJOString += (baseRSPOJOString.equals("") ? "" : ", ") + names.getRSPOJOName(superClass);
            }
        }
        if (baseRSPOJOString.equals("")) {
            return null;
        } else {
            return baseRSPOJOString;
        }
    }*/

    /** Returns base interface of the provided OWLClass
     * @param owlClass The OWLClass whose base interface is to be returned
     * @return
     */
    private String getBaseInterface(OWLClass owlClass) {
        String baseInterfaceString = "";
        for (OWLClass superClass : inference.getSuperClasses(owlClass)) {
            if (inference.getOwlClasses().contains(superClass)) {
                baseInterfaceString += (baseInterfaceString.equals("") ? "" : ", ") + names.getInterfaceName(superClass);
            }
        }
        if (baseInterfaceString.equals("")) {
            return null;
        } else {
            return baseInterfaceString;
        }
    }

	private File getImplementationFile(String implName) {
	    String pack = options.getPackage();
	    if (pack != null) {
	        pack = pack.replace('.', '/') + "/";
	    } else {
	        pack = "";
	    }
	    return new File(options.getOutputFolder(), pack + "impl/" + implName + ".java");
	}

	private File getANYPOJOFile(String pojoName, String dir) {
	    String pack = options.getPackage();
	    if (pack != null) {
	        pack = pack.replace('.', '/') + "/";
	    } else {
	        pack = "";
	    }
	    if (dir != null) {
	        if (!dir.endsWith("/")) 
	            dir = dir+"/";
	    } else {
            dir="";
	    }
	    return new File(options.getOutputFolder(), pack + dir + pojoName + ".java");
	}
	
	private String getJavadoc(OWLEntity e) {
	    StringBuffer sb = new StringBuffer();
	    Collection<OWLAnnotation> annotations = EntitySearcher.getAnnotations(e, owlOntology, Constants.JAVADOC);
	    if (annotations.size() == 1) {
	        OWLAnnotation javadocAnnotation = annotations.iterator().next();
	        if (javadocAnnotation.getValue() instanceof OWLLiteral) {
	            sb.append('\n');
	            sb.append(((OWLLiteral) javadocAnnotation.getValue()).getLiteral());
	        }
	    }
	    return sb.toString();
	}


}
