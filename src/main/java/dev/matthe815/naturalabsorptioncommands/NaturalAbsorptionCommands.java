package dev.matthe815.naturalabsorptioncommands;

import fathertoast.naturalabsorption.health.HealthData;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod(
        modid = NaturalAbsorptionCommands.MOD_ID,
        name = NaturalAbsorptionCommands.MOD_NAME,
        version = NaturalAbsorptionCommands.VERSION
)
public class NaturalAbsorptionCommands {

    public static final String MOD_ID = "naturalabsorptioncommands";
    public static final String MOD_NAME = "NAbsorption Commands";
    public static final String VERSION = "1.0";

    @Mod.EventHandler
    public void init(FMLServerStartingEvent event) {
        event.registerServerCommand(new TestCommand());
    }

    public class TestCommand extends CommandBase {

        @Override
        public String getName() {
            return "ahp";
        }

        @Override
        public String getUsage(ICommandSender sender) {
            return "/ahp ";
        }

        @Override
        public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
            if (args.length < 3) {
                sender.sendMessage(new TextComponentString("Invalid argument count specified."));
                return;
            }

            String task = args[0];
            EntityPlayerMP target = getPlayer(server, sender, args[1]);
            int amount = parseInt(args[2]);

            switch (task) {
                case "add":
                    HealthData.get(target).setAbsorptionCapacity(HealthData.get(target).getAbsorptionCapacity()+amount);
                    System.out.println("Set player to " + amount);
                    break;

                case "remove":
                    HealthData.get(target).setAbsorptionCapacity(HealthData.get(target).getAbsorptionCapacity()-amount);
                    System.out.println("Set player to " + amount);
                    break;

                case "set":
                    HealthData.get(target).setAbsorptionCapacity(amount);
                    System.out.println("Set player to " + amount);
                    break;
            }

        }
    }

}
