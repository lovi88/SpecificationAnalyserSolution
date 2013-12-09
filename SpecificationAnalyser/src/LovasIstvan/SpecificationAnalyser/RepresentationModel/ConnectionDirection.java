/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package LovasIstvan.SpecificationAnalyser.RepresentationModel;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author lovi88
 */

@XmlType(name = "ConnectionDirection")
@XmlEnum
public enum ConnectionDirection {
    Undefinied,
    BiDirectional,
    SourceToDestination,
    DestinationToSource
}
