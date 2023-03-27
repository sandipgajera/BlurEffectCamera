package com.photo.blureffectcamera.svg;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.Xfermode;
import android.util.Log;

import com.photo.blureffectcamera.svg2.SvgBasketball;
import com.photo.blureffectcamera.svg2.SvgBlowFish;
import com.photo.blureffectcamera.svg2.SvgCloudLightening;
import com.photo.blureffectcamera.svg2.SvgCloudRain;
import com.photo.blureffectcamera.svg2.SvgCloudSun;
import com.photo.blureffectcamera.svg2.SvgClover2;
import com.photo.blureffectcamera.svg2.SvgCrescent;
import com.photo.blureffectcamera.svg2.SvgCross;
import com.photo.blureffectcamera.svg2.SvgFilm;
import com.photo.blureffectcamera.svg2.SvgFishBowl;
import com.photo.blureffectcamera.svg2.SvgHeartCam;
import com.photo.blureffectcamera.svg2.SvgHeartEvil;
import com.photo.blureffectcamera.svg2.SvgHeartLetter;
import com.photo.blureffectcamera.svg2.SvgLeaf3;
import com.photo.blureffectcamera.svg2.SvgLeaf4;
import com.photo.blureffectcamera.svg2.SvgLeafAutumn;
import com.photo.blureffectcamera.svg2.SvgLeafEmty;
import com.photo.blureffectcamera.svg2.SvgLoveYou;
import com.photo.blureffectcamera.svg2.SvgPisces;
import com.photo.blureffectcamera.svg2.SvgPlus;
import com.photo.blureffectcamera.svg2.SvgShield;
import com.photo.blureffectcamera.svg2.SvgStar6;
import com.photo.blureffectcamera.svg2.SvgStarSun;
import com.photo.blureffectcamera.svg2.SvgTropicFish;
import com.photo.blureffectcamera.svg2.SvgVollayball;
import com.photo.blureffectcamera.svg2.SvgYinYang;


