package com.smartInvoice.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.theokanning.openai.OpenAiService;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.completion.CompletionResult;

@Service
public class OpenAiServiceWrapper {

    private final OpenAiService openAiService;

    public OpenAiServiceWrapper(@Value("${openai.api.key}") String apiKey) {
        if (apiKey == null || apiKey.isEmpty()) {
            throw new IllegalStateException("OpenAI API key not found in configuration.");
        }
        this.openAiService = new OpenAiService(apiKey);
    }

    public String analyzeInvoice(String invoiceText) {
        CompletionRequest request = CompletionRequest.builder()
                .prompt(invoiceText)
                .maxTokens(100)
                .build();
        CompletionResult result = openAiService.createCompletion(request);
        return result.getChoices().get(0).getText();
    }
}
