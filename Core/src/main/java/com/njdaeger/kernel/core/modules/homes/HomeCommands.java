package com.njdaeger.kernel.core.modules.homes;

import com.coalesce.core.SenderType;
import com.coalesce.core.command.annotation.Alias;
import com.coalesce.core.command.annotation.Command;
import com.coalesce.core.command.annotation.Completion;
import com.coalesce.core.command.annotation.Permission;
import com.coalesce.core.command.annotation.Sender;
import com.coalesce.core.command.base.CommandContext;
import com.coalesce.core.command.base.TabContext;
import com.njdaeger.kernel.core.Kernel;
import com.njdaeger.kernel.core.configuration.homes.IHome;
import com.njdaeger.kernel.core.configuration.homes.IOfflineHome;
import com.njdaeger.kernel.core.session.OfflineUser;
import com.njdaeger.kernel.core.session.User;

import java.util.HashSet;
import java.util.Set;

import static com.coalesce.core.Color.GREEN;
import static com.coalesce.core.Color.SILVER;
import static com.njdaeger.kernel.core.Messages.*;
import static com.njdaeger.kernel.core.Permission.*;

@SuppressWarnings( "unused" )
public final class HomeCommands {
    
    /*
    TODO:
    
    - Add a space after each home in listhomes
    - Send player messages when one user tps another user to another home.
    - Check if the user has any homes at all before listing homes
    - Send messages when homes were created
    - Sends a user does not exist message when /home <u:user> <home> is ran.
    - Add tab completions for homes, and delhome.
    
     */
    //@SuppressWarnings("all")
    @Permission( KERNEL_HOME )
    @Sender( SenderType.PLAYER )
    @Alias( {"gohome", "tphome"} )
    @Command( name = "home",
              desc = "Teleport to a home",
              usage = "/home <home> [user] || /home <u:user> [<home>,<user:home>,<u:home>]",
              max = 2,
              min = 1 )
    public void home(CommandContext context) {
        
        User user;
        User user1;
        OfflineUser offlineUser;
        String firstArg = context.argAt(0);
        String secondArg = context.argAt(1);
        
        if (context.isLength(1)) {

            if (!context.hasPermission(KERNEL_HOME)) {
                context.noPermission(KERNEL_HOME);
                return;
            }
            
            user = Kernel.getUser(context);
            IHome home = (IHome)user.getHome(firstArg);
            
            if (home == null) {
                context.pluginMessage(HOME_NOT_EXIST.toString(), firstArg);
                return;
            }
            context.pluginMessage(USER_SENT_HOME.toString(), home.getName());
            home.sendHere();
            return;
        }
        
        if (firstArg.startsWith("u:")) {
            
            String[] firstSplit = firstArg.split(":");
            user = Kernel.getUser(firstSplit[1]);
    
            if (firstSplit[1] == null) {
                context.pluginMessage(CANNOT_RESOLVE_ARG.toString(), "null");
                return;
            }
            
            if (user == null) {
                
                offlineUser = Kernel.getOfflineUser(firstSplit[1]);
                
                if (offlineUser == null) {
                    context.pluginMessage(USER_NOT_EXIST.toString(), firstSplit[1]);
                    return;
                }
                context.pluginMessage(NOT_YET_SUPPORTED.toString(), "Moving offline players");
                return;
            }
            
            if (secondArg.contains(":")) {
    
                String[] secondSplit = secondArg.split(":");
                
                if (secondSplit[0].equalsIgnoreCase("u")) {
                    IHome home = (IHome)user.getHome(secondArg.split(":")[1]);
                    if (home == null) {
                        context.pluginMessage(HOME_NOT_EXIST.toString(), context.argAt(0));
                        return;
                    }
                    user.pluginMessage(USER_SENT_HOME.toString(), home.getName());
                    context.pluginMessage(USER_SENT_OTHER_HOME_SENDER.toString(), user.getName(), home.getName());
                    home.sendHere();
                    return;
                }
                
                user1 = Kernel.getUser(secondSplit[0]);

                if (user1 == null) {
                    context.pluginMessage(USER_NOT_EXIST.toString(), secondSplit[0]);
                    return;
                }
                
                IHome home = (IHome)user1.getHome(secondSplit[1]);
                
                if (home == null) {
                    context.pluginMessage(HOME_NOT_EXIST.toString(), secondArg);
                    return;
                }
                user.pluginMessage(USER_SENT_OTHER_HOME.toString(), home.getOwner().getName(), home.getName());
                context.pluginMessage(USER_SENT_OTHER_HOME_SENDER.toString(), user.getName(), home.getName());
                home.sendOtherHere(user);
                return;
                
            }
            
            user1 = Kernel.getUser(context);
            
            IHome home = (IHome)user.getHome(secondArg);
            if (home == null) {
                context.pluginMessage(HOME_NOT_EXIST.toString(), secondArg);
                return;
            }
            user1.pluginMessage(USER_SENT_OTHER_HOME.toString(), user1.getName(), home.getName());
            context.pluginMessage(USER_SENT_OTHER_HOME_SENDER.toString(), context.getSender().getName(), home.getName());
            home.sendOtherHere(user1);
            
        }
        
        user = Kernel.getUser(secondArg);
        user1 = Kernel.getUser(context);
        IHome home = (IHome)user1.getHome(firstArg);
        
        if (user == null) {
            context.pluginMessage(USER_NOT_EXIST.toString(), secondArg);
            return;
        }
        
        if (home == null) {
            context.pluginMessage(HOME_NOT_EXIST.toString(), firstArg);
            return;
        }
        context.pluginMessage(USER_SENT_OTHER_HOME_SENDER.toString(), user.getName(), home.getName());
        user.pluginMessage(USER_SENT_OTHER_HOME.toString(), user1.getName(), home.getName());
        home.sendOtherHere(user);
    }
    
