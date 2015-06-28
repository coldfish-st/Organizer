package organiser;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.json.simple.JSONObject;
import org.w3c.dom.Document;

/**
 * The class extends a JFileChooser which enable users to export info as file in xml, json and txt form in any area in PC.
 * @author Zhenge Jia 2015
 *
 */
public class ExportFrame {

	JFileChooser chooser;
	JDialog exFrame;
	@SuppressWarnings("unchecked")
        public ExportFrame() {

		JFileChooser chooser = new JFileChooser();
        	FileFilter xml = new FileNameExtensionFilter(".xml", "xml");
        	FileFilter txt = new FileNameExtensionFilter(".txt", "txt");
        	FileFilter json = new FileNameExtensionFilter(".json", "json");
        	chooser.setFileFilter(xml);
        	chooser.setFileFilter(txt);
        	chooser.setFileFilter(json);
        	int userSelection = chooser.showSaveDialog(chooser);
        	
        	if (userSelection == JFileChooser.APPROVE_OPTION) {
        		File fileToSave = chooser.getSelectedFile();
        		// For xml exporting and saving.
        		if (chooser.getFileFilter() == xml) {
        			File f_xml = fileToSave;
        			
        			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        			DocumentBuilder db;
        			try {
        				// Make the xml tree
        				db = dbf.newDocumentBuilder();
        				ByteArrayInputStream is = new ByteArrayInputStream(SimpleQuery.toXML().getBytes());
        				Document doc = db.parse(is);
        				

        				// Save the xml file.
        				TransformerFactory transformerFactory = TransformerFactory.newInstance();
        				Transformer transformer = transformerFactory.newTransformer();
        				DOMSource source = new DOMSource(doc);
        				f_xml.getName().endsWith(".xml");
        				StreamResult result = new StreamResult(f_xml);
        				transformer.transform(source, result);
        				
        				
        			} catch (Exception e) {
        				System.err.println("Problem saving " + "test.xml");
        			}
        		// For json exporting and saving.
        		} else if (chooser.getFileFilter() == json) {
        			// Make a json object.
        			File f_json = fileToSave;
        			JSONObject obj = new JSONObject();
        			obj.put("Export", SimpleQuery.toJSON());
        			FileWriter out;
        			try {
        				// Write data into json object.
        				out = new FileWriter(f_json);
        				obj.writeJSONString(out);
        				out.close();
        			} catch (IOException e) {
        				e.printStackTrace();
        			}
        		// For txt exporting and saving.
        		} else if (chooser.getFileFilter() == txt) {
        			File f_txt =fileToSave;
        			try {
        		                FileWriter out_txt = new FileWriter(f_txt);
        		                out_txt.write(SimpleQuery.toTXT());
        		                out_txt.close();
        	                } catch (IOException e) {
        		                // TODO Auto-generated catch block
        		                e.printStackTrace();
        	                }
        		}
        	}
        	
	}

}