public abstract class Svg {
    public static final int DEFAULT_STROKE_SIZE = 9;
    public static final int SVG_3_BLOCK_1 = 99;
    public static final int SVG_3_BLOCK_2 = 100;
    public static final int SVG_3_BLOCK_3 = 101;
    public static final int SVG_3_HEART = 76;
    public static final int SVG_3_HEART_LEFT = 75;
    public static final int SVG_3_HEART_RIGHT = 77;
    public static final int SVG_3_WAVE_1 = 105;
    public static final int SVG_3_WAVE_2 = 106;
    public static final int SVG_3_WAVE_3 = 107;
    public static final int SVG_4_CIRCLE_1 = 90;
    public static final int SVG_4_CIRCLE_2 = 91;
    public static final int SVG_4_CIRCLE_3 = 92;
    public static final int SVG_4_CIRCLE_4 = 93;
    public static final int SVG_5_CIRCLE_1 = 94;
    public static final int SVG_5_CIRCLE_2 = 95;
    public static final int SVG_5_CIRCLE_3 = 96;
    public static final int SVG_5_CIRCLE_4 = 97;
    public static final int SVG_5_CIRCLE_5 = 98;
    public static final int SVG_APPLE = 11;
    public static final int SVG_APPLESTEVE = 12;
    public static final int SVG_BARE_FOOT = 6;
    public static final int SVG_BASEBALL = 13;
    public static final int SVG_BASKETBALL = 44;
    public static final int SVG_BAUBLE = 14;
    public static final int SVG_BEARHEART = 15;
    public static final int SVG_BIRD = 5;
    public static final int SVG_BLOWFISH = 45;
    public static final int SVG_BOX = 16;
    public static final int SVG_BROKENHEART = 17;
    public static final int SVG_BUTTERFLY = 3;
    public static final int SVG_BUTTERFLY2 = 18;
    public static final int SVG_CAMERA = 19;
    public static final int SVG_CIRCLE = 1;
    public static final int SVG_CLOUD = 7;
    public static final int SVG_CLOUDLIGHTENING = 46;
    public static final int SVG_CLOUDRAIN = 47;
    public static final int SVG_CLOUDSUN = 48;
    public static final int SVG_CLOVER = 2;
    public static final int SVG_CLOVER2 = 49;
    public static final int SVG_CRESCENT = 50;
    public static final int SVG_CROSS = 51;
    public static final int SVG_DIAMOND = 10;
    public static final int SVG_DISC = 20;
    public static final int SVG_FACE_LEFT = 83;
    public static final int SVG_FACE_MIDDLE = 85;
    public static final int SVG_FACE_RIGHT = 84;
    public static final int SVG_FILM = 52;
    public static final int SVG_FISHBOWL = 53;
    public static final int SVG_FLAME = 21;
    public static final int SVG_GIFT = 22;
    public static final int SVG_HEART = 0;
    public static final int SVG_HEARTBEAT = 23;
    public static final int SVG_HEARTBRAIN = 24;
    public static final int SVG_HEARTCAM = 54;
    public static final int SVG_HEARTCARE = 25;
    public static final int SVG_HEARTEVIL = 55;
    public static final int SVG_HEARTLETTER = 56;
    public static final int SVG_HEARTLOCK = 26;
    public static final int SVG_HEARTSQUARE = 27;
    public static final int SVG_HEARTSTAR = 28;
    public static final int SVG_HEX = 9;
    public static final int SVG_HEX_2 = 70;
    public static final int SVG_LEAF3 = 57;
    public static final int SVG_LEAF4 = 58;
    public static final int SVG_LEAFAUTUMN = 59;
    public static final int SVG_LEAFEMTY = 60;
    public static final int SVG_LOVEYOU = 61;
    public static final int SVG_MAN = 29;
    public static final int SVG_MANLOVE = 30;
    public static final int SVG_MAPLE_LEAF = 4;
    public static final int SVG_NEWBADGE = 31;
    public static final int SVG_PAC = 32;
    public static final int SVG_PAPER_CUT_1 = 78;
    public static final int SVG_PAPER_CUT_2 = 79;
    public static final int SVG_PAPER_CUT_3 = 80;
    public static final int SVG_PAPER_CUT_4 = 81;
    public static final int SVG_PAPER_CUT_5 = 82;
    public static final int SVG_PAW = 8;
    public static final int SVG_PISCES = 62;
    public static final int SVG_PLUS = 63;
    public static final int SVG_PUZZLE = 33;
    public static final int SVG_SHERIFF = 34;
    public static final int SVG_SHIELD = 64;
    public static final int SVG_STAR = 35;
    public static final int SVG_STAR6 = 65;
    public static final int SVG_STARSQUARE = 36;
    public static final int SVG_STARSUN = 66;
    public static final int SVG_STONE_1 = 86;
    public static final int SVG_STONE_2 = 87;
    public static final int SVG_STONE_3 = 88;
    public static final int SVG_STONE_4 = 89;
    public static final int SVG_STONE_HEART = 102;
    public static final int SVG_STONE_HEART_STONE_1 = 103;
    public static final int SVG_STONE_HEART_STONE_2 = 104;
    public static final int SVG_STRAWBERRY = 37;
    public static final int SVG_SUN = 38;
    public static final int SVG_SUN2 = 39;
    public static final int SVG_SUNSHAPE = 40;
    public static final int SVG_TREE = 41;
    public static final int SVG_TROPICFISH = 67;
    public static final int SVG_TURD = 42;
    public static final int SVG_VOLLAYBALL = 68;
    public static final int SVG_WAVE_1 = 71;
    public static final int SVG_WAVE_2 = 72;
    public static final int SVG_WAVE_3 = 73;
    public static final int SVG_WAVE_4 = 74;
    public static final int SVG_WOMAN = 43;
    public static final int SVG_YINYANG = 69;
    protected static ColorFilter cf;
    public static int colorStroke;
    public static boolean isStroke;
    public static float strokeSize;
    public static Svg[] svgList = new Svg[]{new SvgHeart2(),
            new SvgCircle2(), new SvgClover(), new SvgButterfly(),
            new SvgMapleLeaf(), new SvgBird(), new SvgBareFoot(),
            new SvgCloud(), new SvgPaw(), new SvgHexagon(), new SvgDiamond(),
            new SvgApple(), new SvgAppleSteve(), new SvgBaseball(),
            new SvgBauble(), new SvgBearHeart(), new SvgBox(),
            new SvgBrokenHeart(), new SvgButterFly2(), new SvgCamera(),
            new SvgDisc(), new SvgFlame(), new SvgGift(), new SvgHeartBeat(),
            new SvgHeartBrain(), new SvgHeartCare(), new SvgHeartLock(),
            new SvgHeartSquare(), new SvgHeartStar(), new SvgMan(),
            new SvgManLove(), new SvgNewBadge(), new SvgPac(), new SvgPuzzle(),
            new SvgSheriff(), new SvgStar(), new SvgStarSquare(),
            new SvgStrawberry(), new SvgSun(), new SvgSun2(),
            new SvgSunShape(), new SvgTree(), new SvgFlush(), new SvgWoman(),
            new SvgBasketball(), new SvgBlowFish(), new SvgCloudLightening(),
            new SvgCloudRain(), new SvgCloudSun(), new SvgClover2(),
            new SvgCrescent(), new SvgCross(), new SvgFilm(),
            new SvgFishBowl(), new SvgHeartCam(), new SvgHeartEvil(),
            new SvgHeartLetter(), new SvgLeaf3(), new SvgLeaf4(),
            new SvgLeafAutumn(), new SvgLeafEmty(), new SvgLoveYou(),
            new SvgPisces(), new SvgPlus(), new SvgShield(), new SvgStar6(),
            new SvgStarSun(), new SvgTropicFish(), new SvgVollayball(),
            new SvgYinYang(), new SvgHex2(), new SvgWave1(), new SvgWave2(),
            new SvgWave3(), new SvgWave4(), new Svg3HeartLeft(),
            new Svg3Heart(), new Svg3HeartRight(), new SvgPaperCut1(),
            new SvgPaperCut2(), new SvgPaperCut3(), new SvgPaperCut4(),
            new SvgPaperCut5(), new SvgFaceLeft(), new SvgFaceRight(),
            new SvgFaceMiddle(), new SvgStone1(), new SvgStone3(),
            new SvgStone3(), new SvgStone4(), new Svg4Circle1(),
            new Svg4Circle2(), new Svg4Circle3(), new Svg4Circle4(),
            new Svg5Circle1(), new Svg5Circle2(), new Svg5Circle3(),
            new Svg5Circle4(), new Svg5Circle5(), new Svg3Block1(),
            new Svg3Block2(), new Svg3Block3(), new SvgStoneHeart(),
            new SvgStoneHeart1(), new SvgStoneHeart2(), new Svg3Wave1(),
            new Svg3Wave2(), new Svg3Wave3()};
    public Xfermode xferModeClear;

