package org.example;



import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws IOException {

        // read file with scanner
        File file = new File("leg.txt");
        Scanner s = new Scanner(file);

        // store content in a string
        StringBuilder fileContent = new StringBuilder();
        while (s.hasNextLine()) {
            fileContent.append(s.nextLine());
        }
        //exercise 1.a)
        // count sentences
        int sentenceCount = countSentences(fileContent.toString());

        System.out.println("Number of sentences: " + sentenceCount);

        s.close();

        String[] sentences = fileContent.toString().split("[.\\-;,]");

        displaySentenceLengthDistribution(sentences);

        /* exercise 2)
        directive to the opennlp library

        sentence model
        InputStream modelIn = new FileInputStream("apache-opennlp-2.3.1-bin");
        SentenceModel model = new SentenceModel(modelIn);
        SentenceDetectorME sentenceDetector = new SentenceDetectorME(model);

        //read the txt file
        //Path filePath = Paths.get("leg.txt");
        //String fileText = new String(Files.readAllBytes(filePath));

        String[] detectedSentences = sentenceDetector.sentDetect(fileContent);

        find the sentence similarity
        for (int i = 0; i < detectedSentences.length; i++) {
            for (int j = i + 1; j <detectedSentences.length; j++) {
                double similarity = LevenshteinDistance.similarity(detectedSentences[i], detectedSentences[j]);
                System.out.println("Similarity between sentence " + (i + 1) + " and sentence " + (j + 1) + ": " + similarity);
            }
        }

        modelIn.close();

         */

    }
    // exercise 1.b)
    // making a hashmap and taking the length by word frequency
    private static void displaySentenceLengthDistribution(String[] sentences) {
        Map<Integer, Integer> lengthDistribution  = new HashMap<>();

        for (String sentence : sentences) {
            int length = sentence.trim().split("\\s+").length;
            lengthDistribution.put(length, lengthDistribution.getOrDefault(length, 0) + 1);
        }

        System.out.println("Sentence Length Distribution:");

        for (Map.Entry<Integer, Integer> entry : lengthDistribution.entrySet()){
            System.out.println("Length: " + entry.getKey() + ", Frequency: " + entry.getValue());
        }
    }
    // exercise 1.a)
    private static int countSentences(String text) {

        //taking into consideration every symbol to break the sentences

        String[] detectedSentences = text.split("[\\.\\,\\-\\;]");

        //count the number of non-empty sentences
        int count = 0;
        for (String sentence : detectedSentences) {
            if (!sentence.trim().isEmpty()){
                count++;
            }
        }

        return count;
    }



}