    @Completion( "home" )
    public void homeTab(TabContext context) {
        Set<String> completions = new HashSet<>();
        if (context.getLength() == 0) {
            if (context.hasAnyPermission(KERNEL_HOME_ME_OTHER, KERNEL_HOME_OTHER_OWN, KERNEL_HOME_OTHER_OTHER)) {
                Kernel.getUsers().stream().map(User::getName).forEach(n -> completions.add("u:" + n));
            }
            if (context.hasPermission(KERNEL_HOME)) {
                Kernel.getUser(context.getSender().getName()).getHomes().stream().map(IOfflineHome::getName).forEach(completions::add);
            }
        }
        if (context.getLength() == 1) {
            if (context.getPrevious().startsWith("u:")) {
                User user = Kernel.getUser(context.getPrevious().split(":")[1]);
                if (user == null) {
                    return;
                }
                if (context.getSender().hasPermission(KERNEL_HOME_ME_OTHER)) {
                    user.getHomes().stream().map(IOfflineHome::getName).forEach(completions::add);
                }
                if (context.hasPermission(KERNEL_HOME_OTHER_OWN)) {
                    user.getHomes().stream().map(IOfflineHome::getName).forEach(n -> completions.add("u:" + n));
                }
                if (context.hasPermission(KERNEL_HOME_OTHER_OTHER)) {
                    Kernel.getUsers().stream().map(User::getHomes).forEach(homes -> homes.forEach(home -> completions.add(home.getOwner().getName() + ":" + home.getName())));
                }
            } else {
                if (context.hasPermission(KERNEL_HOME_OTHER_ME)) {
                    context.playerCompletion(1);
                    return;
                }
            }
        }
        context.completion(completions.toArray(new String[0]));
    }
    
    //
    //
    //
    //
    //
    
