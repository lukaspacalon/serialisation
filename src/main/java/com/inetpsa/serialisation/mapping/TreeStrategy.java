package com.inetpsa.serialisation.mapping;

//
//import static org.simpleframework.xml.strategy.Name.LABEL;
//import static org.simpleframework.xml.strategy.Name.LENGTH;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.reflections.Reflections;
import org.reflections.scanners.FieldAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.simpleframework.xml.strategy.Strategy;
import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.strategy.Value;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.Node;
import org.simpleframework.xml.stream.NodeMap;
import org.simpleframework.xml.stream.OutputNode;

import com.inetpsa.serialisation.annotation.Alias;

import javassist.CannotCompileException;
import javassist.NotFoundException;
import javassist.tools.reflect.Loader;


public class TreeStrategy implements Strategy {
	
	private static final Logger LOGGER = LogManager.getLogger();

   private static String LABEL;
   private static String LENGTH;
   private final Loader loader;
   private final String length;
   private final String label;
   
   public TreeStrategy() throws CannotCompileException, NotFoundException {
      this(LABEL, LENGTH);  
      //LOGGER.info(loader); //null
      //javassist.tools.reflect.Loader@433ffad1
//      LOGGER.info(length); //null
//      LOGGER.info(label); //null
   }        

   public TreeStrategy(String label, String length) throws CannotCompileException, NotFoundException {
      this.loader = new Loader();
      this.length = length;
      this.label = label;    
   } 

   public Value read(Type type, NodeMap node, Map map) throws Exception {
      Class actual = readValue(type, node, node);
      Class expect = type.getType();
//      LOGGER.info(""); 
//      LOGGER.info("actual");
//      LOGGER.info(actual);
//      LOGGER.info("expect"); 
//      LOGGER.info(expect); 
//      LOGGER.info("type"); 
//      LOGGER.info(type); 
//      LOGGER.info("node"); 
//      LOGGER.info(node);
//      LOGGER.info("map"); 
//      LOGGER.info(map); 
      
      if(expect.isArray()) {
         return readArray(actual, node);   
      }
      if(expect != actual) {
//         return new ObjectValue(actual);
      }
      return null;
   }
 
   private Value readArray(Class type, NodeMap node) throws Exception {      
      Node entry = node.remove(length);
      int size = 0;
      //pas de reponse
      LOGGER.info(entry); 
      
      if(entry != null) {
         String value = entry.getValue();
         size = Integer.parseInt(value);
      }      
//      return new ArrayValue(type, size);
	return null;
   }
   
  
   private Class readValue(Type type, Object value, NodeMap<InputNode> node) throws Exception {      
      Node entry = node.remove(label); 
      Class actual2 = value.getClass();
      Class expect = type.getType();
      Class real = actual2;
//      LOGGER.info(""); 
//      LOGGER.info("entry");
//      LOGGER.info(entry); //null
//      LOGGER.info("actual2"); 
//      LOGGER.info(actual2); // class java.lang.String
//      LOGGER.info("expect"); 
//      LOGGER.info(expect); // class java.lang.String
//      LOGGER.info("real"); 
//      LOGGER.info(real.getAnnotation(Alias.class));
      
//      LOGGER.info("");
//      LOGGER.info("actual2.getAnnotation(Alias.class)"); 
//      LOGGER.info(actual2.getAnnotation(Alias.class));
//      LOGGER.info("expect.getAnnotation(Alias.class)");
//      LOGGER.info(expect.getAnnotation(Alias.class));
//      LOGGER.info("real.getAnnotation(Alias.class)");
//      LOGGER.info(real.getAnnotation(Alias.class));
      
//      //AJOUTER UN ATTRIBUT node.put
//      LOGGER.info(node); 
////      LOGGER.info(real.getName());
//      node.put("port", "80");
//      LOGGER.info(node); 
//      
//      //AJOUTER UN ELEMENT ???
//      LOGGER.info(node); 
//      LOGGER.info(node); 
//      
//      //AJOUTER UNE ANNOTATION ???
//      LOGGER.info(node); 
//      LOGGER.info(node); 
      
      Reflections reflections = new Reflections(new ConfigurationBuilder()
			     .setUrls(ClasspathHelper.forPackage("com.inetpsa.serialisation.model.configuration"))
			     .setScanners(new FieldAnnotationsScanner()));
		Set<Field> fields = reflections.getFieldsAnnotatedWith(Alias.class);
		for (Field field : fields) {
			if (field.getAnnotation(Alias.class) == null) {
				LOGGER.warn("@Alias annotation is not present");
			} else {
				InputNode parent = node.getNode();
//				LOGGER.info(field.getAnnotation(Alias.class).fr());
			      //AJOUTER et RETIRER UN ATTRIBUT node.put
				LOGGER.info("");
				LOGGER.info(parent);
				LOGGER.info("node"); 
			    LOGGER.info(node); 
			    node.put("fr", field.getAnnotation(Alias.class).fr());
			    node.put("en", field.getAnnotation(Alias.class).en());
			    // pas de setter 
			    LOGGER.info("node + put"); 
			    LOGGER.info(node); 
//			    node.remove("fr");
//			    LOGGER.info("node + remove"); 
//			    LOGGER.info(node); 
			      
//			    LOGGER.info(node.toString()); 
			    //{port=attribute port='80', fr=attribute fr='securite'}
			      
//			    LOGGER.info(node.get("fr").getName()); 
//			    //fr
//			    LOGGER.info(node.get("fr").getValue()); 
//			    //securite
			      
//			    LOGGER.info(node.get);
					
				  
			}	
		}
      
      if(expect.isArray()) {
         expect = expect.getComponentType();
      }
      if(entry != null) {
         String name = entry.getValue();
//         expect = loader.load(name);
      }    
      return expect;
   }       
 
   public boolean write(Type type, Object value, NodeMap node, Map map){
      Class actual = value.getClass();
      Class expect = type.getType();
      Class real = actual;
      
//      LOGGER.info(""); 
//      LOGGER.info("actual");
//      LOGGER.info(actual); //null
//      LOGGER.info("expect"); 
//      LOGGER.info(expect); // class java.lang.String
//      LOGGER.info("real"); 
//      LOGGER.info(real); // class java.lang.String
      
      if(actual.isArray()) {
         real = writeArray(expect, value, node);
      }
      if(actual != expect) {
         node.put(label, real.getName());
      }       
      return false;
   }
   
   private Class writeArray(Class field, Object value, NodeMap node){
      int size = Array.getLength(value);
      
    //pas de reponse
//    LOGGER.info(""); 
//    LOGGER.info("value");
//    LOGGER.info(value); 
//    LOGGER.info("field"); 
//    LOGGER.info(field);
//    LOGGER.info("length"); 
//    LOGGER.info(length); 
//    LOGGER.info("field.getComponentType()"); 
//    LOGGER.info(field.getComponentType()); 
      
      if(length != null) {       
         node.put(length, String.valueOf(size));
      }
      return field.getComponentType();
   }

}

