package com.inetpsa.serialisation.mapping;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.simpleframework.xml.convert.Converter;
import org.simpleframework.xml.convert.Registry;
import org.simpleframework.xml.strategy.Strategy;
import org.simpleframework.xml.strategy.TreeStrategy;
import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.strategy.Value;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.NodeMap;
import org.simpleframework.xml.stream.OutputNode;

public class RegistryStrategy implements Strategy {

	private static final Logger LOGGER = LogManager.getLogger();

	private final Registry registry;
	private final Strategy strategy;

	public RegistryStrategy(Registry registry) {
		this(registry, new TreeStrategy());
//		LOGGER.info(strategy);
		// return org.simpleframework.xml.strategy.TreeStrategy@18f20260
//		LOGGER.info(registry);
		// return null
	}

	public RegistryStrategy(Registry registry, Strategy strategy) {
		this.registry = registry;
		this.strategy = strategy;
	}

	public Value read(Type type, NodeMap<InputNode> node, Map map) throws Exception {
		Value value = strategy.read(type, node, map);
		

		if (isReference(value)) {
			return value;
		}
		return read(type, node, value);
	}

	private Value read(Type type, NodeMap<InputNode> node, Value value) throws Exception {
		Converter converter = lookup(type, value);
		InputNode source = node.getNode();

		if (converter != null) {
			Object data = converter.read(source);
			Class actual = type.getType();

			if (value != null) {
				value.setValue(data);
			}
//         return new Reference(value, data, actual);
		}
		return value;
	}

	public boolean write(Type type, Object value, NodeMap<OutputNode> node, Map map) throws Exception {
		boolean reference = strategy.write(type, value, node, map);

		if (!reference) {
			return write(type, value, node);
		}
		return reference;
	}

	private boolean write(Type type, Object value, NodeMap<OutputNode> node) throws Exception {
		Converter converter = lookup(type, value);
		OutputNode source = node.getNode();

		if (converter != null) {
			converter.write(source, value);
			return true;
		}
		return false;
	}

	private Converter lookup(Type type, Value value) throws Exception {
		Class real = type.getType();

		if (value != null) {
			real = value.getType();
		}
		return registry.lookup(real);
	}

	private Converter lookup(Type type, Object value) throws Exception {
		Class real = type.getType();

		if (value != null) {
			real = value.getClass();
		}
		return registry.lookup(real);
	}

	private boolean isReference(Value value) {
		return value != null && value.isReference();
	}
}
