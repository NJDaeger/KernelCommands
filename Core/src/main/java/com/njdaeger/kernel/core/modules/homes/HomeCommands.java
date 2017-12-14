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
        
        if (firstArg == null) {
            context.pluginMessage(CANNOT_RESOLVE_ARG.toString(), "null");
            return;
        }
        
        //The user is using this for himself.
        if (context.isLength(1)) {
            
            //Permission check
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
            home.sendHere();
            context.pluginMessage(USER_SENT_HOME.toString(), home.getName());
            return;
        }
        
        if (secondArg == null) {
            context.pluginMessage(CANNOT_RESOLVE_ARG.toString(), "null");
            return;
        }
        
        //The command must be either sending itself to another home, another user to their home, or another user to another user's home
        if (firstArg.startsWith("u:")) {
            
            user = Kernel.getUser(firstArg.split(":")[1]);
            
            //Check if the user exists, then check if theyre offline...
            if (user == null) {
            
				/*offlineUser = Kernel.getOfflineUser(context.argAt(0).split(":")[1]);
    
				if (offlineUser == null) {
					context.pluginMessage(RED + "User does not exist.");
					return;
				}*/
                return;
            }
            
            
            //At this point we know that the first user exists, now we just need to parse the second arg
            if (secondArg.contains(":")) {
                //Send other to own home -- OTHER to OWN home
                // home [u:user] <u:home>
                if (secondArg.startsWith("u:")) {
                    IHome home = (IHome)user.getHome(secondArg.split(":")[1]);
                    if (home == null) {
                        context.pluginMessage(HOME_NOT_EXIST.toString(), context.argAt(0));
                        return;
                    }
                    user.pluginMessage(USER_SENT_HOME.toString(), home.getName());
                    home.sendHere();
                    return;
                }
                
                //We need to pull the user from the second argument
                user1 = Kernel.getUser(secondArg.split(":")[0]);
                
                //Send other to other home- OTHER to ANOTHER home
                // home [u:user] [user:home]
                if (user1 == null) {
                    context.pluginMessage(USER_NOT_EXIST.toString(), secondArg.split(":")[0]);
                    return;
                }
                
                IHome home = (IHome)user1.getHome(secondArg.split(":")[1]);
                
                //Check if the user in the second arg has the home specified as well
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
            
            //Send self to other home-- ME to OTHER home
            // home [u:user] <home>
            IHome home = (IHome)user.getHome(secondArg);
            if (home == null) {
                context.pluginMessage(HOME_NOT_EXIST.toString(), secondArg);
                return;
            }
            user1.pluginMessage(USER_SENT_OTHER_HOME.toString(), user1.getName(), home.getName());
            user.pluginMessage();
            home.sendOtherHere(user1);
            
        }
        
        //Send other to self home-- OTHER to MY home //Done
        // home <home> [user]
        
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
        
        home.sendOtherHere(user);
        
        //Send self to own home  -- ME to MY home //Done
        // home <home>
        //Send self to other home-- ME to OTHER home
        // home [u:user] <home>
        //Send other to self home-- OTHER to MY home //Done
        // home <home> [user]
        //Send other to own home -- OTHER to OWN home
        // home [u:user] <u:home>
        //Send other to other home- OTHER to ANOTHER home
        // home [u:user] [user:home]
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
                    //add all the homes of the specified user.
                    user.getHomes().stream().map(IOfflineHome::getName).forEach(completions::add);
                }
                if (context.hasPermission(KERNEL_HOME_OTHER_OWN)) {
                    //add all the homes of the specified user with the prefix 'u:'
                    user.getHomes().stream().map(IOfflineHome::getName).forEach(n -> completions.add("u:" + n));
                }
                if (context.hasPermission(KERNEL_HOME_OTHER_OTHER)) {
                    //first loop through the online players, then check if the arg we're currently editing has a colon in it. If it does, get the user behind the colon, and loop though their homes with the users prefix still there
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
        
        User user = null;
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
                offlineUser.getHomes().stream().map(IOfflineHome::getName).forEach(n -> builder.append(GREEN).append(n).append(SILVER).append(","));
            } else {
                if (!context.hasPermission(KERNEL_LISTHOMES_OTHER)) {
                    context.noPermission(KERNEL_LISTHOMES_OTHER);
                    return;
                }
                user.getHomes().stream().map(IOfflineHome::getName).forEach(n -> builder.append(GREEN).append(n).append(SILVER).append(","));
            }
        }
        if (user == null) {
            user = Kernel.getUser(context);
            user.getHomes().stream().map(IOfflineHome::getName).forEach(n -> builder.append(GREEN).append(n).append(SILVER).append(","));
        }
        context.pluginMessage(HOME_LIST.toString(), offlineUser == null ? user.getName() : offlineUser.getName(), builder.toString());
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
        
        context.subCommand(SenderType.CONSOLE, this::deleteHomeConsole);
        
        User user;
        OfflineUser offlineUser;
        
        if (context.isLength(1)) {
            user = Kernel.getUser(context);
            
            if (user == null) {
            
            }
        }
        
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
            
        }
        if (user.getHome(context.argAt(1)) == null) {
            context.pluginMessage(HOME_NOT_EXIST.toString(), context.argAt(1));
            return;
        }
        user.getHome(context.argAt(1)).delete();
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
        if (context.isLength(1)) {
            
            if (!context.hasPermission(KERNEL_SETHOME)) {
                context.noPermission(KERNEL_SETHOME);
                return;
            }
            
            user = Kernel.getUser(context);
            
            if (user.getHome(context.argAt(0)) == null) {
                user.addHome(context.argAt(0));
                user.pluginMessage(HOME_CREATED.toString(), context.argAt(0), user.getName());
                return;
            } else {
                user.pluginMessage(HOME_EXISTS.toString(), context.argAt(0));
            }
        }
        
        user = Kernel.getUser(context.argAt(1));
        
        //Basically checks if the user is online or not. if the user variable is still null then the player is probably offline
        if (user == null) {
            
            if (!context.hasPermission(KERNEL_SETHOME_OFFLINE)) {
                context.noPermission(KERNEL_SETHOME_OFFLINE);
                return;
            }
            
            user = Kernel.getUser(context);
            offlineUser = Kernel.getOfflineUser(context.argAt(1));
            
            //Checks if the offline user exists.
            if (offlineUser == null) {
                context.pluginMessage(USER_NOT_EXIST.toString(), context.argAt(1));
                return;
            }
            
            //Checking if the offline home exists
            if (offlineUser.getHome(context.argAt(0)) == null) {
                offlineUser.addHome(user.getLocation(), context.argAt(0));
                return;
            } else {
                context.pluginMessage(HOME_EXISTS.toString(), context.argAt(0));
            }
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
