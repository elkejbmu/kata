package my.example.kata.preset;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class EncodingTaskHandlerUtilsTest {
    private static List<FFmpegStream> streams = null;
    private static String PRESET_AMAZON_PRIME_VIDEO="apv_#{aspect_ratio}_#{height}p";
    private static String PRESET_MEDIACHAIN="abr_mediachain_#{height}p";
    private static String PRESET_ALCHIMIE="normalize_alchimie_#{height}p";
    private static String PRESET_SFR="sfr_ts_xd_fre";
    private static final List<String> ENCODINS_AMAZON_PRIME_VIDEO= Arrays.asList("apv_other_576p","apv_other_1080p","apv_other_2160p","apv_16-9_1080p","apv_16-9_720p","apv_16-9_576p","apv_4-3_576p","apv_4-3_720p","apv_4-3_1080p","apv_4-3_2160p","apv_other_2160p",
            "apv_other_1p");

    private static final List<String> ENCODINGS_ALCHIMIE=Arrays.asList("normalize_alchimie_576p","normalize_alchimie_720p","normalize_alchimie_1080p","normalize_alchimie_2160p","normalize_alchimie_1p");

    private static final List<String> ENCODINGS_MEDIACHAIN=Arrays.asList("abr_mediachain_576p","abr_mediachain_720p","abr_mediachain_1080p","abr_mediachain_2160p","abr_mediachain_1p");

    private static final String OTHER="other";
    private static final String SIXTEENTHNINETH="16-9";
    private static final String FourthThird="4-3";


    @Before
    public void setup(){
        FFmpegStream STREAM_4_3 =new FFmpegStream();
        FFmpegStream STREAM_16_9 =new FFmpegStream();
        FFmpegStream STREAM_OTHER =new FFmpegStream();

        STREAM_4_3.setHeight(576).setDisplayAspectRatio("4:3");
        STREAM_16_9.setHeight(1080).setDisplayAspectRatio("16:9");
        STREAM_OTHER.setHeight(2160).setDisplayAspectRatio("******");


        //16:9
        FFmpegStream STREAM_3840_2160 =new FFmpegStream();
        STREAM_3840_2160.setWidth(3840);
        STREAM_3840_2160.setHeight(2160);
        STREAM_3840_2160.setDisplayAspectRatio("");

        FFmpegStream STREAM_1920_1080 =new FFmpegStream();
        STREAM_1920_1080.setWidth(1920);
        STREAM_1920_1080.setHeight(1080);


        FFmpegStream STREAM_1280_720 =new FFmpegStream();
        STREAM_1280_720.setWidth(1280);
        STREAM_1280_720.setHeight(720);
        STREAM_1280_720.setDisplayAspectRatio("");

        FFmpegStream STREAM_1024_576 =new FFmpegStream();
        STREAM_1024_576.setWidth(1024);
        STREAM_1024_576.setHeight(576);
        STREAM_1024_576.setDisplayAspectRatio(null);

        //4:3

        FFmpegStream STREAM_2880_2160 =new FFmpegStream();
        STREAM_2880_2160.setWidth(2880);
        STREAM_2880_2160.setHeight(2160);
        STREAM_2880_2160.setDisplayAspectRatio(null);

        FFmpegStream STREAM_1440_1080 =new FFmpegStream();
        STREAM_1440_1080.setWidth(1440);
        STREAM_1440_1080.setHeight(1080);
        STREAM_1440_1080.setDisplayAspectRatio("");

        FFmpegStream STREAM_960_720 =new FFmpegStream();
        STREAM_960_720.setWidth(960);
        STREAM_960_720.setHeight(720);
        STREAM_960_720.setDisplayAspectRatio(null);

        FFmpegStream STREAM_768_576 =new FFmpegStream();
        STREAM_768_576.setWidth(768);
        STREAM_768_576.setHeight(576);
        STREAM_768_576.setDisplayAspectRatio("");

        //other
        FFmpegStream STREAM_X_X =new FFmpegStream();
        STREAM_X_X.setWidth(1);
        STREAM_X_X.setHeight(1);
        STREAM_X_X.setDisplayAspectRatio(null);


        streams= Arrays.asList(STREAM_4_3,STREAM_16_9,STREAM_OTHER, STREAM_3840_2160 , STREAM_1920_1080 , STREAM_1280_720 ,STREAM_1024_576 ,STREAM_2880_2160 ,STREAM_1440_1080 , STREAM_960_720 , STREAM_768_576 ,STREAM_X_X);







    }


    @Test
    public void testReplacementParamsPresetEncodingAmazon(){
        //test case 1
        List<String> outs=streams.stream().filter(Objects::nonNull).map(s->EncodingTaskHandlerUtils.evaluateEncodingPreset(s,PRESET_AMAZON_PRIME_VIDEO)).distinct().collect(Collectors.toList());
        Assert.assertNotNull(outs);
        Assert.assertEquals(ENCODINS_AMAZON_PRIME_VIDEO.size(),outs.size());
        Assert.assertTrue(outs.containsAll(ENCODINS_AMAZON_PRIME_VIDEO));


    }

    @Test
    public void testReplacementParamsPresetEncodingAlchimie(){

        List<String> outs=streams.stream().filter(Objects::nonNull).map(s->EncodingTaskHandlerUtils.evaluateEncodingPreset(s,PRESET_ALCHIMIE)).distinct().collect(Collectors.toList());
        Assert.assertNotNull(outs);
        Assert.assertEquals(ENCODINGS_ALCHIMIE.size(),outs.size());
        Assert.assertTrue(outs.containsAll(ENCODINGS_ALCHIMIE));

    }

    @Test
    public void testReplacementParamsPresetEncodingMediaChain(){

        List<String> outs=streams.stream().filter(Objects::nonNull).map(s->EncodingTaskHandlerUtils.evaluateEncodingPreset(s,PRESET_MEDIACHAIN)).distinct().collect(Collectors.toList());
        Assert.assertNotNull(outs);
        Assert.assertEquals(ENCODINGS_MEDIACHAIN.size(),outs.size());
        Assert.assertTrue(outs.containsAll(ENCODINGS_MEDIACHAIN));

    }

    @Test
    public void testReplacementParamsPresetEncodingSfr(){

        List<String> outs=streams.stream().filter(Objects::nonNull).map(s->EncodingTaskHandlerUtils.evaluateEncodingPreset(s,PRESET_SFR)).distinct().collect(Collectors.toList());
        Assert.assertNotNull(outs);
        Assert.assertEquals(1,outs.size());
        Assert.assertTrue(outs.contains(PRESET_SFR));

    }




    @Test
    public void testingWithoutError(){

        List<String> outs=streams.stream().filter(Objects::nonNull).map(s->EncodingTaskHandlerUtils.getPresetRatioFromWidthAndHeightInMediainfo(s)).collect(Collectors.toList());
        Assert.assertNotNull(outs);
        Assert.assertFalse(outs.isEmpty());

        List<String> disOuts=outs.stream().filter(Objects::nonNull).distinct().collect(Collectors.toList());

        Assert.assertNotNull(disOuts);
        Assert.assertFalse(disOuts.isEmpty());
        Assert.assertEquals(3,disOuts.size());
        Assert.assertTrue(disOuts.containsAll(Arrays.asList(OTHER,SIXTEENTHNINETH,FourthThird)));


        Assert.assertEquals(4l,outs.stream().filter(s->OTHER.equals(s)).count());
        Assert.assertEquals(4l,outs.stream().filter(s->SIXTEENTHNINETH.equals(s)).count());
        Assert.assertEquals(4l,outs.stream().filter(s->FourthThird.equals(s)).count());
    }






    @Test
    public void testCasesSixteenNineth(){

        FFmpegStream STREAMTen =new FFmpegStream();
        STREAMTen.setWidth(964);
        STREAMTen.setHeight(544);


        FFmpegStream STREAMFourteen =new FFmpegStream();
        STREAMFourteen.setWidth(1280);
        STREAMFourteen.setHeight(720);

        FFmpegStream STREAMTwenty =new FFmpegStream();
        STREAMTwenty.setWidth(1366);
        STREAMTwenty.setHeight(768);

        FFmpegStream STREAMTwentyFour =new FFmpegStream();
        STREAMTwentyFour.setWidth(1600);
        STREAMTwentyFour.setHeight(900);

        FFmpegStream STREAMTwentyEight =new FFmpegStream();
        STREAMTwentyEight.setWidth(1920);
        STREAMTwentyEight.setHeight(1080);

        FFmpegStream STREAMThirtyThree =new FFmpegStream();
        STREAMThirtyThree.setWidth(2560);
        STREAMThirtyThree.setHeight(1440);

        FFmpegStream STREAMForty =new FFmpegStream();
        STREAMForty.setWidth(3840);
        STREAMForty.setHeight(2160);

        FFmpegStream STREAMFortySeven =new FFmpegStream();
        STREAMFortySeven.setWidth(7680);
        STREAMFortySeven.setHeight(4320);

        List<FFmpegStream> oneSixNine=Arrays.asList(STREAMTen,STREAMForty,STREAMFortySeven,STREAMFourteen,STREAMTwenty,STREAMThirtyThree,STREAMTwentyEight,STREAMTwentyFour);

        List<String> outs=oneSixNine.stream().filter(Objects::nonNull).map(s->EncodingTaskHandlerUtils.getPresetRatioFromWidthAndHeightInMediainfo(s)).collect(Collectors.toList());

        Assert.assertNotNull(outs);
        Assert.assertFalse(outs.isEmpty());
        Assert.assertEquals(oneSixNine.size(),outs.size());
        Assert.assertEquals(1l,outs.stream().filter(Objects::nonNull).distinct().count());
        Assert.assertTrue(outs.stream().filter(Objects::nonNull).filter(s->!SIXTEENTHNINETH.equals(s)).collect(Collectors.toList()).isEmpty());

    }

    @Test
    public void testCasesFourthThird(){

        FFmpegStream STREAMTwo =new FFmpegStream();
        STREAMTwo.setWidth(320);
        STREAMTwo.setHeight(240);

        FFmpegStream STREAMSeven =new FFmpegStream();
        STREAMSeven.setWidth(640);
        STREAMSeven.setHeight(480);


        FFmpegStream STREAMFour =new FFmpegStream();
        STREAMFour.setWidth(768);
        STREAMFour.setHeight(576);


        FFmpegStream STREAMSix =new FFmpegStream();
        STREAMSix.setWidth(800);
        STREAMSix.setHeight(600);


        FFmpegStream STREAMTwelve =new FFmpegStream();
        STREAMTwelve.setWidth(1024);
        STREAMTwelve.setHeight(768);

        FFmpegStream STREAMThirteen =new FFmpegStream();
        STREAMThirteen.setWidth(1152);
        STREAMThirteen.setHeight(864);


        FFmpegStream STREAMEighteen =new FFmpegStream();
        STREAMEighteen.setWidth(1280);
        STREAMEighteen.setHeight(960);


        FFmpegStream STREAMTwentyOne=new FFmpegStream();
        STREAMTwentyOne.setWidth(1400);
        STREAMTwentyOne.setHeight(1050);

        FFmpegStream STREAMTwentySix =new FFmpegStream();
        STREAMTwentySix.setWidth(1600);
        STREAMTwentySix.setHeight(1200);


        FFmpegStream STREAMThirtyOne =new FFmpegStream();
        STREAMThirtyOne.setWidth(2048);
        STREAMThirtyOne.setHeight(1536);



        FFmpegStream STREAMThirtyFive =new FFmpegStream();
        STREAMThirtyFive.setWidth(2560);
        STREAMThirtyFive.setHeight(1920);


        FFmpegStream STREAMThirtyEight =new FFmpegStream();
        STREAMThirtyEight.setWidth(3200);
        STREAMThirtyEight.setHeight(2400);



        FFmpegStream STREAMFortyTwo =new FFmpegStream();
        STREAMFortyTwo.setWidth(4096);
        STREAMFortyTwo.setHeight(3072);



        FFmpegStream STREAMFortySix =new FFmpegStream();
        STREAMFortySix.setWidth(6400);
        STREAMFortySix.setHeight(4800);


        FFmpegStream STREAMFortyNine =new FFmpegStream();
        STREAMFortyNine.setWidth(8192);
        STREAMFortyNine.setHeight(6144);

        List<FFmpegStream> fourThree=Arrays.asList( STREAMTwo, STREAMSeven,STREAMFour,STREAMSix, STREAMTwelve,  STREAMThirteen,  STREAMEighteen,STREAMTwentyOne,  STREAMTwentySix,  STREAMThirtyOne,  STREAMThirtyFive,  STREAMThirtyEight,      STREAMFortyTwo,  STREAMFortySix, STREAMFortyNine);

        List<String> outs=fourThree.stream().filter(Objects::nonNull).map(s->EncodingTaskHandlerUtils.getPresetRatioFromWidthAndHeightInMediainfo(s)).collect(Collectors.toList());

        Assert.assertNotNull(outs);
        Assert.assertFalse(outs.isEmpty());
        Assert.assertEquals(fourThree.size(),outs.size());
        Assert.assertEquals(1l,outs.stream().filter(Objects::nonNull).distinct().count());
        Assert.assertTrue(outs.stream().filter(Objects::nonNull).filter(s->!FourthThird.equals(s)).collect(Collectors.toList()).isEmpty());


    }

    @Test
    public void testCasesOther(){

        FFmpegStream STREAMOne =new FFmpegStream();
        STREAMOne.setWidth(320);
        STREAMOne.setHeight(200);



        FFmpegStream STREAMThree =new FFmpegStream();
        STREAMThree.setWidth(320);
        STREAMThree.setHeight(480);





        FFmpegStream STREAMFive =new FFmpegStream();
        STREAMFive.setWidth(800);
        STREAMFive.setHeight(480);



        FFmpegStream STREAMEight =new FFmpegStream();
        STREAMEight.setWidth(854);
        STREAMEight.setHeight(400);

        FFmpegStream STREAMNine =new FFmpegStream();
        STREAMNine.setWidth(960);
        STREAMNine.setHeight(600);



        FFmpegStream STREAMEleven =new FFmpegStream();
        STREAMEleven.setWidth(1024);
        STREAMEleven.setHeight(600);


        FFmpegStream STREAMFifteen =new FFmpegStream();
        STREAMFifteen.setWidth(1280);
        STREAMFifteen.setHeight(768);

        FFmpegStream STREAMSixteen =new FFmpegStream();
        STREAMSixteen.setWidth(1280);
        STREAMSixteen.setHeight(800);

        FFmpegStream STREAMSeventeen =new FFmpegStream();
        STREAMSeventeen.setWidth(1280);
        STREAMSeventeen.setHeight(854);


        FFmpegStream STREAMNineteen =new FFmpegStream();
        STREAMNineteen.setWidth(1280);
        STREAMNineteen.setHeight(1024);






        FFmpegStream STREAMTwentyTwo =new FFmpegStream();
        STREAMTwentyTwo.setWidth(1440);
        STREAMTwentyTwo.setHeight(900);

        FFmpegStream STREAMTwentyThree =new FFmpegStream();
        STREAMTwentyThree.setWidth(1440);
        STREAMTwentyThree.setHeight(960);



        FFmpegStream STREAMTwentyFive =new FFmpegStream();
        STREAMTwentyFive.setWidth(1600);
        STREAMTwentyFive.setHeight(1024);




        FFmpegStream STREAMTwentySeven =new FFmpegStream();
        STREAMTwentySeven.setWidth(1680);
        STREAMTwentySeven.setHeight(1050);



        FFmpegStream STREAMTwentyNine =new FFmpegStream();
        STREAMTwentyNine.setWidth(1920);
        STREAMTwentyNine.setHeight(1200);


        FFmpegStream STREAMThirty =new FFmpegStream();
        STREAMThirty.setWidth(2048);
        STREAMThirty.setHeight(1080);





        FFmpegStream STREAMThirtyTwo =new FFmpegStream();
        STREAMThirtyTwo.setWidth(2560);
        STREAMThirtyTwo.setHeight(1080);



        FFmpegStream STREAMThirtyFour =new FFmpegStream();
        STREAMThirtyFour.setWidth(2560);
        STREAMThirtyFour.setHeight(1600);



        FFmpegStream STREAMThirtySix =new FFmpegStream();
        STREAMThirtySix.setWidth(2560);
        STREAMThirtySix.setHeight(2048);


        FFmpegStream STREAMThirtySeven =new FFmpegStream();
        STREAMThirtySeven.setWidth(3200);
        STREAMThirtySeven.setHeight(2048);



        FFmpegStream STREAMThirtyNine =new FFmpegStream();
        STREAMThirtyNine.setWidth(3440);
        STREAMThirtyNine.setHeight(1440);



        FFmpegStream STREAMFortyOne =new FFmpegStream();
        STREAMFortyOne.setWidth(3840);
        STREAMFortyOne.setHeight(2400);





        FFmpegStream STREAMFortyThree =new FFmpegStream();
        STREAMFortyThree.setWidth(5120);
        STREAMFortyThree.setHeight(3200);

        FFmpegStream STREAMFortyFour =new FFmpegStream();
        STREAMFortyFour.setWidth(5120);
        STREAMFortyFour.setHeight(4096);




        FFmpegStream STREAMFortyFive =new FFmpegStream();
        STREAMFortyFive.setWidth(6400);
        STREAMFortyFive.setHeight(4096);






        FFmpegStream STREAMFortyEight =new FFmpegStream();
        STREAMFortyEight.setWidth(7680);
        STREAMFortyEight.setHeight(4800);

        List<FFmpegStream> other=Arrays.asList(  STREAMOne,STREAMThree,   STREAMFive,  STREAMEight,STREAMNine,  STREAMEleven, STREAMFifteen,STREAMSixteen, STREAMSeventeen, STREAMNineteen,  STREAMTwentyTwo,STREAMTwentyThree, STREAMTwentyFive,   STREAMTwentySeven,  STREAMTwentyNine, STREAMThirty,    STREAMThirtyTwo,  STREAMThirtyFour,    STREAMThirtySix, STREAMThirtySeven,   STREAMThirtyNine,    STREAMFortyOne,    STREAMFortyThree, STREAMFortyFour, STREAMFortyFive,STREAMFortyEight         );

        List<String> outs=other.stream().filter(Objects::nonNull).map(s->EncodingTaskHandlerUtils.getPresetRatioFromWidthAndHeightInMediainfo(s)).collect(Collectors.toList());

        Assert.assertNotNull(outs);
        Assert.assertFalse(outs.isEmpty());
        Assert.assertEquals(other.size(),outs.size());
        Assert.assertEquals(1l,outs.stream().filter(Objects::nonNull).distinct().count());
        Assert.assertTrue(outs.stream().filter(Objects::nonNull).filter(s->!OTHER.equals(s)).collect(Collectors.toList()).isEmpty());

    }

}


