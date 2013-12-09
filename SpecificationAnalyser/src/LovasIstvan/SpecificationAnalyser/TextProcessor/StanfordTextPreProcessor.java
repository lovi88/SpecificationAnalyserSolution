package LovasIstvan.SpecificationAnalyser.TextProcessor;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreAnnotations.LemmaAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.NamedEntityTagAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.PartOfSpeechAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TextAnnotation;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.util.ArrayMap;
import edu.stanford.nlp.util.CoreMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.xml.parsers.ParserConfigurationException;

/**
 *
 * @author lovi88
 */
public class StanfordTextPreProcessor extends AbsTextPreProcessor {

    Annotation document;
    StanfordCoreNLP pipeline;

    public StanfordTextPreProcessor(String text){
        super(text);

        initLib();
        initTokenCoreLabels();
    }

    @SuppressWarnings("OverridableMethodCallInConstructor")
    public StanfordTextPreProcessor(String text, IXMLAnnotator annotator) {
        super(text, annotator);

        initLib();
        initTokenCoreLabels();
    }

    private void initLib() {

        Properties props = new Properties();
        props.put("annotators", "tokenize, ssplit"); //, pos, lemma, ner, parse, dcoref

        pipeline = new StanfordCoreNLP(props);

        document = new Annotation(text);

        pipeline.annotate(document);
    }

    
    List<CoreMap> sentencesCoreMap;

    @Override
    public String[] getSentences() {
        if (sentences == null) {
            sentences = new ArrayList<>();

            sentencesCoreMap = document.get(CoreAnnotations.SentencesAnnotation.class);

            for (CoreMap sentence : sentencesCoreMap) {
                sentences.add(sentence.toString());
            }

        }

        return sentences.toArray(new String[0]);
    }

    
    List<CoreLabel> tokenCoreLabels;

    protected void initTokens() {
        
        tokens = new ArrayList<>();
        for (CoreLabel token : tokenCoreLabels) {
            tokens.add(token.get(TextAnnotation.class));
        }
    }

    private void initTokenCoreLabels() {
        tokenCoreLabels = document.get(CoreAnnotations.TokensAnnotation.class);
    }

    @Override
    public String[] getTokens() {
        
        if (tokens == null) {
            initTokens();
        }

        return tokens.toArray(new String[0]);
    }

    @Override
    protected EnumPOS getEnumPosFromPosString(String postString) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public EnumPOS getPosOfWord(String word) throws UncontainedWordException {
        return getEnumPosFromPosString(getPosStringOfWord(word));
    }

    @Override
    public String getPosStringOfWord(String word) throws UncontainedWordException {

        if (tokens == null) {
            initTokens();
        }

        int indexOf = tokens.indexOf(word);

        if (indexOf == -1) {
            throw new UncontainedWordException();
        }
        CoreLabel tLabel = tokenCoreLabels.get(indexOf);

        String postString =  tLabel.get(PartOfSpeechAnnotation.class);
        return postString;
    }

    @Override
    public Map<String, EnumPOS> getWordPosMap() {

        Map<String, EnumPOS> map = new ArrayMap<>();
                
        for (CoreLabel token : tokenCoreLabels) {
            String word = token.get(TextAnnotation.class);
            String pos = token.get(PartOfSpeechAnnotation.class);

            map.put(word, getEnumPosFromPosString(pos));
        }

        return map;
    }

    @Override
    public String getLemmaOfWord(String word) throws UncontainedWordException {
        if (tokens == null) {
            initTokens();
        }

        int indexOf = tokens.indexOf(word);

        if (indexOf == -1) {
            throw new UncontainedWordException();
        }
        CoreLabel tLabel = tokenCoreLabels.get(indexOf);
        
        return tLabel.get(LemmaAnnotation.class);
        
    }

    @Override
    public Map<String, String> getWordLemmaMap() {
        
        Map<String, String> map = new ArrayMap<>();
                
        for (CoreLabel token : tokenCoreLabels) {
            String word = token.get(TextAnnotation.class);
            String lemma = token.get(LemmaAnnotation.class);

            map.put(word, lemma);
        }

        return map;
    }

    @Override
    public String[] getNamedEntities() {
        
        List<String> ners = new ArrayList<>();
        
        for (CoreLabel coreLabel : tokenCoreLabels) {
            ners.add(coreLabel.get(NamedEntityTagAnnotation.class));
        }
        
        return ners.toArray(new String[0]);        
    }

    @Override
    protected void initAnnotator() throws ParserConfigurationException {
        annotator = new StanfordXMLAnnotator(this);
    }

}
