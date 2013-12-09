package LovasIstvan.SpecificationAnalyser.RepresentationModel;

import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 *
 * @author lovi88
 */
public abstract class AbsRepresentationModelItem implements IRepresentationModelItem {

    @XmlID
    long UniqueID;
    String name;
    String description;
    String specificationText;
    String itemText;

    @Override
    public long getUniqueID() {
        return UniqueID;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getSpecificationText() {
        return specificationText;
    }

    @Override
    public void setSpecificationText(String specificationText) {
        this.specificationText = specificationText;
    }

    @Override
    public String getItemText() {
        return itemText;
    }

    @Override
    public void setItemText(String itemText) {
        this.itemText = itemText;
    }

    static class Adapter extends XmlAdapter<AbsRepresentationModelItem, IRepresentationModelItem> {

        @Override
        public IRepresentationModelItem unmarshal(AbsRepresentationModelItem v) {
            return v;
        }

        @Override
        public AbsRepresentationModelItem marshal(IRepresentationModelItem v) {
            return (AbsRepresentationModelItem) v;
        }
    }
}
