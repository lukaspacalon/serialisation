package com.inetpsa.serialisation.model.configuration;

import org.simpleframework.xml.Attribute;



import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import com.inetpsa.serialisation.annotation.Alias;
import com.inetpsa.serialisation.mapping.Mapping.IObjectTest;

@Alias(fr = "configuration", en = "configuration")
@Root(name = "configuration")
public class Configuration implements IObjectTest {
	
	
	@Alias(fr = "serveur", en = "server")
	@Root(name = "server")
	public static class Server {
		
		@Alias(fr = "port", en = "port")
		@Attribute(name = "port")
		private int port;

			
		public int getPort() {
			return port;
		}

		public void setPort(int port) {
			this.port = port;
		}
		
		@Alias(fr = "hebergeur", en = "host")
		@Element(name = "host")
		private String host;

		public String getHost() {
			return host;
		}

		public void setHost(String host) {
			this.host = host;
		}
		
		@Element(name = "client")
		private String client;
		
		public String getClient() {
			return client;
		}

		public void setClient(String client) {
			this.client = client;
		}

		@Alias(fr = "securite", en = "security")
		@Root(name = "security")
		public static class Security {

			@Alias(fr = "css", en = "ssl")
			@Attribute(name = "ssl")
			private boolean ssl;
			private Security security;

			public boolean isSsl() {
				return ssl;
			}

			public void setSsl(boolean ssl) {
				this.ssl = ssl;
			}
			
			@Alias(fr = "keyStore", en = "clef")
			@Element(name = "keyStore")
			private String keyStore;

			public String getKeyStore() {
				return keyStore;
			}

			public void setKeyStore(String keyStore) {
				this.keyStore = keyStore;
			}

		}

		@Alias(fr = "securite", en = "security")
		@Element
		private static Security security;

		public Security getSecurity() {
			return security;
		}

		public void setSecurity(Security security) {
			this.security = security;
		}
		
		

	}

	@Alias(fr = "serveur", en = "server")
	@Element
	private Server server;
	
	public Server getServer() {
		return server;
	}

	public void setServer(Server server) {
		this.server = server;
	}

	@Alias(fr = "libelle", en = "label")
	private String libelle;

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	@Alias(fr = "indice", en = "index")
	@Attribute(name = "id")
	private int index;

	public Configuration() {
		super();
	}

	public Configuration(String text, int index) {
		this.index = index;
	}

	@Alias(en = "getId")
	public int getId() {
		return index;
	}

}