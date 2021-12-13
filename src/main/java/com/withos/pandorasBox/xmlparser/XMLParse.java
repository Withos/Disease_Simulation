package com.withos.pandorasBox.xmlparser;

import com.withos.pandorasBox.entities.Person;
import com.withos.pandorasBox.entities.Population;
import com.withos.pandorasBox.entities.Simulation;
import com.withos.pandorasBox.states.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLParse {


    private Document data;

    public XMLParse(Document data) {
        this.data = data;
    }
    public void createPopulation() throws Exception {
        if (data == null) {
            throw new Exception("Data is null");
        }
        // get all <person>
        NodeList entries = data.getElementsByTagName("person");
        if(entries.getLength() == 0){
            throw new Exception("No tag <person>, can't create Population");
        }

        Population population = Simulation.getPeople();

        for(int i = 0; i < entries.getLength(); i++){
            Node entry = entries.item(i);
            Element element = (Element) entry;
            try{
                double x = Double.parseDouble(element.getElementsByTagName("x").item(0).getTextContent());
                double y = Double.parseDouble(element.getElementsByTagName("y").item(0).getTextContent());
                String state = element.getElementsByTagName("state").item(0).getTextContent();
                IState statePerson = new ImmuneState();
                if(state.contains("SymptomsState")){
                    statePerson = new SymptomsState();
                    int counter = Integer.parseInt(element.getElementsByTagName("timer").item(0).getTextContent());
                    ((SymptomsState) statePerson).setTimer(counter);
                } else if (state.contains("NoSymptomsState")){
                    statePerson = new NoSymptomsState();
                    int counter = Integer.parseInt(element.getElementsByTagName("timer").item(0).getTextContent());
                    ((NoSymptomsState) statePerson).setTimer(counter);
                } else if (state.contains("HealthyState")){
                    statePerson = new HealthyState();
                    int counter = Integer.parseInt(element.getElementsByTagName("timer").item(0).getTextContent());
                    ((HealthyState) statePerson).setTimer(counter);
                }

                Person person = new Person(x, y, statePerson);
                population.addPerson(person);
            }catch(NullPointerException e){
                System.out.println("Couldn't add the person");
            }
        }
    }
}