    public abstract void draw(Canvas canvas, float f, float f2, float f3,
                              float f4, boolean z);

    public Svg() {
        this.xferModeClear = new PorterDuffXfermode(Mode.CLEAR);
    }

    static {
        try {
            cf = null;
            strokeSize = 9.0f;
            colorStroke = -1;
            isStroke = false;
            svgList[SVG_HEART] = new SvgHeart2();
            svgList[SVG_CIRCLE] = new SvgCircle2();
            svgList[SVG_CLOVER] = new SvgClover();
            svgList[SVG_BUTTERFLY] = new SvgButterfly();
            svgList[SVG_MAPLE_LEAF] = new SvgMapleLeaf();
            svgList[SVG_BIRD] = new SvgBird();
            svgList[SVG_BARE_FOOT] = new SvgBareFoot();
            svgList[SVG_CLOUD] = new SvgCloud();
            svgList[SVG_PAW] = new SvgPaw();
            svgList[SVG_HEX] = new SvgHexagon();
            svgList[SVG_DIAMOND] = new SvgDiamond();
            svgList[SVG_APPLE] = new SvgApple();
            svgList[SVG_APPLESTEVE] = new SvgAppleSteve();
            svgList[SVG_BASEBALL] = new SvgBaseball();
            svgList[SVG_BAUBLE] = new SvgBauble();
            svgList[SVG_BEARHEART] = new SvgBearHeart();
            svgList[SVG_BOX] = new SvgBox();
            svgList[SVG_BROKENHEART] = new SvgBrokenHeart();
            svgList[SVG_BUTTERFLY2] = new SvgButterFly2();
            svgList[SVG_CAMERA] = new SvgCamera();
            svgList[SVG_DISC] = new SvgDisc();
            svgList[SVG_FLAME] = new SvgFlame();
            svgList[SVG_GIFT] = new SvgGift();
            svgList[SVG_HEARTBEAT] = new SvgHeartBeat();
            svgList[SVG_HEARTBRAIN] = new SvgHeartBrain();
            svgList[SVG_HEARTCARE] = new SvgHeartCare();
            svgList[SVG_HEARTLOCK] = new SvgHeartLock();
            svgList[SVG_HEARTSQUARE] = new SvgHeartSquare();
            svgList[SVG_HEARTSTAR] = new SvgHeartStar();
            svgList[SVG_MAN] = new SvgMan();
            svgList[SVG_MANLOVE] = new SvgManLove();
            svgList[SVG_NEWBADGE] = new SvgNewBadge();
            svgList[SVG_PAC] = new SvgPac();
            svgList[SVG_PUZZLE] = new SvgPuzzle();
            svgList[SVG_SHERIFF] = new SvgSheriff();
            svgList[SVG_STAR] = new SvgStar();
            svgList[SVG_STARSQUARE] = new SvgStarSquare();
            svgList[SVG_STRAWBERRY] = new SvgStrawberry();
            svgList[SVG_SUN] = new SvgSun();
            svgList[SVG_SUN2] = new SvgSun2();
            svgList[SVG_SUNSHAPE] = new SvgSunShape();
            svgList[SVG_TREE] = new SvgTree();
            svgList[SVG_TURD] = new SvgFlush();
            svgList[SVG_WOMAN] = new SvgWoman();
            svgList[SVG_BASKETBALL] = new SvgBasketball();
            svgList[SVG_BLOWFISH] = new SvgBlowFish();
            svgList[SVG_CLOUDLIGHTENING] = new SvgCloudLightening();
            svgList[SVG_CLOUDRAIN] = new SvgCloudRain();
            svgList[SVG_CLOUDSUN] = new SvgCloudSun();
            svgList[SVG_CLOVER2] = new SvgClover2();
            svgList[SVG_CRESCENT] = new SvgCrescent();
            svgList[SVG_CROSS] = new SvgCross();
            svgList[SVG_FILM] = new SvgFilm();
            svgList[SVG_FISHBOWL] = new SvgFishBowl();
            svgList[SVG_HEARTCAM] = new SvgHeartCam();
            svgList[SVG_HEARTEVIL] = new SvgHeartEvil();
            svgList[SVG_HEARTLETTER] = new SvgHeartLetter();
            svgList[SVG_LEAF3] = new SvgLeaf3();
            svgList[SVG_LEAF4] = new SvgLeaf4();
            svgList[SVG_LEAFAUTUMN] = new SvgLeafAutumn();
            svgList[SVG_LEAFEMTY] = new SvgLeafEmty();
            svgList[SVG_LOVEYOU] = new SvgLoveYou();
            svgList[SVG_PISCES] = new SvgPisces();
            svgList[SVG_PLUS] = new SvgPlus();
            svgList[SVG_SHIELD] = new SvgShield();
            svgList[SVG_STAR6] = new SvgStar6();
            svgList[SVG_STARSUN] = new SvgStarSun();
            svgList[SVG_TROPICFISH] = new SvgTropicFish();
            svgList[SVG_VOLLAYBALL] = new SvgVollayball();
            svgList[SVG_YINYANG] = new SvgYinYang();
            svgList[SVG_HEX_2] = new SvgHex2();
            svgList[SVG_WAVE_1] = new SvgWave1();
            svgList[SVG_WAVE_2] = new SvgWave2();
            svgList[SVG_WAVE_3] = new SvgWave3();
            svgList[SVG_WAVE_4] = new SvgWave4();
            svgList[SVG_3_HEART_LEFT] = new Svg3HeartLeft();
            svgList[SVG_3_HEART] = new Svg3Heart();
            svgList[SVG_3_HEART_RIGHT] = new Svg3HeartRight();
            svgList[SVG_PAPER_CUT_1] = new SvgPaperCut1();
            svgList[SVG_PAPER_CUT_2] = new SvgPaperCut2();
            svgList[SVG_PAPER_CUT_3] = new SvgPaperCut3();
            svgList[SVG_PAPER_CUT_4] = new SvgPaperCut4();
            svgList[SVG_PAPER_CUT_5] = new SvgPaperCut5();
            svgList[SVG_FACE_LEFT] = new SvgFaceLeft();
            svgList[SVG_FACE_RIGHT] = new SvgFaceRight();
            svgList[SVG_FACE_MIDDLE] = new SvgFaceMiddle();
            svgList[SVG_STONE_1] = new SvgStone1();
            svgList[SVG_STONE_2] = new SvgStone3();
            svgList[SVG_STONE_3] = new SvgStone3();
            svgList[SVG_STONE_4] = new SvgStone4();
            svgList[SVG_4_CIRCLE_1] = new Svg4Circle1();
            svgList[SVG_4_CIRCLE_2] = new Svg4Circle2();
            svgList[SVG_4_CIRCLE_3] = new Svg4Circle3();
            svgList[SVG_4_CIRCLE_4] = new Svg4Circle4();
            svgList[SVG_5_CIRCLE_1] = new Svg5Circle1();
            svgList[SVG_5_CIRCLE_2] = new Svg5Circle2();
            svgList[SVG_5_CIRCLE_3] = new Svg5Circle3();
            svgList[SVG_5_CIRCLE_4] = new Svg5Circle4();
            svgList[SVG_5_CIRCLE_5] = new Svg5Circle5();
            svgList[SVG_3_BLOCK_1] = new Svg3Block1();
            svgList[SVG_3_BLOCK_2] = new Svg3Block2();
            svgList[SVG_3_BLOCK_3] = new Svg3Block3();
            svgList[SVG_STONE_HEART] = new SvgStoneHeart();
            svgList[SVG_STONE_HEART_STONE_1] = new SvgStoneHeart1();
            svgList[SVG_STONE_HEART_STONE_2] = new SvgStoneHeart2();
            svgList[SVG_3_WAVE_1] = new Svg3Wave1();
            svgList[SVG_3_WAVE_2] = new Svg3Wave2();
            svgList[SVG_3_WAVE_3] = new Svg3Wave3();

        } catch (Exception e) {
            Log.d("Exception", e.getMessage());
        }
    }

    public static void setColorTint(int color) {
        cf = new PorterDuffColorFilter(color, Mode.SRC_IN);
    }

    public void drawStroke(Canvas c, float w, float h, float dx, float dy,
                           boolean clearMode) {
    }

    public static void clearColorTint(int color) {
        cf = null;
    }
}
