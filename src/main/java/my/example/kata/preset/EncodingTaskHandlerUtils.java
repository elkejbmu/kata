package my.example.kata.preset;


import org.apache.commons.text.StringSubstitutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiPredicate;


/**
 *
 *
 * @author melkejbout HUB-2116
 * class utility to replace preset string parameters with the appropriate values from ffmpeg properties
 * and adapt ratio pattern between ffmpeg and preset encoding
 */

public final class EncodingTaskHandlerUtils {

    private static final Logger logger = LoggerFactory.getLogger(EncodingTaskHandlerUtils.class);


    private static final String HEIGHT_PARAM="height";

    private static final String RATIO_PARAM="aspect_ratio";

     static final String PRESET_ENCODING_RATIO_SEPARATOR="-";


     static final String DEFAULT_RATIO="other";

     private static final double ERR=3.0;


    private static final BiPredicate<FFmpegStream,RATIO> isCorrespondingExactly=(s, r)-> (s.getWidth()*r.getHeight())==(s.getHeight()*r.getWidth());


    private static final BiPredicate<FFmpegStream,RATIO> isCorrespondingWithMarginError=
            (s, r)-> {
        logger.info("stream={width={};height={}} ratio={width={};height={}} error={}",s.getWidth(),s.getHeight(),r.getWidth(),r.getHeight(),Math.abs(100.0*((s.getWidth()*r.getHeight())-(s.getHeight()*r.getWidth())))/(s.getHeight()*r.getWidth()));
        return Math.abs(100.0*((s.getWidth()*r.getHeight())-(s.getHeight()*r.getWidth())))/(s.getHeight()*r.getWidth())<ERR;
            };




    private EncodingTaskHandlerUtils(){
        throw new IllegalStateException("Utility class");
    }

    public  static final String evaluateEncodingPreset(FFmpegStream stream,final String preset) {
        Assert.hasText(preset, "preset is undefined");
        Map<String,String> substitution= new HashMap<>();
        substitution.put(HEIGHT_PARAM, String.valueOf(stream.getHeight()));
        substitution.put(RATIO_PARAM, getPresetRatioFromWidthAndHeightInMediainfo(stream));
        return StringSubstitutor.replace(preset,
                substitution, "#{", "}");
    }


    public static String  getPresetRatioFromWidthAndHeightInMediainfo(FFmpegStream stream){

        Optional<RATIO> ratio=RATIO.nonNullValues().stream().filter(r->isCorrespondingExactly.test(stream,r)).findFirst();
        if(ratio.isPresent()){
            return ratio.get().toDisplayRatio();
        }else if(stream.getHeight()>0 && stream.getWidth()>0){
            ratio=RATIO.nonNullValues().stream().filter(r->isCorrespondingWithMarginError.test(stream,r)).findFirst();
            return ratio.isPresent()?ratio.get().toDisplayRatio():RATIO.OTHERRATIOS.toDisplayRatio();
        }else{
            return RATIO.OTHERRATIOS.toDisplayRatio();
        }
    }

}
