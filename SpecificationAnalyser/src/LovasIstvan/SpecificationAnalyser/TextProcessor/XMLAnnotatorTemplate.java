package LovasIstvan.SpecificationAnalyser.TextProcessor;

import com.jamesmurty.utils.XMLBuilder;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

/**
 *
 * @author lovi88
 */
public class XMLAnnotatorTemplate implements IXMLAnnotator {

    XMLBuilder builder;
    ITextPreProcessor textPreProcessor;

    public XMLAnnotatorTemplate(ITextPreProcessor textPreProcessor) throws ParserConfigurationException {
        this.textPreProcessor = textPreProcessor;
        builder = XMLBuilder.create("Annotations");
    }

    protected void addTokens() {
        XMLBuilder element = builder.e("tokens");

        for (String token : textPreProcessor.getTokens()) {
            element.e(token);
        }
    }

    protected void addLemmas() {
        XMLBuilder element = builder.e("lemmas");

        Map<String, String> wordLemmaMap = textPreProcessor.getWordLemmaMap();
        for (Map.Entry<String, String> entry : wordLemmaMap.entrySet()) {

            String word = entry.getKey();
            String lemma = entry.getValue();

            XMLBuilder part = element.e("lemma_entry");

            part.e("word").t(word);
            part.e("lemma").t(lemma);
        }
    }

    protected void addSentences() {
        XMLBuilder element = builder.e("sentences");

        for (String sentence : textPreProcessor.getSentences()) {
            element.e(sentence);
        }

    }

    protected void addNamedEntities() {
        XMLBuilder element = builder.e("named_entities");

        for (String en : textPreProcessor.getNamedEntities()) {
            element.e(en);
        }

    }

    protected void addPosTags() {
        XMLBuilder element = builder.e("part_of_speech");

        Map<String, EnumPOS> wordPosMap = textPreProcessor.getWordPosMap();
        for (Map.Entry<String, EnumPOS> entry : wordPosMap.entrySet()) {

            String word = entry.getKey();
            EnumPOS pos = entry.getValue();

            XMLBuilder part = element.e("pos_entry");

            part.e("word").t(word);
            part.e("pos").t(pos.name());
        }

    }

    protected String getStringRepresentation() {

        try {
            return builder.asString();
        } catch (TransformerException ex) {
            return builder.toString();
        }

    }

    @Override
    public String getAnnotatedXML() {
        addTokens();
        addSentences();
        addNamedEntities();
        addPosTags();


        return getStringRepresentation();
    }

    public static IXMLAnnotator createAnnotator(Class AnnottorType,ITextPreProcessor textPreProcessor) 
            throws NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalAccessException, IllegalArgumentException, InvocationTargetException 
    {   
        return (IXMLAnnotator)AnnottorType.getDeclaredConstructor(new Class[] {ITextPreProcessor.class}).newInstance(textPreProcessor);
    }

    public static IXMLAnnotator createStanfordAnnotator(ITextPreProcessor preProcessor) throws ParserConfigurationException {
        return new StanfordXMLAnnotator(preProcessor);
    }
    
    public static IXMLAnnotator createMagyarLancAnnotator(ITextPreProcessor preProcessor) throws ParserConfigurationException {
        return new MagyarLancXMLAnnotator(preProcessor);
    }
}
