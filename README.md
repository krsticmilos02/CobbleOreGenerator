# CobbleOre Generator - Fabric 1.21.1

A Minecraft Fabric mod for version 1.21.1 that makes cobblestone generators generate ores! Based on the original mod by [OffsetMods538](https://github.com/OffsetMods538/CobbleOre-Generator).

## Features

- **25% Chance for Ores**: When cobblestone is generated, there's a 25% chance it becomes an ore instead
- **Fully Configurable**: JSON config file lets you control which blocks spawn and their weights
- **Modded Support**: Works with any mod that adds ores - just add them to the config!
- **Balanced by Default**: Comes with sensible default weights for all vanilla ores

## How It Works

When lava and water interact to create cobblestone in a generator, this mod has a 25% chance to replace that cobblestone with a random ore based on weighted probabilities defined in your config file.

## Configuration

The config file is located at `.minecraft/config/cog.json`

### Default Configuration

The mod comes with these default ore weights:

| Ore | Weight | Rarity |
|-----|--------|--------|
| Coal (both variants) | 30 each | Common |
| Iron (both variants) | 25 each | Common |
| Copper (both variants) | 20 each | Common |
| Redstone (both variants) | 15 each | Uncommon |
| Lapis (both variants) | 12 each | Uncommon |
| Gold (both variants) | 10 each | Uncommon |
| Diamond (both variants) | 3 each | Rare |
| Emerald (both variants) | 2 each | Very Rare |

### Config Format

The config uses a simple weight-based system. Higher weight = more common.

```json
{
  "generatableBlocks": {
    "minecraft:cobblestone": 30,
    "minecraft:diamond_ore": 3,
    "minecraft:emerald_ore": 2
  }
}
```

**Weight Examples:**
- 2 blocks with weight 1 each = 50/50 chance
- Block A with weight 30, Block B with weight 1 = ~97% vs ~3%

### Adding Modded Ores

Just add the block ID and weight to the config:

```json
{
  "generatableBlocks": {
    "minecraft:diamond_ore": 3,
    "create:zinc_ore": 15,
    "thermal:silver_ore": 10,
    "mekanism:osmium_ore": 12
  }
}
```

### Fun Configurations

**Chaos Mode** - Generate ANY block:
```json
{
  "generatableBlocks": {
    "minecraft:diamond_block": 1,
    "minecraft:tnt": 1,
    "minecraft:cake": 10
  }
}
```

**Easy Mode** - More valuable ores:
```json
{
  "generatableBlocks": {
    "minecraft:diamond_ore": 30,
    "minecraft:emerald_ore": 20,
    "minecraft:gold_ore": 50
  }
}
```

## Installation

### For Players:

1. Install [Fabric Loader](https://fabricmc.net/use/) for Minecraft 1.21.1
2. Download [Fabric API](https://modrinth.com/mod/fabric-api) for 1.21.1
3. Place both Fabric API and this mod's `.jar` file in your `.minecraft/mods` folder
4. Launch Minecraft with the Fabric profile
5. (Optional) Edit the config at `.minecraft/config/cog.json`

### For Developers:

#### First Time Setup

Before building, you need to set up the Gradle wrapper:

**Option 1: Using the download script (easiest)**
```bash
# Linux/Mac
chmod +x download-wrapper.sh
./download-wrapper.sh

# Windows
download-wrapper.bat
```

**Option 2: If you have Gradle installed**
```bash
gradle wrapper
```

**Option 3: Manual download**
Download `gradle-wrapper.jar` from:
https://raw.githubusercontent.com/gradle/gradle/v8.8.0/gradle/wrapper/gradle-wrapper.jar

Place it in: `gradle/wrapper/gradle-wrapper.jar`

#### Building the Mod

Once the wrapper is set up:

```bash
# Linux/Mac
./gradlew build

# Windows
gradlew.bat build
```

The compiled `.jar` file will be located in `build/libs/`

## Requirements

- Minecraft 1.21.1
- Fabric Loader 0.16.0 or higher
- Fabric API 0.102.0+1.21.1 or higher
- Java 21 or higher

## Compatibility

This mod should be compatible with most other mods. It only modifies the cobblestone generation behavior and uses mixins to hook into fluid interactions.

## FAQ

**Q: Does this work with modded ores?**  
A: Yes! Just add them to the config file with their block ID and desired weight.

**Q: Can I make it generate blocks other than ores?**  
A: Absolutely! You can use any block ID in the config. Want cake generators? Go for it!

**Q: The ores aren't generating!**  
A: Make sure you have a proper cobblestone generator (lava + water creating cobblestone). Stone generators won't work. Also remember it's only a 25% chance.

**Q: Can I change the 25% chance?**  
A: The percentage is hardcoded in the mod, but you can adjust the relative frequencies using weights in the config.

**Q: Will this work on servers?**  
A: Yes! Just install it on the server like any other Fabric mod.

## Credits

- Original concept and mod by [OffsetMods538](https://github.com/OffsetMods538/CobbleOre-Generator)
- Adapted for Minecraft 1.21.1

## License

This mod is available under the MIT license.

## Links

- Original mod: https://github.com/OffsetMods538/CobbleOre-Generator
- Original Modrinth page: https://modrinth.com/mod/cog
