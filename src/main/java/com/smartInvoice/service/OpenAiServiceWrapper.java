package com.smartInvoice.service;

import com.theokanning.openai.OpenAiService;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.completion.CompletionResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OpenAiServiceWrapper {

    private final OpenAiService openAiService;

    public OpenAiServiceWrapper(@Value("${openai.api.key}") String token) {
        this.openAiService = new OpenAiService(token);
    }

    public String analyzeInvoice(String invoiceText) {
        try {
            CompletionRequest request = CompletionRequest.builder()
                    .prompt(invoiceText)
                    .maxTokens(100)
                    .build();
            CompletionResult result = openAiService.createCompletion(request);
            return result.getChoices().get(0).getText();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to analyze invoice using OpenAI.", e);
        }
    }
}
