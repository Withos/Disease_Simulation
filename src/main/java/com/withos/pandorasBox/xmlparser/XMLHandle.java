package com.withos.pandorasBox.xmlparser;

import com.withos.pandorasBox.caretaker.Memento;
import com.withos.pandorasBox.entities.Person;
import com.withos.pandorasBox.states.HealthyState;
import com.withos.pandorasBox.states.ISick;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

public class XMLHandle {

    private int counter = 0;

    public void saveXML(Memento memento){
        try {
            File f = new File("./states/state" + counter + ".xml");
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();

            Element population = doc.createElement("population");
            doc.appendChild(population);

            for(Person person : memento.getPopulation()) {

                Element personElement = doc.createElement("person");
                population.appendChild(personElement);
                Element x = doc.createElement("x");
                x.appendChild(doc.createTextNode(String.valueOf(person.getX())));
                personElement.appendChild(x);
                Element y = doc.createElement("y");
                y.appendChild(doc.createTextNode(String.valueOf(person.getY())));
                personElement.appendChild(y);
                Element state = doc.createElement("state");
                state.appendChild(doc.createTextNode(person.getState().getClass().getName()));
                personElement.appendChild(state);
                if(person.getState() instanceof ISick || person.getState() instanceof HealthyState){
                    Element counter = doc.createElement("timer");
                    if(person.getState() instanceof ISick){
                        int counterInt = ((ISick) person.getState()).getTimer();
                        counter.appendChild(doc.createTextNode(String.valueOf(counterInt)));
                    } else{
                        int counterInt = ((HealthyState) person.getState()).getTimer();
                        counter.appendChild(doc.createTextNode(String.valueOf(counterInt)));
                    }
                    state.appendChild(counter);
                }
            }

            StreamResult result = new StreamResult(f);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            transformer.transform(source, result);
        }
        catch (ParserConfigurationException | TransformerException pce) {
            pce.printStackTrace();
        }
        counter++;
    }

    public void fromXML(String path){
        path = "./states/" + path;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(path);
            doc.getDocumentElement().normalize();
            new XMLParse(doc).createPopulation();
        } catch (ParserConfigurationException | SAXException | IOException e) {
            System.out.println("Didn't found xml file");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
