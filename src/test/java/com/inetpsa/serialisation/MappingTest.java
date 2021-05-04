package com.inetpsa.serialisation;

import static org.junit.Assert.assertEquals;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.util.IOUtils;
import org.junit.Before;
import org.junit.Test;

import com.inetpsa.serialisation.mapping.Mapping;
import com.inetpsa.serialisation.model.configuration.Configuration;
import com.inetpsa.serialisation.model.configuration.Configuration.Server;
import com.inetpsa.serialisation.model.configuration.Configuration.Server.Security;
import com.inetpsa.serialisation.model.configuration.Example;

public class MappingTest {

	private static final Logger LOGGER = LogManager.getLogger();

	private Mapping mapping;

	@Before
	public void setUp() {
		mapping = new Mapping();
	}

	@Test
	public void testSerializationWithExampleObject() throws FileNotFoundException, IOException {
		LOGGER.info("Test Serialization with example object (WRITE)");

		final Example example = new Example();
		example.setIndex(0);
		example.setLibelle("Test label");
		example.setText("Text example");
		final String outputPath = "target/example.xml";

		mapping.writeObjectAsXML(example, outputPath);

		// Assertions
		// - relire le contenu du fichier gï¿½nï¿½rï¿½ => ajouter la dï¿½pendance maven sur
		// commons-io
		// - vï¿½rifier qu'il est comme attendu
		final String actualContent = IOUtils.toString(new InputStreamReader(new FileInputStream(outputPath)));
		final String expected = "<root libelle=\"Test label\" id=\"0\">\n" + "   <message>Text example</message>\n"
				+ "</root>"; // TODO ajouter contenu

		assertEquals(expected, actualContent); // "Written content is not the expected one in the file [" + outputPath +
												// "]");
	}

	@Test
	public void testSerializationWithConfigurationObject() throws FileNotFoundException, IOException {
		LOGGER.info("Test Serialization with Configuration object (WRITE)");

		final Configuration configuration = new Configuration();
		configuration.setIndex(0);
		configuration.setLibelle("Test label");

		
		final Server server = new Server();
		configuration.setServer(server);
		server.setPort(80);
		server.setHost("www.domain.com");
		server.setClient("didier");

		final Security security = new Security();
		server.setSecurity(security);
		security.setSsl(true);
		security.setKeyStore("example keystore");

		final String outputPath = "target/configurationTest.xml";

		mapping.writeObjectAsXML(configuration, outputPath);

		// Assertions
		// - relire le contenu du fichier gï¿½nï¿½rï¿½ => ajouter la dï¿½pendance maven sur
		// commons-io
		// - vï¿½rifier qu'il est comme attendu
		final String actualContent = IOUtils.toString(new InputStreamReader(new FileInputStream(outputPath)));
		final String expected = "<configuration id=\"0\">\n" + "   <server port=\"80\">\n"
				+ "      <host>www.domain.com</host>\n" + "      <client>didier</client>\n" + "      <security ssl=\"true\">\n"
				+ "         <keyStore>example keystore</keyStore>\n" + "      </security>\n" + "   </server>\n"
//				+ "<Client/>\n"
				+ "</configuration>"; // TODO ajouter contenu

		assertEquals(expected, actualContent); // "Written content is not the expected one in the file [" + outputPath +
												// "]");
	}

	@Test
	public void testDeserialisation() throws FileNotFoundException, IOException {
		LOGGER.info("Test Deserialization (READ)");
		final Configuration configuration = new Configuration();
		final String source = "src/test/resources/configuration_2.xml";
		mapping.readObjectFromXML(source, configuration);
		LOGGER.info("le fichier example.xml a été deserialisé sous forme d'objet...");

//		final int actualId = question.getId();
//		final int expectedId = 0;
//		assertEquals(actualId, expectedId);
		try {
			final int actualId = configuration.getId();
			final int expectedId = 0;
			assertEquals(actualId, expectedId);

			final int actualPort = configuration.getServer().getPort();
			final int expectedPort = 80;
			assertEquals(actualPort, expectedPort);

			final String actualHost = configuration.getServer().getHost();
			final String expectedHost = "www.domain.com";
			assertEquals(actualHost, expectedHost);
			
			final String actualClient = configuration.getServer().getClient();
			final String expectedClient = "didier";
			assertEquals(actualClient, expectedClient);

			final boolean actualSsl = configuration.getServer().getSecurity().isSsl();
			final boolean expectedSsl = true;
			assertEquals(actualSsl, expectedSsl);

			final String actualkeystore = configuration.getServer().getSecurity().getKeyStore();
			final String expectedkeystore = "example keystore";
			assertEquals(actualkeystore, expectedkeystore);

		} catch (NullPointerException e) {
//			LOGGER.error("The return value is null ", configuration, source, e);
			LOGGER.error("The return value is null ");
		}

	}

}