    @Alias( "homes" )
    @Permission( KERNEL_LISTHOMES )
    @Command( name = "listhomes",
              desc = "Lists the homes of a user",
              usage = "/listhomes [user]",
              max = 1 )
    public void listHomes(CommandContext context) {
        
        User user;
        OfflineUser offlineUser = null;
        StringBuilder builder = new StringBuilder();
        
        if (context.isLength(1)) {
            user = Kernel.getUser(context.argAt(0));
            if (user == null) {
                if (!context.hasPermission(KERNEL_LISTHOMES_OFFLINE)) {
                    context.noPermission(KERNEL_LISTHOMES_OFFLINE);
                    return;
                }
                offlineUser = Kernel.getOfflineUser(context.argAt(0));
                if (offlineUser == null) {
                    context.pluginMessage(USER_NOT_EXIST.toString(), context.argAt(0));
                    return;
                }
                if (!offlineUser.getHomes().isEmpty()) offlineUser.getHomes().stream().map(IOfflineHome::getName).forEach(n -> builder.append(GREEN).append(n).append(SILVER).append(", "));
            } else {
                if (!context.hasPermission(KERNEL_LISTHOMES_OTHER)) {
                    context.noPermission(KERNEL_LISTHOMES_OTHER);
                    return;
                }
                if (!user.getHomes().isEmpty()) user.getHomes().stream().map(IOfflineHome::getName).forEach(n -> builder.append(GREEN).append(n).append(SILVER).append(", "));
            }
        }
        else {
            user = Kernel.getUser(context);
            if (context.getSender().getType() == SenderType.PLAYER && !user.getHomes().isEmpty()) user.getHomes().stream().map(IOfflineHome::getName).forEach(n -> builder.append(GREEN).append(n).append(SILVER).append(", "));
        }
        if (builder.lastIndexOf(",") < 0) builder.deleteCharAt(builder.lastIndexOf(","));
        context.pluginMessage(HOME_LIST.toString(), offlineUser == null ? user.getName() : offlineUser.getName(), builder.toString().trim());
    }
    
    //
    //
    //
    //
    //
    
    @Alias( "removehome" )
    @Permission( KERNEL_DELHOME )
    @Command( name = "delhome",
              desc = "Removes a home from a user",
              usage = "/delhome <home> || /delhome <user> <home>",
              min = 1,
              max = 2 )
    public void deleteHome(CommandContext context) {
        
        if (context.subCommand(SenderType.CONSOLE, this::deleteHomeConsole)) return;
        
        User user;
        OfflineUser offlineUser;
        String firstArg = context.argAt(0);
        String secondArg = context.argAt(1);
        
        if (context.isLength(1)) {
            
            if (!context.hasPermission(KERNEL_DELHOME)) {
                context.noPermission(KERNEL_DELHOME);
                return;
            }
            
            user = Kernel.getUser(context);
            
            IHome home = (IHome) user.getHome(firstArg);
            if (home == null) {
                context.pluginMessage(HOME_EXISTS.toString(), firstArg);
                return;
            }
            user.removeHome(home);
            user.pluginMessage(HOME_DELETED.toString(), home.getName());
            return;
        }
        
        user = Kernel.getUser(firstArg);
    
        if (user == null) {
            
            if (!context.hasPermission(KERNEL_DELHOME_OFFLINE)) {
                context.noPermission(KERNEL_DELHOME_OFFLINE);
                return;
            }
            
            offlineUser = Kernel.getUser(firstArg);
            
            if (offlineUser == null) {
                context.pluginMessage(USER_NOT_EXIST.toString(), firstArg);
                return;
            }
    
            IOfflineHome home = offlineUser.getHome(secondArg);
            
            if (home == null) {
                context.pluginMessage(HOME_NOT_EXIST.toString(), secondArg);
                return;
            }
            offlineUser.removeHome(home);
            context.pluginMessage(HOME_DELETED.toString(), home.getName(), offlineUser.getName());
            return;
        }
        
        IHome home = (IHome)user.getHome(secondArg);
        
        if (!context.hasPermission(KERNEL_DELHOME_OTHER)) {
            context.noPermission(KERNEL_DELHOME_OTHER);
            return;
        }
        
        if (home == null) {
            context.pluginMessage(HOME_NOT_EXIST.toString(), secondArg);
            return;
        }
        user.removeHome(home);
        context.pluginMessage(HOME_DELETED_OTHER.toString(), home.getName(), user.getName());
        user.pluginMessage(HOME_DELETED.toString(), home.getName());
    }
    
