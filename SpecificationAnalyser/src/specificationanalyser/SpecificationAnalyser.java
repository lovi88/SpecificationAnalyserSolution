/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package specificationanalyser;

import LovasIstvan.SpecificationAnalyser.TextProcessor.*;
import javax.xml.parsers.ParserConfigurationException;

/**
 *
 * @author lovi88
 */
public class SpecificationAnalyser {

    /**
     * @param args the command line arguments
     */
    static void StanfordTest() throws ParserConfigurationException {
        String text_eng = "Students Students Students learn about the purposes and techniques of annotation by examining text closely and critically. They study sample annotations and identify the purposes annotation can serve. Students then practice annotation through a careful reading of a story excerpt, using specific guidelines and writing as many annotations as possible. Students then work in pairs to peer review their annotations, practice using footnotes and PowerPoint to present annotations, and reflect on how creating annotations can change a reader's perspective through personal connection with text.";

        ITextPreProcessor textPreProcessor = AbsTextPreProcessor.create(EnumSupportedLanguages.English, text_eng);

        String[] sentences;
        sentences = textPreProcessor.getSentences();

        for (String sentence : sentences) {
            System.out.println(sentence);
        }

        String[] tokens;
        tokens = textPreProcessor.getTokens();

        for (String token : tokens) {
            System.out.println(token);
        }

    }

    static void MagyarLancTest() {
        String text_hun = "Az iparágban régóta kering az a pletyka, miszerint a Sony a PlayStation 4-hez alapjaiban új fejlesztésű hangmotoron dolgozik, ami nagyságrendi előrelépést hozhat a hangzásvilág területén. A korábbi hírünk adatai zömében helyesnek bizonyultak, de a konkrétumokról csak most tudunk beszámolni, hiszen a japán cég megvárta a konzol startját, hogy felszabadultan beszélhessen a tervekről.";
    
        ITextPreProcessor textPreProcessor = AbsTextPreProcessor.create(EnumSupportedLanguages.Hungarian, text_hun);
        
        String[] sentences;
        sentences = textPreProcessor.getSentences();

        for (String sentence : sentences) {
            System.out.println(sentence);
        }

        String[] tokens;
        tokens = textPreProcessor.getTokens();

        for (String token : tokens) {
            System.out.println(token);
        }
    }

    public static void main(String[] args) {
        try {
            //StanfordTest();
            MagyarLancTest();

        } catch (Exception ex) {
            System.out.println("Ex: " + ex.getMessage());
        }
    }
}
