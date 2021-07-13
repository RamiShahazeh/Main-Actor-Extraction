package nlphw;
import java.io.*;
import java.util.*;

import edu.stanford.nlp.coref.CorefCoreAnnotations;

import edu.stanford.nlp.coref.data.CorefChain;
import edu.stanford.nlp.ie.util.RelationTriple;
import edu.stanford.nlp.io.*;
import edu.stanford.nlp.ling.*;
import edu.stanford.nlp.naturalli.NaturalLogicAnnotations;
import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.semgraph.SemanticGraph;
import edu.stanford.nlp.semgraph.SemanticGraphCoreAnnotations;
import edu.stanford.nlp.semgraph.SemanticGraphEdge;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.trees.*;
import edu.stanford.nlp.util.*;

/** This class demonstrates building and using a Stanford CoreNLP pipeline. */
public class NlpHW {

  private NlpHW() { } // static meain metho

  public static void main(String[] args) throws IOException {
    // Add in sentiment
    Properties props = new Properties();
  
    props.setProperty("annotators", "tokenize,ssplit,pos,lemma,depparse,natlog,openie");

    StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

    // Initialize an Annotation with some text to be annotated. The text is the argument to the constructor.
    Annotation annotation;
    annotation = new Annotation("Kosgi Santosh sent an email to Stanford University. He didn't get a reply.");

    // run all the selected Annotators on this text
    pipeline.annotate(annotation);
     /////Find the subject and object for each verb////////
       // Loop over sentences in the document
        List<CoreMap> sentences = annotation.get(CoreAnnotations.SentencesAnnotation.class);
        if (sentences != null && ! sentences.isEmpty()) {
//        
        
        for ( CoreMap triple_sentence : sentences) {
        
      // Get the OpenIE triples for the sentence
      Collection<RelationTriple> triples =
            triple_sentence.get(NaturalLogicAnnotations.RelationTriplesAnnotation.class);
      // Print the triples
      for (RelationTriple triple : triples) {
        System.out.println("Confidence: "+triple.confidence + "\t" +
            "Subject: "+triple.subjectGloss()/*subjectLemmaGloss()*/ + "\t" +
            "Verb: "+triple.relationGloss() /*relationLemmaGloss() */+ "\t" +
            "Object: "+triple.objectGloss()/*objectLemmaGloss()*/);
      }
    }
        }
  

//    // An Annotation is a Map with Class keys for the linguistic analysis types.
//    // You can get and use the various analyses individually.
//    // For instance, this gets the parse tree of the first sentence in the text.
//    List<CoreMap> sentences = annotation.get(CoreAnnotations.SentencesAnnotation.class);
//   
//    if (sentences != null && ! sentences.isEmpty()) {
//        
//              // Loop over sentences in the document
// 
//      CoreMap sentence = sentences.get(0);
//      //System.out.println("The first sentence is:");
//      //System.out.println(sentence.toShorterString());
//      //System.out.println();
//      System.out.println("The first sentence tokens are:");
//      for (CoreMap token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {
//    	  System.out.println(token.toShorterString());
//      }
//      Tree tree = sentence.get(TreeCoreAnnotations.TreeAnnotation.class);
//      System.out.println();
//      System.out.println("The first sentence parse tree is:");
//      tree.pennPrint();
//      System.out.println();
//      System.out.println("The first sentence basic dependencies are:");
//      System.out.println(sentence.get(SemanticGraphCoreAnnotations.BasicDependenciesAnnotation.class).toString(SemanticGraph.OutputFormat.LIST));
//      System.out.println("The first sentence collapsed, CC-processed dependencies are:");
//      SemanticGraph graph = sentence.get(SemanticGraphCoreAnnotations.CollapsedCCProcessedDependenciesAnnotation.class);
//      System.out.println(graph.toString(SemanticGraph.OutputFormat.LIST));
//
//      // Print out dependency structure around one word
//      // This give some idea of how to navigate the dependency structure in a SemanticGraph
//      IndexedWord node = graph.getNodeByIndexSafe(5);
//      // The below way also works
//      // IndexedWord node = new IndexedWord(sentences.get(0).get(CoreAnnotations.TokensAnnotation.class).get(5 - 1));
//      System.out.println("Printing dependencies around \"" + node.word() + "\" index " + node.index());
//      List<SemanticGraphEdge> edgeList = graph.getIncomingEdgesSorted(node);
//      if (! edgeList.isEmpty()) {
//        assert edgeList.size() == 1;
//        int head = edgeList.get(0).getGovernor().index();
//        String headWord = edgeList.get(0).getGovernor().word();
//        String deprel = edgeList.get(0).getRelation().toString();
//        System.out.println("Parent is word \"" + headWord + "\" index " + head + " via " + deprel);
//      } else  {
//    	  System.out.println("Parent is ROOT via root");
//      }
//      edgeList = graph.outgoingEdgeList(node);
//      for (SemanticGraphEdge edge : edgeList) {
//        String depWord = edge.getDependent().word();
//        int depIdx = edgeList.get(0).getDependent().index();
//        String deprel = edge.getRelation().toString();
//        System.out.println("Child is \"" + depWord + "\" (" + depIdx + ") via " + deprel);
//      }
//      System.out.println();
//
//
//      // Access coreference. In the coreference link graph,
//      // each chain stores a set of mentions that co-refer with each other,
//      // along with a method for getting the most representative mention.
//      // Both sentence and token offsets start at 1!
//      System.out.println("Coreference information");
//      Map<Integer, CorefChain> corefChains =
//          annotation.get(CorefCoreAnnotations.CorefChainAnnotation.class);
//      if (corefChains == null) { return; }
//      for (Map.Entry<Integer,CorefChain> entry: corefChains.entrySet()) {
//    	  System.out.println("Chain " + entry.getKey());
//        for (CorefChain.CorefMention m : entry.getValue().getMentionsInTextualOrder()) {
//          // We need to subtract one since the indices count from 1 but the Lists start from 0
//          List<CoreLabel> tokens = sentences.get(m.sentNum - 1).get(CoreAnnotations.TokensAnnotation.class);
//          // We subtract two for end: one for 0-based indexing, and one because we want last token of mention not one following.
//          System.out.println("  " + m + ", i.e., 0-based character offsets [" + tokens.get(m.startIndex - 1).beginPosition() +
//                  ", " + tokens.get(m.endIndex - 2).endPosition() + ')');
//        }
//      }
//      System.out.println();
//      
//      
//      
//
//
//    }
  }

}