    private void deleteHomeConsole(CommandContext context) {
        User user;
        OfflineUser offlineUser;
        if (context.isLength(1)) {
            context.notEnoughArgs(2, 1);
            return;
        }
        user = Kernel.getUser(context.argAt(0));
        if (user == null) {
            offlineUser = Kernel.getOfflineUser(context.argAt(0));
            if (offlineUser == null) {
                context.pluginMessage(USER_NOT_EXIST.toString(), context.argAt(0));
                return;
            }
            if (offlineUser.getHome(context.argAt(1)) == null) {
                context.pluginMessage(HOME_NOT_EXIST.toString(), context.argAt(1));
                return;
            }
            offlineUser.removeHome(context.argAt(1));
            context.pluginMessage(HOME_DELETED_OTHER.toString(), context.argAt(1), context.argAt(0));
            return;
            
        }
        if (user.getHome(context.argAt(1)) == null) {
            context.pluginMessage(HOME_NOT_EXIST.toString(), context.argAt(1));
            return;
        }
        user.removeHome(context.argAt(1));
        user.pluginMessage(HOME_DELETED.toString(), context.argAt(1));
        context.pluginMessage(HOME_DELETED_OTHER.toString(), context.argAt(1), context.argAt(0));
        
    }
    
    //
    //
    //
    //
    //
    
    @Completion( "sethome" )
    public void createHomeTab(TabContext context) {
        if (context.hasPermission(KERNEL_SETHOME_OTHER) && context.length(1)) {
            context.playerCompletion(1);
        }
    }
    
    @Sender( SenderType.PLAYER )
    @Permission( KERNEL_SETHOME )
    @Alias( {"createhome", "newhome", "addhome"} )
    @Command( name = "sethome",
              desc = "Creates a new home at the user location",
              usage = "/sethome <name> [user]",
              min = 1,
              max = 2 )
    public void createHome(CommandContext context) {
        
        User user;
        OfflineUser offlineUser;
        
        String firstArg = context.argAt(0);
        String secondArg = context.argAt(1);
        
        if (context.isLength(1)) {
            
            if (!context.hasPermission(KERNEL_SETHOME)) {
                context.noPermission(KERNEL_SETHOME);
                return;
            }
            
            user = Kernel.getUser(context);
            
            if (user.getHome(firstArg) == null) {
                user.addHome(firstArg);
                user.pluginMessage(HOME_CREATED.toString(), firstArg, user.getName());
                return;
            }
            user.pluginMessage(HOME_EXISTS.toString(), firstArg);
            return;
        }
        
        user = Kernel.getUser(secondArg);
        
        //Basically checks if the user is online or not. if the user variable is still null then the player is probably offline
        if (user == null) {
            
            if (!context.hasPermission(KERNEL_SETHOME_OFFLINE)) {
                context.noPermission(KERNEL_SETHOME_OFFLINE);
                return;
            }
            
            user = Kernel.getUser(context);
            offlineUser = Kernel.getOfflineUser(context.argAt(1));
            
            if (offlineUser == null) {
                context.pluginMessage(USER_NOT_EXIST.toString(), context.argAt(1));
                return;
            }

            if (offlineUser.getHome(context.argAt(0)) == null) {
                offlineUser.addHome(user.getLocation(), context.argAt(0));
                return;
            }
            context.pluginMessage(HOME_EXISTS.toString(), context.argAt(0));
            return;
        }
        
        if (!context.hasPermission(KERNEL_SETHOME_OTHER)) {
            context.noPermission(KERNEL_SETHOME_OTHER);
            return;
        }
        
        //Okay, the user is online. we just need to see if the home exists or not.
        if (user.getHome(context.argAt(0)) == null) {
            user.addHome(Kernel.getUser(context).getLocation(), context.argAt(0));
            context.pluginMessage(HOME_CREATED.toString(), context.argAt(0), user.getName());
        }
    }
    
}
