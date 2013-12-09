
package LovasIstvan.SpecificationAnalyser.TextProcessor;

import java.util.Map;

/**
 * Created on 2013.12.08.
 * @author lovi88
 */


public interface ITextPreProcessor {
    String getText();
    
    String[] getSentences();
    String[] getTokens();
    
    EnumPOS getPosOfWord(String word) throws UncontainedWordException;
    String getPosStringOfWord(String word) throws UncontainedWordException;
    
    Map<String, EnumPOS> getWordPosMap();
    String getXMLAnnotation();
    
    String getLemmaOfWord(String word) throws UncontainedWordException;
    Map<String, String> getWordLemmaMap();
    
    String[] getNamedEntities();
    
}
