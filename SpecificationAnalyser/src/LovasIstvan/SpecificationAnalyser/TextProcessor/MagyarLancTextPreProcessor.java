package LovasIstvan.SpecificationAnalyser.TextProcessor;

import edu.northwestern.at.utils.corpuslinguistics.tokenizer.WordTokenizer;
import java.util.Map;
import hu.u_szeged.magyarlanc.*;
import hu.u_szeged.splitter.HunSplitter;
import java.util.Set;
import javax.xml.parsers.ParserConfigurationException;

/**
 *
 * @author lovi88
 */
public class MagyarLancTextPreProcessor extends AbsTextPreProcessor {

    public MagyarLancTextPreProcessor(String text){
        super(text);

        initLib();
        
    }

    public MagyarLancTextPreProcessor(String text, IXMLAnnotator annotator) {
        super(text, annotator);

        initLib();
    }

    HunSplitter splitter;
    WordTokenizer tokenizer;

    String[][] sentenceAndTokensArr;

    private void initLib() {

        //Magyarlanc.;
        //ResourceHolder.
        splitter = new HunSplitter();
        tokenizer = splitter.getTokenizer();

        initSentencesAndTokens();
    }

    protected void initSentencesAndTokens() {
        sentenceAndTokensArr = splitter.splitToArray(text);

        for (String[] sent : sentenceAndTokensArr) {
            String s = "";

            for (String tok : sent) {
                tokens.add(tok);
                s += tok;
            }

            sentences.add(s);
        }
    }

    @Override
    public String[] getSentences() {

        return sentences.toArray(new String[0]);
    }

    @Override
    public String[] getTokens() {
        return tokens.toArray(new String[0]);
    }

    @Override
    public EnumPOS getPosOfWord(String word) {
        
        return getEnumPosFromPosString(getPosStringOfWord(word));
    }

    @Override
    public String getPosStringOfWord(String word) {
        Set<MorAna> morphologicalAnalyses = HunLemMor.getMorphologicalAnalyses(word);
        MorAna[] toArray = morphologicalAnalyses.toArray(new MorAna[0]);
        
        toArray[0].getMsd();
        
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Map<String, EnumPOS> getWordPosMap() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getLemmaOfWord(String word) throws UncontainedWordException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Map<String, String> getWordLemmaMap() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String[] getNamedEntities() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected EnumPOS getEnumPosFromPosString(String postString) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void initAnnotator() throws ParserConfigurationException {
        annotator=new MagyarLancXMLAnnotator(this);
    }

}
