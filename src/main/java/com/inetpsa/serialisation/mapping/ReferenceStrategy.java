package com.inetpsa.serialisation.mapping;


//import static org.simpleframework.xml.strategy.Name.LABEL;
//import static org.simpleframework.xml.strategy.Name.LENGTH;
//import static org.simpleframework.xml.strategy.Name.MARK;
//import static org.simpleframework.xml.strategy.Name.REFER;

import java.util.Collection;
import java.util.Map;

import org.simpleframework.xml.strategy.Strategy;
import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.strategy.Value;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.NodeMap;
import org.simpleframework.xml.stream.OutputNode;

public class ReferenceStrategy implements Strategy {
	/**
	 * This is used to maintain session state for writing the graph.
	 */
//	protected final PyramideWriteReferenceState write;

	/**
	 * This is used to maintain session state for reading the graph.
	 */
//	protected final PyramideReadState read;

	/**
	 * This is used to provide the names of the attributes to use.
	 */
//	protected final PyramideReferenceContrat contract;

	/**
	 * Constructor for the <code>CycleStrategy</code> object. This is
	 * used to create a strategy with default values. By default the
	 * values used are "id" and "reference". These values will be
	 * added to XML elements during the serialization process. And 
	 * will be used to deserialize the object cycles fully.
	 */
	public ReferenceStrategy() {
//		this(MARK, REFER);
	}

	/**
	 * Instantiates a new reference strategy.
	 *
	 * @param references the references
	 */
	public ReferenceStrategy(Collection<Class<? extends Object>> references) {
//		this(MARK, REFER,references);
	}

	/**
	 * Constructor for the <code>CycleStrategy</code> object. This is
	 * used to create a strategy with the specified attributes, which
	 * will be added to serialized XML elements. These attributes 
	 * are used to serialize the objects in such a way the cycles in
	 * the object graph can be deserialized and used fully. 
	 * 
	 * @param mark this is used to mark the identity of an object
	 * @param refer this is used to refer to an existing object
	 */
	public ReferenceStrategy(String mark, String refer) {
//		this(mark, refer, LABEL);      
	}

	/**
	 * Constructor for the <code>CycleStrategy</code> object. This is
	 * used to create a strategy with the specified attributes, which
	 * will be added to serialized XML elements. These attributes 
	 * are used to serialize the objects in such a way the cycles in
	 * the object graph can be deserialized and used fully. 
	 * 
	 * @param mark this is used to mark the identity of an object
	 * @param refer this is used to refer to an existing object
	 * @param label this is used to specify the class for the field
	 */   
	public ReferenceStrategy(String mark, String refer, String label){
//		this(mark, refer, label, LENGTH);
	}

	/**
	 * Constructor for the <code>CycleStrategy</code> object. This is
	 * used to create a strategy with the specified attributes, which
	 * will be added to serialized XML elements. These attributes 
	 * are used to serialize the objects in such a way the cycles in
	 * the object graph can be deserialized and used fully. 
	 * 
	 * @param mark this is used to mark the identity of an object
	 * @param refer this is used to refer to an existing object
	 * @param label this is used to specify the class for the field
	 * @param length this is the length attribute used for arrays
	 */   
	public ReferenceStrategy(String mark, String refer, String label, String length){
//		this.contract = new PyramideReferenceContrat(mark, refer, label, length);
//		this.write = new PyramideWriteReferenceState(contract);
//		this.read = new PyramideReadState(contract);
	} 

	/**
	 * Instantiates a new reference strategy.
	 *
	 * @param mark the mark
	 * @param refer the refer
	 * @param label the label
	 * @param length the length
	 * @param references the references
	 */
	public ReferenceStrategy(String mark, String refer, String label, String length,Collection<Class<? extends Object>> references){
//		this.contract = new PyramideReferenceContrat(mark, refer, label, length,references);
//		this.write = new PyramideWriteReferenceState(contract);
//		this.read = new PyramideReadState(contract);

	} 

	/**
	 * Instantiates a new reference strategy.
	 *
	 * @param mark the mark
	 * @param refer the refer
	 * @param references the references
	 */
	public ReferenceStrategy(String mark, String refer,
			Collection<Class<? extends Object>> references) {
//		this(mark, refer, LABEL,references);
	}

	/**
	 * Instantiates a new reference strategy.
	 *
	 * @param mark the mark
	 * @param refer the refer
	 * @param label the label
	 * @param references the references
	 */
	public ReferenceStrategy(String mark, String refer, String label,
			Collection<Class<? extends Object>> references) {
//		this(mark, refer, label, LENGTH,references);
	}

	/**
	 * This method is used to read an object from the specified node.
	 * In order to get the root type the field and node map are
	 * specified. The field represents the annotated method or field
	 * within the deserialized object. The node map is used to get
	 * the attributes used to describe the objects identity, or in
	 * the case of an existing object it contains an object reference.
	 *
	 * @param type the method or field in the deserialized object
	 * @param node this is the XML element attributes to read
	 * @param map this is the session map used for deserialization
	 * @return this returns an instance to insert into the object
	 * @throws Exception the exception
	 */
	public Value read(Type type, NodeMap node, Map map) throws Exception {
//		PyramideReadGraph graph = read.find(map);

//		if(graph != null) {
//			return graph.read(type, node);
//		}
		return null;
	}

	/**
	 * This is used to write the reference in to the XML element that 
	 * is to be written. This will either insert an object identity if
	 * the object has not previously been written, or, if the object
	 * has already been written in a previous element, this will write
	 * the reference to that object. This allows all cycles within the
	 * graph to be serialized so that they can be fully deserialized. 
	 * 
	 * @param type the type of the field or method in the object
	 * @param value this is the actual object that is to be written
	 * @param node this is the XML element attribute map to use
	 * @param map this is the session map used for the serialization
	 * 
	 * @return returns true if the object has been fully serialized
	 */
	public boolean write(Type type, Object value, NodeMap node, Map map){
//		PyramideWriteReferenceGraph graph = write.find(map);

//		if(graph != null) {
//			return graph.write(type, value, node);
//		}
		return false;
	}


}



