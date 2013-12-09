
package LovasIstvan.SpecificationAnalyser.RepresentationModel;

import javax.xml.bind.annotation.XmlIDREF;

/**
 *
 * @author lovi88
 */
public class RepresentationModelConnection {
    
    @XmlIDREF
    IRepresentationModelItem source;
    
    @XmlIDREF
    IRepresentationModelItem destination;
    
    
    long sourceID;
    long destinationID;
    
    
    ConnectionDirection direction;
    
    public IRepresentationModelItem getSource() {
        return source;
    }

    public void setSource(IRepresentationModelItem source) {
        this.source = source;
        this.sourceID = source.getUniqueID();
    }

    public IRepresentationModelItem getDestination() {
        return destination;
    }

    public void setDestination(IRepresentationModelItem destination) {
        this.destination = destination;
        this.destinationID = destination.getUniqueID();
    }

    public ConnectionDirection getDirection() {
        return direction;
    }

    public void setDirection(ConnectionDirection direction) {
        this.direction = direction;
    }
    
}
