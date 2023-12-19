package care.cuddliness.stacy.utils;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class EmbedUtil {
    private EmbedColor color;
    private EmbedBuilder builder;

    public EmbedUtil(EmbedColor color){
        this.color = color;
        builder = new EmbedBuilder();
    }

    public EmbedUtil(){
        this.color = EmbedColor.PRIMARY;
        builder = new EmbedBuilder();
    }

    public EmbedUtil setTitle(@NotNull String title){
        builder.setTitle(title);
        return this;
    }

    public EmbedUtil setDescription(@NotNull String description){
        builder.setDescription(description);
        return this;
    }

    public EmbedUtil addField(String name, String value, boolean inline){
        builder.addField(name, value, inline);
        return this;
    }

    public EmbedUtil setThumbnail(@NotNull String url){
        builder.setThumbnail(url);
        return this;
    }

    public MessageEmbed build(){
        builder.setColor(Color.decode(color.getColor()));
        return builder.build();
    }


}
