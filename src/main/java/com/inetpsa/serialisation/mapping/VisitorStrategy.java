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
import org.simpleframework.xml.strategy.TreeStrategy;
import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.strategy.Value;
import org.simpleframework.xml.strategy.Visitor;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.NodeMap;
import org.simpleframework.xml.stream.OutputNode;

import com.inetpsa.serialisation.annotation.Alias;

public class VisitorStrategy implements Strategy {
	
	private static final Logger LOGGER = LogManager.getLogger();

	private final Strategy strategy;
	private final Visitor visitor;

	public VisitorStrategy(Visitor visitor) {
		this(visitor, new TreeStrategy());
//		LOGGER.info(visitor);
		//return null
	}

	public VisitorStrategy(Visitor visitor, Strategy strategy) {
		this.strategy = strategy;
		this.visitor = visitor;
//		LOGGER.info(visitor);
		//return null
//		LOGGER.info(strategy);
		//return org.simpleframework.xml.strategy.TreeStrategy@21005f6c
	}

	public Value read(Type type, NodeMap<InputNode> node, Map map) throws Exception {
//		LOGGER.info(type);
		//ex return field 'keyStore' private java.lang.String com.inetpsa.serialisation.model.configuration.Configuration$Server$Security.keyStore
		InputNode parent = node.getNode();
//		LOGGER.info(parent.getAnnotation(Alias.class).fr());
		Reflections reflections = new Reflections(new ConfigurationBuilder()
			     .setUrls(ClasspathHelper.forPackage("com.inetpsa.serialisation.model.configuration"))
			     .setScanners(new FieldAnnotationsScanner()));
		Set<Field> fields = reflections.getFieldsAnnotatedWith(Alias.class);
		for (Field field : fields) {
			if (field.getAnnotation(Alias.class) == null) {
				LOGGER.warn("@Alias annotation is not present");
			} else {
				// How can I setName() to translate with .fr() or .en() annotation?
				LOGGER.info(field.getAnnotation(Alias.class).fr());
			}
		}
		if (visitor != null) {
			visitor.read(type, node);
		}
		return strategy.read(type, node, map);
	}

	public boolean write(Type type, Object value, NodeMap<OutputNode> node, Map map) throws Exception {
		boolean result = strategy.write(type, value, node, map);

		if (visitor != null) {
			visitor.write(type, node);
		}
		return result;
	}
	
	public static void main(String[] args) {
//		LOGGER.info(visitor);
	}
}