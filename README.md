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

* generates java POJOs
* generates namespaceconfig.properties file

## 4 Build & Deploy

Build the plugin:

 >mvn clean install

Deploy it by copying the jar file into Protege's plugin directory 

 >cp target/code-generation-ORS-0.1.jar path/to/protege/installation/plugins/
 
## 5 Using the plugin


NOTE: Class names of POJOS must follow the java class name conventions and rules (for example cannot use reserved java names). If conventions and rules are not followed the POJOs will not be used correctly in the generated project or not at all! This is likely to affect the names used in XSD schema elements or OWL classes.

### 5.1 Parameters and Configuration



## 6 Known issues

1. When loading a new ontology on top of an already loaded one in the same window, the plugin will keep the values (namespace, package name, file directory etc)  of the previous ontology. This will cause a problem later in ORS if an incorrect namespace property is used. Edit the namespce textbox to make sure it has the correct value or for automatic refreshing of the values choose to open the new ontology in new window when prompted.
2. When generating the pojos using reasoner you may encounter an exception due to a data property or object property is subclass of topDataProperty or topObjectProperty respectively. Remove this subPropertyOf value.

