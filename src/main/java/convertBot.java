import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class convertBot extends TelegramLongPollingBot {
    public String getBotToken() {
        return "6497291246:AAFgukMQBrYRTqXmwR4ip3zRwQ6fT_nrHCo";
    }

    public void onUpdateReceived(Update update) {

        String chatId = update.getMessage().getChatId().toString();
        String command = update.getMessage().getText();
        String text = update.getMessage().getReplyToMessage().getText();
        int messageLength = update.getMessage().getText().length();

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);

        if (command.equals("/convert")){
        sendMessage.setText(conversation(text));
        }

        try {
            this.execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    public String getBotUsername() {
        return "conversationforua_bot";
    }

    private String conversation(String text){
        String latin = "qwertyuiop[]asdfghjkl;'zxcvbnm,./";
        String cyrillic = "йцукенгшщзхїфівапролджєячсмитьбю";
        StringBuilder stringBuilder = new StringBuilder();

        for (char el : text.toCharArray()){
            int indexEl = latin.indexOf(Character.toLowerCase(el));
            if (indexEl != -1){
                if (!Character.isUpperCase(el)){
                    stringBuilder.append(cyrillic.toCharArray()[indexEl]);
                } else{
                    stringBuilder.append(Character.toUpperCase(cyrillic.toCharArray()[indexEl]));
                }
            }else {
                stringBuilder.append(el);
            }
        }

        return stringBuilder.toString();
    }
}
