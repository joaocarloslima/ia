package br.com.fiap.ia;

import java.util.ArrayList;
import java.util.List;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.websocket.server.PathParam;

@Controller
public class IaController {

    private ChatClient chat;
    private List<String> messages = new ArrayList<>();

    public IaController(ChatClient.Builder builder) {
        this.chat = builder.build();
        messages.add("Como posso ajudar?");
    }

    @GetMapping
    public String index(@PathParam("message") String message, Model model) {
        String response = chat
                            .prompt()
                            .user(message)
                            .call()
                            .content();

        messages.add(message);                            
        messages.add(response);                            

        model.addAttribute("messages", messages);
        return "index";
    }
    
}
