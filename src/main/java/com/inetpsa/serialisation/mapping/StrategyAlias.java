package com.inetpsa.serialisation.mapping;

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
import org.simpleframework.xml.stream.NodeMap;
import org.simpleframework.xml.stream.OutputNode;
import org.springframework.util.StringUtils;

import com.inetpsa.serialisation.annotation.Alias;

/** StrategyAlias : Commentaries

 * LOGGER.info("");
 * setValue(Object value); !!!
 * Value value = StrategyAlias.read(type, node, map);
 * De XML en anglais aux objets java en français ou en anglais
 * InputNode parent = node.getNode();
 * 
 * LOGGER.info(parent);


 * LOGGER.info(parent.isRoot());
 * @return true if this is the root node within the document
 * Everything is false
 
 * .getPrefix .getPosition
 * @return null

 * LOGGER.info(parent.getPosition());
 * @return line 2 / line 3 / line 4 ...

 * LOGGER.info(parent.getAttributes());
 * @return this returns a map of attributes for the element

 * LOGGER.info(parent.isEmpty());
 * .getSource() .getParent() .getNext() .skip() .isEmpty()

 * LOGGER.info(parent.isElement());
 * @return this returns true if the node is an element node
 * Everything is true

 * LOGGER.info(type);
 * @return field

 * LOGGER.info(map);     * @param map this is used to maintain contextual information
 * @return org.simpleframework.xml.core.Session@ //?
 
 * LOGGER.info(map.replace(node, map));
 * @return null

 * Value value = RegestryStrategy.read(type, node1, map);
 * LOGGER.info(value);

 * LOGGER.info(node.getName());
 * node .getNode() .getParent() .getValue()
 * @return null

 * @param type this is the declared class for the field used
 * @param value this is the instance variable being serialized
 * @param node this is the node map used to represent the value
 * @param map this is used to maintain contextual information *An object that maps keys to values
 * @return
 * @throws Exception
 */
public class StrategyAlias implements Strategy {

	private static final Logger LOGGER = LogManager.getLogger();
	boolean flag=true;
	/** Read : Commentaries

	 * Type :contient ... le type de l'objet en cours de désarialisation ? (ex. pendant un junit, ce sera l'objet "Configuration")
	 * @param node
	 * @throws Exception
	 */
	@Override
	public Value read(Type type, NodeMap<InputNode> node, Map map) throws Exception {
		
//		LOGGER.info("");
//		LOGGER.info(node.getNode().getParent().getName()); // configuration
//		InputNode parent = node.getNode();
//		LOGGER.info(map.replace(node, map));
//		LOGGER.info(parent);
//		LOGGER.info("parent.getAttribute()");
//		LOGGER.info(parent.getAttribute("fr"));
//		LOGGER.info(parent.isRoot());
//		LOGGER.info(parent.getPosition());
//		LOGGER.info(parent.getAttributes());
//		LOGGER.info(parent.isEmpty());
//		LOGGER.info(parent.isElement());
//		LOGGER.info(type);
//		LOGGER.info(map);
////		LOGGER.info(map.put(K key, V value));
//		final Type type = new Type();
//		LOGGER.info(map.setValue(V value)); //Je n'arrive pas à mettre une valeur
////		LOGGER.info(map.setValue(parent.getValue());
//		LOGGER.info("");
//		final TreeStrategy testStrategy = new TreeStrategy();
//		final Value value = testStrategy.read(type, node, map);
//		LOGGER.info("value");
//		LOGGER.info(value);
//		LOGGER.info("value.getType()");
//		LOGGER.info(value.getType());
		
		try {
			if (flag == true) {
				String nameFileXml = (node.getNode().getParent().getName());
				nameFileXml = StringUtils.capitalize(nameFileXml);
				String packageName = "com.inetpsa.serialisation.model." + nameFileXml;
				LOGGER.info(packageName);
				setAttributeByAnnotationMethod(node, packageName);
				flag=false;
			} else {}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * setAttributeByAnnotationMethod : Commentaries


	 * We get only @Element names
	 * InputNode parent = node.getNode();
 
	 * ADD OR REMOVE ATTRIBUTE : node.put/.remove		
	 * LOGGER.info("");
	 * LOGGER.info(parent);
	
	 * LOGGER.info("node");
	 * LOGGER.info(node);
	 * node.put("fr", field.getAnnotation(Alias.class).fr());
	 * node.put("en", field.getAnnotation(Alias.class).en());
	 * LOGGER.info("node + put");
	 * LOGGER.info(node);

	 * LOGGER.info("FR");
	 * LOGGER.info(node.get("fr").getValue());
	 * LOGGER.info("EN");
	 * LOGGER.info(node.get("en").getValue());
	 * @param node
	 * @throws Exception
	 */
	public void setAttributeByAnnotationMethod(NodeMap<InputNode> node, String packageName) throws Exception {
		Reflections reflections = new Reflections(new ConfigurationBuilder()
				//.setUrls(ClasspathHelper.forPackage("com.inetpsa.serialisation.model.Configuration"))
				.setUrls(ClasspathHelper.forPackage(packageName))
				.setScanners(new FieldAnnotationsScanner()));
		Set<Field> fields = reflections.getFieldsAnnotatedWith(Alias.class);
		for (Field field : fields) {
			if (field.getAnnotation(Alias.class) == null) {
				LOGGER.warn("@Alias annotation is not present");
			} else {
				InputNode parent = node.getNode();	
//				LOGGER.info(value);
				LOGGER.info("");
				
				LOGGER.info("node");
				LOGGER.info(node);
				node.put("fr", field.getAnnotation(Alias.class).fr());
				node.put("en", field.getAnnotation(Alias.class).en());
				LOGGER.info("node + put");
				LOGGER.info(node);
//
				LOGGER.info("FR");
				LOGGER.info(node.get("fr").getValue());
				LOGGER.info("EN");
				LOGGER.info(node.get("en").getValue());
			}
		}
	}

	@Override
	public boolean write(Type type, Object value, NodeMap<OutputNode> node, Map map) throws Exception {
		return false;
	}

}
