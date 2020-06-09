package com.example.mail.config.flow_objects;

import com.example.mail.model.Ingredient;
import com.example.mail.model.Order;
import com.example.mail.model.Taco;
import com.google.common.base.Splitter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.similarity.LevenshteinDistance;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.mail.transformer.AbstractMailMessageTransformer;
import org.springframework.integration.support.AbstractIntegrationMessageBuilder;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Component;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component
@Slf4j
public class EmailOrderTransformer extends AbstractMailMessageTransformer<Order> {

    private final Map<String, Ingredient> ingredients;

    public EmailOrderTransformer(@Qualifier("ingredients") Map<String, Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    protected AbstractIntegrationMessageBuilder<Order> doTransform(Message message) {
        return MessageBuilder.withPayload(processMail(message).orElseThrow());
    }

    private Optional<Order> processMail(Message message) {
        try {
            if (message.getSubject().equals("TACO ORDER")) {
                return processOrderContent(getTextFromMail(message));
            }
        } catch (MessagingException | IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    private String getTextFromMail(Message message) throws MessagingException, IOException {
        if (message.isMimeType("text/plain")) {
            return message.getContent().toString();
        }
        if (message.isMimeType("multipart/*")) {
            return getTextFromMimeMultipart((MimeMultipart) message.getContent());
        }
        return "";
    }

    private String getTextFromMimeMultipart(MimeMultipart mimeMultipart) throws MessagingException, IOException  {
        StringBuilder result = new StringBuilder();
        int count = mimeMultipart.getCount();
        for (int i = 0; i < count; i++) {
            BodyPart bodyPart = mimeMultipart.getBodyPart(i);
            if (bodyPart.isMimeType("text/plain")) {
                result.append("\n").append(bodyPart.getContent());
                break;
            } else if (bodyPart.isMimeType("text/html")) {
                String html = (String) bodyPart.getContent();
                result.append("\n").append(Jsoup.parse(html).text());
            } else if (bodyPart.getContent() instanceof MimeMultipart) {
                result.append(getTextFromMimeMultipart((MimeMultipart)bodyPart.getContent()));
            }
        }
        return result.toString();
    }

    private Optional<Order> processOrderContent(String content) {
        log.info("RECEIVED CONTENT " + content);
        Pattern pattern = Pattern.compile("^((((\\w{1,10}\\s){1,2})(\\w{1,10}))|(\\w{1,10})): " +
                "((((\\w{1,12}\\s?){1,2},\\s?)+(\\w{1,12}\\s?){1,2})|" +
                "((\\w{1,12}\\s?){1,2}))$", Pattern.MULTILINE
        );
        Matcher matcher = pattern.matcher(content);
        List<Taco> tacos = new ArrayList<>();

        while (matcher.find()) {
            List<Ingredient> currentTacoIngredients = new ArrayList<>();
            List<String> ingredientNames = Splitter.on(", ").splitToList(matcher.group(7));

            ingredientNames.forEach(i -> currentTacoIngredients.add(findIngredientByName(i).orElse(null)));
            tacos.add(new Taco(matcher.group(1), currentTacoIngredients));
        }
        log.info("PROCEEDED TO ORDER " + new Order(tacos));

        //if finIngredientByName(String ingredientName) method doesnt find ingredient it returns optional, which we added to list as null if empty, and now filter
        tacos = tacos.stream().filter(Objects::nonNull).collect(Collectors.toList());
        if (tacos.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(new Order(tacos));
    }

    private Optional<Ingredient> findIngredientByName(String name) {
        log.info("received ingr " + name.toUpperCase());
        for (String existingIngrName : ingredients.keySet()) {
            String nameUpper = name.toUpperCase();
            String existingNameUpper = existingIngrName.toUpperCase();
            log.info("comparing to " + existingIngrName);
            if (LevenshteinDistance.getDefaultInstance().apply(nameUpper, existingNameUpper) < 3 ||
                    nameUpper.contains(existingNameUpper) ||
                    existingNameUpper.contains(nameUpper)) {
                return Optional.of(ingredients.get(existingIngrName));
            }
        }
        return Optional.empty();
    }
}
