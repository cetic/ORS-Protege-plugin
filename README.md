
ORS Protege plugin
========

The protege plugin is based on the code-generation, a plug-in for the Protege Desktop ontology editor for generating Java code from an OWL ontology.

More information on the code-generation project is available on the Protege wiki:

http://protegewiki.stanford.edu/wiki/Protege-OWL_Code_Generator

The ORS Protege plugin generates the Java code including the Java Model from the OWL ontology for using it with ORS. The code includes:

* genetation of Java model classes (POJOs).
* generation of the REST API for resource management.
* generation of the RDF Convertor and Serializer components classes.
* generation of the JSON convertor classes.

## Compile and Run the plug-in

In order to compile the plugin code use the maven script:

mvn clean package

Then in the target directory run the plugin for generating the java code for ORS:

java -jar code-generation-ORS-0.3-jar-with-dependencies.jar -o generated -p be.cetic.ors.ontologybinding <path_to_owl_file>/ontology.owl

Parameters:

 -o Directory of generated code

 -p package name - ORS needs to be configured accordingly if the package is changed.

Use the generated classes in ORS. ORS expects to find the classes under src/generated directory. The directory structure of the code is the one generated by the plugin based on the package name (provided by the -p commmand line parameter)
 

