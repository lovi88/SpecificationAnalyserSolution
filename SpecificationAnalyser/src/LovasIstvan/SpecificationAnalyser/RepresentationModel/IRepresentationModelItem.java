package LovasIstvan.SpecificationAnalyser.RepresentationModel;

/**
 * Created on 2013.11.24.
 *
 * @author lovi88
 */
public interface IRepresentationModelItem {

    public long getUniqueID();

    public String getName();

    public void setName(String name);

    public String getDescription();

    public void setDescription(String description);

    public String getSpecificationText();

    public void setSpecificationText(String specificationText);

    public String getItemText();

    public void setItemText(String itemText);
}
