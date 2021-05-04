package com.inetpsa.serialisation;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.strategy.Value;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.NodeMap;

//import com.inetpsa.serialisation.mapping.HashMap;
import com.inetpsa.serialisation.mapping.StrategyAlias;

class StrategyAliasTest {

	private static final Logger LOGGER = LogManager.getLogger();

	/** StrategyAlias : Commentaries

	 * 
	 * LOGGER.info("");
	 * setValue(Object value); !!!
	 * Value value = StrategyAlias.read(type, node, map);
	 * De XML en anglais aux objets java en français ou en anglais
	 * InputNode parent = node.getNode();
	 * 
	 * LOGGER.info(parent);
	 * @return true if this is the root node within the document

	 * LOGGER.info(parent.isRoot());
	 * Everything is false
	 * .getPrefix .getPosition return null
 
	 * LOGGER.info(parent.getPosition());
	 * return line 2 / line 3 / line 4 ...

	 * LOGGER.info(parent.getAttributes());
	 * @return this returns a map of attributes for the element

	 * LOGGER.info(parent.isEmpty());
	 * .getSource() .getParent() .getNext() .skip() .isEmpty()

	 * LOGGER.info(parent.isElement());
	 * @return this returns true if the node is an element node
	 * Everything is true

	 * LOGGER.info(type);
	 * @return field

	 * Return org.simpleframework.xml.core.Session@ //?
	 * LOGGER.info(map);
	 * 
	 * LOGGER.info(map.replace(node, map));
	 * Return null

	 * Value value = RegestryStrategy.read(type, node1, map);
	 * LOGGER.info(value);

	 * LOGGER.info(node.getName());
	 * node .getNode() .getParent() .getValue()
	 * return null;

	 * @param type
	 * @param node
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Test
	public static Value TestStrategy(Type type, NodeMap<InputNode> node, Map map) throws Exception {


//		final Type type = new Type();
		// type.setClass(Configuration.class);
//		final NodeMap<InputNode> node = new NodeMap<>();
//		node.addNode(new InputNode());
//		final Map map = new HashMap();
		final StrategyAlias testStrategy = new StrategyAlias();
		final Value value = testStrategy.read(type, node, map);
		LOGGER.info(value.getType());
//		assertEquals(value.getType(), "com.inetpsa.serialisation.model.configuration");
//		assertEquals(value.getValue(), "");
		return value;
	}
	
//	public static void main(String[] args, Type type, NodeMap<InputNode> node, Map map) throws Exception {
//		StrategyAlias testStrategy = new StrategyAlias();
//		final Value value = testStrategy.read(type, node, map);
//		LOGGER.info(value.getType());
//	}
	
//	@Test
//	public void setAttributeByAnnotationMethod(NodeMap<InputNode> node) throws Exception {
//
//		Reflections reflections = new Reflections(new ConfigurationBuilder()
//				.setUrls(ClasspathHelper.forPackage("com.inetpsa.serialisation.model.configuration"))
//				.setScanners(new FieldAnnotationsScanner()));
//		Set<Field> fields = reflections.getFieldsAnnotatedWith(Alias.class);
//		for (Field field : fields) {
//			if (field.getAnnotation(Alias.class) == null) {
//				LOGGER.warn("@Alias annotation is not present");
//			} else {
//				// We get only @Element names
//				InputNode parent = node.getNode();
//				// ADD OR REMOVE ATTRIBUTE : node.put/.remove
//				LOGGER.info("");
//				LOGGER.info(parent);
//				LOGGER.info("node");
//				LOGGER.info(node);
//				node.put("fr", field.getAnnotation(Alias.class).fr());
//				node.put("en", field.getAnnotation(Alias.class).en());
//				LOGGER.info("node + put");
//				LOGGER.info(node);
//				LOGGER.info("FR");
//				LOGGER.info(node.get("fr").getValue());
//				LOGGER.info("EN");
//				LOGGER.info(node.get("en").getValue());
//				
//				
//				// L'objet configuration
//				// trop bas niveau
////				try {
////					final String actualFrTraduction = field.getAnnotation(Alias.class).fr();
////					final String expectedTFrTraduction = node.get("fr").getValue();
////					assertEquals(actualFrTraduction, expectedTFrTraduction);
////					
////					final String actualEnTraduction = field.getAnnotation(Alias.class).en();
////					final String expectedTEnTraduction = node.get("en").getValue();
////					assertEquals(actualEnTraduction, expectedTEnTraduction);
////				} catch (NullPointerException e) {
////					LOGGER.error("The return value is null ");
////				}
//			}
//		}
//	}
//
//	public boolean write(Type type, Object value, NodeMap<OutputNode> node, Map map) throws Exception {
//		return false;
//	}

}
