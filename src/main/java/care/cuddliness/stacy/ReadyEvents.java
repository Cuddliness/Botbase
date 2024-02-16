package care.cuddliness.stacy;

import care.cuddliness.stacy.entities.guild.StacyGuildModule;
import care.cuddliness.stacy.entities.guild.StacyGuild;
import care.cuddliness.stacy.entities.guild.StacyGuildId;
import care.cuddliness.stacy.entities.guild.StacyGuildRule;
import care.cuddliness.stacy.entities.user.StacyUser;
import care.cuddliness.stacy.repositories.*;
import care.cuddliness.stacy.utils.EmbedColor;
import care.cuddliness.stacy.utils.EmbedUtil;
import care.cuddliness.stacy.utils.Emoji;
import care.cuddliness.stacy.values.ChannelValues;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

import java.util.List;
import java.util.UUID;

public class ReadyEvents {


    public ReadyEvents(JDA jda, UserRepository userRepository, GuildRepositoryInterface guildRepositoryInterface,
                       GuildModulesRepository guildModulesRepository, GuildChannelsRepository guildChannelsRepository,
                       GuildRulesRepository guildRulesRepository) {
        for (Guild g : jda.getGuilds()) {

            //Check if guild exists in database
            if (guildRepositoryInterface.findById(g.getIdLong()) == null) {
                StacyGuild guild = new StacyGuild();
                guild.setId(g.getIdLong());
                guildRepositoryInterface.save(guild);

            }
            //Check if guild module entity is null
            if (guildModulesRepository.findById(g.getIdLong()) == null) {
                StacyGuildModule stacyGuildModule = new StacyGuildModule();
                stacyGuildModule.setGuildId(g.getIdLong());
                guildModulesRepository.save(stacyGuildModule);
            }

            //Check if rules is true
            if (guildModulesRepository.findById(g.getIdLong()).getRules()) {
                //Check if rules channel exists
                if (!(guildChannelsRepository.findByGuildIdAndName(g.getIdLong(), "rules") == null)) {
                    TextChannel welcomeChannel = ChannelValues.WELCOME.getChannel(guildChannelsRepository, g);

                    //deleting Message history
                    MessageHistory history = MessageHistory.getHistoryFromBeginning(welcomeChannel).complete();
                    List<Message> mess = history.getRetrievedHistory();


                    //If no message exists build one
                    if (mess.isEmpty()) {
                        //Build rules Embed
                        EmbedUtil embedUtil = new EmbedUtil(EmbedColor.PRIMARY);
                        for (StacyGuildRule rule : guildRulesRepository.findByGuildId(g.getIdLong())) {
                            StringBuilder title = new StringBuilder();
                            //Checks if rules contain an emoji
                            if (rule.getEmoji() != null)
                                title.append(new Emoji(jda).fromMarkdown(rule.getEmoji())).append(" ");
                            title.append(rule.getTitle());

                            embedUtil.addField(title.toString(), rule.getContent(), false);
                        }
                        embedUtil.setThumbnail(g.getIconUrl());
                       Message embed =  welcomeChannel.sendMessageEmbeds(embedUtil.build()).complete();
                        if (guildModulesRepository.findById(g.getIdLong()).getInterviews()) {
                            embed.editMessageEmbeds(embedUtil.build()).setActionRow(Button.success("start_interview", "Start interview").withEmoji
                                    (net.dv8tion.jda.api.entities.emoji.Emoji.fromUnicode("U+2B50"))).queue();
                        }
                    }
                    mess.forEach(message -> {
                        if (message.getMember().getUser().isBot()) {
                            //Edit message if it already exists
                            EmbedUtil embedUtil = new EmbedUtil(EmbedColor.PRIMARY);
                            for (StacyGuildRule rule : guildRulesRepository.findByGuildId(g.getIdLong())) {
                                StringBuilder title = new StringBuilder();
                                //Checks if rules contain an emoji
                                if (rule.getEmoji() != null)
                                    title.append(new Emoji(jda).fromMarkdown(rule.getEmoji())).append(" ");
                                title.append(rule.getTitle());
                                embedUtil.addField(title.toString(), rule.getContent(), false);
                            }
                            embedUtil.setThumbnail(g.getIconUrl());
                            Message m = message.editMessageEmbeds(embedUtil.build()).complete();
                            if (guildModulesRepository.findById(g.getIdLong()).getInterviews()) {
                                m.editMessageEmbeds(embedUtil.build()).setActionRow(Button.success("start_interview", "Start interview").withEmoji
                                        (net.dv8tion.jda.api.entities.emoji.Emoji.fromUnicode("U+2B50"))).queue();
                            }
                        } else {
                            message.delete().queue();
                        }
                    });
                }
            }

            // Dirty check if new members are available when possible downtime happened
            for (Member m : g.getMembers()) {
                if (!m.getUser().isBot() && !m.getUser().isSystem()) {
                    StacyUser stacyUser = userRepository.findByUserIdAndGuildIdGuildId(m.getUser().getIdLong(), g.getIdLong());
                    if (stacyUser == null) {
                        System.out.println("Found new user: " + m.getEffectiveName());
                        StacyUser user = new StacyUser();
                        user.setUserId(m.getIdLong());
                        user.setId(UUID.randomUUID());
                        StacyGuildId guildId = new StacyGuildId();
                        guildId.setGuildId(g.getIdLong());

                        user.setGuildId(guildId);
                        userRepository.save(user);
                    }
                }

            }
        }
    }
}
