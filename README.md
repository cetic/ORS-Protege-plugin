# ORS-Protege-plugin

The Ontology Repository Services (ORS) is a tool for generating web services for storing and managing information of a domain and its metadata.
The ORS-Protege-plugin is a plugin for the [Protege](https://github.com/protegeproject/protege) ontology editor that can be used for generating the Java Model from the OWL ontology for using it with ORS.

This plugin is based on the Protege-OWL code generator plugin by Timothy Redmond.  
More information is available on the Protege wiki:

 http://protegewiki.stanford.edu/wiki/Protege-OWL_Code_Generator

## 1 Features

* generates java POJOs
* generates namespaceconfig.properties file

## 2 License 

Copyright © CETIC 2018, www.cetic.be 

Authors: Nikolaos Matskanis

The ORS-Protege-plugin is free open source software available under the LGPL license. See the [LICENSE](https://github.com/cetic/ORS-Protege-plugin/blob/master/LICENSE) file.

## 3 Releases

### Release 0.1 - 18 April 2018

First public release of ORS-Protege-plugin.

This version supports:

* generates a java model represantation of the ontology (POJOs)
* generates namespaceconfig.properties file

## 4 Build & Deploy

Build the plugin:

 >mvn clean package

Deploy it by copying the jar file into Protege's plugin directory 

 >cp target/code-generation-ORS-0.1.jar path/to/protege/installation/plugins/

 (Re)start Protege.
 
## 5 Using the plugin

Once you have loaded or created an ontology in Protege, it is time to create your ORS Java model:
In protege select: tools->Generate ORS Java Model ...

NOTE: Class names of the generated java model (POJOs) must follow the java class name conventions and rules (for example cannot use reserved java names). Thus OWL Classes ideally should also follow the same convetions. If conventions and rules are not followed the POJOs will not be used correctly in the generated ORS project or not at all!

### 5.1 Parameters and Configuration

A new dialog window opens with the following options/cofiguration parameters:

Root output folder -> specify the output directory of the generated java files.

Java Package -> specify the java package name for example "be.cetic.ORS"

Factory class name -> Specify the name of your factory class, anyname that you prefer can be put here. For example "MyORSFactory"

Default namespace -> This is the ontology default namespace prefix. The default one is copied for you already in the textbox. It only makes sense to use one of the prefixes of the current ontology or the imported ones.

Use Reasoner -> This will only be enabled if a reasoner is started and synchronized. The generated code will be more complete if you use a reasoner.

### 5.2 Install model into ORS

Next step is to copy the generated java model into the ORS release:

Copy the pojos into ORS:

> cp path/to/root_output_folder/java_package/pojos/\*.java path/to/ORS/model-resources/generated-sources/pojo/

Copy the namespaceConfig.properties into ORS:

> cp path/to/root_output_folder/java_package/namespace/namespaceConfig.properties path/to/ORS/src/main/resources/

You can now build your ORS and generate your ORS progect!

# 6 Known issues

1. When loading a new ontology on top of an already loaded one in the same window, the plugin will keep the values (namespace, package name, file directory etc)  of the previous ontology. This will cause a problem later in ORS if an incorrect namespace property is used. Edit the namespce textbox to make sure it has the correct value or for automatic refreshing of the values choose to open the new ontology in new window when prompted.
2. When generating the pojos using reasoner you may encounter an exception due to a data property or object property that is subclass of topDataProperty or topObjectProperty respectively. Remove this subPropertyOf value to fix the reasoner-related issue.

