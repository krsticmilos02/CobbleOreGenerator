# Changelog

## Version 1.0.0 (Initial Release)

### Features
- ✨ Cobblestone generators have 25% chance to generate ores
- ⚙️ Fully configurable JSON config system
- 🎲 Weighted random ore selection
- 📦 Includes all vanilla ores in default config (both stone and deepslate variants)
- 🔧 Support for modded ores via config
- 🚀 Optimized mixin implementation

### Technical Details
- Built for Minecraft 1.21.1
- Uses Fabric Loader 0.16.0+
- Requires Fabric API 0.102.0+1.21.1
- Java 21 compatible
- Based on original CobbleOre Generator by OffsetMods538

### Default Ore Weights
- Coal: 30 (both variants)
- Iron: 25 (both variants)
- Copper: 20 (both variants)
- Redstone: 15 (both variants)
- Lapis: 12 (both variants)
- Gold: 10 (both variants)
- Diamond: 3 (both variants)
- Emerald: 2 (both variants)

### Notes
- Config file: `.minecraft/config/cog.json`
- Auto-generates default config on first launch
- Server-compatible
- Lightweight performance impact

---

## Credits

Original mod by [OffsetMods538](https://github.com/OffsetMods538/CobbleOre-Generator)
Adapted for Minecraft 1.21.1
