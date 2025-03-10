package io.github.qe7.features.impl.modules.impl.misc;

import io.github.qe7.events.packet.OutgoingPacketEvent;
import io.github.qe7.features.impl.modules.api.Module;
import io.github.qe7.features.impl.modules.api.ModuleCategory;
import me.zero.alpine.listener.Listener;
import me.zero.alpine.listener.Subscribe;
import net.minecraft.src.Packet3Chat;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class GrammarModule extends Module {

    private final HashMap<String, String> abbreviations = new HashMap<>();

    public GrammarModule() {
        super("Grammar", "\"Grammar nazi\" - Anu", ModuleCategory.MISC);

        abbreviations.put("wassup", "what's up");
        abbreviations.put("sup", "what's up");
        abbreviations.put("tf", "the fuck");
        abbreviations.put("u", "you");
        abbreviations.put("ur", "your");
        abbreviations.put("ure", "you are");
        abbreviations.put("r", "are");
        abbreviations.put("y", "why");
        abbreviations.put("l8r", "later");
        abbreviations.put("cya", "see you");
        abbreviations.put("ttyl", "talk to you later");
        abbreviations.put("brb", "be right back");
        abbreviations.put("idk", "I don't know");
        abbreviations.put("lol", "laugh out loud");
        abbreviations.put("rofl", "rolling on the floor laughing");
        abbreviations.put("lmao", "laughing my ass off");
        abbreviations.put("lmfao", "laughing my fucking ass off");
        abbreviations.put("omg", "oh my god");
        abbreviations.put("thx", "thanks");
        abbreviations.put("np", "no problem");
        abbreviations.put("jk", "just kidding");
        abbreviations.put("bbl", "be back later");
        abbreviations.put("afk", "away from keyboard");
        abbreviations.put("gtg", "got to go");
        abbreviations.put("imo", "in my opinion");
        abbreviations.put("smh", "shaking my head");
        abbreviations.put("fyi", "for your information");
        abbreviations.put("nvm", "never mind");
        abbreviations.put("tbh", "to be honest");
        abbreviations.put("ikr", "I know, right?");
        abbreviations.put("wyd", "what are you doing?");
        abbreviations.put("hmu", "hit me up");
        abbreviations.put("bff", "best friends forever");
        abbreviations.put("ffs", "for fuck's sake");
        abbreviations.put("asap", "as soon as possible");
        abbreviations.put("irl", "in real life");
        abbreviations.put("dm", "direct message");
        abbreviations.put("tmi", "too much information");
        abbreviations.put("btw", "by the way");
        abbreviations.put("gr8", "great");
        abbreviations.put("plz", "please");
        abbreviations.put("pls", "please");
        abbreviations.put("wth", "what the heck");
        abbreviations.put("wtf", "what the fuck");
        abbreviations.put("xoxo", "hugs and kisses");
        abbreviations.put("glhf", "good luck, have fun");
        abbreviations.put("gg", "good game");
        abbreviations.put("wp", "well played");
        abbreviations.put("ggwp", "good game, well played");
        abbreviations.put("ez", "easy");
        abbreviations.put("afaik", "as far as I know");
        abbreviations.put("omw", "on my way");
        abbreviations.put("m8", "mate");
        abbreviations.put("gh", "GameHerobrine");
        abbreviations.put("cliff", "shliff");
        abbreviations.put("wsndow0", "skidder");
        abbreviations.put("color", "colour");
        abbreviations.put("colorful", "colourful");
        abbreviations.put("armor", "armour");
        abbreviations.put("favorite", "favourite");
        abbreviations.put("neighbor", "neighbour");
        abbreviations.put("wat", "what");
        abbreviations.put("wut", "what");
        abbreviations.put("stfu", "shut the fuck up");
        abbreviations.put("kys", "keep yourself safe");
        abbreviations.put("nig", "nigga"); //N-Word pass by wsndow0
        abbreviations.put("fr", "for real");
        abbreviations.put("iirc", "if I recall correctly");
        abbreviations.put("wb", "welcome back");
        abbreviations.put("ong", "on god");
        abbreviations.put("icl", "I can't lie");
        abbreviations.put("ic", "I see");
        abbreviations.put("icr", "I can't remember");
        abbreviations.put("icm", "I can't make");
        abbreviations.put("icn", "I can't name");
        abbreviations.put("icb", "I can't believe");
        abbreviations.put("icd", "I can't describe");
        abbreviations.put("icf", "I can't forget");
        abbreviations.put("icg", "I can't guess");
        abbreviations.put("ich", "I can't help");
        abbreviations.put("icj", "I can't justify");
        abbreviations.put("ick", "I can't keep");
        abbreviations.put("icp", "I can't promise");
        abbreviations.put("ics", "I can't say");
        abbreviations.put("ict", "I can't tell");
        abbreviations.put("icv", "I can't understand");
        abbreviations.put("icw", "I can't wait");
        abbreviations.put("icx", "I can't explain");
        abbreviations.put("avg", "average");
        abbreviations.put("wdym", "what do you mean");
        abbreviations.put("rq", "real quick");
    }

    @Subscribe
    public final Listener<OutgoingPacketEvent> outgoingPacketEventListener = new Listener<>(event -> {
        if (event.getPacket() instanceof Packet3Chat) {
            Packet3Chat chatPacket = (Packet3Chat) event.getPacket();
            String message = chatPacket.message;

            for (String abbreviation : abbreviations.keySet()) {
                String regex = "(?i)\\b" + Pattern.quote(abbreviation) + "\\b";
                message = message.replaceAll(regex, Matcher.quoteReplacement(abbreviations.get(abbreviation)));
            }

            if (!message.matches("^[^a-zA-Z0-9].*") && !message.matches(".*[^a-zA-Z0-9]$")) {
                // Add a period at the end of the message if it doesn't start or end with a special character
                message += ".";
            }

            try {
                message = message.replaceFirst(message.charAt(0) + "", Character.toUpperCase(message.charAt(0)) + "");
            } catch(Exception e){
                //bals
            }
            //chat bugs osiris moment message.substring(0, 100); :fire: @shae
            chatPacket.message = message;
        }
    });
}
