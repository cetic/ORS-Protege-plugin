package org.protege.owl.codegeneration.pojo;

import org.protege.owl.codegeneration.WrappedIndividual;

import java.util.Collection;
import java.util.ArrayList;

public class WrappedIndividualPojo  {

    Collection<String> type; //=new ArrayList<String>();

        
     public WrappedIndividualPojo() {
     }


     String id;
     public String getId() {
         return id;
     }


     public void setId(String newId) {
         this.id=newId;
     }


     public Collection<String> getType(){
         return type;
     }

     public void setType(Collection<String> type){
         this.type=type;
     }
     


}

