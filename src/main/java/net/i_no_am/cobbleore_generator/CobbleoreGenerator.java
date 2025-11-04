package net.i_no_am.cobbleore_generator;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Path;
import java.util.*;

public class CobbleoreGenerator implements ModInitializer {
    public static final String MOD_ID = "cobbleore_generator";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static Config config;
    private static final Random RANDOM = new Random();
    
    // Default ore generation chance
    public static final double ORE_GENERATION_CHANCE = 0.25; // 25%
    
    @Override
    public void onInitialize() {
        LOGGER.info("CobbleOre Generator initializing...");
        
        // Load or create config
        loadConfig();
        
        // Reload config when server starts
        ServerLifecycleEvents.SERVER_STARTING.register(server -> {
            LOGGER.info("Server starting - reloading config");
            loadConfig();
        });
        
        LOGGER.info("CobbleOre Generator initialized!");
    }
    
    private void loadConfig() {
        Path configPath = FabricLoader.getInstance().getConfigDir();
        File configFile = new File(configPath.toFile(), "cog.json");
        
        if (configFile.exists()) {
            try (FileReader reader = new FileReader(configFile)) {
                config = GSON.fromJson(reader, Config.class);
                LOGGER.info("Config loaded from {}", configFile.getAbsolutePath());
            } catch (Exception e) {
                LOGGER.error("Failed to load config, using defaults", e);
                config = createDefaultConfig();
                saveConfig(configFile);
            }
        } else {
            LOGGER.info("Config file not found, creating default config");
            config = createDefaultConfig();
            saveConfig(configFile);
        }
    }
    
    private void saveConfig(File configFile) {
        try {
            configFile.getParentFile().mkdirs();
            try (FileWriter writer = new FileWriter(configFile)) {
                GSON.toJson(config, writer);
                LOGGER.info("Config saved to {}", configFile.getAbsolutePath());
            }
        } catch (Exception e) {
            LOGGER.error("Failed to save config", e);
        }
    }
    
    private Config createDefaultConfig() {
        Config defaultConfig = new Config();
        defaultConfig.generatableBlocks = new LinkedHashMap<>();
        
        // Default ore weights - balanced distribution
        defaultConfig.generatableBlocks.put("minecraft:coal_ore", 30);
        defaultConfig.generatableBlocks.put("minecraft:deepslate_coal_ore", 30);
        defaultConfig.generatableBlocks.put("minecraft:iron_ore", 25);
        defaultConfig.generatableBlocks.put("minecraft:deepslate_iron_ore", 25);
        defaultConfig.generatableBlocks.put("minecraft:copper_ore", 20);
        defaultConfig.generatableBlocks.put("minecraft:deepslate_copper_ore", 20);
        defaultConfig.generatableBlocks.put("minecraft:gold_ore", 10);
        defaultConfig.generatableBlocks.put("minecraft:deepslate_gold_ore", 10);
        defaultConfig.generatableBlocks.put("minecraft:redstone_ore", 15);
        defaultConfig.generatableBlocks.put("minecraft:deepslate_redstone_ore", 15);
        defaultConfig.generatableBlocks.put("minecraft:lapis_ore", 12);
        defaultConfig.generatableBlocks.put("minecraft:deepslate_lapis_ore", 12);
        defaultConfig.generatableBlocks.put("minecraft:diamond_ore", 3);
        defaultConfig.generatableBlocks.put("minecraft:deepslate_diamond_ore", 3);
        defaultConfig.generatableBlocks.put("minecraft:emerald_ore", 2);
        defaultConfig.generatableBlocks.put("minecraft:deepslate_emerald_ore", 2);
        
        return defaultConfig;
    }
    
    /**
     * Get a random block based on weighted probabilities from config
     */
    public static Block getRandomBlock() {
        if (config == null || config.generatableBlocks.isEmpty()) {
            return Blocks.COBBLESTONE;
        }
        
        // Calculate total weight
        int totalWeight = config.generatableBlocks.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
        
        if (totalWeight <= 0) {
            return Blocks.COBBLESTONE;
        }
        
        // Get random weighted selection
        int randomWeight = RANDOM.nextInt(totalWeight);
        int currentWeight = 0;
        
        for (Map.Entry<String, Integer> entry : config.generatableBlocks.entrySet()) {
            currentWeight += entry.getValue();
            if (randomWeight < currentWeight) {
                try {
                    Identifier blockId = Identifier.of(entry.getKey());
                    // Use getOrEmpty which returns Optional<Block>
                    Block block = Registries.BLOCK.getOrEmpty(blockId).orElse(Blocks.AIR);
                    if (block != Blocks.AIR) {
                        return block;
                    }
                } catch (Exception e) {
                    LOGGER.warn("Invalid block ID in config: {}", entry.getKey());
                }
            }
        }
        
        return Blocks.COBBLESTONE; // Fallback
    }
    
    /**
     * Check if cobblestone should be converted to an ore
     */
    public static boolean shouldGenerateOre() {
        return RANDOM.nextDouble() < ORE_GENERATION_CHANCE;
    }
    
    /**
     * Config class for JSON serialization
     */
    public static class Config {
        public Map<String, Integer> generatableBlocks;
    }
}
