
package LovasIstvan.SpecificationAnalyser.RepresentationModel;

import java.io.File;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.transform.stream.StreamSource;

/**
 *
 * @author lovi88
 */
@XmlRootElement(name = "RepresentationModel")
public class RepresentationModel {
    
    @XmlElementWrapper(name = "ModelItems")
    @XmlAnyElement
    List<IRepresentationModelItem> modelItems = new ArrayList<>();
    
    @XmlElementWrapper(name = "ModelConnections")
    @XmlElement(name="ModelConnection")
    List<RepresentationModelConnection> itemConnections = new ArrayList<>();
    
    
    static void SaveModelToRepresentationModelXML(){
    
    }
    
    static RepresentationModel GetModelFromRepresentationModelXML() throws JAXBException{
        
        JAXBContext jaxbContext = JAXBContext.newInstance(RepresentationModel.class);
        
        Unmarshaller u = jaxbContext.createUnmarshaller();
        JAXBElement<RepresentationModel> rootModel = u.unmarshal(new StreamSource(new File("foo.xml")),RepresentationModel.class);
        RepresentationModel ret = rootModel.getValue();
        
        return null;
    }
    
}
