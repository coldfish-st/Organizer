package organiser;

import static org.junit.Assert.*;

import org.junit.*;

/**
 * The class is to test converting methods in SimpleQuery.
 * @author Zhenge Jia 2015
 *
 */
public class TestExport {

	private String inXML;
	private String inXMLEX;
	private String inJSON;
	private String inJSONEX;
	
	@Before
	public void setup() {
		inXML = "aaaa";
		inXMLEX = "<string>" + inXML + "</string>";
		inJSON = "bbbb";
		inJSONEX = "{" +'"' +"string" + '"' +": " + '"' +inJSON + '"' + "}";
	}
	
	@Test
	public void testToXML() {
		
		assertEquals(SimpleQuery.toXmlTest(inXML), inXMLEX);
	}
	
	@Test
	public void testToJSON() {
		assertEquals(SimpleQuery.toJsonTest(inJSON), inJSONEX);
	}

}
