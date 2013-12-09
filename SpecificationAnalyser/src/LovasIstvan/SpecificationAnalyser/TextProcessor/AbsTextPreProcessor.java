
package LovasIstvan.SpecificationAnalyser.TextProcessor;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;


/**
 *
 * @author lovi88
 */
public abstract class AbsTextPreProcessor implements ITextPreProcessor{

    String text;
    IXMLAnnotator annotator;
    
    protected List<String> sentences;
    List<String> tokens;
    
    public AbsTextPreProcessor( String text ) {
        this.text = text;
    }
    
    public AbsTextPreProcessor( String text, IXMLAnnotator annotator ) {
        this.text = text;
        this.annotator = annotator;
    }

    abstract protected void initAnnotator() throws ParserConfigurationException;
    
    @Override
    public String getXMLAnnotation(){
        if (annotator==null) {
            try {
                initAnnotator();
            } catch (ParserConfigurationException ex) {
               return "<error>"+ex.getMessage()+"</error>";
            }
        }
        
        return annotator.getAnnotatedXML();
    }

    @Override
    public String getText() {
        return text;
    }
    
    abstract protected EnumPOS getEnumPosFromPosString(String postString);
    
    public static AbsTextPreProcessor create(EnumSupportedLanguages language, String text) {
        
        switch(language){
            case English:
                return new StanfordTextPreProcessor(text);
            case Hungarian:
                return new MagyarLancTextPreProcessor(text);
            default: return null;
        }
        
    }
}
