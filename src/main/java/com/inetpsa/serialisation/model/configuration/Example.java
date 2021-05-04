package com.inetpsa.serialisation.model.configuration;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import com.inetpsa.serialisation.annotation.Alias;
import com.inetpsa.serialisation.mapping.Mapping.IObjectTest;

@Root(name = "root")
public class Example implements IObjectTest {

	@Element(name = "message")
	private String text;

//   @Element(name="test")
//   private String test;

	@Attribute(name = "libelle")
	@Alias(en = "label")
	private String libelle;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

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

	@Attribute(name = "id")
	private int index;

	public Example() {
		super();
	}

	public Example(String text, int index) {
		this.text = text;
		this.index = index;
	}

	public String getMessage() {
		return text;
	}

	public int getId() {
		return index;
	}

}