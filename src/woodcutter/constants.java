package woodcutter;

import org.tbot.wrappers.Area;
import org.tbot.wrappers.Tile;

public class constants {
    public static final String[] TREE_NAMES = {"Tree", "Oak", "Willow", "Yew"};
    public static final String[] LOCATIONS = {"Grand Exchange", "Edgeville", "Rimmington", "Varrock West", "Draynor Village", "Port Sarim", "Lumbridge"};
    public static final String[] AXES = {"Bronze axe", "Iron axe", "Black axe", "Steel axe", "Mithril axe", "Adamant axe", "Rune axe"};

    // Location
    //<editor-fold defaultstate="collapsed" desc="Locations">
    //<editor-fold defaultstate="collapsed" desc="Bank">
    public static final Area GRAND_EXCHANGE = new Area(3159, 3495, 3170, 3483);

    public static final Area FALADOR_BANK = new Area(
            new Tile[]{
                    new Tile(3009, 3359),
                    new Tile(3019, 3359),
                    new Tile(3019, 3357),
                    new Tile(3022, 3357),
                    new Tile(3022, 3353),
                    new Tile(3009, 3353)
            }
    );

    public static final Area DRAYNOR_VILLAGE_BANK = new Area(
            new Tile[]{
                    new Tile(3088, 3247),
                    new Tile(3098, 3247),
                    new Tile(3098, 3240),
                    new Tile(3088, 3240)
            }
    );

//        public static final Area LUMBRIDGE_CASTLE_BANK = new Area(new Tile[] {
//            new Tile(3206, 3214, 2),
//            new Tile(2311, 3214, 2),
//            new Tile(3211, 3223, 2),
//            new Tile(3206, 3223, 2)
//        });

    public static final Area LUMBRIDGE_CASTLE_BANK = new Area(new Tile(3206, 3214), new Tile(3211, 3223), 2);

    public static final Area EDGEVILLE_BANK = new Area(
            new Tile[]{
                    new Tile(3099, 3500),
                    new Tile(3099, 3487),
                    new Tile(3091, 3487),
                    new Tile(3091, 3493),
                    new Tile(3090, 3494),
                    new Tile(3090, 3497),
                    new Tile(3091, 3498),
                    new Tile(3091, 3500)
            }
    );

    public static final Area VARROCK_WEST_BANK = new Area(3180, 3447, 3190, 3433);
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Tree">
    public static final Area TREE_GRAND_EXCHANGE = new Area(
            new Tile[]{
                    new Tile(3186, 3468),
                    new Tile(3144, 3468),
                    new Tile(3144, 3468),
                    new Tile(3139, 3473),
                    new Tile(3139, 3482),
                    new Tile(3142, 3485),
                    new Tile(3142, 3492),
                    new Tile(3139, 3495),
                    new Tile(3139, 3514),
                    new Tile(3142, 3517),
                    new Tile(3158, 3517),
                    new Tile(3161, 3514),
                    new Tile(3168, 3514),
                    new Tile(3171, 3517),
                    new Tile(3189, 3517),
                    new Tile(3198, 3508),
                    new Tile(3198, 3506),
                    new Tile(3196, 3504),
                    new Tile(3194, 3501),
                    new Tile(3190, 3497),
                    new Tile(3190, 3479),
                    new Tile(3187, 3476),
                    new Tile(3187, 3468)
            });

    public static final Area TREE_LUMBRIDGE = new Area(
            new Tile[]{
                    new Tile(3267, 3213),
                    new Tile(3263, 3213),
                    new Tile(3260, 3217),
                    new Tile(3257, 3219),
                    new Tile(3254, 3222),
                    new Tile(3250, 3223),
                    new Tile(3246, 3230),
                    new Tile(3242, 3235),
                    new Tile(3240, 3240),
                    new Tile(3239, 3243),
                    new Tile(3252, 3243),
                    new Tile(3252, 3255),
                    new Tile(3265, 3255),
                    new Tile(3266, 3247),
                    new Tile(3267, 3238),
                    new Tile(3268, 3231)
            }
    );

    public static final Area OAK_GRAND_EXCHANGE = new Area(new Tile[]{
            new Tile(3188, 3465, 0),
            new Tile(3196, 3465, 0),
            new Tile(3196, 3453, 0),
            new Tile(3192, 3459, 0),
            new Tile(3188, 3459, 0)
    });

    public static final Area OAK_VARROCK_WEST = new Area(new Tile[]{
            new Tile(3173, 3426, 0),
            new Tile(3168, 3427, 0),
            new Tile(3165, 3422, 0),
            new Tile(3165, 3416, 0),
            new Tile(3173, 3416, 0)
    });

    public static final Area WILLOW_DRAYNOR_VILLAGE = new Area(new Tile[]{
            new Tile(3076, 3242, 0),
            new Tile(3087, 3242, 0),
            new Tile(3087, 3239, 0),
            new Tile(3094, 3239, 0),
            new Tile(3095, 3230, 0),
            new Tile(3087, 3230, 0)
    });

    public static final Area WILLOW_PORT_SARIM = new Area(new Tile[]{
            new Tile(3064, 3257, 0),
            new Tile(3056, 3257, 0),
            new Tile(3056, 3250, 0),
            new Tile(3064, 3250, 0)
    });

    public static final Area WILLOW_RIMMINGTON = new Area(2959, 3201, 2974, 3189);

    public static final Area YEW_GRAND_EXCHANGE = new Area(new Tile[]{
            new Tile(3200, 3506, 0),
            new Tile(3226, 3506, 0),
            new Tile(3226, 3497, 0),
            new Tile(3208, 3497, 0),
            new Tile(3204, 3501, 0),
            new Tile(3200, 3501, 0)
    });

    public static final Area YEW_EDGEVILLE = new Area(new Tile[]{
            new Tile(3082, 3486, 0),
            new Tile(3090, 3486, 0),
            new Tile(3090, 3466, 0),
            new Tile(3082, 3466, 0)
    });

    public static final Area YEW_RIMMINGTON = new Area(new Tile[]{
            new Tile(2940, 3237, 0),
            new Tile(2930, 3237, 0),
            new Tile(2931, 3228, 0),
            new Tile(2940, 3228, 0)
    });
    //</editor-fold>
    //</editor-fold>
}
