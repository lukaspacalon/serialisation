package com.inetpsa.serialisation.mapping;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.convert.Registry;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.strategy.Strategy;
import org.simpleframework.xml.strategy.Visitor;

import com.inetpsa.serialisation.model.configuration.Configuration;

import javassist.CannotCompileException;
import javassist.NotFoundException;

public class Mapping {

	private static final Logger LOGGER = LogManager.getLogger();
	private Strategy strategy;
	private Serializer serializer;

	public Mapping() {
		super();
		init();
	}

	public void init() {
		LOGGER.info("Initialization of Mapping internal objects");
		strategy = new StrategyAlias();
		serializer = new Persister(strategy);
	}

	public interface IObjectTest {}

	public interface IObjectTestD {}

	public void writeObjectAsXML(final IObjectTest objectToBeSerialized, final String xmlOutputFileName) {
		try {
			serializer.write(objectToBeSerialized, new File(xmlOutputFileName));
		} catch (Exception e) {
			LOGGER.error("Can't serizalize content inside file [" + xmlOutputFileName + "]", e);
		}
	}

	final String xmlIntputFileName = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
			+ "<question id=\"1\">\n" + "	<answers>java is a programming language</answers>\n" + "	<id>101</id>\n"
			+ "	<postedby>lukas</postedby>\n" + "	<answers>\n"
			+ "		<answername>java is a plateform</answername>\n" + "		<id>102</id>\n"
			+ "		<postedby>john</postedby>\n" + "	</answers>\n"
			+ "	<questionname>What is java?</questionname>\n" + "</question>";

	public Configuration readObjectFromXML(final String xmlIntputFileName, final Configuration configuration) {
		try {
			serializer.read(configuration, new File(xmlIntputFileName));
		} catch (Exception e) {
			LOGGER.error("Can't deserialize content");
//			LOGGER.error("Can't deserialize content", e);
		}
		return configuration;

	}

}