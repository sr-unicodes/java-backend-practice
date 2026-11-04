package com.fintech.jvm;

import java.util.*;

public class BrowserEngine {

    private Set<String> uniqueSites = new HashSet<>();
    private Map<String, String> pageCache = new HashMap<>();
    private Deque<String> historyStack = new ArrayDeque<>();

    public void visitPage(String url, String htmlContent) {

        uniqueSites.add(url);
        pageCache.put(url, htmlContent);
        historyStack.push(url);

        System.out.println("Visited: " + url);
    }

    public void goBack(){
        if (historyStack.isEmpty()){
            System.out.println("No history.");
            return;
        }

        historyStack.pop();

        if (historyStack.isEmpty()){
            System.out.println("Blank Tab.");
            return;
        }

        String prevUrl = historyStack.peek();
        pageCache.get(prevUrl);
        System.out.println("Loaded " + prevUrl + " : " + pageCache.get(prevUrl));
    }

    public static void main(String[] args){
        BrowserEngine engine = new BrowserEngine();

        engine.visitPage("google.com", "<html>Search Engine</html>");
        engine.visitPage("github.com", "<html>Code Repo</html>");
        engine.visitPage("leetcode.com", "<html>DSA Practice</html>");

        System.out.println("\n--- Clicking Back ---");

        engine.goBack();

        engine.goBack();
    }
}
