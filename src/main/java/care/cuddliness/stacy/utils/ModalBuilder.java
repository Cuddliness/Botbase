package care.cuddliness.stacy.utils;

import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.ItemComponent;
import net.dv8tion.jda.api.interactions.components.text.TextInput;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;
import net.dv8tion.jda.api.interactions.modals.Modal;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ModalBuilder {
    private final List<ActionRow> input = new ArrayList<>();
    public ModalBuilder(){}

    public ModalBuilder addQuestion(String id, String name, String placeholder, TextInputStyle style){
        input.add(ActionRow.of(TextInput.create(id, name, style).setPlaceholder(placeholder).setMinLength(1).setMaxLength(300).build()));
        return this;
    }

    public Modal build(String id){
        return Modal.create(id, id).addComponents(input).build();

    }


}
