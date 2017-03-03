package iChopper;

import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.map.Position;

public class constants {
    public static final String[] TREE_NAMES = {"Tree", "Oak", "Willow", "Yew"};
    public static final String[] LOCATIONS = {"Grand Exchange", "Edgeville", "Rimmington", "Varrock West", "Draynor Village", "Port Sarim", "Lumbridge"};
    public static final String[] AXES = {"Bronze axe", "Iron axe", "Black axe", "Steel axe", "Mithril axe", "Adamant axe", "Rune axe"};

    // Location
    //<editor-fold defaultstate="collapsed" desc="Locations">
        //<editor-fold defaultstate="collapsed" desc="Bank">
        public static final Area GRAND_EXCHANGE = new Area(3159, 3495, 3170, 3483);
    
        public static final Area FALADOR_BANK = new Area(
            new int[][] {
                {3009, 3359},
                {3019, 3359},
                {3019, 3357},
                {3022, 3357},
                {3022, 3353},
                {3009, 3353}
            }
        );
        
        public static final Area DRAYNOR_VILLAGE_BANK = new Area(
            new int[][] {
                {3088, 3247},
                {3098, 3247},
                {3098, 3240},
                {3088, 3240}
            }
        );
        
//        public static final Area LUMBRIDGE_CASTLE_BANK = new Area(new Position[] {
//            new Position(3206, 3214, 2),
//            new Position(2311, 3214, 2),
//            new Position(3211, 3223, 2),
//            new Position(3206, 3223, 2)
//        });
        
        public static final Area LUMBRIDGE_CASTLE_BANK = new Area(3206, 3214, 3211, 3223).setPlane(2);
        
        public static final Area EDGEVILLE_BANK = new Area(
            new int[][]{
                {3099, 3500},
                {3099, 3487},
                {3091, 3487},
                {3091, 3493},
                {3090, 3494},
                {3090, 3497},
                {3091, 3498},
                {3091, 3500}
            }
        );
        
        public static final Area VARROCK_WEST_BANK = new Area(3180, 3447, 3190, 3433);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Tree">
        public static final Area TREE_GRAND_EXCHANGE = new Area(
            new int[][] {
                {3186, 3468},
                {3144, 3468},
                {3144, 3468},
                {3139, 3473},
                {3139, 3482},
                {3142, 3485},
                {3142, 3492},
                {3139, 3495},
                {3139, 3514},
                {3142, 3517},
                {3158, 3517},
                {3161, 3514},
                {3168, 3514},
                {3171, 3517},
                {3189, 3517},
                {3198, 3508},
                {3198, 3506},
                {3196, 3504},
                {3194, 3501},
                {3190, 3497},
                {3190, 3479},
                {3187, 3476},
                {3187, 3468}
            });
        
        public static final Area TREE_LUMBRIDGE = new Area(
            new int[][] {
                {3267, 3213},
                {3263, 3213},
                {3260, 3217},
                {3257, 3219},
                {3254, 3222},
                {3250, 3223},
                {3246, 3230},
                {3242, 3235},
                {3240, 3240},
                {3239, 3243},
                {3252, 3243},
                {3252, 3255},
                {3265, 3255},
                {3266, 3247},
                {3267, 3238},
                {3268, 3231}
            }
        );
        
        public static final Area OAK_GRAND_EXCHANGE = new Area(new Position[]{
            new Position(3188, 3465, 0),
            new Position(3196, 3465, 0),
            new Position(3196, 3453, 0),
            new Position(3192, 3459, 0),
            new Position(3188, 3459, 0)
        });

        public static final Area OAK_VARROCK_WEST = new Area(new Position[]{
            new Position(3173, 3426, 0),
            new Position(3168, 3427, 0),
            new Position(3165, 3422, 0),
            new Position(3165, 3416, 0),
            new Position(3173, 3416, 0)
        });

        public static final Area WILLOW_DRAYNOR_VILLAGE = new Area(new Position[]{
            new Position(3076, 3242, 0),
            new Position(3087, 3242, 0),
            new Position(3087, 3239, 0),
            new Position(3094, 3239, 0),
            new Position(3095, 3230, 0),
            new Position(3087, 3230, 0)
        });

        public static final Area WILLOW_PORT_SARIM = new Area(new Position[]{
            new Position(3064, 3257, 0),
            new Position(3056, 3257, 0),
            new Position(3056, 3250, 0),
            new Position(3064, 3250, 0)
        });
        
        public static final Area WILLOW_RIMMINGTON = new Area(2959, 3201, 2974, 3189);

        public static final Area YEW_GRAND_EXCHANGE = new Area(new Position[]{
            new Position(3200, 3506, 0),
            new Position(3226, 3506, 0),
            new Position(3226, 3497, 0),
            new Position(3208, 3497, 0),
            new Position(3204, 3501, 0),
            new Position(3200, 3501, 0)
        });

        public static final Area YEW_EDGEVILLE = new Area(new Position[]{
            new Position(3082, 3486, 0),
            new Position(3090, 3486, 0),
            new Position(3090, 3466, 0),
            new Position(3082, 3466, 0)
        });

        public static final Area YEW_RIMMINGTON = new Area(new Position[]{
            new Position(2940, 3237, 0),
            new Position(2930, 3237, 0),
            new Position(2931, 3228, 0),
            new Position(2940, 3228, 0)
        });
        //</editor-fold>
    //</editor-fold>
}
